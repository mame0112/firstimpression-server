package com.mame.impression;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mame.impression.action.Action;
import com.mame.impression.action.ActionException;
import com.mame.impression.action.ActionFactory;
import com.mame.impression.constant.Constants;
import com.mame.impression.util.LogUtil;
import java.util.logging.Logger;

public class ImpressionControllerServlet extends HttpServlet {

	public final static Logger log = Logger
			.getLogger(ImpressionControllerServlet.class.getName());
	
	public final static String TAG = ImpressionControllerServlet.class.getName();

	@Override
	public void init() {
		log.log(Level.INFO, "init");
		LogUtil.d(TAG, "init");
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		log.log(Level.INFO, "service");
//		LogUtil.d(TAG, "service:" + request.getPathInfo());
		LogUtil.d(TAG, "service");
		String result = null;
		Action action;

		try {
			action = ActionFactory.getAction(request);

			// If passed action is expected
			if (action != null) {
				try {
					result = action.execute(request, response);
					response.setStatus(200);
				} catch (Exception e) {
					LogUtil.d(TAG, "Exception: " + e.getMessage());
					response.setStatus(405);
				}
			} else {
				// If passed action is not expected (meaning it is not defined
				// in ActionFactory)
				response.setStatus(404);
			}
		} catch (ActionException e1) {
			response.setStatus(400);
		}

		if (result != null) {
			try {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(result);
			} catch (IOException e) {
				LogUtil.d(TAG, "IOException: " + e.getMessage());
				response.setStatus(500);
			}
		} else {
			response.setStatus(500);
		}
	}

}
