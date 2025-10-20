package seedu.address.testutil;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.model.course.Course;
import seedu.address.model.person.Person;

/**
 * A utility class to ensure easy testing for commands requiring both course and student.
 */
public class PersonCourseUtil {
    public static String getAddCommand(Person person, Course course) {
        return AddStudentCommand.COMMAND_WORD + " " + person.getStudentId() + " " + course.getCourseId();
    }
}
