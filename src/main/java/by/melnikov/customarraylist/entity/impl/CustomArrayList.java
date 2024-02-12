package by.melnikov.customarraylist.entity.impl;

import by.melnikov.customarraylist.entity.CustomList;
import by.melnikov.customarraylist.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Iterator;
import java.util.StringJoiner;


/**
 * Пользовательская реализация интерфейса CustomList. Класс предоставляет собой реализацию методов для работы с списком элементов.
 * {@link CustomArrayList} представляет собой упорядоченную, не потокобезобасную, динамически расширяемую коллекцию элементов.
 *
 * @param <T> тип элементов, хранящихся в коллекции.
 * @author  Slava Melnikov
 */
public class CustomArrayList<T> implements CustomList<T> {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Размер списка по умолчанию
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Текущий размер списка
     */
    private int size;

    /**
     * Текущая вместимость списка
     */
    private int capacity;

    /**
     *  Поле, хранящее сами элементы списка.
     */
    T[] elements;


    /**
     * Конструктор, который создает список с размером по умолчанию
     */
    public CustomArrayList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Конструктор, который создает список с указаным размером. Если в качестве аргументы было пердано отрицательное число
     * или ноль, создает список с размером по умолчанию и сообщает об этом. (Возможно выбор такой логики поведения не совсем
     * правильный)
     * @param initialCapacity изначальная длина созданого списка
     */
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            elements = (T[]) new Object[DEFAULT_SIZE];
            logger.log(Level.WARN, "You tried to create list with incorrect capacity. Was created default list");
        }
        elements = (T[]) new Object[initialCapacity];
        capacity = initialCapacity;
    }

    /**
     * Добавляет элемент element в конец списка.
     * @param element  элемент, который будет добавлен в конец списка
     */
    @Override
    public void add(T element) {
        if (isListFull()) {
            increaseArrayList();
        }
        elements[size++] = element;
    }

    /**
     * Вспомогательный метод, который проверяет текущее состояние списка: заполнен или нет.
     * @return true если заполнен, false если есть свободное место
     */
    private boolean isListFull() {
        return size == capacity;
    }

    /**
     * Вспомогательный метод, который увеличивает размер списка на половину.
     */
    private void increaseArrayList() {
        int newCapacity = (capacity * 3) / 2 + 1;
        T[] temp = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, temp, 0, size);
        elements = temp;
        capacity = newCapacity;
    }

    /**
     * Добавляет элемент element на указанную позицию index в списке. Все элементы, находящиеся правее от
     * позици вставки, будут сдвинуты вправо на одну позицию.
     * @param index  позиция в списке, куда будет вставлен element
     * @param element  объект, который будет добавлен в список
     * @throws CustomException если передан индекс, выходящий за текущие границы списка.
     */
    @Override
    public void add(int index, T element) throws CustomException {
        checkIndex(index);
        if (isListFull()) {
            increaseArrayList();
        }
        int countOfShiftedElements = size - index;
        System.arraycopy(elements, index, elements, index + 1, countOfShiftedElements);
        set(index, element);
        size++;
    }

    /**
     * Вспомогательный метод для определения валидности индекса текущего размера списка.
     * @param index индекс, который будет проверен на валидность
     * @throws CustomException если передан индекс, выходящий за текущие границы списка.
     */
    private void checkIndex(int index) throws CustomException {
        if (index < 0 || index >= size) {
            logger.log(Level.ERROR, "Index " + index + " is incorrect");
            throw new CustomException("Index " + index + " is incorrect");
        }
    }

    /**
     * Заменяет элемент на позиции index новым элементом element и возвращает старый элемент.
     * Если индекс недопустим, выбрасывается исключение CustomException.
     * @param index  позиция в списке, в которую будет вставлен объект element
     * @param element  объект, который будет вставлен в список на позицию index
     * @return объект, который бы заменен
     * @throws CustomException если передан индекс, выходящий за текущие границы списка.
     */
    @Override
    public T set(int index, T element) throws CustomException {
        T oldValue = get(index);
        elements[index] = element;
        return oldValue;
    }

    /**
     * Возвращает элемент, находящийся на позиции index в списке.
     * @param index позиция в списке, по которой будет получен объект
     * @return объект, находящийся на позиции index
     * @throws CustomException если передан индекс, выходящий за текущие границы списка.
     */
    @Override
    public T get(int index) throws CustomException {
        checkIndex(index);
        return elements[index];
    }

    /**
     * Проверяет, содержится ли элемент element в списке. Возвращает индекс первого вхождения элемента.
     * @param element объект, наличие в списке которого мы проверяем
     * @return возвращает индекс первого вхождения элемента или -1, если элемент не найден.
     */
    @Override
    public int contains(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].hashCode() == element.hashCode() && elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Удаляет элемент на позиции index из списка и возвращает его.
     * @param index индекс элемента, который будет удален
     * @return объект, который был удален из списка.
     * @throws CustomException если передан индекс, выходящий за текущие границы списка.
     */
    @Override
    public T remove(int index) throws CustomException {
        checkIndex(index);
        T removedElement = get(index);
        int countOfShiftedElements = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, countOfShiftedElements);
        size--;
        return removedElement;
    }

    /**
     * Удаляет первое вхождение объекта element из списка.
     * @param element объект, который будет удален из списка
     * @return true если объект был удален, false если такой объект отсутствует в списке
     * @throws CustomException если передан индекс, выходящий за текущие границы списка.
     */
    @Override
    public boolean remove(T element) throws CustomException {
        int indexOfElementForRemove = contains(element);
        if (indexOfElementForRemove == -1) {
            return false;
        }
        remove(indexOfElementForRemove);
        return true;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    @Override
    public void clear() {
        for (T element : elements) {
            element = null;
        }
        size = 0;
    }

    /**
     * Возвращает текущее количество элементов в списке.
     * @return текущее количество элементов в списке.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список. Возвращает true, если список не содержит элементов, и false в противном случае.
     * @return true, если список не содержит элементов, и false в противном случае.
     */
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

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomArrayList.class.getSimpleName() + "[", "]")
                .add("elements=" + Arrays.toString(elements))
                .toString();
    }
}
