package com.mame.impression.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mame.impression.action.answer.ReplyToQuestionAction;
import com.mame.impression.action.answer.RequestAnswerAction;
import com.mame.impression.action.contact.ContactAction;
import com.mame.impression.action.debug.DebugCreateNewQuestionAction;
import com.mame.impression.action.list.RequestQuestionListAction;
import com.mame.impression.action.question.CreateNewQuestionAction;
import com.mame.impression.action.question.DeleteQuestionAction;
import com.mame.impression.action.question.RequestQuestionAction;
import com.mame.impression.action.question.UpdateQuestionAction;
import com.mame.impression.action.result.RequestQuestionDetailAction;
import com.mame.impression.action.user.SignInAction;
import com.mame.impression.action.user.SignOutAction;
import com.mame.impression.action.user.SignUpAction;
import com.mame.impression.action.user.UserInfoUpdateAction;
import com.mame.impression.constant.Constants;
import com.mame.impression.util.LogUtil;

public class ActionFactory {

	private static final String TAG = Constants.TAG
			+ ActionFactory.class.getSimpleName();

	private final static Map<String, Action> mAction = new HashMap<String, Action>();

	static {
		// User actions
		mAction.put(ActionConstants.GET + ActionConstants.KEY_USER,
				new SignInAction());
		mAction.put(ActionConstants.POST + ActionConstants.KEY_USER,
				new SignUpAction());
//		mAction.put(ActionConstants.DELETE + ActionConstants.KEY_USER,
//				new DeleteAccountAction());
		mAction.put(ActionConstants.PUT + ActionConstants.KEY_USER,
				new UserInfoUpdateAction());
		mAction.put(ActionConstants.DELETE + ActionConstants.KEY_USER,
				new SignOutAction());

		// Status
		// Use
		// Get my current status. (user name, point etc.)

		// Question list actions
		// Use
		mAction.put(ActionConstants.GET + ActionConstants.KEY_LIST,
				new RequestQuestionListAction());

		// Question actions
		mAction.put(ActionConstants.GET + ActionConstants.KEY_QUESTION,
				new RequestQuestionAction());
		mAction.put(ActionConstants.POST + ActionConstants.KEY_QUESTION,
				new CreateNewQuestionAction());
		mAction.put(ActionConstants.DELETE + ActionConstants.KEY_QUESTION,
				new DeleteQuestionAction());
		mAction.put(ActionConstants.PUT + ActionConstants.KEY_QUESTION,
				new UpdateQuestionAction());

		// Question detail actions
		mAction.put(ActionConstants.GET + ActionConstants.KEY_QUESTION_RESULT,
				new RequestQuestionDetailAction());

		// Answer action
		mAction.put(ActionConstants.GET + ActionConstants.KEY_ANSWER,
				new RequestAnswerAction());
		// Use
		mAction.put(ActionConstants.PUT + ActionConstants.KEY_ANSWER,
				new ReplyToQuestionAction());
		
		//Contact action
		mAction.put(ActionConstants.GET + ActionConstants.KEY_CONTACT,
				new ContactAction());
		
		//Debug action
		mAction.put(ActionConstants.POST + ActionConstants.KEY_DEBUG,
				new DebugCreateNewQuestionAction());
		
	}

	public static Action getAction(HttpServletRequest request)
			throws ActionException {

		String method = request.getMethod();
		String path = request.getPathInfo();
		
		LogUtil.d(TAG,  "method: " + method + " path: " + path); 

		if (method == null || path == null) {
			LogUtil.d(TAG, "getAction method or pathinfo is null");
			throw new ActionException("method or path is null");
		}

		LogUtil.d(TAG, "getAction: " + method + path);
		return mAction.get(method + path);

	}
}
