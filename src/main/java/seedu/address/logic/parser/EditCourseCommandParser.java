package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.EditCourseCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.EditCourseCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCourseCommand;
import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;

/**
 * Parses input arguments and creates a new EditCourseCommand object
 */
public class EditCourseCommandParser implements Parser<EditCourseCommand> {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    /**
     * Parses the given {@code String} of arguments in the context of the EditCourseCommand
     * and returns an EditCourseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCourseCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            logger.warning(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE),
                    pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ID);

        EditCourseDescriptor editCourseDescriptor = new EditCourseDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editCourseDescriptor.setCourseName(ParserUtil.parseCourseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            editCourseDescriptor.setCourseId(ParserUtil.parseCourseId(argMultimap.getValue(PREFIX_ID).get()));
        }
        if (!editCourseDescriptor.isAnyFieldEdited()) {
            logger.warning(MESSAGE_NOT_EDITED);
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditCourseCommand(index, editCourseDescriptor);
    }
}
