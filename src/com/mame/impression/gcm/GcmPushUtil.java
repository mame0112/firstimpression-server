package com.mame.impression.gcm;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.GcmPushData;
import com.mame.impression.util.LogUtil;

public class GcmPushUtil {

	private final static String TAG = Constants.TAG
			+ GcmPushUtil.class.getSimpleName();

	public JSONObject createPushContent(GcmPushData input) {

		if (input == null) {
			throw new IllegalArgumentException("GcmPushData cannot be null");
		}

		if (input.getQuestionId() == Constants.NO_QUESTION) {
			throw new IllegalArgumentException("Question id cannot be null");
		}

		JSONObject result = new JSONObject();

		try {
			result.put(GcmConstants.PARAM_QUESTION_ID, input.getQuestionId());
			result.put(GcmConstants.PARAM_QUESTION_DESCRIPTION, input.getQuestionTitle());
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}
		
		return result;
	}

}
