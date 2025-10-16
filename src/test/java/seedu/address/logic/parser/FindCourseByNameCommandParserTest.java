package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCourseByNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.CourseNameContainsKeywordsPredicate;

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
