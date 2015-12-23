package com.mame.impression.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.mame.impression.Result;
import com.mame.impression.constant.Constants;
import com.mame.impression.constant.JsonConstants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.manager.ImpressionDataManager;
import com.mame.impression.util.LogUtil;

/**
 * Return Question list. JSON Format should be:
 * 
 * <pre>
 * {
 * 	"version": 1,
 * 	"id": 1,
 * 	"param": [{
 * 		"id": 1,
 * 		"thumbnail": 111,
 * 		"postdate": 222,
 * 		"username": "test name",
 * 		"description": "here is description",
 * 		"a_text": "choice A",
 * 		"b_text": "choice B"
 * 	}, {
 * 		"id": 2,
 * 		"thumbnail": 222,
 * 		"postdate": 333,
 * 		"username": "test name2",
 * 		"description": "here is description2",
 * 		"a_text": "choice A2",
 * 		"b_text": "choice B2"
 * 	}]
 * }
 * </pre>
 * 
 * @author kosukeEndo
 * 
 */
public class RequestQuestionListAction implements Action {

	private static final String TAG = Constants.TAG
			+ RequestQuestionListAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");

		String responseId = request.getParameter(ActionConstants.ID);
		String param = request.getParameter(ActionConstants.PARAM);

		if (param != null) {

			JSONObject obj = new JSONObject(param);
			int start = obj.getInt(ActionConstants.QUESTION_START_POS);
			int end = obj.getInt(ActionConstants.QUESTION_END_POS);

			// Create result
			Result result = new Result();

			List<QuestionData> questions = ImpressionDataManager.getInstance()
					.getLatestQuestionList(result, start, end);

			ActionUtil util = new ActionUtil();
			JSONArray questionArray = util
					.createJsonArrayFromQuestionList(questions);
			JSONObject resultJson = util.createResultJsonObject(questionArray,
					responseId);

			LogUtil.d(TAG, "resultJson: " + resultJson.toString());
			return resultJson.toString();
		}

		return null;

	}

}
