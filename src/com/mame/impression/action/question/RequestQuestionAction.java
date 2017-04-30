package com.mame.impression.action.question;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.Result.ActionResult;
import com.mame.impression.action.Action;
import com.mame.impression.action.ActionConstants;
import com.mame.impression.action.ActionUtil;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.manager.ImpressionDataManager;
import com.mame.impression.util.LogUtil;

public class RequestQuestionAction implements Action {

	private static final String TAG = Constants.TAG
			+ RequestQuestionAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");

		String responseId = "1";
		String param = request.getParameter(ActionConstants.PARAM);

		Result result = new Result();

		int num = retriveRequestedQuestionNum(param);
		JSONObject resultJson = null;

		ActionUtil util = new ActionUtil();

		if (num > 1) {
			List<QuestionData> questions = ImpressionDataManager.getInstance()
					.getLatestQuestionList(result, num);

			JSONArray questionArray = util
					.createJsonArrayFromQuestionList(questions);
			resultJson = util.createResultJsonObject(questionArray, responseId);

			if (resultJson != null) {
				LogUtil.d(TAG, "resultJson: " + resultJson.toString());
				return resultJson.toString();
			} else {
				// TODO Need to have error handlinge
				return util
						.createResultJsonObject(new JSONObject(), responseId)
						.toString();
			}

		} else {
			JSONObject errorJsonParamObj = createErrorParamJsonObject();
			return util.createResultJsonObject(errorJsonParamObj, responseId)
					.toString();
		}

	}

	private int retriveRequestedQuestionNum(String param) {
		if (param == null) {
			return 0;
		}

		try {
			JSONObject obj = new JSONObject(param);
			return obj.getInt(ActionConstants.QUESTION_REQUEST_NUM);
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		return 0;

	}

	private JSONObject createErrorParamJsonObject() {
		JSONObject errorObj = new JSONObject();

		try {
			errorObj.put(ActionConstants.ERROR,
					"The number of request num is more than 1");
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		return errorObj;
	}
}
