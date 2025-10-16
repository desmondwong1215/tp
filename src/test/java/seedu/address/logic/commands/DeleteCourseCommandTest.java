package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DeleteCourseCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.CourseName;

public class DeleteCourseCommandTest {
    private Model model;
    private CourseId validCourseId;
    private Course validCourse;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        validCourseId = new CourseId("C0001");
        validCourse = new Course(new CourseName("English 101"), validCourseId, Collections.emptySet());
        model.addCourse(validCourse);
    }

    @Test
    public void execute_validCourseId_success() {
        DeleteCourseCommand command = new DeleteCourseCommand(validCourseId);
        CommandResult result = command.execute(model);
        assertEquals(
            String.format(DeleteCourseCommand.MESSAGE_SUCCESS, validCourse.getName()),
            result.getFeedbackToUser()
        );
    }

    @Test
    public void execute_nonExistentCourseId_failure() {
        CourseId nonExistentId = new CourseId("C9999");
        DeleteCourseCommand command = new DeleteCourseCommand(nonExistentId);
        CommandResult result = command.execute(model);
        assertEquals(DeleteCourseCommand.MESSAGE_NOT_FOUND, result.getFeedbackToUser());
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
