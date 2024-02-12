package by.melnikov.customarraylist.service;

import by.melnikov.customarraylist.entity.impl.CustomArrayList;
import by.melnikov.customarraylist.exception.CustomException;

import java.util.Comparator;

/**
 * Интерфейс, предоставляющий методы для сортировки списков. Позволяет сортировать списки, содержащие любые объекты
 * реализующие интерфейс {@link Comparable}. Также позволяет использовать пользовательскую сортировку.
 */
public interface SortCustomArrayListService {

    /**
     * Метод, который позволяет отсортировать список на основании предоставленого компоратора
     * @param list список, который будет отсортирован
     * @param comparator объект компаратор, реализующий интерфейс {@link Comparator} и предоставляющий логику сравнения объектов списка
     * @param <T> тип объектов, хранящихся в данном списке
     * @throws CustomException возникает в случае доступа к элементам списка по неверному индексу (в данном случае не возникнет)
     */
    <T> void sort(CustomArrayList<T> list, Comparator<T> comparator) throws CustomException;

    /**
     * Метод, который позволяет отсортировать список по умолчанию
     * @param list список, который будет отсортирован
     * @param <T> тип объектов, хранящихся в данном списке. Класс T должен реализовывать интерфейс {@link Comparable}
     * @throws CustomException возникает в случае доступа к элементам списка по неверному индексу (в данном случае не возникнет)
     */
    <T extends Comparable<T>> void sort(CustomArrayList<T> list) throws CustomException;
}
