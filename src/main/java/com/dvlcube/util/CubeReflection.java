package com.dvlcube.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * @author wonka
 * @since 16/09/2012
 */
public class CubeReflection {

	/**
	 * @return
	 * @author wonka
	 * @since 16/09/2012
	 */
	public static <T> Type[] getGenericTypes(final Class<T> jClass) {
		final ParameterizedType genericSuperclass = (ParameterizedType) jClass.getGenericSuperclass();
		return genericSuperclass.getActualTypeArguments();
	}
}
