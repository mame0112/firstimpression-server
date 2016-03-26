package com.mame.impression.data;

public class GcmPushData {

	private long mQuestionId;
	
//	private String mQuestionTitle;
	
	public GcmPushData(long questionId){
		mQuestionId = questionId;
	}
	
	public long getQuestionId(){
		return mQuestionId;
	}

}
