package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeregisterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new DeregisterCommand object.
 */
public class DeregisterCommandParser implements Parser<DeregisterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeregisterCommand
     * and returns a DeregisterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeregisterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeregisterCommand.MESSAGE_USAGE));
        }

        try {
            StudentId studentId = ParserUtil.parseStudentId(trimmedArgs);
            return new DeregisterCommand(studentId);
        } catch (ParseException pe) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS, pe);
        }
    }
}
