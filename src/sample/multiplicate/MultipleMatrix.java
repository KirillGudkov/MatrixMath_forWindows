package sample.multiplicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.start.Controller;

import java.util.ArrayList;
import java.util.List;

public class MultipleMatrix {
    private Stage stage;
    private TextField[][] matrixOne;
    private TextField[][] matrixTwo;
    private Label labelSign;
    private String valueSign;
    private int widthOne;
    private int heightOne;
    private int widthTwo;
    private int heightTwo;
    List<Integer> listOneBox = new ArrayList<>();
    ObservableList<Integer> observableListOne = FXCollections.observableList(listOneBox);
    List<Integer> listTwoBox = new ArrayList<>();
    ObservableList<Integer> observableListTwo = FXCollections.observableList(listTwoBox);
    List<String> listForSign = new ArrayList<>();
    ObservableList<String> observableListSign = FXCollections.observableList(listForSign);
    @FXML
    ComboBox widthMatrixOne;
    @FXML
    ComboBox heightMatrixOne;
    @FXML
    ComboBox widthMatrixTwo;
    @FXML
    ComboBox heightMatrixTwo;
    @FXML
    Pane paneForOne;
    @FXML
    Pane paneForTwo;
    @FXML
    Pane paneForSign;
    @FXML
    ComboBox sign;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void MultipleMatrix() {
        observableListOne.addAll(2, 3, 4, 5, 6);
        widthMatrixOne.setItems(observableListOne);
        heightMatrixOne.setItems(observableListOne);
        heightMatrixOne.setDisable(true);
        widthMatrixOne.setDisable(true);

        observableListTwo.addAll(2, 3, 4, 5, 6);
        widthMatrixTwo.setItems(observableListTwo);
        heightMatrixTwo.setItems(observableListTwo);
        heightMatrixTwo.setDisable(true);
        widthMatrixTwo.setDisable(true);

        observableListSign.addAll("+", "-", "*");
        sign.setItems(observableListSign);
    }

    public void InitMatrixOne() {
        widthOne = Integer.parseInt(widthMatrixOne.getValue().toString());
        heightOne = Integer.parseInt(heightMatrixOne.getValue().toString());
        matrixOne = new TextField[heightOne][widthOne];
            for (int i = 0; i < heightOne; i++) {
                for (int j = 0; j < widthOne; j++) {
                    matrixOne[i][j] = new TextField();
                    paneForOne.getChildren().add(matrixOne[i][j]);
                    matrixOne[i][j].setLayoutX(10 + (j * 55));
                    matrixOne[i][j].setLayoutY(i * 45);
                    matrixOne[i][j].setPromptText("0.0");
                    matrixOne[i][j].setStyle("-fx-max-width: 50px; -fx-background-color:  #339999; -fx-border-color: white; " +
                            "-fx-font-family: Yu Gothic UI Light; -fx-text-fill: white; ");
                }
            }
        heightMatrixTwo.setDisable(false);
        widthMatrixTwo.setDisable(false);
//      switch (valueSign) {
//            case "+": {
//                widthMatrixTwo.getSelectionModel().select(Integer.parseInt(widthMatrixOne.getValue().toString()));
//                heightMatrixTwo.getSelectionModel().select(Integer.parseInt(heightMatrixOne.getValue().toString()));
//            }
//            case "-": {
//                widthMatrixTwo.getSelectionModel().select(Integer.parseInt(widthMatrixOne.getValue().toString()));
//                heightMatrixTwo.getSelectionModel().select(Integer.parseInt(heightMatrixOne.getValue().toString()));
//            }
//            case "*": {
//                heightMatrixTwo.getSelectionModel().select(Integer.parseInt(widthMatrixOne.getValue().toString()));
//                widthMatrixOne.setDisable(false);
//           }
//        }
    }

    public void InitSign() {
        valueSign = sign.getValue().toString();
        labelSign = new Label();
        paneForSign.getChildren().add(labelSign);
        labelSign.setLayoutX(0);
        labelSign.setLayoutY(0);
        labelSign.setText(valueSign);
        labelSign.setStyle("-fx-min-width: 30px; -fx-min-height: 10px; -fx-background-color:  #339999;    " +
                           "-fx-font-family: Yu Gothic UI Light; -fx-text-fill: white; -fx-font-size: 25; " +
                           "-fx-alignment: center" +
                           "");
    }
    public void InitMatrixTwo() {
        widthTwo = Integer.parseInt(widthMatrixTwo.getValue().toString());
        heightTwo = Integer.parseInt(heightMatrixTwo.getValue().toString());
        matrixTwo = new TextField[heightTwo][widthTwo];
            for (int i = 0; i < heightTwo; i++) {
                for (int j = 0; j < widthTwo; j++) {
                    matrixTwo[i][j] = new TextField();
                    paneForTwo.getChildren().add(matrixTwo[i][j]);
                    matrixTwo[i][j].setLayoutX(10 + (j * 55));
                    matrixTwo[i][j].setLayoutY(i * 45);
                    matrixTwo[i][j].setPromptText("0.0");
                    matrixTwo[i][j].setStyle("-fx-max-width: 50px; -fx-background-color:  #339999; -fx-border-color: white; " +
                                             "-fx-font-family: Yu Gothic UI Light; -fx-text-fill: white; " +
                                             "");
                }
            }
    }

    public void widthMatrixOneChange(ActionEvent actionEvent) {
        for (int i = 0; i < heightOne; i++) {
            for (int j = 0; j < widthOne; j++) {
                paneForOne.getChildren().remove(matrixOne[i][j]);
                matrixOne[i][j] = null;
            }
        }
        InitMatrixOne();
    }

    public void heightMatrixOneChange(ActionEvent actionEvent) {
        for (int i = 0; i < heightOne; i++) {
            for (int j = 0; j < widthOne; j++) {
                paneForOne.getChildren().remove(matrixOne[i][j]);
                matrixOne[i][j] = null;
            }
        }
        InitMatrixOne();
    }

    public void heightMatrixTwoChange(ActionEvent actionEvent) {
        for (int i = 0; i < heightTwo; i++) {
            for (int j = 0; j < widthTwo; j++) {
                paneForTwo.getChildren().remove(matrixTwo[i][j]);
                matrixTwo[i][j] = null;
            }
        }
        InitMatrixTwo();
    }

    public void widthMatrixTwoChange(ActionEvent actionEvent) {
        for (int i = 0; i < heightTwo; i++) {
            for (int j = 0; j < widthTwo; j++) {
                paneForTwo.getChildren().remove(matrixTwo[i][j]);
                matrixTwo[i][j] = null;
            }
        }
        InitMatrixTwo();
    }

    public void signChange(ActionEvent actionEvent) {
        paneForSign.getChildren().remove(labelSign);
        labelSign = null;
        heightMatrixOne.setDisable(false);
        widthMatrixOne.setDisable(false);
        InitSign();
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

//    public void observer () {
//        switch (valueSign) {
//            case "+": {
//                widthMatrixTwo.getSelectionModel().select(Integer.parseInt(widthMatrixOne.getValue().toString()));
//                heightMatrixTwo.getSelectionModel().select(Integer.parseInt(heightMatrixOne.getValue().toString()));
//            }
//            case "-": {
//            }
//            case "*": {
//           }
//        }
//    }
}
