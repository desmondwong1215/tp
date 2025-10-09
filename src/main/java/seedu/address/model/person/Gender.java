package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's gender.
 */
public class Gender {
    public static final String MESSAGE_CONSTRAINTS = "Gender should be 'Male', 'Female', or 'Other'";
    public final String value;

    public Gender(String gender) {
        requireNonNull(gender);
        String formatted = gender.trim();
        if (!isValidGender(formatted)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = capitalize(formatted);
    }

    public static boolean isValidGender(String test) {
        String t = test.trim().toLowerCase();
        return t.equals("male") || t.equals("female") || t.equals("other");
    }

    public String getValue() {
        return value;
    }

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Gender && value.equals(((Gender) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
