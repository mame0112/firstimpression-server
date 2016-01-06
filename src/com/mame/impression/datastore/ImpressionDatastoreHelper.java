package com.mame.impression.datastore;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.QuestionDataBuilder;
import com.mame.impression.data.UserData;
import com.mame.impression.data.QuestionData.Category;
import com.mame.impression.data.UserData.AGE;
import com.mame.impression.data.UserData.GENDER;
import com.mame.impression.data.UserDataBuilder;
import com.mame.impression.util.LogUtil;

public class ImpressionDatastoreHelper {

	private static final String TAG = Constants.TAG
			+ ImpressionDatastoreHelper.class.getSimpleName();

	public Entity putUserDataToEntity(UserData data, Entity e) {
		LogUtil.d(TAG, "putUserDataToEntity");

		if (data == null) {
			throw new IllegalArgumentException("UserData cannot be null");
		}

		if (e == null) {
			throw new IllegalArgumentException("Entity cannot be null");
		}

		e.setProperty(DbConstant.ENTITY_USER_ID, data.getUserId());

		if (data.getUserName() != null) {
			e.setProperty(DbConstant.ENTITY_USER_NAME, data.getUserName());
		}

		if (data.getPassword() != null) {
			e.setProperty(DbConstant.ENTITY_USER_PASSWORD, data.getPassword());
		}

		if (data.getThumbnailUrl() != null) {
			e.setProperty(DbConstant.ENTITY_USER_THUMBNAIL,
					data.getThumbnailUrl());
		}

		if (data.getAge() != null && data.getAge().name() != null) {
			e.setProperty(DbConstant.ENTITY_USER_AGE, data.getAge().name());
		}

		if (data.getGender() != null && data.getGender().name() != null) {
			e.setProperty(DbConstant.ENTITY_USER_GENDER, data.getGender()
					.name());
		}

		if (data.getCreatedQuestionIds() != null) {
			e.setProperty(DbConstant.ENTITY_USER_CREATED_QUESTION_ID,
					data.getCreatedQuestionIds());
		}

		return e;
	}

	/**
	 * Update user data. But input is null, its field will not updadte (keep
	 * default value)
	 * 
	 * @param data
	 * @param e
	 * @return
	 */
	public Entity putUserDataToEntityWithoutOverride(UserData data, Entity e) {
		LogUtil.d(TAG, "putUserDataToEntityWithoutOverride");

		if (data == null) {
			throw new IllegalArgumentException("UserData cannot be null");
		}

		if (e == null) {
			throw new IllegalArgumentException("Entity cannot be null");
		}

		if (data.getUserId() != Constants.NO_USER) {
			e.setProperty(DbConstant.ENTITY_USER_ID, data.getUserId());
		}

		if (data.getUserName() != null) {
			e.setProperty(DbConstant.ENTITY_USER_NAME, data.getUserName());
		}

		if (data.getPassword() != null) {
			e.setProperty(DbConstant.ENTITY_USER_PASSWORD, data.getPassword());
		}

		if (data.getThumbnailUrl() != null) {
			e.setProperty(DbConstant.ENTITY_USER_THUMBNAIL,
					data.getThumbnailUrl());
		}

		if (data.getAge() != null) {
			e.setProperty(DbConstant.ENTITY_USER_AGE, data.getAge().name());
		}

		if (data.getGender() != null) {
			e.setProperty(DbConstant.ENTITY_USER_GENDER, data.getGender()
					.name());
		}

		if (data.getCreatedQuestionIds() != null
				&& data.getCreatedQuestionIds().size() != 0) {
			e.setProperty(DbConstant.ENTITY_USER_CREATED_QUESTION_ID,
					data.getCreatedQuestionIds());
		}

		return e;
	}

	public UserData createUserDataFromEntity(Entity e) {
		LogUtil.d(TAG, "createUserDataFromEntity");

		if (e == null) {
			throw new IllegalArgumentException("Entity cannot be null");
		}

		UserDataBuilder builder = new UserDataBuilder();

		long userId = (Long) e.getProperty(DbConstant.ENTITY_USER_ID);
		String userName = (String) e.getProperty(DbConstant.ENTITY_USER_NAME);
		String ageString = (String) e.getProperty(DbConstant.ENTITY_USER_AGE);
		AGE age = null;
		if(ageString != null){
			age = AGE.valueOf(ageString);
		}
		
		String genderString = (String) e
				.getProperty(DbConstant.ENTITY_USER_GENDER);
		GENDER gender = null;
		if(genderString != null){
			gender = GENDER.valueOf(genderString);
		}
		String password = (String) e
				.getProperty(DbConstant.ENTITY_USER_PASSWORD);
		String thumbUrl = (String) e
				.getProperty(DbConstant.ENTITY_USER_THUMBNAIL);
		List<Long> createdIds = (List) e
				.getProperty(DbConstant.ENTITY_USER_CREATED_QUESTION_ID);

		return builder.setUserId(userId).setUserName(userName)
				.setPassword(password).setThumbnailUrl(thumbUrl).setAge(age)
				.setGender(gender).setCreatedQuestionIds(createdIds)
				.getResult();

	}

	public Entity putQuestionDataToEntity(QuestionData data, Entity e) {
		LogUtil.d(TAG, "putQuestionDataToEntity");

		if (data == null) {
			throw new IllegalArgumentException("QuestionData cannot be null");
		}

		if (e == null) {
			throw new IllegalArgumentException("Entity cannot be null");
		}

		LogUtil.d(TAG, "data.getCategory().name(): "
				+ data.getCategory().name());

		try {
			e.setProperty(DbConstant.ENTITY_QUESTION_ID, data.getQuestionId());
			e.setProperty(DbConstant.ENTITY_QUESTION_DESCRIPTION,
					data.getDescription());
			e.setProperty(DbConstant.ENTITY_QUESTION_CATEGORY, data
					.getCategory().name());
			e.setProperty(DbConstant.ENTITY_QUESTION_CHOICE_A,
					data.getChoiceA());
			e.setProperty(DbConstant.ENTITY_QUESTION_CHOICE_B,
					data.getChoiceB());
			e.setProperty(DbConstant.ENTITY_QUESTION_CREATED_USERNAME,
					data.getCreatedUserName());
			e.setProperty(DbConstant.ENTITY_QUESTION_CREATED_USERID,
					data.getCreatedUserId());
			e.setProperty(DbConstant.ENTITY_QUESTION_CREATED_USER_THUMB,
					data.getThumbnail());
			e.setProperty(DbConstant.ENTITY_QUESTION_CHOICE_A_RESP,
					data.getChoiceAResponseNum());
			e.setProperty(DbConstant.ENTITY_QUESTION_CHOICE_B_RESP,
					data.getChoiceBResponseNum());
			e.setProperty(DbConstant.ENTITY_QUESTION_ADDITIONAL_QUESTION,
					data.getAdditionalQuestion());
			e.setProperty(DbConstant.ENTITY_QUESTION_ADDITIONAL_ANSWER,
					data.getAdditionalAnswer());
		} catch (IllegalArgumentException e1) {
			LogUtil.d(TAG, "IllegalArgumentException: " + e1.getMessage());
		}

		return e;

	}

	public QuestionData createQuestionDataFromEntity(Entity e) {

		LogUtil.d(TAG, "createQuestionDataFromEntity");

		if (e == null) {
			throw new IllegalArgumentException("Entity cannot be null");
		}

		QuestionDataBuilder builder = new QuestionDataBuilder();

		long questionId = (Long) e.getProperty(DbConstant.ENTITY_QUESTION_ID);
		String description = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_DESCRIPTION);
		Category category = Category.valueOf((String) e
				.getProperty(DbConstant.ENTITY_QUESTION_CATEGORY));
		String choiceA = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_A);
		String choiceB = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_B);
		String createdUserName = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_CREATED_USERNAME);
		long createdUserId = (Long) e
				.getProperty(DbConstant.ENTITY_QUESTION_CREATED_USERID);
		String thumbnail = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_CREATED_USER_THUMB);
		long choiceAResponse = (long) e
				.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_A_RESP);
		long choiceBResponse = (long) e
				.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_B_RESP);
		String additionalQuestion = (String) e
				.getProperty(DbConstant.ENTITY_QUESTION_ADDITIONAL_QUESTION);
		List<String> additionalAnswer = (List) e
				.getProperty(DbConstant.ENTITY_QUESTION_ADDITIONAL_ANSWER);

		return builder.setQuestionId(questionId).setDescription(description)
				.setCategory(category).setChoiceA(choiceA).setChoiceB(choiceB)
				.setCreatedUserName(createdUserName)
				.setCreatedUserId(createdUserId).setThumbnail(thumbnail)
				.setChoiceAResponseNum((int)choiceAResponse)
				.setChoiceBResponseNum((int)choiceBResponse)
				.setAdditionalQuestion(additionalQuestion)
				.setAdditionalAnswer(additionalAnswer).getResult();

	}
}
