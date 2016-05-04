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
	
	public final static String ENTITY_USER_DEVICE_ID = "deviceid";

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

	public final static String ENTITY_QUESTION_GENDER_MALE_A = "male_a";

	public final static String ENTITY_QUESTION_GENDER_FEMALE_A = "female_a";

	public final static String ENTITY_QUESTION_GENDER_UNKNOWN_A = "unknown_a";

	public final static String ENTITY_QUESTION_AGE_UNDER10_A = "under10_a";

	public final static String ENTITY_QUESTION_AGE_FROM10_20_A = "from10_20_a";

	public final static String ENTITY_QUESTION_AGE_FROM20_30_A = "from20_30_a";

	public final static String ENTITY_QUESTION_AGE_FROM30_40_A = "from30_40_a";

	public final static String ENTITY_QUESTION_AGE_FROM40_50_A = "from40_50_a";

	public final static String ENTITY_QUESTION_AGE_FROM50_60_A = "from50_60_a";

	public final static String ENTITY_QUESTION_AGE_FROM60_70_A = "from60_70_a";

	public final static String ENTITY_QUESTION_AGE_OVER70_A = "over70_a";
	
	public final static String ENTITY_QUESTION_AGE_UNKNOWN_A = "age_unknown_a";
	
	public final static String ENTITY_QUESTION_GENDER_MALE_B = "male_b";

	public final static String ENTITY_QUESTION_GENDER_FEMALE_B = "female_b";

	public final static String ENTITY_QUESTION_GENDER_UNKNOWN_B = "unknown_b";

	public final static String ENTITY_QUESTION_AGE_UNDER10_B = "under10_b";

	public final static String ENTITY_QUESTION_AGE_FROM10_20_B = "from10_20_b";

	public final static String ENTITY_QUESTION_AGE_FROM20_30_B = "from20_30_b";

	public final static String ENTITY_QUESTION_AGE_FROM30_40_B = "from30_40_b";

	public final static String ENTITY_QUESTION_AGE_FROM40_50_B = "from40_50_b";

	public final static String ENTITY_QUESTION_AGE_FROM50_60_B = "from50_60_b";

	public final static String ENTITY_QUESTION_AGE_FROM60_70_B = "from60_70_b";

	public final static String ENTITY_QUESTION_AGE_OVER70_B = "over70_b";
	
	public final static String ENTITY_QUESTION_AGE_UNKNOWN_B = "age_unknown_b";

	public final static String ENTITY_QUESTION_ADDITIONAL_QUESTION = "additional_question";

	public final static String ENTITY_QUESTION_ADDITIONAL_ANSWER = "additional_answer";

	public final static String ENTITY_QUESTION_LAST_UPDATE_DATE = "last_update_date";

	public final static String ENTITY_QUESTION_NUM_OF_ADDITIONAL_COMMENT = "additional_comment_num";

}
