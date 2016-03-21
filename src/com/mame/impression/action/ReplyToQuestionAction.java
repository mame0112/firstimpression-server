package com.mame.impression.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.IndexUserData;
import com.mame.impression.data.UserData;
import com.mame.impression.data.UserData.Age;
import com.mame.impression.data.UserData.Gender;
import com.mame.impression.gcm.GCMPushSender;
import com.mame.impression.manager.ImpressionDataManager;
import com.mame.impression.util.LogUtil;

/**
 * Action for Reply to action.<br>
 * Input: Target question ID and A or B choice<br>
 * Output: boolean result. true if success. otherwise false.
 * 
 * @author kosukeEndo
 * 
 */
public class ReplyToQuestionAction implements Action {

	private static final String TAG = Constants.TAG
			+ ReplyToQuestionAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");

		// TODO
		String responseId = "1";
		// String responseId = request.getParameter(ActionConstants.ID);
		JSONObject obj = ParameterRetriver.retrieveParam(request);
		// String param = request.getParameter(ActionConstants.PARAM);
		//
		// JSONObject obj = new JSONObject(param);
		long questionId = obj.getLong(ActionConstants.QUESTION_ID);
		int choice = obj.getInt(ActionConstants.QUESTION_CHOICE);
		String genderString = obj.getString(ActionConstants.USER_GENDER);
		String ageString = obj.getString(ActionConstants.USER_AGE);

		// Create result
		Result result = new Result();

		Gender gender = null;
		Age age = null;

		if (genderString != null) {
			gender = Gender.valueOf(genderString);
		}
		if (ageString != null) {
			age = Age.valueOf(ageString);
		}

		IndexUserData indexUserData = ImpressionDataManager.getInstance()
				.respondToQuestion(result, questionId, choice, gender, age);

		if (result.isSuccess()) {
			UserData data = ImpressionDataManager.getInstance().getUserData(
					result, indexUserData.getUserId(),
					indexUserData.getUserName());
			if (data != null) {
				GCMPushSender sender = new GCMPushSender();
				sender.sendPushNotification(response, data.getDeviceId(),
						"Test message");
			}

		}

		JSONObject resultObject = createResultParam(result, questionId,
				responseId);

		LogUtil.d(TAG, "resultObject: " + resultObject.toString());

		return resultObject.toString();
	}

	private JSONObject createResultParam(Result result, long questionId,
			String responseId) {
		ActionUtil util = new ActionUtil();

		JSONObject resultParam = new JSONObject();
		if (result.isFailed()) {
			try {
				resultParam
						.put(ActionConstants.ERROR, result.getErrorMessage());
				resultParam.put(ActionConstants.ID, responseId);
			} catch (JSONException e) {
				LogUtil.w(TAG, "JSONException: " + e.getMessage());
			}
		} else {
			try {
				resultParam.put(ActionConstants.QUESTION_ID, questionId);
			} catch (JSONException e) {
				LogUtil.w(TAG, "JSONException: " + e.getMessage());
			}
		}

		return util.createResultJsonObject(resultParam, responseId);

	}
}
