package com.mame.impression.util;

import java.util.Date;

public class TimeUtil {

	public long getCurrentTime() {
		Date date = new Date();
		return date.getTime();
	}

}
