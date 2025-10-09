package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidStudentId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidStudentId));
    }

    @Test
    public void isValidStudentId() {
        // null student ID
        assertFalse(StudentId.isValidStudentId(null));

        // blank student ID
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only

        // missing parts
        assertFalse(StudentId.isValidStudentId("S")); // missing digits
        assertFalse(StudentId.isValidStudentId("00001")); // missing 'S' prefix
        assertFalse(StudentId.isValidStudentId("S1234")); // only 4 digits
        assertFalse(StudentId.isValidStudentId("S123456")); // 6 digits

        // invalid parts
        assertFalse(StudentId.isValidStudentId("A12345")); // wrong prefix
        assertFalse(StudentId.isValidStudentId("s12345")); // lowercase 's'
        assertFalse(StudentId.isValidStudentId("S1234A")); // contains letter
        assertFalse(StudentId.isValidStudentId("S12 45")); // contains space
        assertFalse(StudentId.isValidStudentId("S12-45")); // contains hyphen
        assertFalse(StudentId.isValidStudentId("S1234.")); // contains period
        assertFalse(StudentId.isValidStudentId(" S12345")); // leading space
        assertFalse(StudentId.isValidStudentId("S12345 ")); // trailing space
        assertFalse(StudentId.isValidStudentId("SS12345")); // double 'S'
        assertFalse(StudentId.isValidStudentId("S1234")); // only 4 digits after S
        assertFalse(StudentId.isValidStudentId("S1234567")); // 7 digits after S

        // valid student IDs
        assertTrue(StudentId.isValidStudentId("S00001")); // minimum valid
        assertTrue(StudentId.isValidStudentId("S12345")); // typical case
        assertTrue(StudentId.isValidStudentId("S99999")); // maximum typical
        assertTrue(StudentId.isValidStudentId("S00000")); // zeros are allowed
        assertTrue(StudentId.isValidStudentId("S10000")); // mixed digits
        assertTrue(StudentId.isValidStudentId("S54321")); // another typical case
    }

    @Test
    public void equals() {
        StudentId studentId = new StudentId("S12345");

        // same values -> returns true
        assertTrue(studentId.equals(new StudentId("S12345")));

        // same object -> returns true
        assertTrue(studentId.equals(studentId));

        // null -> returns false
        assertFalse(studentId.equals(null));

        // different types -> returns false
        assertFalse(studentId.equals(5.0f));

        // different values -> returns false
        assertFalse(studentId.equals(new StudentId("S67890")));
    }

    @Test
    public void hashCode_test() {
        StudentId studentId1 = new StudentId("S12345");
        StudentId studentId2 = new StudentId("S12345");
        StudentId studentId3 = new StudentId("S67890");

        // same values -> same hash code
        assertTrue(studentId1.hashCode() == studentId2.hashCode());

        // different values -> different hash code (not guaranteed but likely)
        assertFalse(studentId1.hashCode() == studentId3.hashCode());
    }

    @Test
    public void toString_test() {
        StudentId studentId = new StudentId("S12345");
        String expected = "S12345";
        assertTrue(studentId.toString().equals(expected));
    }

    @Test
    public void getValue_test() {
        StudentId studentId = new StudentId("S12345");
        String expected = "S12345";
        assertTrue(studentId.getValue().equals(expected));
    }
}
