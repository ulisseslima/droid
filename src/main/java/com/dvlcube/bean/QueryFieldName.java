package com.dvlcube.bean;

import com.dvlcube.reflection.FieldName;

/**
 * 
 * @author wonka
 * @since 05/04/2013
 */
public interface QueryFieldName extends FieldName {
	String getAlias();

	String id();

	String join();
}
