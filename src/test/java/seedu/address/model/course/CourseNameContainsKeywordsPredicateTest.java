package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;

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
    void test_courseNameContainsKeyword_caseInsensitive_returnsTrue() {
        Course course = new CourseBuilder().withName("Data Science").build();
        CourseNameContainsKeywordsPredicate predicate = new CourseNameContainsKeywordsPredicate(Arrays.asList("science"));
        assertTrue(predicate.test(course));
    }

    @Test
    void test_courseNameDoesNotContainKeyword_returnsFalse() {
        Course course = new CourseBuilder().withName("Data Science").build();
        CourseNameContainsKeywordsPredicate predicate = new CourseNameContainsKeywordsPredicate(Arrays.asList("Math"));
        assertFalse(predicate.test(course));
    }

    @Test
    void test_emptyKeywordList_returnsFalse() {
        Course course = new CourseBuilder().withName("Data Science").build();
        CourseNameContainsKeywordsPredicate predicate = new CourseNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(course));
    }
}

