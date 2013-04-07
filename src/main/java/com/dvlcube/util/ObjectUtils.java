package com.dvlcube.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * @author wonka
 * @since 06/04/2013
 */
public class ObjectUtils {
	/**
	 * Adapted from
	 * http://stackoverflow.com/questions/835416/how-to-copy-properties-from-one-java-bean-to-another
	 * 
	 * @param from
	 *            an object
	 * @param to
	 *            to another object
	 * @param ignoreNull
	 *            whether null values should be copied.
	 * @author wonka
	 * @since 06/04/2013
	 */
	public static void copyProperties(Object from, Object to, boolean ignoreNull) {
		try {
			Class<? extends Object> fromClass = from.getClass();
			Class<? extends Object> toClass = to.getClass();

			BeanInfo fromBean = Introspector.getBeanInfo(fromClass);
			BeanInfo toBean = Introspector.getBeanInfo(toClass);

			PropertyDescriptor[] toPd = toBean.getPropertyDescriptors();
			List<PropertyDescriptor> fromPd = Arrays.asList(fromBean.getPropertyDescriptors());

			for (PropertyDescriptor property : toPd) {
				PropertyDescriptor descriptor = fromPd.get(fromPd.indexOf(property));
				String displayName = descriptor.getDisplayName();
				if (displayName.equals(property.getDisplayName()) && !displayName.equals("class")) {
					if (property.getWriteMethod() != null) {
						Object value = get(property, from);
						if (value == null && ignoreNull) {
						} else {
							String packageName = value.getClass().getPackage().getName();
							if (!packageName.startsWith("java")) {
								copyProperties(get(property, from), get(property, to), ignoreNull);
							} else {
								property.getWriteMethod().invoke(to, get(descriptor, from));
							}
						}
					}
				}

			}
		} catch (Exception e) {
			throw new RuntimeException("Could not update properties", e);
		}
	}

	/**
	 * @param property
	 * @param from
	 * @return
	 * @author wonka
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @since 06/04/2013
	 */
	private static Object get(PropertyDescriptor property, Object from) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		return property.getReadMethod().invoke(from);
	}

	/**
	 * @param from
	 *            an object
	 * @param to
	 *            another object
	 * @author wonka
	 * @since 06/04/2013
	 */
	public static void updateProperties(Object from, Object to) {
		copyProperties(from, to, true);
	}
}
