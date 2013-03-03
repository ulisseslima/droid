package com.dvlcube.util;

/**
 * 
 * @author wonka
 * @since 17/09/2012
 */
public interface I18n {
	public enum User {
		SUBTITLE("label.motivation.subtitle");
		private final String key;

		User(final String key) {
			this.key = key;
		}

		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}
	}

	public enum Response {
		FAIL("label.fail"), SUCCESS("label.success");
		private final String key;

		Response(final String key) {
			this.key = key;
		}

		/**
		 * @return the key
		 */
		public String key() {
			return key;
		}
	}
}
