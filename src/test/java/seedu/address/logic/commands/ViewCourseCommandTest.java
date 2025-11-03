package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.CourseBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.StudentId;
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

    @Test
    public void execute_emptyCourseBook_showsNoCourses() {
        Model emptyModel = new ModelManager(new AddressBook(), new CourseBook(), new UserPrefs());
        Model emptyExpectedModel = new ModelManager(new AddressBook(), new CourseBook(), new UserPrefs());
        assertCommandSuccess(new ViewCourseCommand(), emptyModel,
                ViewCourseCommand.MESSAGE_NO_COURSES, emptyExpectedModel);
    }

    @Test
    public void execute_viewCourseWithStudentId_noCoursesForStudent() {
        StudentId studentId = ALICE.getStudentId();
        String expectedMessage = String.format(ViewCourseCommand.MESSAGE_NO_COURSES_FOR_STUDENT, studentId);
        expectedModel.updateFilteredCourseList(course -> course.getStudentIds().contains(studentId.getValue()));
        assertCommandSuccess(new ViewCourseCommand(studentId), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_viewCourseWithStudentId_showsCoursesForStudent() {
        // Create fresh models with separate course books
        Model testModel = new ModelManager(
                TypicalPersons.getTypicalAddressBook(),
                TypicalCourses.getTypicalCourseBook(),
                new UserPrefs()
        );
        Model testExpectedModel = new ModelManager(
                TypicalPersons.getTypicalAddressBook(),
                TypicalCourses.getTypicalCourseBook(),
                new UserPrefs()
        );

        // Enroll ALICE in some courses
        testModel.getCourseBook().getCourseList().get(0).addStudent(ALICE);
        testModel.getCourseBook().getCourseList().get(1).addStudent(ALICE);

        testExpectedModel.getCourseBook().getCourseList().get(0).addStudent(ALICE);
        testExpectedModel.getCourseBook().getCourseList().get(1).addStudent(ALICE);

        StudentId studentId = ALICE.getStudentId();
        String expectedMessage = String.format(ViewCourseCommand.MESSAGE_SUCCESS_FILTERED, studentId);
        testExpectedModel.updateFilteredCourseList(course -> course.getStudentIds().contains(studentId.getValue()));
        assertCommandSuccess(new ViewCourseCommand(studentId), testModel, expectedMessage, testExpectedModel);
    }
}
