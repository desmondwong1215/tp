package seedu.address.model.course;

import java.util.Objects;

/**
 * Unique identifier for Course
 * courseId is an integer but prints out with prefix C with courseId padded to a total of 5 digits.
 * Current design can only accommodate up to 10^4 different courses
 */
public class CourseId {

    private static int nextCourseId = 0;

    public final int courseId;

    /**
     * Creates new courseId, automatically generated. No params needed.
     */
    public CourseId() {
        courseId = nextCourseId;
        nextCourseId++;
    }

    @Override
    public String toString() {
        return "C" + String.format("%04d", courseId);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseId)) {
            return false;
        }

        CourseId otherId = (CourseId) other;
        return courseId == (otherId.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}
