package seedu.address.model.course.exceptions;

public class DuplicateCourseIdException extends RuntimeException {
    public DuplicateCourseIdException() {
        super("CourseId already exists");
    }
}
