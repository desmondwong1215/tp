package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalCourses;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewCourseCommand.
 */
public class ViewCourseCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        // Set up model with both address book and course book populated
        model = new ModelManager(
                TypicalPersons.getTypicalAddressBook(),
                TypicalCourses.getTypicalCourseBook(),
                new UserPrefs()
        );
        expectedModel = new ModelManager(
                model.getAddressBook(),
                model.getCourseBook(),
                new UserPrefs()
        );
    }

    @Test
    public void execute_viewCourse_showsAllCourses() {
        assertCommandSuccess(new ViewCourseCommand(), model,
                ViewCourseCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
