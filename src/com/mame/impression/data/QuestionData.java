package com.mame.impression.data;

import java.util.ArrayList;
import java.util.List;

import com.mame.impression.constant.Constants;

public class QuestionData {

	/**
	 * Question id. This also stands for created time of this question
	 */
	private long mQuestionId = Constants.NO_QUESTION;

	private String mDescription;

	// TODO TO be used if necessary
	private Category mCategory;

	private String mChoiceA;

	private String mChoiceB;

	private String mCreatedUserName;

	private long mCreatedUserId;

	private String mThumbnail;

	private int mChoiceAResponse;

	private int mChoiceBResponse;

	// TODO TO be used if necessary
	private String mAdditionalQuestion;

	// TODO TO be used if necessary
	private List<String> mAdditionalAnswer;

	public enum Category {
		GENERAL
	}

	/* Package private */QuestionData() {

	}

	// TODO This should not be public. Need to consider
	public void setQuestionId(long questionId) {
		mQuestionId = questionId;
	}

	void setDescription(String description) {
		mDescription = description;
	}

	void setCategory(Category category) {
		mCategory = category;
	}

	void setChoiceA(String choiceA) {
		mChoiceA = choiceA;
	}

	void setChoiceB(String choiceB) {
		mChoiceB = choiceB;
	}

	void setCreatedUserName(String createdUserName) {
		mCreatedUserName = createdUserName;
	}

	void setCreatedUserId(long createdUserId) {
		mCreatedUserId = createdUserId;
	}

	void setThumbnail(String thumbnail) {
		mThumbnail = thumbnail;
	}

	void setChoiceAResponseNum(int choiceAResponseNum) {
		mChoiceAResponse = choiceAResponseNum;
	}

	void setChoiceBResponseNum(int choiceBResponseNum) {
		mChoiceBResponse = choiceBResponseNum;
	}

	void setAdditionalQuestion(String additionalQuestion) {
		mAdditionalQuestion = additionalQuestion;
	}

	void setAdditioanlAnswer(List<String> additionalAnswer) {
		mAdditionalAnswer = additionalAnswer;
	}

	public long getQuestionId() {
		return mQuestionId;
	}

	public String getDescription() {
		return mDescription;
	}

	public Category getCategory() {
		return mCategory;
	}

	public String getChoiceA() {
		return mChoiceA;
	}

	public String getChoiceB() {
		return mChoiceB;
	}

	public String getCreatedUserName() {
		return mCreatedUserName;
	}

	public long getCreatedUserId() {
		return mCreatedUserId;
	}

	public String getThumbnail() {
		return mThumbnail;
	}

	public int getChoiceAResponseNum() {
		return mChoiceAResponse;
	}

	public int getChoiceBResponseNum() {
		return mChoiceBResponse;
	}

	public String getAdditionalQuestion() {
		return mAdditionalQuestion;
	}

	public List<String> getAdditionalAnswer() {
		return mAdditionalAnswer;
	}
}
