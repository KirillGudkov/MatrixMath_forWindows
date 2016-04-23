package sample.dialog;

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
import javafx.stage.Window;

import java.security.acl.Owner;

/**
 * Created by sillybird on 17.04.2016.
 * Диалоговое окно, вызываемое любыми событиями происходящими на этапе вычисления
 * будь то ошибка или какой-либо некорректный ответ сервера.
 * в метод showDialog() передаётся предок и Label в который помещаем месседж
 */
public class Dialog {
    private Stage stage;
    @FXML
    Button ok;
    @FXML
    Pane paneForError;
    public void showDialog (Window owner, Label label) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../dialog/dialog.fxml"));
        loader.setController(this);
        Parent root = (Parent)loader.load();
        Platform.runLater(()->{stage = new Stage();});
        Platform.runLater(()->{stage.setScene(new Scene(root));});
            Platform.runLater(()->{stage.setMinHeight(120);});
                Platform.runLater(()->{stage.setMinWidth(400);});
                    Platform.runLater(()->{stage.getIcons().add(new Image("file:resources/image/error.png"));});
                        Platform.runLater(()->{stage.setTitle("error");});
                            Platform.runLater(()->{stage.initModality(Modality.WINDOW_MODAL);});
                                Platform.runLater(()->{stage.setResizable(false);});
                                    Platform.runLater(()->{stage.initOwner(owner);});
        label.setStyle("-fx-min-width: 380px; -fx-max-width: 380px; -fx-min-height: 90px; -fx-background-color:  rgba(0, 0, 0, 0); -fx-wrap-text: true; -fx-font-family: Yu Gothic UI Light; -fx-font-size: 14px; -fx-text-fill: white;");
        label.setWrapText(true);
        Platform.runLater(()->{paneForError.getChildren().add(label);});
        System.out.println(owner);
                                        Platform.runLater(()->{stage.show();});
    }

    public void ok(ActionEvent actionEvent) {
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }
}
