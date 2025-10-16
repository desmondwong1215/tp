package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getObservablePerson;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.CourseBook;
import seedu.address.testutil.TypicalCourses;

public class JsonSerializableCourseBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCourseBookTest");
    private static final Path TYPICAL_COURSE_FILE = TEST_DATA_FOLDER.resolve("typicalCourseCourseBook.json");
    private static final Path INVALID_COURSE_FILE = TEST_DATA_FOLDER.resolve("invalidCourseCourseBook.json");
    private static final Path DUPLICATE_COURSE_FILE = TEST_DATA_FOLDER.resolve("duplicateCourseCourseBook.json");

    @Test
    public void toModelType_typicalCourseFile_success() throws Exception {
        JsonSerializableCourseBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_COURSE_FILE,
                JsonSerializableCourseBook.class).get();
        CourseBook courseBookFromFile = dataFromFile.toModelType(getObservablePerson());
        CourseBook typicalCourseBook = TypicalCourses.getTypicalCourseBook();
        assertEquals(courseBookFromFile, typicalCourseBook);
    }

    @Test
    public void toModelType_invalidCourseFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCourseBook dataFromFile = JsonUtil.readJsonFile(INVALID_COURSE_FILE,
                JsonSerializableCourseBook.class).get();
        assertThrows(IllegalValueException.class, () -> dataFromFile.toModelType(getObservablePerson()));
    }

    @Test
    public void toModelType_duplicateCourse_throwsIllegalValueException() throws Exception {
        JsonSerializableCourseBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_COURSE_FILE,
                JsonSerializableCourseBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCourseBook.MESSAGE_DUPLICATE_COURSE, () ->
                dataFromFile.toModelType(getObservablePerson()));
    }

}
