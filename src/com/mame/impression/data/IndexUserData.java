package com.mame.impression.data;

import com.mame.impression.constant.Constants;

/**
 * To be used to query user data (user id and user name is mimum data set)
 * 
 */
public class IndexUserData {

	private long mUserId = Constants.NO_USER;

	private String mUserName;
	
	private String mDescription;

	public IndexUserData(long userId, String userName, String description) {
		mUserId = userId;
		mUserName = userName;
		mDescription = description;
	}

	public long getUserId() {
		return mUserId;
	}

	public String getUserName() {
		return mUserName;
	}
	
	public String getDescription(){
		return mDescription;
	}

}
