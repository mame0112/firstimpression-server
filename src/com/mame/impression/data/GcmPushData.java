package com.mame.impression.data;

public class GcmPushData {

	private long mQuestionId;
	
	private String mQuestionTitle;
	
	public GcmPushData(long questionId, String title){
		mQuestionId = questionId;
		mQuestionTitle = title;
	}
	
	public long getQuestionId(){
		return mQuestionId;
	}
	
	public String getQuestionTitle(){
		return mQuestionTitle;
	}

}
