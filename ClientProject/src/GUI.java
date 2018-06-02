import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;                            //NOTE FIX ERRORS ABOUT 8TH PERIOD AS WELL AS WHAT TO DO AT ANY OTHER TIME THAN THE SCHOOL DAY- POSSIBLY MAKE TIME BETWEEN CLASSES EXEMPT FROM AUTOFILL FOR TEACHERS-  FIX GUI-  FIX AUTOFILL (CLearing at the end, confirmation message, etc)
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

public class GUI extends Application{       //TODO  ASK FOR CONFIRMATION BEFORE CLOSING , Graph titles, Change the inputs to be able to enter lower case, Make file upload for OTHER the actual input in text box, Are you sure that you want to do this popup, Blank out fields when submit is pressed, Update teachers on keystroke , make autofill, edit GUI, use thread to do checking for autofill/Combobox
    static int[] inputs = new int[2];
    static int numberOfRuns;
    static boolean buttonClicked;
    static Scene scene;
    static boolean isAutofill;
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

    ComboBox idBox;
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

    static Scene adminScene;

    Scene scene2;

    PieChart pieChart;

    int ID;

    String[] listOfBackgrounds = {"colors/blue.css","colors/yellow.css","colors/green.css","colors/red.css","colors/orange.css","colors/purple.css",
    "programSheet.css"};
    public static Stage stageClone = new Stage();
    public static void main(String[] args){
        FileReaderTeacher.readFromFile("\\C:\\Users\\jacob\\Documents\\Teachers.txt");
        launch(args);
    }

    public void start(Stage stage) throws Exception{
        stageClone = stage;

        numberOfRuns=0;

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);



        seePieCharts = new Button();//Make new window + add case for someone trying to open it multiple times
        seePieCharts.setText("See Pie Charts");

        //Text Boxes
        CreateAutofillFields.createFields();
        idBox= new ComboBox();
        idBox.setMinSize(400,40);
        idBox.setEditable(true);
        grid.add(idBox,1,1);
        ArrayList<String> idArray = new ArrayList<String>();
        for(int i=0; i<CreateAutofillFields.autofillArray.size();i++){
            if(idBox.getItems().indexOf(CreateAutofillFields.autofillArray.get(i).get(0)) == -1) {
                idBox.getItems().add(CreateAutofillFields.autofillArray.get(i).get(0));
                idArray.add(CreateAutofillFields.autofillArray.get(i).get(0));
            }
        }
        System.out.println(idArray);
        idBox.setOnKeyReleased( e -> {
                    System.out.println("Key Typed");
                    System.out.println(idBox.getValue());
                    for(int j=0; j<idArray.size();j++){
                        if(!idArray.get(j).contains(idBox.getEditor().getText())){
                            idBox.getItems().remove(idArray.get(j));
                        }
                        else if(!idBox.getItems().contains(idArray.get(j))  &&  idArray.get(j).contains( idBox.getEditor().getText())){
                            idBox.getItems().add(idArray.get(j));

                        }
                        else if(idBox.getItems().isEmpty()){
                            idBox.getEditor().setText("");
                            nameBox.setText("");
                            gradeBox.setText("");
                            counsBox.setText("");
                            teachBox.getEditor().setText("");
                            for(int i=0; i<idArray.size();i++) {
                                idBox.getItems().add(idArray.get(i));
                            }



                        }
                    }
                }
        );                                //REDO AUTOFILL FOR ID        //Handle times outside of school day and during lunch
                                      //add else ifs to OTHER for Pie Charts
        idBox.setOnAction( e -> {
            nameBox.setText("");
            gradeBox.setText("");
            counsBox.setText("");
            teachBox.getEditor().setText("");//FULLSCREEN      // create eliminate semester one or two method in Autofill Class
        //Eliminate all in list that do not have chosen ID
            ArrayList<ArrayList<String>> testList = new ArrayList<ArrayList<String>>();
            testList = CreateAutofillFields.periodOneList();
            System.out.println(testList);
            int y=0;
            while(y<testList.size()){
                if(testList.get(y).contains(idBox.getEditor().getText())){
                    y++;
                }
                else {
                    testList.remove(y);
                }
            }
            System.out.println(testList);
            boolean isSMCS= false;
            if(testList.get(0).get(4).equals("SMC")) {
                isSMCS = true;
            }else{
                isSMCS= false;
            }
            ArrayList<ArrayList<String>> listForAutoFill = new ArrayList<ArrayList<String>>();
            Date d = new Date();
            d.setHours(12);
            d.setMinutes(0);
            boolean isDuringDay;
            boolean isEigthPeriod;
            if ((d.getHours() == 7 && d.getMinutes() >= 45) || (d.getHours() == 8 && d.getMinutes() <= 31)) {
                listForAutoFill = CreateAutofillFields.periodOneList();
                isDuringDay = true;
                isEigthPeriod= false;
            } else if ((d.getHours() == 8 && d.getMinutes() > 31) || (d.getHours() == 9 && d.getMinutes() <= 22)) {
                listForAutoFill = CreateAutofillFields.periodTwoList();
                isDuringDay = true;
                isEigthPeriod= false;
            } else if ((d.getHours() == 9 && d.getMinutes() > 22) || (d.getHours() == 10 && d.getMinutes() <= 13)) {
                listForAutoFill = CreateAutofillFields.periodThreeList();
                isDuringDay = true;
                isEigthPeriod= false;
            } else if ((d.getHours() == 10 && d.getMinutes() > 13) || (d.getHours() == 11 && d.getMinutes() <= 10)) {
                listForAutoFill = CreateAutofillFields.periodFourList();
                isDuringDay = true;
                isEigthPeriod= false;
            } else if ((d.getHours() == 12 && d.getMinutes() <= 47)) {
                listForAutoFill = CreateAutofillFields.periodFiveList();
                isDuringDay = true;
                isEigthPeriod= false;
            } else if ((d.getHours() == 12 && d.getMinutes() > 47) || (d.getHours() == 13 && d.getMinutes() <= 38)) {
                listForAutoFill = CreateAutofillFields.periodSixList();
                isDuringDay = true;
                isEigthPeriod= false;
            } else if ((d.getHours() == 13 && d.getMinutes() > 38) || (d.getHours() == 14 && d.getMinutes() <= 30)) {
                listForAutoFill = CreateAutofillFields.periodSevenList();
                isDuringDay = true;
                isEigthPeriod= false;
            } else if ((d.getHours() == 14 && d.getMinutes() > 30) || (d.getHours() == 15 && d.getMinutes() <= 40)) {
                if(isSMCS== true) {
                    listForAutoFill = CreateAutofillFields.periodEightList();
                }else{
                    listForAutoFill = CreateAutofillFields.periodOneList();
                }
                isDuringDay = true;
                isEigthPeriod= true;
            }
            else{
                listForAutoFill = CreateAutofillFields.periodOneList();
                isDuringDay = false;
                isEigthPeriod= false;
            }


            int x=0;
            while(x<listForAutoFill.size()){
                if(listForAutoFill.get(x).contains(idBox.getEditor().getText())){
                    x++;
                }
                    else {
                    listForAutoFill.remove(x);
                }
            }
            System.out.println(isSMCS);
            System.out.println(isEigthPeriod);
            System.out.println(isDuringDay);
            if((d.getMonth()== 8  && d.getDay()>26) || (d.getMonth()>8) || (d.getMonth()==0 && d.getDay()<26)) {
                if((isDuringDay == true) && (isEigthPeriod == false)) {
                    System.out.println("THIS DID NOT WORK");
                    nameBox.setText(listForAutoFill.get(0).get(2) + " " + listForAutoFill.get(0).get(1));
                    gradeBox.setText(listForAutoFill.get(0).get(3));
                    teachBox.getEditor().setText(listForAutoFill.get(0).get(9) + " " + listForAutoFill.get(0).get(10));
                    counsBox.setText(listForAutoFill.get(0).get(11) + " " + listForAutoFill.get(0).get(12));
                }
                else if(isEigthPeriod == true){
                    System.out.println("THIS DID WORK");
                    if(isSMCS == true){
                        nameBox.setText(listForAutoFill.get(0).get(2) + " " + listForAutoFill.get(0).get(1));
                        gradeBox.setText(listForAutoFill.get(0).get(3));
                        teachBox.getEditor().setText(listForAutoFill.get(0).get(9) + " " + listForAutoFill.get(0).get(10));
                        counsBox.setText(listForAutoFill.get(0).get(11) + " " + listForAutoFill.get(0).get(12));
                    }else if(isSMCS == false){
                        nameBox.setText(listForAutoFill.get(0).get(2) + " " + listForAutoFill.get(0).get(1));
                        gradeBox.setText(listForAutoFill.get(0).get(3));
                        counsBox.setText(listForAutoFill.get(0).get(11) + " " + listForAutoFill.get(0).get(12));
                    }
                }
                else{
                    System.out.println("THIS DID WORK");
                    nameBox.setText(listForAutoFill.get(0).get(2) + " " + listForAutoFill.get(0).get(1));
                    gradeBox.setText(listForAutoFill.get(0).get(3));
                    counsBox.setText(listForAutoFill.get(0).get(11) + " " + listForAutoFill.get(0).get(12));
                }
            }
            System.out.println(listForAutoFill);
            if((d.getMonth() == 0 && d.getDay()>26) || d.getMonth()>0 && d.getMonth()<6) {
                if ((isDuringDay == true) && (isEigthPeriod == false)) {
                    nameBox.setText(listForAutoFill.get(1).get(2) + " " + listForAutoFill.get(1).get(1));
                    gradeBox.setText(listForAutoFill.get(1).get(3));
                    teachBox.getEditor().setText(listForAutoFill.get(1).get(9) + " " + listForAutoFill.get(1).get(10));
                    counsBox.setText(listForAutoFill.get(1).get(11) + " " + listForAutoFill.get(1).get(12));
                } else if (isEigthPeriod == true) {
                    System.out.println("THIS DID WORK");
                    if (isSMCS == true) {
                        nameBox.setText(listForAutoFill.get(1).get(2) + " " + listForAutoFill.get(1).get(1));
                        gradeBox.setText(listForAutoFill.get(1).get(3));
                        teachBox.getEditor().setText(listForAutoFill.get(1).get(9) + " " + listForAutoFill.get(0).get(10));
                        counsBox.setText(listForAutoFill.get(1).get(11) + " " + listForAutoFill.get(1).get(12));
                    } else if (isSMCS == false) {
                        nameBox.setText(listForAutoFill.get(1).get(2) + " " + listForAutoFill.get(1).get(1));
                        gradeBox.setText(listForAutoFill.get(1).get(3));
                        counsBox.setText(listForAutoFill.get(1).get(11) + " " + listForAutoFill.get(1).get(12));
                    }
                } else{
                    nameBox.setText(listForAutoFill.get(1).get(2) + " " + listForAutoFill.get(1).get(1));
                    gradeBox.setText(listForAutoFill.get(1).get(3));
                    counsBox.setText(listForAutoFill.get(1).get(11) + " " + listForAutoFill.get(1).get(12));
                }
            }
            try {
                System.out.println("ThisWorks");
                int h = 0;
                int origSize = idBox.getItems().size();
                System.out.println(h);
                while (h < origSize) {
                    if (idBox.getItems().get(h).equals(idBox.getEditor().getText())) {
                        h++;
                    } else {
                        idBox.getItems().remove(h);
                    }                                             //SEE IF THIS EXCEPTION CAN BE FIXED
                }
            }catch(IndexOutOfBoundsException w){

            }

        });


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
               else if(teachBox.getItems().isEmpty()){
                   for(int i=0; i<FileReaderTeacher.teacherList.size();i++) {
                       teachBox.getItems().add(FileReaderTeacher.teacherList.get(i));
                   }
                   teachBox.getEditor().setText("");
               }
           }
        }
       );
    teachBox.setOnAction( e ->{
        try {
            System.out.println(teachBox.getEditor().getText());
            int h = 0;
            int origSize = teachBox.getItems().size();
            System.out.println(h);
            while (h < origSize) {
                if (!teachBox.getItems().get(h).equals(teachBox.getEditor().getText())) {
                    teachBox.getItems().remove(h);
                } else {
                    h++;
                }
            }
        }catch(IndexOutOfBoundsException q){

        }
    });

        teachBox.setEditable(true);
        nameBox= new TextField();
        grid.add(nameBox,1,4);
        nameBox.setMinSize(400,40);


        counsBox= new TextField();
        grid.add(counsBox,4,4);
        counsBox.setMinSize(400,40);


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
        String fileName = "C:\\Users\\jacob\\Documents\\" + String.valueOf(month) + "_" + String.valueOf(year) +".csv";
        System.out.println(fileName);
        submitID.setOnAction( e -> {
            String[] returnList= {idBox.getEditor().getText(), teachBox.getEditor().getText(), nameBox.getText(), counsBox.getText(),  gradeBox.getText(), reasonBox.getValue()};
            if(returnList[5].equals("Other")){
                returnList[5] = other.getText();
            }
            //System.out.println(FileReaderTeacher.emailList.get(FileReaderTeacher.teacherList.indexOf(returnList[1])));
            CSVWriter.writeFile(fileName , returnList);  //email To could be changed to teacherList.
            Emailer.email("infinitechSMCS2020@gmail.com","Infinitech1238","jacobkiviat@gmail.com",idBox.getEditor().getText() + " has just signed in", "Counseling Office Sign-In");
            numberOfRuns++;
            idBox.getEditor().setText("");
            teachBox.getEditor().setText("");
            nameBox.setText("");
            counsBox.setText("");
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
        adminScene = new Scene(adminGrid,1920,1080);
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


    private void handle(Event e) {
        int j = teachBox.getItems().size() - 1;
        while (j >= 0) {
            if (teachBox.getItems().get(j) != teachBox.getEditor().getText()) {
                teachBox.getItems().remove(j);
                System.out.println("works");
            } else {
                j--;
            }
        }
    }
}
