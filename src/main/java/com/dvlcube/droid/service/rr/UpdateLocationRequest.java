package com.dvlcube.droid.service.rr;

/**
 * A request for the creation of a new User.
 * 
 * @author wonka
 * @since 13/03/2013
 */
public class UpdateLocationRequest extends AsyncRequest {
	private boolean active;
	private String element;
	private Long userId;

	/**
	 * @return the element
	 */
	public String getElement() {
		return element;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	public void setElement(String element) {
		this.element = element;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UpdateLocationRequest [active=" + active + ", element=" + element + ", userId=" + userId + "]";
	}
}
