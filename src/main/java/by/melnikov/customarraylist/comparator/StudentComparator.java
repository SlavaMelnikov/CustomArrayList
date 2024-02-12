package by.melnikov.customarraylist.comparator;

import by.melnikov.customarraylist.entity.Student;

import java.util.Comparator;

/**
 * Класс, реализующий интерфейс {@link Comparator} и описывающий своб логику сравнение объектов
 * класса {@link Student}.
 */
public class StudentComparator implements Comparator<Student> {

    /**
     * Сравнивает два объекта класса {@link Student} по возврасту.
     * @param o1 первый объект, который будет сравниваться
     * @param o2 второй объект, который будет сравниваться
     * @return отрицательно число, если первый объект больше второго, положительное, если наоборот, 0 если равны
     */
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getAge() - o2.getAge();
    }
}
