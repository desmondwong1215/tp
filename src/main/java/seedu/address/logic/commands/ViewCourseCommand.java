package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSES;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.person.StudentId;

/**
 * Lists all courses in eduBase to the user.
 * Can optionally filter by student ID.
 */
public class ViewCourseCommand extends Command {

    public static final String COMMAND_WORD = "view_courses";

    public static final String MESSAGE_SUCCESS = "Listed all courses";
    public static final String MESSAGE_SUCCESS_FILTERED = "Listed all courses for student: %1$s";
    public static final String MESSAGE_NO_COURSES = "No courses have been created yet.";
    public static final String MESSAGE_NO_COURSES_FOR_STUDENT = "Student %1$s is not enrolled in any courses.";

    private final StudentId studentId;

    public ViewCourseCommand() {
        this.studentId = null;
    }

    public ViewCourseCommand(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (studentId == null) {
            model.updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
            return model.getFilteredCourseList().isEmpty()
                    ? new CommandResult(MESSAGE_NO_COURSES)
                    : new CommandResult(MESSAGE_SUCCESS);
        } else {
            Predicate<Course> predicate = course -> course.getStudentIds().contains(studentId.getValue());
            model.updateFilteredCourseList(predicate);
            return model.getFilteredCourseList().isEmpty()
                    ? new CommandResult(String.format(MESSAGE_NO_COURSES_FOR_STUDENT, studentId))
                    : new CommandResult(String.format(MESSAGE_SUCCESS_FILTERED, studentId));
        }
    }
}
