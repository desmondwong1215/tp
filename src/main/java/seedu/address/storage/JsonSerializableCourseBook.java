package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.CourseBook;
import seedu.address.model.ReadOnlyCourseBook;
import seedu.address.model.course.Course;

/**
 * An Immutable CourseBook that is serializable to JSON format.
 */
@JsonRootName(value = "coursebook")
class JsonSerializableCourseBook {

    public static final String MESSAGE_DUPLICATE_COURSE = "Courses list contains duplicate course(s).";

    private final List<JsonAdaptedCourse> courses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCourseBook} with the given courses.
     */
    @JsonCreator
    public JsonSerializableCourseBook(@JsonProperty("courses") List<JsonAdaptedCourse> courses) {
        this.courses.addAll(courses);
    }

    /**
     * Converts a given {@code ReadOnlyCourseBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCourseBook}.
     */
    public JsonSerializableCourseBook(ReadOnlyCourseBook source) {
        courses.addAll(source.getCourseList().stream().map(JsonAdaptedCourse::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code CourseBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CourseBook toModelType() throws IllegalValueException {
        CourseBook courseBook = new CourseBook();
        for (JsonAdaptedCourse jsonAdaptedCourse : courses) {
            Course course = jsonAdaptedCourse.toModelType();
            if (courseBook.hasCourse(course)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_COURSE);
            }
            courseBook.addCourse(course);
        }
        return courseBook;
    }

}
