package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Id} matches any of the keywords given.
 */
public class IdMatchesKeywordsPredicate implements Predicate<Person> {
    private final List<StudentId> keywords;

    public IdMatchesKeywordsPredicate(List<StudentId> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getStudentId().equals(keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdMatchesKeywordsPredicate)) {
            return false;
        }

        IdMatchesKeywordsPredicate otherIdMatchesKeywordsPredicate = (IdMatchesKeywordsPredicate) other;
        return keywords.equals(otherIdMatchesKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
