package com.dvlcube.cuber;

import static com.dvlcube.cuber.Cuber.$;

import java.io.File;

/**
 * 
 * @author wonka
 * @since 31/03/2013
 */
public class Debug {
	private static final boolean ENABLED;
	static {
		if (new File("H:/tmp/debug-enabled").exists()) {
			ENABLED = true;
		} else {
			ENABLED = false;
		}
		log("dvlcube debug is enabled");
	}

	public static void log(String msg, Object... args) {
		if (ENABLED) {
			System.out.printf(msg + "\n", args);
		}
	}

	public static void logIf(Boolean condition, String msg, Object... args) {
		if (condition != null && condition) {
			log(msg, args);
		}
	}

	/**
	 * @param string
	 * @param query
	 * @author wonka
	 * @since 01/04/2013
	 */
	public static void logObj(String msg, Object obj) {
		log(msg + "%s", $(obj).stringify());
	}
}
