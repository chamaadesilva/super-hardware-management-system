package lk.ijse.superHardware.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.superHardware.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

        @FXML
        private Button btnLogin;

        @FXML
        private AnchorPane loginPane;

      //  private String UserName="chamaa";
       // private String Password= "1234";

        @FXML
        private TextField txtPassword;


        @FXML
    private TextField txtUserName;

//    public LoginFormController(Button btnLogin) {
//        this.btnLogin = btnLogin;
//    }


//    public LoginFormController(Button btnLogin) {
//        this.btnLogin = btnLogin;
//    }





////        System.out.printf("sample");
//
//            if (UserName.equals(txtUserName.getText()) && Password.equals(txtPassword.getText())){
//
//                Parent root;
//                root = FXMLLoader.load(this.getClass().getResource("/view/HomePage_From.fxml"));
//
//                // Parent root = FXMLLoader.load(this.getClass().getResource("/view/DashBoardFormController.fxml"));
//                Scene scene=new Scene(root);
//                Stage stage = new Stage();
//                stage.setScene(scene);
//                stage.show();
//
//                Stage stage1 = (Stage) loginPane.getScene().getWindow();
//                stage1.close();
//
//            }else{
//                new Alert(Alert.AlertType.ERROR,"login information are incorrect");
//            }

//        System.out.println("button press");
//
//        String UserName=txtUserName.getText();
//        String Password=txtPassword.getText();
//
//        try {
//
//
//            boolean isCheck = UserModel.getUser(UserName, Password);
//            if (isCheck) {
//                // new Alert(Alert.AlertType.CONFIRMATION,"LOGIN SUCCESSFULLY").show();
//
//                //open homepage
//
//
//
//                AppInitializer.stageLogIn.close();
//
//                Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/homePageForm.fxml"));
//
//                Scene scene = new Scene(rootNode);
//
//                Stage stage = new Stage();
//                stage.setTitle("home page");
//
//                stage.setScene(scene);
//                stage.show();
//            }
//            else {
//                new Alert(Alert.AlertType.ERROR,"INCORRECT USER NAME OR PASSWORD").show();
//            }
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException, SQLException {

            var UserModel = new UserModel();

            boolean isCorrect =UserModel .isCurrect(txtUserName.getText(), txtPassword.getText());

            if (isCorrect){
                Parent root = FXMLLoader.load(getClass().getResource("/view/HomePage_From.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();

            }else {
                new Alert(Alert.AlertType.WARNING,"Wrong Password").show();
                txtUserName.clear();
                txtPassword.clear();
            }

    }



    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

}
