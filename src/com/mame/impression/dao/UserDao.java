package com.mame.impression.dao;

import com.mame.impression.Result;
import com.mame.impression.data.UserData;

public interface UserDao {

	public UserData findUserDataByNameAndPassword(Result result,
			String userName, String password);

	public void storeNewUserData(Result result, UserData data);

	public void updateUserData(Result result, UserData data);

}
