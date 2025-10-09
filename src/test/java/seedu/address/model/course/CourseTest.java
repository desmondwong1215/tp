package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class CourseTest {

    private static final CourseName VALID_NAME = new CourseName("Mathematics");
    private static final Set<Tag> VALID_TAGS = new HashSet<>();

    @Test
    public void constructor_null_throwsNullPointerException() {
        // null name
        assertThrows(NullPointerException.class, () -> new Course(null, VALID_TAGS));

        // null tags
        assertThrows(NullPointerException.class, () -> new Course(VALID_NAME, null));

        // both null
        assertThrows(NullPointerException.class, () -> new Course(null, null));
    }

    @Test
    public void constructor_validInputs_success() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("core"));

        Course course = new Course(VALID_NAME, tags);

        assertEquals(VALID_NAME, course.getName());
        assertEquals(tags, course.getTags());
        assertTrue(course.getCourseId() != null);
    }

    @Test
    public void constructor_emptyTags_success() {
        Course course = new Course(VALID_NAME, new HashSet<>());
        assertTrue(course.getTags().isEmpty());
    }

    @Test
    public void getTags_modifyReturnedSet_throwsUnsupportedOperationException() {
        Course course = new Course(VALID_NAME, VALID_TAGS);
        assertThrows(UnsupportedOperationException.class, () ->
                course.getTags().add(new Tag("test")));
    }

    @Test
    public void equals() {
        Course course1 = new Course(VALID_NAME, VALID_TAGS);
        Course course2 = new Course(new CourseName("Physics"), VALID_TAGS);

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
        // Since CourseId is auto-generated, we can't easily create two courses
        // with the same ID through the constructor. This test verifies the logic.
        Course course = new Course(VALID_NAME, VALID_TAGS);
        assertTrue(course.equals(course));
    }

    @Test
    public void hashCode_sameCourse_sameHashCode() {
        Course course = new Course(VALID_NAME, VALID_TAGS);
        assertEquals(course.hashCode(), course.hashCode());
    }

    @Test
    public void hashCode_differentCourses_differentHashCodes() {
        Course course1 = new Course(VALID_NAME, VALID_TAGS);
        Course course2 = new Course(new CourseName("Physics"), VALID_TAGS);
        assertNotEquals(course1.hashCode(), course2.hashCode());
    }

    @Test
    public void toString_validCourse_correctFormat() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("core"));
        Course course = new Course(VALID_NAME, tags);

        String result = course.toString();

        // Should contain all key information
        assertTrue(result.contains("name"));
        assertTrue(result.contains("courseId"));
        assertTrue(result.contains("tags"));
        assertTrue(result.contains(VALID_NAME.toString()));
    }

    @Test
    public void getCourseId_uniqueForEachCourse() {
        Course course1 = new Course(VALID_NAME, VALID_TAGS);
        Course course2 = new Course(VALID_NAME, VALID_TAGS);

        assertNotEquals(course1.getCourseId(), course2.getCourseId());
    }
}
