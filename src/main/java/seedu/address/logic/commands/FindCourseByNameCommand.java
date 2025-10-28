package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.course.CourseNameContainsKeywordsPredicate;

/**
 * Finds and lists all courses whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCourseByNameCommand extends Command {
    public static final String COMMAND_WORD = "find_course_by_name";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all courses whose names contain any of "
            + "the specified keywords (case-insensitive).\n"
            + "Parameters: COURSE_NAME\n"
            + "Example: " + COMMAND_WORD + " data science";

    private final CourseNameContainsKeywordsPredicate predicate;

    public FindCourseByNameCommand(CourseNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCourseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_COURSES_LISTED_OVERVIEW, model.getFilteredCourseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof FindCourseByNameCommand)) {
            return false;
        }
        FindCourseByNameCommand otherCommand = (FindCourseByNameCommand) other;
        return predicate.equals(otherCommand.predicate);
    }
}

