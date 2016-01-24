package com.mame.impression.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.mame.impression.Result;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.UserData;
import com.mame.impression.datastore.DatastoreKeyFactory;
import com.mame.impression.datastore.DbConstant;
import com.mame.impression.datastore.ImpressionDatastoreHelper;
import com.mame.impression.util.LogUtil;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;

public class DefaultUserDao implements UserDao {

	private static final String TAG = Constants.TAG
			+ DefaultUserDao.class.getSimpleName();

	private final static DatastoreService mDS = DatastoreServiceFactory
			.getDatastoreService();

	private final static String ASCII_FIRST = "0";

	public UserData findUserDataByNameAndPassword(Result result,
			String userName, String password) {
		LogUtil.d(TAG, "findUserDataByNameAndPassword");

		Key firstKey = DatastoreKeyFactory.getUserNameKey(userName);
		Key lastKey = DatastoreKeyFactory.getUserNameKey(userName + ASCII_FIRST);

		Filter userFirstFilter = new FilterPredicate(
				Entity.KEY_RESERVED_PROPERTY,
				FilterOperator.GREATER_THAN_OR_EQUAL, firstKey);
		Filter userLastFilter = new FilterPredicate(
				Entity.KEY_RESERVED_PROPERTY, FilterOperator.LESS_THAN, lastKey);
		
		Query q = new Query(DbConstant.KIND_USER).setFilter(CompositeFilterOperator.and(userFirstFilter, userLastFilter));
		
		PreparedQuery pq = mDS.prepare(q);

		for (Entity e : pq.asIterable()) {
			ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
			UserData data = helper.createUserDataFromEntity(e);
			if (password.equals(data.getPassword())) {
				return data;
			}
		}

		return null;
	}

	public void storeNewUserData(Result result, UserData data) {
		LogUtil.d(TAG, "storeNewUserData");
		Key key = DatastoreKeyFactory.getUserIdKey(data.getUserName(),
				data.getUserId());
		Entity entity = new Entity(key);

		ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
		helper.putUserDataToEntity(data, entity);

		DatastoreHandler.put(result, entity);

	}

	public void updateUserData(Result result, UserData data) {
		LogUtil.d(TAG, "updateUserData");

		Key key = DatastoreKeyFactory.getUserIdKey(data.getUserName(),
				data.getUserId());
		Entity entity = new Entity(key);

		ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
		helper.putUserDataToEntityWithoutOverride(data, entity);
		DatastoreHandler.put(result, entity);
	}

	@Override
	public void signOut(Result result, long userId, String userName) {
		LogUtil.d(TAG,  "signOut");
		
		Key key = DatastoreKeyFactory.getUserIdKey(userName, userId);
		Entity e = DatastoreHandler.get(result, key);
		
		//Update Device id to null
		e.setProperty(DbConstant.ENTITY_USER_DEVICE_ID, null);
		DatastoreHandler.put(result, e);
		
	}

}
