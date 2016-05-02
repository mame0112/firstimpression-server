package com.mame.impression.datastore;

import com.google.appengine.api.memcache.ConsistentErrorHandler;
import com.google.appengine.api.memcache.InvalidValueException;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceException;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.mame.impression.constant.Constants;
import com.mame.impression.util.LogUtil;

public class MemcacheHandler {

	private static final String TAG = Constants.TAG
			+ MemcacheHandler.class.getSimpleName();

	private static MemcacheService mCache = MemcacheServiceFactory
			.getMemcacheService();

	private final static String SEPARETER = "/";

	static {
		mCache.setErrorHandler(new MemcacheErrorHander());
	}

	/**
	 * get Memcache. prefix is mandatory to store memcache. If needed, client
	 * can use param1.
	 * 
	 * @param prefix
	 * @param param1
	 * @return
	 */
	public static synchronized Object get(String prefix, String param1) {
		LogUtil.d(TAG, "get");
		if (Constants.IS_MEMCACHE_ENABLED) {

			if (prefix == null) {
				throw new IllegalArgumentException("prefix cannot be null");
			}

			try {
				// If client supplies param1, we use as part of key.
				if (param1 != null) {
					return (Object) mCache.get(prefix + SEPARETER + param1);
				} else {
					// Otherwise, we only use prefix as key.
					return mCache.get(prefix);
				}
			} catch (IllegalArgumentException e1) {
				LogUtil.w(TAG, "IllegalArgumentException: " + e1.getMessage());
			} catch (InvalidValueException e2) {
				LogUtil.w(TAG, "InvalidValueException: " + e2.getMessage());
			}

		}

		return null;

	}

	/**
	 * put Memcache. prefix is mandatory to store memcache. If needed, client
	 * can use as optional value
	 * 
	 * @param prefix
	 * @param param1
	 * @param value
	 */
	public static synchronized void put(String prefix, String param1,
			Object value) {
		LogUtil.d(TAG, "put");
		if (Constants.IS_MEMCACHE_ENABLED) {

			if (prefix == null) {
				throw new IllegalArgumentException("prefix cannot be null");
			}

			if (value == null) {
				throw new IllegalArgumentException("value cannot be null");
			}

			try {
				// If client supplies param1, we use as part of key.
				if (param1 != null) {
					mCache.put(prefix + SEPARETER + param1, value);
				} else {
					// Otherwise, we only use prefix as key.
					mCache.put(prefix, value);
				}

			} catch (IllegalArgumentException e1) {
				LogUtil.w(TAG, "IllegalArgumentException: " + e1.getMessage());
			} catch (MemcacheServiceException e2) {
				// We should not come in here because we have configured
				// ConsistentErrorHandler
				LogUtil.w(TAG, "MemcacheServiceException: " + e2.getMessage());
			}
		}
	}

	/**
	 * Delete memcache. Prefix is mandatory to delete memcache.
	 * 
	 * @param prefix
	 * @param param1
	 */
	public static synchronized void delete(String prefix, String param1) {
		LogUtil.d(TAG, "remove");

		if (Constants.IS_MEMCACHE_ENABLED) {
			if (prefix == null) {
				throw new IllegalArgumentException("prefix cannot be null");
			}

			try {
				// If client supplies param1, we use as part of key.
				if (param1 != null) {
					mCache.delete(prefix + SEPARETER + param1);
				} else {
					// Otherwise, we only use prefix as key.
					mCache.delete(prefix);
				}

			} catch (IllegalArgumentException e1) {
				LogUtil.w(TAG, "IllegalArgumentException: " + e1.getMessage());
			} catch (MemcacheServiceException e2) {
				// We should not come in here because we have configured
				// ConsistentErrorHandler
				LogUtil.w(TAG, "MemcacheServiceException: " + e2.getMessage());
			}

		}

	}

	public static class MemcacheErrorHander implements ConsistentErrorHandler {

		private static final String TAG = Constants.TAG
				+ MemcacheErrorHander.class.getSimpleName();

		public void handleDeserializationError(InvalidValueException ivx) {
			LogUtil.d(TAG, "handleDeserializationError: " + ivx.getMessage());
		}

		public void handleServiceError(MemcacheServiceException ex) {
			LogUtil.d(TAG, "handleServiceError: " + ex.getMessage());

		}

	}

}
