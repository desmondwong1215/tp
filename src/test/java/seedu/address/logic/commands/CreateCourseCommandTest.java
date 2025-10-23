package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourses.CS1010;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CourseBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCourseBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.person.Person;
import seedu.address.testutil.CourseBuilder;

public class CreateCourseCommandTest {
    @Test
    public void constructor_nullCourse_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCourseCommand(null));
    }

    @Test
    public void execute_courseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCourseAdded modelStub = new ModelStubAcceptingCourseAdded();
        Course validCourse = new CourseBuilder().build();

        CommandResult commandResult = new CreateCourseCommand(validCourse).execute(modelStub);

        assertEquals(String.format(CreateCourseCommand.MESSAGE_SUCCESS, Messages.format(validCourse)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCourse), modelStub.coursesAdded);
    }

    @Test
    public void execute_duplicateCourse_throwsCommandException() {
        Course validCourse = new CourseBuilder().build();
        CreateCourseCommand addCommand = new CreateCourseCommand(validCourse);
        ModelStub modelStub = new ModelStubWithCourse(validCourse);

        assertThrows(CommandException.class, CreateCourseCommand.MESSAGE_DUPLICATE_COURSE_ID, () ->
                addCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        Course english = new CourseBuilder().withName("English 101").withCourseId("C2660").build();
        Course englishWithDiffId = new CourseBuilder().withName("English 101").withCourseId("C2661").build();
        Course mathematics = new CourseBuilder().withName("Mathematics").withCourseId("C1521").build();
        CreateCourseCommand createEnglishCourseCommand = new CreateCourseCommand(english);
        CreateCourseCommand createEnglishWithDiffIdCourseCommand = new CreateCourseCommand(englishWithDiffId);
        CreateCourseCommand createMathematicsCourseCommand = new CreateCourseCommand(mathematics);

        // same object -> returns true
        assertTrue(createEnglishCourseCommand.equals(createEnglishCourseCommand));

        // same values -> returns true
        CreateCourseCommand createEnglishCourseCommandCopy = new CreateCourseCommand(english);
        assertTrue(createEnglishCourseCommand.equals(createEnglishCourseCommandCopy));

        // different types -> returns false
        assertFalse(createEnglishCourseCommand.equals(1));

        // null -> returns false
        assertFalse(createEnglishCourseCommand.equals(null));

        // different course -> returns false
        assertFalse(createEnglishCourseCommand.equals(createMathematicsCourseCommand));

        // same course name with different course id -> returns false
        assertFalse(createEnglishCourseCommand.equals(createEnglishWithDiffIdCourseCommand));
    }

    @Test
    public void toStringMethod() {
        CreateCourseCommand createCourseCommand = new CreateCourseCommand(CS1010);
        String expected = CreateCourseCommand.class.getCanonicalName() + "{toCreateCourse=" + CS1010 + "}";
        assertEquals(expected, createCourseCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getCourseBookFilePath() {
            return null;
        }

        @Override
        public void setCourseBookFilePath(Path courseBookFilePath) {

        }

        @Override
        public void setCourseBook(ReadOnlyCourseBook courseBook) {

        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCourseBook getCourseBook() {
            return null;
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCourse(Course course) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCourse(Course target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCourse(Course course) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCourse(Course target, Course editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean checkStudentInAllCourse(Person student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Course> getFilteredCourseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCourseList(Predicate<Course> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Course getCourseById(CourseId courseId) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single course.
     */
    private class ModelStubWithCourse extends ModelStub {
        private final Course course;

        ModelStubWithCourse(Course course) {
            requireNonNull(course);
            this.course = course;
        }

        @Override
        public boolean hasCourse(Course course) {
            requireNonNull(course);
            return this.course.equals(this.course);
        }
    }

    /**
     * A Model stub that always accept the course being added.
     */
    private class ModelStubAcceptingCourseAdded extends ModelStub {
        final ArrayList<Course> coursesAdded = new ArrayList<>();

        @Override
        public boolean hasCourse(Course course) {
            requireNonNull(course);
            return coursesAdded.stream().anyMatch(course::equals);
        }

        @Override
        public void addCourse(Course course) {
            requireNonNull(course);
            coursesAdded.add(course);
        }

        @Override
        public ReadOnlyCourseBook getCourseBook() {
            return new CourseBook();
        }
    }

}
