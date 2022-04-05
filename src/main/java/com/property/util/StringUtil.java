package com.property.util;

import java.util.UUID;

public class StringUtil {
	public static String getUniqueString() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
