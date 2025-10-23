package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DeleteCourseCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.CourseName;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class DeleteCourseCommandTest {
    private Model model;
    private CourseId validCourseId;
    private Course validCourse;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        validCourseId = new CourseId("C0001");
        validCourse = new Course(new CourseName("English 101"), validCourseId,
                Collections.emptySet());
        model.addCourse(validCourse);
    }


    @Test
    public void execute_validCourseId_success() {
        DeleteCourseCommand command = new DeleteCourseCommand(validCourseId);
        CommandResult result = command.execute(model);
        assertEquals(String.format(DeleteCourseCommand.MESSAGE_SUCCESS, validCourse.getName()),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_nonExistentCourseId_failure() {
        CourseId nonExistentId = new CourseId("C9999");
        DeleteCourseCommand command = new DeleteCourseCommand(nonExistentId);
        CommandResult result = command.execute(model);
        assertEquals(DeleteCourseCommand.MESSAGE_NOT_FOUND, result.getFeedbackToUser());
    }

    @Test
    public void execute_courseWithStudents_returnsStudentsEnrolledMessage() {
        UniquePersonList students = new UniquePersonList();
        Person p = new PersonBuilder().build();
        students.add(p);
        CourseId cid = new CourseId("C0002");
        Course courseWithStudents = new Course(new CourseName("With Students"), cid,
                students, Collections.emptySet());
        model.addCourse(courseWithStudents);

        DeleteCourseCommand command = new DeleteCourseCommand(cid);
        CommandResult result = command.execute(model);
        assertEquals(DeleteCourseCommand.MESSAGE_STUDENTS_ENROLLED,
                result.getFeedbackToUser());
    }

    @Test
    public void execute_courseNameNull_returnsGenericSuccessMessage() {
        class CourseWithNullName extends Course {
            CourseWithNullName(CourseName name, CourseId id, UniquePersonList students,
                    Set<Tag> tags) {
                super(name, id, students, tags);
            }

            @Override
            public CourseName getName() {
                return null;
            }
        }

        UniquePersonList students = new UniquePersonList();
        CourseId cid = new CourseId("C0003");
        Course courseNullName = new CourseWithNullName(new CourseName("Temp"), cid,
                students, Collections.emptySet());
        model.addCourse(courseNullName);

        DeleteCourseCommand command = new DeleteCourseCommand(cid);
        CommandResult result = command.execute(model);
        assertEquals(DeleteCourseCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());
    }

    @Test
    public void equals() {
        CourseId courseId1 = new CourseId("C0001");
        CourseId courseId2 = new CourseId("C0002");
        DeleteCourseCommand deleteCommand1 = new DeleteCourseCommand(courseId1);
        DeleteCourseCommand deleteCommand2 = new DeleteCourseCommand(courseId1);
        DeleteCourseCommand deleteCommand3 = new DeleteCourseCommand(courseId2);

        // same object -> returns true
        assertTrue(deleteCommand1.equals(deleteCommand1));

        // same values -> returns true
        assertTrue(deleteCommand1.equals(deleteCommand2));

        // different types -> returns false
        assertFalse(deleteCommand1.equals(1));

        // null -> returns false
        assertFalse(deleteCommand1.equals(null));

        // different course -> returns false
        assertFalse(deleteCommand1.equals(deleteCommand3));
    }

    @Test
    public void hashCode_test() {
        CourseId courseId1 = new CourseId("C0001");
        CourseId courseId2 = new CourseId("C0001");
        DeleteCourseCommand deleteCommand1 = new DeleteCourseCommand(courseId1);
        DeleteCourseCommand deleteCommand2 = new DeleteCourseCommand(courseId2);

        // same course id -> same hashcode
        assertEquals(deleteCommand1.hashCode(), deleteCommand2.hashCode());
    }

    @Test
    public void parse_invalidFormat_throwsParseException() {
        DeleteCourseCommandParser parser = new DeleteCourseCommandParser();
        assertThrows(ParseException.class, () -> parser.parse("invalid_id"));
    }

    @Test
    public void parse_missingId_throwsParseException() {
        DeleteCourseCommandParser parser = new DeleteCourseCommandParser();
        assertThrows(ParseException.class, () -> parser.parse("   "));
    }
}
