package com.mame.impression.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.mame.impression.Result;
import com.mame.impression.Result.ErrorType;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.QuestionDataBuilder;
import com.mame.impression.datastore.DatastoreKeyFactory;
import com.mame.impression.datastore.DbConstant;
import com.mame.impression.datastore.ImpressionDatastoreHelper;
import com.mame.impression.util.LogUtil;

public class DefaultQuestionDao implements QuestionDao {

	private static final String TAG = Constants.TAG
			+ DefaultQuestionDao.class.getSimpleName();

	private final static DatastoreService mDS = DatastoreServiceFactory
			.getDatastoreService();

	public QuestionData storeNewQuestionData(Result result, QuestionData data) {
		LogUtil.d(TAG, "storeNewQuestionData");

		if (data == null) {
			throw new IllegalArgumentException("QuestionData cannot be null");
		}

		Key key = DatastoreKeyFactory.getQuestionKey(data.getQuestionId());
		Entity entity = new Entity(key);

		ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
		helper.putQuestionDataToEntity(data, entity);

		DatastoreHandler.put(result, entity);

		// TODO
		return null;

	}

	public List<QuestionData> getLatestQuestionData(Result result, int start,
			int end) {

		LogUtil.d(TAG, "getLatestQuestionData");

		if (start < 0) {
			throw new IllegalArgumentException(
					"Start index must be more than 1");
		}

		if (end < 0) {
			throw new IllegalArgumentException("End index must be more than 1");
		}

		if (start > end) {
			throw new IllegalArgumentException(
					"Start index must be larger than end index");
		}

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
		helper.putQuestionDataToEntity(data, e);

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

}
