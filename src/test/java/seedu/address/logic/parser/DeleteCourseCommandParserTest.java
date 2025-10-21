package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseId;

public class DeleteCourseCommandParserTest {
    private final DeleteCourseCommandParser parser = new DeleteCourseCommandParser();

    @Test
    public void parse_validCourseId_success() throws Exception {
        DeleteCourseCommand command = (DeleteCourseCommand) parser.parse("C1234");
        assertEquals(new DeleteCourseCommand(new CourseId("C1234")), command);
    }

    @Test
    public void parse_invalidCourseIdFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("1234"));
        assertThrows(ParseException.class, () -> parser.parse("C12A4"));
        assertThrows(ParseException.class, () -> parser.parse("C12345"));
        assertThrows(ParseException.class, () -> parser.parse("C12"));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" "));
    }
}

