package com.dvlcube.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TODO: implement a cache strategy.
 * 
 * @author wonka
 * @param <E>
 * @since 07/09/2012
 */
public class CubeList<E> extends ArrayList<E> {

	public interface Factory<T> {
		List<T> f(int start, int maxResults);
	}

	private static final long serialVersionUID = 166616661666L;

	private final Factory<E> factory;

	private int lastFilledIndex;

	private final int pageSize;

	/**
	 * Creates a lazy list with the default page size.
	 * 
	 * @see Pageable
	 * @param factory
	 * @author wonka
	 * @since 22/09/2012
	 */
	public CubeList(final Factory<E> factory) {
		this(factory, Pageable.PAGE_SIZE);
	}

	/**
	 * @param factory
	 *            Factory function.
	 * @param pageSize
	 *            Page size. Every time index x is requested, <code>pageSize</<code> items will be fetched.
	 * @author wonka
	 * @since 13/09/2012
	 */
	public CubeList(final Factory<E> factory, final int pageSize) {
		super(pageSize);
		this.factory = factory;
		this.pageSize = pageSize;
	}

	/**
	 * @param index
	 * @author wonka
	 * @since 16/09/2012
	 */
	private void expand(final int index) {
		for (int i = size(); i < index + 1 + pageSize; i++) {
			add(null);
		}
	}

	/**
	 * Fills the list with the results for that range.
	 * 
	 * @param index
	 *            The accessed index.
	 * @author wonka
	 * @since 16/09/2012
	 */
	private void fill(final int index) {
		final int start = new Function<Integer, Integer>() {
			@Override
			public Integer f(final Integer x) {
				for (int i = x; i > -1; i--) {
					if (i % pageSize == 0) {
						return i;
					}
				}
				return 0;
			}
		}.f(index);

		final List<E> newItems = factory.f(start, pageSize);
		lastFilledIndex = start + newItems.size();
		addAll(start, newItems);
	}

	/**
	 * @param position
	 * @author wonka
	 * @since 16/09/2012
	 */
	private void fillIfEmpty(final int position) {
		if (isEmpty(position) || isEmpty(position + pageSize)) {
			fill(position);
		}
	}

	public void forEach(final Function<E, E> exe) {
		for (final E e : this) {
			set(indexOf(e), exe.f(e));
		}
	}

	@Override
	public E get(final int position) {
		growIfOutOfBounds(position);
		return super.get(position);
	}

	/**
	 * @param position
	 * @author wonka
	 * @since 16/09/2012
	 */
	private void growIfOutOfBounds(final int position) {
		if (outOfBounds(position)) {
			expand(position);
		}
		fillIfEmpty(position);
	}

	/**
	 * 
	 * @author wonka
	 * @since 16/09/2012
	 */
	private void initialize() {
		get(0);
	}

	/**
	 * @param position
	 * @return
	 * @author wonka
	 * @since 16/09/2012
	 */
	private boolean isEmpty(final int position) {
		return super.get(position) == null;
	}

	@Override
	public Iterator<E> iterator() {
		initialize();
		return super.iterator();
	}

	/**
	 * @param position
	 * @return
	 * @author wonka
	 * @since 16/09/2012
	 */
	private boolean outOfBounds(final int position) {
		return position > size() - 1;
	}

	/**
	 * @return The contents of the next page.
	 * @author wonka
	 * @since 22/09/2012
	 */
	public List<E> scroll() {
		final int newIndex = lastFilledIndex + 1;
		get(newIndex);
		return subList(newIndex, newIndex + pageSize);
	}

	@Override
	public List<E> subList(final int fromIndex, final int toIndex) {
		growIfOutOfBounds(fromIndex);
		return super.subList(fromIndex, toIndex);
	}

	/**
	 * @param request
	 * @return Element range.
	 * @author wonka
	 * @since 22/09/2012
	 */
	public List<E> subList(final PageRequest request) {
		final int start = request.getStart();
		return subList(start, start + pageSize);
	}
}
