package by.melnikov.customarraylist.main;

import by.melnikov.customarraylist.comparator.StudentComparator;
import by.melnikov.customarraylist.entity.impl.CustomArrayList;
import by.melnikov.customarraylist.entity.Student;
import by.melnikov.customarraylist.exception.CustomException;
import by.melnikov.customarraylist.service.impl.QuickSort;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static final String TEXT = "HashSet в Java использует хэш-таблицу для хранения элементов. Он использует метод hashCode() для получения хэш-кода объекта и метод equals() для сравнения объектов на равенство.";

    public static void main(String[] args) throws CustomException {
        logger.log(Level.INFO, "Application is working...");

        String[] words = TEXT.split("\\s");
        CustomArrayList<String> stringList = new CustomArrayList<>();
        CustomArrayList<Integer> integerList = new CustomArrayList<>(words.length);
        CustomArrayList<Student> studentsList = new CustomArrayList<>();
        logger.log(Level.INFO, "All custom lists was successfully created");

        for(String word : words) {
            stringList.add(word);
            integerList.add(word.length());
        }
        studentsList.add(new Student("Petya", 18));
        studentsList.add(new Student("Elena", 19));
        studentsList.add(new Student("Max", 22));
        studentsList.add(new Student("Olga", 18));
        studentsList.add(new Student("John", 21));
        studentsList.add(new Student("Travis", 20));

        logger.log(Level.INFO, "All custom lists was successfully filled. Lists before sorting:");
        System.out.println(stringList);
        System.out.println(integerList);
        System.out.println(studentsList);

        QuickSort sortService = new QuickSort();
        try {
            sortService.sort(stringList);
            sortService.sort(integerList);
            sortService.sort(studentsList, new StudentComparator());
        } catch (CustomException e) {
            logger.log(Level.ERROR, "Some error during sorting");
            throw new CustomException("Some error during sort. " + e.getMessage());
        }

        logger.log(Level.INFO, "Lists after sorting:");
        System.out.println(stringList);
        System.out.println(integerList);
        System.out.println(studentsList);

        logger.log(Level.INFO, "Application working was finished.");
    }
}
