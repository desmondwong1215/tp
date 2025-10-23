package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseId;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class RemoveStudentCommandParser implements Parser<RemoveStudentCommand> {
    /**
     *  Parses the given {@code String} of arguments in the context of the RemoveStudentCommand
     *  and returns an RemoveStudentCommand object for execution/
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args);

        String[] arguments = argMultiMap.getPreamble().split("\\s+");

        if (arguments.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveStudentCommand.MESSAGE_USAGE));
        }

        StudentId studentId = ParserUtil.parseStudentId(arguments[0]);
        CourseId courseId = ParserUtil.parseCourseId(arguments[1]);

        return new RemoveStudentCommand(studentId, courseId);
    }
}
