package seedu.address.model.course.exceptions;

/**
 * Signals that there is a duplicate course ID.
 */
public class DuplicateCourseIdException extends RuntimeException {
    public DuplicateCourseIdException() {
        super("CourseId already exists");
    }
}
