package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import seedu.address.logic.commands.FindStudentByIdCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.IdMatchesKeywordsPredicate;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new FindStudentByIdCommand object
 */
public class FindStudentByIdCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the FindStudentByIdCommand
     * and returns a FindStudentByIdCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStudentByIdCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStudentByIdCommand.MESSAGE_USAGE));
        }

        String[] idKeywords = trimmedArgs.split("\\s+");
        ArrayList<StudentId> studentIds = new ArrayList<>();

        try {
            for (String idKeyword: idKeywords) {
                StudentId studentId = ParserUtil.parseStudentId(idKeyword);
                studentIds.add(studentId);
            }
        } catch (ParseException pe) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS, pe);
        }


        return new FindStudentByIdCommand(new IdMatchesKeywordsPredicate(studentIds));
    }

}
