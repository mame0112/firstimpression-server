package com.mame.impression.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.UserData;
import com.mame.impression.manager.ImpressionDataManager;
import com.mame.impression.util.LogUtil;
import com.mame.impression.util.TimeUtil;

/**
 * Action for Sign up.<br>
 * Input: user name, password (user id must be -1.) <br>
 * Output: user id
 * 
 * @author kosukeEndo
 * 
 */
public class SignUpAction implements Action {

	private static final String TAG = Constants.TAG
			+ SignUpAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");

		String responseId = request.getParameter(ActionConstants.ID);
		String param = request.getParameter(ActionConstants.PARAM);

		ActionUtil util = new ActionUtil();
		UserData data = util.createUserDataFromParam(param);

		// Use current time as user id
		data.setUserId(new TimeUtil().getCurrentTime());

		// Create result
		Result result = new Result();

		ImpressionDataManager.getInstance().storeNewUserData(result, data);

		JSONObject paramObject = new JSONObject();

		if (result.isSuccess()) {
			paramObject = createSuccessResultJson(paramObject, data);
		} else if (result.isFailed()) {
			paramObject = createFailResultJson(paramObject);
		}

		JSONObject resultJson = util.createResultJsonObject(paramObject,
				responseId);

		LogUtil.d(TAG, "resultjson: " + resultJson.toString());

		return resultJson.toString();
	}

	private JSONObject createSuccessResultJson(JSONObject object, UserData data) {

		try {
			object.put(ActionConstants.USER_ID, data.getUserId());
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}
		return object;
	}

	private JSONObject createFailResultJson(JSONObject object) {
		try {
			object.put(ActionConstants.ERROR, "Error occurrs");
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}
		return object;
	}

}
