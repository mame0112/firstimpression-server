package com.mame.impression.data;

import java.util.List;

import com.mame.impression.data.UserData.Age;
import com.mame.impression.data.UserData.Gender;

public class UserDataBuilder {

	private UserData mData;

	public UserDataBuilder() {
		mData = new UserData();
	}

	public UserDataBuilder setUserId(long userId) {
		mData.setUserId(userId);
		return this;
	}

	public UserDataBuilder setUserName(String userName) {
		mData.setUserName(userName);
		return this;
	}

	public UserDataBuilder setPassword(String password) {
		mData.setPassword(password);
		return this;
	}

	public UserDataBuilder setThumbnailUrl(String thumbUrl) {
		mData.setThumbnailUrl(thumbUrl);
		return this;
	}

	public UserDataBuilder setAge(Age age) {
		mData.setAge(age);
		return this;
	}

	public UserDataBuilder setGender(Gender gender) {
		mData.setGender(gender);
		return this;
	}

	public UserDataBuilder setCreatedQuestionIds(List<Long> createdQuestionIds) {
		mData.setCreatedQuestionIds(createdQuestionIds);
		return this;
	}

	public UserData getResult() {
		return mData;
	}

}
