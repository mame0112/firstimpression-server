package com.mame.impression.gcm;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.action.ActionConstants;
import com.mame.impression.constant.Constants;
import com.mame.impression.manager.ImpressionDataManager;
import com.mame.impression.util.LogUtil;

/**
 * This is a class that manager GCM notification target. This class should have
 * responsibility to: - Select target user by unique logic - Get Device ID
 * according for the selected users - Call send command to push notification.
 * 
 * @author kosukeEndo
 * 
 */
public class GCMPushTargetManager {

	private final static String TAG = Constants.TAG
			+ GCMPushTargetManager.class.getName();

	public void pushNewQuestionCreatedNotification(long questionId,
			String questionDescription) {

		LogUtil.d(TAG, "pushNewQuestionCreatedNotification");

		if (questionId == Constants.NO_QUESTION) {
			return;
		}

		if (questionDescription == null) {
			return;
		}

		Result result = new Result();
		List<String> targetDeviceIds = selectLatestUsersDeviceId(result);

		// If successfully get target device list
		if (targetDeviceIds != null && targetDeviceIds.size() != 0
				&& result.isSuccess()) {
			// Send push notification
			sendPushNotification(targetDeviceIds, questionDescription);
		}

	}

	private List<String> selectLatestUsersDeviceId(Result result) {

		LogUtil.d(TAG, "selectLatestUsersDeviceId");

		return ImpressionDataManager.getInstance()
				.getLatestDeviceIdList(result);
	}

	private void sendPushNotification(List<String> deviceIdList,
			String questionDescription) {

		LogUtil.d(TAG, "sendPushNotification");

		String deviceIdJsonArray = createDeviceIdsJsonArray(deviceIdList)
				.toString();

		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(TaskOptions.Builder
				.withUrl(ActionConstants.KEY_PUSH)
				.param(GcmConstants.PARAM_QUESTION_ID,
						String.valueOf(deviceIdList.get(0)))
				.param(GcmConstants.PARAM_QUESTION_DESCRIPTION,
						questionDescription)
				.param(GcmConstants.PARAM_DEVICE_ID_LIST, deviceIdJsonArray));
	}

	private JSONArray createDeviceIdsJsonArray(List<String> deviceIds) {

		LogUtil.d(TAG, "createDeviceIdsJsonArray");

		JSONArray deviceIdArray = new JSONArray();

		if (deviceIds != null && deviceIds.size() != 0) {
			for (String deviceId : deviceIds) {
				try {
					if (deviceId != null) {
						JSONObject obj = new JSONObject();
						obj.put(GcmConstants.PARAM_DEVICE_ID, deviceId);
						deviceIdArray.put(obj);
					}

				} catch (JSONException e) {
					LogUtil.d(TAG, "JSONException: " + e.getMessage());
				}
			}

		}

		return deviceIdArray;

	}

}
