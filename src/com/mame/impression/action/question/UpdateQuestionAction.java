package com.mame.impression.action.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mame.impression.action.Action;
import com.mame.impression.constant.Constants;
import com.mame.impression.util.LogUtil;

public class UpdateQuestionAction implements Action {

	private static final String TAG = Constants.TAG
			+ UpdateQuestionAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");
		return null;
	}

}
