package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.CourseBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCourseBook;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.CourseName;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Gender("male"), new StudentId("S00001")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Gender("female"), new StudentId("S00002")),
            new Person(new Name("Charlotte Oliveiro"),
                    new Phone("93210283"), new Gender("female"), new StudentId("S00003")),
            new Person(new Name("David Li"), new Phone("91031282"),
                    new Gender("male"), new StudentId("S00004")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Gender("male"), new StudentId("S00005")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Gender("male"), new StudentId("S00006"))
        };
    }

    public static Course[] getSampleCourse() {
        UniquePersonList studentList = new UniquePersonList();
        Person[] sample = getSamplePersons();
        studentList.add(sample[0]);
        studentList.add(sample[1]);
        return new Course[] {
            new Course(new CourseName("Mathematics"), new CourseId("C1231"), studentList, getTagSet())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyCourseBook getSampleCourseBook() {
        CourseBook sampleCb = new CourseBook();
        for (Course sampleCourse : getSampleCourse()) {
            sampleCb.addCourse(sampleCourse);
        }
        return sampleCb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
