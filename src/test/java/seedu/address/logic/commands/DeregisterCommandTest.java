package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.TypicalCourses;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeregisterCommand}.
 */
public class DeregisterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), TypicalCourses.getTypicalCourseBook(),
            new UserPrefs());

    @Test
    public void execute_validStudentIdUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(0);
        StudentId targetId = personToDelete.getStudentId();
        DeregisterCommand deregisterCommand = new DeregisterCommand(targetId);

        String expectedMessage = String.format(DeregisterCommand.MESSAGE_DEREGISTER_SUCCESS,
                personToDelete.getName(), targetId);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getCourseBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deregisterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIdUnfilteredList_throwsCommandException() {
        StudentId invalidStudentId = new StudentId("S99999");
        DeregisterCommand deregisterCommand = new DeregisterCommand(invalidStudentId);

        assertCommandFailure(deregisterCommand, model,
                String.format(DeregisterCommand.MESSAGE_STUDENT_NOT_FOUND, invalidStudentId));
    }

    @Test
    public void execute_validStudentIdFilteredList_success() {
        Person personToDelete = model.getAddressBook().getPersonList().get(0);
        StudentId targetId = personToDelete.getStudentId();

        model.updateFilteredPersonList(person -> person.equals(personToDelete));

        assertEquals(1, model.getFilteredPersonList().size());
        assertTrue(model.getFilteredPersonList().contains(personToDelete));

        DeregisterCommand deregisterCommand = new DeregisterCommand(targetId);

        String expectedMessage = String.format(DeregisterCommand.MESSAGE_DEREGISTER_SUCCESS,
                personToDelete.getName(), targetId);

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getCourseBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        expectedModel.updateFilteredPersonList(person -> person.equals(personToDelete));

        assertCommandSuccess(deregisterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        StudentId firstStudentId = new StudentId("S00001");
        StudentId secondStudentId = new StudentId("S00002");
        DeregisterCommand deregisterFirstCommand = new DeregisterCommand(firstStudentId);
        DeregisterCommand deregisterSecondCommand = new DeregisterCommand(secondStudentId);

        // same object -> returns true
        assertTrue(deregisterFirstCommand.equals(deregisterFirstCommand));

        // same values -> returns true
        DeregisterCommand deregisterFirstCommandCopy = new DeregisterCommand(firstStudentId);
        assertTrue(deregisterFirstCommand.equals(deregisterFirstCommandCopy));

        // different types -> returns false
        assertFalse(deregisterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deregisterFirstCommand.equals(null));

        // different student ID -> returns false
        assertFalse(deregisterFirstCommand.equals(deregisterSecondCommand));
    }

    @Test
    public void toStringMethod() {
        StudentId targetId = new StudentId("S00001");
        DeregisterCommand deregisterCommand = new DeregisterCommand(targetId);
        String expected = DeregisterCommand.class.getCanonicalName() + "{targetId=" + targetId + "}";
        assertEquals(expected, deregisterCommand.toString());
    }
}
