package com.mame.impression.manager;

import java.util.List;

import com.ibm.icu.text.IDNA.Error;
import com.mame.impression.Result;
import com.mame.impression.Result.ActionResult;
import com.mame.impression.constant.Constants;
import com.mame.impression.dao.ImpressionDaoFactory;
import com.mame.impression.dao.QuestionDao;
import com.mame.impression.dao.UserDao;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.UserData;
import com.mame.impression.util.LogUtil;

public class ImpressionDataManager {

	private static final String TAG = Constants.TAG
			+ ImpressionDataManager.class.getSimpleName();

	private static ImpressionDataManager sInstance = new ImpressionDataManager();

	private static UserDao mUserDao = ImpressionDaoFactory.getDaoFactory()
			.getUserDao();

	private static QuestionDao mQuestionDao = ImpressionDaoFactory
			.getDaoFactory().getQuestionDao();

	/**
	 * Singletone
	 */
	private ImpressionDataManager() {

	}

	public static ImpressionDataManager getInstance() {
		return sInstance;
	}

	public synchronized UserData findUserDataByName(Result result,
			String userName) {
		LogUtil.d(TAG, "findUserDataByName");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (userName == null) {
			result.setActionResult(ActionResult.FAIL);
			throw new IllegalArgumentException("user name cannot be null");
		}

		return mUserDao.findUserDataByName(userName);
	}

	// public synchronized long findUserIdByName(Result result, String userName)
	// {
	// LogUtil.d(TAG, "findUserIdByName");
	//
	// if (result == null) {
	// throw new IllegalArgumentException("Result cannot be null");
	// }
	//
	// if (userName == null) {
	// result.setActionResult(ActionResult.FAIL);
	// throw new IllegalArgumentException("user name cannot be null");
	// }
	//
	// return mUserDao.findUserIdByName(userName);
	// }

	public synchronized void storeNewUserData(Result result, UserData data) {
		LogUtil.d(TAG, "storeNewUserData");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (data == null) {
			throw new IllegalArgumentException("user data cannot be null");
		}

		mUserDao.storeNewUserData(data);

	}

	public synchronized UserData updateUserData(Result result, UserData data) {
		LogUtil.d(TAG, "updateUserData");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (data == null) {
			throw new IllegalArgumentException("user data cannot be null");
		}

		// TODO
		mUserDao.updateUserData(data);

		return null;
	}

	public synchronized QuestionData saveNewQuestionData(Result result,
			QuestionData data) {
		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (data == null) {
			throw new IllegalArgumentException("question data cannot be null");
		}

		mQuestionDao.storeNewQuestionData(data);

		// TODO
		return null;
	}

	public List<QuestionData> getLatestQuestionList(Result result, int start,
			int end) {

		return mQuestionDao.getLatestQuestionData(start, end);

	}

}
