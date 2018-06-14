import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class pieChartData {
    static Button button= new Button();
    static ObservableList<PieChart.Data> pieData;
    static ObservableList<PieChart.Data> pieDataGrade;
    static ObservableList<PieChart.Data> pieDataCouns;

    static Scene scene2;
    static ComboBox inputMonthBox = new ComboBox();
    static ComboBox inputYearBox = new ComboBox();
    static ComboBox inputMonthBox2 = new ComboBox();
    static ComboBox inputYearBox2 = new ComboBox();
    static Button submitTime = new Button();
    static GridPane pane;
    static PieChart pieChart;
    static PieChart pieChartGrade;
    static PieChart pieChartCouns;
    static Label gradeTitle = new Label();
    static Label pieTitle= new Label();
    static Label counsTitle = new Label();
    static Label monthLabel = new Label("Month");
    static Label yearLabel= new Label("Year");
    public static void execute() {
        CreateDataSet dataSet = new CreateDataSet();
        dataSet.fillLists(GUI.filePath +"\\ClientProject\\src\\" + GUI.inputs[0] + "_" + GUI.inputs[1] + ".csv");
        //reasons
        int schedule = 0;
        int planning = 0;
        int personal = 0;
        int college = 0;
        int pass = 0;
        int other = 0;


        ArrayList<String> reasonListGUI = dataSet.getReasonList();
        System.out.println(reasonListGUI);
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
        pieChart.setMinSize(750, 750);
        pieChart.getStylesheets().add("cssfile.css");
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
            else if (gradeListGUI.get(b)== 10) {
                ten++;
            }
            else if (gradeListGUI.get(b)== 11) {
                eleven++;
            }
            else if (gradeListGUI.get(b)== 12) {
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
        pieChartGrade.getStylesheets().add("cssfile.css");
        gradeTitle.getStyleClass().add("label-big");
        gradeTitle.setText("Student Grade Levels During " + GUI.inputs[0] +"/"+ GUI.inputs[1]);

        //counselors
        int reed = 0;
        int nagy= 0;
        int martin = 0;
        int gysberts = 0;
        int boishin=0;

        ArrayList<String> counselorListGUI = dataSet.getCounselorList();
        System.out.println(counselorListGUI);
        for (int b = 0; b < counselorListGUI.size(); b++) {
            if (counselorListGUI.get(b).equals("Edward Reed")) {
                reed++;
            }
            else if (counselorListGUI.get(b).equals("Melissa Nagy")) {
                nagy++;
            }
            else if (counselorListGUI.get(b).equals("Barbara Martin")) {
                martin++;
            }
            else if (counselorListGUI.get(b).equals("David Gysberts")) {
                gysberts++;
            }
            else if(counselorListGUI.get(b).equals("Nelly Boishin")){
                boishin++;
            }
        }
        System.out.println(twelve);
        pieDataCouns = FXCollections.observableArrayList(
                new PieChart.Data("Edward Reed- " + String.valueOf(reed), reed),
                new PieChart.Data("Melissa Nagy- " + String.valueOf(nagy), nagy),
                new PieChart.Data("Barbara Martin- " + String.valueOf(martin), martin),
                new PieChart.Data("David Gysberts- " + String.valueOf(gysberts), gysberts),
                new PieChart.Data("Nelly Boishin- " + String.valueOf(boishin), boishin)
        );
        System.out.println(pieDataGrade);
        pieChartCouns = new PieChart(pieDataCouns);
        pieChartCouns.setMinSize(625, 625);
        pieChartCouns.getStylesheets().add("cssfile.css");
        counsTitle.getStyleClass().add("label-big");
        counsTitle.setText("Visits to Counselors During " + GUI.inputs[0] +"/"+ GUI.inputs[1]);




    }

    public static void execute(int startMonth, int startYear, int endMonth, int endYear) {
            int schedule = 0;
            int planning = 0;
            int personal = 0;
            int college = 0;
            int pass = 0;
            int other = 0;

            int nine = 0;
            int ten = 0;
            int eleven = 0;
            int twelve = 0;

            int reed = 0;
            int nagy = 0;
            int martin = 0;
            int gysberts = 0;
            int boishin = 0;

        if (startYear == endYear) {
            for (int i = startMonth; i <= endMonth; i++) {
                CreateDataSet dataSet = new CreateDataSet();
                dataSet.fillLists("" + i + "_" + startYear + ".csv");

                ArrayList<String> reasonListGUI = dataSet.getReasonList();
                for (int a = 0; a < reasonListGUI.size(); a++) {
                    if (reasonListGUI.get(a).equals("Schedule")) {
                        schedule++;
                    } else if (reasonListGUI.get(a).equals("Academic Planning")) {
                        planning++;
                    } else if (reasonListGUI.get(a).equals("Personal/Social/Emotional")) {
                        personal++;
                    } else if (reasonListGUI.get(a).equals("College and Career Information")) {
                        college++;
                    } else if (reasonListGUI.get(a).equals("Got a Pass from Counselor")) {
                        pass++;
                    } else {
                        other++;
                    }
                }


                ArrayList<Integer> gradeListGUI = dataSet.getGradeList();
                System.out.println(gradeListGUI);
                for (int b = 0; b < gradeListGUI.size(); b++) {
                    if (gradeListGUI.get(b) == 9) {
                        nine++;
                    } else if (gradeListGUI.get(b) == 10) {
                        ten++;
                    } else if (gradeListGUI.get(b) == 11) {
                        eleven++;
                    } else if (gradeListGUI.get(b) == 12) {
                        twelve++;
                    }
                }

                //counselors
                ArrayList<String> counselorListGUI = dataSet.getCounselorList();
                System.out.println(counselorListGUI);
                for (int b = 0; b < counselorListGUI.size(); b++) {
                    if (counselorListGUI.get(b).equals("Edward Reed")) {
                        reed++;
                    } else if (counselorListGUI.get(b).equals("Melissa Nagy")) {
                        nagy++;
                    } else if (counselorListGUI.get(b).equals("Barbara Martin")) {
                        martin++;
                    } else if (counselorListGUI.get(b).equals("David Gysberts")) {
                        gysberts++;
                    } else if (counselorListGUI.get(b).equals("Nelly Boishin")) {
                        boishin++;
                    }
                }
            }
        }
        else if(startYear== endYear-1) {
            for (int i = startMonth; i <= 12; i++) {
                CreateDataSet dataSet = new CreateDataSet();
                dataSet.fillLists(GUI.filePath + "\\ClientProject\\src\\" + i + "_" + startYear + ".csv");

                ArrayList<String> reasonListGUI = dataSet.getReasonList();
                for (int a = 0; a < reasonListGUI.size(); a++) {
                    if (reasonListGUI.get(a).equals("Schedule")) {
                        schedule++;
                    } else if (reasonListGUI.get(a).equals("Academic Planning")) {
                        planning++;
                    } else if (reasonListGUI.get(a).equals("Personal/Social/Emotional")) {
                        personal++;
                    } else if (reasonListGUI.get(a).equals("College and Career Information")) {
                        college++;
                    } else if (reasonListGUI.get(a).equals("Got a Pass from Counselor")) {
                        pass++;
                    } else {
                        other++;
                    }
                }


                ArrayList<Integer> gradeListGUI = dataSet.getGradeList();
                System.out.println(gradeListGUI);
                for (int b = 0; b < gradeListGUI.size(); b++) {
                    if (gradeListGUI.get(b) == 9) {
                        nine++;
                    } else if (gradeListGUI.get(b) == 10) {
                        ten++;
                    } else if (gradeListGUI.get(b) == 11) {
                        eleven++;
                    } else if (gradeListGUI.get(b) == 12) {
                        twelve++;
                    }
                }

                //counselors
                ArrayList<String> counselorListGUI = dataSet.getCounselorList();
                System.out.println(counselorListGUI);
                for (int b = 0; b < counselorListGUI.size(); b++) {
                    if (counselorListGUI.get(b).equals("Edward Reed")) {
                        reed++;
                    } else if (counselorListGUI.get(b).equals("Melissa Nagy")) {
                        nagy++;
                    } else if (counselorListGUI.get(b).equals("Barbara Martin")) {
                        martin++;
                    } else if (counselorListGUI.get(b).equals("David Gysberts")) {
                        gysberts++;
                    } else if (counselorListGUI.get(b).equals("Nelly Boishin")) {
                        boishin++;
                    }
                }
            }
            for (int i = 1; i <= endMonth; i++) {
                CreateDataSet dataSet = new CreateDataSet();
                dataSet.fillLists(GUI.filePath + "\\ClientProject\\src\\" + i + "_" + startYear + ".csv");

                ArrayList<String> reasonListGUI = dataSet.getReasonList();
                for (int a = 0; a < reasonListGUI.size(); a++) {
                    if (reasonListGUI.get(a).equals("Schedule")) {
                        schedule++;
                    } else if (reasonListGUI.get(a).equals("Academic Planning")) {
                        planning++;
                    } else if (reasonListGUI.get(a).equals("Personal/Social/Emotional")) {
                        personal++;
                    } else if (reasonListGUI.get(a).equals("College and Career Information")) {
                        college++;
                    } else if (reasonListGUI.get(a).equals("Got a Pass from Counselor")) {
                        pass++;
                    } else {
                        other++;
                    }
                }


                ArrayList<Integer> gradeListGUI = dataSet.getGradeList();
                System.out.println(gradeListGUI);
                for (int b = 0; b < gradeListGUI.size(); b++) {
                    if (gradeListGUI.get(b) == 9) {
                        nine++;
                    } else if (gradeListGUI.get(b) == 10) {
                        ten++;
                    } else if (gradeListGUI.get(b) == 11) {
                        eleven++;
                    } else if (gradeListGUI.get(b) == 12) {
                        twelve++;
                    }
                }

                //counselors
                ArrayList<String> counselorListGUI = dataSet.getCounselorList();
                System.out.println(counselorListGUI);
                for (int b = 0; b < counselorListGUI.size(); b++) {
                    if (counselorListGUI.get(b).equals("Edward Reed")) {
                        reed++;
                    } else if (counselorListGUI.get(b).equals("Melissa Nagy")) {
                        nagy++;
                    } else if (counselorListGUI.get(b).equals("Barbara Martin")) {
                        martin++;
                    } else if (counselorListGUI.get(b).equals("David Gysberts")) {
                        gysberts++;
                    } else if (counselorListGUI.get(b).equals("Nelly Boishin")) {
                        boishin++;
                    }
                }
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
            pieChart.getStylesheets().add("cssfile.css");
            pieTitle.getStyleClass().add("label-big");
            pieTitle.setText("Student Reasons for Visiting From " + GUI.inputs[0] + "/" + GUI.inputs[1]+" - " +GUI.inputs2[0] + "/" + GUI.inputs2[1] );

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
            pieChartGrade.getStylesheets().add("cssfile.css");
            gradeTitle.getStyleClass().add("label-big");
            gradeTitle.setText("Student Grade Levels From " + GUI.inputs[0] + "/" + GUI.inputs[1]+" - " +GUI.inputs2[0] + "/" + GUI.inputs2[1] );
            System.out.println(twelve);

            pieDataCouns = FXCollections.observableArrayList(
                    new PieChart.Data("Edward Reed- " + String.valueOf(reed), reed),
                    new PieChart.Data("Melissa Nagy- " + String.valueOf(nagy), nagy),
                    new PieChart.Data("Barbara Martin- " + String.valueOf(martin), martin),
                    new PieChart.Data("David Gysberts- " + String.valueOf(gysberts), gysberts),
                    new PieChart.Data("Nelly Boishin- " + String.valueOf(boishin), boishin)
            );
            System.out.println(pieDataGrade);
            pieChartCouns = new PieChart(pieDataCouns);
            pieChartCouns.setMinSize(625, 625);
            pieChartCouns.getStylesheets().add("cssfile.css");
            counsTitle.getStyleClass().add("label-big");
            counsTitle.setText("Visits to Counselors From " + GUI.inputs[0] + "/" + GUI.inputs[1]+" - " +GUI.inputs2[0] + "/" + GUI.inputs2[1] );


        }



    public static void create(){
        GUI.singleMonth.getStyleClass().add("button-singleMonth");
        GUI.singleMonth.setMinSize(110,36);
        GUI.rangeChoice.getStyleClass().add("button-range");
        GUI.rangeChoice.setMinSize(110,36);
        submitTime.getStyleClass().add("button-viewData2");
        submitTime.setMinSize(110,36);
        button.getStyleClass().add("button-goBack2");
        button.setMinSize(110,36);
        pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(11);
        scene2 = new Scene(pane, 1920, 1080);
        scene2.getStylesheets().add("cssfile.css");
        pane.setAlignment(Pos.CENTER);
        pane.add(inputMonthBox, 1, 3);
        pane.add(inputYearBox, 2, 3);
        pane.add(GUI.singleMonth,3,3);
        pane.add(GUI.rangeChoice,3,4);
        pane.add(inputMonthBox2,1,4);
        inputMonthBox2.setVisible(false);
        pane.add(inputYearBox2,2,4);
        inputYearBox2.setVisible(false);
        pane.add(submitTime, 1, 5);
        pane.add(pieTitle,1,1);
        pane.add(gradeTitle,2,1);
        pane.add(counsTitle,3,1);
        pane.add(button,2,5);
        pane.add(monthLabel,1,2);
        pane.add(yearLabel,2,2);



    }
    public static void setSheet(String sheet){
        scene2.getStylesheets().add(sheet);
        pieChart.getStylesheets().add(sheet);
        pieChartGrade.getStylesheets().add(sheet);
        pieChartCouns.getStylesheets().add(sheet);
    }
}
