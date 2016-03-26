package com.mame.impression.gcm;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.GcmPushData;
import com.mame.impression.util.LogUtil;

public class GCMPushSender {

	private static final String API_KEY = "AIzaSyBqd3I6JieGeK34NE6u25Jjm0I_ToTaHhI";
	private static final int RETRY_COUNT = 5;

	private final static String TAG = Constants.TAG
			+ GCMPushSender.class.getSimpleName();

	public void sendPushNotification(String deviceId, GcmPushData data) {
		LogUtil.d(TAG, "sendPushNotification");

		if (deviceId != null) {

			String input = new GcmPushUtil().createPushContent(data).toString();

			Sender sender = new Sender(API_KEY);
			Message message = new Message.Builder().addData(
					GcmConstants.MESSAGE, input).build();

			Result result = null;
			try {
				result = sender.send(message, deviceId, RETRY_COUNT);
				LogUtil.d(TAG, "result: " + result);
			} catch (IOException e) {
				LogUtil.d(TAG, "IOException: " + e.getMessage());
			}

		}
	}
}
