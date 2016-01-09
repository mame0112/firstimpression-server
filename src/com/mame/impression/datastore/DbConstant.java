package com.mame.impression.datastore;

public class DbConstant {

	/**
	 * Kind name for user table. Storing user related inforamtion (including
	 * user generated question ids)
	 */
	public static final String KIND_USER = "user";

	/**
	 * Kind name for question table.
	 */
	public static final String KIND_QUESTION = "question";

	/**
	 * Key divider so that we can store more than two data onto one key
	 */
	public final static String KEY_DIVIDER = "#";

	/**
	 * Entities for user kind
	 */
	public final static String ENTITY_USER_ID = "userid";

	public final static String ENTITY_USER_NAME = "username";

	public final static String ENTITY_USER_PASSWORD = "password";

	public final static String ENTITY_USER_THUMBNAIL = "thumbnail";

	public final static String ENTITY_USER_AGE = "age";

	public final static String ENTITY_USER_GENDER = "gender";

	public final static String ENTITY_USER_CREATED_QUESTION_ID = "created_question_ids";

	/**
	 * Entities for question kind
	 */

	public final static String ENTITY_QUESTION_ID = "question_id";

	public final static String ENTITY_QUESTION_DESCRIPTION = "description";

	public final static String ENTITY_QUESTION_CATEGORY = "category";

	public final static String ENTITY_QUESTION_CHOICE_A = "choice_a";

	public final static String ENTITY_QUESTION_CHOICE_B = "choice_b";

	public final static String ENTITY_QUESTION_POST_DATE = "post_date";

	public final static String ENTITY_QUESTION_CREATED_USERNAME = "created_username";

	public final static String ENTITY_QUESTION_CREATED_USERID = "created_userid";

	public final static String ENTITY_QUESTION_CREATED_USER_THUMB = "created_user_thumb";

	public final static String ENTITY_QUESTION_CHOICE_A_RESP = "choice_a_response";

	public final static String ENTITY_QUESTION_CHOICE_B_RESP = "choice_b_response";

	public final static String ENTITY_QUESTION_GENDER_MALE = "male";

	public final static String ENTITY_QUESTION_GENDER_FEMALE = "female";

	public final static String ENTITY_QUESTION_GENDER_UNKNOWN = "unknonw";

	public final static String ENTITY_QUESTION_AGE_UNDER10 = "under10";

	public final static String ENTITY_QUESTION_AGE_FROM10_20 = "from10_20";

	public final static String ENTITY_QUESTION_AGE_FROM20_30 = "from20_30";

	public final static String ENTITY_QUESTION_AGE_FROM30_40 = "from30_40";

	public final static String ENTITY_QUESTION_AGE_FROM40_50 = "from40_50";

	public final static String ENTITY_QUESTION_AGE_FROM50_60 = "from50_60";

	public final static String ENTITY_QUESTION_AGE_FROM60_70 = "from60_70";

	public final static String ENTITY_QUESTION_AGE_OVER70 = "over70";

	public final static String ENTITY_QUESTION_ADDITIONAL_QUESTION = "additional_question";

	public final static String ENTITY_QUESTION_ADDITIONAL_ANSWER = "additional_answer";

	public final static String ENTITY_QUESTION_NUM_OF_ANSWER = "num_of_answer";

	public final static String ENTITY_QUESTION_LAST_COMMENT_DATE = "last_comment_date";

	public final static String ENTITY_QUESTION_NUM_OF_ADDITIONAL_COMMENT = "additional_comment_num";

}
