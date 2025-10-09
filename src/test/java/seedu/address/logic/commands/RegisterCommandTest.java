package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCourseBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.PersonBuilder;

public class RegisterCommandTest {

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        Phone phone = new Phone("12345678");
        Gender gender = new Gender("Male");
        assertThrows(NullPointerException.class, () -> new RegisterCommand(null, phone, gender));
    }

    @Test
    public void constructor_nullPhone_throwsNullPointerException() {
        Name name = new Name("John Doe");
        Gender gender = new Gender("Male");
        assertThrows(NullPointerException.class, () -> new RegisterCommand(name, null, gender));
    }

    @Test
    public void constructor_nullGender_throwsNullPointerException() {
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        assertThrows(NullPointerException.class, () -> new RegisterCommand(name, phone, null));
    }

    @Test
    public void execute_personAcceptedByModel_registerSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        Gender gender = new Gender("Male");
        RegisterCommand registerCommand = new RegisterCommand(name, phone, gender);

        CommandResult commandResult = registerCommand.execute(modelStub);

        assertEquals(1, modelStub.personsAdded.size());
        Person addedPerson = modelStub.personsAdded.get(0);
        assertEquals(name, addedPerson.getName());
        assertEquals(phone, addedPerson.getPhone());
        assertEquals(gender, addedPerson.getGender());
        assertEquals(String.format(RegisterCommand.MESSAGE_SUCCESS, Messages.format(addedPerson)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        Gender gender = new Gender("Male");
        RegisterCommand registerCommand = new RegisterCommand(name, phone, gender);
        ModelStub modelStub = new ModelStubWithPerson(new PersonBuilder().withName("John Doe")
                .withPhone("12345678").withGender("Male").build());

        assertThrows(CommandException.class, RegisterCommand.MESSAGE_DUPLICATE_PERSON, () ->
                registerCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        Gender gender = new Gender("Male");
        RegisterCommand registerCommand = new RegisterCommand(name, phone, gender);

        RegisterCommand sameValuesCommand = new RegisterCommand(name, phone, gender);

        Name differentName = new Name("Jane Doe");
        Phone differentPhone = new Phone("87654321");
        Gender differentGender = new Gender("Female");
        RegisterCommand differentCommand = new RegisterCommand(differentName, differentPhone, differentGender);

        // same object -> returns true
        assertTrue(registerCommand.equals(registerCommand));

        // same values -> returns true
        assertTrue(registerCommand.equals(sameValuesCommand));

        // different types -> returns false
        assertFalse(registerCommand.equals(1));

        // null -> returns false
        assertFalse(registerCommand.equals(null));

        // different person -> returns false
        assertFalse(registerCommand.equals(differentCommand));
    }

    @Test
    public void toStringMethod() {
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        Gender gender = new Gender("Male");
        RegisterCommand registerCommand = new RegisterCommand(name, phone, gender);
        String expected = RegisterCommand.class.getCanonicalName() + "{name=" + name + ", phone=" + phone
                + ", gender=" + gender + "}";
        assertEquals(expected, registerCommand.toString());
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCourseBook(ReadOnlyCourseBook courseBook) {
            throw new AssertionError("This method should not be called.");
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
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that always accept the person being added and generates student IDs.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        private int nextStudentId = 1;

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            AddressBook addressBook = new AddressBook();
            // Override generateStudentId to provide predictable IDs for testing
            return new AddressBook() {
                @Override
                public StudentId generateStudentId() {
                    String idString = String.format("S%05d", nextStudentId++);
                    return new StudentId(idString);
                }
            };
        }

        public StudentId generateStudentId() {
            String idString = String.format("S%05d", nextStudentId++);
            return new StudentId(idString);
        }
    }
}
