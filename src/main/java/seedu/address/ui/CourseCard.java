package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.course.Course;

/**
 * An UI component that displays information of a {@code Course}.
 */
public class CourseCard extends UiPart<Region> {

    private static final String FXML = "CourseListCard.fxml";

    public final Course course;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label courseId;

    /**
     * Creates a {@code CourseCode} with the given {@code Course} and index to display.
     */
    public CourseCard(Course course, int displayedIndex) {
        super(FXML);
        this.course = course;
        id.setText(displayedIndex + ". ");
        name.setText(course.getName().fullName);
        courseId.setText("Course ID: " + course.getCourseId().toString());
    }
}
