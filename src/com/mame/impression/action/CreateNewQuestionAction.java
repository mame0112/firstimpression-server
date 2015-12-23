package com.mame.impression.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
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

		String responseId = request.getParameter(ActionConstants.ID);
		String param = request.getParameter(ActionConstants.PARAM);

		// Create Question data from input string
		ActionUtil util = new ActionUtil();
		QuestionData data = util.createQuestionDataFromParam(param);

		// Use current time as question id
		data.setQuestionId(new TimeUtil().getCurrentTime());

		// Create result
		Result result = new Result();

		QuestionData storeResult = ImpressionDataManager.getInstance()
				.saveNewQuestionData(result, data);
		JSONObject paramObject = createResponseJson(storeResult);
		JSONObject resultObject = util.createResultJsonObject(paramObject,
				responseId);

		LogUtil.d(TAG, "resultObject: " + resultObject.toString());

		return resultObject.toString();
	}

	private JSONObject createResponseJson(QuestionData data) {

		JSONObject result = new JSONObject();
		if (data != null) {
			try {
				result.put(ActionConstants.ID, data.getQuestionId());
				result.put(ActionConstants.QUESTION_DESCRIPTION,
						data.getDescription());
			} catch (JSONException e) {
				LogUtil.d(TAG, "JSONException: " + e.getMessage());
			}
		}

		return result;
	}

}
