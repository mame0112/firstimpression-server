package com.mame.impression.data;

import java.util.ArrayList;
import java.util.List;

import com.mame.impression.constant.Constants;

public class UserData {

	private long mUserId = Constants.NO_USER;

	private String mUserName;

	private String mPassword;

	private AGE mAge;

	private String mThumbnail;

	private GENDER mGender;

	private List<Long> mCreatedQuestionIds;

	public enum AGE {
		UNDER10, FROM10_20, FROM20_30, FROM30_40, FROM40_50, FROM50_60, FROM60_70, OVER70
	}

	public enum GENDER {
		MALE, FEMALE, OTHER, UNKNOWN
	}

	/* Package private */UserData() {

	}

	/* Package private */UserData(long userid, String userName,
			String password, AGE age, String thumbnail, GENDER gender,
			List<Long> createdQuestionIds) {
		mUserId = Constants.NO_USER;
		mUserName = userName;
		mPassword = password;
		mAge = age;
		mThumbnail = thumbnail;
		mGender = gender;
		mCreatedQuestionIds = createdQuestionIds;
	}

	// TODO This should not be public. Need to consider.
	public void setUserId(long userId) {
		mUserId = userId;
	}

	void setUserName(String userName) {
		mUserName = userName;
	}

	void setPassword(String password) {
		mPassword = password;
	}

	void setThumbnailUrl(String thumbUrl) {
		mThumbnail = thumbUrl;
	}

	void setAge(AGE age) {
		mAge = age;
	}

	void setGender(GENDER gender) {
		mGender = gender;
	}

	void setCreatedQuestionIds(List<Long> createdQuestionIds) {
		mCreatedQuestionIds = createdQuestionIds;
	}

	public long getUserId() {
		return mUserId;
	}

	public String getUserName() {
		return mUserName;
	}

	public String getPassword() {
		return mPassword;
	}

	public String getThumbnailUrl() {
		return mThumbnail;
	}

	public AGE getAge() {
		return mAge;
	}

	public GENDER getGender() {
		return mGender;
	}

	public List<Long> getCreatedQuestionIds() {
		return mCreatedQuestionIds;
	}

}
