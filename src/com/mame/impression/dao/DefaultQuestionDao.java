package com.mame.impression.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.Constant;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.mame.impression.Result;
import com.mame.impression.Result.ActionResult;
import com.mame.impression.Result.ErrorType;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.IndexUserData;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.QuestionData.Category;
import com.mame.impression.data.ResultDetailData;
import com.mame.impression.data.ResultDetailDataBuilder;
import com.mame.impression.data.ResultDetailDataItem;
import com.mame.impression.data.ResultListData;
import com.mame.impression.data.UserData.Age;
import com.mame.impression.data.UserData.Gender;
import com.mame.impression.datastore.DatastoreKeyFactory;
import com.mame.impression.datastore.DbConstant;
import com.mame.impression.datastore.ImpressionDatastoreHelper;
import com.mame.impression.datastore.MemcacheConstant;
import com.mame.impression.datastore.MemcacheHandler;
import com.mame.impression.util.LogUtil;

public class DefaultQuestionDao implements QuestionDao {

	private static final String TAG = Constants.TAG
			+ DefaultQuestionDao.class.getSimpleName();

	private final static DatastoreService mDS = DatastoreServiceFactory
			.getDatastoreService();

	/**
	 * Put new Question to Datastore. All results shall be put into Result.
	 */
	public void storeNewQuestionData(Result result, QuestionData data) {
		LogUtil.d(TAG, "storeNewQuestionData");

		if (data == null) {
			throw new IllegalArgumentException("QuestionData cannot be null");
		}

		Key key = DatastoreKeyFactory.getQuestionKey(data.getQuestionId());
		Entity entity = new Entity(key);

		ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
		helper.putQuestionDataToEntity(result, data, entity);

		DatastoreHandler.put(result, entity);

	}

	public List<QuestionData> getLatestQuestionData(Result result) {

		LogUtil.d(TAG, "getLatestQuestionData");

		List<QuestionData> questions = (List<QuestionData>) MemcacheHandler
				.get(DbConstant.KIND_QUESTION, MemcacheConstant.LATEST_QUESTION);

		// If memcache already exists, return it.
		if (questions != null && questions.size() != 0) {
			LogUtil.d(TAG, "memcache exists");
			return questions;
		}

		questions = new ArrayList<QuestionData>();

		Query q = new Query(DbConstant.KIND_QUESTION);
		q.addSort(DbConstant.ENTITY_QUESTION_POST_DATE,
				SortDirection.DESCENDING);
		FetchOptions fetchOptions = FetchOptions.Builder
				.withLimit(Constants.LATEST_QUESTION_LIST_NUM);
		PreparedQuery pq = mDS.prepare(q);

		for (Entity entity : pq.asIterable(fetchOptions)) {
			ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
			questions.add(helper.createQuestionDataFromEntity(entity));
		}

		// Store data to memcache
		MemcacheHandler.put(DbConstant.KIND_QUESTION,
				MemcacheConstant.LATEST_QUESTION, questions);

		return questions;
	}

	@Override
	public List<QuestionData> getOlderQuestionData(Result result) {
		LogUtil.d(TAG, "getOlderQuestionData");

		List<QuestionData> questions = (List<QuestionData>) MemcacheHandler
				.get(DbConstant.KIND_QUESTION, MemcacheConstant.OLDER_QUESTION);

		// If memcache already exists, return it.
		if (questions != null && questions.size() != 0) {
			LogUtil.d(TAG, "memcache exists");
			return questions;
		}

		questions = new ArrayList<QuestionData>();

		Query q = new Query(DbConstant.KIND_QUESTION);
		q.addSort(DbConstant.ENTITY_QUESTION_POST_DATE,
				SortDirection.DESCENDING);
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(
				Constants.OLDER_QUESTION_LIST_NUM).offset(
				Constants.LATEST_QUESTION_LIST_NUM);
		PreparedQuery pq = mDS.prepare(q);

		// TODO Need to consider how we retrieve old questions if the number of
		// question increased
		// int totalNum = pq.countEntities(FetchOptions.Builder.withDefaults());

		for (Entity entity : pq.asIterable(fetchOptions)) {
			ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
			questions.add(helper.createQuestionDataFromEntity(entity));

		}

		// Store data to memcache
		MemcacheHandler.put(DbConstant.KIND_QUESTION,
				MemcacheConstant.OLDER_QUESTION, questions);

		return questions;
	}

	public void updateQuestionData(Result result, QuestionData data) {

		LogUtil.d(TAG, "updateQuestionData");

		if (data == null) {
			throw new IllegalArgumentException("QuestionData cannot be null");
		}

		Key key = DatastoreKeyFactory.getQuestionKey(data.getQuestionId());
		ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();

		// TODO Need to consider if we can complete override whole current data
		// by new data
		Entity e = new Entity(key);
		helper.putQuestionDataToEntity(result, data, e);

		mDS.put(e);

	}

	public QuestionData findQuestionById(Result result, long questionId) {

		LogUtil.d(TAG, "findQuestionById");

		if (questionId == Constants.NO_QUESTION) {
			throw new IllegalArgumentException("Illegal Question id");
		}

		Key key = DatastoreKeyFactory.getQuestionKey(questionId);

		Entity e = DatastoreHandler.get(result, key);
		ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();

		return helper.createQuestionDataFromEntity(e);

	}

	/**
	 * Return created user name and user id . If error ocurrs. returns null
	 */
	@Override
	public IndexUserData saveQuestionResponseData(Result result,
			long questionId, int choice, Gender gender, Age age) {
		LogUtil.d(TAG, "saveQuestionResponseData");

		IndexUserData indexUserData = null;

		try {
			Key key = DatastoreKeyFactory.getQuestionKey(questionId);

			Entity e = DatastoreHandler.get(result, key);

			if (e != null) {

				long num = 0;
				long createdUserId = Constants.NO_USER;
				String createdUserName = null;
				String description = null;

				switch (choice) {
				case 0:
					num = (long) e
							.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_A_RESP);
					num = num + 1;
					e.setProperty(DbConstant.ENTITY_QUESTION_CHOICE_A_RESP, num);
					createdUserId = (long) e
							.getProperty(DbConstant.ENTITY_QUESTION_CREATED_USERID);
					createdUserName = (String) e
							.getProperty(DbConstant.ENTITY_QUESTION_CREATED_USERNAME);
					description = (String) e
							.getProperty(DbConstant.ENTITY_QUESTION_DESCRIPTION);
					indexUserData = new IndexUserData(createdUserId,
							createdUserName, description);
					break;
				case 1:
					num = (long) e
							.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_B_RESP);
					num = num + 1;
					e.setProperty(DbConstant.ENTITY_QUESTION_CHOICE_B_RESP, num);
					createdUserId = (long) e
							.getProperty(DbConstant.ENTITY_QUESTION_CREATED_USERID);
					createdUserName = (String) e
							.getProperty(DbConstant.ENTITY_QUESTION_CREATED_USERNAME);
					description = (String) e
							.getProperty(DbConstant.ENTITY_QUESTION_DESCRIPTION);
					indexUserData = new IndexUserData(createdUserId,
							createdUserName, description);

					break;
				default:
					LogUtil.w(TAG, "Illegal choice value");
					result.setErrorType(ErrorType.FAILED_TO_WRITE_TO_DB);
					result.setErrorMessage("Illegal choice value");
					break;
				}

				// Store response data for each gender and age.
				if (gender != null && age != null) {
					ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
					helper.setGenderAndAgeData(e, gender, age, choice);
				}

				// Store back to Datastore
				DatastoreHandler.put(result, e);
			} else {
				LogUtil.w(TAG, "Entity is null");
				result.setErrorType(ErrorType.FAILED_TO_WRITE_TO_DB);
				result.setErrorMessage("Entity is null");
			}
		} catch (Exception e) {
			LogUtil.w(TAG, "Exception: " + e.getMessage());
			result.setErrorType(ErrorType.FAILED_TO_WRITE_TO_DB);
		}

		return indexUserData;
	}

	@Override
	public List<ResultListData> getQuestionResultList(Result result,
			long createdUserId) {
		LogUtil.d(TAG, "getQuestionResultList");

		ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();

		List<ResultListData> results = new ArrayList<ResultListData>();

		Filter createdUserIdFilter = new FilterPredicate(
				DbConstant.ENTITY_QUESTION_CREATED_USERID,
				FilterOperator.EQUAL, createdUserId);

		Query q = new Query(DbConstant.KIND_QUESTION)
				.setFilter(createdUserIdFilter);

		PreparedQuery pq = mDS.prepare(q);

		for (Entity e : pq.asIterable()) {

			ResultListData data = helper.createResultListDataFromEntity(e);
			results.add(data);
		}
		return results;
	}

	@Override
	public ResultDetailData getQuestionResultDetail(Result result,
			long questionId) {
		LogUtil.d(TAG, "getQuestionResultDetail");

		Key key = DatastoreKeyFactory.getQuestionKey(questionId);
		Entity e = DatastoreHandler.get(result, key);

		// Get common part
		// Mandatory field
		long createUserId = Constants.NO_USER;
		String description = null;
		String choiceA = null;
		String choiceB = null;
		ResultDetailDataItem choiceAItem = null;
		ResultDetailDataItem choiceBItem = null;
		try {
			createUserId = (long) e.getProperty(DbConstant.ENTITY_QUESTION_ID);
			description = (String) e
					.getProperty(DbConstant.ENTITY_QUESTION_DESCRIPTION);
			choiceA = (String) e
					.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_A);
			choiceB = (String) e
					.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_B);

			ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
			choiceAItem = helper.getAnswerResultResultItemA(e);
			choiceBItem = helper.getAnswerResultResultItemB(e);

		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
			result.setActionResult(ActionResult.FAIL);
			result.setErrorMessage("No information for mandatory field");
			result.setErrorType(ErrorType.LACK_OF_GIVEN_PARAMETER);
			return null;
		}

		String additionalQuestion = null;
		try {
			additionalQuestion = (String) e
					.getProperty(DbConstant.ENTITY_QUESTION_ADDITIONAL_QUESTION);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		List<String> additionalAnswer = new ArrayList<String>();
		try {
			additionalAnswer = (List) e
					.getProperty(DbConstant.ENTITY_QUESTION_ADDITIONAL_ANSWER);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		String thumbnail = null;
		try {
			thumbnail = (String) e
					.getProperty(DbConstant.ENTITY_QUESTION_CREATED_USER_THUMB);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		String category = Category.GENERAL.name();
		try {
			category = (String) e
					.getProperty(DbConstant.ENTITY_QUESTION_CATEGORY);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		String createdUserName = null;
		try {
			createdUserName = (String) e
					.getProperty(DbConstant.ENTITY_QUESTION_CREATED_USERNAME);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		long createdDate = 0L;
		try {
			createdDate = (Long) e
					.getProperty(DbConstant.ENTITY_QUESTION_POST_DATE);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		ResultDetailDataBuilder builder = new ResultDetailDataBuilder();

		return builder.setQuestionId(questionId).setDescription(description)
				.setChoiceA(choiceA).setChoiceB(choiceB)
				.setThumbnail(thumbnail).setPostDate(createdDate)
				.setCreatedUserId(createUserId)
				.setCreatedUserName(createdUserName).setCategory(category)
				.setAdditionalQuestion(additionalQuestion)
				.setAdditionalComments(additionalAnswer)
				.setChoiceAItem(choiceAItem).setChoiceBItem(choiceBItem)
				.getResult();
	}

}
