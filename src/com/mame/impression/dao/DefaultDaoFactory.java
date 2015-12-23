package com.mame.impression.dao;

public class DefaultDaoFactory extends ImpressionDaoFactory{
	
	public DefaultDaoFactory(){

	}

	@Override
	public UserDao getUserDao() {
		return new DefaultUserDao();
	}

	@Override
	public QuestionDao getQuestionDao() {
		return new DefaultQuestionDao();
	}

}
