package seedu.address.model.course;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Course}'s {@code CourseName} matches any of the keywords given.
 */
public class CourseNameContainsKeywordsPredicate implements Predicate<Course> {
    private final List<String> keywords;

    public CourseNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Course course) {
        return keywords.stream()
                .anyMatch(keyword -> course.getName().containsIgnoreCase(keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CourseNameContainsKeywordsPredicate)) {
            return false;
        }
        CourseNameContainsKeywordsPredicate otherPredicate = (CourseNameContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return "CourseNameContainsKeywordsPredicate{"
                + "keywords=" + keywords
                + '}';
    }
}
