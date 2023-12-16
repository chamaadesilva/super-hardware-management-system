package lk.ijse.superHardware.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.superHardware.dto.EmployeeDto;
import lk.ijse.superHardware.dto.tm.EmployeeTm;
import lk.ijse.superHardware.dto.tm.ItemStockTm;
import lk.ijse.superHardware.model.EmployeeModel;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class EmployeeFormController { 
   public TextField txtEmployeeId;
   public TextField txtEmployeeName;
   public TextField txtAddress;

   public TableColumn colEmployeeId;
   public TableColumn colEmployeeName;
   public TableColumn colAddress;
  
   public TableView tblEmployee;

 
   @FXML
   private AnchorPane root;

    private final EmployeeModel employeeModel = new EmployeeModel();



    @FXML
   void initialize() {

   colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
   colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
   colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));

   setTable();
 }

    private void setTable() {
      ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
      EmployeeModel employeeModel = new EmployeeModel();
      try {
         for (EmployeeDto allEmployee : employeeModel.getAllEmployees()) {
      obList.add((new EmployeeTm(allEmployee.getEmp_id(), allEmployee.getEmp_name(), allEmployee.getAddress())));
     }
        tblEmployee.setItems(obList);
     } catch (SQLException e) {


  }
 }


 @FXML
    void btnOnActionClear(ActionEvent event) {
       txtEmployeeId.setText("");
       txtEmployeeName.setText("");
       txtAddress.setText("");
    
   }
 


 @FXML
    void btnOnActionCustomer(ActionEvent event) throws IOException {
     AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerFormController.fxml"));
     Scene scene = new Scene(anchorPane);
     Stage stage = (Stage) root.getScene().getWindow();
     stage.setScene(scene);
     stage.setTitle("Customer Manage");
     stage.centerOnScreen();

    }

    @FXML
    void btnOnActionDashboard(ActionEvent event) throws IOException {
     AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashboardFormController.fxml"));
     Scene scene = new Scene(anchorPane);
     Stage stage = (Stage) root.getScene().getWindow();
     stage.setScene(scene);
     stage.setTitle("Dashboard Manage");
     stage.centerOnScreen();


    }

     @FXML
     void btnOnActionDelete(ActionEvent event) {
      String employeeId = txtEmployeeId.getText();
      EmployeeModel employeeModel = new EmployeeModel();
      try {

       final boolean b = employeeModel.deleteEmployee(employeeId);
       if (b) {
        new Alert(Alert.AlertType.CONFIRMATION, "saved").show();
       } else {
        new Alert(Alert.AlertType.ERROR, "not saved").show();
       }
      } catch (SQLException e) {
       new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
       throw new RuntimeException(e);
      }
      setTable();
     }

 

    @FXML
    void btnOnActionDelivery(ActionEvent event) throws IOException {
     AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DeliveryFormController.fxml"));
     Scene scene = new Scene(anchorPane);
     Stage stage = (Stage) root.getScene().getWindow();
     stage.setScene(scene);
     stage.setTitle("Delivery Manage");
     stage.centerOnScreen();
    }

    @FXML
    void btnOnActionEmployee(ActionEvent event) throws IOException {
     AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/EmployeeFormController.fxml"));
     Scene scene = new Scene(anchorPane);
     Stage stage = (Stage) root.getScene().getWindow();
     stage.setScene(scene);
     stage.setTitle("Employee Manage");
     stage.centerOnScreen();

    }

    @FXML
    void btnOnActionFullReport(ActionEvent event) throws IOException {
     AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/FullReportFormController.fxml"));
     Scene scene = new Scene(anchorPane);
     Stage stage = (Stage) root.getScene().getWindow();
     stage.setScene(scene);
     stage.setTitle("FullReport Manage");
     stage.centerOnScreen();

    }

    @FXML
    void btnOnActionItemStock(ActionEvent event) throws IOException {
     AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ItemStockFormController.fxml"));
     Scene scene = new Scene(anchorPane);
     Stage stage = (Stage) root.getScene().getWindow();
     stage.setScene(scene);
     stage.setTitle("ItemStock Manage");
     stage.centerOnScreen();

    }

    @FXML
    void btnOnActionItemStockDetails(ActionEvent event) throws IOException {
     AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ItemStockDetailsFormController.fxml"));
     Scene scene = new Scene(anchorPane);
     Stage stage = (Stage) root.getScene().getWindow();
     stage.setScene(scene);
     stage.setTitle("ItemStockDetails Manage");
     stage.centerOnScreen();

    }

    @FXML
    void btnOnActionOrder(ActionEvent event) throws IOException {
     AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OrderFormController.fxml"));
     Scene scene = new Scene(anchorPane);
     Stage stage = (Stage) root.getScene().getWindow();
     stage.setScene(scene);
     stage.setTitle("Order Manage");
     stage.centerOnScreen();

    }

    @FXML
    void btnOnActionOrderItemDetails(ActionEvent event) throws IOException {
     AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OrderItemDetailsFormController.fxml"));
     Scene scene = new Scene(anchorPane);
     Stage stage = (Stage) root.getScene().getWindow();
     stage.setScene(scene);
     stage.setTitle("OrderItemDetails Manage");
     stage.centerOnScreen();

    }

    @FXML
    void btnOnActionSave(ActionEvent event) {
     String employeeId = txtEmployeeId.getText();
     String employeeName = txtEmployeeName.getText();
     String address = txtAddress.getText();
     try {
      var employeeModel = new EmployeeModel();

         boolean isEmployeeValidated = validateEmployee();

      final boolean b = employeeModel.saveEmployee(new EmployeeDto(employeeId,employeeName, address));
      if (b) {
       new Alert(Alert.AlertType.CONFIRMATION, "saved").show();
      } else {
       new Alert(Alert.AlertType.ERROR, "not saved").show();
      }
     } catch (SQLException e) {
      new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

      throw new RuntimeException(e);
     }
     setTable();
    }

    private boolean validateEmployee() {

        //validate employee id

        String idText = txtEmployeeId.getText();
        boolean isEmployeeIdValidated = Pattern.compile("[E][0-9]{3,}").matcher(idText).matches();
        if (!isEmployeeIdValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee id").show();
            return false;
        }

        // validate employee name

        String nameText = txtEmployeeName.getText();
        boolean isEmployeeNameValidated = Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();
        if (!isEmployeeNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee name").show();
            return false;

        }


        //validate employee address

        String addressText = txtAddress.getText();
        boolean isEmployeeAddressValidate = Pattern.compile("[A-Za-z0-9/.\\s]{3,}").matcher(addressText).matches();
        if (!isEmployeeAddressValidate) {

            new Alert(Alert.AlertType.ERROR, "Invalid employee address").show();
            return false;
        }

            return true;
        }



    @FXML
    void btnOnActionSupplier(ActionEvent event) throws IOException {
      AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SupplierFormController.fxml"));
      Scene scene = new Scene(anchorPane);
      Stage stage = (Stage) root.getScene().getWindow();
      stage.setScene(scene);
      stage.setTitle("Supplier Manage");
      stage.centerOnScreen();
    }

    @FXML
    void btnOnActionSupplierOrder(ActionEvent event) throws IOException {
     AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SupplierOrderFormController.fxml"));
     Scene scene = new Scene(anchorPane);
     Stage stage = (Stage) root.getScene().getWindow();
     stage.setScene(scene);
     stage.setTitle("SupplierOrder Manage");
     stage.centerOnScreen();

    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
     String employeeId = txtEmployeeId.getText();
     String employeeName = txtEmployeeName.getText();
     String address = txtAddress.getText();
     try {
      EmployeeModel employeeModel = new EmployeeModel();
      final boolean b = employeeModel.updateEmployee(new EmployeeDto(employeeId,employeeName, address));
      if (b) {
       new Alert(Alert.AlertType.CONFIRMATION, "saved").show();
      } else {
       new Alert(Alert.AlertType.ERROR, "not saved").show();
      }
     } catch (SQLException e) {
      new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

      throw new RuntimeException(e);
     }

     setTable();

    }

    public void tblOnMouseClickedAction(javafx.scene.input.MouseEvent mouseEvent) {
        TablePosition pos= (TablePosition) tblEmployee.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<ItemStockTm,?>> columns=tblEmployee.getColumns();

        txtEmployeeId.setText(columns.get(0).getCellData(row).toString());
        txtEmployeeName.setText(columns.get(1).getCellData(row).toString());
        txtAddress.setText(columns.get(2).getCellData(row).toString());

    }
    }






