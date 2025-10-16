package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.address.logic.commands.RegisterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new RegisterCommand object.
 */
public class RegisterCommandParser implements Parser<RegisterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RegisterCommand
     * and returns a RegisterCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public RegisterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_GENDER);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_GENDER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RegisterCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_GENDER);

        Name name = ParserUtil.parseStudentName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());

        return new RegisterCommand(name, phone, gender);
    }

    /**
     * Returns true if all prefixes are present and have non-empty values.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
