package com.mame.impression.action.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.action.Action;
import com.mame.impression.action.ActionConstants;
import com.mame.impression.action.ActionUtil;
import com.mame.impression.action.ParameterRetriver;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.manager.ImpressionDataManager;
import com.mame.impression.util.LogUtil;
import com.mame.impression.util.TimeUtil;

/**
 * Action for Create new question.<br>
 * Input: QuestionId must be -1, description, choise A, B, user name, user id,
 * user thumbnail. CHoice A, B response, additional question must be null<br>
 * Output: Created question Id and its description, For other infomraiton, they
 * must be decided by client side.
 * 
 * @author kosukeEndo
 * 
 */
public class CreateNewQuestionAction implements Action {

	private static final String TAG = Constants.TAG
			+ CreateNewQuestionAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");

		// TDDO
		// String responseId = request.getParameter(ActionConstants.ID);
		// String param = request.getParameter(ActionConstants.PARAM);
		String responseId = "1";
		JSONObject param = ParameterRetriver.retrieveParam(request);

		// Create Question data from input string
		ActionUtil util = new ActionUtil();
		QuestionData data = util.createQuestionDataFromParam(param.toString());

		// Use current time as question id
		long current = new TimeUtil().getCurrentTime();
		data.setQuestionId(current);
		data.setCreatedDate(current);

		// Create result
		Result result = new Result();

		ImpressionDataManager.getInstance().saveNewQuestionData(result, data);

		JSONObject paramObject = null;
		if (result.isSuccess()) {
			paramObject = createSuccessResponseJson(data);
		} else {
			paramObject = createFailedResponseJson(result);
		}
		JSONObject resultObject = util.createResultJsonObject(paramObject,
				responseId);

		LogUtil.d(TAG, "resultObject: " + resultObject.toString());

		return resultObject.toString();
	}

	private JSONObject createSuccessResponseJson(QuestionData data) {
		LogUtil.d(TAG, "createSuccessResponseJson");

		JSONObject result = new JSONObject();
		if (data != null) {
			try {
				result.put(ActionConstants.ID, data.getQuestionId());
			} catch (JSONException e) {
				LogUtil.d(TAG, "JSONException: " + e.getMessage());
			}
		}

		return result;
	}

	private JSONObject createFailedResponseJson(Result actionResult) {
		LogUtil.d(TAG, "createFailedResponseJson");

		JSONObject result = new JSONObject();
		if (actionResult != null) {
			try {
				result.put(ActionConstants.ERROR,
						actionResult.getErrorMessage());
			} catch (JSONException e) {
				LogUtil.d(TAG, "JSONException: " + e.getMessage());
			}
		}

		return result;
	}

}
