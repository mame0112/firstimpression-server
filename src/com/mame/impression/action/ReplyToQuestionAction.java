package com.mame.impression.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mame.impression.constant.Constants;
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

		ActionUtil util = new ActionUtil();

		return null;
	}

}
