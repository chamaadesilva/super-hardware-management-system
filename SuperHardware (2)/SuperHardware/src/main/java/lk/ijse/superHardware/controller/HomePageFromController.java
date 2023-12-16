package lk.ijse.superHardware.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.superHardware.util.Navigation;
import lombok.SneakyThrows;

public class HomePageFromController implements Initializable {

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane txtAnchor;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(txtAnchor,"CustomerFormController.fxml","customer");

    }

   // @FXML

//    void btnDashboardOnAction(ActionEvent event) throws IOException {
//
//        Navigation.switchPaging(txtAnchor,"DashboardFormController.fxml","dashboard");
//
//    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {

        Navigation.switchPaging(txtAnchor,"EmployeeFormController.fxml","employee");

    }

    @FXML
    void btnItemStockOnAction(ActionEvent event) throws IOException {

        Navigation.switchPaging(txtAnchor,"ItemStockFormController.fxml","Item");



    }

    @SneakyThrows
    @FXML
    void btnOrderItemDetailsOnAction(ActionEvent event) throws IOException {

        Navigation.switchPaging(txtAnchor,"OrderItemDetailsFormController.fxml","Item");

    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {

        Navigation.switchPaging(txtAnchor,"OrderFormController.fxml","order");


    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {

        Navigation.switchPaging(txtAnchor,"SupplierFormController.fxml","supplier");



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

          //Navigation.switchPaging(txtAnchor,"DashboardFormController.fxml","Dashboard");


    }


    public void btnDashboardOnAction(ActionEvent actionEvent) {

        try {
           Navigation.switchPaging(txtAnchor,"Dashboard1FromController.fxml","Dashboard");
        } catch (IOException e) {
           throw new RuntimeException(e);
       }

    }
}


