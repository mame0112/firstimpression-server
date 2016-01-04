package com.mame.impression.debug;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.action.ActionConstants;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.UserData;
import com.mame.impression.data.UserData.AGE;
import com.mame.impression.data.UserData.GENDER;
import com.mame.impression.util.LogUtil;
import com.mame.impression.util.TimeUtil;

public class MockJsonGenerator {

	private static final String TAG = Constants.TAG
			+ MockJsonGenerator.class.getSimpleName();

	public static JSONObject createInputForCreateNewUser() {

		JSONObject paramObject = new JSONObject();
		try {
			paramObject.put(ActionConstants.USER_ID, 1);
			paramObject.put(ActionConstants.USER_NAME, "test_user_name");
			paramObject.put(ActionConstants.USER_PASSWORD, "test_password");
			paramObject.put(ActionConstants.USER_THUMBNAIL, "http://xxxx");

			paramObject.put(ActionConstants.USER_AGE, AGE.valueOf("FROM20_30"));
			paramObject
					.put(ActionConstants.USER_GENDER, GENDER.valueOf("MALE"));
			paramObject.put(ActionConstants.USER_CREATED_QUESTION_ID,
					new ArrayList<Long>());
		} catch (JSONException e) {
			LogUtil.d(
					TAG,
					"createInputForCreateNewUser JSONException: "
							+ e.getMessage());
		}

		LogUtil.d(TAG,
				"createInputForCreateNewUser output: " + paramObject.toString());

		return paramObject;

	}

	public static JSONObject createInputForCreateNewQuestion() {
		LogUtil.d(TAG, "createInputForCreateNewQuestion");

		JSONObject paramObject = new JSONObject();

		try {
			paramObject.put(ActionConstants.QUESTION_ID,
					new TimeUtil().getCurrentTime());
			paramObject.put(ActionConstants.QUESTION_DESCRIPTION,
					"testDescription");
			paramObject.put(ActionConstants.QUESTION_CHOICE_A, "ChoiceA");
			paramObject.put(ActionConstants.QUESTION_CHOICE_B, "ChoiceB");
			paramObject.put(ActionConstants.QUESTION_CREATED_USER_NAME,
					"createdUserName");
			paramObject.put(ActionConstants.QUESTION_CREATED_USER_ID, 4L);
			paramObject.put(ActionConstants.QUESTION_THUMBNAIL,
					"http://thumbnail.com");
			paramObject.put(ActionConstants.QUESTION_CHOICE_A_RESPONSE, 2);
			paramObject.put(ActionConstants.QUESTION_CHOICE_B_RESPONSE, 4);
			paramObject.put(ActionConstants.QUESTION_CATEGORY,
					QuestionData.Category.GENERAL.name());
			LogUtil.d(TAG, "input: " + QuestionData.Category.GENERAL.name());

			paramObject.put(ActionConstants.QUESTION_ADDITIONAL_COMMENT,
					new ArrayList<String>().add("Additional1"));
			paramObject.put(ActionConstants.QUESTION_ADDITIONAL_QUESTION,
					"AdditionalQuestion");
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		return paramObject;
	}

	public static JSONObject createInputFotStartAndEndPos() {

		JSONObject paramObject = new JSONObject();
		try {
			paramObject.put(ActionConstants.QUESTION_START_POS, 0);
			paramObject.put(ActionConstants.QUESTION_END_POS, 19);
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}
		return paramObject;
	}
}
