package com.energy.consumption.util;

import java.time.LocalDateTime;

public class CurrentDataTime {

	private CurrentDataTime() {
		throw new IllegalStateException("Utility class");
	}

	public static LocalDateTime getCurrentTime() {
		return LocalDateTime.now();
	}
}
