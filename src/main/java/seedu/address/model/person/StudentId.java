package seedu.address.model.person;

/**
 * Represents a student's auto-generated ID.
 */
public class StudentId {
    public final String value;

    /**
     * Constructs a {@code StudentId} with the specified value.
     * The value must be in the format S followed by exactly 5 digits (e.g., S00001).
     *
     * @param value the student ID string
     * @throws IllegalArgumentException if the value does not match the required format
     */
    public StudentId(String value) {
        if (!value.matches("S\\d{5}")) {
            throw new IllegalArgumentException("Invalid Student ID format. Must be like S00001");
        }
        this.value = value;
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
