package sample.multOnNumber;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sample.Matrix;
import sample.start.Controller;

import java.util.ArrayList;
import java.util.List;

public class MultOnNumber {
    private Stage stage;
    private int width;
    private int height;
    private double number;
    private Matrix matrix;
    private List<Integer> listOneBox = new ArrayList<>();
    private ObservableList<Integer> observableListOne = FXCollections.observableList(listOneBox);
    private List<Integer> listTwoBox = new ArrayList<>();
    private ObservableList<Integer> observableListTwo = FXCollections.observableList(listTwoBox);

    @FXML
    Pane paneForMatrix;
    @FXML
    ComboBox widthMatrix;
    @FXML
    ComboBox heightMatrix;
    @FXML
    TextField factor;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initMultOnNumber (){
        observableListOne.addAll(2, 3, 4, 5, 6);
        observableListTwo.addAll(2, 3, 4, 5, 6);
        widthMatrix.setItems(observableListOne);
        heightMatrix.setItems(observableListTwo);
        matrix = new Matrix(0, 0, paneForMatrix);
    }

    public void initMatrix (){
        width = Integer.parseInt(widthMatrix.getValue().toString());
        height = Integer.parseInt(heightMatrix.getValue().toString());
        matrix = new Matrix(width, height, paneForMatrix);
        throw new NullPointerException();
    }

    public void heightMatrixChange(ActionEvent actionEvent) {
            matrix.removeMatrix(paneForMatrix);
        try {initMatrix();}
        catch (NullPointerException e) {}
    }

    public void widthMatrixChange(ActionEvent actionEvent) {
            matrix.removeMatrix(paneForMatrix);
        try {initMatrix();}
        catch (NullPointerException e) {}
    }

    public void goBack(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../start/sample.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 500);
        Controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setTitle("matrixMath");
        stage.setScene(scene);
        stage.show();
    }

    public void goForward(ActionEvent actionEvent) {
        number = Integer.parseInt(factor.getText());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("matrix",matrix.getList());
        jsonObject.put("factor",number);
        System.out.println(jsonObject.toString());
    }
}
