package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.EditCourseCommand.EditCourseDescriptor;

/**
 * A utility class for Course.
 */
public class CourseUtil {

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditCourseDescriptorDetails(EditCourseDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCourseName().ifPresent(courseName -> sb.append(PREFIX_NAME)
                .append(courseName.fullName).append(" "));
        descriptor.getCourseId().ifPresent(courseId -> sb.append(PREFIX_ID)
                .append(courseId.value).append(" "));
        return sb.toString();
    }
}
