package com.dvlcube.cuber;

/**
 * 
 * @author wonka
 * @since 18/04/2013
 */
public class CubeClass<C> {
	public Class<C> c;
	public boolean isAssist;

	/**
	 * @param c
	 * @author wonka
	 * @since 18/04/2013
	 */
	public CubeClass(Class<C> c) {
		if (c.getSimpleName().contains("assist")) {
			isAssist = true;
		}
		this.c = c;
	}

	/**
	 * @param interfaceClass
	 * @return
	 * @author wonka
	 * @since 18/04/2013
	 */
	public boolean doesImplement(Class<?> interfaceClass) {
		return ClassUtils.doesImplement(c, interfaceClass);
	}

	/**
	 * @param interfaceClasses
	 * @return
	 * @author wonka
	 * @since 18/04/2013
	 */
	public boolean doesImplementAll(Class<?>... interfaceClasses) {
		return ClassUtils.doesImplementAll(c, interfaceClasses);
	}

	/**
	 * @return the default naming convention for spring beans.
	 * @author wonka
	 * @since 27/10/2013
	 */
	public String getBeanName() {
		return StringUtils.decapitalize(getSimpleName());
	}

	/**
	 * @return the simple class name, decapitalized.
	 * @author wonka
	 * @since 09/11/2013
	 */
	public String getElementName() {
		String className = StringUtils.decapitalize(c.getSimpleName());
		return isAssist ? StringUtils.decapitalize(className.split("_")[0]) : className;
	}

	public String getName() {
		String className = c.getCanonicalName();
		return isAssist ? className.split("_")[0] : className;
	}

	/**
	 * @return the simple class name.
	 * @author wonka
	 * @since 27/10/2013
	 */
	public String getSimpleName() {
		String className = c.getSimpleName();
		return isAssist ? className.split("_")[0] : className;
	}
}
