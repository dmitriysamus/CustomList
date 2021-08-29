package samus;

import java.util.Collection;
import java.util.NoSuchElementException;

public class CustomListImpl <T> implements CustomList <T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        public Node(T item, Node<T> next, Node<T> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public CustomListImpl() {
        this.size = 0;
        this.first = this.last = new Node<T>(null, null, null);
    }

    public CustomListImpl(Collection<T> c) {
        nullCheck(c);
        CustomListImpl<T> customListImpl = new CustomListImpl<T>();
        Object[] newData = c.toArray();
        size = newData.length;
        for (int i = 0; i < size; i++) {
            customListImpl.addLast((T)newData[i]);
        }
        first = customListImpl.first;

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(T item) {
        addLast(item);
        return true;
    }

    public boolean addAll(T[] items) {
        nullCheck(items);
        for (T item : items)
            addLast(item);

        return true;
    }

    public boolean addAll(Collection<T> items) {
        nullCheck(items);
        Object[] newData = items.toArray();
        int newSize = size + newData.length;
        for (Object item : newData)
            addLast((T) item);

        size = newSize;
        return true;
    }

    public boolean addAll(int index, T[] items) {
        nullCheck(items);
        rangeCheck(index);

        Node<T> tmp = first.next;
        CustomListImpl<T> customListImpl = new CustomListImpl();
        int newSize = size + items.length;
        for (int i = 0; i < index; i++) {
            customListImpl.add(tmp.item);
            tmp = tmp.next;
        }

        int j = 0;
        for (int i = index; i < index + items.length; i++, j++) {
            customListImpl.add(items[j]);
        }

        int contStart = index + items.length;
        int contEnd = newSize;
        for (int i = contStart; i < contEnd; i++) {
            customListImpl.add(tmp.item);
            tmp = tmp.next;
        }

        first = customListImpl.first;
        size = newSize;

        return true;
    }

    public void addFirst(T item) {
        final Node<T> f = first;
        final Node<T> newNode = new Node<T>(item, f.next, null);
        if (f.next == null) {
            newNode.next = null;
        }
        first.next = newNode;
        newNode.prev = first;
        size++;
    }

    public void addLast(T item) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<T>(item, null, null);
        last = newNode;

        if (l == null) {
            first = newNode;
            first.prev = null;
        } else {
            l.next = newNode;
            newNode.next = null;
            newNode.prev = last;
        }
        size++;
    }

    public boolean offerFirst(T item) {
        addFirst(item);
        return true;
    }

    public boolean offerLast(T item) {
        addLast(item);
        return true;
    }

    public T get(int index) {
        rangeCheck(index);
        int i = 0;
        Node<T> tmp = first.next;

        while (tmp != null) {
            if (i == index) {
                break;
            }
            i++;
            tmp = tmp.next;
        }

        return tmp.item;
    }

    public T getFirst() {
        final Node<T> f = first.next;
        if (first.next == null) {
            throw new NoSuchElementException("first не существует!");
        }
        return first.item;
    }

    public T getLast() {
        if (last == first || last == null) {
            throw new NoSuchElementException("last не существует!");
        }
        return last.item;
    }

    public T peekFirst() {
        if (first.next == null) {
            return null;
        }
        return first.next.item;
    }

    public T peekLast() {
        if (last == first || last == null) {
            return null;
        }
        return last.item;
    }

    public T set(int index, T item) {
        rangeCheck(index);
        int i = 0;
        Node<T> tmp = first.next;

        while (tmp != null) {
            if (i == index) {
                break;
            }
            i++;
            tmp = tmp.next;
        }
        T oldItem = tmp.item;
        tmp.item = item;
        return oldItem;
    }

    public void remove(int index) {
        rangeCheck(index);
        CustomListImpl<T> customListImpl = new CustomListImpl();
        Node<T> tmp = first.next;

        for (int i = 0; i < size; i++) {
            if (i != index) {
                customListImpl.addLast(tmp.item);
            }
            tmp = tmp.next;
        }
        size--;
        first = customListImpl.first;
    }

    public boolean remove(T item) {
        CustomListImpl<T> customListImpl = new CustomListImpl();
        Node<T> tmp = first.next;
        boolean result = false;
        boolean remoteChecker = true;
        int count = size;

        if (item != null) {
            for (int i = 0; i < count; i++) {
                if (item.equals(tmp.item) && remoteChecker) {
                    result = true;
                    remoteChecker = false;
                    tmp = tmp.next;
                    size--;
                    continue;
                }
                customListImpl.addLast(tmp.item);
                tmp = tmp.next;
            }
        } else {
            for (int i = 0; i < count; i++) {
                if (tmp.item != null && remoteChecker) {
                    result = true;
                    remoteChecker = false;
                    tmp = tmp.next;
                    size--;
                    continue;
                }
                customListImpl.addLast(tmp.item);
                tmp = tmp.next;
            }
        }
        first = customListImpl.first;

        return result;
    }

    public T pollFirst() {
        Node<T> tmp = first.next;
        if (tmp == null) {
            return null;
        }

        if (first.next != null) {
            first.next.prev = null;
            first = first.next;
        } else {
            first = null;
        }
        size--;
        return tmp.item;
    }

    public T pollLast() {
        Node<T> tmp = last;
        if (last == null || last == first) {
            return null;
        }

        if (last.prev != null) {
            last.prev.next = null;
            last = last.prev;
        } else {
            first.next = null;
            last = null;
        }
        size--;
        return tmp.item;
    }

    public T removeFirst() {
        if (first.next == null) {
            throw new NoSuchElementException("first не существует!");
        }
        return pollFirst();
    }

    public T removeLast() {
        if (last == null || last == first) {
            throw new NoSuchElementException("last не существует!");
        }
        return pollLast();
    }

    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    public int indexOf(T item) {
        int i = 0;
        Node<T> tmp = first.next;

        while (tmp != null) {
            if (tmp.item == item) {
                return i;
            }
            i++;
            tmp = tmp.next;
        }
        return -1;
    }

    public void reverse() {
        CustomListImpl<T> customListImpl = new CustomListImpl<T>();
        Node<T> tmp = first.next;
        for (int i = 0; i < size; i++) {
            customListImpl.addFirst(tmp.item);
            tmp = tmp.next;
        }
        first = customListImpl.first;
    }

    public Object[] toArray() {
        Object[] newData = new Object[size];
        Node<T> tmp = first.next;
        for (int i = 0; i < size; i++) {
            newData[i] = tmp.item;
            tmp = tmp.next;
        }
        return newData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node<T> tmp = first.next;
        sb.append("[");
        while (tmp != null) {

            sb.append(" " + tmp.item);
            tmp = tmp.next;
        }

        sb.append(" ]");
        return sb.toString();
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("" + index);
        }
    }

    private void nullCheck(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Объект для добавления не может быть null!");
        }
    }
}
