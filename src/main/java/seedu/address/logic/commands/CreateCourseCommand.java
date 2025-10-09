package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.Course;

/**
 * Adds a course to the address book.
 */
public class CreateCourseCommand extends Command {

    public static final String COMMAND_WORD = "create_course";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a course to the course book. "
            + "Parameters: "
            + "NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + "English";

    public static final String MESSAGE_SUCCESS = "New course created: %1$s";

    private final Course toAdd;

    /**
     * Creates an CreateCourseCommand to add the specified {@code Course}
     */
    public CreateCourseCommand(Course course) {
        requireNonNull(course);
        toAdd = course;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addCourse(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateCourseCommand)) {
            return false;
        }

        CreateCourseCommand otherCreateCourseCommand = (CreateCourseCommand) other;
        return toAdd.equals(otherCreateCourseCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toCreateCourse", toAdd)
                .toString();
    }
}
