package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseNameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.CourseBuilder;

class FindCourseByNameCommandTest {
    private ModelStubWithCourses model;
    private Course course1;
    private Course course2;
    private Course course3;

    @BeforeEach
    void setUp() {
        course1 = new CourseBuilder().withName("Data Science").build();
        course2 = new CourseBuilder().withName("Mathematics").build();
        course3 = new CourseBuilder().withName("Science of Cooking").build();
        model = new ModelStubWithCourses(course1, course2, course3);
    }

    @Test
    void execute_oneKeyword_matchesOneCourse() {
        FindCourseByNameCommand command = new FindCourseByNameCommand(
                new CourseNameContainsKeywordsPredicate(Collections.singletonList("Mathematics")));
        CommandResult result = command.execute(model);
        assertEquals(1, model.getFilteredCourseList().size());
        assertTrue(model.getFilteredCourseList().contains(course2));
        assertTrue(result.getFeedbackToUser().contains("1 courses listed"));
    }

    @Test
    void execute_multipleKeywords_matchesMultipleCourses() {
        FindCourseByNameCommand command = new FindCourseByNameCommand(
                new CourseNameContainsKeywordsPredicate(Arrays.asList("Science", "Cooking")));
        CommandResult result = command.execute(model);
        assertEquals(2, model.getFilteredCourseList().size());
        assertTrue(model.getFilteredCourseList().contains(course1));
        assertTrue(model.getFilteredCourseList().contains(course3));
        assertTrue(result.getFeedbackToUser().contains("2 courses listed"));
    }

    @Test
    void execute_noMatch_returnsZero() {
        FindCourseByNameCommand command = new FindCourseByNameCommand(
                new CourseNameContainsKeywordsPredicate(Collections.singletonList("History")));
        CommandResult result = command.execute(model);
        assertEquals(0, model.getFilteredCourseList().size());
        assertTrue(result.getFeedbackToUser().contains("0 courses listed"));
    }

    // Minimal Model stub for testing
    private static class ModelStubWithCourses implements Model {
        private final ObservableList<Course> courses = FXCollections.observableArrayList();
        private final ObservableList<Course> filteredCourses = FXCollections.observableArrayList();

        ModelStubWithCourses(Course... courses) {
            this.courses.addAll(Arrays.asList(courses));
            this.filteredCourses.setAll(this.courses);
        }

        @Override
        public ObservableList<Course> getFilteredCourseList() {
            return filteredCourses;
        }

        @Override
        public void updateFilteredCourseList(Predicate<Course> predicate) {
            filteredCourses.setAll(courses.filtered(predicate));
        }

        // All other Model methods throw UnsupportedOperationException
        @Override public void setUserPrefs(seedu.address.model.ReadOnlyUserPrefs userPrefs) {
            throw new UnsupportedOperationException();
        }
        @Override public seedu.address.model.ReadOnlyUserPrefs getUserPrefs() {
            throw new UnsupportedOperationException();
        }
        @Override public seedu.address.commons.core.GuiSettings getGuiSettings() {
            throw new UnsupportedOperationException();
        }
        @Override public void setGuiSettings(seedu.address.commons.core.GuiSettings guiSettings) {
            throw new UnsupportedOperationException();
        }
        @Override public java.nio.file.Path getAddressBookFilePath() {
            throw new UnsupportedOperationException();
        }
        @Override public void setAddressBookFilePath(java.nio.file.Path addressBookFilePath) {
            throw new UnsupportedOperationException();
        }
        @Override public void setAddressBook(seedu.address.model.ReadOnlyAddressBook addressBook) {
            throw new UnsupportedOperationException();
        }
        @Override public java.nio.file.Path getCourseBookFilePath() {
            throw new UnsupportedOperationException();
        }
        @Override public void setCourseBookFilePath(java.nio.file.Path courseBookFilePath) {
            throw new UnsupportedOperationException();
        }
        @Override public void setCourseBook(seedu.address.model.ReadOnlyCourseBook courseBook) {
            throw new UnsupportedOperationException();
        }
        @Override public seedu.address.model.ReadOnlyAddressBook getAddressBook() {
            throw new UnsupportedOperationException();
        }
        @Override public seedu.address.model.ReadOnlyCourseBook getCourseBook() {
            throw new UnsupportedOperationException();
        }
        @Override public boolean hasPerson(seedu.address.model.person.Person person) {
            throw new UnsupportedOperationException();
        }
        @Override public void deletePerson(seedu.address.model.person.Person target) {
            throw new UnsupportedOperationException();
        }
        @Override public void addPerson(seedu.address.model.person.Person person) {
            throw new UnsupportedOperationException();
        }
        @Override public void setPerson(seedu.address.model.person.Person target,
                                        seedu.address.model.person.Person editedPerson) {
            throw new UnsupportedOperationException();
        }
        @Override public ObservableList<seedu.address.model.person.Person> getFilteredPersonList() {
            throw new UnsupportedOperationException();
        }
        @Override public void updateFilteredPersonList(Predicate<seedu.address.model.person.Person> predicate) {
            throw new UnsupportedOperationException();
        }
        @Override public boolean hasCourse(Course course) {
            throw new UnsupportedOperationException();
        }
        @Override public void deleteCourse(Course target) {
            throw new UnsupportedOperationException();
        }
        @Override public void addCourse(Course course) {
            throw new UnsupportedOperationException();
        }
        @Override public void setCourse(Course target, Course editedPerson) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean checkStudentInAllCourse(Person student) {
            throw new UnsupportedOperationException();
        }

        @Override public Course getCourseById(seedu.address.model.course.CourseId courseId) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void updateFilteredStudentListForCourse(Course course) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void updateFilteredCourseListForCourse(Course course) {
            throw new UnsupportedOperationException();
        }
    }

    @Test
    void equals() {
        CourseNameContainsKeywordsPredicate predicate1 =
                new CourseNameContainsKeywordsPredicate(Collections.singletonList("first"));
        CourseNameContainsKeywordsPredicate predicate2 =
                new CourseNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCourseByNameCommand command1 = new FindCourseByNameCommand(predicate1);
        FindCourseByNameCommand command2 = new FindCourseByNameCommand(predicate1);
        FindCourseByNameCommand command3 = new FindCourseByNameCommand(predicate2);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        assertTrue(command1.equals(command2));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different predicate -> returns false
        assertFalse(command1.equals(command3));
    }

    @Test
    void equals_variousCases() {
        CourseNameContainsKeywordsPredicate predicate1 =
                new CourseNameContainsKeywordsPredicate(Collections.singletonList("first"));
        CourseNameContainsKeywordsPredicate predicate2 =
                new CourseNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCourseByNameCommand command1 = new FindCourseByNameCommand(predicate1);
        FindCourseByNameCommand command2 = new FindCourseByNameCommand(predicate1);
        FindCourseByNameCommand command3 = new FindCourseByNameCommand(predicate2);

        // Reflexive
        assertTrue(command1.equals(command1));
        // Same values
        assertTrue(command1.equals(command2));
        // Null
        assertFalse(command1.equals(null));
        // Different type
        assertFalse(command1.equals("not a command"));
        // Different predicate
        assertFalse(command1.equals(command3));
    }
}
