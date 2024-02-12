package by.melnikov.customarraylist.service.impl;

import by.melnikov.customarraylist.entity.impl.CustomArrayList;
import by.melnikov.customarraylist.exception.CustomException;
import by.melnikov.customarraylist.service.SortCustomArrayListService;

import java.util.Comparator;

/**
 * Класс, предоставлющий реализацию  методов сортировки интерфейса {@link SortCustomArrayListService}.
 * Сортирует списки используя алгоритм быстрой сортировки (Quick sort).
 */
public class QuickSort implements SortCustomArrayListService {

    /**
     * Метод, который позволяет отсортировать список на основании предоставленого компоратора
     * @param list список, который будет отсортирован
     * @param comparator объект компаратор, реализующий интерфейс {@link Comparator} и предоставляющий логику сравнения объектов списка
     * @param <T> тип объектов, хранящихся в данном списке
     * @throws CustomException возникает в случае доступа к элементам списка по неверному индексу (в данном случае не возникнет)
     */
    @Override
    public <T> void sort(CustomArrayList<T> list, Comparator<T> comparator) throws CustomException {
        quickSort(list, 0, list.size() - 1, comparator);
    }

    private <T> void quickSort(CustomArrayList<T> list, int left, int right, Comparator<T> comparator) throws CustomException {
        if (left < right) {
            int pivot = partition(list, left, right, comparator);
            quickSort(list, left, pivot - 1, comparator);
            quickSort(list, pivot + 1, right, comparator);
        }
    }

    private <T> int partition(CustomArrayList<T> list, int left, int right, Comparator<T> comparator) throws CustomException {
        int pivot = right;
        while (left < pivot) {
            if (comparator.compare(list.get(left), list.get(pivot)) > 0) {
                T temp = list.get(pivot - 1);
                list.set(pivot - 1, list.get(pivot));
                list.set(pivot, temp);

                if (left + 1 != pivot) {
                    temp = list.get(left);
                    list.set(left, list.get(pivot));
                    list.set(pivot, temp);
                }
                pivot--;
                left--;
            }
            left++;
        }
        return pivot;
    }

    /**
     * Метод, который позволяет отсортировать список по умолчанию
     * @param list список, который будет отсортирован
     * @param <T> тип объектов, хранящихся в данном списке. Класс T должен реализовывать интерфейс {@link Comparable}
     * @throws CustomException возникает в случае доступа к элементам списка по неверному индексу (в данном случае не возникнет)
     */
    @Override
    public <T extends Comparable<T>> void sort(CustomArrayList<T> list) throws CustomException {
        quickSort(list, 0, list.size() - 1);
    }

    private <T extends Comparable<T>> void quickSort(CustomArrayList<T> list, int left, int right) throws CustomException {
        if (left < right) {
            int pivot = partition(list, left, right);
            quickSort(list, left, pivot - 1);
            quickSort(list, pivot + 1, right);
        }
    }

    private <T extends Comparable<T>> int partition(CustomArrayList<T> list, int left, int right) throws CustomException {
        int pivot = right;
        while (left < pivot) {
            T leftElement = list.get(left);
            T onPivotElement = list.get(pivot);
            if (leftElement.compareTo(onPivotElement) > 0) {
                T temp = list.get(pivot - 1);
                list.set(pivot - 1, list.get(pivot));
                list.set(pivot, temp);

                if (left + 1 != pivot) {
                    temp = list.get(left);
                    list.set(left, list.get(pivot));
                    list.set(pivot, temp);
                }
                pivot--;
                left--;
            }
            left++;
        }
        return pivot;
    }

}


