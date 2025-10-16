package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.CourseBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCourseBook;
import seedu.address.model.course.Course;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons_returnsCorrectPersons() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        // Check for null and correct array size
        assertNotNull(samplePersons);
        assertEquals(6, samplePersons.length);

        // Spot-check the first person to ensure data integrity
        assertEquals("Alex Yeoh", samplePersons[0].getName().toString());
        assertEquals("87438807", samplePersons[0].getPhone().toString());
        assertEquals("S00001", samplePersons[0].getStudentId().toString());
    }

    @Test
    public void getSampleCourse_returnsCorrectCourses() {
        Course[] sampleCourses = SampleDataUtil.getSampleCourse();

        // Check for null and correct array size
        assertNotNull(sampleCourses);
        assertEquals(1, sampleCourses.length);

        // Check the course details
        assertEquals("Mathematics", sampleCourses[0].getName().toString());
        assertEquals("C1231", sampleCourses[0].getCourseId().toString());
        // The sample course is created with an empty tag set
        assertTrue(sampleCourses[0].getTags().isEmpty());
    }

    @Test
    public void getSampleAddressBook_returnsCorrectAddressBook() {
        ReadOnlyAddressBook sampleAb = SampleDataUtil.getSampleAddressBook();
        AddressBook expectedAb = new AddressBook();
        for (Person p : SampleDataUtil.getSamplePersons()) {
            expectedAb.addPerson(p);
        }

        // Verify that the generated sample AddressBook is equal to a manually created one
        assertEquals(expectedAb, sampleAb);
    }

    @Test
    public void getSampleCourseBook_returnsCorrectCourseBook() {
        ReadOnlyCourseBook sampleCb = SampleDataUtil.getSampleCourseBook();
        CourseBook expectedCb = new CourseBook();
        for (Course c : SampleDataUtil.getSampleCourse()) {
            expectedCb.addCourse(c);
        }

        // Verify that the generated sample CourseBook is equal to a manually created one
        assertEquals(expectedCb, sampleCb);
    }

    @Test
    public void getTagSet_noArguments_returnsEmptySet() {
        Set<Tag> tagSet = SampleDataUtil.getTagSet();
        assertTrue(tagSet.isEmpty());
    }

    @Test
    public void getTagSet_singleString_returnsSetWithOneTag() {
        Set<Tag> tagSet = SampleDataUtil.getTagSet("friends");
        Set<Tag> expectedSet = new HashSet<>();
        expectedSet.add(new Tag("friends"));
        assertEquals(expectedSet, tagSet);
    }

    @Test
    public void getTagSet_multipleStrings_returnsSetWithCorrectTags() {
        Set<Tag> tagSet = SampleDataUtil.getTagSet("friends", "colleagues");
        Set<Tag> expectedSet = Arrays.stream(new String[]{"friends", "colleagues"})
                .map(Tag::new)
                .collect(Collectors.toSet());
        assertEquals(expectedSet, tagSet);
    }

    @Test
    public void getTagSet_duplicateStrings_returnsSetWithUniqueTags() {
        // A Set should only contain unique elements
        Set<Tag> tagSet = SampleDataUtil.getTagSet("owesMoney", "owesMoney");
        Set<Tag> expectedSet = new HashSet<>();
        expectedSet.add(new Tag("owesMoney"));

        assertEquals(1, tagSet.size());
        assertEquals(expectedSet, tagSet);
    }
}
