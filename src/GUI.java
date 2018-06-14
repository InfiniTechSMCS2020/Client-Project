import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;                            //NOTE FIX ERRORS ABOUT 8TH PERIOD AS WELL AS WHAT TO DO AT ANY OTHER TIME THAN THE SCHOOL DAY- POSSIBLY MAKE TIME BETWEEN CLASSES EXEMPT FROM AUTOFILL FOR TEACHERS-  FIX GUI-  FIX AUTOFILL (CLearing at the end, confirmation message, etc)
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.PieChart;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class GUI extends Application{       //TODO  ASK FOR CONFIRMATION BEFORE CLOSING , Graph titles, Change the inputs to be able to enter lower case, Make file upload for OTHER the actual input in text box, Are you sure that you want to do this popup, Blank out fields when submit is pressed, Update teachers on keystroke , make autofill, edit GUI, use thread to do checking for autofill/Combobox
    public static String fileCss = "cssfile.css";
    public static String filePath = "C:\\Users\\jacob\\Desktop\\Eclipse Workspace";
    
    public static Label loading= new Label("");

    
    public static boolean loadingStatus;
    static String specialString;
    static int[] inputs = new int[2];
    static int[] inputs2 = new int[2];
    static int numberOfRuns;
    static boolean buttonClicked;
    static Scene scene;
    static boolean isAutofill;
    static boolean range;
    static Button singleMonth;
    static Button rangeChoice;
    static Scene settingsScene;

    static String errorString;
    Button seePieCharts;

    static ArrayList<String> idArray;

    Button goBack;
    Button blue;
    Button yellow;
    Button green;
    Button red;
    Button orange;
    Button purple;

    Button submitID;

    GridPane grid = new GridPane();

    Button buttonPie;

    static TextField idBox;
    static ComboBox teachBox;
    static TextField nameBox;
    static ComboBox counsBox;
    static TextField gradeBox;
    static TextField other;


    static ComboBox<String> reasonBox;

    Label studentID;
    Label currTeach;
    Label studName;
    Label couns;
    Label studGrade;
    Label reason;

    ComboBox<String> pdBox;

    static Scene adminScene;

    Scene scene2;

    PieChart pieChart;

    boolean isNormalDay= true;

    int ID;

    static Rectangle square;

    String[] listOfBackgrounds = {"colors/blue.css","colors/yellow.css","colors/green.css","colors/red.css","colors/orange.css","colors/purple.css",
    "programSheet.css"};
    public static Stage stageClone = new Stage();
    public static void main(String[] args){
        FileReaderTeacher.readFromFile(filePath + "\\ClientProject\\src\\Teachers.txt");
        launch(args);

    }
    public void start(Stage stage) throws Exception{
        CreateDataSet data = new CreateDataSet();
        ArrayList<String> teachArray = data.getTeacherList();
        ArrayList<String> counsArray = data.getCounselorList();
        stage.getIcons().add(new Image("Images/Falcon.png"));
        loading.getStyleClass().add("label-big");
        grid.add(loading,3,13);
        stageClone = stage;

        numberOfRuns=0;

        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);



        seePieCharts = new Button();//Make new window + add case for someone trying to open it multiple times
        seePieCharts.getStyleClass().add("button-viewData");
        seePieCharts.setMinSize(141,53);

        //Text Boxes
        CreateAutofillFields.createFields();
        idBox= new TextField();
        idBox.setMinSize(500,50);
        idBox.setEditable(true);
        grid.add(idBox,1,1);
        idArray = new ArrayList<String>();
        ArrayList<String> nameArray = new ArrayList<String>();
        for(int i=0; i<CreateAutofillFields.autofillArray.size();i++){
          //  if(idBox.getItems().indexOf(CreateAutofillFields.autofillArray.get(i).get(0)) == -1) {
                /*
                idBox.getItems().add(CreateAutofillFields.autofillArray.get(i).get(0));
                */
                idArray.add(CreateAutofillFields.autofillArray.get(i).get(0));
                nameArray.add(CreateAutofillFields.autofillArray.get(i).get(2) + " " + CreateAutofillFields.autofillArray.get(i).get(1));
           // }
        }
        System.out.println(idArray);
        /*
        idBox.setOnKeyReleased( e -> {
                    System.out.println("Key Typed");
                    System.out.println(idBox.getValue());
                for(int j=0; j<idArray.size();j++){
                    if(idBox.getItems().isEmpty()){
                        System.out.println("THIS IS NOT ACTUALLY BROKEN");
                        idBox.getEditor().setText("");
                        nameBox.setText("");
                        gradeBox.setText("");
                        counsBox.setText("");
                        teachBox.getEditor().setText("");
                        for(int i=0; i<idArray.size();i++) {
                            System.out.println(idArray.get(i) + "This thing");
                            idBox.getItems().add(idArray.get(i));
                        }
                    }
                        else if(idBox.getItems().size()>0 && !idArray.get(j).contains(idBox.getEditor().getText())){
                            idBox.getItems().remove(idArray.get(j));
                        }
                        else if(idBox.getItems().size()>0 && !idBox.getItems().contains(idArray.get(j))  &&  idArray.get(j).contains( idBox.getEditor().getText())){
                            idBox.getItems().add(idArray.get(j));

                        }




                    }
                }
        );*/
                                   //REDO AUTOFILL FOR ID        //Handle times outside of school day and during lunch
                                      //add else ifs to OTHER for Pie Charts
        Button b = new Button("Enter");
        grid.add(b,2,1);
        b.setOnAction( e -> {
            nameBox.setText("");
            gradeBox.setText("");
            counsBox.setValue("");
            //FULLSCREEN      // create eliminate semester one or two method in Autofill Class
        //Eliminate all in list that do not have chosen ID
            ArrayList<ArrayList<String>> testList = new ArrayList<ArrayList<String>>();
            testList = CreateAutofillFields.periodOneList();
            System.out.println("ACTION");
            int y=0;
            while(y<testList.size()){
                if(!testList.get(y).contains(idBox.getText())){
                    testList.remove(y);
                }
                else {
                    y++;
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
            boolean isDuringDay;
            boolean isEigthPeriod;
            if(isNormalDay) {
                if ((d.getHours() == 7 && d.getMinutes() >= 45) || (d.getHours() == 8 && d.getMinutes() <= 31)) {
                    listForAutoFill = CreateAutofillFields.periodOneList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if ((d.getHours() == 8 && d.getMinutes() > 31) || (d.getHours() == 9 && d.getMinutes() <= 22)) {
                    listForAutoFill = CreateAutofillFields.periodTwoList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if ((d.getHours() == 9 && d.getMinutes() > 22) || (d.getHours() == 10 && d.getMinutes() <= 13)) {
                    listForAutoFill = CreateAutofillFields.periodThreeList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if ((d.getHours() == 10 && d.getMinutes() > 13) || (d.getHours() == 11 && d.getMinutes() <= 10)) {
                    listForAutoFill = CreateAutofillFields.periodFourList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if ((d.getHours() == 12 && d.getMinutes() <= 47)) {
                    listForAutoFill = CreateAutofillFields.periodFiveList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if ((d.getHours() == 12 && d.getMinutes() > 47) || (d.getHours() == 13 && d.getMinutes() <= 38)) {
                    listForAutoFill = CreateAutofillFields.periodSixList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if ((d.getHours() == 13 && d.getMinutes() > 38) || (d.getHours() == 14 && d.getMinutes() <= 30)) {
                    listForAutoFill = CreateAutofillFields.periodSevenList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if ((d.getHours() == 14 && d.getMinutes() > 30) || (d.getHours() == 15 && d.getMinutes() <= 25)) {
                    if (isSMCS == true) {
                        listForAutoFill = CreateAutofillFields.periodEightList();
                    } else {
                        listForAutoFill = CreateAutofillFields.periodOneList();
                    }
                    isDuringDay = true;
                    isEigthPeriod = true;
                } else {
                    listForAutoFill = CreateAutofillFields.periodOneList();
                    isDuringDay = false;
                    isEigthPeriod = false;
                }

            }else {
                if (WhatPeriodIsIt.currentPeriod.equals("1")){
                    listForAutoFill = CreateAutofillFields.periodOneList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if (WhatPeriodIsIt.currentPeriod.equals("2")) {
                    listForAutoFill = CreateAutofillFields.periodTwoList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if (WhatPeriodIsIt.currentPeriod.equals("3")) {
                    listForAutoFill = CreateAutofillFields.periodThreeList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if (WhatPeriodIsIt.currentPeriod.equals("4")) {
                    listForAutoFill = CreateAutofillFields.periodFourList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if (WhatPeriodIsIt.currentPeriod.equals("5")) {
                    listForAutoFill = CreateAutofillFields.periodFiveList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if (WhatPeriodIsIt.currentPeriod.equals("6")) {
                    listForAutoFill = CreateAutofillFields.periodSixList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if (WhatPeriodIsIt.currentPeriod.equals("7")) {
                    listForAutoFill = CreateAutofillFields.periodSevenList();
                    isDuringDay = true;
                    isEigthPeriod = false;
                } else if (WhatPeriodIsIt.currentPeriod.equals("8")) {
                    if (isSMCS == true) {
                        listForAutoFill = CreateAutofillFields.periodEightList();
                    } else {
                        listForAutoFill = CreateAutofillFields.periodOneList();
                    }
                    isDuringDay = true;
                    isEigthPeriod = true;
                } else {
                    listForAutoFill = CreateAutofillFields.periodOneList();
                    isDuringDay = false;
                    isEigthPeriod = false;
                }
            }
                int x = 0;
                while (x < listForAutoFill.size()) {
                    if (listForAutoFill.get(x).contains(idBox.getText())) {
                        x++;
                    } else {
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
                    counsBox.setValue(listForAutoFill.get(0).get(11) + " " + listForAutoFill.get(0).get(12));
                }
                else if(isEigthPeriod == true){
                    System.out.println("THIS DID WORK");
                    if(isSMCS == true){
                        nameBox.setText(listForAutoFill.get(0).get(2) + " " + listForAutoFill.get(0).get(1));
                        gradeBox.setText(listForAutoFill.get(0).get(3));
                        teachBox.getEditor().setText(listForAutoFill.get(0).get(9) + " " + listForAutoFill.get(0).get(10));
                        counsBox.setValue(listForAutoFill.get(0).get(11) + " " + listForAutoFill.get(0).get(12));
                    }else if(isSMCS == false){
                        nameBox.setText(listForAutoFill.get(0).get(2) + " " + listForAutoFill.get(0).get(1));
                        gradeBox.setText(listForAutoFill.get(0).get(3));
                        counsBox.setValue(listForAutoFill.get(0).get(11) + " " + listForAutoFill.get(0).get(12));
                    }
                }
                else{
                    System.out.println("THIS DID WORK");
                    nameBox.setText(listForAutoFill.get(0).get(2) + " " + listForAutoFill.get(0).get(1));
                    gradeBox.setText(listForAutoFill.get(0).get(3));
                    counsBox.setValue(listForAutoFill.get(0).get(11) + " " + listForAutoFill.get(0).get(12));
                }
            }
            System.out.println(listForAutoFill);
            if((d.getMonth() == 0 && d.getDay()>26) || d.getMonth()>0 && d.getMonth()<6) {
                if ((isDuringDay == true) && (isEigthPeriod == false)) {
                    nameBox.setText(listForAutoFill.get(1).get(2) + " " + listForAutoFill.get(1).get(1));
                    gradeBox.setText(listForAutoFill.get(1).get(3));
                    teachBox.getEditor().setText(listForAutoFill.get(1).get(9) + " " + listForAutoFill.get(1).get(10));
                    counsBox.setValue(listForAutoFill.get(1).get(11) + " " + listForAutoFill.get(1).get(12));
                } else if (isEigthPeriod == true) {
                    System.out.println("THIS DID WORK");
                    if (isSMCS == true) {
                        nameBox.setText(listForAutoFill.get(1).get(2) + " " + listForAutoFill.get(1).get(1));
                        gradeBox.setText(listForAutoFill.get(1).get(3));
                        teachBox.getEditor().setText(listForAutoFill.get(1).get(9) + " " + listForAutoFill.get(0).get(10));
                        counsBox.setValue(listForAutoFill.get(1).get(11) + " " + listForAutoFill.get(1).get(12));
                    } else if (isSMCS == false) {
                        nameBox.setText(listForAutoFill.get(1).get(2) + " " + listForAutoFill.get(1).get(1));
                        gradeBox.setText(listForAutoFill.get(1).get(3));
                        counsBox.setValue(listForAutoFill.get(1).get(11) + " " + listForAutoFill.get(1).get(12));
                    }
                } else{
                    nameBox.setText(listForAutoFill.get(1).get(2) + " " + listForAutoFill.get(1).get(1));
                    gradeBox.setText(listForAutoFill.get(1).get(3));
                    counsBox.setValue(listForAutoFill.get(1).get(11) + " " + listForAutoFill.get(1).get(12));
                }
            }
           /* try {
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
*/
        });


        teachBox = new ComboBox();
        teachBox.setMinSize(500,50);
        grid.add(teachBox,4,7);

        for(int i=0; i<FileReaderTeacher.teacherList.size();i++) {
            teachBox.getItems().add(FileReaderTeacher.teacherList.get(i));
        }
       teachBox.setOnKeyReleased( e -> {
                       for(int j=0; j<FileReaderTeacher.teacherList.size();j++){
                           if(teachBox.getItems().isEmpty()){
                               System.out.println("THIS IS NOT ACTUALLY BROKEN");
                               teachBox.getEditor().setText("");
                               for(int i=0; i<FileReaderTeacher.teacherList.size();i++) {
                                   System.out.println(FileReaderTeacher.teacherList.get(i) + "This thing");
                                   teachBox.getItems().add(FileReaderTeacher.teacherList.get(i));
                               }
                           }
                           else if(teachBox.getItems().size()>0 && !FileReaderTeacher.teacherList.get(j).contains(teachBox.getEditor().getText())){
                               teachBox.getItems().remove(FileReaderTeacher.teacherList.get(j));
                           }
                           else if(teachBox.getItems().size()>0 && !teachBox.getItems().contains(FileReaderTeacher.teacherList.get(j))  &&  FileReaderTeacher.teacherList.get(j).contains( teachBox.getEditor().getText())){
                               teachBox.getItems().add(FileReaderTeacher.teacherList.get(j));

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
        grid.add(nameBox,1,7);
        nameBox.setMinSize(500,50);


        counsBox= new ComboBox();
        System.out.println(teachArray);
        counsBox.getItems().add("Edward Reed");
        counsBox.getItems().add("Melissa Nagy");
        counsBox.getItems().add("Barbara Martin");
        counsBox.getItems().add("David Gysberts");
        counsBox.getItems().add("Nelly Boishin");

        grid.add(counsBox,4,11);
        counsBox.setMinSize(500,50);


        gradeBox= new TextField();
        grid.add(gradeBox,1,11);
        gradeBox.setMinSize(500,50);


        String[] reasonList = {"Schedule",
                "Academic Planning",
                "Personal/Social/Emotional",
                "College and Career Information",
                "Got a Pass from Counselor",
                "Other"};
        reasonBox= new ComboBox();
        reasonBox.setMinSize(500,50);
        other = new TextField();
        other.setVisible(false);
        other.setMinSize(500,50);
        grid.add(other, 4, 2);
        reasonBox.getItems().addAll(reasonList);

        reasonBox.setOnAction( event -> {
            if (reasonBox.getValue().equals("Other")) {
                other.setVisible(true);
            } else {
                other.setVisible(false);
            }
        }
        );


        grid.add(reasonBox,4,1);

        studentID = new Label("Student ID");
        studentID.getStyleClass().add("label-big");
        grid.add(studentID, 1, 0);
        currTeach = new Label("Current Teacher");
        currTeach.getStyleClass().add("label-big");
        grid.add(currTeach, 4, 6);
        studName = new Label("Student Name");
        studName.getStyleClass().add("label-big");
        grid.add(studName, 1, 6);
        couns = new Label("Counselor");
        couns.getStyleClass().add("label-big");
        grid.add(couns, 4, 9);
        studGrade = new Label("Student Grade");
        studGrade.getStyleClass().add("label-big");
        grid.add(studGrade, 1, 9);
        reason = new Label("Reason for visit");
        reason.getStyleClass().add("label-big");
        grid.add(reason, 4, 0);

        range= false;

        rangeChoice = new Button();
        singleMonth = new Button();
        singleMonth.setOnAction(e->{
            range=false;
            pieChartData.inputMonthBox2.setVisible(false);
            pieChartData.inputYearBox2.setVisible(false);
        });

        rangeChoice.setOnAction(e ->{
            range=true;
            pieChartData.inputMonthBox2.setVisible(true);
            pieChartData.inputYearBox2.setVisible(true);
        });

        //LISTS AND DATA OPERATIONS
        seePieCharts.setOnAction( e -> {
            pieChartData.create();
            for(int i=1; i<13; i++) {
                pieChartData.inputMonthBox.getItems().add(String.valueOf(i));
                pieChartData.inputMonthBox2.getItems().add(String.valueOf(i));
            }
            for(int i=18; i<20; i++) {
                pieChartData.inputYearBox.getItems().add(String.valueOf(i));
                pieChartData.inputYearBox2.getItems().add(String.valueOf(i));
            }
            stage.setScene(pieChartData.scene2);
        });
        pieChartData.submitTime.setOnAction( e -> {
            if(range == false) {
                GUI.inputs[0] = Integer.valueOf((String) pieChartData.inputMonthBox.getValue());
                GUI.inputs[1] = Integer.valueOf((String) pieChartData.inputYearBox.getValue());
                pieChartData.execute();
                System.out.println(pieChartData.pane.getChildren());
                while (pieChartData.pane.getChildren().size() > 13) {
                    pieChartData.pane.getChildren().remove(pieChartData.pane.getChildren().size() - 1);
                }
                pieChartData.pane.add(pieChartData.pieChart, 1, 0);
                pieChartData.pane.add(pieChartData.pieChartGrade, 2, 0);
                pieChartData.pane.add(pieChartData.pieChartCouns, 3, 0);
            }
            else{
                GUI.inputs[0] = Integer.valueOf((String) pieChartData.inputMonthBox.getValue());
                GUI.inputs[1] = Integer.valueOf((String)pieChartData.inputYearBox.getValue());
                GUI.inputs2[0] = Integer.valueOf((String) pieChartData.inputMonthBox2.getValue());
                GUI.inputs2[1] = Integer.valueOf((String)pieChartData.inputYearBox2.getValue());
                pieChartData.execute(inputs[0],inputs[1],inputs2[0],inputs2[1]);
                System.out.println(pieChartData.pane.getChildren());
                System.out.println(pieChartData.pane.getChildren().size());
                while (pieChartData.pane.getChildren().size() > 13) {
                    pieChartData.pane.getChildren().remove(pieChartData.pane.getChildren().size() - 1);
                }
                pieChartData.pane.add(pieChartData.pieChart, 1, 0);
                pieChartData.pane.add(pieChartData.pieChartGrade, 2, 0);
                pieChartData.pane.add(pieChartData.pieChartCouns, 3, 0);
            }

        });

        Button admin = new Button();
        grid.add(admin,7,13);
        scene = new Scene(grid, 1920, 1080 );
        scene.getStylesheets().add(GUI.fileCss);
        stage.setScene(scene);
        stage.show();
        if(isNormalDay == false){
            WhatPeriodIsIt.show();
        }


        Label settingstTitle = new Label("Settings                                                   ");
        settingstTitle.getStylesheets().add(GUI.fileCss);
        settingstTitle.getStyleClass().add("label-big");
        goBack = new Button();

        Button changePass = new Button();


        goBack.setOnAction( e -> {
            stage.setScene(adminScene);
        });

        GridPane settingsGrid = new GridPane();
        settingsGrid.setVgap(10);
        settingsGrid.setHgap(10);
        goBack.getStyleClass().add("button-goBack");
        goBack.setMinSize(141,53);
        changePass.getStyleClass().add("button-changePassword");
        changePass.setMinSize(141,53);
        Button changeDayTypeNormal = new Button();
        Button changeDayTypeAbnormal = new Button();
        changeDayTypeNormal.getStyleClass().add("button-normal");
        changeDayTypeNormal.setMinSize(141,53);
        changeDayTypeAbnormal.getStyleClass().add("button-abnormal");
        changeDayTypeAbnormal.setMinSize(141,53);
        settingsGrid.add(settingstTitle,0,0);
        settingsGrid.add(goBack,0,4);
        settingsGrid.add(changePass,0,1);
        settingsGrid.add(changeDayTypeAbnormal,0,3);
        settingsGrid.add(changeDayTypeNormal,0,2);

        Button changeImage = new Button("Change Background Image");
        settingsGrid.add(changeImage,0,5);

        ComboBox boxOfImages = new ComboBox();
        ArrayList<String> list = NormalFileReader.readFilesInFolder(new File(filePath+ "\\ClientProject\\src\\Images"));
        for(int i=0; i<list.size(); i++){
            boxOfImages.getItems().add(list.get(i));
        }
        settingsGrid.add(boxOfImages,0,6);

        changeImage.setOnAction(e -> {
            try {
                File f = new File(GUI.fileCss);
                f.delete();
                f.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write(".root{\n-fx-background-image: url(\"Images/" + boxOfImages.getValue() + "\");\n-fx-background-size: 1920 1080;\n-fx-background-position: center center;\n} \n" +
                        ".scene{\n-fx-background-image: url(\"Images/" + boxOfImages.getValue() + "\");\n-fx-background-size: 1920 1080;\n-fx-background-position: center center;\n} \n"
            +

                ".label{\n" +
                        "    -fx-text-fill: #000000;\n" +
                        "    -fx-font-weight: bold;\n" +
                        "}\n" +
                        ".label-big{\n" +
                        "    -fx-font-size: 28;\n" +
                        "\n" +
                        "}\n" +
                        ".button-submit{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"submit.png\");\n" +
                        "    -fx-background-size: 141 53\n" +
                        "}\n" +
                        ".button-keep{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"keep.png\");\n" +
                        "    -fx-background-size: 141 53\n" +
                        "}\n" +
                        ".button-erase{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"erase.png\");\n" +
                        "    -fx-background-size: 141 53\n" +
                        "}\n" +
                        ".button-admin{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"admin.png\");\n" +
                        "    -fx-background-size: 100 100;\n" +
                        "}\n" +
                        ".button-settings{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"setting.png\");\n" +
                        "    -fx-background-size: 100 100;\n" +
                        "}\n" +
                        ".button-home{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"home.png\");\n" +
                        "    -fx-background-size: 100 100;\n" +
                        "}\n" +
                        ".button-signIn{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"sign_in.png\");\n" +
                        "    -fx-background-size: 141 53;\n" +
                        "}\n" +
                        ".button-changePassword{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"change_pass.png\");\n" +
                        "    -fx-background-size: 141 53;\n" +
                        "}\n" +
                        ".button-abnormal{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"Images/abnormal.png\");\n" +
                        "    -fx-background-size: 141 53;\n" +
                        "}\n" +
                        ".button-normal{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"normal.png\");\n" +
                        "    -fx-background-size: 141 53;\n" +
                        "}\n" +
                        ".button-viewData{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"view_data.png\");\n" +
                        "    -fx-background-size: 141 53;\n" +
                        "}\n" +
                        ".button-viewData2{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"view_data.png\");\n" +
                        "    -fx-background-size: 110 36;\n" +
                        "}\n" +
                        ".button-goBack{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"go_back.png\");\n" +
                        "    -fx-background-size: 141 53;\n" +
                        "}\n" +
                        ".button-goBack2{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"go_back.png\");\n" +
                        "    -fx-background-size:  110 36;\n" +
                        "}\n" +
                        ".button-range{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"range.png\");\n" +
                        "    -fx-background-size: 110 36;\n" +
                        "}\n" +
                        ".button-singleMonth{\n" +
                        "    -fx-border-radius: 10;\n" +
                        "    -fx-background-radius: 10;\n" +
                        "    -fx-background-image: url(\"single_month.png\");\n" +
                        "    -fx-background-size: 110 36;\n" +
                        "}\n" +
                        ".chart-legend{\n" +
                        "    -fx-background-color: #FCF69D;\n" +
                        "}\n" +
                        "\n" +
                        "\n");
                bw.close();

                scene.getStylesheets().add(f.toURL().toString());
                adminScene.getStylesheets().add(f.toURL().toString());
                settingsScene.getStylesheets().add(f.toURL().toString());



            }catch(IOException x){
                System.out.println("File does not exist hello ");
            }
        });
        Button changeSymbol = new Button("Change Logo");
        settingsGrid.add(changeSymbol,0,7);

        ComboBox boxOfImages2 = new ComboBox();
        ArrayList<String> list2 = NormalFileReader.readFilesInFolder(new File(filePath+ "\\ClientProject\\src\\Images"));
        for(int i=0; i<list2.size(); i++){
            boxOfImages2.getItems().add(list2.get(i));
        }
        settingsGrid.add(boxOfImages2,0,8);

        changeSymbol.setOnAction(e -> {
                    Image i = new Image("Images/" + boxOfImages2.getValue());
                    System.out.println(i.toString());
                    Paint paint = new ImagePattern(i);
                    System.out.println(paint.toString());
                    square = new Rectangle(100, 100, paint);
                    grid.add(square, 3,0);
                });

        System.out.println(list);
        System.out.println(list);

        settingsGrid.setAlignment(Pos.CENTER);
        settingsScene = new Scene(settingsGrid,1920,1080);
        settingsScene.getStylesheets().add(GUI.fileCss);
        Button settings = new Button();
        settings.getStyleClass().add("button-settings");
        settings.setMinSize(100,100);
        changeDayTypeNormal.setOnAction(e ->{
            isNormalDay= true;
        });
        changeDayTypeAbnormal.setOnAction(e ->{
            isNormalDay= false;
        });
        goBack.setOnAction( e -> {
            stageClone.setScene(adminScene);
        });
        settings.setOnAction( e -> {
            stageClone.setScene(settingsScene);
        });
        changePass.setOnAction(e ->{
            ChangePassword.show();
        });
/*
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


*/

        Date date = new Date();
        int month= date.getMonth()+1;
        int year = date.getYear() - 100;
        submitID= new Button();
        submitID.getStyleClass().add("button-submit");
        grid.add(submitID,3,13);


        String fileName = filePath+ "\\ClientProject\\src\\" + String.valueOf(month) + "_" + String.valueOf(year) +".csv";
        System.out.println(fileName);
        submitID.setOnAction( e -> {
            boolean areFieldsGood = false;
            int count = 0;                                  //check to see if grade can be corrected
            if (!( (idArray.indexOf(idBox.getText()) == nameArray.indexOf(nameBox.getText())) && idArray.contains(idBox.getText()) && FileReaderTeacher.teacherList.contains(teachBox.getEditor().getText()) && (counsBox.getValue().equals("Edward Reed") || counsBox.getValue().equals("Melissa Nagy") || counsBox.getValue().equals("Barbara Martin") || counsBox.getValue().equals("David Gysberts") || counsBox.getValue().equals("Nelly Boishin")) && ((String.valueOf(gradeBox.getText())).equals("9") || (String.valueOf(gradeBox.getText())).equals("09") || String.valueOf(gradeBox.getText()).equals("10") || String.valueOf(gradeBox.getText()).equals("11") || String.valueOf(gradeBox.getText()).equals("12")) && (nameArray.contains(nameBox.getText())) && (reasonBox.getItems().contains(reasonBox.getValue())))) {
                if (!idArray.contains(idBox.getText())) {
                    System.out.println("1");
                    errorString = "Please enter a valid field into School ID";
                    count++;
                }
                if (!FileReaderTeacher.teacherList.contains(teachBox.getEditor().getText())){
                    System.out.println("2");
                errorString = "Please enter a valid field into Teacher";
                count++;
            }
            if (!(counsBox.getValue().equals("Edward Reed") || counsBox.getValue().equals("Melissa Nagy") || counsBox.getValue().equals("Barbara Martin") || counsBox.getValue().equals("David Gysberts") || counsBox.getValue().equals("Nelly Boishin"))) {
                System.out.println("3");
                errorString = "Please enter a valid field into Counselor";
                count++;
            }
            if (!((String.valueOf(gradeBox.getText())).equals("9") || (String.valueOf(gradeBox.getText())).equals("09") || String.valueOf(gradeBox.getText()).equals("10") || String.valueOf(gradeBox.getText()).equals("11") || String.valueOf(gradeBox.getText()).equals("12"))) {
                System.out.println("4");
                errorString = "Please enter a valid field into Grade";
                count++;
            }
            if (!(nameArray.contains(nameBox.getText()))) {
                System.out.println("5");
                errorString = "Please enter a valid field into Name";
                count++;
            }
            if (!(reasonBox.getItems().contains(reasonBox.getValue()))) {
                    System.out.println("5");
                    errorString = "Please enter a valid field into Reason";
                    count++;
                }
                if (!(idArray.indexOf(idBox.getText()) == nameArray.indexOf(nameBox.getText()))) {
                    System.out.println("5");
                    errorString = "The name entered does not match the ID entered";
                    count++;
                }

            if (count > 1) {
                errorString = "There are multiple fields that need to be fixed";
            }
            BadFieldsMessage.show();
        }

            else if (idArray.contains(idBox.getText()) && FileReaderTeacher.teacherList.contains(teachBox.getEditor().getText()) && (counsBox.getValue().equals("Edward Reed") || counsBox.getValue().equals("Melissa Nagy") || counsBox.getValue().equals("Barbara Martin") || counsBox.getValue().equals("David Gysberts") || counsBox.getValue().equals("Nelly Boishin")) && (Integer.valueOf(gradeBox.getText()) == 9 || Integer.valueOf(gradeBox.getText()) == 10 || Integer.valueOf(gradeBox.getText()) == 11 || Integer.valueOf(gradeBox.getText()) == 12) && (nameArray.contains(nameBox.getText()))) {
                    areFieldsGood = true;
                }
            if(areFieldsGood) {
                String[] returnList = {idBox.getText(), teachBox.getEditor().getText(), nameBox.getText(), (String)counsBox.getValue(), gradeBox.getText(), reasonBox.getValue(), String.valueOf(new Date())};
                if (returnList[5].equals("Other")) {
                    returnList[5] = other.getText();
                }
                ArrayList<String> emptyList = new ArrayList<String>();
                CSVWriter.writeFile(fileName, returnList);  //email To could be changed to teacherList.
                //FileReaderTeacher.emailList.get(FileReaderTeacher.teacherList.indexOf(returnList[1]))
                Emailer.email("infinitechSMCS2020@gmail.com", "Infinitech1238", "jacobkiviat@gmail.com", "Dear " + teachBox.getEditor().getText() + ",\n" + "     " + nameBox.getText()+ " has just signed in to the counseling office.\n\nFrom,\nThe PHS Counseling Team ", "Counseling Office Sign-In");
                numberOfRuns++;
                LoadingScreen.show();
                /*
                idBox.setValue("");
                for (int i = 0; i < idArray.size(); i++) {
                        idBox.getItems().add(idArray.get(i));
                    if (!idBox.getItems().contains(idArray.get(i))) {
                    }
                }*/
                idBox.setText("");
                teachBox.getEditor().setText("");
                teachBox.getItems().remove(0);
                for (int i = 0; i < FileReaderTeacher.teacherList.size(); i++) {
                        teachBox.getItems().add(FileReaderTeacher.teacherList.get(i));
                }
                nameBox.setText("");
                counsBox.setValue("");
                gradeBox.setText("");
                reasonBox.setValue("");
                other.setText("");
                other.setVisible(false);//Add it so that the email sends to a teacher that is inputted
                if (isNormalDay == false) {
                    WhatPeriodIsIt.show();
                }
            }
        });
        submitID.setMinSize(141,53);
        submitID.setMaxSize(141,53);

        Button back = new Button();
        back.getStyleClass().add("button-home");
        back.setMinSize(100,100);
        Label mainMenuAdmin = new Label("Welcome to PHS Counseling Admin Screen");
        mainMenuAdmin.getStylesheets().add(GUI.fileCss);
        mainMenuAdmin.getStyleClass().add("label-big");
        GridPane adminGrid = new GridPane();
        adminGrid.setVgap(10);
        adminGrid.setHgap(10);
        adminGrid.setAlignment(Pos.CENTER);
        adminGrid.add(mainMenuAdmin,0,0);
        adminGrid.add(seePieCharts,0,1);
        adminGrid.add(settings, 0,2);
        adminGrid.add(back,0,3);

        back.setOnAction(e ->{
            stage.setScene(scene);
            if(isNormalDay == false){
                WhatPeriodIsIt.show();
            }
        });
        adminScene = new Scene(adminGrid,1920,1080);
        adminScene.getStylesheets().add(GUI.fileCss);
        admin.setMinSize(100,100);
        admin.getStyleClass().add("button-admin");

        admin.setOnAction( e ->{
            AdminLogIn.show();
        });

        pieChartData.button.setOnAction( e ->{
            GUI.setPieSceneBack();
        });

        stage.setOnCloseRequest( e ->{
            stage.show();
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
    public static void setSettingsScene(){
        stageClone.setScene(settingsScene);
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
