package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.stream.Stream;

import seedu.address.logic.commands.CreateCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.CourseName;

/**
 * Parses input arguments and creates a new CreateCourseCommand object
 */
public class CreateCourseCommandParser implements Parser<CreateCourseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateCourseCommand
     * and returns an CreateCourseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CreateCourseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCourseCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID);
        CourseName courseName = ParserUtil.parseCourseName(argMultimap.getValue(PREFIX_NAME).get());
        CourseId courseId = ParserUtil.parseCourseId(argMultimap.getValue(PREFIX_ID).get());
        return new CreateCourseCommand(new Course(courseName, courseId, new HashSet<>()));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
