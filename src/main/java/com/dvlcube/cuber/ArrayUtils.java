package com.dvlcube.cuber;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Source: http://stackoverflow.com/questions/80476/how-to-concatenate-two-arrays-in-java
 * 
 * @author wonka
 * @since 05/04/2013
 */
public class ArrayUtils {
	public static <T> Set<T> asSet(T[] a) {
		HashSet<T> hashSet = new HashSet<>(Arrays.asList(a));
		return hashSet;
	}

	/**
	 * @param conditions
	 * @param authConditions
	 * @return
	 * @author wonka
	 * @since 05/04/2013
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] concat(T[] array, Collection<T> collection) {
		if (collection == null || collection.isEmpty()) {
			return array;
		}
		return concat(array, (T[]) collection.toArray());
	}

	/**
	 * @param first
	 * @param rest
	 * @return
	 * @author wonka
	 * @since 05/04/2013
	 */
	@SafeVarargs
	public static <T> T[] concat(T[] first, T[]... rest) {
		if (rest == null || rest.length == 0) {
			return first;
		}
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * @param array
	 * @param collection
	 * @return
	 * @author wonka
	 * @since 05/04/2013
	 */
	public static <T> Set<T> concatIntoSet(T[] array, Collection<T> collection) {
		@SuppressWarnings("unchecked")
		T[] concat = concat(array, (T[]) collection.toArray());
		return asSet(concat);
	}
}
