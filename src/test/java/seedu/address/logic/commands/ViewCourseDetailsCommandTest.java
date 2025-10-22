package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCourses.CS1010;
import static seedu.address.testutil.TypicalCourses.MA1521;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.testutil.TypicalCourses;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewCourseDetailsCommand.
 */
public class ViewCourseDetailsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        // Initialize model with typical persons and courses

        model = new ModelManager(
                TypicalPersons.getTypicalAddressBook(),
                TypicalCourses.getTypicalCourseBook(),
                new UserPrefs());

        // expectedModel should reflect the same state initially
        expectedModel = new ModelManager(
                model.getAddressBook(),
                model.getCourseBook(),
                new UserPrefs());
    }

    @Test
    public void execute_validCourseIdExistingCourseWithStudents_success() {

        Course courseToShow = model.getCourseById(CS1010.getCourseId());
        ViewCourseDetailsCommand command = new ViewCourseDetailsCommand(CS1010.getCourseId());
        courseToShow.addStudent(ALICE);
        courseToShow.addStudent(BENSON);


        String studentListString = courseToShow.getStudentList().asUnmodifiableObservableList()
                .stream()
                .map(student -> "  - " + student.getName().fullName + " (" + student.getStudentId().getValue() + ")")
                .collect(Collectors.joining("\n"));

        // Check that the student list is not empty, as assumed
        assertFalse(studentListString.isEmpty(), "Test setup assumption failed: CS1010 should have students.");

        String expectedMessage = String.format(ViewCourseDetailsCommand.MESSAGE_SUCCESS,
                courseToShow.getName().fullName,
                courseToShow.getCourseId().value,
                studentListString);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validCourseIdExistingCourseWithoutStudents_success() {
        // Assuming MA1521 has no students enrolled in the typical setup
        Course courseToShow = model.getCourseById(MA1521.getCourseId());
        ViewCourseDetailsCommand command = new ViewCourseDetailsCommand(MA1521.getCourseId());

        // Check that the student list is empty, as assumed
        assertTrue(courseToShow.getStudentList().asUnmodifiableObservableList().isEmpty(),
                "Test setup assumption failed: MA1521 should not have students.");

        String expectedMessage = String.format(ViewCourseDetailsCommand.MESSAGE_SUCCESS,
                courseToShow.getName().fullName,
                courseToShow.getCourseId().value,
                ViewCourseDetailsCommand.MESSAGE_NO_STUDENTS); // Use the specific message for no students

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidCourseIdNonExistentCourse_throwsCommandException() {
        CourseId nonExistentCourseId = new CourseId("C9999"); // Assuming C9999 does not exist
        ViewCourseDetailsCommand command = new ViewCourseDetailsCommand(nonExistentCourseId);

        String expectedMessage = String.format(ViewCourseDetailsCommand.MESSAGE_COURSE_NOT_FOUND, nonExistentCourseId);

        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        CourseId courseId1 = new CourseId("C1234");
        CourseId courseId2 = new CourseId("C5678");

        ViewCourseDetailsCommand viewCommand1 = new ViewCourseDetailsCommand(courseId1);
        ViewCourseDetailsCommand viewCommand1Copy = new ViewCourseDetailsCommand(courseId1);
        ViewCourseDetailsCommand viewCommand2 = new ViewCourseDetailsCommand(courseId2);

        // same object -> returns true
        assertTrue(viewCommand1.equals(viewCommand1));

        // same values -> returns true
        assertTrue(viewCommand1.equals(viewCommand1Copy));

        // different types -> returns false
        assertFalse(viewCommand1.equals(1));

        // null -> returns false
        assertFalse(viewCommand1.equals(null));

        // different course ID -> returns false
        assertFalse(viewCommand1.equals(viewCommand2));
    }

    @Test
    public void toStringMethod() {
        CourseId targetId = new CourseId("C1234");
        ViewCourseDetailsCommand command = new ViewCourseDetailsCommand(targetId);
        String expected = ViewCourseDetailsCommand.class.getCanonicalName() + "{targetId=" + targetId + "}";
        assertEquals(expected, command.toString());
    }
}