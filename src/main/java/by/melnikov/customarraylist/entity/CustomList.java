package by.melnikov.customarraylist.entity;

import by.melnikov.customarraylist.exception.CustomException;

public interface CustomList<T> extends Iterable<T> {
    void add(T element);
    void add(int index, T element) throws CustomException;
    void addFirst(T element) throws CustomException;
    void addLast(T element);
    T get(int index) throws CustomException;
    T getFirst() throws CustomException;
    T getLast() throws CustomException;
    int contains(T element);
    T remove(int index) throws CustomException;
    boolean remove(T element) throws CustomException;
    T removeFirst() throws CustomException;
    T removeLast() throws CustomException;
    void clear();
    int size();
    boolean isEmpty();
    T set(int index, T element) throws CustomException;
    CustomList<T> subList(int from, int to) throws CustomException;
    void sort();
}
