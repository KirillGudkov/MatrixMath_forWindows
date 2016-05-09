package sample;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sillybird on 05.05.2016.
 */
public class Vector {
    private int size;
    private RestrictiveTextField [] vector;
    private ParallelTransition parallelTransition;
    private int scale;

    public Vector (int size, Pane pane) {
        this.size = size;
        vector = new RestrictiveTextField[size];

        for (int i = 0; i < this.size; i++) {
            vector[i] = new RestrictiveTextField();
            vector[i].setMaxLength(4);
            vector[i].setRestrict("[0-9.]");
            vector[i].setLayoutX(10 + (i * 55));
            vector[i].setPromptText("0.0");
            vector[i].setStyle("-fx-max-width: 50px; -fx-background-color:  #3399FF; -fx-border-color: rgba(255, 255, 255, 0.6); " +
                    "-fx-font-family: Yu Gothic UI Light; -fx-text-fill: white; ");
        }
        for (int i = 0; i < this.size; i++) {
                Random random = new Random(System.currentTimeMillis());
                scale = random.nextInt((2001)-700);
                pane.getChildren().add(vector[i]);
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(scale), vector[i]);
                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(1);
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(scale), vector[i]);
                translateTransition.setFromY(250);
                translateTransition.setToY(0);
                parallelTransition = new ParallelTransition();
                parallelTransition.getChildren().addAll(fadeTransition,translateTransition);
                parallelTransition.play();
        }
    }

    public void removeVector (Pane pane) {
        for (int i = 0; i < this.size; i++) {
                pane.getChildren().remove(vector[i]);
                this.vector[i] = null;
        }
    }

    public int checkValue () {
        int flag = 0;
        for (int i = 0; i < this.size; i++) {
                if (vector[i].getText().isEmpty()) {
                    flag = 1;
                }
        }
        return flag;
    }

    public List getList() {
        List<Double> I = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
                I.add(Double.parseDouble(vector[i].getText()));
            }
        return I;
    }
}
