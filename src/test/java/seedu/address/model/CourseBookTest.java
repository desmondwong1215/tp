package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourses.CS1010;
import static seedu.address.testutil.TypicalCourses.CS2040;
import static seedu.address.testutil.TypicalCourses.MA1521;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.exceptions.CourseNotFoundException;
import seedu.address.model.course.exceptions.DuplicateCourseIdException;
import seedu.address.testutil.CourseBuilder;

public class CourseBookTest {

    private final CourseBook courseBook = new CourseBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), courseBook.getCourseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> courseBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyCourseBook_replacesData() {
        CourseBook newData = getTypicalCourseBook();
        courseBook.resetData(newData);
        assertEquals(newData, courseBook);
    }

    @Test
    public void resetData_withDuplicateCourses_throwsDuplicateCourseException() {
        // Two courses with the same identity fields
        Course editedCs1010 = new CourseBuilder(CS1010).withName("Different Name").build();
        List<Course> newCourses = Arrays.asList(CS1010, editedCs1010);
        CourseBookStub newData = new CourseBookStub(newCourses);

        assertThrows(DuplicateCourseIdException.class, () -> courseBook.resetData(newData));
    }

    @Test
    public void hasCourse_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> courseBook.hasCourse(null));
    }

    @Test
    public void hasCourse_courseNotInCourseBook_returnsFalse() {
        assertFalse(courseBook.hasCourse(CS1010));
    }

    @Test
    public void hasCourse_courseInCourseBook_returnsTrue() {
        courseBook.addCourse(CS1010);
        assertTrue(courseBook.hasCourse(CS1010));
    }

    @Test
    public void hasCourse_courseWithSameIdentityInCourseBook_returnsTrue() {
        courseBook.addCourse(CS1010);
        // Course with the same CourseId but different name
        Course editedCs1010 = new CourseBuilder(CS1010).withName("A Different Name").build();
        assertTrue(courseBook.hasCourse(editedCs1010));
    }

    @Test
    public void addCourse_duplicateCourse_throwsDuplicateCourseException() {
        courseBook.addCourse(CS1010);
        assertThrows(DuplicateCourseIdException.class, () -> courseBook.addCourse(CS1010));
    }

    @Test
    public void removeCourse_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> courseBook.removeCourse(null));
    }

    @Test
    public void removeCourse_courseDoesNotExist_throwsCourseNotFoundException() {
        assertThrows(CourseNotFoundException.class, () -> courseBook.removeCourse(CS1010));
    }

    @Test
    public void removeCourse_existingCourse_removesCourse() {
        courseBook.addCourse(CS1010);
        courseBook.removeCourse(CS1010);
        CourseBook expectedCourseBook = new CourseBook();
        assertEquals(expectedCourseBook, courseBook);
    }

    @Test
    public void setCourses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> courseBook.setCourses(null));
    }

    @Test
    public void setCourses_listWithDuplicateCourses_throwsDuplicateCourseException() {
        List<Course> listWithDuplicates = Arrays.asList(CS1010, CS1010);
        assertThrows(DuplicateCourseIdException.class, () -> courseBook.setCourses(listWithDuplicates));
    }

    @Test
    public void setCourses_replacesOwnListWithProvidedList() {
        courseBook.addCourse(CS1010);
        List<Course> courseList = Collections.singletonList(CS2040);
        courseBook.setCourses(courseList);
        CourseBook expectedCourseBook = new CourseBook();
        expectedCourseBook.addCourse(CS2040);
        assertEquals(expectedCourseBook, courseBook);
    }

    // Note: The setCourse method is marked with a TODO in the provided source code.
    // Tests for this method should be written once its implementation is complete.

    @Test
    public void getCourseById_nullCourseId_throwsNullPointerException() {
        // This assumes the underlying CourseList's getByCourseId method throws a NullPointerException for null input.
        assertThrows(NullPointerException.class, () -> courseBook.getCourseById(null));
    }

    @Test
    public void getCourseById_idNotInBook_returnsNull() {
        courseBook.addCourse(CS1010);
        assertNull(courseBook.getCourseById(new CourseId("C9999")));
    }

    @Test
    public void getCourseById_idInBook_returnsCourse() {
        courseBook.addCourse(MA1521);
        Course foundCourse = courseBook.getCourseById(MA1521.getCourseId());
        assertEquals(MA1521, foundCourse);
    }


    @Test
    public void getCourseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> courseBook.getCourseList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = new ToStringBuilder(new CourseBook())
                .add("courses", courseBook.getCourseList())
                .toString();
        assertEquals(expected, courseBook.toString());
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(courseBook.equals(courseBook));

        // null -> returns false
        assertFalse(courseBook.equals(null));

        // different type -> returns false
        assertFalse(courseBook.equals(5));

        // same values -> returns true
        CourseBook courseBookCopy = new CourseBook(getTypicalCourseBook());
        assertTrue(getTypicalCourseBook().equals(courseBookCopy));

        // different courseBook -> returns false
        CourseBook differentCourseBook = new CourseBook();
        differentCourseBook.addCourse(CS2040);
        assertFalse(courseBook.equals(differentCourseBook));
    }

    @Test
    public void hashCode_test() {
        CourseBook cb1 = getTypicalCourseBook();
        CourseBook cb2 = getTypicalCourseBook();
        // same courses -> same hashcode
        assertEquals(cb1.hashCode(), cb2.hashCode());
    }

    /**
     * A stub ReadOnlyCourseBook whose courses list can violate interface constraints.
     * This is used for testing scenarios with duplicate courses.
     */
    private static class CourseBookStub implements ReadOnlyCourseBook {
        private final ObservableList<Course> courses = FXCollections.observableArrayList();

        CourseBookStub(Collection<Course> courses) {
            this.courses.setAll(courses);
        }

        @Override
        public ObservableList<Course> getCourseList() {
            return courses;
        }
    }
}
