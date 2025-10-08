package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.CourseBook;
import seedu.address.model.course.Course;

/**
 * A utility class containing a list of {@code Course} objects to be used in tests.
 */
public class TypicalCourses {

    // Typical Course Data
    public static final Course CS1010 = new CourseBuilder().withCourseId("C1010")
            .withName("Programming Methodology").build();
    public static final Course MA1521 = new CourseBuilder().withCourseId("C1521")
            .withName("Calculus").build();
    public static final Course GEQ1000 = new CourseBuilder().withCourseId("C9876")
            .withName("Thinking and Expression").build();
    public static final Course CS2040 = new CourseBuilder().withCourseId("C2040")
            .withName("Data Structures and Algorithms").build();
    public static final Course IS3240 = new CourseBuilder().withCourseId("C3240")
            .withName("Project Management").build();

    // Manually added (for use in specific command tests)
    public static final Course GEH1000 = new CourseBuilder().withCourseId("C1000")
            .withName("Globalisation").build();
    public static final Course CFG1002 = new CourseBuilder().withCourseId("C0002")
            .withName("Career Strategy").build();


    private TypicalCourses() {} // prevents instantiation

    /**
     * Returns a list of all the typical courses.
     */
    public static List<Course> getTypicalCourses() {
        return new ArrayList<>(Arrays.asList(CS1010, MA1521, GEQ1000, CS2040, IS3240));
    }

    /**
     * Returns an {@code CourseBook} with all the typical courses.
     */
    public static CourseBook getTypicalCourseBook() {
        CourseBook cb = new CourseBook();
        for (Course course : getTypicalCourses()) {
            cb.addCourse(course);
        }
        return cb;
    }
}
