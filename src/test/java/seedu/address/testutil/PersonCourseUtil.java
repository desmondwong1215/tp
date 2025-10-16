package seedu.address.testutil;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.model.course.Course;
import seedu.address.model.person.Person;

public class PersonCourseUtil {
    public static String getAddCommand(Person person, Course course) {
        return AddStudentCommand.COMMAND_WORD + " " + person.getStudentId() + " " + course.getCourseId();
    }
}
