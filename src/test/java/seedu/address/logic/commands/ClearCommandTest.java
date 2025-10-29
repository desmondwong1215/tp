package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.CourseBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBookAndEmptyCourseBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_EMPTY, expectedModel);
    }

    @Test
    public void execute_nonEmptyBook_success() {
        Model nonEmptyAddrBookModel = new ModelManager(getTypicalAddressBook(), new CourseBook(),
                new UserPrefs());
        Model nonEmptyCourseBookModel = new ModelManager(new AddressBook(), getTypicalCourseBook(),
                new UserPrefs());
        Model bothNonEmptyModel = new ModelManager(new AddressBook(), getTypicalCourseBook(),
                new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(), new CourseBook(),
                new UserPrefs());

        assertCommandSuccess(new ClearCommand(), nonEmptyAddrBookModel, ClearCommand.MESSAGE_SUCCESS, expectedModel);
        assertCommandSuccess(new ClearCommand(), nonEmptyCourseBookModel, ClearCommand.MESSAGE_SUCCESS, expectedModel);
        assertCommandSuccess(new ClearCommand(), bothNonEmptyModel, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
