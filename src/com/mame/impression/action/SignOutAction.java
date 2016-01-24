package com.mame.impression.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.constant.Constants;
import com.mame.impression.manager.ImpressionDataManager;
import com.mame.impression.util.LogUtil;

public class SignOutAction implements Action {

	private static final String TAG = Constants.TAG
			+ SignOutAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");
		
//		String responseId = request.getParameter(ActionConstants.ID);
		String responseId = "1";
//		String param = request.getParameter(ActionConstants.PARAM);
//		JSONObject param = ParameterRetriver.retrieveParam(request);
		String input = request.getParameter(ActionConstants.PARAM);
		
		if(input != null){
			
			JSONObject param = new JSONObject(input);
			
			long userId = Constants.NO_USER;
			String userName = null;
			
			try {
				userId = param.getLong(ActionConstants.USER_ID);
				userName = param.getString(ActionConstants.USER_NAME);
			} catch(JSONException e){
				LogUtil.d(TAG,  "JSONException: " + e.getMessage());
			}
			
			if(userId != Constants.NO_USER && userName != null){
				// Create result
				Result result = new Result();
				ImpressionDataManager.getInstance().signOut(result, userId, userName);
				
				JSONObject resultJson = new JSONObject();
				resultJson.put(ActionConstants.ID, responseId);
				resultJson.put(ActionConstants.PARAM, new JSONObject());
				
				return resultJson.toString();

			}
			

		}
		
		return null;
	}

}
