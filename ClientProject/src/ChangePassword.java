import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChangePassword {

        public static void show(){
            Stage passStage = new Stage();

            passStage.initModality(Modality.APPLICATION_MODAL);
            passStage.setTitle("Change Password");
            passStage.setMinWidth(250);
            passStage.setMinHeight(250);

            Label label = new Label("Please enter the new password");

            TextField text = new TextField();

            Button button = new Button("Change Password");

            GridPane grid = new GridPane();
            grid.add(label,0,0);
            grid.add(text,0,1);
            grid.add(button, 0,2);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setAlignment(Pos.CENTER);

            Scene scene = new Scene(grid,500,500);
            scene.getStylesheets().add("programSheet.css");
            passStage.setScene(scene);
            passStage.show();

            button.setOnAction( e ->{
                PropertyReadWrite.readPassword();
                PropertyReadWrite.writePassword(text.getText());
                passStage.close();

        });
    }

}
