package by.melnikov.customarraylist.entity.impl;

import by.melnikov.customarraylist.entity.CustomList;
import by.melnikov.customarraylist.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class CustomArrayList<T> implements CustomList<T> {
    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private int capacity = DEFAULT_SIZE;
    T[] elements;

    public CustomArrayList() {
        this(DEFAULT_SIZE);
    }

    public CustomArrayList(int initialCapacity) {
        elements = (T[]) new Object[initialCapacity];
    }

    @Override
    public void add(T element) {
        if (isListFull()) {
            increaseArrayList();
        }
        elements[size++] = element;
    }

    private boolean isListFull() {
        return size == capacity;
    }

    private void increaseArrayList() {
        int newCapacity = (capacity * 3) / 2 + 1;
        T[] temp = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, temp, 0, size);
        elements = temp;
        capacity = newCapacity;
    }

    @Override
    public void add(int index, T element) throws CustomException {
        checkIndex(index);
        if (isListFull()) {
            increaseArrayList();
        }
        int countOfShiftedElements = size - index;
        System.arraycopy(elements, index, elements, index + 1, countOfShiftedElements);
        set(index, element);
    }

    private void checkIndex(int index) throws CustomException {
        if (index < 0 || index >= size) {
            logger.log(Level.ERROR, "Index " + index + " is incorrect");
            throw new CustomException("Index " + index + " is incorrect");
        }
    }

    @Override
    public void addFirst(T element) throws CustomException {
        add(0, element);
    }

    @Override
    public void addLast(T element) {
        add(element);
    }

    @Override
    public T get(int index) throws CustomException {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public T getFirst() throws CustomException {
        return get(0);
    }

    @Override
    public T getLast() throws CustomException {
        return get(size - 1);
    }

    @Override
    public int contains(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].hashCode() == element.hashCode() && elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T remove(int index) throws CustomException {
        checkIndex(index);
        T removedElement = get(index);
        int countOfShiftedElements = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, countOfShiftedElements);
        return removedElement;
    }

    @Override
    public boolean remove(T element) throws CustomException {
        int indexOfElementForRemove = contains(element);
        if (indexOfElementForRemove == -1) {
            return false;
        }
        remove(indexOfElementForRemove);
        return true;
    }

    @Override
    public T removeFirst() throws CustomException {
        if (size == 0) {
            throw new CustomException("Array list is empty");
        }
        return remove(0);
    }

    @Override
    public T removeLast() throws CustomException {
        if (size == 0) {
            throw new CustomException("Array list is empty");
        }
        return remove(size);
    }

    @Override
    public void clear() {
        for (T element : elements) {
            element = null;
        }
    }

    @Override
    public T set(int index, T element) throws CustomException {
        return null;
    }

    @Override
    public CustomList<T> subList(int from, int to) throws CustomException {
        return null;
    }

    @Override
    public void sort() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return elements[currentIndex++];
            }

            @Override
            public void remove() {
                int countOfShiftedElements = size - currentIndex - 1;
                System.arraycopy(elements, currentIndex + 1, elements, currentIndex, countOfShiftedElements);
            }
        };
    }
}
