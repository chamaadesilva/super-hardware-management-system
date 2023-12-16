package lk.ijse.superHardware;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/LoginFormController.fxml"))));
//        stage.setTitle("Customer Form");
//        stage.centerOnScreen();
//
//        stage.show();
//    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginFormController.fxml"));
        stage.setScene(new Scene(root));

        stage.show();
    }
}
