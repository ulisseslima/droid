package com.dvlcube.droid.service.rr;

/**
 * A request for the creation of a new User.
 * 
 * @author wonka
 * @since 13/03/2013
 */
public class NewUserRequest {
	private String email;
	private String password;
	private String passwordConfirmation;
	private String username;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the passwordConfirmation
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param passwordConfirmation
	 *            the passwordConfirmation to set
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "NewUserRequest [username=" + username + ", email=" + email + ", password=" + password
				+ ", passwordConfirmation=" + passwordConfirmation + "]";
	}
}
