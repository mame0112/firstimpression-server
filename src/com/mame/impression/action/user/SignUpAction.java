package com.mame.impression.action.user;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.action.Action;
import com.mame.impression.action.ActionConstants;
import com.mame.impression.action.ActionUtil;
import com.mame.impression.action.ParameterRetriver;
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

		// TODO
		// String responseId = request.getParameter(ActionConstants.ID);
		String responseId = "1";
		// String param = request.getParameter(ActionConstants.PARAM);
		JSONObject param = ParameterRetriver.retrieveParam(request);

		LogUtil.d(TAG, "id: " + responseId);
		LogUtil.d(TAG, "param: " + param);

		Map map = request.getParameterMap();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String name = (String) it.next();
			String[] val = (String[]) map.get(name);
			for (int i = 0; i < val.length; i++) {
				// out.println(name + "=" + val[i] + "<br>");
				LogUtil.d(TAG, "name; " + name + " val[i]: " + val[i]);
			}
		}

		ActionUtil util = new ActionUtil();
		UserData data = util.createUserDataFromParam(param.toString());

		// Create result
		Result result = new Result();

		boolean isUsed = ImpressionDataManager.getInstance()
				.isUserNameAlreadyUsed(result, data.getUserName());

		// Result JSON Object
		JSONObject paramObject = new JSONObject();

		// If user name has already been used
		if (isUsed) {
			// Return NO_USER for User id.
			paramObject = createUserNameAlreadyUsedResultJson(paramObject);
		} else {
			// If user name has not been used
			// Use current time as user id
			data.setUserId(new TimeUtil().getCurrentTime());

			ImpressionDataManager.getInstance().storeNewUserData(result, data);

			if (result.isSuccess()) {
				paramObject = createSuccessResultJson(paramObject, data);
			} else if (result.isFailed()) {
				paramObject = createFailResultJson(paramObject);
			}
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

	private JSONObject createUserNameAlreadyUsedResultJson(JSONObject object) {
		try {
			object.put(ActionConstants.USER_ID, (Long) Constants.NO_USER);
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}
		return object;
	}

}
