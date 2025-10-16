package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCourseByNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseNameContainsKeywordsPredicate;

import java.util.Arrays;

class FindCourseByNameCommandParserTest {
    private final FindCourseByNameCommandParser parser = new FindCourseByNameCommandParser();

    @Test
    void parse_validArgs_returnsFindCourseByNameCommand() throws Exception {
        FindCourseByNameCommand expectedCommand = new FindCourseByNameCommand(
                new CourseNameContainsKeywordsPredicate(Arrays.asList("Data", "Science")));
        assertEquals(expectedCommand, parser.parse("Data Science"));
    }

    @Test
    void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("   "));
    }
}

