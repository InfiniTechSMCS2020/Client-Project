package ClientProj;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;

import java.util.ArrayList;

public class pieChartData {
    static Button button= new Button("Back to Admin Screen");
    static ObservableList<PieChart.Data> pieData;
    static ObservableList<PieChart.Data> pieDataGrade;
    static Scene scene2;
    static TextField inputMonthBox = new TextField();
    static TextField inputYearBox = new TextField();
    static Button submitTime = new Button("See Pie Charts");
    static GridPane pane;
    static PieChart pieChart;
    static PieChart pieChartGrade;
    static Label gradeTitle = new Label();
    static Label pieTitle= new Label();
    static Label monthLabel = new Label("Month");
    static Label yearLabel= new Label("Year");
    public static void execute() {
        CreateDataSet dataSet = new CreateDataSet();
        dataSet.fillLists("\\\\PV152-DATA\\STUHOME\\368677\\documents\\" + GUI.inputs[0] + "_" + GUI.inputs[1] + ".csv");
        //reasons
        int schedule = 0;
        int planning = 0;
        int personal = 0;
        int college = 0;
        int pass = 0;
        int other = 0;


        ArrayList<String> reasonListGUI = dataSet.getReasonList();
        System.out.println("reason list: "+ reasonListGUI);
        for (int a = 0; a < reasonListGUI.size(); a++) {
            if (reasonListGUI.get(a).equals("Schedule")) {
                schedule++;
            }
            else if (reasonListGUI.get(a).equals("Academic Planning")) {
                planning++;
            }
            else if (reasonListGUI.get(a).equals("Personal/Social/Emotional")) {
                personal++;
            }
            else if (reasonListGUI.get(a).equals("College and Career Information")) {
                college++;
            }
            else if (reasonListGUI.get(a).equals("Got a Pass from Counselor")) {
                pass++;
            }
            else{
            	other++;	
            }
        }

        pieData = FXCollections.observableArrayList(
                new PieChart.Data("Schedule- " + String.valueOf(schedule), schedule),
                new PieChart.Data("Academic Planning- " + String.valueOf(planning), planning),
                new PieChart.Data("Personal/Social/Emotional- " + String.valueOf(personal), personal),  //DISPLAYS AS ONLY PERSONAL-  FIX
                new PieChart.Data("College and Career Information- " + String.valueOf(college), college),
                new PieChart.Data("Got a Pass from Counselor- " + String.valueOf(pass), pass),
                new PieChart.Data("Other- " + String.valueOf(other), other)
        );

        pieChart = new PieChart(pieChartData.pieData);
        pieChart.setMinSize(250, 250);
        pieChart.getStylesheets().add("programSheet.css");
        pieTitle.getStyleClass().add("label-big");
        pieTitle.setText("Student Reasons for Visiting During " + GUI.inputs[0] +"/"+ GUI.inputs[1]);


        //grades
        int nine = 0;
        int ten = 0;
        int eleven = 0;
        int twelve = 0;

        ArrayList<Integer> gradeListGUI = dataSet.getGradeList();
        System.out.println(gradeListGUI);
        for (int b = 0; b < gradeListGUI.size(); b++) {
            if (gradeListGUI.get(b)== 9) {
                nine++;
            }
            if (gradeListGUI.get(b)== 10) {
                ten++;
            }
            if (gradeListGUI.get(b)== 11) {
                eleven++;
            }
            if (gradeListGUI.get(b)== 12) {
                twelve++;
            }
        }
        System.out.println(twelve);
        pieDataGrade = FXCollections.observableArrayList(
                new PieChart.Data("Ninth- " + String.valueOf(nine), nine),
                new PieChart.Data("Tenth- " + String.valueOf(ten), ten),
                new PieChart.Data("Eleventh- " + String.valueOf(eleven), eleven),
                new PieChart.Data("Twelth- " + String.valueOf(twelve), twelve)
        );
        System.out.println(pieDataGrade);
        pieChartGrade = new PieChart(pieDataGrade);
        pieChartGrade.setMinSize(250, 250);
        pieChartGrade.getStylesheets().add("programSheet.css");
        gradeTitle.getStyleClass().add("label-big");
        gradeTitle.setText("Student Grade Levels During " + GUI.inputs[0] +"/"+ GUI.inputs[1]);



    }
    public static void create(){
        pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        scene2 = new Scene(pane, 1280, 1024);
        scene2.getStylesheets().add("programSheet.css");
        pane.setAlignment(Pos.CENTER);
        pane.add(inputMonthBox, 4, 2);
        pane.add(inputYearBox, 4, 3);
        pane.add(submitTime, 4, 4);
        pane.add(pieTitle,1,2);
        pane.add(gradeTitle,2,2);
        pane.add(button,4,5);
        pane.add(monthLabel,3,2);
        pane.add(yearLabel,3,3);
    }
}
