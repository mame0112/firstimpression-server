package com.mame.impression.data;

import java.util.List;

public class ResultDetailDataBuilder {

	private ResultDetailData mContent = new ResultDetailData();

	public ResultDetailDataBuilder setQuestionId(long questionId) {
		mContent.setQuestionId(questionId);
		return this;
	}

	public ResultDetailDataBuilder setDescription(String description) {
		mContent.setDescription(description);
		return this;
	}

	public ResultDetailDataBuilder setChoiceA(String choiceA) {
		mContent.setChoiceA(choiceA);
		return this;
	}

	public ResultDetailDataBuilder setChoiceB(String choiceB) {
		mContent.setChoiceB(choiceB);
		return this;
	}

	public ResultDetailDataBuilder setMale(int male) {
		mContent.setMale(male);
		return this;
	}

	public ResultDetailDataBuilder setFemale(int female) {
		mContent.setFemale(female);
		return this;
	}

	public ResultDetailDataBuilder setGenderUnknown(int unknown) {
		mContent.setGenderUnknown(unknown);
		return this;
	}

	public ResultDetailDataBuilder setUnder10(int under10) {
		mContent.setUnder10(under10);
		return this;
	}

	public ResultDetailDataBuilder setFrom10_20(int from10_20) {
		mContent.setFrom10_20(from10_20);
		return this;
	}

	public ResultDetailDataBuilder setFrom20_30(int from20_30) {
		mContent.setFrom20_30(from20_30);
		return this;
	}

	public ResultDetailDataBuilder setFrom30_40(int from30_40) {
		mContent.setFrom30_40(from30_40);
		return this;
	}

	public ResultDetailDataBuilder setFrom40_50(int from40_50) {
		mContent.setFrom40_50(from40_50);
		return this;
	}

	public ResultDetailDataBuilder setFrom50_60(int from50_60) {
		mContent.setFrom50_60(from50_60);
		return this;
	}

	public ResultDetailDataBuilder setFrom60_70(int from60_70) {
		mContent.setFrom60_70(from60_70);
		return this;
	}

	public ResultDetailDataBuilder setOver70(int over70) {
		mContent.setOver70(over70);
		return this;
	}

	public ResultDetailDataBuilder setThumbnail(String thumbUrl) {
		mContent.setThumbnail(thumbUrl);
		return this;
	}

	public ResultDetailDataBuilder setPostDate(long postDate) {
		mContent.setPostDate(postDate);
		return this;
	}

	public ResultDetailDataBuilder setCreatedUserId(long createduserId) {
		mContent.setCreatedUserId(createduserId);
		return this;
	}

	public ResultDetailDataBuilder setCreatedUserName(String createdUserName) {
		mContent.setCreatedUserName(createdUserName);
		return this;
	}

	public ResultDetailDataBuilder setCategory(String category) {
		mContent.setCategory(category);
		return this;
	}

	public ResultDetailDataBuilder setAdditionalQuestion(
			String additionalQuestion) {
		mContent.setAdditionalQuestion(additionalQuestion);
		return this;
	}

	public ResultDetailDataBuilder setAdditionalComments(List<String> comments) {
		mContent.setAdditionalComment(comments);
		return this;
	}

	public ResultDetailData getResult() {
		return mContent;
	}

}
