package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CourseBuilder;

class CourseNameContainsKeywordsPredicateTest {
    @Test
    void test_courseNameContainsKeyword_returnsTrue() {
        Course course = new CourseBuilder().withName("Data Science").build();
        CourseNameContainsKeywordsPredicate predicate = new CourseNameContainsKeywordsPredicate(Arrays.asList("Data"));
        assertTrue(predicate.test(course));
    }

    @Test
    void test_courseNameContainsKeywordCaseInsensitive_returnsTrue() {
        Course course = new CourseBuilder().withName("Data Science").build();
        CourseNameContainsKeywordsPredicate predicate =
                new CourseNameContainsKeywordsPredicate(Arrays.asList("science"));
        assertTrue(predicate.test(course));
    }

    @Test
    void test_courseNameDoesNotContainKeyword_returnsFalse() {
        Course course = new CourseBuilder().withName("Data Science").build();
        CourseNameContainsKeywordsPredicate predicate =
                new CourseNameContainsKeywordsPredicate(Arrays.asList("Math"));
        assertFalse(predicate.test(course));
    }

    @Test
    void test_emptyKeywordList_returnsFalse() {
        Course course = new CourseBuilder().withName("Data Science").build();
        CourseNameContainsKeywordsPredicate predicate =
                new CourseNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(course));
    }

    @Test
    void equals_variousCases() {
        List<String> keywords1 = Arrays.asList("Data", "Science");
        List<String> keywords2 = Arrays.asList("Math");

        CourseNameContainsKeywordsPredicate predicate1 = new CourseNameContainsKeywordsPredicate(keywords1);
        CourseNameContainsKeywordsPredicate predicate2 = new CourseNameContainsKeywordsPredicate(keywords1);
        CourseNameContainsKeywordsPredicate predicate3 = new CourseNameContainsKeywordsPredicate(keywords2);

        // Reflexive
        assertTrue(predicate1.equals(predicate1));
        // Same values
        assertTrue(predicate1.equals(predicate2));
        // Null
        assertFalse(predicate1.equals(null));
        // Different type
        assertFalse(predicate1.equals("not a predicate"));
        // Different keywords
        assertFalse(predicate1.equals(predicate3));
    }

    @Test
    void toString_containsKeywords() {
        List<String> keywords = Arrays.asList("Data", "Science");
        CourseNameContainsKeywordsPredicate predicate = new CourseNameContainsKeywordsPredicate(keywords);
        String str = predicate.toString();
        assertTrue(str.contains("Data"));
        assertTrue(str.contains("Science"));
        assertTrue(str.contains("CourseNameContainsKeywordsPredicate"));
    }
}

