package com.dvlcube.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.criterion.Order;

import com.dvlcube.reflection.FieldName;

/**
 * 
 * @author wonka
 * @since 22/09/2012
 */
public class CubeOrder {
	public static List<Order> asc(final FieldName... fields) {
		return by(true, fields);
	}

	public static List<Order> by(final boolean asc, final FieldName... fields) {
		final List<Order> orders = new ArrayList<>();
		for (final FieldName field : fields) {
			orders.add(asc ? Order.asc(field.name()) : Order.desc(field.name()));
		}
		return orders;
	}

	public static List<Order> by(final Order... orders) {
		return Arrays.asList(orders);
	}

	public static List<Order> desc(final FieldName... fields) {
		return by(false, fields);
	}
}
