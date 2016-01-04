package com.mame.impression.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.constant.Constants;
import com.mame.impression.manager.ImpressionDataManager;
import com.mame.impression.util.LogUtil;

/**
 * Action for Reply to action.<br>
 * Input: Target question ID and A or B choice<br>
 * Output: boolean result. true if success. otherwise false.
 * 
 * @author kosukeEndo
 * 
 */
public class ReplyToQuestionAction implements Action {

	private static final String TAG = Constants.TAG
			+ ReplyToQuestionAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");

		String responseId = request.getParameter(ActionConstants.ID);
		String param = request.getParameter(ActionConstants.PARAM);

		JSONObject obj = new JSONObject(param);
		long questionId = obj.getLong(ActionConstants.QUESTION_ID);
		int choice = obj.getInt(ActionConstants.QUESTION_CHOICE);

		// Create result
		Result result = new Result();

		ImpressionDataManager.getInstance().respondToQuestion(result,
				questionId, choice);

		JSONObject resultObject = createResultParam(result, responseId);

		LogUtil.d(TAG, "resultObject: " + resultObject.toString());

		return resultObject.toString();
	}

	private JSONObject createResultParam(Result result, String responseId) {
		ActionUtil util = new ActionUtil();

		JSONObject resultParam = new JSONObject();
		if (result.isFailed()) {
			try {
				resultParam
						.put(ActionConstants.ERROR, result.getErrorMessage());
			} catch (JSONException e) {

			}
		}

		util.createResultJsonObject(resultParam, responseId);

		return resultParam;

	}
}
