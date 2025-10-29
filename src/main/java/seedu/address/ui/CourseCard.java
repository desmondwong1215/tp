package seedu.address.ui;

import javafx.beans.binding.Bindings;
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
    private Label courseIdValue;
    @FXML
    private Label courseSizeLabel;
    @FXML
    private Label courseSizeValue;

    /**
     * Creates a {@code CourseCard} with the given {@code Course} and index to display.
     */
    public CourseCard(Course course, int displayedIndex) {
        super(FXML);
        this.course = course;
        id.setText(displayedIndex + ". ");
        name.setText(course.getName().fullName);
        courseIdValue.setText(course.getCourseId().value);

        courseSizeLabel.setText("Student(s) enrolled:");
        courseSizeValue.textProperty().bind(
                Bindings.size(course.getStudentList().asUnmodifiableObservableList()).asString());
    }
}
