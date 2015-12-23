package com.mame.impression.data;

import java.util.List;

import com.mame.impression.data.QuestionData.Category;

public class QuestionDataBuilder {
	private QuestionData mData;

	public QuestionDataBuilder() {
		mData = new QuestionData();
	}

	public QuestionDataBuilder setQuestionId(long questionId) {
		mData.setQuestionId(questionId);
		return this;
	}

	public QuestionDataBuilder setDescription(String description) {
		mData.setDescription(description);
		return this;
	}
	
	public QuestionDataBuilder setCategory(Category category) {
		mData.setCategory(category);
		return this;
	}

	public QuestionDataBuilder setChoiceA(String choiceA) {
		mData.setChoiceA(choiceA);
		return this;
	}

	public QuestionDataBuilder setChoiceB(String choiceB) {
		mData.setChoiceB(choiceB);
		return this;
	}

	public QuestionDataBuilder setCreatedUserName(String createdUserName) {
		mData.setCreatedUserName(createdUserName);
		return this;
	}

	public QuestionDataBuilder setCreatedUserId(long createdUserId) {
		mData.setCreatedUserId(createdUserId);
		return this;
	}

	public QuestionDataBuilder setThumbnail(String thumbnail) {
		mData.setThumbnail(thumbnail);
		return this;
	}

	public QuestionDataBuilder setChoiceAResponseNum(int choiceAResponseNum) {
		mData.setChoiceAResponseNum(choiceAResponseNum);
		return this;
	}

	public QuestionDataBuilder setChoiceBResponseNum(int choiceBResponseNum) {
		mData.setChoiceBResponseNum(choiceBResponseNum);
		return this;
	}

	public QuestionDataBuilder setAdditionalQuestion(String additionalQuestion) {
		mData.setAdditionalQuestion(additionalQuestion);
		return this;
	}

	public QuestionDataBuilder setAdditionalAnswer(List<String> additionalAnswer) {
		mData.setAdditioanlAnswer(additionalAnswer);
		return this;
	}

	public QuestionData getResult() {
		return mData;
	}
}
