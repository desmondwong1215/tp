package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // blank gender
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only

        // invalid genders
        assertFalse(Gender.isValidGender("M")); // too short
        assertFalse(Gender.isValidGender("F")); // too short
        assertFalse(Gender.isValidGender("Malee")); // misspelled
        assertFalse(Gender.isValidGender("Femalle")); // misspelled
        assertFalse(Gender.isValidGender("Otherr")); // misspelled
        assertFalse(Gender.isValidGender("123")); // numbers
        assertFalse(Gender.isValidGender("M@le")); // special characters
        assertFalse(Gender.isValidGender("GENDER")); // all caps
        assertFalse(Gender.isValidGender("male female")); // multiple words
        assertFalse(Gender.isValidGender("Trans")); // not in valid list
        assertFalse(Gender.isValidGender("Non-binary")); // not in valid list

        // valid genders - case insensitive
        assertTrue(Gender.isValidGender("male")); // lowercase
        assertTrue(Gender.isValidGender("MALE")); // uppercase
        assertTrue(Gender.isValidGender("Male")); // capitalized
        assertTrue(Gender.isValidGender("mAlE")); // mixed case
        assertTrue(Gender.isValidGender("female")); // lowercase
        assertTrue(Gender.isValidGender("FEMALE")); // uppercase
        assertTrue(Gender.isValidGender("Female")); // capitalized
        assertTrue(Gender.isValidGender("fEmAlE")); // mixed case
        assertTrue(Gender.isValidGender("other")); // lowercase
        assertTrue(Gender.isValidGender("OTHER")); // uppercase
        assertTrue(Gender.isValidGender("Other")); // capitalized
        assertTrue(Gender.isValidGender("OtHeR")); // mixed case

        // valid genders with surrounding spaces (should be trimmed and validated)
        assertTrue(Gender.isValidGender("  male  ")); // with spaces
        assertTrue(Gender.isValidGender("  female  ")); // with spaces
        assertTrue(Gender.isValidGender("  other  ")); // with spaces
    }

    @Test
    public void equals() {
        Gender gender = new Gender("male");

        // same values -> returns true
        assertTrue(gender.equals(new Gender("male")));

        // same object -> returns true
        assertTrue(gender.equals(gender));

        // null -> returns false
        assertFalse(gender.equals(null));

        // different types -> returns false
        assertFalse(gender.equals(5.0f));

        // different values -> returns false
        assertFalse(gender.equals(new Gender("female")));
    }

    @Test
    public void hashCode_test() {
        Gender gender1 = new Gender("male");
        Gender gender2 = new Gender("male");
        Gender gender3 = new Gender("female");

        // same values -> same hash code
        assertTrue(gender1.hashCode() == gender2.hashCode());

        // different values -> different hash code (not guaranteed but likely)
        assertFalse(gender1.hashCode() == gender3.hashCode());
    }

    @Test
    public void toString_test() {
        Gender gender = new Gender("male");
        String expected = "Male";
        assertTrue(gender.toString().equals(expected));
    }
}
