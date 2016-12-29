package com.mame.impression.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.mame.impression.Result;
import com.mame.impression.Result.ActionResult;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.UserData;
import com.mame.impression.datastore.DatastoreKeyFactory;
import com.mame.impression.datastore.DbConstant;
import com.mame.impression.datastore.ImpressionDatastoreHelper;
import com.mame.impression.util.LogUtil;
import com.google.appengine.api.datastore.FetchOptions;
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

	public boolean isUserNameExist(Result result, String userName) {
		LogUtil.d(TAG, "isUserNameExist");

		Key firstKey = DatastoreKeyFactory.getUserNameKey(userName);
		Key lastKey = DatastoreKeyFactory
				.getUserNameKey(userName + ASCII_FIRST);

		Filter userFirstFilter = new FilterPredicate(
				Entity.KEY_RESERVED_PROPERTY,
				FilterOperator.GREATER_THAN_OR_EQUAL, firstKey);
		Filter userLastFilter = new FilterPredicate(
				Entity.KEY_RESERVED_PROPERTY, FilterOperator.LESS_THAN, lastKey);

		Query q = new Query(DbConstant.KIND_USER)
				.setFilter(CompositeFilterOperator.and(userFirstFilter,
						userLastFilter));

		PreparedQuery pq = mDS.prepare(q);
		List<Entity> eList = pq.asList(FetchOptions.Builder.withOffset(0));
		if (eList != null && eList.size() != 0) {
			return true;
		}

		return false;
	}

	public UserData findUserDataByNameAndPassword(Result result,
			String userName, String password) {
		LogUtil.d(TAG, "findUserDataByNameAndPassword");

		Key firstKey = DatastoreKeyFactory.getUserNameKey(userName);
		Key lastKey = DatastoreKeyFactory
				.getUserNameKey(userName + ASCII_FIRST);

		Filter userFirstFilter = new FilterPredicate(
				Entity.KEY_RESERVED_PROPERTY,
				FilterOperator.GREATER_THAN_OR_EQUAL, firstKey);
		Filter userLastFilter = new FilterPredicate(
				Entity.KEY_RESERVED_PROPERTY, FilterOperator.LESS_THAN, lastKey);

		Query q = new Query(DbConstant.KIND_USER)
				.setFilter(CompositeFilterOperator.and(userFirstFilter,
						userLastFilter));

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

	public UserData getUserData(Result result, long userId, String userName) {
		LogUtil.d(TAG, "getUserData");

		Key key = DatastoreKeyFactory.getUserIdKey(userName, userId);
		Entity entity = DatastoreHandler.get(result, key);

		if (entity != null) {
			ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
			return helper.createUserDataFromEntity(entity);
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
		// Entity entity = new Entity(key);
		Entity entity = DatastoreHandler.get(result, key);

		ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
		helper.putUserDataToEntityWithoutOverride(data, entity);
		DatastoreHandler.put(result, entity);
	}

	/**
	 * Update deviceid. This shall be used for Sign out case (in this case, to
	 * be given deviceId shall be null)
	 */
	@Override
	public void updateDeviceId(Result result, long userId, String userName,
			String deviceId) {
		LogUtil.d(TAG, "updateDeviceId");

		Key key = DatastoreKeyFactory.getUserIdKey(userName, userId);
		Entity e = DatastoreHandler.get(result, key);

		// If key based ID is found
		if (e != null) {
			// Update Device.
			e.setProperty(DbConstant.ENTITY_USER_DEVICE_ID, deviceId);
			DatastoreHandler.put(result, e);
		}

	}

}
