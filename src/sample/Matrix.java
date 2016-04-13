package sample;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

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
    private TextField[][] matrix;

    public Matrix (int width, int height, Pane pane) {
        this.width = width;
        this.height = height;
        matrix = new TextField[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.matrix[i][j] = new TextField();
                pane.getChildren().add(this.matrix[i][j]);
                this.matrix[i][j].setLayoutX(10 + (j * 55));
                this.matrix[i][j].setLayoutY(i * 45);
                this.matrix[i][j].setPromptText("0.0");
                this.matrix[i][j].setStyle("-fx-max-width: 50px; -fx-background-color:  #339999; -fx-border-color: white; " +
                        "-fx-font-family: Yu Gothic UI Light; -fx-text-fill: white; ");
            }
        }
    }
    public void removeMatrix (Pane pane) {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                pane.getChildren().remove(this.matrix[i][j]);
                this.matrix[i][j] = null;
            }
        }
    }

    public List getList() {
        List<List> I = new ArrayList<>();
        List<Integer> J = new ArrayList<>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                J.add(Integer.parseInt(matrix[i][j].getText()));
            }
            I.add(J);
            J = new ArrayList<>();
        }
        return I;
    }
}
