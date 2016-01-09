package com.mame.impression.data;

import java.util.List;

import com.mame.impression.constant.Constants;

public class ResultDetailData {

	private long mCreateUserId = Constants.NO_USER;

	private long mQuestionId = Constants.NO_QUESTION;

	private String mDescription = null;

	private String mChoiceA = null;

	private String mChoiceB = null;

	private int mMale;

	private int mFemale;

	private int mGenderUnknown;

	private int mUnder10;

	private int mFrom10_20;

	private int mFrom20_30;

	private int mFrom30_40;

	private int mFrom40_50;

	private int mFrom50_60;

	private int mFrom60_70;

	private int mOver70;

	private String mAdditionalQuestion = null;

	private List<String> mAdditionalComment = null;

	// TODO need to consider if we should store bitmap here.
	private String mThumbnail;

	private String mCategory = null;

	private String mCreatedUserName = null;

	private long mPostDate = 0;

	void setQuestionId(long questionId) {
		mQuestionId = questionId;
	}

	void setDescription(String description) {
		mDescription = description;
	}

	void setChoiceA(String choiceA) {
		mChoiceA = choiceA;
	}

	void setChoiceB(String choiceB) {
		mChoiceB = choiceB;
	}

	void setMale(int male) {
		mMale = male;
	}

	void setFemale(int female) {
		mFemale = female;
	}

	void setGenderUnknown(int unknown) {
		mGenderUnknown = unknown;
	}

	void setUnder10(int under10) {
		mUnder10 = under10;
	}

	void setFrom10_20(int from10_20) {
		mFrom10_20 = from10_20;
	}

	void setFrom20_30(int from20_30) {
		mFrom20_30 = from20_30;
	}

	void setFrom30_40(int from30_40) {
		mFrom30_40 = from30_40;
	}

	void setFrom40_50(int from40_50) {
		mFrom40_50 = from40_50;
	}

	void setFrom50_60(int from50_60) {
		mFrom50_60 = from50_60;
	}

	void setFrom60_70(int from60_70) {
		mFrom60_70 = from60_70;
	}

	void setOver70(int over70) {
		mOver70 = over70;
	}

	void setThumbnail(String thumbUrl) {
		mThumbnail = thumbUrl;
	}

	void setPostDate(long postDate) {
		mPostDate = postDate;
	}

	void setCreatedUserId(long createduserId) {
		mCreateUserId = createduserId;
	}

	void setCreatedUserName(String createdUserName) {
		mCreatedUserName = createdUserName;
	}

	void setCategory(String category) {
		mCategory = category;
	}

	void setAdditionalQuestion(String additionalQuestion) {
		mAdditionalQuestion = additionalQuestion;
	}

	void setAdditionalComment(List<String> comments) {
		mAdditionalComment = comments;
	}

	public long getQuestionId() {
		return mQuestionId;
	}

	public String getThumbnail() {
		return mThumbnail;
	}

	public long getPostDate() {
		return mPostDate;
	}

	public String getUserName() {
		return mCreatedUserName;
	}

	public String getDescription() {
		return mDescription;
	}

	public String getChoiceA() {
		return mChoiceA;
	}

	public String getChoiceB() {
		return mChoiceB;
	}

	public String getAdditionalQuestion() {
		return mAdditionalQuestion;
	}

	public List<String> getAdditionalComments() {
		return mAdditionalComment;
	}

	public int getMale() {
		return mMale;
	}

	public int getFemale() {
		return mFemale;
	}

	public int getGenderUnknown() {
		return mGenderUnknown;
	}

	public int getUnder10() {
		return mUnder10;
	}

	public int getFrom10_20() {
		return mFrom10_20;
	}

	public int getFrom20_30() {
		return mFrom20_30;
	}

	public int getFrom39_40() {
		return mFrom30_40;
	}

	public int getFrom40_50() {
		return mFrom40_50;
	}

	public int getFrom50_60() {
		return mFrom50_60;
	}

	public int getFrom60_70() {
		return mFrom60_70;
	}

	public int getOver70() {
		return mOver70;
	}

	public long getCreatedUserId() {
		return mCreateUserId;
	}

	public String getCategory() {
		return mCategory;
	}

}
