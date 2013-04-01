package com.dvlcube.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Response format.
 * 
 * @author wonka
 * @since 10/09/2012
 */
public class Response<T> {
	private T content;
	private List<T> contents = new ArrayList<>();
	private String message;
	private int size;
	private final boolean success;

	/**
	 * @param success
	 *            Indicator of a successful service.
	 * @author wonka
	 * @since 15/09/2012
	 */
	public Response(final boolean success) {
		this(success, (T) null);
	}

	/**
	 * @param success
	 * @param contents
	 * @author wonka
	 * @since 15/09/2012
	 */
	public Response(final boolean success, final List<T> contents) {
		this(success, contents, null);
	}

	/**
	 * @param success
	 *            Indicator of a successful service.
	 * @param contents
	 *            Message contents.
	 * @param message
	 *            Informational message.
	 * @author wonka
	 * @since 15/09/2012
	 */
	public Response(final boolean success, final List<T> contents, final String message) {
		this.success = success;
		this.message = message;
		this.contents = contents;
	}

	/**
	 * @param success
	 *            Indicator of a successful service.
	 * @param message
	 *            Informational message.
	 * @author wonka
	 * @since 15/09/2012
	 */
	public Response(final boolean success, final T body) {
		this(success, body, null);
	}

	/**
	 * @param success
	 *            Indicator of a successful service.
	 * @param body
	 *            Message contents.
	 * @param message
	 *            Informational message.
	 * @author wonka
	 * @since 15/09/2012
	 */
	public Response(final boolean success, final T body, final String message) {
		this.success = success;
		this.message = message;
		this.content = body;
		if (contents == null) {
			this.size = 1;
		} else {
			this.size = contents.size();
		}
	}

	/**
	 * @return the content
	 */
	public T getContent() {
		return content;
	}

	/**
	 * @return the contents
	 */
	public List<T> getContents() {
		return contents;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return FileDataStore quantidade de itens na resposta.
	 * @author wonka
	 * @since 22/09/2012
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [content=" + content + ", message=" + message + ", size=" + size + ", success=" + success
				+ "]";
	}
}
