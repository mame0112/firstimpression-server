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
	
	public ResultDetailDataBuilder setChoiceAItem(ResultDetailDataItem item){
		mContent.setChoiceAItem(item);
		return this;
	}
	
	public ResultDetailDataBuilder setChoiceBItem(ResultDetailDataItem item){
		mContent.setChoiceBItem(item);
		return this;
	}

	public ResultDetailData getResult() {
		return mContent;
	}

}
