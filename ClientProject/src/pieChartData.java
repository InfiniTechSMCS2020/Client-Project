import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class pieChartData {
    static ObservableList<PieChart.Data> pieData;
    static ObservableList<PieChart.Data> pieDataGrade;
    static Scene scene2;
    static TextField inputMonthBox = new TextField();
    static TextField inputYearBox = new TextField();
    static Button submitTime = new Button();
    static GridPane pane;
    static PieChart pieChart;
    static PieChart pieChartGrade;
    public static void execute() {
        CreateDataSet dataSet = new CreateDataSet();
        dataSet.fillLists("\\C:\\Users\\jacob\\Documents\\" + GUI.inputs[0] + "_" + GUI.inputs[1] + ".csv");
        //reasons
        int schedule = 0;
        int planning = 0;
        int personal = 0;
        int college = 0;
        int pass = 0;
        int other = 0;


        ArrayList<String> reasonListGUI = dataSet.getReasonList();
        for (int a = 0; a < reasonListGUI.size(); a++) {
            if (reasonListGUI.get(a).equals("Schedule")) {
                schedule++;
            }
            if (reasonListGUI.get(a).equals("Academic Planning")) {
                planning++;
            }
            if (reasonListGUI.get(a).equals("Personal/Social/Emotional")) {
                personal++;
            }
            if (reasonListGUI.get(a).equals("College and Career Information")) {
                college++;
            }
            if (reasonListGUI.get(a).equals("Got a Pass from Counselor")) {
                pass++;
            }
            if (reasonListGUI.get(a).equals("Other")) {
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
        pieChart.setMinSize(750, 750);

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
        pieChartGrade.setMinSize(500, 500);
    }
    public static void create(){
        pane = new GridPane();
        scene2 = new Scene(pane, 1920, 1080);
        pane.setAlignment(Pos.CENTER);
        pane.add(inputMonthBox, 3, 3);
        pane.add(inputYearBox, 3, 4);
        pane.add(submitTime, 3, 5);
    }
}
