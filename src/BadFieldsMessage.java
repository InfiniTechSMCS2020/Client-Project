import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BadFieldsMessage {
        static int choice;
        static Scene scene;
        static boolean isOpen = false;
        public static void show() {
            isOpen= true;
            Stage badStage = new Stage();
            badStage.initModality(Modality.APPLICATION_MODAL);
            badStage.setTitle("Incorrect Fields");
            badStage.setMinWidth(750);
            badStage.setMinHeight(750);

            Label label = new Label(GUI.errorString);
            label.getStyleClass().add("label-big");
            Label label2= new Label("Please choose a solution.");
            label2.getStyleClass().add("label-big");
            Button button = new Button();
            button.getStyleClass().add("button-erase");
            button.setMinSize(141,53);
            Button button2 = new Button();
            button2.getStyleClass().add("button-keep");
            button2.setMinSize(141,53);
            GridPane grid = new GridPane();
            grid.add(label, 0, 0);
            grid.add(label2,0,1);
            grid.add(button, 0, 2);
            grid.add(button2, 0, 3);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setAlignment(Pos.CENTER);

            scene = new Scene(grid, 750, 750);
            scene.getStylesheets().

                    add("cssfile.css");
            badStage.setScene(scene);
            badStage.show();

            button.setOnAction(e ->

            {
                badStage.close();
                choice = 1;
                GUI.idBox.setText("");
                GUI.nameBox.setText("");
                GUI.counsBox.setValue("");
                GUI.gradeBox.setText("");
                GUI.reasonBox.setValue("");
                GUI.other.setText("");
                GUI.other.setVisible(false);
                if(GUI.teachBox.getItems().isEmpty()){
                    System.out.println("THIS IS NOT ACTUALLY BROKEN");
                    GUI.teachBox.getEditor().setText("");
                    for(int i=0; i<FileReaderTeacher.teacherList.size();i++) {
                        System.out.println(FileReaderTeacher.teacherList.get(i) + "This thing");
                        GUI.teachBox.getItems().add(FileReaderTeacher.teacherList.get(i));
                    }
                }
                if(GUI.idBox.getText().equals("")){
                    System.out.println("THIS IS NOT ACTUALLY BROKEN");
                    GUI.idBox.setText("");
                    GUI.nameBox.setText("");
                    GUI.gradeBox.setText("");
                    GUI.counsBox.setValue("");
                    GUI.teachBox.getEditor().setText("");
                   // for(int i=0; i<GUI.idArray.size();i++) {
                   //     System.out.println(GUI.idArray.get(i) + "This thing");
                   //     GUI.idBox.getItems().add(GUI.idArray.get(i));
                  //  }
                }
            });
            button2.setOnAction(e ->

            {
                badStage.close();
                choice = 2;
            });
        }

    }



