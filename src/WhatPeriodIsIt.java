import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WhatPeriodIsIt {
    static String currentPeriod;
    public static void show() {

        Stage pdStage = new Stage();

        pdStage.initModality(Modality.APPLICATION_MODAL);
        pdStage.setTitle("Period");
        pdStage.setMinWidth(250);
        pdStage.setMinHeight(250);

        Label label = new Label("Please enter what period it currently is");
        Label label2 = new Label("");
        label2.setVisible(false);

        ComboBox<String> choice = new ComboBox<String>();
        for (int s = 1; s < 9; s++) {
            choice.getItems().add(String.valueOf(s));
        }
        choice.getItems().add("Not during class");

        Button button = new Button("Sign-In");

        GridPane grid = new GridPane();
        grid.add(label, 0, 0);
        grid.add(choice, 0, 1);
        grid.add(button, 0, 2);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 500, 500);
        scene.getStylesheets().

                add("cssfile.css");
        pdStage.setScene(scene);
        pdStage.show();

        button.setOnAction(e ->

        {
            currentPeriod = choice.getValue();
            pdStage.close();
        });
        pdStage.setOnCloseRequest(e ->{
            if(choice.getValue().isEmpty()){
                pdStage.close();
                pdStage.show();
            }else{
                currentPeriod = choice.getValue();
            }
        });
    }
}

