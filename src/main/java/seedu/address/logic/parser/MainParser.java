package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CreateCourseCommand;
import seedu.address.logic.commands.DeleteCourseCommand;
import seedu.address.logic.commands.DeregisterCommand;
import seedu.address.logic.commands.EditCourseCommand;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCourseByNameCommand;
import seedu.address.logic.commands.FindStudentByIdCommand;
import seedu.address.logic.commands.FindStudentByNameCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RegisterCommand;
import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.logic.commands.ViewCourseCommand;
import seedu.address.logic.commands.ViewCourseDetailsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class MainParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(MainParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        return switch (commandWord) {
        case AddStudentCommand.COMMAND_WORD -> new AddStudentCommandParser().parse(arguments);
        case RemoveStudentCommand.COMMAND_WORD -> new RemoveStudentCommandParser().parse(arguments);
        case RegisterCommand.COMMAND_WORD -> new RegisterCommandParser().parse(arguments);
        case EditCourseCommand.COMMAND_WORD -> new EditCourseCommandParser().parse(arguments);
        case EditStudentCommand.COMMAND_WORD -> new EditStudentCommandParser().parse(arguments);
        case DeregisterCommand.COMMAND_WORD -> new DeregisterCommandParser().parse(arguments);
        case ClearCommand.COMMAND_WORD -> new ClearCommand();
        case FindStudentByNameCommand.COMMAND_WORD -> new FindStudentByNameCommandParser().parse(arguments);
        case FindStudentByIdCommand.COMMAND_WORD -> new FindStudentByIdCommandParser().parse(arguments);
        case CreateCourseCommand.COMMAND_WORD -> new CreateCourseCommandParser().parse(arguments);
        case ListCommand.COMMAND_WORD -> new ListCommand();
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        case HelpCommand.COMMAND_WORD -> new HelpCommand();
        case DeleteCourseCommand.COMMAND_WORD -> new DeleteCourseCommandParser().parse(arguments);
        case ViewCourseCommand.COMMAND_WORD -> new ViewCourseCommandParser().parse(arguments);
        case FindCourseByNameCommand.COMMAND_WORD -> new FindCourseByNameCommandParser().parse(arguments);
        case ViewCourseDetailsCommand.COMMAND_WORD -> new ViewCourseDetailsCommandParser().parse(arguments);
        default -> {
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
        };
    }
}
