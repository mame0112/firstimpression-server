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

	public synchronized void updateUserData(Result result, UserData data) {
		LogUtil.d(TAG, "updateUserData");

		if (result == null) {
			result.setActionResult(ActionResult.FAIL);
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (data == null) {
			result.setActionResult(ActionResult.FAIL);
			throw new IllegalArgumentException("user data cannot be null");
		}

		mUserDao.updateUserData(result, data);

	}

	public synchronized void saveNewQuestionData(Result result,
			QuestionData data) {
		LogUtil.d(TAG,  "saveNewQuestionData");
		if (result == null) {
			throw new IllegalArgumentException("Result cannot be null");
		}

		if (data == null) {
			throw new IllegalArgumentException("question data cannot be null");
		}

		mQuestionDao.storeNewQuestionData(result, data);

	}

	public synchronized void respondToQuestion(Result result, long questionId,
			int choice) {

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

		mQuestionDao.saveQuestionResponseData(result, questionId, choice);

	}

	public List<QuestionData> getLatestQuestionList(Result result) {
		
		if(result == null){
			throw new IllegalArgumentException("Result cannot be null");
		}

		return mQuestionDao.getLatestQuestionData(result);

	}
	
	public List<QuestionData> getLatestQuestionListForUser(Result result, long userId) {
		
		if(result == null){
			throw new IllegalArgumentException("Result cannot be null");
		}

		//TODO
		return mQuestionDao.getLatestQuestionData(result);

	}

}
