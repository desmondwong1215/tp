package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ViewCourseDetailsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseId;

/**
 * Parses input arguments and creates a new ViewCourseDetailsCommand object.
 */
public class ViewCourseDetailsCommandParser implements Parser<ViewCourseDetailsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCourseDetailsCommand
     * and returns a ViewCourseDetailsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCourseDetailsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCourseDetailsCommand.MESSAGE_USAGE));
        }

        try {
            CourseId courseId = ParserUtil.parseCourseId(trimmedArgs);
            return new ViewCourseDetailsCommand(courseId);
        } catch (ParseException pe) {
            // Improve feedback by specifying the constraint violation
            throw new ParseException(CourseId.MESSAGE_CONSTRAINTS, pe);
        }
    }
}
