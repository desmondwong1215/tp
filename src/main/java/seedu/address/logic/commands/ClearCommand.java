package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.CourseBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book and course book have been cleared!";
    public static final String MESSAGE_EMPTY = "Both address book and course book are empty!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.getAddressBook().isEmpty() && model.getCourseBook().isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY);
        }

        model.setAddressBook(new AddressBook());
        model.setCourseBook(new CourseBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
