package com.dvlcube.etc;

import java.util.ArrayList;

class JsonObject {

}

/**
 * 
 * @author wonka
 * @since 11/03/2013
 */
public class Main {
	/**
	 * @param args
	 * @author wonka
	 * @since 11/03/2013
	 */
	public static void main(final String[] args) {
		final Main main = new Main();
		System.out.println(main.q);
	}

	private int q;

	{
		q = 2;
	}

	{
		q = 1;
	}

	/**
	 * @param key
	 * @return
	 * @author wonka
	 * @since 15/03/2013
	 */
	private Object get(final String key) {
		return null;
	}

	public JsonObject[] getObjectArray(final String key) {
		final ArrayList<?> original = (ArrayList<?>) get(key);
		if (original != null) {
			final JsonObject[] arr = new JsonObject[original.size()];
			final JsonObject[] array = ((ArrayList<?>) get(key)).toArray(arr);
			return array;
		} else {
			return null;
		}
	}

}