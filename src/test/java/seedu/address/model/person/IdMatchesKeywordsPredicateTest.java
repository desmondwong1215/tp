package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IdMatchesKeywordsPredicateTest {

    private final StudentId first = new StudentId("S00001");
    private final StudentId second = new StudentId("S00002");
    private final StudentId third = new StudentId("S00003");

    @Test
    public void equals() {
        List<StudentId> firstPredicateKeywordList = Collections.singletonList(first);
        List<StudentId> secondPredicateKeywordList = Arrays.asList(first, second);

        IdMatchesKeywordsPredicate firstPredicate = new IdMatchesKeywordsPredicate(firstPredicateKeywordList);
        IdMatchesKeywordsPredicate secondPredicate = new IdMatchesKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IdMatchesKeywordsPredicate firstPredicateCopy = new IdMatchesKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        IdMatchesKeywordsPredicate predicate = new IdMatchesKeywordsPredicate(Collections.singletonList(first));
        assertTrue(predicate.test(new PersonBuilder().withStudentId("S00001").build()));

        // Multiple keywords
        predicate = new IdMatchesKeywordsPredicate(Arrays.asList(first, second));
        assertTrue(predicate.test(new PersonBuilder().withStudentId("S00001").build()));

        // Duplicate keywords
        predicate = new IdMatchesKeywordsPredicate(Arrays.asList(first, first));
        assertTrue(predicate.test(new PersonBuilder().withStudentId("S00001").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        IdMatchesKeywordsPredicate predicate = new IdMatchesKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withStudentId("S00001").build()));

        // Non-matching keyword
        predicate = new IdMatchesKeywordsPredicate(Collections.singletonList(third));
        assertFalse(predicate.test(new PersonBuilder().withStudentId("S00001").build()));
    }

    @Test
    public void toStringMethod() {
        List<StudentId> keywords = List.of(first, second);
        IdMatchesKeywordsPredicate predicate = new IdMatchesKeywordsPredicate(keywords);

        String expected = IdMatchesKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
