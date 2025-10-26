package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCourseAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COURSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COURSE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.CourseBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.EditCourseDescriptorBuilder;
import seedu.address.testutil.TypicalCourses;

public class EditCourseCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), TypicalCourses.getTypicalCourseBook(),
            new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Course editedCourse = new CourseBuilder().build();
        EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder(editedCourse).build();
        EditCourseCommand editStudentCommand = new EditCourseCommand(INDEX_FIRST_COURSE, descriptor);

        String expectedMessage = String.format(EditCourseCommand.MESSAGE_EDIT_COURSE_SUCCESS,
                Messages.format(editedCourse));

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook(), new UserPrefs(model.getUserPrefs())),
                new CourseBook(model.getCourseBook()),
                new UserPrefs(model.getUserPrefs())
        );
        expectedModel.setCourse(model.getFilteredCourseList().get(0), editedCourse);

        assertCommandSuccess(editStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastCourse = Index.fromOneBased(model.getFilteredCourseList().size());
        Course lastCourse = model.getFilteredCourseList().get(indexLastCourse.getZeroBased());

        CourseBuilder courseInList = new CourseBuilder(lastCourse);
        Course editedCourse = courseInList.withName("English").withCourseId("C1234")
                .build();

        EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder().withCourseName("English")
                .withCourseId("C1234").build();
        EditCourseCommand editCourseCommand = new EditCourseCommand(indexLastCourse, descriptor);

        String expectedMessage = String.format(EditCourseCommand.MESSAGE_EDIT_COURSE_SUCCESS,
                Messages.format(editedCourse));

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook(), new UserPrefs(model.getUserPrefs())),
                new CourseBook(model.getCourseBook()),
                new UserPrefs(model.getUserPrefs())
        );
        expectedModel.setCourse(lastCourse, editedCourse);

        assertCommandSuccess(editCourseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_FIRST_COURSE,
                new EditCourseCommand.EditCourseDescriptor());
        Course editedCourse = model.getFilteredCourseList().get(INDEX_FIRST_COURSE.getZeroBased());

        String expectedMessage = String.format(EditCourseCommand.MESSAGE_EDIT_COURSE_SUCCESS,
                Messages.format(editedCourse));

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook(), new UserPrefs(model.getUserPrefs())),
                new CourseBook(model.getCourseBook()),
                new UserPrefs(model.getUserPrefs())
        );

        assertCommandSuccess(editCourseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCourseAtIndex(model, INDEX_FIRST_COURSE);

        Course personInFilteredList = model.getFilteredCourseList().get(INDEX_FIRST_COURSE.getZeroBased());
        Course editedCourse = new CourseBuilder(personInFilteredList).withName("English").build();
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_FIRST_COURSE,
                new EditCourseDescriptorBuilder().withCourseName("English").build());

        String expectedMessage = String.format(EditCourseCommand.MESSAGE_EDIT_COURSE_SUCCESS,
                Messages.format(editedCourse));

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook(), new UserPrefs(model.getUserPrefs())),
                new CourseBook(model.getCourseBook()),
                new UserPrefs(model.getUserPrefs())
        );
        expectedModel.setCourse(model.getFilteredCourseList().get(0), editedCourse);

        assertCommandSuccess(editCourseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateCourseUnfilteredList_failure() {
        Course firstCourse = model.getFilteredCourseList().get(INDEX_FIRST_COURSE.getZeroBased());
        EditCourseCommand.EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder(firstCourse).build();
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_SECOND_COURSE, descriptor);

        assertCommandFailure(editCourseCommand, model, EditCourseCommand.MESSAGE_DUPLICATE_COURSE);
    }

    @Test
    public void execute_duplicateCourseFilteredList_failure() {
        showCourseAtIndex(model, INDEX_FIRST_COURSE);

        // edit person in filtered list into a duplicate in address book
        Course courseInList = model.getCourseBook().getCourseList().get(INDEX_SECOND_COURSE.getZeroBased());
        EditCourseCommand editCourseCommand = new EditCourseCommand(INDEX_FIRST_COURSE,
                new EditCourseDescriptorBuilder(courseInList).build());

        assertCommandFailure(editCourseCommand, model, EditCourseCommand.MESSAGE_DUPLICATE_COURSE);
    }

    @Test
    public void execute_invalidCourseIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCourseList().size() + 1);
        EditCourseCommand.EditCourseDescriptor descriptor = new EditCourseDescriptorBuilder()
                .withCourseName("English").build();
        EditCourseCommand editCourseCommand = new EditCourseCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCourseCommand, model, Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of course book
     */
    @Test
    public void execute_invalidCourseIndexFilteredList_failure() {
        showCourseAtIndex(model, INDEX_FIRST_COURSE);
        Index outOfBoundIndex = INDEX_SECOND_COURSE;
        // ensures that outOfBoundIndex is still in bounds of course book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCourseBook().getCourseList().size());

        EditCourseCommand editCourseCommand = new EditCourseCommand(outOfBoundIndex,
                new EditCourseDescriptorBuilder().withCourseName("English").build());

        assertCommandFailure(editCourseCommand, model, Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCourseCommand standardCommand = new EditCourseCommand(INDEX_FIRST_COURSE,
                new EditCourseDescriptorBuilder().withCourseName("English").withCourseId("C1234").build());

        // same values -> returns true
        EditCourseDescriptor copyDescriptor = new EditCourseDescriptorBuilder()
                .withCourseName("English").withCourseId("C1234").build();
        EditCourseCommand commandWithSameValues = new EditCourseCommand(INDEX_FIRST_COURSE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCourseCommand(INDEX_SECOND_COURSE, copyDescriptor)));

        // different descriptor -> returns false
        EditCourseCommand.EditCourseDescriptor differentDescriptor = new EditCourseDescriptorBuilder()
                .withCourseName("Maths").withCourseId("C1212").build();
        assertFalse(standardCommand.equals(new EditCourseCommand(INDEX_FIRST_COURSE, differentDescriptor)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditCourseDescriptor editCourseDescriptor = new EditCourseDescriptor();
        EditCourseCommand editCourseCommand = new EditCourseCommand(index, editCourseDescriptor);
        String expected = EditCourseCommand.class.getCanonicalName() + "{index=" + index + ", editCourseDescriptor="
                + editCourseDescriptor + "}";
        assertEquals(expected, editCourseCommand.toString());
    }
}
