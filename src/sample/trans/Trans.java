package sample.trans;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import sample.Matrix;
import sample.start.Controller;

import javax.xml.ws.FaultAction;
import java.util.ArrayList;
import java.util.List;

public class Trans {
    private Stage stage;
    private Matrix matrix;
    private int width;
    private int height;
    private List<Integer> list = new ArrayList<>();
    private ObservableList<Integer> observableList = FXCollections.observableList(list);

    @FXML
    ComboBox widthMatrix;
    @FXML
    ComboBox heightMatrix;
    @FXML
    Pane paneForMatrix;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initTrans () {
        observableList.addAll(2, 3, 4, 5, 6);
        widthMatrix.setItems(observableList);
        heightMatrix.setItems(observableList);
        matrix = new Matrix(0, 0, paneForMatrix);
    }

    public void initMatrix () {
        width = Integer.parseInt(widthMatrix.getValue().toString());
        height = Integer.parseInt(heightMatrix.getValue().toString());
        matrix = new Matrix(width, height, paneForMatrix);
        throw new NullPointerException("");
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

    public void goBack(ActionEvent actionEvent) throws Exception{
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("matrix",matrix.getList());
        System.out.println(jsonObject.toString());
    }


}
