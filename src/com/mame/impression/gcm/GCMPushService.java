package com.mame.impression.gcm;

//import com.google.android.gcm.server.Message;
//import com.google.android.gcm.server.Result;
//import com.google.android.gcm.server.Sender;

public class GCMPushService {

	private final static String TAG = GCMPushService.class.getSimpleName();

	private static final String API_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	private static final int RETRY_COUNT = 5;

	public void GCMPushService() {

		String action = "action";
		String regId = "regId";
		long userId = 1L;
		String message = "message";

		if ("register".equals(action)) {
			// Device registration (To be called from Android)
			// TODO
			// Register deviceId to DB

		} else if ("unregister".equals(action)) {
			// Device unregistration (To be called from Android)
			// TODO Unregister deviceId from DB

		} else if ("send".equals(action)) {
			// Send message

			// TODO
//			registrationId = "dtfghjk";
//			Sender sender = new Sender(API_KEY);
//			Message message = new Message.Builder().addData("msg",
//					"test message").build();
//			Result result = sender.send(message, registrationId, RETRY_COUNT);

		} else if ("sendAll".equals(action)) {
			// TODO: Look at Google sample
		} else {
			// Status 500
		}
	}
}
