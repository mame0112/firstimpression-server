package com.mame.impression.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.UserData;
import com.mame.impression.datastore.DatastoreKeyFactory;
import com.mame.impression.datastore.ImpressionDatastoreHelper;
import com.mame.impression.util.LogUtil;

public class DefaultUserDao implements UserDao {

	private static final String TAG = Constants.TAG
			+ DefaultUserDao.class.getSimpleName();

	private final static DatastoreService mDS = DatastoreServiceFactory
			.getDatastoreService();

	public UserData findUserDataByName(String userName) {
		LogUtil.d(TAG, "findUserDataByName");

		return null;
	}

	public void storeNewUserData(UserData data) {
		LogUtil.d(TAG, "storeNewUserData");
		Key key = DatastoreKeyFactory.getUserIdKey(data.getUserName(),
				data.getUserId());
		Entity entity = new Entity(key);

		ImpressionDatastoreHelper helper = new ImpressionDatastoreHelper();
		helper.putUserDataToEntity(data, entity);

		mDS.put(entity);

	}

	public UserData updateUserData(UserData data) {
		LogUtil.d(TAG, "updateUserData");
		return null;
	}

}
