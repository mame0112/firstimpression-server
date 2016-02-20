package com.mame.impression;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import com.mame.impression.util.LogUtil;

public class GCMPushServlet extends HttpServlet {

	private final static Logger log = Logger
			.getLogger(GCMPushServlet.class.getName());

	private final static String TAG = "GCMPushServlet";

	private static final String API_KEY = "AIzaSyBqd3I6JieGeK34NE6u25Jjm0I_ToTaHhI";
	private static final int RETRY_COUNT = 5;

	static Map<String, String> deviceMap = new HashMap<String, String>();

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		LogUtil.d(TAG, "doGet");
//		DbgUtil.showLog(TAG, "req.getQueryString(): " + req.getQueryString());

//		String secretKey = req.getParameter(LcomConst.SERVLET_IDENTIFIER);
//
//		String action = CipherUtil.decrypt(req.getParameter("action"),
//				secretKey);
//		String registrationId = CipherUtil.decrypt(req.getParameter("regId"),
//				secretKey);
//		String userId = CipherUtil.decrypt(req.getParameter("userId"),
//				secretKey);
//		String msg = CipherUtil.decrypt(req.getParameter("msg"), secretKey);
//		String apiLevel = CipherUtil.decrypt(
//				req.getParameter(LcomConst.SERVLET_API_LEVEL), secretKey);

//		DbgUtil.showLog(TAG, "action: " + action);
//		DbgUtil.showLog(TAG, "registrationId: " + registrationId);

		LogUtil.d(TAG, "send");
		
		String msg ="Test message";
		String registrationId = "c_HUcOVvaxk:APA91bHESNvR1FNmqAJxDgvcB_O-tPZGXBM9mF1xUAtrziYNa_PO3n2XHG_dTs9BUoBZ9mxvvknpZKYl4LYOFN26XiBS4eJdHOVQ_Ja7Tyip63ig5UqxqtUrpykgBdYDUFRd031_s9bs";

		// registrationId = deviceMap.get(userId);
		Sender sender = new Sender(API_KEY);
		Message message = new Message.Builder().addData("msg", msg).build();

		LogUtil.d(TAG, "message: " + message);
		LogUtil.d(TAG, "registrationId: " + registrationId);

		Result result = sender.send(message, registrationId, RETRY_COUNT);
		res.setContentType("text/plain");
		res.getWriter().println("Result=" + result);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		LogUtil.d(TAG, "doPost");
		doGet(req, res);
	}

}
