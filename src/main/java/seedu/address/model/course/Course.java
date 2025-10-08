package seedu.address.model.course;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Course in the EduBase.
 * Guarantees: details are present and not null, field values are validated, immutable and is can be
 * uniquely identified by {@code courseId}.
 */
public class Course {
    private final Name name;
    private final CourseId courseId;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Course(Name name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.courseId = new CourseId();
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public CourseId getCourseId() {
        return courseId;
    }
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameCourse(Course otherCourse) {
        if (otherCourse == this) {
            return true;
        }

        return otherCourse != null
                && otherCourse.getCourseId().equals(getCourseId());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
