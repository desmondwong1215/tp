package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCourseByNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCourseByNameCommand object
 */
public class FindCourseByNameCommandParser implements Parser<FindCourseByNameCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCourseByNameCommand
     * and returns a FindCourseByNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCourseByNameCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCourseByNameCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");
        return new FindCourseByNameCommand(new CourseNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}

