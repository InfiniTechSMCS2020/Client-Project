package ClientProj;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Date;

public class GUI extends Application{  
	public static int screenY = (int) Screen.getPrimary().getBounds().getMinY();//TODO  ASK FOR CONFIRMATION BEFORE CLOSING , Graph titles, Change the inputs to be able to enter lower case, Make file upload for OTHER the actual input in text box, Are you sure that you want to do this popup, Blank out fields when submit is pressed, Update teachers on keystroke , make autofill, edit GUI, use thread to do checking for autofill/Combobox
    static int[] inputs = new int[2];
    static int numberOfRuns;
    static boolean buttonClicked;
    static Scene scene;
    Scene settingsScene;

    Button seePieCharts;

    Button goBack;
    Button blue;
    Button yellow;
    Button green;
    Button red;
    Button orange;
    Button purple;

    Button submitID;

    Button buttonPie;

    TextField idBox;
    ComboBox teachBox;
    TextField nameBox;
    ComboBox counsBox;
    TextField gradeBox;
    TextField other;


    ComboBox<String> reasonBox;

    Label studentID;
    Label currTeach;
    Label studName;
    Label couns;
    Label studGrade;
    Label reason;

    static Scene adminScene;

    Scene scene2;

    PieChart pieChart;

    int ID;

    String[] listOfBackgrounds = {"colors/blue.css","colors/yellow.css","colors/green.css","colors/red.css","colors/orange.css","colors/purple.css",
    "programSheet.css"};
    public static Stage stageClone = new Stage();
    public static void main(String[] args){
        FileReaderTeacher.readFromFile("\\\\PV152-DATA\\STUHOME\\368677\\documents\\Teachers.txt");
        launch(args);
    }

    public void start(Stage stage) throws Exception{
    	System.out.println(screenY);
        stageClone = stage;

        numberOfRuns=0;

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);



        seePieCharts = new Button();//Make new window + add case for someone trying to open it multiple times
        seePieCharts.setText("See Pie Charts");

        //Text Boxes

        idBox= new TextField();
        idBox.setMinSize(400,40);
        grid.add(idBox,1,1);
        teachBox = new ComboBox();
        teachBox.setMinSize(400,40);
        grid.add(teachBox,4,1);

        for(int i=0; i<FileReaderTeacher.teacherList.size();i++) {
            teachBox.getItems().add(FileReaderTeacher.teacherList.get(i));
        }

       teachBox.setOnKeyReleased( e -> {
           System.out.println("Key Typed");
           System.out.println(teachBox.getValue());
           for(int j=0; j<FileReaderTeacher.teacherList.size();j++){
               if(!FileReaderTeacher.teacherList.get(j).contains(teachBox.getEditor().getText())){
                   teachBox.getItems().remove(FileReaderTeacher.teacherList.get(j));
               }
               else if(!teachBox.getItems().contains(FileReaderTeacher.teacherList.get(j))  &&  FileReaderTeacher.teacherList.get(j).contains( teachBox.getEditor().getText())){
                   teachBox.getItems().add(FileReaderTeacher.teacherList.get(j));

               }
               if(teachBox.getItems().isEmpty()){
                   for(int i=0; i<FileReaderTeacher.teacherList.size();i++) {
                       teachBox.getItems().add(FileReaderTeacher.teacherList.get(i));
                   }
                   teachBox.getEditor().setText("");
               }
           }
        }
       );

      
        /*
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
        */
        teachBox.setOnAction(e -> {
        System.out.println("hi");
        System.out.println(teachBox.onActionProperty().toString().substring(teachBox.onActionProperty().toString().indexOf("value") + 7));
    });
        System.out.println(teachBox.getOnAction());
        teachBox.setEditable(true);
        nameBox= new TextField();
        grid.add(nameBox,1,4);
        nameBox.setMinSize(400,40);


        counsBox= new ComboBox();
        grid.add(counsBox,4,4);
        counsBox.setMinSize(400,40);

        String[] counsList = {"Ms. Nelly Boishin",
                "Mr. David Gysberts",
                "Mrs. Barbara Martin",
                "Mrs. Melissa Nagy",
                "Mr. Edward Reed",
                };
        for(int c = 0; c<counsList.length; c++) {
            counsBox.getItems().add(counsList[c]);
        }

        gradeBox= new TextField();
        grid.add(gradeBox,1,7);
        gradeBox.setMinSize(400,40);


        String[] reasonList = {"Schedule",
                "Academic Planning",
                "Personal/Social/Emotional",
                "College and Career Information",
                "Got a Pass from Counselor",
                "Other"};
        reasonBox= new ComboBox();
        reasonBox.setMinSize(400,40);
        other = new TextField();
        other.setVisible(false);
        other.setMinSize(400,40);
        grid.add(other, 4, 8);
        reasonBox.getItems().addAll(reasonList);

        reasonBox.setOnAction( event -> {
            if (reasonBox.getValue().equals("Other")) {
                other.setVisible(true);
            } else {
                other.setVisible(false);
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

        //LISTS AND DATA OPERATIONS
        seePieCharts.setOnAction( e -> {
            pieChartData.create();
            stage.setScene(pieChartData.scene2);
        });
        pieChartData.submitTime.setOnAction( e -> {
            GUI.inputs[0]= Integer.valueOf(pieChartData.inputMonthBox.getText());
            GUI.inputs[1]= Integer.valueOf(pieChartData.inputYearBox.getText());
            pieChartData.execute();
            System.out.println(pieChartData.pane.getChildren());
            while(pieChartData.pane.getChildren().size()>8){
                        pieChartData.pane.getChildren().remove(pieChartData.pane.getChildren().size() -1);
                }
            pieChartData.pane.add(pieChartData.pieChart,1,1);
            pieChartData.pane.add(pieChartData.pieChartGrade,2,1);
        });

        Button admin = new Button("Admin Screen");
        grid.add(admin,7,10);
        scene = new Scene(grid, 1280, 1024 );
        scene.getStylesheets().add("programSheet.css");
        stage.setScene(scene);
        stage.show();


        GridPane settingsGrid = new GridPane();
        goBack = new Button();
        settingsGrid.add(goBack,1,1);
        settingsGrid.setAlignment(Pos.CENTER);
        settingsScene = new Scene(settingsGrid,1280,1024);
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

/*
        settings.setOnAction( e -> {
            stage.setScene(scene2);
        });
        */
        Date date = new Date();
        int month= date.getMonth() + 1;
        int year = date.getYear() - 100;
        submitID= new Button();
        submitID.getStyleClass().add("button-signIn");
        grid.add(submitID,3,9);
        String fileName = "\\\\PV152-DATA\\STUHOME\\368677\\documents\\" + String.valueOf(month) + "_" + String.valueOf(year) +".csv";
        System.out.println(fileName);
        submitID.setOnAction( e -> {
            String[] returnList= {idBox.getText(),(String) teachBox.getValue(), nameBox.getText(), (String)counsBox.getValue(),  gradeBox.getText(), reasonBox.getValue()};
            if(returnList[5].equals("Other")){
                returnList[5] = other.getText();
            }
            //System.out.println(FileReaderTeacher.emailList.get(FileReaderTeacher.teacherList.indexOf(returnList[1])));
            CSVWriter.writeFile(fileName , returnList);  //email To could be changed to teacherList.
            Emailer.email("infinitechSMCS2020@gmail.com","Infinity1238","jacobkiviat@gmail.com",idBox.getText() + " has just signed in", "Counseling Office Sign-In");
            numberOfRuns++;
            idBox.setText("");
            teachBox.getEditor().setText("");
            nameBox.setText("");
            counsBox.setValue("");
            gradeBox.setText("");
            reasonBox.setValue("");
            other.setText("");
            other.setVisible(false);//Add it so that the email sends to a teacher that is inputted
        });
        submitID.setMinSize(141,53);
        submitID.setMaxSize(141,53);

        Button back = new Button("Go to Sign-In Program");
        Label mainMenuAdmin = new Label("Welcome to PHS Counseling Admin Screen");
        mainMenuAdmin.getStylesheets().add("programSheet.css");
        mainMenuAdmin.getStyleClass().add("label-big");
        GridPane adminGrid = new GridPane();
        adminGrid.setVgap(10);
        adminGrid.setHgap(10);
        adminGrid.setAlignment(Pos.CENTER);
        adminGrid.add(mainMenuAdmin,0,0);
        adminGrid.add(seePieCharts,0,1);
        adminGrid.add(back,0,2);
        back.setOnAction(e ->{
            stage.setScene(scene);
        });
        adminScene = new Scene(adminGrid,1280,1024);
        adminScene.getStylesheets().add("programSheet.css");
        admin.setOnAction( e ->{
            AdminLogIn.show();
        });

        pieChartData.button.setOnAction( e ->{
            GUI.setPieSceneBack();
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
    public static void setAdminScene(){
        stageClone.setScene(adminScene);
    }
    public static void setPieSceneBack(){
        stageClone.setScene(adminScene);
    }




}
