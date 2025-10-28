package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code RegisterCommand}.
 */
public class RegisterCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalCourseBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getCourseBook(), new UserPrefs());

        StudentId expectedId = ((AddressBook) expectedModel.getAddressBook()).getLatestStudentId();
        Person expectedPerson = new Person(
                validPerson.getName(),
                validPerson.getPhone(),
                validPerson.getGender(),
                expectedId
        );
        expectedModel.addPerson(expectedPerson);

        assertCommandSuccess(
                new RegisterCommand(
                        validPerson.getName(),
                        validPerson.getPhone(),
                        validPerson.getGender()
                ),
                model,
                String.format(RegisterCommand.MESSAGE_SUCCESS, Messages.format(expectedPerson)),
                expectedModel);
    }
}
