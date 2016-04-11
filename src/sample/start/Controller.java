package sample.start;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import sample.multOnNumber.MultOnNumber;
import sample.multiplicate.MultipleMatrix;
import sample.slau.Slau;
import sample.trans.Trans;

public class Controller {
    @FXML
    RadioButton slau;
    @FXML
    RadioButton multipleMatrix;
    @FXML
    RadioButton multOnNumber;
    @FXML
    RadioButton trans;
    @FXML
    Button nextButton;

    ToggleGroup sdf = new ToggleGroup();
    private Stage stage;
    private String pressedRadio;


    /* Инциализация группы радиокнопок в главном окне.
       каждому радиобатону сетТоглГруп за мой счёт!*/
    public void initialize () {
        slau.setToggleGroup(sdf);
        multipleMatrix.setToggleGroup(sdf);
        multOnNumber.setToggleGroup(sdf);
        trans.setToggleGroup(sdf);
        nextButton.setDisable(true);
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void slau(ActionEvent actionEvent) {
        nextButton.setDisable(false);
    }
    public void multipleMatrix(ActionEvent actionEvent) {
        nextButton.setDisable(false);
    }
    public void multOnNumber(ActionEvent actionEvent) {
        nextButton.setDisable(false);
    }
    public void trans(ActionEvent actionEvent) {
        nextButton.setDisable(false);
    }

    /*
      По нажанию кнопки достаём fx:id радиобатона и
      согласно свитчу рисуем соответствующую сцену
    */
    public void Go(ActionEvent actionEvent) throws Exception{
        pressedRadio = ((RadioButton)sdf.getSelectedToggle()).getId();
        switch (pressedRadio) {
            case "slau": {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../slau/Slau.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 900, 500);
                Slau controller = fxmlLoader.getController();
                controller.setStage(stage);
                stage.setTitle("Решение СЛАУ");
                stage.setScene(scene);
                controller.Slau();
                break;
            }
            case "multipleMatrix": {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../multiplicate/MultipleMatrix.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 900, 500);
                MultipleMatrix controller = fxmlLoader.getController();
                controller.setStage(stage);
                stage.setTitle("Умножение матриц");
                stage.setScene(scene);
                break;
            }
            case "multOnNumber": {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../multOnNumber/MultOnNumber.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 900, 500);
                MultOnNumber controller = fxmlLoader.getController();
                controller.setStage(stage);
                stage.setTitle("Умножение матрицы на число");
                stage.setScene(scene);
                break;
            }
            case "trans": {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../trans/Trans.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 900, 500);
                Trans controller = fxmlLoader.getController();
                controller.setStage(stage);
                stage.setTitle("Транспонироване матрицы");
                stage.setScene(scene);
                break;
            }
        }
    }
}