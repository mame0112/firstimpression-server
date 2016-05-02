package com.mame.impression.action.contact;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.action.Action;
import com.mame.impression.action.ActionConstants;
import com.mame.impression.action.ActionUtil;
import com.mame.impression.constant.Constants;
import com.mame.impression.util.LogUtil;

public class ContactAction implements Action {

	private static final String TAG = Constants.TAG
			+ ContactAction.class.getSimpleName();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");

		String responseId = "1";
		String param = request.getParameter(ActionConstants.PARAM);

		if (param != null) {
			JSONObject input = new JSONObject(param);
			String userName = null;
			try {
				userName = input.getString(ActionConstants.CONTACT_NAME);
				LogUtil.d(TAG, "userName:" + userName);
			} catch (JSONException e) {
				LogUtil.d(TAG, "JSONException: " + e.getMessage());
			}

			String mailAddress = null;
			try {
				mailAddress = input.getString(ActionConstants.CONTACT_EMAIL);
				LogUtil.d(TAG, "mailAddress:" + mailAddress);
			} catch (JSONException e) {
				LogUtil.d(TAG, "JSONException: " + e.getMessage());
			}

			String message = null;
			try {
				message = input.getString(ActionConstants.CONTACT_MESSAGE);
				LogUtil.d(TAG, "message:" + message);
			} catch (JSONException e) {
				LogUtil.d(TAG, "JSONException: " + e.getMessage());
			}

			boolean result = sendMessage(userName, mailAddress, message);

		} else {
			// param is null
		}

		ActionUtil util = new ActionUtil();

		return null;
	}

	private boolean sendMessage(String userName, String address, String message) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "userName: " + userName + "<br>" + " address: "
				+ address + "<br>" + " message: " + message;

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("mame0112@gmail.com", "Inquery"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(
					"mame0112@gmail.com", "First Impression"));
			msg.setSubject("Contact");
			msg.setText(msgBody);
			msg.setContent(msgBody, "text/html");
			Transport.send(msg);
			LogUtil.d(TAG, "Successfully sent message.");
			return true;
		} catch (MessagingException e) {
			LogUtil.d(TAG, "MessagingException: " + e.getMessage());
			return false;
		} catch (Exception e) {
			LogUtil.d(TAG, "Exception: " + e.getMessage());
			return false;
		}
	}

}
