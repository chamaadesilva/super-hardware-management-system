package lk.ijse.superHardware.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.superHardware.dto.SupplierDto;
import lk.ijse.superHardware.dto.tm.ItemStockTm;
import lk.ijse.superHardware.dto.tm.SupplierTm;
import lk.ijse.superHardware.model.SupplierModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class SupplierFormController {
    public TextField txtSupplierId;
    public TextField txtSupplierName;
    public TextField txtSupplierAddress;
    public TextField txtSupplierEmail;
    public TableColumn colSupplierId;
    public TableColumn colSupplierName;
    public TableColumn colSupplierAddress;
    public TableColumn colSupplierEmail;
    public TableView tblSupplier;
    
    
    @FXML
    private AnchorPane root;


    private final SupplierModel supplierModel = new SupplierModel();


    @FXML
    void initialize() {

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplier_address"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("supplier_email"));


        setTable();
    }

    private void setTable() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();
        SupplierModel supplierModel = new SupplierModel();
        try {
            for (SupplierDto allSupplier : supplierModel.getAllSuppliers()) {
                obList.add((new SupplierTm(allSupplier.getSup_id(), allSupplier.getSup_name(), allSupplier.getAddress(), allSupplier.getEmail())));
            }
            tblSupplier.setItems(obList);
        } catch (SQLException e) {


        }
    }

    @FXML
    void btnOnActionClear(ActionEvent event) {
        txtSupplierId.setText("");
        txtSupplierName.setText("");
        txtSupplierAddress.setText("");
        txtSupplierEmail.setText("");
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
        String supplierId = txtSupplierId.getText();
        SupplierModel supplierModel = new SupplierModel();
        try {

            final boolean b = supplierModel.deleteSupplier(supplierId);
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
        String supplierId = txtSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String supplierAddress = txtSupplierAddress.getText();
        String supplierEmail = txtSupplierEmail.getText();
        try {
            var supplierModel = new SupplierModel();

            boolean isSupplierValidated = validateSupplier();
            final boolean b = supplierModel.saveSupplier(new SupplierDto(supplierId, supplierName, supplierAddress,supplierEmail));
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

    private boolean validateSupplier() {
        // validate supplier id

        String idText = txtSupplierId.getText();
        boolean isSupplierIdValidated = Pattern.compile("[S][0-9]{3,}").matcher(idText).matches();
        if (!isSupplierIdValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier id").show();
            return false;
        }

        //validate supplier name
        String nameText = txtSupplierName.getText();
        boolean isSupplierNameValidated = Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();
        if (!isSupplierNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier name").show();
            return false;
        }

        // validate supplier address
        String addressText = txtSupplierAddress.getText();
        boolean isSupplierAddressValidated = Pattern.compile("[A-Za-z0-9/.\\s]{3,}").matcher(nameText).matches();
        if (!isSupplierAddressValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier address").show();
            return false;
        }

        //validate supplier email
        String emailText = txtSupplierEmail.getText();
        boolean isSupplierEmailValidated = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b").matcher(nameText).matches();
        if (!isSupplierEmailValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier email").show();
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
        String supplierId = txtSupplierId.getText();
        String supplierName = txtSupplierName.getText();
        String supplierAddress = txtSupplierAddress.getText();
        String supplierEmail = txtSupplierEmail.getText();
        try {
            SupplierModel supplierModel = new SupplierModel();
            final boolean b = supplierModel.updateSupplier(new SupplierDto(supplierId,supplierName,supplierAddress,supplierEmail));
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


    public void tblOnMouseClickedAction(MouseEvent mouseEvent) {
        TablePosition pos= (TablePosition) tblSupplier.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<ItemStockTm,?>> columns=tblSupplier.getColumns();

        txtSupplierId.setText(columns.get(0).getCellData(row).toString());
        txtSupplierName.setText(columns.get(1).getCellData(row).toString());
        txtSupplierAddress.setText(columns.get(2).getCellData(row).toString());
        txtSupplierEmail.setText(columns.get(3).getCellData(row).toString());
    }
}


