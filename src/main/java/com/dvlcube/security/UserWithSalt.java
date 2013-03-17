package com.dvlcube.security;

import com.dvlcube.droid.bean.User;

/**
 * 
 * @author wonka
 * @since 17/03/2013
 */
public class UserWithSalt extends User {

	private static final long serialVersionUID = 7394914903484537577L;

	private String resumo;

	private String salt;

	public String getResumo() {
		return resumo;
	}

	public String getSalt() {
		return salt;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
