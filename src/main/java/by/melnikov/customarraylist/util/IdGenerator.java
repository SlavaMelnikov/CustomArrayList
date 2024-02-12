package by.melnikov.customarraylist.util;

/**
    Утилитный класс, генерирующий уникальные id для созданных объектов.
 */
public class IdGenerator {
    private static int currentId;

    public static int generateId() {
        return ++currentId;
    }
}
