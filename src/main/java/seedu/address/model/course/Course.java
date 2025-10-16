package seedu.address.model.course;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Course in the EduBase.
 * Guarantees: details are present and not null, field values are validated, immutable and can be
 * uniquely identified by {@code courseId}.
 */
public class Course {
    private final CourseName name;
    private final CourseId courseId;
    private final UniquePersonList studentList;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();


    /**
     * Constructor for creating new course. Every field must be present and not null.
     */
    public Course(CourseName name, CourseId courseId, Set<Tag> tags) {
        requireAllNonNull(name, tags, courseId);
        this.name = name;
        this.courseId = courseId;
        this.studentList = new UniquePersonList();
        this.tags.addAll(tags);
    }

    /**
     * Constructor for loading data from storage. Every field must be present and not null.
     */
    public Course(CourseName name, CourseId courseId, UniquePersonList studentList, Set<Tag> tags) {
        requireAllNonNull(name, tags, courseId);
        this.name = name;
        this.courseId = courseId;
        this.studentList = studentList;
        this.tags.addAll(tags);
    }

    public CourseName getName() {
        return name;
    }

    public CourseId getCourseId() {
        return courseId;
    }

    public UniquePersonList getStudentList() {
        return studentList;
    }

    public List<String> getStudentIds() {
        return studentList.asUnmodifiableObservableList()
                .stream().map(person -> person.getStudentId().getValue()).toList();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if there are students enrolled in this course.
     */
    public boolean hasEnrolledStudents() {
        return !studentList.asUnmodifiableObservableList().isEmpty();
    }

    /**
     * Returns true if both courses have the same {@code courseId}
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Course)) {
            return false;
        }

        Course otherCourse = (Course) other;
        return courseId.equals(otherCourse.courseId);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, courseId, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("courseId", courseId)
                .add("tags", tags)
                .toString();
    }
}
