package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourses.CFG1002;
import static seedu.address.testutil.TypicalCourses.CS2040;
import static seedu.address.testutil.TypicalCourses.getTypicalCourseBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.CourseBook;
import seedu.address.model.ReadOnlyCourseBook;

public class JsonCourseBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCourseBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCourseBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCourseBook(null));
    }

    private java.util.Optional<ReadOnlyCourseBook> readCourseBook(String filePath) throws Exception {
        return new JsonCourseBookStorage(Paths.get(filePath)).readCourseBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCourseBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readCourseBook("notJsonFormatCourseBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readCourseBook("invalidCourseCourseBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readCourseBook("invalidAndValidCourseCourseBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCourseBook.json");
        CourseBook original = getTypicalCourseBook();
        JsonCourseBookStorage jsonCourseBookStorage = new JsonCourseBookStorage(filePath);

        // Save in new file and read back
        jsonCourseBookStorage.saveCourseBook(original, filePath);
        ReadOnlyCourseBook readBack = jsonCourseBookStorage.readCourseBook(filePath).get();
        assertEquals(original, new CourseBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCourse(CFG1002);
        original.removeCourse(CS2040);
        jsonCourseBookStorage.saveCourseBook(original, filePath);
        readBack = jsonCourseBookStorage.readCourseBook(filePath).get();
        assertEquals(original, new CourseBook(readBack));

        // Save and read without specifying file path
        original.addCourse(CS2040);
        jsonCourseBookStorage.saveCourseBook(original); // file path not specified
        readBack = jsonCourseBookStorage.readCourseBook().get(); // file path not specified
        assertEquals(original, new CourseBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCourseBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveCourseBook(ReadOnlyCourseBook addressBook, String filePath) {
        try {
            new JsonCourseBookStorage(Paths.get(filePath))
                    .saveCourseBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCourseBook(new CourseBook(), null));
    }
}
