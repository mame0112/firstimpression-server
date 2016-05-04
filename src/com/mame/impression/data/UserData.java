package com.mame.impression.data;

import java.util.ArrayList;
import java.util.List;

import com.mame.impression.constant.Constants;

public class UserData {

	private long mUserId = Constants.NO_USER;

	private String mUserName;

	private String mPassword;

	private Age mAge;

	private String mThumbnail;

	private Gender mGender;

	private List<Long> mCreatedQuestionIds;
	
	private String mDeviceId;

	public enum Age {
		UNDER10, FROM10_20, FROM20_30, FROM30_40, FROM40_50, FROM50_60, FROM60_70, OVER70, UNKNOWN
	}

	public enum Gender {
		MALE, FEMALE, OTHER, UNKNOWN
	}

	/* Package private */UserData() {

	}

	/* Package private */UserData(long userid, String userName,
			String password, Age age, String thumbnail, Gender gender,
			List<Long> createdQuestionIds, String deviceId) {
		mUserId = Constants.NO_USER;
		mUserName = userName;
		mPassword = password;
		mAge = age;
		mThumbnail = thumbnail;
		mGender = gender;
		mCreatedQuestionIds = createdQuestionIds;
		mDeviceId = deviceId;
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

	void setAge(Age age) {
		mAge = age;
	}

	void setGender(Gender gender) {
		mGender = gender;
	}

	void setCreatedQuestionIds(List<Long> createdQuestionIds) {
		mCreatedQuestionIds = createdQuestionIds;
	}
	
	void setDeviceId(String deviceId){
		mDeviceId = deviceId;
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

	public Age getAge() {
		return mAge;
	}

	public Gender getGender() {
		return mGender;
	}

	public List<Long> getCreatedQuestionIds() {
		return mCreatedQuestionIds;
	}
	
	public String getDeviceId(){
		return mDeviceId;
	}

}
