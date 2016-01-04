package com.mame.impression.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mame.impression.constant.Constants;
import com.mame.impression.util.LogUtil;

public class DeleteQuestionAction implements Action {

	private static final String TAG = Constants.TAG
			+ DeleteQuestionAction.class.getSimpleName();

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LogUtil.d(TAG, "execute");
		return null;
	}

}
