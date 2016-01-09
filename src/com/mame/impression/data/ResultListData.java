package com.mame.impression.data;

public class ResultListData {

	private String mDescription;

	private String mCategory;

	private int mNumOfAnswer = 0;

	private long mLastCommentDate = 0L;

	private int mNumOfAdditionalComment = 0;

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
