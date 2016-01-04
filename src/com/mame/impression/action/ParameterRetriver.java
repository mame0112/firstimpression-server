package com.mame.impression.action;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.constant.Constants;
import com.mame.impression.util.LogUtil;

public class ParameterRetriver {

	private static final String TAG = Constants.TAG + ParameterRetriver.class.getSimpleName();
	
	public static JSONObject retrieveParam(HttpServletRequest request){
		
		Map map = request.getParameterMap();
		Iterator it = map.keySet().iterator();
		
		if(it.hasNext()){
			String param = (String) it.next();
			try {
				LogUtil.d(TAG,  "param: " + param);
				return new JSONObject(param);
			} catch (JSONException e) {
				LogUtil.d(TAG,  "JSONException; " + e.getMessage());
			}
		}
		
		return null;
	}
}
