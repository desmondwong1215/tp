package seedu.address.model.course;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Unique identifier for Course
 * courseId is an integer but prints out with prefix C with courseId padded to a total of 5 digits.
 * Current design can only accommodate up to 10^4 different courses
 */
public class CourseId {

    public static final String MESSAGE_CONSTRAINTS = "CourseId must start with C followed by 4 digits";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^C\\d{4}$";

    private static int nextCourseId = 0;

    public final String value;


    /**
     * Creates new courseId, automatically generated. No params needed.
     */
    public CourseId() {
        value = "C" + String.format("%04d", nextCourseId);
        nextCourseId++;
    }

    /**
     * Creates new courseId with an id string. No params needed.
     */
    public CourseId(String courseId) {
        requireNonNull(courseId);
        checkArgument(isValidCourseId(courseId), MESSAGE_CONSTRAINTS);
        value = courseId;
    }

    /**
     * Returns true if a given string is a valid courseId.
     */
    public static boolean isValidCourseId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseId otherId)) {
            return false;
        }

        return Objects.equals(value, otherId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
