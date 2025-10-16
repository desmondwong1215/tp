package seedu.address.model.person;

/**
 * Represents a student's auto-generated ID.
 */
public class StudentId {
    public static final String MESSAGE_CONSTRAINTS =
            "Student ID must be in the format S followed by exactly 5 digits (e.g., S00001)";
    public static final String VALIDATION_REGEX = "S\\d{5}";

    private final String value;

    /**
     * Constructs a {@code StudentId} with the specified value.
     * The value must be in the format S followed by exactly 5 digits (e.g., S00001).
     *
     * @param value the student ID string
     * @throws NullPointerException if the value is null
     * @throws IllegalArgumentException if the value does not match the required format
     */
    public StudentId(String value) {
        if (value == null) {
            throw new NullPointerException("Student ID cannot be null");
        }
        if (!isValidStudentId(value)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid student ID.
     */
    public static boolean isValidStudentId(String test) {
        return test != null && test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof StudentId && value.equals(((StudentId) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
