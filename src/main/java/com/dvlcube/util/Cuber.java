package com.dvlcube.util;


/**
 * 
 * @author wonka
 * @since 14/04/2013
 */
public class Cuber {
	public static Cube $(Object object) {
		return new Cube(object);
	}

	public static CubeString $(String string) {
		return new CubeString(string);
	}
}
