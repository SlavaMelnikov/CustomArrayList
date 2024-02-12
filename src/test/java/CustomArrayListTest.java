import by.melnikov.customarraylist.entity.CustomList;
import by.melnikov.customarraylist.entity.Student;
import by.melnikov.customarraylist.entity.impl.CustomArrayList;
import by.melnikov.customarraylist.exception.CustomException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовы класс, проверяющий правильность выполнения методов для работы со списком и ожидаемого поведения.
 */
class CustomArrayListTest {
    private CustomList<Integer> testListOfIntegers;
    private CustomList<Student> testListOfStudents;
    private Student student1;
    private Student student2;
    private Student student3;
    private Student testStudent;
    @BeforeEach
    public void setUp() {
        testListOfIntegers = new CustomArrayList<>();
        for (int i = 0; i < 10; i++) {
            testListOfIntegers.add(i + 1);
        }
        testListOfStudents = new CustomArrayList<>();
        student1 = new Student("First", 18);
        student2 = new Student("Second", 19);
        student3 = new Student("Third", 20);
        testListOfStudents.add(student1);
        testListOfStudents.add(student2);
        testListOfStudents.add(student3);
        testStudent = new Student("test student", 100);
    }

    @Test
    public void testAddIntegerAndGet() throws CustomException {
        testListOfIntegers.add(1000);
        assertEquals(Integer.valueOf(1000), testListOfIntegers.get(10));
    }

    @Test
    public void testAddStudentAndGet() throws CustomException {
        testListOfStudents.add(testStudent);
        assertEquals(testStudent, testListOfStudents.get(3));
    }

    @Test
    public void testAddIntegerAtIndex() throws CustomException {
        testListOfIntegers.add(5, 2000);
        assertEquals(Integer.valueOf(2000), testListOfIntegers.get(5));
    }

    @Test
    public void testAddStudentAtIndex() throws CustomException {
        testListOfStudents.add(0, testStudent);
        assertEquals(testStudent, testListOfStudents.get(0));
    }

    @Test
    public void testContainsInteger() {
        testListOfIntegers.add(1000);
        assertAll(
                () -> assertEquals(10, testListOfIntegers.contains(1000)),
                () -> assertEquals(-1, testListOfIntegers.contains(-100)));
    }

    @Test
    public void testContainsStudent() {
        testListOfStudents.add(testStudent);
        assertAll(
                () -> assertEquals(3, testListOfStudents.contains(testStudent)),
                () -> assertEquals(-1, testListOfStudents.contains(new Student("not added", 300))));
    }

    @Test
    public void testRemoveIntegerByIndex() throws CustomException {
        assertEquals(Integer.valueOf(1), testListOfIntegers.remove(0));
    }

    @Test
    public void testRemoveStudentByIndex() throws CustomException {
        assertEquals(student1, testListOfStudents.remove(0));
    }

    @Test
    public void testRemoveInteger() throws CustomException {
        assertAll(
                () -> assertTrue(testListOfIntegers.remove(Integer.valueOf(10))),
                () -> assertFalse(testListOfIntegers.remove(Integer.valueOf(1000))));
    }

    @Test
    public void testRemoveStudent() throws CustomException {
        assertAll(
                () -> assertTrue(testListOfStudents.remove(student2)),
                () -> assertFalse(testListOfStudents.remove(testStudent)));
    }

    @Test
    public void testClearAndIsEmpty() {
        testListOfIntegers.clear();
        assertTrue(testListOfIntegers.isEmpty());
    }

    @Test
    public void testSize() {
        testListOfIntegers.add(1000);
        testListOfIntegers.add(2000);
        assertEquals(12, testListOfIntegers.size());
    }

    @Test
    public void testSetInteger() throws CustomException {
        assertAll(
                () -> assertEquals(Integer.valueOf(6), testListOfIntegers.set(5, 1000)),
                () -> assertEquals(Integer.valueOf(1000), testListOfIntegers.get(5)));
    }

    @Test
    public void testSetStudent() throws CustomException {
        assertAll(
                () -> assertEquals(student2, testListOfStudents.set(1, testStudent)),
                () -> assertEquals(testStudent, testListOfStudents.get(1)));
    }



    @AfterEach
    public void clearList() {
        testListOfIntegers.clear();
        testListOfStudents.clear();
    }

}
