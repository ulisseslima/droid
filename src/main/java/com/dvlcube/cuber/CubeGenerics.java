package com.dvlcube.cuber;

import java.util.List;

/**
 * 
 * @author wonka
 * @since 19/03/2013
 */
@SuppressWarnings("unchecked")
public class CubeGenerics {
	public static <T> T unchecked(Object object) {
		return (T) object;
	}

	public static <T> List<T> uncheckedList(Object object) {
		return (List<T>) object;
	}
}
