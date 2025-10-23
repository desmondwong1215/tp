package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCourseDetailsCommand;
import seedu.address.model.course.CourseId;

public class ViewCourseDetailsCommandParserTest {

    private ViewCourseDetailsCommandParser parser = new ViewCourseDetailsCommandParser();

    @Test
    public void parse_validArgs_returnsViewCourseDetailsCommand() {
        // No leading/trailing whitespaces
        CourseId expectedCourseId = new CourseId("C1234");
        assertParseSuccess(parser, "C1234", new ViewCourseDetailsCommand(expectedCourseId));

        // Leading/trailing whitespaces
        assertParseSuccess(parser, "  C5678  ", new ViewCourseDetailsCommand(new CourseId("C5678")));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCourseDetailsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_throwsParseException() {
        // Invalid format (not starting with C)
        assertParseFailure(parser, "1234", CourseId.MESSAGE_CONSTRAINTS);

        // Invalid format (wrong number of digits)
        assertParseFailure(parser, "C123", CourseId.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "C12345", CourseId.MESSAGE_CONSTRAINTS);

        // Invalid format (contains letters)
        assertParseFailure(parser, "C12A4", CourseId.MESSAGE_CONSTRAINTS);

        // Invalid format (lowercase c)
        assertParseFailure(parser, "c1234", CourseId.MESSAGE_CONSTRAINTS);
    }
}
