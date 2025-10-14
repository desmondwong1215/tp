package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.course.exceptions.CourseNotFoundException;
import seedu.address.model.course.exceptions.DuplicateCourseIdException;
import seedu.address.model.tag.Tag;

public class CourseListTest {

    private CourseList courseList;
    private Course mathematics;
    private Course physics;
    private Set<Tag> emptyTags;


    @BeforeEach
    public void setUp() {
        courseList = new CourseList();
        emptyTags = new HashSet<>();
        mathematics = new Course(new CourseName("Mathematics"), new CourseId("C0001"), emptyTags);
        physics = new Course(new CourseName("Physics"), new CourseId("C0002"), emptyTags);
    }

    @Test
    public void contains_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> courseList.contains(null));
    }

    @Test
    public void contains_courseNotInList_returnsFalse() {
        assertFalse(courseList.contains(mathematics));
    }

    @Test
    public void contains_courseInList_returnsTrue() {
        courseList.add(mathematics);
        assertTrue(courseList.contains(mathematics));
    }

    @Test
    public void add_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> courseList.add(null));
    }

    @Test
    public void add_validCourse_success() {
        courseList.add(mathematics);
        assertTrue(courseList.contains(mathematics));
    }

    @Test
    public void add_duplicateCourse_throwsAssertionError() {
        courseList.add(mathematics);
        assertThrows(DuplicateCourseIdException.class, () -> courseList.add(mathematics));
    }

    @Test
    public void remove_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> courseList.remove(null));
    }

    @Test
    public void remove_courseDoesNotExist_throwsCourseNotFoundException() {
        assertThrows(CourseNotFoundException.class, () -> courseList.remove(mathematics));
    }

    @Test
    public void remove_existingCourse_removesCourse() {
        courseList.add(mathematics);
        courseList.remove(mathematics);
        assertFalse(courseList.contains(mathematics));
    }

    @Test
    public void remove_fromMultipleCourses_removesCorrectCourse() {
        courseList.add(mathematics);
        courseList.add(physics);

        courseList.remove(mathematics);

        assertFalse(courseList.contains(mathematics));
        assertTrue(courseList.contains(physics));
    }

    @Test
    public void setCourses_nullCourseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> courseList.setCourses((CourseList) null));
    }

    @Test
    public void setCourses_validCourseList_setsInternalList() {
        CourseList newCL = new CourseList();
        newCL.add(mathematics);
        courseList.setCourses(newCL);
        assertTrue(courseList.contains(mathematics));
        assertFalse(courseList.contains(physics));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        courseList.add(mathematics);
        assertThrows(UnsupportedOperationException.class, () ->
                courseList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void asUnmodifiableObservableList_reflectsChanges() {
        var unmodifiableList = courseList.asUnmodifiableObservableList();

        assertEquals(0, unmodifiableList.size());

        courseList.add(mathematics);
        assertEquals(1, unmodifiableList.size());

        courseList.add(physics);
        assertEquals(2, unmodifiableList.size());

        courseList.remove(mathematics);
        assertEquals(1, unmodifiableList.size());
    }

    @Test
    public void iterator_emptyList_noElements() {
        int count = 0;
        for (Course course : courseList) {
            count++;
        }
        assertEquals(0, count);
    }

    @Test
    public void iterator_withCourses_iteratesAllElements() {
        courseList.add(mathematics);
        courseList.add(physics);

        int count = 0;
        for (Course course : courseList) {
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    public void equals() {
        courseList.add(mathematics);

        // same object -> returns true
        assertTrue(courseList.equals(courseList));

        // null -> returns false
        assertFalse(courseList.equals(null));

        // different types -> returns false
        assertFalse(courseList.equals(5));

        // different course lists -> returns false
        CourseList differentList = new CourseList();
        differentList.add(physics);
        assertFalse(courseList.equals(differentList));

        // same courses -> returns true
        CourseList sameList = new CourseList();
        sameList.add(mathematics);
        assertTrue(courseList.equals(sameList));
    }

    @Test
    public void hashCode_sameLists_sameHashCode() {
        courseList.add(mathematics);

        CourseList sameList = new CourseList();
        sameList.add(mathematics);

        assertEquals(courseList.hashCode(), sameList.hashCode());
    }

    @Test
    public void toString_emptyList_emptyString() {
        String result = courseList.toString();
        assertEquals("[]", result);
    }

    @Test
    public void toString_withCourses_containsCourseInfo() {
        courseList.add(mathematics);
        String result = courseList.toString();
        assertTrue(result.contains("Mathematics"));
    }
}
