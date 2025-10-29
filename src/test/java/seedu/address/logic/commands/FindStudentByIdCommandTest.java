package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.IdMatchesKeywordsPredicate;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.TypicalCourses;

public class FindStudentByIdCommandTest {

    private final StudentId first = new StudentId("S00001");
    private final StudentId second = new StudentId("S00002");
    private final StudentId third = new StudentId("S00003");

    private Model model = new ModelManager(getTypicalAddressBook(), TypicalCourses.getTypicalCourseBook(),
            new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), TypicalCourses.getTypicalCourseBook(),
            new UserPrefs());

    @Test
    public void equals() {
        IdMatchesKeywordsPredicate firstPredicate =
                new IdMatchesKeywordsPredicate(Collections.singletonList(first));
        IdMatchesKeywordsPredicate secondPredicate =
                new IdMatchesKeywordsPredicate(Collections.singletonList(second));

        FindStudentByIdCommand findFirstCommand = new FindStudentByIdCommand(firstPredicate);
        FindStudentByIdCommand findSecondCommand = new FindStudentByIdCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStudentByIdCommand findFirstCommandCopy = new FindStudentByIdCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = FindStudentByIdCommand.MESSAGE_STUDENT_NOT_FOUND;
        IdMatchesKeywordsPredicate predicate = preparePredicate(new ArrayList<>());
        FindStudentByIdCommand command = new FindStudentByIdCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        IdMatchesKeywordsPredicate predicate = preparePredicate(new ArrayList<>(Arrays.asList(first, second, third)));
        FindStudentByIdCommand command = new FindStudentByIdCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        IdMatchesKeywordsPredicate predicate = new IdMatchesKeywordsPredicate(Arrays.asList(first));
        FindStudentByIdCommand findStudentByIdCommand = new FindStudentByIdCommand(predicate);
        String expected = FindStudentByIdCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findStudentByIdCommand.toString());
    }

    /**
     * Parses {@code arrayList} into a {@code IdMatchesKeywordsPredicate}.
     */
    private IdMatchesKeywordsPredicate preparePredicate(ArrayList<StudentId> arrayList) {
        return new IdMatchesKeywordsPredicate(arrayList);
    }
}
