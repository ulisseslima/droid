package com.dvlcube.droid.bean;

import java.util.Set;

import com.dvlcube.bean.QueryFieldName;

/**
 * Indicates that this object can be shared with other people.
 * 
 * @author wonka
 * @since 05/04/2013
 */
public interface Shared {
	public enum Field implements QueryFieldName {
		participants("participant");
		private String alias;

		Field(String alias) {
			this.alias = alias;
		}

		@Override
		public String getAlias() {
			return alias;
		}

		/**
		 * @return
		 * @author wonka
		 * @since 05/04/2013
		 */
		public String join() {
			return alias + "." + "name";
		}
	}

	Set<User> getParticipants();

	void setParticipants(Set<User> participants);
}
