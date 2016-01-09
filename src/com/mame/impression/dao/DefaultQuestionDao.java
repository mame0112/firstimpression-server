package com.mame.impression.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.mame.impression.Result;
import com.mame.impression.Result.ErrorType;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.QuestionDataBuilder;
import com.mame.impression.data.ResultDetailData;
import com.mame.impression.data.ResultDetailDataBuilder;
import com.mame.impression.data.ResultListData;
import com.mame.impression.data.UserData;
import com.mame.impression.data.QuestionData.Category;
import com.mame.impression.datastore.DatastoreKeyFactory;
import com.mame.impression.datastore.DbConstant;
import com.mame.impression.datastore.ImpressionDatastoreHelper;
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

		List<QuestionData> questions = new ArrayList<QuestionData>();

		Query q = new Query(DbConstant.KIND_QUESTION);
		PreparedQuery pq = mDS.prepare(q);

		for (Entity entity : pq.asIterable()) {
			ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
			questions.add(helper.createQuestionDataFromEntity(entity));
			// long id = (Long)
			// entity.getProperty(DbConstant.ENTITY_QUESTION_ID);
			// String description = (String) entity
			// .getProperty(DbConstant.ENTITY_QUESTION_DESCRIPTION);
			// LogUtil.d(TAG, "id: " + id);
			//
			// QuestionDataBuilder builder = new QuestionDataBuilder();
			//
			// // TODO
			// questions.add(builder.setQuestionId(id).setDescription(description)
			// .getResult());
		}

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

	@Override
	public void saveQuestionResponseData(Result result, long questionId,
			int choice) {
		LogUtil.d(TAG, "saveQuestionResponseData");

		try {
			Key key = DatastoreKeyFactory.getQuestionKey(questionId);

			Entity e = DatastoreHandler.get(result, key);

			if (e != null) {

				long num = 0;

				switch (choice) {
				case 0:
					num = (long) e
							.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_A_RESP);
					num = num + 1;
					e.setProperty(DbConstant.ENTITY_QUESTION_CHOICE_A_RESP, num);
					break;
				case 1:
					num = (long) e
							.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_B_RESP);
					num = num + 1;
					e.setProperty(DbConstant.ENTITY_QUESTION_CHOICE_B_RESP, num);
					break;
				default:
					LogUtil.w(TAG, "Illegal choice value");
					result.setErrorType(ErrorType.FAILED_TO_WRITE_TO_DB);
					result.setErrorMessage("Illegal choice value");
					break;
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

			ResultListData data = helper
					.createResultListDataFromEntity(e);
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

		long createUserId = (long) e.getProperty(DbConstant.ENTITY_QUESTION_ID);
		String description = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_DESCRIPTION);
		String choiceA = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_A);
		String choiceB = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_B);
		int male = (int) e.getProperty(DbConstant.ENTITY_QUESTION_GENDER_MALE);
		int female = (int) e
				.getProperty(DbConstant.ENTITY_QUESTION_GENDER_FEMALE);
		int genderUnknown = (int) e
				.getProperty(DbConstant.ENTITY_QUESTION_GENDER_UNKNOWN);
		int under10 = (int) e
				.getProperty(DbConstant.ENTITY_QUESTION_AGE_UNDER10);
		int from10_20 = (int) e
				.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM10_20);
		int from20_30 = (int) e
				.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM20_30);
		int from30_40 = (int) e
				.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM30_40);
		int from40_50 = (int) e
				.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM40_50);
		int from50_60 = (int) e
				.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM50_60);
		int from60_70 = (int) e
				.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM60_70);
		int over70 = (int) e.getProperty(DbConstant.ENTITY_QUESTION_AGE_OVER70);
		String additionalQuestion = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_ADDITIONAL_QUESTION);
		List<String> additionalAnswer = (List) e
				.getProperty(DbConstant.ENTITY_QUESTION_ADDITIONAL_ANSWER);
		String thumbnail = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_CREATED_USER_THUMB);
		String category = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_CATEGORY);
		String createdUserName = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_CREATED_USERNAME);
		long createdDate = (Long) e
				.getProperty(DbConstant.ENTITY_QUESTION_POST_DATE);

		ResultDetailDataBuilder builder = new ResultDetailDataBuilder();

		return builder.setQuestionId(questionId).setDescription(description)
				.setChoiceA(choiceA).setChoiceB(choiceB).setMale(male)
				.setFemale(female).setGenderUnknown(genderUnknown)
				.setUnder10(under10).setFrom10_20(from10_20)
				.setFrom20_30(from20_30).setFrom30_40(from30_40)
				.setFrom40_50(from40_50).setFrom50_60(from50_60)
				.setFrom60_70(from60_70).setOver70(over70)
				.setThumbnail(thumbnail).setPostDate(createdDate)
				.setCreatedUserId(createUserId)
				.setCreatedUserName(createdUserName).setCategory(category)
				.setAdditionalQuestion(additionalQuestion)
				.setAdditionalComments(additionalAnswer).getResult();
	}

}
