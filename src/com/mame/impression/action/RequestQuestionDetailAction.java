package com.mame.impression.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.Result.ActionResult;
import com.mame.impression.Result.ErrorType;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.ResultDetailData;
import com.mame.impression.data.ResultListData;
import com.mame.impression.data.ResultListDataBuilder;
import com.mame.impression.manager.ImpressionDataManager;
import com.mame.impression.util.LogUtil;

public class RequestQuestionDetailAction implements Action {

	private static final String TAG = Constants.TAG
			+ RequestQuestionDetailAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");

		// TOOD
		// String responseId = request.getParameter(ActionConstants.ID);
		String responseId = "1";
		String param = request.getParameter(ActionConstants.PARAM);

		Result result = new Result();
		ActionUtil util = new ActionUtil();

		JSONObject resultJson = null;

		if (param != null) {
			LogUtil.d(TAG, "param: " + param.toString());

			JSONObject obj = new JSONObject(param);

			long createdUserId = Constants.NO_USER;
			long questionId = Constants.NO_QUESTION;

			/*
			 * Get created User id. Since this parameter is mandatory for this
			 * action, if this is not given, we return as error
			 */
			try {
				createdUserId = obj
						.getLong(ActionConstants.QUESTION_CREATED_USER_ID);
			} catch (JSONException e) {
				result.setActionResult(ActionResult.FAIL);
				result.setErrorMessage("Created user Id is not given. "
						+ e.getMessage());
				result.setErrorType(ErrorType.LACK_OF_GIVEN_PARAMETER);
			}

			/*
			 * Get question id. This could be null if client side is trying to
			 * get result list.
			 */
			try {
				questionId = obj.getLong(ActionConstants.QUESTION_ID);
			} catch (JSONException e) {
				LogUtil.d(TAG, "JSONException: " + e.getMessage());
			}

			// If question id is not given from client side (meaning query
			// detail list)
			if (questionId == Constants.NO_QUESTION) {
				List<ResultListData> lists = ImpressionDataManager
						.getInstance().getQuestionResultList(result,
								createdUserId);
				JSONArray resultArray = createJSONArrayForResultDetail(lists);
				resultJson = util.createResultJsonObject(resultArray,
						responseId);
			} else {
				// If question id is given from client side (meaning get detail
				// data for certain question)
				ResultDetailData data = ImpressionDataManager.getInstance()
						.getQuestionResultDetail(result, questionId);
				JSONObject resultObject = createJSONObjectForResultList(data);
				resultJson = util.createResultJsonObject(resultObject,
						responseId);
			}

		} else {
			result.setActionResult(ActionResult.FAIL);
			result.setErrorMessage("param cannot be null");
			result.setErrorType(ErrorType.LACK_OF_GIVEN_PARAMETER);
		}

		if (result.isFailed()) {
			JSONObject failObject = createJSONObjectForFail(result);
			resultJson = util.createResultJsonObject(failObject, responseId);
		}

		LogUtil.d(TAG, "resultJson:" + resultJson.toString());

		return resultJson.toString();
	}

	private JSONArray createJSONArrayForResultDetail(
			List<ResultListData> results) {
		LogUtil.d(TAG, "createJSONArrayForResultDetail");
		JSONArray array = new JSONArray();

		for (ResultListData data : results) {

			JSONObject obj = new JSONObject();

			try {
				obj.put(ActionConstants.QUESTION_DESCRIPTION,
						data.getDescription());

				obj.put(ActionConstants.QUESTION_CATEGORY, data.getCategory());
				obj.put(ActionConstants.QUESTION_NUM_OF_ANSWER,
						data.getNumOfAnswer());
				obj.put(ActionConstants.QUESTION_LAST_COMMENT_DATE,
						data.getLastCommentDate());
				obj.put(ActionConstants.QUESTION_NUM_OF_ADDITIONAL_COMMENT,
						data.getNumOfAdditionalComment());
				array.put(obj);
			} catch (JSONException e) {
				LogUtil.d(TAG, "JSONException: " + e.getMessage());
			}

		}

		return array;
	}

	private JSONObject createJSONObjectForResultList(ResultDetailData data) {
		LogUtil.d(TAG, "createJSONObjectForResultList");
		JSONObject obj = new JSONObject();

		try {
			obj.put(ActionConstants.QUESTION_ID, data.getQuestionId());
			obj.put(ActionConstants.QUESTION_THUMBNAIL, data.getThumbnail());
			obj.put(ActionConstants.QUESTION_POST_DATE, data.getPostDate());
			obj.put(ActionConstants.QUESTION_CREATED_USER_NAME,
					data.getUserName());
			obj.put(ActionConstants.QUESTION_DESCRIPTION, data.getDescription());
			obj.put(ActionConstants.QUESTION_CHOICE_A, data.getChoiceA());
			obj.put(ActionConstants.QUESTION_CHOICE_B, data.getChoiceB());
			obj.put(ActionConstants.QUESTION_ADDITIONAL_QUESTION,
					data.getAdditionalQuestion());
			obj.put(ActionConstants.QUESTION_ADDITIONAL_COMMENT,
					data.getAdditionalComments());
			obj.put(ActionConstants.QUESTION_GENDER_MALE, data.getMale());
			obj.put(ActionConstants.QUESTION_GENDER_FEMALE, data.getFemale());
			obj.put(ActionConstants.QUESTION_GENDER_UNKNOWN,
					data.getGenderUnknown());
			obj.put(ActionConstants.QUESTION_AGE_UNDER10, data.getUnder10());

			obj.put(ActionConstants.QUESTION_AGE_FROM10_20, data.getFrom10_20());
			obj.put(ActionConstants.QUESTION_AGE_FROM20_30, data.getFrom20_30());
			obj.put(ActionConstants.QUESTION_AGE_FROM30_40, data.getFrom39_40());
			obj.put(ActionConstants.QUESTION_AGE_FROM40_50, data.getFrom40_50());
			obj.put(ActionConstants.QUESTION_AGE_FROM50_60, data.getFrom50_60());
			obj.put(ActionConstants.QUESTION_AGE_FROM60_70, data.getFrom60_70());
			obj.put(ActionConstants.QUESTION_AGE_OVER70, data.getOver70());
			obj.put(ActionConstants.QUESTION_CREATED_USER_ID,
					data.getCreatedUserId());
			obj.put(ActionConstants.QUESTION_CATEGORY, data.getCategory());
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		return obj;
	}

	private JSONObject createJSONObjectForFail(Result result) {
		JSONObject obj = new JSONObject();
		try {
			obj.put(ActionConstants.ERROR, result.getErrorType() + " / "
					+ result.getErrorMessage());
		} catch (JSONException e) {
			LogUtil.d(TAG, "JSONException: " + e.getMessage());
		}

		return obj;

	}

}
