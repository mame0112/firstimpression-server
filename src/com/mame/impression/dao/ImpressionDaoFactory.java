package com.mame.impression.dao;

public abstract class ImpressionDaoFactory {

	private static final int DEFAULT_MODE = 0;

	private static final int CYPHER_MODE = 1;

	private static final int MODE = DEFAULT_MODE;
	
	public abstract UserDao getUserDao();
	
	public abstract QuestionDao getQuestionDao();

	public static ImpressionDaoFactory getDaoFactory() {
		switch (MODE) {
		case DEFAULT_MODE:
			return new DefaultDaoFactory();
		case CYPHER_MODE:
			return new CipherDaoFactory();
		default:
			return null;
		}

	}

}
