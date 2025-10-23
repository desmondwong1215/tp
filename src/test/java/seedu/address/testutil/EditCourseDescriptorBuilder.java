package seedu.address.testutil;

import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.CourseName;

/**
 * A utility class to help with building EditCourseDescriptor objects.
 */
public class EditCourseDescriptorBuilder {


    private EditCourseDescriptor descriptor;

    public EditCourseDescriptorBuilder() {
        descriptor = new EditCourseDescriptor();
    }

    public EditCourseDescriptorBuilder(EditCourseDescriptor descriptor) {
        this.descriptor = new EditCourseDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCourseDescriptor} with fields containing {@code course}'s details
     */
    public EditCourseDescriptorBuilder(Course course) {
        descriptor = new EditCourseDescriptor();
        descriptor.setCourseName(course.getName());
        descriptor.setCourseId(course.getCourseId());
    }

    /**
     * Sets the {@code Course} of the {@code EditCourseDescriptor} that we are building.
     */
    public EditCourseDescriptorBuilder withCourseName(String name) {
        descriptor.setCourseName(new CourseName(name));
        return this;
    }

    /**
     * Sets the {@code CourseId} of the {@code EditCourseDescriptor} that we are building.
     */
    public EditCourseDescriptorBuilder withCourseId(String courseId) {
        descriptor.setCourseId(new CourseId(courseId));
        return this;
    }

    public EditCourseDescriptor build() {
        return descriptor;
    }
}
