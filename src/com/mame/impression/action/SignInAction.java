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

/**
 * Action for Sign in.<br>
 * Input: user name, password and user id (all fields are mandatory)<br>
 * Output: all user data
 * 
 * @author kosukeEndo
 * 
 */
public class SignInAction implements Action {

	private static final String TAG = Constants.TAG
			+ SignInAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");

		String responseId = request.getParameter(ActionConstants.ID);
		String param = request.getParameter(ActionConstants.PARAM);

		ActionUtil util = new ActionUtil();
		UserData data = util.createUserDataFromParam(param);

		// Create result
		Result result = new Result();

		UserData userData = null;
		JSONObject paramObject = new JSONObject();

		// Error check
		if (data != null) {
			if (data.getUserId() != Constants.NO_USER
					&& data.getUserName() != null && data.getPassword() != null) {
				userData = ImpressionDataManager.getInstance()
						.findUserDataByName(result, data.getUserName());
				paramObject = util
						.createJsonFromUserData(paramObject, userData);
			} else {
				// If some information is missing
				paramObject = createFailResultJson(paramObject);
			}
		} else {
			// If data is null
			paramObject = createFailResultJson(paramObject);
		}

		JSONObject resultJson = util.createResultJsonObject(paramObject,
				responseId);

		LogUtil.d(TAG, "resultjson: " + resultJson.toString());

		return resultJson.toString();
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
