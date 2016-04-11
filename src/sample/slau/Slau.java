package sample.slau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.start.Controller;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Slau {
    private Stage stage;
    private int selectedCountValue;
    private TextField[][] matrix;
    private TextField[] vector;
    List<Integer> list = new ArrayList<>();
    ObservableList<Integer> observableList = FXCollections.observableList(list);

    @FXML
    private ComboBox countValue;
    @FXML
    private Button ButtonBack;
    @FXML
    AnchorPane anchor;
    @FXML
    Pane paneForValue;
    @FXML
    Pane paneForVector;

    public void Slau() {
        observableList.addAll(2, 3, 4, 5);
        countValue.setItems(observableList);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void InitMatrix() {
        selectedCountValue = Integer.parseInt(countValue.getValue().toString());
        matrix = new TextField[selectedCountValue][selectedCountValue];
        vector = new TextField[selectedCountValue];
        for (int i = 0; i < selectedCountValue; i++) {
            for (int j = 0; j < selectedCountValue; j++) {
                matrix[i][j] = new TextField();
                paneForValue.getChildren().add(matrix[i][j]);
                matrix[i][j].setId("value" + i + "_" + j);
                matrix[i][j].setLayoutX(10 + (j * 55));
                matrix[i][j].setLayoutY(i * 45);
                matrix[i][j].setPromptText("0.0");
                matrix[i][j].setStyle("-fx-max-width: 50px; -fx-background-color: #666699; -fx-border-color: white; " +
                                        "-fx-font-family: Yu Gothic UI Light; -fx-text-fill: white; ");
            }
            vector[i] = new TextField();
            paneForVector.getChildren().add(vector[i]);
            vector[i].setLayoutY(i * 45);
            vector[i].setPromptText("0.0");
            vector[i].setStyle("-fx-max-width: 50px; -fx-background-color: #666699; -fx-border-color: white;" +
                    " -fx-font-family: Yu Gothic UI Light; -fx-text-fill: white; ");
        }
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
    }

    public void countChange(ActionEvent actionEvent) {
        for (int i = 0; i < selectedCountValue; i++) {
            for (int j = 0; j < selectedCountValue; j++) {
                paneForValue.getChildren().remove(matrix[i][j]);
                matrix[i][j] = null;
            }
            paneForVector.getChildren().remove(vector[i]);
            vector[i] = null;
        }
        InitMatrix();
    }
}
