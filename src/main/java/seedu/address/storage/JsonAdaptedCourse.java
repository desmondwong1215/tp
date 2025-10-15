package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.exceptions.ItemNotFoundException;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.course.CourseName;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link seedu.address.model.course.Course}.
 */
class JsonAdaptedCourse {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Course's %s field is missing!";

    private final String name;
    private final String courseId;
    private final List<String> studentIds;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCourse} with the given course. details.
     */
    @JsonCreator
    public JsonAdaptedCourse(@JsonProperty("name") String name, @JsonProperty("courseId") String courseId,
                             @JsonProperty("students") List<String> studentIds,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.courseId = courseId;
        this.studentIds = studentIds == null ? new ArrayList<>() : studentIds;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Course} into this class for Jackson use.
     */
    public JsonAdaptedCourse(Course source) {
        name = source.getName().fullName;
        courseId = source.getCourseId().toString();
        studentIds = source.getStudentList().asUnmodifiableObservableList()
                        .stream().map(person -> person.getStudentId().value).toList();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .toList());
    }

    /**
     * Converts this Jackson-friendly adapted course. object into the model's {@code Course} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted course.
     */
    public Course toModelType(ObservableList<Person> studentList) throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CourseName.class.getSimpleName()));
        }
        if (!CourseName.isValidName(name)) {
            throw new IllegalValueException(CourseName.MESSAGE_CONSTRAINTS);
        }
        final CourseName modelName = new CourseName(name);

        if (courseId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CourseId.class.getSimpleName()));
        }
        if (!CourseId.isValidCourseId(courseId)) {
            throw new IllegalValueException(CourseId.MESSAGE_CONSTRAINTS);
        }
        final CourseId modelCourseId = new CourseId(courseId);
        final UniquePersonList modelStudents = new UniquePersonList();
        for (String id : studentIds) {
            try {
                StudentId studentId = new StudentId(id);
                for (int i = 0; i < studentList.size(); i++) {
                    Person student = studentList.get(i);
                    if (student.getStudentId().equals(studentId)) {
                        modelStudents.add(student);
                        break;
                    }
                    if (i == studentList.size() - 1) {
                        throw new ItemNotFoundException("Student Id not found.");
                    }
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
            }
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Course(modelName, modelCourseId, modelStudents, modelTags);
    }

}
