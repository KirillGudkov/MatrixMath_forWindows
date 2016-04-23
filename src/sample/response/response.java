package sample.response;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sample.dialog.Dialog;

import java.util.Random;

/**
 * Created by sillybird on 17.04.2016.
 */
public class Response {
    private Stage stage;
    private Label[][] text;
    private String code;
    private String message;
    private String result;
    private int scale;
    private ParallelTransition parallelTransition;

    @FXML
    Button ok;
    @FXML
    Pane pane;

    public void showResponse(ActionEvent actionEvent, String htmlResponse, int width, int height) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../response/response.fxml"));
        loader.setController(this);
        Parent root = (Parent) loader.load();
        Platform.runLater(()-> {stage = new Stage();});
            Platform.runLater(()-> {stage.setScene(new Scene(root));});
                Platform.runLater(()-> {stage.setMinHeight(400);});
                    Platform.runLater(()-> {stage.setMinWidth(600);});
                        Platform.runLater(()-> {stage.getIcons().add(new Image("file:resources/image/icon.png"));});
                            Platform.runLater(()-> {stage.setTitle("Ответ");});
                                Platform.runLater(()-> {stage.initModality(Modality.WINDOW_MODAL);});
                                    Platform.runLater(()-> {stage.setResizable(false);});
                                        Platform.runLater(()-> {stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());});
        parseJson(htmlResponse, width, height, actionEvent);
    }


    /**
     * @param actionEvent закрытие окна по нажатию кнопки "ок"
     */
    public void ok(ActionEvent actionEvent) {
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    /**--------------------------------------
     * PAY ATTENTION!
     * BE CAREFUL!
     ---------------------------------------*/


    /**
     * Этот метод сплошной говнокод, впрочем как и весь код в этом проекте :)
     * в общем алгоритм таков:
     * - парсим JSON
     * - за тем проверям существуют ли поля статус код и статус месседж
     * - далее в зависимости от кода выводим в UI ошибку, либо же смотрим далее
     * - а далее проверяем что лежит в ключе "result" елси массив то массив если объект то объект
     * - ну и опять же суём это все в UI
     **/

    public void parseJson(String htmlResponse, int width, int height, ActionEvent actionEvent) throws Exception {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(htmlResponse);
        JSONObject jsonObject = (JSONObject) object;

        if (jsonObject.get("status_code") != null && jsonObject.get("status_message") != null) {
            code = jsonObject.get("status_code").toString();
            message = jsonObject.get("status_message").toString();
            System.out.println("Код : " + code);
            System.out.println("Сообщение от сервера: " + message);

            if (code.equalsIgnoreCase("200")) {
                result = jsonObject.get("result").toString();
                System.out.println("Результат вычислений: " + result);

                if ((jsonObject.get("result")).getClass().toString().equalsIgnoreCase("class org.json.simple.JSONArray")) {
                    JSONArray arr = (JSONArray) jsonObject.get("result");

                    double[][] arrayResponse = new double[arr.size()][arr.size()];
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            arrayResponse[i][j] = Double.parseDouble(((JSONArray) arr.get(i)).get(j).toString());
                        }
                    }
                    text = new Label[width][height];
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            text[i][j] = new Label();
                            text[i][j].setAlignment(Pos.TOP_RIGHT);
                            text[i][j].setText(String.valueOf(arrayResponse[i][j]));
                            text[i][j].setLayoutX(10 + (j * 55));
                            text[i][j].setLayoutY(i * 45);
                            text[i][j].setStyle("-fx-min-width: 50px; -fx-min-height: 25px;-fx-alignment: center; -fx-background-color:  #3399FF; -fx-border-color: rgba(255, 255, 255, 0.6); " +
                                    "-fx-font-family: Yu Gothic UI Light; -fx-text-fill: white; ");
                            Random random = new Random(System.currentTimeMillis());
                            scale = random.nextInt((2001) - 700);
                            FadeTransition fadeTransition = new FadeTransition(Duration.millis(scale), text[i][j]);
                            fadeTransition.setFromValue(0);
                            fadeTransition.setToValue(1);
                            TranslateTransition translateTransition = new TranslateTransition(Duration.millis(scale), text[i][j]);
                            translateTransition.setFromY(250);
                            translateTransition.setToY(0);
                            parallelTransition = new ParallelTransition();
                            parallelTransition.getChildren().addAll(fadeTransition, translateTransition);
                            parallelTransition.play();
                            int finalI = i;
                            int finalJ = j;
                            Platform.runLater(()->{pane.getChildren().add(text[finalI][finalJ]);});
                        }
                    }
                    Platform.runLater(()->{stage.show();});

                } else {
                    Label lab = new Label();
                    lab.setStyle("-fx-min-width: 50px; -fx-min-height: 25px;-fx-alignment: center; -fx-background-color:  #3399FF; -fx-border-color: rgba(255, 255, 255, 0.6); " +
                            "-fx-font-family: Yu Gothic UI Light; -fx-text-fill: white; ");
                    lab.setText(jsonObject.get("result").toString());
                    Platform.runLater(()->{pane.getChildren().add(lab);});
                    Platform.runLater(()->{lab.setAlignment(Pos.CENTER);});
                    Platform.runLater(()->{stage.show();});
                }
            } else {
                Dialog dialog = new Dialog();
                Label label = new Label();
                label.setText(code + "; " + message);
                dialog.showDialog(stage, label);
            }
        } else if (jsonObject.get("error") != null) {
            Dialog dialog = new Dialog();
            Label label = new Label();
            label.setText(jsonObject.get("error").toString());
            dialog.showDialog(stage, label);
        }
    }
}
