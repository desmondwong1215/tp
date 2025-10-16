package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.CourseBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddStudentCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_addStudentToCourse_success() throws CommandException {
        Person student = new PersonBuilder().withStudentId("S00001").build();
        Course course = new CourseBuilder().withCourseId("C0001").build();
        model.addPerson(student);
        model.addCourse(course);

        AddStudentCommand addStudentCommand = new AddStudentCommand(student.getStudentId(), course.getCourseId());
        CommandResult commandResult = addStudentCommand.execute(model);

        assertEquals(String.format(AddStudentCommand.MESSAGE_SUCCESS, student.getName(), student.getStudentId(), course.getName(), course.getCourseId()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateStudentInCourse_throwsCommandException() {
        Person student = new PersonBuilder().withStudentId("S00001").build();
        Course course = new CourseBuilder().withCourseId("C0001").build();
        model.addPerson(student);
        model.addCourse(course);
        course.getStudentList().add(student);

        AddStudentCommand addStudentCommand = new AddStudentCommand(student.getStudentId(), course.getCourseId());

        assertThrows(CommandException.class, String.format(AddStudentCommand.MESSAGE_DUPLICATE_STUDENT, "S00001"), () -> addStudentCommand.execute(model));
    }
}
