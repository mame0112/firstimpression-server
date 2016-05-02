package com.mame.impression;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mame.impression.datastore.DbConstant;
import com.mame.impression.datastore.MemcacheConstant;
import com.mame.impression.datastore.MemcacheHandler;
import com.mame.impression.util.LogUtil;

public class MemcacheClearServlet extends HttpServlet {

	public final static Logger log = Logger
			.getLogger(MemcacheClearServlet.class.getName());

	public final static String TAG = MemcacheClearServlet.class.getName();

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		LogUtil.d(TAG, "doGet");

		// Delete cache for latest question list
		MemcacheHandler.delete(DbConstant.KIND_QUESTION,
				MemcacheConstant.LATEST_QUESTION);

		// Delete cache for older question list
		MemcacheHandler.delete(DbConstant.KIND_QUESTION,
				MemcacheConstant.OLDER_QUESTION);

	}

}
