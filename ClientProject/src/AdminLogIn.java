import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminLogIn {
    public static String PASSWORD = "phs";  //settimgs screen change password
    public static boolean password;

    public static void show() {
        Stage adminStage = new Stage();

        adminStage.initModality(Modality.APPLICATION_MODAL);
        adminStage.setTitle("Admin Sign-In");
        adminStage.setMinWidth(750);
        adminStage.setMinHeight(750);

        Label label = new Label("Please enter in the Admin password");
        label.getStyleClass().add("label-big");
        Label label2 = new Label("Password not correct");
        label2.setVisible(false);

        PasswordField text = new PasswordField();

        Button button = new Button();
        button.getStyleClass().add("button-signIn");
        button.setMinSize(141 ,53);
        GridPane grid = new GridPane();
        grid.add(label, 0, 0);
        grid.add(text, 0, 1);
        grid.add(button, 0, 2);
        grid.add(label2, 0, 3);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 500, 500);
        scene.getStylesheets().add("programSheet.css");
        adminStage.setScene(scene);
        adminStage.show();

        button.setOnAction(e -> {
            String vpassword = PropertyReadWrite.readPassword();
            if (text.getText().equals(vpassword)) {
                password = true;
                label2.setVisible(false);
                if (AdminLogIn.password == true) {
                    GUI.setAdminScene();
                }
                adminStage.close();
            } else {
                password = false;
                label2.setVisible(true);
            }

        });
    }
}
