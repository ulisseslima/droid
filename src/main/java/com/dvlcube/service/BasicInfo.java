package com.dvlcube.service;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.dvlcube.bean.Describable;
import com.dvlcube.bean.Identifiable;
import com.dvlcube.bean.Trackable;
import com.dvlcube.bean.Validatable;
import com.dvlcube.reflection.FieldName;

/**
 * 
 * @author wonka
 * @since 12/03/2013
 */
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, ignoreUnknown = true)
public interface BasicInfo extends Identifiable, Trackable, Describable, Validatable {
	public enum Field implements FieldName {
		dateModified, description, id, label, name
	}
}
