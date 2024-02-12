import by.melnikov.customarraylist.entity.CustomList;
import by.melnikov.customarraylist.entity.impl.CustomArrayList;
import by.melnikov.customarraylist.exception.CustomException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовы класс, проверяющий правильность выполнения ожидаемого поведения в исключительных ситуациях.
 */
public class CustomArrayListNegativeTest {
    private CustomList<Integer> testListOfIntegers;
    @BeforeEach
    public void setUp() {
        testListOfIntegers = new CustomArrayList<>();
        for (int i = 0; i < 10; i++) {
            testListOfIntegers.add(i + 1);
        }
    }

    @Test
    public void testWrongGetIndex() throws CustomException {
        assertThrows(CustomException.class, () -> testListOfIntegers.get(100));
        assertThrows(CustomException.class, () -> testListOfIntegers.get(-100));
    }

    @Test
    public void testWrongAddIndex() throws CustomException {
        assertThrows(CustomException.class, () -> testListOfIntegers.add(100, 1));
        assertThrows(CustomException.class, () -> testListOfIntegers.add(-100, 1));
    }

    @Test
    public void testWrongRemoveIndex() throws CustomException {
        assertThrows(CustomException.class, () -> testListOfIntegers.remove(100));
        assertThrows(CustomException.class, () -> testListOfIntegers.remove(-100));
    }

    @Test
    public void testWrongSetIndex() throws CustomException {
        assertThrows(CustomException.class, () -> testListOfIntegers.set(100, 1));
        assertThrows(CustomException.class, () -> testListOfIntegers.set(-100, 1));
    }

    @AfterEach
    public void clearList() {
        testListOfIntegers.clear();
    }

}
