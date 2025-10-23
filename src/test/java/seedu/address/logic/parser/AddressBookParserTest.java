package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COURSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CreateCourseCommand;
import seedu.address.logic.commands.DeleteCourseCommand;
import seedu.address.logic.commands.DeregisterCommand;
import seedu.address.logic.commands.EditCourseCommand;
import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCourseByNameCommand;
import seedu.address.logic.commands.FindStudentByIdCommand;
import seedu.address.logic.commands.FindStudentByNameCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RegisterCommand;
import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.logic.commands.ViewCourseCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.Course;
import seedu.address.model.person.IdMatchesKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.CourseUtil;
import seedu.address.testutil.EditCourseDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonCourseUtil;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        Course course = new CourseBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(
                PersonCourseUtil.getAddCommand(person, course)
        );
        assertEquals(
                new AddStudentCommand(person.getStudentId(), course.getCourseId()),
                command
        );
    }

    @Test
    public void parseCommand_register() throws Exception {
        Person person = new PersonBuilder().build();
        RegisterCommand command = (RegisterCommand) parser.parseCommand(PersonUtil.getRegisterCommand(person));
        assertEquals(new RegisterCommand(person.getName(), person.getPhone(), person.getGender()), command);
    }

    @Test
    public void parseCommand_deregister() throws Exception {
        StudentId studentId = new StudentId("S00001");
        DeregisterCommand command = (DeregisterCommand) parser.parseCommand(
                DeregisterCommand.COMMAND_WORD + " " + studentId);
        assertEquals(new DeregisterCommand(studentId), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_editStudent() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditStudentCommand command = (EditStudentCommand) parser.parseCommand(EditStudentCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditStudentCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_editCourse() throws Exception {
        Course course = new CourseBuilder().build();
        EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder(course).build();
        EditCourseCommand command = (EditCourseCommand) parser.parseCommand(EditCourseCommand.COMMAND_WORD
                + " " + INDEX_FIRST_COURSE.getOneBased() + " " + CourseUtil.getEditCourseDescriptorDetails(descriptor));
        assertEquals(new EditCourseCommand(INDEX_FIRST_COURSE, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindStudentByNameCommand command = (FindStudentByNameCommand) parser.parseCommand(
                FindStudentByNameCommand.COMMAND_WORD
                        + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStudentByNameCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findById() throws Exception {
        List<String> keywords = Arrays.asList("S00001", "S00002", "S00003");
        List<StudentId> ids = keywords.stream().map(StudentId::new).toList();
        FindStudentByIdCommand command = (FindStudentByIdCommand) parser.parseCommand(
                FindStudentByIdCommand.COMMAND_WORD
                        + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStudentByIdCommand(new IdMatchesKeywordsPredicate(ids)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_deleteCourse() throws Exception {
        assertTrue(parser.parseCommand(DeleteCourseCommand.COMMAND_WORD + " C1234") instanceof DeleteCourseCommand);
    }

    @Test
    public void parseCommand_viewCourse() throws Exception {
        assertTrue(parser.parseCommand(ViewCourseCommand.COMMAND_WORD) instanceof ViewCourseCommand);
    }

    @Test
    public void parseCommand_findCourseByName() throws Exception {
        assertTrue(parser.parseCommand(FindCourseByNameCommand.COMMAND_WORD + " Data")
                instanceof FindCourseByNameCommand);
    }

    @Test
    public void parseCommand_createCourse() throws Exception {
        assertTrue(parser.parseCommand(CreateCourseCommand.COMMAND_WORD + " n/Math id/C1234")
                instanceof CreateCourseCommand);
    }

    @Test
    public void parseCommand_addStudent() throws Exception {
        assertTrue(parser.parseCommand(AddStudentCommand.COMMAND_WORD + " S00001 C1234")
                instanceof AddStudentCommand);
    }

    @Test
    public void parseCommand_removeStudent() throws Exception {
        assertTrue(parser.parseCommand(RemoveStudentCommand.COMMAND_WORD + " S00001 C1231")
                instanceof RemoveStudentCommand);
    }
}
