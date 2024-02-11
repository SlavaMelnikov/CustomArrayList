package by.melnikov.customarraylist.main;

import by.melnikov.customarraylist.entity.impl.CustomArrayList;
import by.melnikov.customarraylist.entity.SomeCustomObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.log(Level.INFO, "Application is working...");

        CustomArrayList<String> stringList = new CustomArrayList<>();
        CustomArrayList<Integer> integerList = new CustomArrayList<>(100);
        CustomArrayList<SomeCustomObject> customObjectList = new CustomArrayList<>();

    }
}
