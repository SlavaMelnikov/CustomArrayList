package by.melnikov.customarraylist.exception;

/**
 * Класс, являющийся проверяемым кастомным исключением. Создан для повышения контроля
 * работы списка в исключительных ситуациях и повышения надежности в целом.
 *
 */
public class CustomException extends Exception {
    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
}
