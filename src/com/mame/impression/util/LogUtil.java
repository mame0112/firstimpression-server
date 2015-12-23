package com.mame.impression.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mame.impression.constant.Constants;

public class LogUtil {
	
	public final static Logger log = Logger
			.getLogger(LogUtil.class.getName());
	
	public static void d (String tag, String str){
		if(Constants.IS_DEBUG){
			if (str != null) {
				log.log(Level.INFO, tag + ": " + str);
			}
		}
	}
	
	public static void w (String tag, String str){
		if(Constants.IS_DEBUG){
			if (str != null) {
				log.log(Level.WARNING, tag + ": " + str);
			}
		}
	}
}
