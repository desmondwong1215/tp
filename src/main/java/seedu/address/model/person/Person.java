package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    private final Name name;
    private final Phone phone;
    private final Gender gender;
    private final StudentId studentId;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Gender gender, StudentId studentId) {
        requireAllNonNull(name, phone, gender, studentId);
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.studentId = studentId;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;

    }
    public Gender getGender() {
        return gender;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    /**
     * Returns true if both persons have the same student id.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getStudentId().equals(getStudentId());
    }

    /**
     * Returns true if the student id provided is same as the student id.
     */
    public boolean isSameStudentId(StudentId studentId) {
        return this.studentId.equals(studentId);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && gender.equals(otherPerson.gender)
                && studentId.equals(otherPerson.studentId);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, gender, studentId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("gender", gender)
                .add("studentId", studentId)
                .toString();
    }
}
