package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseId;

/**
 * Parses input arguments and creates a new DeleteCourseCommand object
 */
public class DeleteCourseCommandParser implements Parser<DeleteCourseCommand> {
    @Override
    public DeleteCourseCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(DeleteCourseCommand.MESSAGE_MISSING_ID);
        }
        // Validate format: C followed by 4 digits
        if (!trimmedArgs.matches("C\\d{4}")) {
            throw new ParseException(DeleteCourseCommand.MESSAGE_INVALID_FORMAT);
        }
        CourseId courseId = new CourseId(trimmedArgs);
        return new DeleteCourseCommand(courseId);
    }
}
