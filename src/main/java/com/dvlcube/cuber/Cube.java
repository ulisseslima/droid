package com.dvlcube.cuber;

/**
 * 
 * @author wonka
 * @since 14/04/2013
 */
public class Cube {
	public Object object;

	/**
	 * @param object2
	 * @author wonka
	 * @since 14/04/2013
	 */
	public Cube(Object object) {
		this.object = object;
	}

	/**
	 * Merges this object's attributes with another Object, ignoring null values.
	 * 
	 * @param on
	 *            This object will have its properties updated, according to this object's values.
	 * @author wonka
	 * @since 14/04/2013
	 */
	public boolean merge(Object on) {
		if (on instanceof Cube) {
			return ObjectUtils.updateProperties(object, ((Cube) on).object);
		} else {
			return ObjectUtils.updateProperties(object, on);
		}
	}

	/**
	 * @return
	 * @author wonka
	 * @since 14/04/2013
	 */
	public String stringify() {
		return StringUtils.stringify(object);
	}
}
