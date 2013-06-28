package com.dvlcube.cuber;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wonka
 * @since 28/02/2013
 */
public class StringUtils {
	/**
	 * @param string
	 *            the string to escape.
	 * @return a string formatted for use in web pages.
	 * @author wonka
	 * @since 15/03/2013
	 */
	public static String escapeHTML(final String string) {
		return string.replace("\"", "&quot;");
	}

	/**
	 * @param list
	 *            the list to calculate.
	 * @return a string representation of a list's size. Returning 0 for null.
	 * @author wonka
	 * @since 29/03/2013
	 */
	public static String getSize(List<?> list) {
		return (String) (list != null ? list.size() : "0");
	}

	/**
	 * @param string
	 *            the string to check.
	 * @return whether a string contains no printable characters.
	 * @author wonka
	 * @since 25/04/2013
	 */
	public static boolean isBlank(final String string) {
		if (string == null) {
			return true;
		}
		return string.trim().isEmpty();
	}

	/**
	 * @param array
	 *            the array to randomize.
	 * @return an array that is a result of mixing the index values of the first array.
	 * @author wonka
	 * @since 23/04/2013
	 */
	private static char[] randomizeIndex(char[] array) {
		char[] randomArray = new char[array.length];
		List<Integer> set = new ArrayList<>();
		while (set.size() < array.length) {
			int index = (int) (Math.random() * array.length);
			if (!set.contains(index)) {
				set.add(index);
			}
		}
		Queue<Integer> queue = new LinkedList<>(set);
		int i = 0;
		while (!queue.isEmpty()) {
			randomArray[i++] = array[queue.poll()];
		}
		return randomArray;
	}

	/**
	 * Will alter the order of the letters in the String representation of an Object.
	 * 
	 * @param object
	 *            the object.
	 * @return a scrambled string representation of the object.
	 * @author wonka
	 * @since 23/04/2013
	 */
	public static String scramble(Object object) {
		String string = object.toString();
		char[] array = string.toCharArray();
		char[] randomArray = randomizeIndex(array);
		return new String(randomArray);
	}

	/**
	 * @param object
	 *            object to stringify.
	 * @return A stringified representation of the Object.
	 * @author wonka
	 * @since 29/03/2013
	 */
	public static String stringify(Object object) {
		try {
			StringBuilder builder = new StringBuilder();
			Field[] fields = object.getClass().getDeclaredFields();
			builder.append(object.getClass().getSimpleName() + " {");
			for (Field field : fields) {
				field.setAccessible(true);
				builder.append("\n\t" + field.getName() + ": ");
				try {
					if (field.getType().isArray()) {
						builder.append(Arrays.toString((Object[]) field.get(object)));
					} else {
						builder.append(field.get(object));
					}
				} catch (Exception e) {
					builder.append("n/a");
				}
			}
			builder.append("\n}");
			return builder.toString();
		} catch (Exception e) {
			return "Couldn't stringify: " + e.getMessage();
		}
	}
}
