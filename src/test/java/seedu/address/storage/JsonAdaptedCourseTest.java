package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.getObservablePerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.exceptions.ItemNotFoundException;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.CourseName;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;


public class JsonAdaptedCourseTest {
    // According to CourseName Regex: "[\\p{Alnum}][\\p{Alnum} ]*"
    private static final String VALID_NAME = "Software Engineering 101";
    private static final String INVALID_NAME = "@Special Module!"; // Contains special characters not in \p{Alnum}

    // According to CourseId Regex: "^C\\d{4}$"
    private static final String VALID_COURSE_ID = "C2103";
    private static final String INVALID_COURSE_ID = "CS2103"; // 'S' is invalid

    // According to CourseId Regex: "^S\\d{5}$"
    private static final String INVALID_STUDENT_ID = "S000001";
    private static final String OUTBOUND_STUDENT_ID = "S99999";

    private static final List<String> EMPTY_STUDENT = new ArrayList<String>();

    // A valid sample Course object for testing the constructor
    private static final Course SAMPLE_COURSE = new Course(
            new CourseName("Software Engineering"),
            new CourseId("C2103"),
            Set.of(new Tag("core"), new Tag("popular"))
    );

    @Test
    public void toModelType_validCourseDetails_returnsCourse() throws Exception {
        // Test conversion from a valid JsonAdaptedCourse created from a Course object
        JsonAdaptedCourse jsonCourse = new JsonAdaptedCourse(SAMPLE_COURSE);
        assertEquals(SAMPLE_COURSE, jsonCourse.toModelType(getObservablePerson()));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        // Create a list of valid tags for the test
        List<JsonAdaptedTag> validTags = SAMPLE_COURSE.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList());

        JsonAdaptedCourse course = new JsonAdaptedCourse(INVALID_NAME, VALID_COURSE_ID, EMPTY_STUDENT, validTags);
        String expectedMessage = CourseName.MESSAGE_CONSTRAINTS;

        // The lambda course::toModelType is a runnable that will be executed by assertThrows
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () ->
                course.toModelType(getObservablePerson()));
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        List<JsonAdaptedTag> validTags = SAMPLE_COURSE.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList());
        JsonAdaptedCourse course = new JsonAdaptedCourse(null, VALID_COURSE_ID, EMPTY_STUDENT, validTags);
        String expectedMessage = String.format(JsonAdaptedCourse.MISSING_FIELD_MESSAGE_FORMAT,
                CourseName.class.getSimpleName());

        IllegalValueException thrown = assertThrows(IllegalValueException.class, () ->
                course.toModelType(getObservablePerson()));
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void toModelType_invalidCourseId_throwsIllegalValueException() {
        List<JsonAdaptedTag> validTags = SAMPLE_COURSE.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList());

        JsonAdaptedCourse course = new JsonAdaptedCourse(VALID_NAME, INVALID_COURSE_ID, EMPTY_STUDENT, validTags);
        String expectedMessage = CourseId.MESSAGE_CONSTRAINTS;

        IllegalValueException thrown = assertThrows(IllegalValueException.class, () ->
                course.toModelType(getObservablePerson()));
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void toModelType_nullCourseId_throwsIllegalValueException() {
        List<JsonAdaptedTag> validTags = SAMPLE_COURSE.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList());
        JsonAdaptedCourse course = new JsonAdaptedCourse(VALID_NAME, null, EMPTY_STUDENT, validTags);
        String expectedMessage = String.format(JsonAdaptedCourse.MISSING_FIELD_MESSAGE_FORMAT,
                CourseId.class.getSimpleName());

        IllegalValueException thrown = assertThrows(IllegalValueException.class, () ->
                course.toModelType(getObservablePerson()));
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        List<JsonAdaptedTag> validTags = SAMPLE_COURSE.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList());
        List<String> invalidStudents = new ArrayList<>();
        invalidStudents.add(INVALID_STUDENT_ID);
        JsonAdaptedCourse course = new JsonAdaptedCourse(VALID_NAME, VALID_COURSE_ID, invalidStudents, validTags);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;

        IllegalValueException thrown = assertThrows(IllegalValueException.class, () ->
                course.toModelType(getObservablePerson()));
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void toModelType_studentIdNotFound_throwsItemNotFoundException() {
        List<JsonAdaptedTag> validTags = SAMPLE_COURSE.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList());
        List<String> invalidStudents = new ArrayList<>();
        invalidStudents.add(OUTBOUND_STUDENT_ID);
        JsonAdaptedCourse course = new JsonAdaptedCourse(VALID_NAME, VALID_COURSE_ID, invalidStudents, validTags);
        String expectedMessage = "Student Id not found.";

        ItemNotFoundException thrown = assertThrows(ItemNotFoundException.class, () ->
                course.toModelType(getObservablePerson()));
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    public void toModelType_valid_returnCourse() {
        List<JsonAdaptedTag> validTags = SAMPLE_COURSE.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList());
        List<String> validStudents = new ArrayList<>();
        validStudents.add("S00001");
        JsonAdaptedCourse course = new JsonAdaptedCourse(VALID_NAME, VALID_COURSE_ID, validStudents, validTags);
        Course c;
        try {
            c = course.toModelType(getObservablePerson());
        } catch (IllegalValueException e) {
            throw new AssertionError("This will not happen.");
        }
        assertEquals("S00001", c.getStudentList().asUnmodifiableObservableList().get(0).getStudentId()
                .getValue());
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>();
        invalidTags.add(new JsonAdaptedTag("invalid tag")); // Tag with a space is typically invalid

        JsonAdaptedCourse course = new JsonAdaptedCourse(VALID_NAME, VALID_COURSE_ID, EMPTY_STUDENT, invalidTags);

        // We only check for the exception type, as the message comes from Tag.MESSAGE_CONSTRAINTS
        assertThrows(IllegalValueException.class, () ->
                course.toModelType(getObservablePerson()));
    }
}
