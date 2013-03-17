package com.dvlcube.droid.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.dvlcube.reflection.FieldName;

/**
 * 
 * @author wonka
 * @since 16/03/2013
 */
@Entity
public class Role implements Serializable {
	public enum Field implements FieldName {
		authority, id, user
	}

	private static final long serialVersionUID = 5642280917182146894L;
	private String authority;
	@Id
	@GeneratedValue
	private Long id;
	private User user;

	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param authority
	 *            the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Role [authority=" + authority + ", id=" + id + ", user=" + user + "]";
	}
}
