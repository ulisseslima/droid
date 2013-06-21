package com.dvlcube.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author wonka
 * @since 05/04/2013
 */
public class ClassUtils {
	/**
	 * @param class1
	 * @param interfaceClass
	 * @return true if class1 implements interfaceClass.
	 * @author wonka
	 * @since 05/04/2013
	 */
	public static boolean doesImplement(Class<?> class1, Class<?> interfaceClass) {
		Class<?>[] interfaces = class1.getInterfaces();
		for (Class<?> class2 : interfaces) {
			if (class2 == interfaceClass) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param class1
	 * @param interfaceClasses
	 * @return true if class1 implements all interfaces in the list.
	 * @author wonka
	 * @since 05/04/2013
	 */
	public static boolean doesImplementAll(Class<?> class1, Class<?>... interfaceClasses) {
		Set<Class<?>> set = new HashSet<>(Arrays.asList(interfaceClasses));
		for (Class<?> class2 : interfaceClasses) {
			if (doesImplement(class1, class2)) {
				set.remove(class2);
			}
		}
		return set.isEmpty();
	}
}
