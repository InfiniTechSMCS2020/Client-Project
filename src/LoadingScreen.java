import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoadingScreen {
    static Scene scene;
        public static Label loading;
        public static String url = "file:abnormal.png";
        public static String url2= "https://pbs.twimg.com/profile_images/2535848270/Screen_shot_2012-08-23_at_3.18.56_PM_400x400.png";
        public static void show() {
                loading = new Label();
                loading.setText("Thank you for signing in!");
                Stage loadingStage = new Stage();

                loadingStage.initModality(Modality.APPLICATION_MODAL);
                loadingStage.setTitle("Confirmation");
                loadingStage.setMinWidth(250);
                loadingStage.setMinHeight(250);


                GridPane grid = new GridPane();
                grid.add(loading, 0, 0);
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setAlignment(Pos.CENTER);

                scene = new Scene(grid, 500, 500);
                scene.getStylesheets().add("cssfile.css");
                loadingStage.setScene(scene);
                loadingStage.show();
        }
    public static void setSheet(String sheet){
        scene.getStylesheets().add(sheet);
    }
    }



