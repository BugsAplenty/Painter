import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

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
    private ColorPicker fillColorPicker;
    @FXML ColorPicker lineColorPicker;
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
        switch(shapeSelected) {
            case LINE -> {
                currentLine = new Line(e.getX(), e.getY(), e.getX(), e.getY());
                currentLine.setFill(fillColorPicker.getValue());
                currentLine.setStroke(lineColorPicker.getValue());
                drawPane.getChildren().add(currentLine);
            }
            case OVAL -> {
                currentOval = new Ellipse(e.getX(), e.getY(), 0, 0);
                currentOval.setFill(fillColorPicker.getValue());
                currentOval.setStroke(lineColorPicker.getValue());
                drawPane.getChildren().add(currentOval);
            }
            case RECTANGLE -> {
                currentRect = new Rectangle(e.getX(), e.getY(), 0, 0);
                currentRect.setFill(fillColorPicker.getValue());
                currentRect.setStroke(lineColorPicker.getValue());
                drawPane.getChildren().add(currentRect);
            }
        }
    }
    @FXML
    private void drawPaneMouseDragged(MouseEvent e) {
        switch(shapeSelected) {
            case LINE -> {
                currentLine.setEndX(e.getX());
                currentLine.setEndY(e.getY());
            }
            case OVAL -> {
                currentOval.setRadiusX(e.getX() - currentOval.getCenterX());
                currentOval.setRadiusY(e.getY() - currentOval.getCenterY());
            }
            case RECTANGLE -> {
                currentRect.setWidth(e.getX());
                currentRect.setHeight(e.getY());
            }
        }
    }
    @FXML
    private void redoEditMenuItemSelected() {

    }
    @FXML
    private void undoEditMenuItemSelected() {
        int count = drawPane.getChildren().size();
        if (count > 0) {
            drawPane.getChildren().remove(count - 1);
        }
    }
    @FXML
    private void clearEditMenuItemSelected() {
        drawPane.getChildren().clear();
    }
}