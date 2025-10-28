package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.IdMatchesKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose id matches any of the argument keywords.
 * Student id must match with the argument keyword exactly.
 */
public class FindStudentByIdCommand extends Command {

    public static final String COMMAND_WORD = "find_student_by_id";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose id matches any of "
            + "the specified id and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " S00001 S00003 S10000";

    private final IdMatchesKeywordsPredicate predicate;

    public FindStudentByIdCommand(IdMatchesKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        int matchedCount = model.getFilteredPersonList().size();

        if (matchedCount == 0) {
            return new CommandResult("Error: No student found.");
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, matchedCount));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindStudentByIdCommand)) {
            return false;
        }

        FindStudentByIdCommand otherFindStudentByIdCommand = (FindStudentByIdCommand) other;
        return predicate.equals(otherFindStudentByIdCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
