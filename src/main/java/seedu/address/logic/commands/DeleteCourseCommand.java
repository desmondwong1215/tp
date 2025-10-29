package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
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
    public static final String MESSAGE_NOT_FOUND = "Error: Course not found. No course with id \"%1$s\" exists.";
    public static final String MESSAGE_INVALID_FORMAT = "Error: Invalid course id format. It should be CXXXX "
            + "(e.g., C1234).";
    public static final String MESSAGE_STUDENTS_ENROLLED =
            "Error: Remove all students from the course before deleting.";
    public static final String MESSAGE_MISSING_ID = "Error: Missing course id.";

    private final CourseId courseId;

    /**
     * Constructs a DeleteCourseCommand with the specified {@link CourseId}.
     *
     * @param courseId The ID of the course to delete. Must not be null.
     */
    public DeleteCourseCommand(CourseId courseId) {
        assert courseId != null : "CourseId cannot be null";
        this.courseId = courseId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model cannot be null";
        Course course = model.getCourseById(courseId);
        if (course == null) {
            throw new CommandException(String.format(MESSAGE_NOT_FOUND, courseId));
        }
        if (course.hasEnrolledStudents()) {
            throw new CommandException(MESSAGE_STUDENTS_ENROLLED);
        }
        model.deleteCourse(course);
        if (course.getName() == null) {
            return new CommandResult(MESSAGE_SUCCESS);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, course.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCourseCommand)) {
            return false;
        }

        DeleteCourseCommand otherCommand = (DeleteCourseCommand) other;
        return java.util.Objects.equals(courseId, otherCommand.courseId);
    }

    @Override
    public int hashCode() {
        return courseId.hashCode();
    }
}
