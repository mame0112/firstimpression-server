package com.mame.impression.dao;

import java.util.ConcurrentModificationException;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.mame.impression.Result;
import com.mame.impression.Result.ActionResult;
import com.mame.impression.util.LogUtil;

public class DatastoreHandler {

	private static final String TAG = DatastoreHandler.class.getSimpleName();

	private final static DatastoreService mDS = DatastoreServiceFactory
			.getDatastoreService();

	private final static int RETRY_COUNT = 2;

	/**
	 * Try to put Entity to Datastore. Retry is 3 times.
	 * 
	 * @param result
	 * @param e
	 * @return put result. True if successfully put data. Otherwise false.
	 */
	public static boolean put(Result result, Entity e) {

		if (result == null) {
			throw new IllegalArgumentException(TAG + " Result cannot be null");
		}

		if (e == null) {
			throw new IllegalArgumentException(TAG + " Entity cannot be null");
		}

		// Retry count
		int retry = 0;

		while (retry <= RETRY_COUNT) {
			try {
				mDS.put(e);
				return true;
			} catch (IllegalArgumentException e1) {
				// If the specified entity was incomplete.
				LogUtil.d(TAG, "IllegalArgumentException retry: " + retry);
				result.setActionResult(ActionResult.FAIL);
				result.setErrorMessage("IllegalArgumentException"
						+ e1.getMessage());
				break;
			} catch (ConcurrentModificationException e2) {
				// If the entity group to which the entity belongs was modified
				// concurrently.
				LogUtil.d(TAG, "ConcurrentModificationException retry: "
						+ retry);
				if (retry <= 2) {
					retry = retry + 1;
				} else {
					result.setActionResult(ActionResult.FAIL);
					result.setErrorMessage("ConcurrentModificationException"
							+ e2.getMessage());
					break;
				}
			} catch (DatastoreFailureException e3) {
				// If any other datastore error occurs.
				LogUtil.d(TAG, "DatastoreFailureException retry: " + retry);
				if (retry <= 2) {
					retry = retry + 1;
				} else {
					result.setActionResult(ActionResult.FAIL);
					result.setErrorMessage("DatastoreFailureException"
							+ e3.getMessage());
					break;
				}
			}
		}

		return false;

	}

	/**
	 * Try to get Entity by given key.
	 * 
	 * @param result
	 * @param key
	 * @return Entity for given key. Otherwise null if entity is not found.
	 */
	public static Entity get(Result result, Key key) {

		if (result == null) {
			throw new IllegalArgumentException(TAG + " result cannot be null");
		}

		if (key == null) {
			throw new IllegalArgumentException(TAG + " key cannot be null");
		}

		// Retry count
		int retry = 0;

		while (retry <= RETRY_COUNT) {
			try {
				return mDS.get(key);
			} catch (EntityNotFoundException e1) {
				// If the specified entity could not be found.
				LogUtil.d(TAG, "EntityNotFoundException: " + e1.getMessage());
				result.setActionResult(ActionResult.FAIL);
				result.setErrorMessage("EntityNotFoundException"
						+ e1.getMessage());
				break;
			} catch (IllegalArgumentException e2) {
				// If the specified key is invalid.
				result.setActionResult(ActionResult.FAIL);
				result.setErrorMessage("IllegalArgumentException"
						+ e2.getMessage());
				break;
			} catch (DatastoreFailureException e3) {
				// If any other datastore error occurs.
				if (retry <= 2) {
					retry = retry + 1;
				} else {
					result.setActionResult(ActionResult.FAIL);
					result.setErrorMessage("DatastoreFailureException"
							+ e3.getMessage());
					break;
				}
			}
		}

		return null;
	}
}
