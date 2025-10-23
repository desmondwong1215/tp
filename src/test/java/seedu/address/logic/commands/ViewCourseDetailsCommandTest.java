package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCourses.CS1010;
import static seedu.address.testutil.TypicalCourses.MA1521;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.person.UniquePersonList;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewCourseDetailsCommand.
 */
public class ViewCourseDetailsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalCourseBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalCourseBook(), new UserPrefs());
    }

    @Test
    public void execute_validCourseIdExistingCourseWithStudents_success() throws CommandException {
        Course courseToShow = new Course(
                CS1010.getName(),
                CS1010.getCourseId(),
                new UniquePersonList(),
                CS1010.getTags()
        );
        courseToShow.addStudent(ALICE);
        courseToShow.addStudent(BENSON);

        model.setCourse(model.getCourseById(CS1010.getCourseId()), courseToShow);
        expectedModel.setCourse(expectedModel.getCourseById(CS1010.getCourseId()), courseToShow);

        ViewCourseDetailsCommand command = new ViewCourseDetailsCommand(CS1010.getCourseId());

        String expectedStudents = String.join("\n",
                "  - " + ALICE.getName().fullName + " (" + ALICE.getStudentId().getValue() + ")",
                "  - " + BENSON.getName().fullName + " (" + BENSON.getStudentId().getValue() + ")"
        );

        String expectedMessage = String.format(
                ViewCourseDetailsCommand.MESSAGE_SUCCESS,
                courseToShow.getName().fullName,
                courseToShow.getCourseId().value,
                expectedStudents
        );

        CommandResult result = command.execute(model);
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_validCourseIdExistingCourseWithoutStudents_success() throws CommandException {
        Course courseToShow = new Course(
                MA1521.getName(),
                MA1521.getCourseId(),
                new UniquePersonList(),
                MA1521.getTags()
        );

        model.setCourse(model.getCourseById(MA1521.getCourseId()), courseToShow);
        expectedModel.setCourse(expectedModel.getCourseById(MA1521.getCourseId()), courseToShow);

        ViewCourseDetailsCommand viewCommand = new ViewCourseDetailsCommand(MA1521.getCourseId());
        String expectedMessage = String.format(
                ViewCourseDetailsCommand.MESSAGE_SUCCESS,
                courseToShow.getName().fullName,
                courseToShow.getCourseId().value,
                ViewCourseDetailsCommand.MESSAGE_NO_STUDENTS
        );

        CommandResult result = viewCommand.execute(model);
        assertEquals(expectedMessage, result.getFeedbackToUser());
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
