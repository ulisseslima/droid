package com.dvlcube.bean;

import java.io.Serializable;

/**
 * 
 * @author wonka
 * @since 23/02/2013
 */
public interface Identifiable extends Serializable {

	Serializable getId();

	String getLabel();

	void setId(Long id);
}
