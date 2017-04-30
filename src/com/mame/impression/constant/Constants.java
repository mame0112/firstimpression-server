package com.mame.impression.constant;

public class Constants {

	public static final String TAG = "FirstImpression/";

	public static final boolean IS_DEBUG = true;

	/**
	 * Boolean value to decide if memache is used.
	 */
	public static final boolean IS_MEMCACHE_ENABLED = true;

	public static final long NO_USER = -1;

	public static final long NO_QUESTION = -1;

	/**
	 * The number of question to be return to client side as latest question.
	 */
	public final static int LATEST_QUESTION_LIST_NUM = 10;
	
	public final static int OLDER_QUESTION_LIST_NUM = 10;
	
	/**
	 * The number of user they will receive new question created notification at same time
	 */
	public final static int MAX_NUMBER_OF_USER_NUM_FOR_PUSH = 10;

	
}
