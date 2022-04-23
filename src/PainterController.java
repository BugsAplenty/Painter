import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PainterController {
    private enum ShapeSelected {
        OVAL,
        RECTANGLE,
        LINE
    }
    private ShapeSelected shapeSelected;

    @FXML
    private Line currentLine;
    @FXML
    private Ellipse currentOval;
    @FXML
    private Rectangle currentRect;
    @FXML
    private ToggleButton lineToggleBtn;

    @FXML
    private ToggleButton rectToggleBtn;

    @FXML
    private ToggleButton circleToggleBtn;

    @FXML
    private ColorPicker fillColorPicker;
    @FXML ColorPicker lineColorPicker;
    @FXML
    private MenuItem undoEditMenuItem;

    @FXML
    private MenuItem redoEditMenuItem;

    @FXML
    private Pane drawPane;

    @FXML
    private void rectToggleBtnPushed() {
        shapeSelected = ShapeSelected.RECTANGLE;
    }
    @FXML
    private void ovalToggleBtnPushed() {
        shapeSelected = ShapeSelected.OVAL;
    }

    @FXML
    private void lineToggleBtnPushed() {
        shapeSelected = ShapeSelected.LINE;
    }

    @FXML
    private void drawPaneMouseClicked(MouseEvent e) {
        if (shapeSelected == ShapeSelected.LINE) {
            currentLine = new Line(e.getX(), e.getY(), e.getX(), e.getY());
            currentLine.setFill(fillColorPicker.getValue());
            currentLine.setStroke(lineColorPicker.getValue());
            drawPane.getChildren().add(currentLine);
        } else if (shapeSelected == ShapeSelected.OVAL) {
            currentOval = new Ellipse(e.getX(), e.getY(), 0, 0);
            currentLine.setFill(fillColorPicker.getValue());
            currentOval.setStroke(lineColorPicker.getValue());
            drawPane.getChildren().add(currentOval);
        } else if (shapeSelected == ShapeSelected.RECTANGLE) {
            currentRect = new Rectangle(e.getX(), e.getY(), 0, 0);
            currentLine.setFill(fillColorPicker.getValue());
            currentOval.setStroke(lineColorPicker.getValue());
            drawPane.getChildren().add(currentRect);
        }
    }
}