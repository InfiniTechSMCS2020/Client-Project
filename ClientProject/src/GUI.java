import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;

import java.util.Date;

public class GUI extends Application{       //TODO apply the date to csv file names, make autofill, edit GUI, use thread to do checking for autofill/Combobox
    static int numberOfRuns;
    Scene scene;
    Scene settingsScene;

    Button settings;

    Button goBack;
    Button blue;
    Button yellow;
    Button green;
    Button red;
    Button orange;
    Button purple;

    Button submitID;

    TextField idBox;
    ComboBox teachBox;
    TextField nameBox;
    TextField counsBox;
    TextField gradeBox;
    TextField other;


    ComboBox<String> reasonBox;

    Label studentID;
    Label currTeach;
    Label studName;
    Label couns;
    Label studGrade;
    Label reason;

    int ID;

    String[] listOfBackgrounds = {"colors/blue.css","colors/yellow.css","colors/green.css","colors/red.css","colors/orange.css","colors/purple.css",
    "programSheet.css"};

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage) throws Exception{
        numberOfRuns=0;


        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);



        settings = new Button();               //Make new window + add case for someone trying to open it multiple times
        settings.setText("Settings");
        grid.add(settings,3,9);

        //Text Boxes

        idBox= new TextField();
        grid.add(idBox,1,1);

        String[] teacher = {"Flowers", "Gerard", "Curran", "Lee", "Gerardles", "Gerardlist"};
        teachBox= new ComboBox();
        teachBox.getItems().add("Hi");
        grid.add(teachBox,4,1);
        boolean status = true;
        while(teachBox.onActionProperty().toString().substring(teachBox.onActionProperty().toString().indexOf("value") + 7).equals("null")) {
            for (int i = 0; i < teacher.length; i++) {
                if (teacher[i].contains((String) teachBox.getValue())) {
                    teachBox.getItems().add(teacher[i]);
                } else {
                    teachBox.getItems().remove(teacher[i]);
                }
            }
        }
        teachBox.setOnAction(e -> {
        System.out.println("hi");
        System.out.println(teachBox.onActionProperty().toString().substring(teachBox.onActionProperty().toString().indexOf("value") + 7));
    });
        System.out.println(teachBox.getOnAction());
        teachBox.setEditable(true);
        nameBox= new TextField();
        grid.add(nameBox,1,4);
        counsBox= new TextField();
        grid.add(counsBox,4,4);
        gradeBox= new TextField();
        grid.add(gradeBox,1,7);

        String[] reasonList = {"Schedule",
                "Academic Planning",
                "Personal, Social, or Emotional",
                "College and Career Information",
                "Got a Pass from Counselor",
                "Other"};
        reasonBox= new ComboBox();
        other = new TextField();
        reasonBox.getItems().addAll(reasonList);

        reasonBox.setOnAction( event -> {
            if (reasonBox.getValue().equals("Other")) {
                grid.add(other, 4, 8);
            } else {
                grid.getChildren().remove(other);
            }
        }
        );


        grid.add(reasonBox,4,7);

        studentID = new Label("Student ID");
        grid.add(studentID, 1, 0);
        currTeach = new Label("Current Teacher");
        grid.add(currTeach, 4, 0);
        studName = new Label("Student Name");
        grid.add(studName, 1, 3);
        couns = new Label("Counselor");
        grid.add(couns, 4, 3);
        studGrade = new Label("Student Grade");
        grid.add(studGrade, 1, 6);
        reason = new Label("Reason for visit");
        grid.add(reason, 4, 6);

        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Option 1", 25),
                new PieChart.Data("Option 2", 50),
                new PieChart.Data("Option 3", 25)
        );

        PieChart pieChart = new PieChart(pieData);
        BorderPane pane = new BorderPane();
        Scene scene2 = new Scene(pane, 1000,1000);
        pane.setCenter(pieChart);



        scene = new Scene(grid, 1920, 1080 );
        scene.getStylesheets().add("programSheet.css");
        stage.setScene(scene);
        stage.show();


        GridPane settingsGrid = new GridPane();
        goBack = new Button();
        settingsGrid.add(goBack,1,1);
        settingsGrid.setAlignment(Pos.CENTER);
        settingsScene = new Scene(settingsGrid,1920,1080);
        settingsScene.getStylesheets().add("programSheet.css");


        goBack.setOnAction( e -> {
            stage.setScene(scene);
        });

        //Color Buttons
        blue= new Button("Blue");
        settingsGrid.add(blue,1,2);
        yellow = new Button("Yellow");
        settingsGrid.add(yellow,1,3);
        green = new Button("Green");
        settingsGrid.add(green,1,4);
        red = new Button("Red");
        settingsGrid.add(red,1,5);
        purple = new Button("Purple");
        settingsGrid.add(purple,1,6);
        orange = new Button("Orange");
        settingsGrid.add(orange,1,7);

        //Change colors
        blue.setOnAction( e -> {
            scene.getStylesheets().add("colors/blue.css");
            settingsScene.getStylesheets().add("colors/blue.css");
            removeAllBut("colors/blue.css");
        });

        yellow.setOnAction( e -> {
            scene.getStylesheets().add("colors/yellow.css");
            settingsScene.getStylesheets().add("colors/yellow.css");
            removeAllBut("colors/yellow.css");
        });

        green.setOnAction( e -> {
            scene.getStylesheets().add("colors/green.css");
            settingsScene.getStylesheets().add("colors/green.css");
            removeAllBut("colors/green.css");
        });

        red.setOnAction( e -> {
            scene.getStylesheets().add("colors/red.css");
            settingsScene.getStylesheets().add("colors/red.css");
            removeAllBut("colors/red.css");
        });

        purple.setOnAction( e -> {
            scene.getStylesheets().add("colors/purple.css");
            settingsScene.getStylesheets().add("colors/purple.css");
            removeAllBut("colors/purple.css");
        });

        orange.setOnAction( e -> {
            scene.getStylesheets().add("colors/orange.css");
            settingsScene.getStylesheets().add("colors/orange.css");
            removeAllBut("colors/orange.css");
        });


        settings.setOnAction( e -> {
            stage.setScene(scene2);
        });
        Date date = new Date();
        int month= date.getMonth() + 2;
        int year = date.getYear() - 100;
        submitID= new Button("Press to submit");
        grid.add(submitID,3,10);
        String fileName = "C:\\Users\\jacob\\Documents\\" + String.valueOf(month) + "_" + String.valueOf(year) +".csv";
        System.out.println(fileName);
        submitID.setOnAction( e -> {
            ID= Integer.valueOf(idBox.getText());
            CSVWriter.writeFile(fileName , String.valueOf(ID));
            System.out.println(date.getYear());
            numberOfRuns++;
        });
    }
    private void removeAllBut(String file) {
        for (int i = 0; i < listOfBackgrounds.length; i++) {
            if (!file.equals(listOfBackgrounds[i])) {
                settingsScene.getStylesheets().remove(listOfBackgrounds[i]);
                scene.getStylesheets().remove(listOfBackgrounds[i]);
            }
        }
    }


}
