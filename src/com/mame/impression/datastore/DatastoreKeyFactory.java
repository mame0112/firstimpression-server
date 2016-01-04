package com.mame.impression.datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.mame.impression.constant.Constants;
import com.mame.impression.util.LogUtil;

public class DatastoreKeyFactory {

	private static final String TAG = DatastoreKeyFactory.class.getSimpleName();

	public static Key getUserIdKey(String userName, long userId) {

		LogUtil.d(TAG, "getUserIdKey");

		if (userName == null) {
			throw new IllegalArgumentException("user name can not be null");
		}

		if (userId == Constants.NO_USER) {
			throw new IllegalArgumentException("user id must be more than 0");
		}

		return KeyFactory.createKey(DbConstant.KIND_USER,
				generateUserKeyIdentifier(userName, userId));
	}

	public static Key getQuestionKey(long questionId) {
		if (questionId == Constants.NO_QUESTION) {
			throw new IllegalArgumentException(
					"question id must be more than 0");
		}

		return KeyFactory.createKey(DbConstant.KIND_QUESTION, questionId);
	}

	private static String generateUserKeyIdentifier(String userName, long userId) {
		return userName + DbConstant.KEY_DIVIDER + userId;
	}
}
