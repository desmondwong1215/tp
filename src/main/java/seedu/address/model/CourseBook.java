package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class CourseBook implements ReadOnlyCourseBook {

    private final CourseList courses;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        courses = new CourseList();
    }

    public CourseBook() {}

    /**
     * Creates an CourseBook using the Persons in the {@code toBeCopied}
     */
    public CourseBook(ReadOnlyCourseBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the course list with {@code courses}.
     * {@code courses} must not contain duplicate courses.
     */
    public void setCourses(List<Course> courses) {
        this.courses.setCourses(courses);

    }

    /**
     * Resets the existing data of this {@code CourseBook} with {@code newData}.
     */
    public void resetData(ReadOnlyCourseBook newData) {
        requireNonNull(newData);

        setCourses(newData.getCourseList());
    }

    //// course-level operations

    /**
     * Returns true if a course with the same identity as {@code course} exists in the address book.
     */
    public boolean hasCourse(Course course) {
        requireNonNull(course);
        return courses.contains(course);
    }

    /**
     * Adds a course to the address book.
     * The course must not already exist in the address book.
     */
    public void addCourse(Course c) {
        courses.add(c);
    }

    /**
     * Replaces the given course {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The course identity of {@code editedPerson} must not be the same as another existing course in the address book.
     */
    public void setCourse(Course target, Course edittedCourse) {
        requireNonNull(edittedCourse);
        ///     TODO: courses.setPerson(target, edittedCourse);
    }

    /**
     * Removes {@code key} from this {@code CourseBook}.
     * {@code key} must exist in the address book.
     */
    public void removeCourse(Course key) {
        courses.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("courses", courses)
                .toString();
    }

    @Override
    public ObservableList<Course> getCourseList() {
        return courses.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseBook otherAddressBook)) {
            return false;
        }

        return courses.equals(otherAddressBook.courses);
    }

    @Override
    public int hashCode() {
        return courses.hashCode();
    }
}
