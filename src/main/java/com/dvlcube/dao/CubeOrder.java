package com.dvlcube.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.criterion.Order;

import com.dvlcube.reflection.FieldName;
import com.dvlcube.util.ArrayUtils;

/**
 * 
 * @author wonka
 * @since 22/09/2012
 */
public class CubeOrder {
	public static Set<Order> asc(final FieldName... fields) {
		return by(true, fields);
	}

	public static Set<Order> by(final boolean asc, final FieldName... fields) {
		final Set<Order> orders = new HashSet<>();
		for (final FieldName field : fields) {
			orders.add(asc ? Order.asc(field.name()) : Order.desc(field.name()));
		}
		return orders;
	}

	public static Set<Order> by(final Order... orders) {
		return ArrayUtils.asSet(orders);
	}

	public static Set<Order> desc(final FieldName... fields) {
		return by(false, fields);
	}
}
