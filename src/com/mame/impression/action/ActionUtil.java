package com.mame.impression.action;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.QuestionData.Category;
import com.mame.impression.data.QuestionDataBuilder;
import com.mame.impression.data.UserData;
import com.mame.impression.data.UserData.AGE;
import com.mame.impression.data.UserData.GENDER;
import com.mame.impression.data.UserDataBuilder;
import com.mame.impression.util.LogUtil;

public class ActionUtil {

	private static final String TAG = Constants.TAG
			+ ActionUtil.class.getSimpleName();

	public UserData createUserDataFromParam(String param) throws JSONException {

		LogUtil.d(TAG, "createUserDataFromParam");

		if (param == null) {
			throw new IllegalArgumentException("Input param cannot be null");
		}

		LogUtil.d(TAG, "param: " + param);

		JSONObject input = new JSONObject(param);

		long userId = Constants.NO_USER;
		try {
			userId = (Long) input.getLong(ActionConstants.USER_ID);
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		String userName = null;
		try {
			userName = (String) input.getString(ActionConstants.USER_NAME);
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		String password = null;
		try {
			password = (String) input.getString(ActionConstants.USER_PASSWORD);
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		String thumbUrl = null;
		try {
			thumbUrl = (String) input.getString(ActionConstants.USER_THUMBNAIL);
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		AGE age = null;
		try {
			age = AGE.valueOf((String) input.get(ActionConstants.USER_AGE));
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		GENDER gender = null;
		try {
			gender = GENDER.valueOf((String) input
					.get(ActionConstants.USER_GENDER));
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		List<Long> listData = new ArrayList<Long>();
		try {
			JSONArray idArrays = null;
			idArrays = (JSONArray) input
					.get(ActionConstants.USER_CREATED_QUESTION_ID);

			for (int i = 0; i < idArrays.length(); i++) {
				listData.add((Long) idArrays.get(i));
			}

		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		return new UserDataBuilder().setUserId(userId).setUserName(userName)
				.setPassword(password).setThumbnailUrl(thumbUrl).setAge(age)
				.setGender(gender).setCreatedQuestionIds(listData).getResult();
	}

	public JSONObject createJsonFromUserData(JSONObject object, UserData data)
			throws JSONException {

		if (data == null) {
			throw new IllegalArgumentException("user data cannot be null");
		}

		if (object == null) {
			throw new IllegalArgumentException("JSONObject cannot be null");
		}

		long userId = data.getUserId();
		String userName = data.getUserName();
		String password = data.getPassword();
		String thumbUrl = data.getThumbnailUrl();
		AGE age = data.getAge();
		GENDER gender = data.getGender();
		List<Long> ids = data.getCreatedQuestionIds();

		object.put(ActionConstants.USER_ID, userId);
		object.put(ActionConstants.USER_NAME, userName);
		object.put(ActionConstants.USER_PASSWORD, password);
		object.put(ActionConstants.USER_AGE, age);
		object.put(ActionConstants.USER_GENDER, gender);
		object.put(ActionConstants.USER_THUMBNAIL, thumbUrl);
		object.put(ActionConstants.USER_CREATED_QUESTION_ID, ids);

		return object;
	}

	public JSONObject createResultJsonObject(JSONObject paramObject,
			String responseId) {
		LogUtil.d(TAG, "createResultJsonObject");

		if (paramObject == null) {
			throw new IllegalArgumentException("paramObject cannot be null");
		}

		if (responseId == null) {
			throw new IllegalArgumentException("Response id cannot be null");
		}

		JSONObject rootObject = new JSONObject();
		try {
			rootObject.put(ActionConstants.PARAM, paramObject);
			rootObject.put(ActionConstants.ID, responseId);
		} catch (JSONException e) {
			LogUtil.d(TAG,
					"createResulJsonObject JSONException: " + e.getMessage());
		}

		return rootObject;

	}

	public JSONObject createResultJsonObject(JSONArray paramArray,
			String responseId) {

		if (paramArray == null) {
			throw new IllegalArgumentException("paramArray cannot be null");
		}

		if (responseId == null) {
			throw new IllegalArgumentException("Response id cannot be null");
		}

		JSONObject rootObject = new JSONObject();
		try {
			rootObject.put(ActionConstants.PARAM, paramArray);
			rootObject.put(ActionConstants.ID, responseId);
		} catch (JSONException e) {
			LogUtil.d(TAG,
					"createResulJsonObject JSONException: " + e.getMessage());
		}

		return rootObject;

	}

	public QuestionData createQuestionDataFromParam(String param)
			throws JSONException {

		LogUtil.d(TAG, "createQuestionDataFromParam");

		if (param == null) {
			throw new IllegalArgumentException("Input param cannot be null");
		}

		JSONObject input = new JSONObject(param);

		// Mandatory field
		long questionId = Constants.NO_QUESTION;

		String description = (String) input
				.getString(ActionConstants.QUESTION_DESCRIPTION);
		String choiceA = (String) input
				.getString(ActionConstants.QUESTION_CHOICE_A);
		String choiceB = (String) input
				.getString(ActionConstants.QUESTION_CHOICE_B);
		String createdUserName = (String) input
				.getString(ActionConstants.QUESTION_CREATED_USER_NAME);
		long createdUserId = (Long) input
				.getLong(ActionConstants.QUESTION_CREATED_USER_ID);

		// Optional field
		Category category = Category.GENERAL;
		try {
			category = Category.valueOf((String) input
					.getString(ActionConstants.QUESTION_CATEGORY));
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		String thumbnail = null;
		try {
			thumbnail = (String) input
					.getString(ActionConstants.QUESTION_THUMBNAIL);
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		int choiceAResp = 0;
		try {
			choiceAResp = (Integer) input
					.getInt(ActionConstants.QUESTION_CHOICE_A_RESPONSE);
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		int choiceBResp = 0;
		try {
			choiceBResp = (Integer) input
					.getInt(ActionConstants.QUESTION_CHOICE_B_RESPONSE);
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		return new QuestionDataBuilder().setQuestionId(questionId)
				.setDescription(description).setChoiceA(choiceA)
				.setChoiceB(choiceB).setCreatedUserName(createdUserName)
				.setCreatedUserId(createdUserId).setThumbnail(thumbnail)
				.setChoiceAResponseNum(choiceAResp)
				.setChoiceBResponseNum(choiceBResp)
				.setCategory(category).getResult();
	}

	/**
	 * Create JSONArray from list of question.
	 * 
	 * @param questions
	 * @return
	 */
	public JSONArray createJsonArrayFromQuestionList(
			List<QuestionData> questions) {

		JSONArray array = new JSONArray();

		if (questions != null && questions.size() != 0) {
			for (QuestionData question : questions) {
				array.put(createJsonObjectFromQuestion(question));
			}
		}

		return array;
	}

	/**
	 * Create a JSONObject fomr QuestionData
	 * 
	 * @param question
	 * @return
	 */
	public JSONObject createJsonObjectFromQuestion(QuestionData question) {

		JSONObject object = new JSONObject();
		if (question != null) {
			try {
				object.put(ActionConstants.QUESTION_ID,
						question.getQuestionId());
				object.put(ActionConstants.QUESTION_DESCRIPTION,
						question.getDescription());

				object.put(ActionConstants.QUESTION_CATEGORY,
						question.getCategory());
				object.put(ActionConstants.QUESTION_CHOICE_A,
						question.getChoiceA());
				object.put(ActionConstants.QUESTION_CHOICE_B,
						question.getChoiceB());
				object.put(ActionConstants.QUESTION_POST_DATE,
						question.getCreatedDate());
				object.put(ActionConstants.QUESTION_CHOICE_A_RESPONSE,
						question.getChoiceAResponseNum());
				object.put(ActionConstants.QUESTION_CHOICE_B_RESPONSE,
						question.getChoiceBResponseNum());
				object.put(ActionConstants.QUESTION_CREATED_USER_ID,
						question.getCreatedUserId());
				object.put(ActionConstants.QUESTION_CREATED_USER_NAME,
						question.getCreatedUserName());
				object.put(ActionConstants.QUESTION_THUMBNAIL,
						question.getThumbnail());
				object.put(ActionConstants.QUESTION_ADDITIONAL_QUESTION,
						question.getAdditionalQuestion());
				object.put(ActionConstants.QUESTION_ADDITIONAL_COMMENT,
						question.getAdditionalAnswer());

			} catch (JSONException e) {
				LogUtil.w(TAG, "JSONException: " + e.getMessage());
			}

		}
		return object;
	}
}
