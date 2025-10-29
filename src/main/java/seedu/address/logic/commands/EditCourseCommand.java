package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSES;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.CourseName;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing course in the course book.
 */
public class EditCourseCommand extends Command {

    public static final String COMMAND_WORD = "edit_course";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the course identified "
            + "by the index number used in the displayed course list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ID + "COURSE_ID] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "English 101 "
            + PREFIX_ID + "C0001";

    public static final String MESSAGE_EDIT_COURSE_SUCCESS = "Edited Course: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_COURSE = "This course already exists in the course book.";

    private final Index index;
    private final EditCourseDescriptor editCourseDescriptor;

    /**
     * @param index of the course in the filtered course list to edit
     * @param editCourseDescriptor details to edit the course with
     */
    public EditCourseCommand(Index index, EditCourseDescriptor editCourseDescriptor) {
        requireNonNull(index);
        requireNonNull(editCourseDescriptor);

        this.index = index;
        this.editCourseDescriptor = new EditCourseDescriptor(editCourseDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Course> lastShownList = model.getFilteredCourseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
        }

        Course courseToEdit = lastShownList.get(index.getZeroBased());
        Course editedCourse = createEditedCourse(courseToEdit, editCourseDescriptor);

        if (!courseToEdit.equals(editedCourse) && model.hasCourse(editedCourse)) {
            throw new CommandException(MESSAGE_DUPLICATE_COURSE);
        }

        model.setCourse(courseToEdit, editedCourse);
        model.updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
        return new CommandResult(String.format(MESSAGE_EDIT_COURSE_SUCCESS, Messages.format(editedCourse)));
    }

    /**
     * Creates and returns a {@code Course} with the details of {@code courseToEdit}
     * edited with {@code editCourseDescriptor}.
     */
    private static Course createEditedCourse(Course courseToEdit, EditCourseDescriptor editCourseDescriptor) {
        assert courseToEdit != null;

        CourseName updatedName = editCourseDescriptor.getCourseName().orElse(courseToEdit.getName());
        CourseId updatedId = editCourseDescriptor.getCourseId().orElse(courseToEdit.getCourseId());
        Set<Tag> tags = courseToEdit.getTags(); // Preserve the original tags
        UniquePersonList originalStudentList = courseToEdit.getStudentList(); // Preserve the original student list

        return new Course(updatedName, updatedId, originalStudentList, tags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCourseCommand otherEditCourseCommand)) {
            return false;
        }

        return index.equals(otherEditCourseCommand.index)
                && editCourseDescriptor.equals(otherEditCourseCommand.editCourseDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editCourseDescriptor", editCourseDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the course with. Each non-empty field value will replace the
     * corresponding field value of the course.
     */
    public static class EditCourseDescriptor {
        private CourseName courseName;
        private CourseId courseId;

        public EditCourseDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditCourseDescriptor(EditCourseDescriptor toCopy) {
            setCourseName(toCopy.courseName);
            setCourseId(toCopy.courseId);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(courseName, courseId);
        }

        public void setCourseName(CourseName courseName) {
            this.courseName = courseName;
        }

        public Optional<CourseName> getCourseName() {
            return Optional.ofNullable(courseName);
        }

        public void setCourseId(CourseId courseId) {
            this.courseId = courseId;
        }

        public Optional<CourseId> getCourseId() {
            return Optional.ofNullable(courseId);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCourseDescriptor otherEditCourseDescriptor)) {
                return false;
            }

            return Objects.equals(courseName, otherEditCourseDescriptor.courseName)
                    && Objects.equals(courseId, otherEditCourseDescriptor.courseId);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("courseName", courseName)
                    .add("courseId", courseId)
                    .toString();
        }
    }
}
