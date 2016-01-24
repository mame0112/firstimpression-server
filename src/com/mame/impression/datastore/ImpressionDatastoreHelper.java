package com.mame.impression.datastore;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.mame.impression.Result;
import com.mame.impression.Result.ActionResult;
import com.mame.impression.constant.Constants;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.QuestionDataBuilder;
import com.mame.impression.data.ResultDetailDataItem;
import com.mame.impression.data.ResultDetailDataItemBuilder;
import com.mame.impression.data.ResultListData;
import com.mame.impression.data.ResultListDataBuilder;
import com.mame.impression.data.UserData;
import com.mame.impression.data.QuestionData.Category;
import com.mame.impression.data.UserData.Age;
import com.mame.impression.data.UserData.Gender;
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
		Age age = null;
		if (ageString != null) {
			age = Age.valueOf(ageString);
		}

		String genderString = (String) e
				.getProperty(DbConstant.ENTITY_USER_GENDER);
		Gender gender = null;
		if (genderString != null) {
			gender = Gender.valueOf(genderString);
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

	public Entity putQuestionDataToEntity(Result result, QuestionData data,
			Entity e) {
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
			e.setProperty(DbConstant.ENTITY_QUESTION_POST_DATE,
					data.getCreatedDate());
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
			result.setErrorMessage(e1.getMessage());
			result.setActionResult(ActionResult.FAIL);
		}

		return e;

	}

	public QuestionData createQuestionDataFromEntity(Entity e) {

		if (e == null) {
			throw new IllegalArgumentException("Entity cannot be null");
		}

		QuestionDataBuilder builder = new QuestionDataBuilder();

		long questionId = (Long) e.getProperty(DbConstant.ENTITY_QUESTION_ID);
		long createdDate = (Long) e
				.getProperty(DbConstant.ENTITY_QUESTION_POST_DATE);
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
				.setCreatedDate(createdDate)
				.setCreatedUserName(createdUserName)
				.setCreatedUserId(createdUserId).setThumbnail(thumbnail)
				.setChoiceAResponseNum((int) choiceAResponse)
				.setChoiceBResponseNum((int) choiceBResponse)
				.setAdditionalQuestion(additionalQuestion)
				.setAdditionalAnswer(additionalAnswer).getResult();

	}

	/**
	 * Translate Entity to ResultListData format
	 * 
	 * @param e
	 * @return
	 */
	public ResultListData createResultListDataFromEntity(Entity e) {

		LogUtil.d(TAG, "createResultListDataFromEntity");

		if (e == null) {
			throw new IllegalArgumentException("Entity cannot be null");
		}

		ResultListDataBuilder builder = new ResultListDataBuilder();

		long questionId = Constants.NO_QUESTION;
		String description = null;
		String category = null;
		long lastUpdateDate = 0L;
		long numOfChoiceA = 0;
		long numOfChoiceB = 0;
		int numOfAdditionalComment = 0;

		// Mandatory field
		try {
			questionId = (long) e.getProperty(DbConstant.ENTITY_QUESTION_ID);
			description = (String) e
					.getProperty(DbConstant.ENTITY_QUESTION_DESCRIPTION);
			category = (String) e
					.getProperty(DbConstant.ENTITY_QUESTION_CATEGORY);
			numOfChoiceA = (long) e
					.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_A_RESP);
			numOfChoiceB = (long) e
					.getProperty(DbConstant.ENTITY_QUESTION_CHOICE_B_RESP);
			numOfAdditionalComment = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_NUM_OF_ADDITIONAL_COMMENT);
		} catch (Exception e1) {
			LogUtil.d(TAG, e1.getMessage());
		}

		// Optional field
		try {
			lastUpdateDate = (long) e
					.getProperty(DbConstant.ENTITY_QUESTION_LAST_UPDATE_DATE);
		} catch (Exception e1) {
			LogUtil.d(TAG, e1.getMessage());
			// If no last update date xist, we use created date instead.
			lastUpdateDate = (long) e
					.getProperty(DbConstant.ENTITY_QUESTION_POST_DATE);
		}

		return builder.setQuestionId(questionId).setDescription(description)
				.setCategory(category).setLastCommentDate(lastUpdateDate)
				.setNumOfChoiceA((int) numOfChoiceA)
				.setNumOfChoiceB((int) numOfChoiceB)
				.setNumOfAdditionalComment(numOfAdditionalComment).getResult();

	}

	public ResultDetailDataItem getAnswerResultResultItemA(Entity e) {
		int male = 0;
		try {
			male = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_GENDER_MALE_A);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int female = 0;
		try {
			female = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_GENDER_FEMALE_A);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int genderUnknown = 0;
		try {
			genderUnknown = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_GENDER_UNKNOWN_A);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int under10 = 0;
		try {
			under10 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_UNDER10_A);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from10_20 = 0;
		try {
			from10_20 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM10_20_A);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from20_30 = 0;
		try {
			from20_30 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM20_30_A);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from30_40 = 0;
		try {
			from30_40 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM30_40_A);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from40_50 = 0;
		try {
			from40_50 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM40_50_A);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from50_60 = 0;
		try {
			from50_60 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM50_60_A);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from60_70 = 0;
		try {
			from60_70 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM60_70_A);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int over70 = 0;
		try {
			over70 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_OVER70_A);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		return new ResultDetailDataItemBuilder().setMale(male)
				.setFemale(female).setGenderUnknown(genderUnknown)
				.setUnder10(under10).setFrom10_20(from10_20)
				.setFrom20_30(from20_30).setFrom30_40(from30_40)
				.setFrom40_50(from40_50).setFrom50_60(from50_60)
				.setFrom60_70(from60_70).setOver70(over70).getResult();
	}

	public ResultDetailDataItem getAnswerResultResultItemB(Entity e) {
		int male = 0;
		try {
			male = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_GENDER_MALE_B);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int female = 0;
		try {
			female = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_GENDER_FEMALE_B);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int genderUnknown = 0;
		try {
			genderUnknown = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_GENDER_UNKNOWN_B);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int under10 = 0;
		try {
			under10 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_UNDER10_B);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from10_20 = 0;
		try {
			from10_20 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM10_20_B);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from20_30 = 0;
		try {
			from20_30 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM20_30_B);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from30_40 = 0;
		try {
			from30_40 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM30_40_B);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from40_50 = 0;
		try {
			from40_50 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM40_50_B);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from50_60 = 0;
		try {
			from50_60 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM50_60_B);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int from60_70 = 0;
		try {
			from60_70 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_FROM60_70_B);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		int over70 = 0;
		try {
			over70 = (int) e
					.getProperty(DbConstant.ENTITY_QUESTION_AGE_OVER70_B);
		} catch (Exception e1) {
			LogUtil.d(TAG, "Exception: " + e1.getMessage());
		}

		return new ResultDetailDataItemBuilder().setMale(male)
				.setFemale(female).setGenderUnknown(genderUnknown)
				.setUnder10(under10).setFrom10_20(from10_20)
				.setFrom20_30(from20_30).setFrom30_40(from30_40)
				.setFrom40_50(from40_50).setFrom50_60(from50_60)
				.setFrom60_70(from60_70).setOver70(over70).getResult();
	}

	public void setGenderAndAgeData(Entity entity, Gender gender, Age age,
			int choice) {
		LogUtil.d(TAG, "setGenderAndAgeData");

		if (entity == null) {
			throw new IllegalArgumentException("Entity cannot be null");
		}

		if (gender == null) {
			throw new IllegalArgumentException("Gender cannot be null");
		}

		if (age == null) {
			throw new IllegalArgumentException("Age cannot be null");
		}
		
		switch (gender) {
		case MALE:
			switch (choice) {
			case 0:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_GENDER_MALE_A);
				break;
			case 1:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_GENDER_MALE_B);
				break;
			default:
				break;
			}
			break;
		case FEMALE:
			switch (choice) {
			case 0:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_GENDER_FEMALE_A);
				break;
			case 1:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_GENDER_FEMALE_B);
				break;
			default:
				break;
			}
			break;
		case UNKNOWN:
			LogUtil.d(TAG,  "Unknown");
			switch (choice) {
			case 0:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_GENDER_UNKNOWN_A);
				break;
			case 1:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_GENDER_UNKNOWN_B);
				break;
			default:
				break;
			}
			break;
		case OTHER:
			LogUtil.d(TAG,  "Other");
			break;
		}
		
		switch (age) {
		case UNDER10:
			switch (choice) {
			case 0:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_UNDER10_A);
				break;
			case 1:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_UNDER10_B);
				break;
			default:
				break;
			}
			break;
		case FROM10_20:
			switch (choice) {
			case 0:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM10_20_A);
				break;
			case 1:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM10_20_B);
				break;
			default:
				break;
			}
			break;
		case FROM20_30:
			switch (choice) {
			case 0:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM20_30_A);
				break;
			case 1:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM20_30_B);
				break;
			default:
				break;
			}
			break;
		case FROM30_40:
			switch (choice) {
			case 0:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM30_40_A);
				break;
			case 1:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM30_40_B);
				break;
			default:
				break;
			}
			break;
		case FROM40_50:
			switch (choice) {
			case 0:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM40_50_A);
				break;
			case 1:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM40_50_B);
				break;
			default:
				break;
			}
			break;
		case FROM50_60:
			switch (choice) {
			case 0:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM50_60_A);
				break;
			case 1:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM50_60_B);
				break;
			default:
				break;
			}
			break;
		case FROM60_70:
			switch (choice) {
			case 0:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM60_70_A);
				break;
			case 1:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_FROM60_70_B);
				break;
			default:
				break;
			}
			break;
		case OVER70:
			switch (choice) {
			case 0:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_OVER70_A);
				break;
			case 1:
				updateCategoryResponseNum(entity,
						DbConstant.ENTITY_QUESTION_AGE_OVER70_B);
				break;
			default:
				break;
			}
			break;
		}

	}

	private void updateCategoryResponseNum(Entity entity, String fieldName) {
		long num = 0;
		try {
			num = (long) entity.getProperty(fieldName);
			num = num + 1;
			entity.setProperty(fieldName, num);
		} catch (Exception e){
			//If no property exist
			entity.setProperty(fieldName, 1);
		}

	}
}
