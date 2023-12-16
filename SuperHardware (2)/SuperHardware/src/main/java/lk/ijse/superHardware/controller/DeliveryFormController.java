package lk.ijse.superHardware.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.superHardware.dto.DeliveryDto;
import lk.ijse.superHardware.dto.tm.DeliveryTm;
import lk.ijse.superHardware.model.DeliveryModel;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

//public class DeliveryFormController {
//
//    public TextField txtDeliveryId;
//    public TextField txtOrderId;
//    public TextField txtEmployeeId;
//    public TextField txtLocation;
//    public DatePicker txtDeliveredDate;
//    public TableColumn colDeliveryId;
//    public TableColumn colOrderId;
//    public TableColumn colEmployeeId;
//    public TableColumn colLocation;
//    public TableColumn colDeliveredDate;
//    public TableView tblDelivery;
//
//    @FXML
//    private AnchorPane root;
//
//
//    @FXML
//    void initialize() {
//
//        colDeliveryId.setCellValueFactory(new PropertyValueFactory<>("delivery_id"));
//        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
//        colOrderId.setCellValueFactory(new PropertyValueFactory<>("ord_id"));
//        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
//        colDeliveredDate.setCellValueFactory(new PropertyValueFactory<>("delivered_date"));
//
//
//        setTable();
//    }
//
//    private void setTable() {
//        ObservableList<DeliveryTm> obList = FXCollections.observableArrayList();
//        DeliveryModel deliveryModel = new DeliveryModel();
//        try {
//            for (DeliveryDto allDelivery : deliveryModel.getAllDeliveries()) {
//                obList.add(new DeliveryTm(allDelivery.getDelivery_id(), allDelivery.getEmp_id(), allDelivery.getOrd_id(), allDelivery.getLocation(),allDelivery.getDelivered_date()));
//            }
//            tblDelivery.setItems(obList);
//        } catch (SQLException e) {
//
//
//        }
//    }
//
//
//
//    @FXML
//    void btnOnActionClear(ActionEvent event) {
//        txtDeliveryId.setText("");
//        txtEmployeeId.setText("");
//        txtOrderId.setText("");
//        txtLocation.setText("");
//        txtDeliveredDate.setValue(null);
//    }
//
//
//
//
//    @FXML
//    void btnOnActionCustomer(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerFormController.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("Customer Manage");
//        stage.centerOnScreen();
//
//    }
//
//    @FXML
//    void btnOnActionDashboard(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashboardFormController.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("Dashboard Manage");
//        stage.centerOnScreen();
//
//    }
//
//    @FXML
//    void btnOnActionDelete(ActionEvent event) {
//        String deliveryId = txtDeliveryId.getText();
//        DeliveryModel deliveryModel = new DeliveryModel();
//        try {
//
//            final boolean b = deliveryModel.deleteDelivery(deliveryId);
//            if (b) {
//                new Alert(Alert.AlertType.CONFIRMATION, "saved").show();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "not saved").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            throw new RuntimeException(e);
//        }
//        setTable();
//    }
//
//
//
//    @FXML
//    void btnOnActionDelivery(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DeliveryFormController.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("Delivery Manage");
//        stage.centerOnScreen();
//
//    }
//
//    @FXML
//    void btnOnActionEmployee(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/EmployeeFormController.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("Employee Manage");
//        stage.centerOnScreen();
//
//    }
//
//    @FXML
//    void btnOnActionFullReport(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/FullReportFormController.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("FullReport Manage");
//        stage.centerOnScreen();
//
//    }
//
//    @FXML
//    void btnOnActionItemStock(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ItemStockFormController.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("ItemStock Manage");
//        stage.centerOnScreen();
//
//    }
//
//    @FXML
//    void btnOnActionItemStockDetails(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ItemStockDetailsFormController.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("ItemStockDetails Manage");
//        stage.centerOnScreen();
//
//    }
//
//    @FXML
//    void btnOnActionOrder(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OrderFormController.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("Order Manage");
//        stage.centerOnScreen();
//
//    }
//
//    @FXML
//    void btnOnActionOrderItemDetails(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OrderItemDetailsFormController.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("OrderItemDetails Manage");
//        stage.centerOnScreen();
//
//
//    }
//
//    @FXML
//    void btnOnActionSave(ActionEvent event) {
//        String deliveryId = txtDeliveryId.getText();
//        String orderId = txtEmployeeId.getText();
//        String employeeId = txtOrderId.getText();
//        String location = txtLocation.getText();
//        Date deliveredDate = Date.valueOf(txtDeliveredDate.getValue());
//        try {
//            var deliveryModel = new DeliveryModel();
//            final boolean b = deliveryModel.saveDelivery(new DeliveryDto(deliveryId,employeeId,orderId,location,deliveredDate));
//            if (b) {
//                new Alert(Alert.AlertType.CONFIRMATION, "saved").show();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "not saved").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//
//            throw new RuntimeException(e);
//        }
//        setTable();
//    }
//
//
//
//    @FXML
//    void btnOnActionSupplier(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SupplierFormController.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("Supplier Manage");
//        stage.centerOnScreen();
//
//    }
//
//    @FXML
//    void btnOnActionSupplierOrder(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SupplierOrderFormController.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("SupplierOrder Manage");
//        stage.centerOnScreen();
//
//    }
//
//    @FXML
//    void btnOnActionUpdate(ActionEvent event) {
//        String deliveryId = txtDeliveryId.getText();
//        String orderId = txtEmployeeId.getText();
//        String employeeId = txtOrderId.getText();
//        String location = txtLocation.getText();
//        Date deliveredDate = Date.valueOf(txtDeliveredDate.getValue());
//        try {
//            DeliveryModel deliveryModel = new DeliveryModel();
//            final boolean b = deliveryModel.updateDelivery(new DeliveryDto(deliveryId,employeeId,orderId,location,deliveredDate));
//            if (b) {
//                new Alert(Alert.AlertType.CONFIRMATION, "saved").show();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "not saved").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//
//            throw new RuntimeException(e);
//        }
//
//        setTable();
//
//    }
//
//    public void txtDeliveryId(ActionEvent actionEvent) {
//    }
//
//    public void txtOrderId(ActionEvent actionEvent) {
//    }
//
//    public void txtLocation(ActionEvent actionEvent) {
//    }
//
//    public void txtEmployeeId(ActionEvent actionEvent) {
//    }
//
//    public void txtDeliveredDate(ActionEvent actionEvent) {
//    }
//
//    public void colDeliveryId(TableColumn.CellEditEvent cellEditEvent) {
//    }
//
//    public void colEmployeeId(TableColumn.CellEditEvent cellEditEvent) {
//    }
//
//    public void colOrderId(TableColumn.CellEditEvent cellEditEvent) {
//    }
//
//    public void colLocation(TableColumn.CellEditEvent cellEditEvent) {
//    }
//
//    public void colDeliveredDate(TableColumn.CellEditEvent cellEditEvent) {
//    }
//
//    public void tblDelivery(SortEvent<TableView> tableViewSortEvent) {
//    }
//}
//
//
//
//
