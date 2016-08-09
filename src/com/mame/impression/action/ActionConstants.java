package com.mame.impression.action;

import com.mame.impression.constant.Constants;

public class ActionConstants {
	/**
	 * URL pattern for user
	 */
	public static final String KEY_USER = "/user";

	/**
	 * URL pattern for question list
	 */
	public static final String KEY_LIST = "/list";

	/**
	 * URL pattern for questions operation
	 */
	public static final String KEY_QUESTION = "/questions";

	/**
	 * URL pattern for detail of question operation
	 */
	public static final String KEY_QUESTION_RESULT = "/results";

	/**
	 * URL pattern for answer
	 */
	public static final String KEY_ANSWER = "/answers";

	/**
	 * URL pattern for device
	 */
	public static final String KEY_DEVICE = "/device";

	/**
	 * URL pattern for contact
	 */
	public static final String KEY_CONTACT = "/contact";

	/**
	 * Methods for RESTful APIs
	 */
	public static final String GET = "GET";

	public static final String POST = "POST";

	public static final String PUT = "PUT";

	public static final String DELETE = "DELETE";

	/**
	 * Field names (Input/output common)
	 */
	public static final String ID = "id";

	public static final String PARAM = "param";

	public static final String VERSION = "version";

	/**
	 * field names (Output)
	 */
	public static final String ERROR = "error";

	/**
	 * Parameter fields for UserData
	 */
	public final static String USER_ID = "userid";

	public final static String USER_NAME = "username";

	public final static String USER_PASSWORD = "password";

	public final static String USER_THUMBNAIL = "thumbnail";

	public final static String USER_MAILADDRESS = "email";

	public final static String USER_AGE = "age";

	public final static String USER_GENDER = "gender";

	public final static String USER_CREATED_QUESTION_ID = "created_question_ids";

	public final static String USER_DEVICE_ID = "deviceid";

	/**
	 * Field name for contact
	 */
	public final static String CONTACT_NAME = "name";

	public final static String CONTACT_EMAIL = "email";

	public final static String CONTACT_MESSAGE = "message";

	public final static String CONTACT_RESULT = "contact_result";

	public final static String CONTACT_SUCCESS = "success";

	public final static String CONTACT_FAIL = "fail";

	/**
	 * Parameter fields for QuestionData
	 */
	public final static String QUESTION_ID = "questionId";

	public final static String QUESTION_DESCRIPTION = "description";

	public final static String QUESTION_CATEGORY = "category";

	public final static String QUESTION_CHOICE = "choice";

	public final static String QUESTION_CHOICE_A = "choice_a";

	public final static String QUESTION_CHOICE_B = "choice_b";

	public final static String QUESTION_POST_DATE = "post_date";

	public final static String QUESTION_CREATED_USER_NAME = "created_user_name";

	public final static String QUESTION_CREATED_USER_ID = "created_user_id";

	public final static String QUESTION_THUMBNAIL = "thumbnail";

	public final static String QUESTION_CHOICE_A_RESPONSE = "choice_a_response";

	public final static String QUESTION_CHOICE_B_RESPONSE = "choice_b_response";

	public final static String QUESTION_ADDITIONAL_QUESTION = "additional_question";

	public final static String QUESTION_ADDITIONAL_COMMENT = "additional_comment";

	public final static String QUESTION_SELECTED_CHOICE = "selected_choice";

	public final static String QUESTION_GENDER_MALE = "male";

	public final static String QUESTION_GENDER_FEMALE = "female";

	public final static String QUESTION_GENDER_UNKNOWN = "gender_unknonw";

	public final static String QUESTION_AGE_UNDER10 = "under10";

	public final static String QUESTION_AGE_FROM10_20 = "from10_20";

	public final static String QUESTION_AGE_FROM20_30 = "from20_30";

	public final static String QUESTION_AGE_FROM30_40 = "from30_40";

	public final static String QUESTION_AGE_FROM40_50 = "from40_50";

	public final static String QUESTION_AGE_FROM50_60 = "from50_60";

	public final static String QUESTION_AGE_FROM60_70 = "from60_70";

	public final static String QUESTION_AGE_OVER70 = "over70";

	public final static String QUESTION_AGE_UNKNOWN = "generation_unknown";

	public final static String QUESTION_CHOICE_ITEM_A = "item_a";

	public final static String QUESTION_CHOICE_ITEM_B = "item_b";

	public final static String QUESTION_LAST_COMMENT_DATE = "last_comment_date";

	public final static String QUESTION_NUM_OF_ADDITIONAL_COMMENT = "additional_comment_num";

}
