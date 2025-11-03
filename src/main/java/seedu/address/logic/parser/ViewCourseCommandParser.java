package seedu.address.logic.parser;

import seedu.address.logic.commands.ViewCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new ViewCourseCommand object
 */
public class ViewCourseCommandParser implements Parser<ViewCourseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCourseCommand
     * and returns a ViewCourseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCourseCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            return new ViewCourseCommand();
        }

        StudentId studentId = ParserUtil.parseStudentId(trimmedArgs);
        return new ViewCourseCommand(studentId);
    }
}
