package sample.slau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import sample.Matrix;
import sample.start.Controller;
import java.util.ArrayList;
import java.util.List;

public class Slau {
    private Stage stage;
    private int selectedCountValue;
    private Matrix matrix;
    private Matrix vector;
    private List<Integer> list = new ArrayList<>();
    private ObservableList<Integer> observableList = FXCollections.observableList(list);

    @FXML
    private ComboBox countValue;
    @FXML
    AnchorPane anchor;
    @FXML
    Pane paneForValue;
    @FXML
    Pane paneForVector;

    public void initSlau() {
        observableList.addAll(2, 3, 4, 5, 6);
        countValue.setItems(observableList);
        matrix = new Matrix(0, 0, paneForValue);
        vector = new Matrix(0,0, paneForVector);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void initMatrix() {
        selectedCountValue = Integer.parseInt(countValue.getValue().toString());
        matrix = new Matrix(selectedCountValue, selectedCountValue, paneForValue);
        vector = new Matrix(1,selectedCountValue, paneForVector);
    }
    public void countChange(ActionEvent actionEvent) {
        matrix.removeMatrix(paneForValue);
        vector.removeMatrix(paneForVector);
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("vector",vector.getList());
        jsonObject.put("matrix",matrix.getList());
        System.out.println(jsonObject.toString());
    }
}
