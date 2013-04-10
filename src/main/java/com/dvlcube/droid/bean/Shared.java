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
		participants("participant") {
			@Override
			public String id() {
				throw new UnsupportedOperationException("can't get id on a collection");
			}
		};
		private String alias;

		Field(String alias) {
			this.alias = alias;
		}

		@Override
		public String getAlias() {
			return alias;
		}

		@Override
		public String id() {
			return name() + ".id";
		}

		/**
		 * @return
		 * @author wonka
		 * @since 05/04/2013
		 */
		@Override
		public String join() {
			return alias + ".id";
		}
	}

	Set<User> getParticipants();

	void setParticipants(Set<User> participants);
}
