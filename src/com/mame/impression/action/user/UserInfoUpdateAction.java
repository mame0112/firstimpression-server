package com.mame.impression.action.user;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.action.Action;
import com.mame.impression.action.ActionConstants;
import com.mame.impression.action.ActionUtil;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.UserData;
import com.mame.impression.manager.ImpressionDataManager;
import com.mame.impression.util.LogUtil;

public class UserInfoUpdateAction implements Action {

	private static final String TAG = Constants.TAG
			+ UserInfoUpdateAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");

		String responseId = request.getParameter(ActionConstants.ID);
		String param = request.getParameter(ActionConstants.PARAM);
		
		LogUtil.d(TAG, "id: " + responseId);
		LogUtil.d(TAG, "param: " + param);
		
		Map map = request.getParameterMap();
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			String name = (String) it.next();
			String[] val = (String[]) map.get(name);
			for (int i = 0; i < val.length; i++) {
//				out.println(name + "=" + val[i] + "<br>");
				LogUtil.d(TAG, "name; " + name + " val[i]: " + val[i]);
			}
		}

		ActionUtil util = new ActionUtil();
		UserData data = util.createUserDataFromParam(param);

		// Create result
		Result result = new Result();

		ImpressionDataManager.getInstance().updateUserData(result, data);

		JSONObject resultJson;

		if (result.isSuccess()) {
			resultJson = util.createResultJsonObject(new JSONObject(),
					responseId);
		} else {
			JSONObject paramJson = new JSONObject();
			paramJson.put(ActionConstants.ERROR, result.getErrorMessage());
			resultJson = util.createResultJsonObject(paramJson, responseId);
		}

		LogUtil.d(TAG, "resultJson: " + resultJson.toString());

		return resultJson.toString();
	}

}
