package samus;

import java.util.Collection;
import java.util.NoSuchElementException;
/**
 * Двусвязный список, реализует интерфейс CustomList<T>,
 * может хранить объекты любого типа.
 */
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
    /**
     * Конструктор пустого объекта.
     */

    public CustomListImpl() {
        this.size = 0;
        this.first = this.last = new Node<T>(null, null, null);
    }

    /**
     * Конструктор объекта на основе Collection.
     */
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

    /**
     * Возвращает размер связанного списка
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет связанный список на то, пустой ли он
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Добавляет объект в список
     */
    public boolean add(T item) {
        addLast(item);
        return true;
    }

    /**
     * Добавляет объекты списка типа Т в список
     */
    public boolean addAll(T[] items) {
        nullCheck(items);
        for (T item : items)
            addLast(item);

        return true;
    }

    /**
     * Добавляет объекты типа Collection в список
     */
    public boolean addAll(Collection<T> items) {
        nullCheck(items);
        Object[] newData = items.toArray();
        int newSize = size + newData.length;
        for (Object item : newData)
            addLast((T) item);

        size = newSize;
        return true;
    }

    /**
     * Добавляет объекты списка типа Т в список по указанному индексу
     */
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

    /**
     * Добавляет объект списка типа Т на позицию связанного списка first.
     */
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

    /**
     * Добавляет объект списка типа Т на позицию связанного списка last.
     */
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

    /**
     * Добавляет объект списка типа Т на позицию связанного списка first.
     */
    public boolean offerFirst(T item) {
        addFirst(item);
        return true;
    }

    /**
     * Добавляет объект списка типа Т на позицию связанного списка last.
     */
    public boolean offerLast(T item) {
        addLast(item);
        return true;
    }

    /**
     * Возвращает объект связанного списка типа Т.
     */
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

    /**
     * Возвращает объект связанного списка типа Т с позиции first.
     */
    public T getFirst() {
        final Node<T> f = first.next;
        if (first.next == null) {
            throw new NoSuchElementException("first не существует!");
        }
        return first.item;
    }

    /**
     * Возвращает объект связанного списка типа Т с позиции last.
     */
    public T getLast() {
        if (last == first || last == null) {
            throw new NoSuchElementException("last не существует!");
        }
        return last.item;
    }

    /**
     * Возвращает объект связанного списка типа Т с позиции first.
     */
    public T peekFirst() {
        if (first.next == null) {
            return null;
        }
        return first.next.item;
    }

    /**
     * Возвращает объект связанного списка типа Т с позиции last.
     */
    public T peekLast() {
        if (last == first || last == null) {
            return null;
        }
        return last.item;
    }

    /**
     * Заменяет объект по указанному индексу,
     * возвращает удаленный объект списка.
     */
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

    /**
     * Удаляет объект по указанному индексу.
     */
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

    /**
     * Удаляет объект эквивалентный указанному.
     */
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

    /**
     * Удаляет объект first из связанного списка.
     */
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

    /**
     * Удаляет объект last из связанного списка.
     */
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

    /**
     * Удаляет объект first из связанного списка.
     */
    public T removeFirst() {
        if (first.next == null) {
            throw new NoSuchElementException("first не существует!");
        }
        return pollFirst();
    }

    /**
     * Удаляет объект last из связанного списка.
     */
    public T removeLast() {
        if (last == null || last == first) {
            throw new NoSuchElementException("last не существует!");
        }
        return pollLast();
    }

    /**
     * Проверяет наличие объекта в связанном списке.
     */
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

    /**
     * Отображает объекты связанного списка в обратном порядке.
     */
    public void reverse() {
        CustomListImpl<T> customListImpl = new CustomListImpl<T>();
        Node<T> tmp = first.next;
        for (int i = 0; i < size; i++) {
            customListImpl.addFirst(tmp.item);
            tmp = tmp.next;
        }
        first = customListImpl.first;
    }

    /**
     * Возвращает объекты связанного списка в виде массива.
     */
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

    /**
     * Проверяет передаваемый индекс на неотрицательность и выход за пределы списка.
     */
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("" + index);
        }
    }

    /**
     * Проверяет передаваемый объект на null.
     */
    private void nullCheck(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Объект для добавления не может быть null!");
        }
    }
}
