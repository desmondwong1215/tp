package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.model.course.CourseId;
import seedu.address.model.person.StudentId;

public class AddStudentCommandParserTest {

    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_validArgs_returnsAddStudentCommand() {
        assertParseSuccess(parser, "S00001 C0001", new AddStudentCommand(new StudentId("S00001"), new CourseId("C0001")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
