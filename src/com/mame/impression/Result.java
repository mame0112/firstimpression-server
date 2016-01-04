package com.mame.impression;

/**
 * Result class to be used to return some values (e.g. Success, fail, error
 * messages)
 * 
 * @author kosukeEndo
 * 
 */
public class Result {

	/**
	 * Result overview.
	 * 
	 * @author kosukeEndo Success: Operation is finished successfully Fail:
	 *         Operation failed. Need to see error message. In progress:
	 *         Operation is on-going. This message shall be used when we support
	 *         for async operation.
	 * 
	 */
	public enum ActionResult {
		SUCCESS, FAIL, IN_PROGRESS
	}

	/**
	 * Error type to identify errors in back-end. (Based on this error type.
	 * Action classes can consider if it needs to return error message to client
	 * side.
	 * 
	 * @author kosukeEndo
	 * 
	 */
	public enum ErrorType {
		NONE, FAILED_TO_WRITE_TO_DB, ILLEGAL_JSON_FORMAT
	}

	private ErrorType mErrorType = ErrorType.NONE;

	public void setErrorType(ErrorType type) {
		if (type == null) {
			throw new IllegalArgumentException("ErrorType cannot be null");
		}
		mErrorType = type;
	}

	public ErrorType getErrorType() {
		return mErrorType;
	}

	/**
	 * Return parameter. This shall be used if necessary (necessary informaton
	 * shall be stored on this object)
	 */

	/**
	 * Error message. This shall be used when some error occurs.
	 */
	public String mErrorMessage;

	private ActionResult mResult = ActionResult.SUCCESS;

	public void setActionResult(ActionResult result) {
		mResult = result;
	}

	public ActionResult getResult() {
		return mResult;
	}

	public void setErrorMessage(String errorMessage) {
		if (errorMessage == null) {
			throw new IllegalArgumentException("Error message cannot be null");
		}
		// In case Error message is not stored case, we accept to put new
		// message (We should keep original error message)
		if (mErrorMessage == null) {
			mErrorMessage = errorMessage;
		}
	}

	public String getErrorMessage() {
		return mErrorMessage;
	}

	/**
	 * Util method to check if operation is success
	 * 
	 * @return
	 */
	public boolean isSuccess() {
		if (mResult.equals(ActionResult.SUCCESS)) {
			return true;
		}
		return false;
	}

	public boolean isFailed() {
		if (mResult.equals(ActionResult.FAIL)) {
			return true;
		}
		return false;
	}

}
