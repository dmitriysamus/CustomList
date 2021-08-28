package samus;

import java.util.Collection;

/**
 * Simple list. Can store any objects.
 */
public interface CustomList<T> {

    int size();

    boolean isEmpty();

    /**
     * Add single item.
     */
    boolean add(T item);

    /**
     * Add all items.
     *
     * @throws IllegalArgumentException if parameter items is null
     */
    boolean addAll(T[] items);

    /**
     * Add all items.
     *
     * @throws IllegalArgumentException if parameter items is null
     */
    boolean addAll(Collection<T> items);

    /**
     * Add items to current position.
     *
     * @param index - index
     * @param items - items for insert
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     * @throws IllegalArgumentException       if items is null
     */
    boolean addAll(int index, T[] items);

    /**
     * Add item to head.
     */
    void addFirst(T item);

    /**
     * Add item to tail.
     */
    void addLast(T item);

    /**
     * Try to add item to head.
     */
    boolean offerFirst(T item);

    /**
     * Try to add item to tail.
     */
    boolean offerLast(T item);

    /**
     * Get item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    T get(int index);

    /**
     * Get first element.
     *
     * @throws java.util.NoSuchElementException if empty
     */
    T getFirst();

    /**
     * Get last element.
     *
     * @throws java.util.NoSuchElementException if empty
     */
    T getLast();

    /**
     * Peek first element.
     *
     * @return null if empty
     */
    T peekFirst();

    /**
     * Peek last element.
     *
     * @return null if empty
     */
    T peekLast();

    /**
     * Set item by index.
     *
     * @param index - index
     * @return old value
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    T set(int index, T item);

    /**
     * Remove item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    void remove(int index);

    /**
     * Remove item by value. Remove first item occurrence.
     *
     * @param item - item
     * @return true if item was removed
     */
    boolean remove(T item);

    /**
     * Poll first element in list.
     *
     * @return null if empty
     */
    T pollFirst();

    /**
     * Poll last element in list.
     *
     * @return null if empty
     */
    T pollLast();

    /**
     * Remove first element in list
     *
     * @throws java.util.NoSuchElementException if empty
     */
    T removeFirst();

    /**
     * Remove last element in list
     *
     * @throws java.util.NoSuchElementException if empty
     */
    T removeLast();

    /**
     * Checks if item exists.
     *
     * @param item - item
     * @return true or false
     */
    boolean contains(T item);

    /**
     * Index of item.
     *
     * @param item - item
     * @return index of element or -1 of list doesn't contain element
     */
    int indexOf(T item);

    /**
     * Reverse list.
     */
    void reverse();

    /**
     * Get content in format '[ element1 element2 ... elementN ] or [ ] if empty.
     */
    String toString();

    /**
     * Copy list to array.
     */
    Object[] toArray();
}

