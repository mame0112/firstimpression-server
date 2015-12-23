package com.mame.impression.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.QuestionDataBuilder;
import com.mame.impression.datastore.DatastoreKeyFactory;
import com.mame.impression.datastore.DbConstant;
import com.mame.impression.datastore.ImpressionDatastoreHelper;
import com.mame.impression.util.LogUtil;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class DefaultQuestionDao implements QuestionDao {

	private static final String TAG = Constants.TAG
			+ DefaultQuestionDao.class.getSimpleName();

	private final static DatastoreService mDS = DatastoreServiceFactory
			.getDatastoreService();

	public QuestionData storeNewQuestionData(QuestionData data) {
		LogUtil.d(TAG, "storeNewQuestionData");

		if (data == null) {
			throw new IllegalArgumentException("QuestionData cannot be null");
		}

		Key key = DatastoreKeyFactory.getQuestionKey(data.getQuestionId());
		Entity entity = new Entity(key);

		ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
		helper.putQuestionDataToEntity(data, entity);

		mDS.put(entity);

		// TODO
		return null;

	}

	public List<QuestionData> getLatestQuestionData(int start, int end) {

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

		List<QuestionData> result = new ArrayList<QuestionData>();

		Query q = new Query(DbConstant.KIND_QUESTION);
		PreparedQuery pq = mDS.prepare(q);

		for (Entity entity : pq.asIterable()) {
			long id = (Long) entity.getProperty(DbConstant.ENTITY_QUESTION_ID);
			String description = (String) entity
					.getProperty(DbConstant.ENTITY_QUESTION_DESCRIPTION);
			LogUtil.d(TAG, "id: " + id);

			QuestionDataBuilder builder = new QuestionDataBuilder();

			// TODO
			result.add(builder.setQuestionId(id).setDescription(description)
					.getResult());
		}

		// Filter questionFilter = new
		// FilterPredicate(Entity.KEY_RESERVED_PROPERTY,
		// FilterOperator., minHeight);

		return result;
	}

	public void updateQuestionData(QuestionData data) {

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

	public QuestionData findQuestionById(long questionId) {

		LogUtil.d(TAG, "findQuestionById");

		if (questionId == Constants.NO_QUESTION) {
			throw new IllegalArgumentException("Illegal Question id");
		}

		Key key = DatastoreKeyFactory.getQuestionKey(questionId);

		try {
			Entity e = mDS.get(key);
			ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();

			return helper.createQuestionDataFromEntity(e);

		} catch (EntityNotFoundException e) {
			LogUtil.d(TAG, "EntityNotFoundException: " + e.getMessage());
		}

		return null;
	}

}
