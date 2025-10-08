package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Course objects.
 */
public class CourseBuilder {

    public static final String DEFAULT_NAME = "Programming";
    public static final String DEFAULT_COURSEID = "C1101";

    private Name name;
    private CourseId courseId;
    private Set<Tag> tags;

    /**
     * Creates a {@code CourseBuilder} with the default details.
     */
    public CourseBuilder() {
        name = new Name(DEFAULT_NAME);
        courseId = new CourseId(DEFAULT_COURSEID);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code courseToCopy}.
     */
    public CourseBuilder(Course courseToCopy) {
        name = courseToCopy.getName();
        courseId = courseToCopy.getCourseId();
        tags = new HashSet<>(courseToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Course} that we are building.
     */
    public CourseBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Course} that we are building.
     */
    public CourseBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    /**
     * Sets the {@code Email} of the {@code Course} that we are building.
     */
    public CourseBuilder withCourseId(String id) {
        this.courseId = new CourseId(id);
        return this;
    }

    public Course build() {
        return new Course(name, courseId, tags);
    }

}
