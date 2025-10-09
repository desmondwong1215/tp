package seedu.address.model.person;

/**
 * Represents a student's auto-generated ID.
 */
public class StudentId {
    public final String value;

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
