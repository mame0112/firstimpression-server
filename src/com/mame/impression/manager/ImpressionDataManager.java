package com.mame.impression.manager;

import java.util.ArrayList;
import java.util.List;

import com.mame.impression.Result;
import com.mame.impression.Result.ActionResult;
import com.mame.impression.constant.Constants;
import com.mame.impression.dao.ImpressionDaoFactory;
import com.mame.impression.dao.QuestionDao;
import com.mame.impression.dao.UserDao;
import com.mame.impression.data.IndexUserData;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.ResultDetailData;
import com.mame.impression.data.ResultListData;
import com.mame.impression.data.UserData;
import com.mame.impression.data.UserData.Age;
import com.mame.impression.data.UserData.Gender;
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

	public synchronized boolean isUserNameAlreadyUsed(Result result,
			String userName) {
		LogUtil.d(TAG, "isUserNameAlreadyUsed");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (userName == null) {
			result.setActionResult(ActionResult.FAIL);
			throw new IllegalArgumentException("user name cannot be null");
		}

		return mUserDao.isUserNameExist(result, userName);
	}

	public synchronized UserData findUserDataByNameAndPassword(Result result,
			String userName, String password) {
		LogUtil.d(TAG, "findUserDataByNameAndPassword");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (userName == null) {
			result.setActionResult(ActionResult.FAIL);
			throw new IllegalArgumentException("user name cannot be null");
		}

		return mUserDao.findUserDataByNameAndPassword(result, userName,
				password);
	}

	public synchronized void storeNewUserData(Result result, UserData data) {
		LogUtil.d(TAG, "storeNewUserData");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (data == null) {
			throw new IllegalArgumentException("user data cannot be null");
		}

		mUserDao.storeNewUserData(result, data);

	}

	public synchronized UserData getUserData(Result result, long userId,
			String userName) {
		LogUtil.d(TAG, "getUserData");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (userId == Constants.NO_USER) {
			throw new IllegalArgumentException("User Id cannot be NO_USER");
		}

		if (userName == null) {
			throw new IllegalArgumentException("User name cannot be null");
		}

		return mUserDao.getUserData(result, userId, userName);
	}

	public synchronized void updateDeviceId(Result result, long userId,
			String userName, String deviceId) {
		LogUtil.d(TAG, "updateDeviceId");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (userId == Constants.NO_USER) {
			throw new IllegalArgumentException("User Id cannot be NO_USER");
		}

		if (userName == null) {
			throw new IllegalArgumentException("User name cannot be null");
		}

		if (deviceId == null) {
			throw new IllegalArgumentException("Device Id cannot be null");
		}

		mUserDao.updateDeviceId(result, userId, userName, deviceId);

	}

	public synchronized void updateUserData(Result result, UserData data) {
		LogUtil.d(TAG, "updateUserData");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (data == null) {
			result.setActionResult(ActionResult.FAIL);
			throw new IllegalArgumentException("user data cannot be null");
		}

		mUserDao.updateUserData(result, data);

	}

	public synchronized void signOut(Result result, long userId, String userName) {
		LogUtil.d(TAG, "signOut");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (userId == Constants.NO_USER) {
			throw new IllegalArgumentException("User id cannot be NO_USER");
		}

		if (userName == null) {
			throw new IllegalArgumentException("User name cannot be null");
		}

		mUserDao.updateDeviceId(result, userId, userName, null);

	}

	public synchronized void saveNewQuestionData(Result result,
			QuestionData data) {
		LogUtil.d(TAG, "saveNewQuestionData");
		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (data == null) {
			throw new IllegalArgumentException("question data cannot be null");
		}

		mQuestionDao.storeNewQuestionData(result, data);

	}

	/**
	 * Store response data to Database. Return created user id and user name.
	 * 
	 * @param result
	 *            Mandatory field.
	 * @param questionId
	 *            Mandatory field.
	 * @param choice
	 *            Mandatory field.
	 * @param gender
	 *            Optional field since user might be able to respond to question
	 *            with anonymous.
	 * @param age
	 *            Optional field since user might be able to respond to question
	 *            with anonymous.
	 */
	public synchronized IndexUserData respondToQuestion(Result result,
			long questionId, int choice, Gender gender, Age age) {

		LogUtil.d(TAG, "respondToQuestion");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (questionId == Constants.NO_QUESTION) {
			throw new IllegalArgumentException("question id must more than 0");
		}

		if (choice < 0) {
			throw new IllegalArgumentException("Illegal choice parameter");
		}

		return mQuestionDao.saveQuestionResponseData(result, questionId,
				choice, gender, age);

	}

	/**
	 * Get Question list. The maximum number of qustion is 20. 10 is from latest
	 * question. Another 10 is from old questions.
	 * 
	 * @param result
	 * @return
	 */
	public List<QuestionData> getQuestionList(Result result) {

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		List<QuestionData> questions = mQuestionDao
				.getLatestQuestionData(result);
		if (questions != null) {
			List<QuestionData> olderQuestions = mQuestionDao
					.getOlderQuestionData(result);
			if (olderQuestions != null) {
				questions.addAll(olderQuestions);
				return questions;
			}
		} else {
			// Just in case, if latest question is null
			return mQuestionDao.getOlderQuestionData(result);
		}

		return questions;

	}

	public List<QuestionData> getLatestQuestionList(Result result,
			int requestNum) {
		LogUtil.d(TAG, "getLatestQuestionList");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (requestNum <= 0) {
			throw new IllegalArgumentException(
					"The number of question request is more than 1");
		}

		return mQuestionDao.getLatestQuestionData(result);

	}

	public List<ResultListData> getQuestionResultList(Result result, long userId) {

		LogUtil.d(TAG, "getQuestionResultList");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (userId == Constants.NO_USER) {
			throw new IllegalArgumentException("user id cannot be -1");
		}

		return mQuestionDao.getQuestionResultList(result, userId);

	}

	public ResultDetailData getQuestionResultDetail(Result result,
			long questionId) {

		LogUtil.d(TAG, "getQuestionResultDetail");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (questionId == Constants.NO_QUESTION) {
			throw new IllegalArgumentException("question id cannot be -1");
		}

		return mQuestionDao.getQuestionResultDetail(result, questionId);

	}

	public List<String> getLatestDeviceIdList(Result result) {

		LogUtil.d(TAG, "getLatestDeviceIdList");

		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		return mUserDao.getDeviceIds(result);

	}

}
