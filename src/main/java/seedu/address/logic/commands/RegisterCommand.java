package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.AddressBook;

/**
 * Registers a new student in the system.
 */
public class RegisterCommand extends Command {

    public static final String COMMAND_WORD = "register";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Registers a new student. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_GENDER + "GENDER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_GENDER + "Male";

    public static final String MESSAGE_SUCCESS = "New student registered: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the system";

    private final Name name;
    private final Phone phone;
    private final Gender gender;

    /**
     * Creates a RegisterCommand with the specified details.
     */
    public RegisterCommand(Name name, Phone phone, Gender gender) {
        requireNonNull(name);
        requireNonNull(phone);
        requireNonNull(gender);
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Generate a new StudentId
        StudentId id = ((AddressBook) model.getAddressBook()).generateStudentId();
        Person toAdd = new Person(name, phone, gender, id);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RegisterCommand)) {
            return false;
        }

        RegisterCommand otherCmd = (RegisterCommand) other;
        return name.equals(otherCmd.name)
                && phone.equals(otherCmd.phone)
                && gender.equals(otherCmd.gender);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("gender", gender)
                .toString();
    }
}
