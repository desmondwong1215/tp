package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;

/**
 * Deletes a course identified by its CourseId.
 */
public class DeleteCourseCommand extends Command {
    public static final String COMMAND_WORD = "delete_course";
    public static final String MESSAGE_USAGE = "delete_course <COURSE_ID>";
    public static final String MESSAGE_SUCCESS = "Course \"%1$s\" deleted successfully.";
    public static final String MESSAGE_NOT_FOUND = "Error: Course not found. No course with that ID exists.";
    public static final String MESSAGE_INVALID_FORMAT = "Error: Invalid course id format. It should be CXXXX.";
    public static final String MESSAGE_STUDENTS_ENROLLED =
            "Error: Remove all students from the course before deleting.";
    public static final String MESSAGE_MISSING_ID = "Error: Missing course id.";

    private final CourseId courseId;

    public DeleteCourseCommand(CourseId courseId) {
        if (courseId == null) {
            throw new IllegalArgumentException(MESSAGE_MISSING_ID);
        }
        this.courseId = courseId;
    }

    @Override
    public CommandResult execute(Model model) {
        Course course = model.getCourseById(courseId);
        if (course == null) {
            return new CommandResult(MESSAGE_NOT_FOUND);
        }
        if (course.hasEnrolledStudents()) {
            return new CommandResult(MESSAGE_STUDENTS_ENROLLED);
        }
        model.deleteCourse(course);
        return new CommandResult(String.format(MESSAGE_SUCCESS, course.getName()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteCourseCommand that = (DeleteCourseCommand) o;
        if (courseId == null || that.courseId == null) {
            return false;
        }
        return courseId.equals(that.courseId);
    }

    @Override
    public int hashCode() {
        return courseId == null ? 0 : courseId.hashCode();
    }
}
