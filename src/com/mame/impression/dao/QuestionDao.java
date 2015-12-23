package com.mame.impression.dao;

import java.util.List;

import com.mame.impression.data.QuestionData;

public interface QuestionDao {

	/**
	 * Save a question data
	 * 
	 * @param data
	 * @return
	 */
	public QuestionData storeNewQuestionData(QuestionData data);

	/**
	 * Get latest questions
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public List<QuestionData> getLatestQuestionData(int start, int end);

	/**
	 * Update question data if it updated (e.g. Get additional comment, get
	 * feedback )
	 * 
	 * @param data
	 * @return
	 */
	public void updateQuestionData(QuestionData data);

	/**
	 * Get question data based on questionId
	 * 
	 * @param questionId
	 * @return
	 */
	public QuestionData findQuestionById(long questionId);

}
