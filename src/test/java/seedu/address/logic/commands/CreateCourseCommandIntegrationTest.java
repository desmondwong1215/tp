package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;
import seedu.address.testutil.CourseBuilder;

public class CreateCourseCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalCourseBook(), new UserPrefs());
    }

    @Test
    public void execute_newCourse_success() {
        Course validCourse = new CourseBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getCourseBook(), new UserPrefs());
        expectedModel.addCourse(validCourse);

        assertCommandSuccess(new CreateCourseCommand(validCourse), model,
                String.format(CreateCourseCommand.MESSAGE_SUCCESS, Messages.format(validCourse)),
                expectedModel);
    }

    @Test
    public void execute_duplicateCourse_throwsCommandException() {
        Course courseInList = model.getCourseBook().getCourseList().get(0);
        assertCommandFailure(new CreateCourseCommand(courseInList), model,
                CreateCourseCommand.MESSAGE_DUPLICATE_COURSE_ID);
    }

}
