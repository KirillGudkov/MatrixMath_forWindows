package sample.slau;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import sample.Client;
import sample.Matrix;
import sample.Vector;
import sample.dialog.Dialog;
import sample.response.Response;
import sample.start.Controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Slau {
    private Stage stage;
    private Pane forLoad = new Pane();
    private int selectedCountValue;
    private Matrix matrix;
    private Vector vector;
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
    @FXML
    Button next;

    /**
     * я бы конечно назвал этот метод initialize(), но чёт не хочу :)
     * закладываем в комбобокс варианты размеров матрицы
     */
    public void initSlau() {
        observableList.addAll(2, 3, 4, 5, 6);
        countValue.setItems(observableList);
        matrix = new Matrix(0, 0, paneForValue);
        vector = new Vector(0, paneForVector);
        next.setDisable(true);
    }

    public void showLoading () {
        javafx.scene.image.Image image = new javafx.scene.image.Image("file:resources/image/anim.gif");
        ImageView imageView = new ImageView(image);
        imageView.setX(360);
        imageView.setY(180);
        forLoad.setStyle("-fx-background-color: #3399FF; -fx-min-width: 900px; -fx-min-height: 500px;");
        forLoad.getChildren().add(imageView);
        Platform.runLater(()-> {anchor.getChildren().add(forLoad);});
    }

    public void hideLoading () {
        Platform.runLater(()->{anchor.getChildren().remove(forLoad);});
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void initMatrix() {
        selectedCountValue = Integer.parseInt(countValue.getValue().toString());
        matrix = new Matrix(selectedCountValue, selectedCountValue, paneForValue);
    }

    public void initVector() {
        selectedCountValue = Integer.parseInt(countValue.getValue().toString());
        vector = new Vector(selectedCountValue, paneForVector);
    }

    public void countChange(ActionEvent actionEvent) {
        next.setDisable(false);
        matrix.removeMatrix(paneForValue);
        vector.removeVector(paneForVector);
        try {
            initMatrix();
            initVector();
        }
        catch (NullPointerException e) {}
    }
    public void goBack(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../start/sample.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 400, 480);
        Controller controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setTitle("matrixMath");
        stage.setX((Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-200);
        stage.setY((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)-240);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void goForward(ActionEvent actionEvent) throws Exception{
        if (matrix.checkValue() == 0 && vector.checkValue() == 0) {
            Thread resp = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("matrix", matrix.getList());
                        jsonObject.put("vector", vector.getList());
                        System.out.println(jsonObject.toString());
                        Client client = new Client();
                        Response response = new Response();
                        response.showResponse(actionEvent, client.initConnection(jsonObject, "solve", stage), stage, Integer.parseInt(countValue.getValue().toString()), Integer.parseInt(countValue.getValue().toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread load = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        showLoading();
                        resp.start();
                        resp.join();
                        hideLoading();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            load.start();
        }
        else {
            Dialog dialog = new Dialog();
            Label label = new Label();
            label.setText("Вы не заполнили одно или несколько полей!");
            dialog.showDialog(stage, label);
        }
    }
}
