package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class CourseTest {

    private static final CourseName VALID_NAME = new CourseName("Mathematics");
    private static final CourseId VALID_COURSE_ID = new CourseId("C9999");
    private static final Set<Tag> VALID_TAGS = new HashSet<>();

    @Test
    public void constructor_null_throwsNullPointerException() {
        // null name
        assertThrows(NullPointerException.class, () -> new Course(null, VALID_COURSE_ID, VALID_TAGS));

        // null courseId
        assertThrows(NullPointerException.class, () -> new Course(VALID_NAME, null, VALID_TAGS));

        // null tags
        assertThrows(NullPointerException.class, () -> new Course(VALID_NAME, VALID_COURSE_ID, null));

        // all null
        assertThrows(NullPointerException.class, () -> new Course(null, null, null));
    }

    @Test
    public void constructor_validInputs_success() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("core"));

        Course course = new Course(VALID_NAME, VALID_COURSE_ID, tags);

        assertEquals(VALID_NAME, course.getName());
        assertEquals(VALID_COURSE_ID, course.getCourseId());
        assertEquals(tags, course.getTags());
        assertNotNull(course.getCourseId());
    }

    @Test
    public void constructor_emptyTags_success() {
        Course course = new Course(VALID_NAME, VALID_COURSE_ID, new HashSet<>());
        assertTrue(course.getTags().isEmpty());
    }

    @Test
    public void getTags_modifyReturnedSet_throwsUnsupportedOperationException() {
        Course course = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        assertThrows(UnsupportedOperationException.class, () ->
                course.getTags().add(new Tag("test")));
    }

    @Test
    public void equals() {
        Course course1 = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        CourseId differentId = new CourseId("C0101");
        Course course2 = new Course(new CourseName("Physics"), differentId, VALID_TAGS);

        // same object -> returns true
        assertTrue(course1.equals(course1));

        // null -> returns false
        assertFalse(course1.equals(null));

        // different type -> returns false
        assertFalse(course1.equals(5));

        // different course -> returns false (different course IDs)
        assertFalse(course1.equals(course2));
    }

    @Test
    public void equals_sameCourseId_returnsTrue() {
        // Two courses with the same ID should be equal, even with different names
        Course course1 = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        Course course2 = new Course(new CourseName("Physics"), VALID_COURSE_ID, VALID_TAGS);

        assertTrue(course1.equals(course2));
    }

    @Test
    public void hashCode_sameCourse_sameHashCode() {
        Course course = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        assertEquals(course.hashCode(), course.hashCode());
    }

    @Test
    public void hashCode_differentCourses_differentHashCodes() {
        Course course1 = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        CourseId differentId = new CourseId("C0101");
        Course course2 = new Course(new CourseName("Physics"), differentId, VALID_TAGS);
        assertNotEquals(course1.hashCode(), course2.hashCode());
    }

    @Test
    public void toString_validCourse_correctFormat() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("core"));
        Course course = new Course(VALID_NAME, VALID_COURSE_ID, tags);

        String result = course.toString();

        // Should contain all key information
        assertTrue(result.contains("name"));
        assertTrue(result.contains("courseId"));
        assertTrue(result.contains("tags"));
        assertTrue(result.contains(VALID_NAME.toString()));
    }

    @Test
    public void getCourseId_returnsCorrectCourseId() {
        Course course1 = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        assertEquals(VALID_COURSE_ID, course1.getCourseId());

        CourseId anotherId = new CourseId("C0101");
        Course course2 = new Course(VALID_NAME, anotherId, VALID_TAGS);
        assertEquals(anotherId, course2.getCourseId());

        assertNotEquals(course1.getCourseId(), course2.getCourseId());
    }

    @Test
    public void addStudent_null_throwsNullPointerException() {
        Course course1 = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        assertThrows(NullPointerException.class, () -> course1.addStudent(null));
    }

    @Test
    public void addStudent_valid() {
        Course course1 = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        course1.addStudent(ALICE);
        assertTrue(course1.getStudentList().contains(ALICE));
    }

    @Test
    public void removeStudent_null_throwsNullPointerException() {
        Course course1 = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        assertThrows(NullPointerException.class, () -> course1.removeStudent(null));
    }

    @Test
    public void removeStudent_valid() {
        Course course1 = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        course1.addStudent(ALICE);
        assertTrue(course1.getStudentList().contains(ALICE));
        course1.removeStudent(ALICE);
        assertFalse(course1.getStudentList().contains(ALICE));
    }

    @Test
    public void containsStudent_null_throwsNullPointerException() {
        Course course1 = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        assertThrows(NullPointerException.class, () -> course1.containsStudent(null));
    }

    @Test
    public void containsStudent_returnsTrue() {
        Course course1 = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        course1.addStudent(ALICE);
        assertTrue(() -> course1.containsStudent(ALICE));
    }

    @Test
    public void containsStudent_returnsFalse() {
        Course course1 = new Course(VALID_NAME, VALID_COURSE_ID, VALID_TAGS);
        assertFalse(() -> course1.containsStudent(ALICE));
    }
}
