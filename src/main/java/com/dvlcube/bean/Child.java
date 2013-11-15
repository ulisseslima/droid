package com.dvlcube.bean;

import com.dvlcube.service.BasicInfo;

/**
 * @param <P>
 *            Parent type.
 * @author wonka
 * @since 29/03/2013
 */
public interface Child<P extends BasicInfo> {
	public enum Field implements QueryFieldName {
		parent_participants("parentParticipant") {
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

	/**
	 * @return
	 * @author wonka
	 * @since 29/03/2013
	 */
	P getParent();

	/**
	 * @param parent
	 * @author wonka
	 * @since 29/03/2013
	 */
	void setParent(P parent);
}
