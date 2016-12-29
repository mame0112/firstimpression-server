package com.mame.impression.action.debug;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.action.Action;
import com.mame.impression.action.ActionConstants;
import com.mame.impression.action.ActionUtil;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.QuestionDataBuilder;
import com.mame.impression.data.QuestionData.Category;
import com.mame.impression.manager.ImpressionDataManager;
import com.mame.impression.util.LogUtil;
import com.mame.impression.util.TimeUtil;

public class DebugCreateNewQuestionAction implements Action {

	private static final String TAG = Constants.TAG
			+ DebugCreateNewQuestionAction.class.getSimpleName();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String responseId = "1";

		QuestionData data = createDummyQuestionData(request);

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
		ActionUtil util = new ActionUtil();
		JSONObject resultObject = util.createResultJsonObject(paramObject,
				responseId);

		LogUtil.d(TAG, "resultObject: " + resultObject.toString());

		return resultObject.toString();

	}

	private QuestionData createDummyQuestionData(HttpServletRequest request) {
		String description = request
				.getParameter(ActionConstants.QUESTION_DESCRIPTION);
		String choiceA = (String) request
				.getParameter(ActionConstants.QUESTION_CHOICE_A);
		String choiceB = (String) request
				.getParameter(ActionConstants.QUESTION_CHOICE_B);
		String createdUserName = (String) request
				.getParameter(ActionConstants.QUESTION_CREATED_USER_NAME);
		long createdUserId = Long.valueOf(request
				.getParameter(ActionConstants.QUESTION_CREATED_USER_ID));

		long questionId = Constants.NO_QUESTION;

		return new QuestionDataBuilder().setQuestionId(questionId)
				.setDescription(description).setChoiceA(choiceA)
				.setChoiceB(choiceB).setCreatedUserName(createdUserName)
				.setCreatedUserId(createdUserId).setThumbnail(null)
				.setChoiceAResponseNum(0).setChoiceBResponseNum(0)
				.setCategory(Category.GENERAL).getResult();

	}

	private JSONObject createSuccessResponseJson(QuestionData data) {
		LogUtil.d(TAG, "createSuccessResponseJson");

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
