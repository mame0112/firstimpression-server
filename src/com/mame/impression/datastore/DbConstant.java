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
	public final static String ENTITY_QUESTION_ID = "questionid";
	
	public final static String ENTITY_QUESTION_DESCRIPTION = "description";
	
	public final static String ENTITY_QUESTION_CATEGORY = "category";
	
	public final static String ENTITY_QUESTION_CHOICE_A = "choice_a";
	
	public final static String ENTITY_QUESTION_CHOICE_B = "choice_b";
	
	public final static String ENTITY_QUESTION_CREATED_USERNAME = "created_username";
	
	public final static String ENTITY_QUESTION_CREATED_USERID = "created_userid";
	
	public final static String ENTITY_QUESTION_CREATED_USER_THUMB = "created_user_thumb";
	
	public final static String ENTITY_QUESTION_CHOICE_A_RESP = "choice_a_response";
	
	public final static String ENTITY_QUESTION_CHOICE_B_RESP = "choice_b_response";
	
	public final static String ENTITY_QUESTION_ADDITIONAL_QUESTION = "additional_question";
	
	public final static String ENTITY_QUESTION_ADDITIONAL_ANSWER = "additional_answer";
	
	
	


}
