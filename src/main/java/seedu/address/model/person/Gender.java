package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's gender.
 */
public class Gender {
    public static final String MESSAGE_CONSTRAINTS = "Gender should be 'Male', 'Female', or 'Other'";
    public final String value;

    /**
     * Constructs a {@code Gender} object with the specified gender value.
     * The gender value is trimmed and capitalized after validation.
     *
     * @param gender the gender value to be stored
     * @throws NullPointerException if the input gender is null
     * @throws IllegalArgumentException if the input gender is not valid
     * @see #isValidGender(String)
     */
    public Gender(String gender) {
        requireNonNull(gender);
        String formatted = gender.trim();
        if (!isValidGender(formatted)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = capitalize(formatted);
    }

    /**
     * Checks if the given string represents a valid gender.
     * Valid genders are "male", "female", or "other" (case-insensitive).
     *
     * @param test the string to test for gender validity
     * @return true if the string is a valid gender, false otherwise
     */
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
