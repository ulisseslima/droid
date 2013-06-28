package com.dvlcube.cuber;

/**
 * 
 * @author wonka
 * @since 18/04/2013
 */
public class CubeClass<C> {
	public Class<C> c;

	/**
	 * @param c
	 * @author wonka
	 * @since 18/04/2013
	 */
	public CubeClass(Class<C> c) {
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
}
