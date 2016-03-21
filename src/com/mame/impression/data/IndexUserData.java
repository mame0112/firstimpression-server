package com.mame.impression.data;

import com.mame.impression.constant.Constants;

/**
 * To be used to query user data (user id and user name is mimum data set)
 * 
 */
public class IndexUserData {

	private long mUserId = Constants.NO_USER;

	private String mUserName;

	public IndexUserData(long userId, String userName) {
		mUserId = userId;
		mUserName = userName;
	}

	public long getUserId() {
		return mUserId;
	}

	public String getUserName() {
		return mUserName;
	}

}
