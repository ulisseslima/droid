package com.dvlcube.service;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.dvlcube.bean.Describable;
import com.dvlcube.bean.Identifiable;
import com.dvlcube.bean.Trackable;
import com.dvlcube.bean.Validatable;

/**
 * 
 * @author wonka
 * @since 12/03/2013
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public interface BasicInfo extends Identifiable, Trackable, Describable, Validatable {

}
