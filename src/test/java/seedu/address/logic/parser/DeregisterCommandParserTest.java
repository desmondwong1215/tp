package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeregisterCommand;
import seedu.address.model.person.StudentId;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeregisterCommand code. For example, inputs "S00001" and "S00001 abc" take the
 * same path through the DeregisterCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeregisterCommandParserTest {

    private DeregisterCommandParser parser = new DeregisterCommandParser();

    @Test
    public void parse_validArgs_returnsDeregisterCommand() {
        StudentId validStudentId = new StudentId("S00001");
        assertParseSuccess(parser, "S00001", new DeregisterCommand(validStudentId));

        // Test with different valid student ID
        StudentId anotherValidStudentId = new StudentId("S99999");
        assertParseSuccess(parser, "S99999", new DeregisterCommand(anotherValidStudentId));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid student ID format
        assertParseFailure(parser, "A12345",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeregisterCommand.MESSAGE_USAGE));

        // Empty input
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeregisterCommand.MESSAGE_USAGE));

        // Invalid format (not starting with S)
        assertParseFailure(parser, "12345",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeregisterCommand.MESSAGE_USAGE));

        // Invalid format (wrong length)
        assertParseFailure(parser, "S1234",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeregisterCommand.MESSAGE_USAGE));

        // Invalid format (too long)
        assertParseFailure(parser, "S123456",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeregisterCommand.MESSAGE_USAGE));

        // Invalid format (contains letters after S)
        assertParseFailure(parser, "SABC12",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeregisterCommand.MESSAGE_USAGE));
    }
}
