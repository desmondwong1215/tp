package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_ID_DESC_C1010;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_ID_DESC_C2660;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_NAME_DESC_ENGLISH;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_NAME_DESC_MATH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COURSE_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COURSE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_ID_C1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_NAME_ENGLISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalCourses.CS1010;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CreateCourseCommand;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.CourseName;
import seedu.address.testutil.CourseBuilder;

public class CreateCourseCommandParserTest {

    private CreateCourseCommandParser parser = new CreateCourseCommandParser();

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedCourseString = COURSE_NAME_DESC_ENGLISH + COURSE_ID_DESC_C2660;

        // multiple course names
        assertParseFailure(parser, COURSE_NAME_DESC_ENGLISH + validExpectedCourseString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple course id
        assertParseFailure(parser, COURSE_ID_DESC_C2660 + validExpectedCourseString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedCourseString + COURSE_NAME_DESC_ENGLISH + COURSE_ID_DESC_C2660
                        + validExpectedCourseString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ID));

        // invalid value followed by valid value

        // invalid course id
        assertParseFailure(parser, INVALID_COURSE_ID_DESC + validExpectedCourseString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // valid value followed by invalid value

        // invalid course id
        assertParseFailure(parser, validExpectedCourseString + INVALID_COURSE_ID_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Course expectedCourse = new CourseBuilder(CS1010).withTags().build();
        assertParseSuccess(parser, COURSE_NAME_DESC_MATH + COURSE_ID_DESC_C1010,
                new CreateCourseCommand(expectedCourse));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCourseCommand.MESSAGE_USAGE);

        // missing course name prefix
        assertParseFailure(parser, VALID_COURSE_NAME_ENGLISH + COURSE_ID_DESC_C2660,
                expectedMessage);

        // missing course id prefix
        assertParseFailure(parser, COURSE_NAME_DESC_MATH + VALID_COURSE_ID_C1010,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_COURSE_NAME_DESC + COURSE_ID_DESC_C1010,
                CourseName.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, COURSE_NAME_DESC_MATH + INVALID_COURSE_ID_DESC,
                CourseId.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_COURSE_NAME_DESC + INVALID_COURSE_ID_DESC,
                CourseName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + COURSE_NAME_DESC_MATH + COURSE_ID_DESC_C1010,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCourseCommand.MESSAGE_USAGE));
    }
}
