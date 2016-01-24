package com.mame.impression.data;

public class ResultListDataBuilder {

	private ResultListData mData = new ResultListData();
	
	public ResultListDataBuilder setQuestionId(long questionId) {
		mData.setQuestionId(questionId);
		return this;
	}

	public ResultListDataBuilder setDescription(String description) {
		mData.setDescription(description);
		return this;
	}

	public ResultListDataBuilder setCategory(String category) {
		mData.setCategory(category);
		return this;
	}

	public ResultListDataBuilder setNumOfChoiceA(int numOfChoiceA) {
		mData.setNumOfChoiceA(numOfChoiceA);
		return this;
	}
	
	public ResultListDataBuilder setNumOfChoiceB(int numOfChoiceB) {
		mData.setNumOfChoiceB(numOfChoiceB);
		return this;
	}

	public ResultListDataBuilder setLastCommentDate(long date) {
		mData.setLastCommentDate(date);
		return this;
	}

	public ResultListDataBuilder setNumOfAdditionalComment(int numOfAddiComment) {
		mData.setNumOfAdditionalComment(numOfAddiComment);
		return this;
	}

	public ResultListData getResult() {
		return mData;
	}

}
