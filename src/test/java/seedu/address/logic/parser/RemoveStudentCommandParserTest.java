package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.model.course.CourseId;
import seedu.address.model.person.StudentId;

public class RemoveStudentCommandParserTest {

    private RemoveStudentCommandParser parser = new RemoveStudentCommandParser();

    @Test
    public void parse_validArgs_returnsAddStudentCommand() {
        assertParseSuccess(
                parser,
                "S00001 C0001",
                new RemoveStudentCommand(new StudentId("S00001"), new CourseId("C0001"))
        );
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveStudentCommand.MESSAGE_USAGE));
    }
}
