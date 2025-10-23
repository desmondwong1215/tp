package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_C1010;
import static seedu.address.logic.commands.CommandTestUtil.DESC_C2660;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_ID_C2660;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.address.testutil.EditCourseDescriptorBuilder;

public class EditCourseDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCourseDescriptor descriptorWithSameValues = new EditCourseDescriptor(DESC_C1010);
        assertTrue(DESC_C1010.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_C1010.equals(DESC_C1010));

        // null -> returns false
        assertFalse(DESC_C1010.equals(null));

        // different types -> returns false
        assertFalse(DESC_C1010.equals(5));

        // different values -> returns false
        assertFalse(DESC_C1010.equals(DESC_C2660));

        // different name -> returns false
        EditCourseDescriptor editedC1010 = new EditCourseDescriptorBuilder(DESC_C1010)
                .withCourseName(VALID_NAME_BOB)
                .build();
        assertFalse(DESC_C1010.equals(editedC1010));

        // different course id -> returns false
        editedC1010 = new EditCourseDescriptorBuilder(DESC_C1010)
                .withCourseId(VALID_COURSE_ID_C2660)
                .build();
        assertFalse(DESC_C1010.equals(editedC1010));
    }

    @Test
    public void toStringMethod() {
        EditCourseDescriptor editCourseDescriptor = new EditCourseDescriptor();
        String expected = EditCourseDescriptor.class.getCanonicalName() + "{courseName="
                + editCourseDescriptor.getCourseName().orElse(null) + ", courseId="
                + editCourseDescriptor.getCourseId().orElse(null) + "}";
        assertEquals(expected, editCourseDescriptor.toString());
    }
}
