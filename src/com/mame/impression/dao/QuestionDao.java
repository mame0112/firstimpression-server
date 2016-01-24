package com.mame.impression.dao;

import java.util.List;

import com.mame.impression.Result;
import com.mame.impression.data.QuestionData;
import com.mame.impression.data.ResultDetailData;
import com.mame.impression.data.ResultListData;
import com.mame.impression.data.UserData.Age;
import com.mame.impression.data.UserData.Gender;

public interface QuestionDao {

	/**
	 * Get latest question list.
	 * 
	 * @param result
	 * @return
	 */
	public List<QuestionData> getLatestQuestionData(Result result);

	/**
	 * Save a question data
	 * 
	 * @param data
	 */
	public void storeNewQuestionData(Result result, QuestionData data);

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
			int choice, Gender gender, Age age);

	/**
	 * Get Question result list for given user id
	 * 
	 * @param result
	 * @param userId
	 * @return
	 */
	public List<ResultListData> getQuestionResultList(Result result, long userId);

	/**
	 * Get detail Question result for given user id and given question id
	 * 
	 * @param result
	 * @param userId
	 * @param questionId
	 * @return
	 */
	public ResultDetailData getQuestionResultDetail(Result result,
			long questionId);

}
