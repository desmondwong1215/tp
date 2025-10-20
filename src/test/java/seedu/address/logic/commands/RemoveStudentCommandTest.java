package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.course.Course;
import seedu.address.model.person.Person;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.PersonBuilder;

public class RemoveStudentCommandTest {

    private final Model model = new ModelManager();

    @Test
    public void execute_removeStudentToCourse_success() throws CommandException {
        Person student = new PersonBuilder().withStudentId("S00001").build();
        Course course = new CourseBuilder().withCourseId("C0001").build();
        course.addStudent(student);
        model.addPerson(student);
        model.addCourse(course);

        RemoveStudentCommand removeStudentCommand =
                new RemoveStudentCommand(student.getStudentId(), course.getCourseId());
        CommandResult commandResult = removeStudentCommand.execute(model);

        assertEquals(
                String.format(
                        RemoveStudentCommand.MESSAGE_SUCCESS,
                        student.getName(),
                        student.getStudentId(),
                        course.getName(),
                        course.getCourseId()
                ),
                commandResult.getFeedbackToUser()
        );
    }

    @Test
    public void execute_studentNotInCourse_throwsCommandException() {
        Person student = new PersonBuilder().withStudentId("S00001").build();
        Course course = new CourseBuilder().withCourseId("C0001").build();
        model.addPerson(student);
        model.addCourse(course);

        RemoveStudentCommand removeStudentCommand =
                new RemoveStudentCommand(student.getStudentId(), course.getCourseId());

        assertThrows(
                CommandException.class,
                String.format(
                        RemoveStudentCommand.MESSAGE_STUDENT_NOT_IN_COURSE,
                        "S00001"
                ), () -> removeStudentCommand.execute(model)
        );
    }
}
