package com.mame.impression.gcm;

public class GcmConstants {

	private final static String SERVER_API_KEY = "AIzaSyDBhdu8WIhhwMw0atP4V-XzBkpLNHh8msw";

	final static String MESSAGE = "message";

	final static String PARAM_QUESTION_ID = "question_id";

	final static String PARAM_QUESTION_DESCRIPTION = "description";

	final static String PARAM_DEVICE_ID = "device_id";

	final static String PARAM_DEVICE_ID_LIST = "device_ids";

	/**
	 * Push message constants to client side
	 */
	final static String PUSH_MESSAGE_CATEGORY = "category";

	final static String PUSH_MESSAGE_DESCRIPTION = "description";

	final static String PUSH_MESSAGE_QUESTION_ID = "questionId";

	/**
	 * Push category. Client should identify this category and parse pushed
	 * message depends on this.
	 */
	static enum PUSH_CATEGORY {
		/**
		 * Someone replied to user's message
		 */
		MESSAGE_REPLIED,
		/**
		 * Someone newly created message
		 */
		MESSAGE_CREATED
	}

}
