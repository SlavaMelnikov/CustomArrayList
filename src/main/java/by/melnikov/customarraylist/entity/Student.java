package by.melnikov.customarraylist.entity;

import by.melnikov.customarraylist.util.IdGenerator;

import java.util.StringJoiner;

/**
 * Класс сущность, созданный для проверки работы {@link CustomList} с случайным объектом.
 * Реализует интерфейс {@link Comparable} для проверки правильности списка при реализации
 * кастомной сортировки.
 */
public class Student implements Comparable<Student> {
    private int id;
    private String name;
    private int age;

    public Student(String name, int age) {
        this.id = IdGenerator.generateId();
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
        return this.id - o.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student that = (Student) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("age=" + age)
                .toString();
    }
}
