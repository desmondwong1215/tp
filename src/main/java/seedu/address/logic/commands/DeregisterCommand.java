package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Deregisters (deletes) a student from the system by their Student ID.
 */
public class DeregisterCommand extends Command {

    public static final String COMMAND_WORD = "deregister";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deregisters a student identified by their Student ID.\n"
            + "Parameters: STUDENT_ID (e.g. S00001)\n"
            + "Example: " + COMMAND_WORD + " S00001";

    public static final String MESSAGE_DEREGISTER_SUCCESS = "Student '%1$s' (%2$s) deregistered successfully.";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "No student found with ID: %1$s";
    public static final String MESSAGE_STUDENT_IN_COURSE = "Student %1$s is still enrolled in one of the course.";

    private final StudentId targetId;

    /**
     * Constructs a {@code DeregisterCommand} to deregister the student with the specified student ID.
     *
     * @param targetId the student ID of the student to be deregistered
     * @throws NullPointerException if the targetId is null
     */
    public DeregisterCommand(StudentId targetId) {
        requireNonNull(targetId);
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person toDelete = lastShownList.stream()
                .filter(p -> p.getStudentId().equals(targetId))
                .findFirst()
                .orElse(null);

        if (toDelete == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND, targetId));
        }

        if (model.checkStudentInAllCourse(toDelete)) {
            throw new CommandException(String.format(MESSAGE_STUDENT_IN_COURSE, targetId));
        }

        model.deletePerson(toDelete);
        return new CommandResult(String.format(MESSAGE_DEREGISTER_SUCCESS,
                toDelete.getName(), toDelete.getStudentId()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeregisterCommand
                && targetId.equals(((DeregisterCommand) other).targetId));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetId", targetId)
                .toString();
    }
}
