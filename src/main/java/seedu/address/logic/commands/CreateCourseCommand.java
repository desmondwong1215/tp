package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

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
            + PREFIX_NAME + "NAME "
            + PREFIX_ID + "ID\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "English "
            + PREFIX_ID + "ES2660";

    public static final String MESSAGE_SUCCESS = "New course created: %1$s";

    public static final String MESSAGE_DUPLICATE_COURSE_ID = "This course id already exists in the course book";

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

        if (model.hasCourse(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_COURSE_ID);
        }

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
