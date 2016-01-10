package com.mame.impression.data;

import com.mame.impression.constant.Constants;

public class ResultListData {
	
	private long mQuestionId = Constants.NO_QUESTION;

	private String mDescription;

	private String mCategory;

	private int mNumOfAnswer = 0;

	private long mLastCommentDate = 0L;

	private int mNumOfAdditionalComment = 0;
	
	void setQuestionId(long questionId){
		mQuestionId = questionId;
	}

	void setDescription(String description) {
		mDescription = description;
	}

	void setCategory(String category) {
		mCategory = category;
	}

	void setNumOfAnswer(int numOfAnswer) {
		mNumOfAnswer = numOfAnswer;
	}

	void setLastCommentDate(long date) {
		mLastCommentDate = date;
	}

	void setNumOfAdditionalComment(int numOfAddiComment) {
		mNumOfAdditionalComment = numOfAddiComment;
	}
	
	public long getQuestionId(){
		return mQuestionId;
	}

	public String getDescription() {
		return mDescription;
	}

	public String getCategory() {
		return mCategory;
	}

	public int getNumOfAnswer() {
		return mNumOfAnswer;
	}

	public long getLastCommentDate() {
		return mLastCommentDate;
	}

	public int getNumOfAdditionalComment() {
		return mNumOfAdditionalComment;
	}

}
