package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSES;

import seedu.address.model.Model;

/**
 * Lists all courses in eduBase to the user.
 */
public class ViewCourseCommand extends Command {

    public static final String COMMAND_WORD = "view_courses";

    public static final String MESSAGE_SUCCESS = "Listed all courses";
    public static final String MESSAGE_NO_COURSES = "No courses have been created yet.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);

        if (model.getFilteredCourseList().isEmpty()) {
            return new CommandResult(MESSAGE_NO_COURSES);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
