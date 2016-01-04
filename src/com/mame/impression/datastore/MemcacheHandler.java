package com.mame.impression.datastore;

import com.google.appengine.api.memcache.ConsistentErrorHandler;
import com.google.appengine.api.memcache.InvalidValueException;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceException;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.mame.impression.constant.Constants;
import com.mame.impression.util.LogUtil;

public class MemcacheHandler<T> {

	private static final String TAG = Constants.TAG
			+ MemcacheHandler.class.getSimpleName();

	private static MemcacheService mCache = MemcacheServiceFactory
			.getMemcacheService();

	private final static String SEPARETER = "/";

	static {
		mCache.setErrorHandler(new MemcacheErrorHander());
	}

	public T get(Object prefix, Object param1) {
		LogUtil.d(TAG, "get");
		if (Constants.iS_MEMCACHE_ENABLED) {
			try {
				@SuppressWarnings("unchecked")
				T value = (T) mCache.get(prefix + SEPARETER + param1);
				return value;
			} catch (IllegalArgumentException e1) {
				LogUtil.w(TAG, "IllegalArgumentException: " + e1.getMessage());
			} catch (InvalidValueException e2) {
				LogUtil.w(TAG, "InvalidValueException: " + e2.getMessage());
			}

		}

		return null;

	}

	public void put(Object prefix, Object param1, T value) {
		LogUtil.d(TAG, "put");
		if (Constants.iS_MEMCACHE_ENABLED) {
			try {
				mCache.put(prefix + SEPARETER + param1, value);
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
