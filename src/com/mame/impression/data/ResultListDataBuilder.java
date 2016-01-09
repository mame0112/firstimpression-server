package com.mame.impression.data;

public class ResultListDataBuilder {

	private ResultListData mData = new ResultListData();

	public ResultListDataBuilder setDescription(String description) {
		mData.setDescription(description);
		return this;
	}

	public ResultListDataBuilder setCategory(String category) {
		mData.setCategory(category);
		return this;
	}

	public ResultListDataBuilder setNumOfAnswer(int numOfAnswer) {
		mData.setNumOfAnswer(numOfAnswer);
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
