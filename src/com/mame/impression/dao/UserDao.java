package com.mame.impression.dao;

import com.mame.impression.data.UserData;

public interface UserDao {
	
	public UserData findUserDataByName(String userName);
	
	public void storeNewUserData(UserData data);
	
	public UserData updateUserData(UserData data);
	
}
