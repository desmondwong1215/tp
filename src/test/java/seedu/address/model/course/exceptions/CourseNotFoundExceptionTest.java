package seedu.address.model.course.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CourseNotFoundExceptionTest {

    @Test
    public void constructor_noArgs_createsException() {
        CourseNotFoundException exception = new CourseNotFoundException();
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    public void throwException_canBeCaught() {
        assertThrows(CourseNotFoundException.class, () -> {
            throw new CourseNotFoundException();
        });
    }

    @Test
    public void throwException_canBeCaughtAsRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            throw new CourseNotFoundException();
        });
    }
}
