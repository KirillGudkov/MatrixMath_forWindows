package sample;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.*;

/**
 * Created by sillybird on 13.04.2016.
 * class Matrix. Every time, when user want to find solution, he will choose size of matrix, and constuctor Matrix()
 * will create instance of the class, wich will know where paste his text fields.
 * When user want to change size of matrix, he'll click on combobox, and method removeMatrix() will make clear pane and will call
 * method, wich will initialize new matrix;
 */
public class Matrix {
    private int height;
    private int width;

    private RestrictiveTextField[][] matrix;
    private ParallelTransition parallelTransition;
    private int scale;

    public Matrix (int width, int height, Pane pane) {
        this.width = width;
        this.height = height;

        matrix = new RestrictiveTextField[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                matrix[i][j] = new RestrictiveTextField();
                matrix[i][j].setMaxLength(4);
                matrix[i][j].setRestrict("[0-9.]");
                matrix[i][j].setLayoutX(10 + (j * 55));
                matrix[i][j].setLayoutY(i * 45);
                matrix[i][j].setPromptText("0.0");
                matrix[i][j].setStyle("-fx-max-width: 50px; -fx-background-color:  #3399FF; -fx-border-color: rgba(255, 255, 255, 0.6); " +
                                            "-fx-font-family: Yu Gothic UI Light; -fx-text-fill: white; ");
            }
        }
        for (int j = 0; j < this.width; j++) {
            for (int i = 0; i < this.height; i++){
                Random random = new Random(System.currentTimeMillis());
                scale = random.nextInt((2001)-700);
                pane.getChildren().add(matrix[i][j]);
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(scale), matrix[i][j]);
                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(1);
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(scale), matrix[i][j]);
                translateTransition.setFromY(250);
                translateTransition.setToY(0);
                parallelTransition = new ParallelTransition();
                parallelTransition.getChildren().addAll(fadeTransition,translateTransition);
                parallelTransition.play();
            }
        }
    }



    public void removeMatrix (Pane pane) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                pane.getChildren().remove(matrix[i][j]);
                this.matrix[i][j] = null;
            }
        }
    }

    public int checkValue () {
        int flag = 0;
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (matrix[i][j].getText().isEmpty()) {
                    flag = 1;
                }
            }
        }
        return flag;
    }

    public List getList() {
        List<List> I = new ArrayList<>();
        List<Double> J = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                J.add(Double.parseDouble(matrix[i][j].getText()));
            }
            I.add(J);
            J = new ArrayList<>();
        }
        return I;
    }
}
