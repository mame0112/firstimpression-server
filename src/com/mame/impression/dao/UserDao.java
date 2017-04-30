package com.mame.impression.dao;

import java.util.List;

import com.mame.impression.Result;
import com.mame.impression.data.UserData;

public interface UserDao {

	public boolean isUserNameExist(Result result, String userName);

	public UserData findUserDataByNameAndPassword(Result result,
			String userName, String password);

	public UserData getUserData(Result result, long userId, String userName);

	public void storeNewUserData(Result result, UserData data);

	public void updateUserData(Result result, UserData data);

	public void updateDeviceId(Result result, long userId, String userName,
			String deviceId);

	public List<String> getDeviveIds(Result result);

}
