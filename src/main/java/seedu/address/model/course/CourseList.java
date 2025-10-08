package seedu.address.model.course;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.course.exceptions.CourseNotFoundException;
import seedu.address.model.person.exceptions.DuplicatePersonException;


/**
 * A list of courses that enforces uniqueness between its elements and does not allow nulls.
 * A course is considered unique by comparing using {@code Course#equals(Course)}.
 * Supports a minimal set of list operations.
 *
 */
public class CourseList implements Iterable<Course> {

    private final ObservableList<Course> internalList = FXCollections.observableArrayList();
    private final ObservableList<Course> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent course as the given argument.
     */
    public boolean contains(Course toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a course to the list.
     * The course must not already exist in the list.
     */
    public void add(Course toAdd) {
        requireNonNull(toAdd);
        assert !contains(toAdd);
        internalList.add(toAdd);
    }

    //TODO allow for editing of course

    /**
     * Removes the equivalent course from the list.
     * The course must exist in the list.
     */
    public void remove(Course toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CourseNotFoundException();
        }
    }

    public void setCourses(CourseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCourses(List<Course> course) {
        requireAllNonNull(course);
        if (!personsAreUnique(course)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(course);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Course> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Course> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseList otherUniquePersonList)) {
            return false;
        }

        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code courses} contains only unique courses.
     */
    private boolean personsAreUnique(List<Course> courses) {
        for (int i = 0; i < courses.size() - 1; i++) {
            for (int j = i + 1; j < courses.size(); j++) {
                if (courses.get(i).equals(courses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
