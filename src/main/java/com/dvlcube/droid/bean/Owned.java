package com.dvlcube.droid.bean;

import com.dvlcube.bean.QueryFieldName;

/**
 * 
 * @author wonka
 * @since 29/03/2013
 */
public interface Owned {
	public enum Field implements QueryFieldName {
		owner;

		@Override
		public String getAlias() {
			return new String(new char[] { name().charAt(0) });
		};

		@Override
		public String join() {
			return getAlias() + ".name";
		}
	}

	User getOwner();

	void setOwner(User owner);
}
