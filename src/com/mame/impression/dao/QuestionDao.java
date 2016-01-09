package com.mame.impression.dao;

import java.util.List;

import com.mame.impression.Result;
import com.mame.impression.data.QuestionData;

public interface QuestionDao {

	/**
	 * Save a question data
	 * 
	 * @param data
	 */
	public void storeNewQuestionData(Result result, QuestionData data);

	/**
	 * Get latest questions
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public List<QuestionData> getLatestQuestionData(Result result, int start,
			int end);

	/**
	 * Update question data if it updated (e.g. Get additional comment, get
	 * feedback )
	 * 
	 * @param data
	 * @return
	 */
	public void updateQuestionData(Result result, QuestionData data);

	/**
	 * Get question data based on questionId
	 * 
	 * @param questionId
	 * @return
	 */
	public QuestionData findQuestionById(Result result, long questionId);

	/**
	 * Get question data based on questionId
	 * 
	 * @param questionId
	 * @return
	 */
	public void saveQuestionResponseData(Result result, long questionId,
			int choice);

}
