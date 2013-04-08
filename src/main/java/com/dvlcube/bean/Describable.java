package com.dvlcube.bean;

import com.dvlcube.reflection.FieldName;

/**
 * 
 * @author wonka
 * @since 23/02/2013
 */
public interface Describable {
	String getDescription();

	FieldName[] getFields();

	String getName();

	void setDescription(String description);

	void setName(String name);
}
