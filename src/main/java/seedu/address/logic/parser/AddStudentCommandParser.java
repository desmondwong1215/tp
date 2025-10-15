package seedu.address.logic.parser;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseId;
import seedu.address.model.person.StudentId;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {
    /**
     *  Parses the given {@code String} of arguments in the context of the AddStudentCommand
     *  and returns an AddStudentCommand object for execution/
     * @throws ParseException if the user input does not confrom the exepected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args);

        String[] arguments = argMultiMap.getPreamble().split("\\s+");

        if (arguments.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        StudentId studentId = ParserUtil.parseStudentId(arguments[0]);
        CourseId courseId = ParserUtil.parseCourseId(arguments[1]);

        return new AddStudentCommand(studentId, courseId);
    }
}
