package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_ID_DESC_C1010;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_NAME_DESC_ENGLISH;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_NAME_DESC_MATH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COURSE_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COURSE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_ID_C1010;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_NAME_ENGLISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COURSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_COURSE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCourseCommand;
import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.CourseName;
import seedu.address.testutil.EditCourseDescriptorBuilder;

public class EditCourseCommandParserTest {


    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCourseCommand.MESSAGE_USAGE);

    private EditCourseCommandParser parser = new EditCourseCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COURSE_NAME_ENGLISH, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCourseCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COURSE_NAME_DESC_ENGLISH, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + COURSE_NAME_DESC_ENGLISH, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_COURSE_NAME_DESC,
                CourseName.MESSAGE_CONSTRAINTS); // invalid course name
        assertParseFailure(parser, "1" + INVALID_COURSE_ID_DESC,
                CourseId.MESSAGE_CONSTRAINTS); // invalid course id

        // invalid course id followed by valid course name
        assertParseFailure(parser, "1" + INVALID_COURSE_ID_DESC + COURSE_NAME_DESC_MATH,
                CourseId.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_COURSE_NAME_DESC + INVALID_COURSE_ID_DESC,
                CourseName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_COURSE;
        String userInput = targetIndex.getOneBased() + COURSE_NAME_DESC_ENGLISH + COURSE_ID_DESC_C1010;

        EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder()
                .withCourseName(VALID_COURSE_NAME_ENGLISH)
                .withCourseId(VALID_COURSE_ID_C1010)
                .build();
        EditCourseCommand expectedCommand = new EditCourseCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // courseName
        Index targetIndex = INDEX_THIRD_COURSE;
        String userInput = targetIndex.getOneBased() + COURSE_NAME_DESC_ENGLISH;
        EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder()
                .withCourseName(VALID_COURSE_NAME_ENGLISH)
                .build();
        EditCourseCommand expectedCommand = new EditCourseCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // courseId
        userInput = targetIndex.getOneBased() + COURSE_ID_DESC_C1010;
        descriptor = new EditCourseDescriptorBuilder().withCourseId(VALID_COURSE_ID_C1010).build();
        expectedCommand = new EditCourseCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // CreateCourseCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_COURSE_ID_DESC + COURSE_ID_DESC_C1010;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + COURSE_ID_DESC_C1010 + INVALID_COURSE_ID_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + COURSE_ID_DESC_C1010 + COURSE_NAME_DESC_MATH
                + COURSE_ID_DESC_C1010 + COURSE_NAME_DESC_MATH;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID, PREFIX_NAME));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_COURSE_ID_DESC + INVALID_COURSE_NAME_DESC
                + INVALID_COURSE_ID_DESC + INVALID_COURSE_NAME_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ID, PREFIX_NAME));
    }
}
