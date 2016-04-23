package sample.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 400, 480);
        Controller controller = fxmlLoader.getController();
        controller.setStage(primaryStage);
        primaryStage.setTitle("MatrixMath");
        primaryStage.getIcons().add(new Image("file:resources/image/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setX((Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-200);
        primaryStage.setY((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)-240);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}