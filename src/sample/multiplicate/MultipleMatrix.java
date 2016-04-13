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
import org.json.simple.JSONObject;
import sample.Matrix;
import sample.start.Controller;

import java.util.ArrayList;
import java.util.List;

public class MultipleMatrix {
    private Stage stage;
    private Matrix matrixOne;
    private Matrix matrixTwo;
    private Label labelSign;
    private String valueSign;
    private int widthOne;
    private int heightOne;
    private int widthTwo;
    private int heightTwo;
    private List<Integer> listOneBox = new ArrayList<>();
    private ObservableList<Integer> observableListOne = FXCollections.observableList(listOneBox);
    private List<Integer> listTwoBox = new ArrayList<>();
    private ObservableList<Integer> observableListTwo = FXCollections.observableList(listTwoBox);
    private List<String> listForSign = new ArrayList<>();
    private ObservableList<String> observableListSign = FXCollections.observableList(listForSign);
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

    public void initMultipleMatrix() {
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

        matrixOne = new Matrix(0, 0, paneForOne);
        matrixTwo = new Matrix(0, 0, paneForTwo);
    }

    public void initMatrixOne() {
        widthOne = Integer.parseInt(widthMatrixOne.getValue().toString());
        heightOne = Integer.parseInt(heightMatrixOne.getValue().toString());
        matrixOne = new Matrix(widthOne, heightOne, paneForOne);
        heightMatrixTwo.setDisable(false);
        widthMatrixTwo.setDisable(false);
        throw new NullPointerException("");
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

    public void initSign() {
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
    public void initMatrixTwo() {
        widthTwo = Integer.parseInt(widthMatrixTwo.getValue().toString());
        heightTwo = Integer.parseInt(heightMatrixTwo.getValue().toString());
        matrixTwo = new Matrix(widthTwo, heightTwo, paneForTwo);
        throw new NullPointerException();
    }

    public void widthMatrixOneChange(ActionEvent actionEvent) {
        matrixOne.removeMatrix(paneForOne);
        try {initMatrixOne();}
        catch (NullPointerException e) {}
    }

    public void heightMatrixOneChange(ActionEvent actionEvent) {
        matrixOne.removeMatrix(paneForOne);
        try {initMatrixOne();}
        catch (NullPointerException e) {}
    }

    public void heightMatrixTwoChange(ActionEvent actionEvent) {
        matrixTwo.removeMatrix(paneForTwo);
        try {initMatrixTwo();}
        catch (NullPointerException e) {}
    }

    public void widthMatrixTwoChange(ActionEvent actionEvent) {
        matrixTwo.removeMatrix(paneForTwo);
        try {initMatrixTwo();}
        catch (NullPointerException e) {}
    }

    public void signChange(ActionEvent actionEvent) {
        paneForSign.getChildren().remove(labelSign);
        labelSign = null;
        heightMatrixOne.setDisable(false);
        widthMatrixOne.setDisable(false);
        initSign();
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
        jsonObject.put("right",matrixTwo.getList());
        jsonObject.put("left",matrixOne.getList());
        jsonObject.put("sign",labelSign.getText());
        System.out.println(jsonObject.toString());
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
