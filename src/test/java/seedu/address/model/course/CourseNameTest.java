package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CourseNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CourseName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CourseName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> CourseName.isValidName(null));

        // invalid names
        assertFalse(CourseName.isValidName("")); // empty string
        assertFalse(CourseName.isValidName(" ")); // spaces only
        assertFalse(CourseName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(CourseName.isValidName("math*")); // contains non-alphanumeric characters
        assertFalse(CourseName.isValidName(" Programming")); // leading space

        // valid names
        assertTrue(CourseName.isValidName("mathematics")); // alphabets only
        assertTrue(CourseName.isValidName("12345")); // numbers only
        assertTrue(CourseName.isValidName("CS2103T")); // alphanumeric characters
        assertTrue(CourseName.isValidName("Computer Science")); // with spaces
        assertTrue(CourseName.isValidName("Introduction to Data Structures")); // long names
        assertTrue(CourseName.isValidName("a")); // single character
        assertTrue(CourseName.isValidName("A")); // single uppercase character
        assertTrue(CourseName.isValidName("1")); // single digit
    }

    @Test
    public void equals() {
        CourseName name = new CourseName("Mathematics");

        // same values -> returns true
        assertTrue(name.equals(new CourseName("Mathematics")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new CourseName("Physics")));
    }

    @Test
    public void hashCode_sameValue_sameHashCode() {
        CourseName name1 = new CourseName("Computer Science");
        CourseName name2 = new CourseName("Computer Science");
        assertEquals(name1.hashCode(), name2.hashCode());
    }

    @Test
    public void toString_validName_returnsFullName() {
        CourseName name = new CourseName("Data Structures");
        assertEquals("Data Structures", name.toString());
    }
}
