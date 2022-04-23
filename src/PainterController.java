import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class PainterController implements Initializable{
    public ToggleButton ovalToggleBtn;
    public MenuItem undoEditMenuItem;
    public ToggleButton rectToggleBtn;
    public ToggleButton lineToggleBtn;
    public ToggleGroup shapeToggleGroup;
    public MenuItem redoEditMenuItem;
    public MenuItem clearEditMenuItem;

    private enum ShapeSelected {
        OVAL, RECTANGLE, LINE
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
    private void drawPaneMousePressed(MouseEvent e) {
        if (shapeSelected != null) {
            switch(shapeSelected) {
                case LINE:
                    currentLine = new Line(e.getX(), e.getY(), e.getX(), e.getY());
                    currentLine.setFill(fillColorPicker.getValue());
                    currentLine.setStroke(lineColorPicker.getValue());
                    drawPane.getChildren().add(currentLine);
                    break;
                case OVAL:
                    currentOval = new Ellipse(e.getX(), e.getY(), 0, 0);
                    currentOval.setFill(fillColorPicker.getValue());
                    currentOval.setStroke(lineColorPicker.getValue());
                    drawPane.getChildren().add(currentOval);
                    break;
                case RECTANGLE:
                    currentRect = new Rectangle(e.getX(), e.getY(), 0, 0);
                    currentRect.setFill(fillColorPicker.getValue());
                    currentRect.setStroke(lineColorPicker.getValue());
                    drawPane.getChildren().add(currentRect);
                    break;
            }
        }
    }
    @FXML
    private void drawPaneMouseDragged(MouseEvent e) {
        if (shapeSelected != null) {
            switch (shapeSelected) {
                case LINE:
                    if (currentLine != null) {
                        drawLine(e);
                    }
                    break;
                case OVAL:
                    if (currentOval != null) {
                        drawOval(e);
                    }
                    break;
                case RECTANGLE:
                    if (currentRect != null) {
                        drawRect(e);
                    }
                    break;
            }
        }
    }

    private void drawRect(MouseEvent e) {
        double dX = e.getX() - currentRect.getX();
        double dY = e.getY() - currentRect.getY();
        if(dX < 0) {
            currentRect.setTranslateX(dX);
            currentRect.setWidth(-dX);
        } else {
            currentRect.setTranslateX(0);
            currentRect.setWidth(dX);
        }
        if (dY < 0) {
            currentRect.setTranslateY(dY);
            currentRect.setHeight(-dY);
        } else {
            currentRect.setTranslateY(0);
            currentRect.setHeight(dY);
        }
    }

    private void drawOval(MouseEvent e) {
        currentOval.setRadiusX(Math.abs(e.getX() - currentOval.getCenterX()));
        currentOval.setRadiusY(Math.abs(e.getY() - currentOval.getCenterY()));
    }

    private void drawLine(MouseEvent e) {
        currentLine.setEndX(e.getX());
        currentLine.setEndY(e.getY());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillColorPicker.setValue(Color.WHITE);
        lineColorPicker.setValue(Color.BLACK);
    }
}