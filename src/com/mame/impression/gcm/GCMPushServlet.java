package com.mame.impression.gcm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.util.LogUtil;

public class GCMPushServlet extends HttpServlet {

	private final static Logger log = Logger.getLogger(GCMPushServlet.class
			.getName());

	private final static String TAG = "GCMPushServlet";

	private static final String API_KEY = "AIzaSyBqd3I6JieGeK34NE6u25Jjm0I_ToTaHhI";
	private static final int RETRY_COUNT = 1;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		LogUtil.d(TAG, "doGet");
		String description = req
				.getParameter(GcmConstants.PARAM_QUESTION_DESCRIPTION);
		String questionId = req.getParameter(GcmConstants.PARAM_QUESTION_ID);
		String deviceIds = req.getParameter(GcmConstants.PARAM_DEVICE_ID_LIST);

		List<String> deviceIdList = convertDeviceIdJsonArray2StringList(deviceIds);

		Sender sender = new Sender(API_KEY);
		Message message = new Message.Builder()
				.addData(GcmConstants.PARAM_QUESTION_ID, questionId)
				.addData(GcmConstants.PARAM_QUESTION_DESCRIPTION, description)
				.build();

		for (String deviceId : deviceIdList) {
			Result result = sender.send(message, deviceId, RETRY_COUNT);
			res.setContentType("text/plain");
			res.getWriter().println("Result=" + result);
		}

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		LogUtil.d(TAG, "doPost");
		doGet(req, res);
	}

	private List<String> convertDeviceIdJsonArray2StringList(
			String jsonArrayString) {

		LogUtil.d(TAG, "convertDeviceIdJsonArray2StringList");

		List<String> deviceIds = new ArrayList<String>();

		if (jsonArrayString != null) {

			try {
				JSONArray jsonArray = new JSONArray(jsonArrayString);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject obj = (JSONObject) jsonArray.get(i);
					String deviceId = obj
							.getString(GcmConstants.PARAM_DEVICE_ID);
					deviceIds.add(deviceId);
				}
			} catch (JSONException e) {
				LogUtil.w(TAG, "JSONException: " + e.getMessage());
			}

		}

		return deviceIds;

	}

}
