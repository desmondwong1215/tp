package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid names
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("math*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName(" Programming")); // leading space

        // valid names
        assertTrue(Name.isValidName("mathematics")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("CS2103T")); // alphanumeric characters
        assertTrue(Name.isValidName("Computer Science")); // with spaces
        assertTrue(Name.isValidName("Introduction to Data Structures")); // long names
        assertTrue(Name.isValidName("a")); // single character
        assertTrue(Name.isValidName("A")); // single uppercase character
        assertTrue(Name.isValidName("1")); // single digit
    }

    @Test
    public void equals() {
        Name name = new Name("Mathematics");

        // same values -> returns true
        assertTrue(name.equals(new Name("Mathematics")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Physics")));
    }

    @Test
    public void hashCode_sameValue_sameHashCode() {
        Name name1 = new Name("Computer Science");
        Name name2 = new Name("Computer Science");
        assertEquals(name1.hashCode(), name2.hashCode());
    }

    @Test
    public void toString_validName_returnsFullName() {
        Name name = new Name("Data Structures");
        assertEquals("Data Structures", name.toString());
    }
}
