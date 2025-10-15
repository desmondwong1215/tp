package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindStudentByIdCommand;
import seedu.address.model.person.IdMatchesKeywordsPredicate;
import seedu.address.model.person.StudentId;

public class FindStudentByIdCommandParserTest {

    private FindStudentByIdCommandParser parser = new FindStudentByIdCommandParser();

    private final StudentId first = new StudentId("S00001");
    private final StudentId second = new StudentId("S00002");

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindStudentByIdCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidStudentId_throwsParseException() {
        // Enter with small capital letter s.
        assertParseFailure(parser, "s00001", StudentId.MESSAGE_CONSTRAINTS);

        // Less than 5 digits provided.
        assertParseFailure(parser, "S0001", StudentId.MESSAGE_CONSTRAINTS);

        // More than 5 digits provided.
        assertParseFailure(parser, "S000001", StudentId.MESSAGE_CONSTRAINTS);

        // Enter course code.
        assertParseFailure(parser, "C0001", StudentId.MESSAGE_CONSTRAINTS);

        // Replace S with other alphabet.
        assertParseFailure(parser, "T0001", StudentId.MESSAGE_CONSTRAINTS);

        // Enter with random string.
        assertParseFailure(parser, "random string", StudentId.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindStudentByIdCommand expectedFindStudentByIdCommand =
                new FindStudentByIdCommand(new IdMatchesKeywordsPredicate(Arrays.asList(first, second)));
        assertParseSuccess(parser, "S00001 S00002", expectedFindStudentByIdCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n S00001 \n \t S00002  \t", expectedFindStudentByIdCommand);
    }

}
