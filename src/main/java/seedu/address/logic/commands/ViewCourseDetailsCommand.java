package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;

/**
 * Displays the details of a specific course identified using its CourseId.
 */
public class ViewCourseDetailsCommand extends Command {

    public static final String COMMAND_WORD = "view_course_details"; // Adjust if needed

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the details of a specific course, including enrolled students, using its Course ID.\n"
            + "Parameters: COURSE_ID (must be in the format C####, e.g., C1234)\n"
            + "Example: " + COMMAND_WORD + " C1234";

    public static final String MESSAGE_SUCCESS = "Filter applied.\nCourse: %1$s (%2$s)\nStudents enrolled:\n%3$s";
    public static final String MESSAGE_COURSE_NOT_FOUND = "No course found with ID: %1$s";
    public static final String MESSAGE_NO_STUDENTS = "No students enrolled in this course.";


    private final CourseId targetId;

    /**
     * Creates a ViewCourseDetailsCommand to view the specified {@code Course}.
     */
    public ViewCourseDetailsCommand(CourseId targetId) {
        requireNonNull(targetId);
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Course courseToShow = model.getCourseById(targetId);

        if (courseToShow == null) {
            throw new CommandException(String.format(MESSAGE_COURSE_NOT_FOUND, targetId));
        }

        model.updateFilteredCourseListForCourse(courseToShow);
        model.updateFilteredStudentListForCourse(courseToShow);

        String studentListString = courseToShow.getStudentList().asUnmodifiableObservableList()
                .stream()
                .map(student -> "  - " + student.getName().fullName + " (" + student.getStudentId().getValue() + ")")
                .collect(Collectors.joining("\n"));

        if (studentListString.isEmpty()) {
            studentListString = MESSAGE_NO_STUDENTS;
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS,
                courseToShow.getName().fullName,
                courseToShow.getCourseId().value,
                studentListString));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCourseDetailsCommand)) {
            return false;
        }

        ViewCourseDetailsCommand otherCommand = (ViewCourseDetailsCommand) other;
        return targetId.equals(otherCommand.targetId);
    }

    @Override
    public String toString() {
        return new seedu.address.commons.util.ToStringBuilder(this)
                .add("targetId", targetId)
                .toString();
    }
}
