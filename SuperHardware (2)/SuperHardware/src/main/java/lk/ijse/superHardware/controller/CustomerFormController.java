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
import lk.ijse.superHardware.db.DbConnection;
import lk.ijse.superHardware.dto.CustomerDto;

import lk.ijse.superHardware.dto.tm.CustomerTm;
import lk.ijse.superHardware.model.CustomerModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class CustomerFormController {
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtAddress;
    public TextField txtContact;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colAddress;
    public TableColumn colContact;
    @FXML
    private TableView<CustomerTm> tblCustomer;
    @FXML
    private AnchorPane root;

    private final CustomerModel customerModel = new CustomerModel();

    @FXML
    void initialize() {

        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));


        setTable();
    }

    @FXML
    void tblOnMouseClickedAction(MouseEvent event) {   ///////////////new///////////////////////////////////////////
        TablePosition pos=tblCustomer.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<CustomerTm,?>> columns=tblCustomer.getColumns();

        txtCustomerId.setText(columns.get(0).getCellData(row).toString());
        txtCustomerName.setText(columns.get(1).getCellData(row).toString());
        txtAddress.setText(columns.get(2).getCellData(row).toString());
        txtContact.setText(columns.get(3).getCellData(row).toString());
    }

    private void setTable() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        CustomerModel customerModel = new CustomerModel();
        try {
            for (CustomerDto allCustomer : customerModel.getAllCustomers()) {
                obList.add((new CustomerTm(allCustomer.getCust_id(), allCustomer.getCust_name(), allCustomer.getAddress(), allCustomer.getContact())));
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {


        }
    }

    @FXML
    void btnOnActionClear(ActionEvent event) {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
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
        String customerId = txtCustomerId.getText();
        CustomerModel customerModel = new CustomerModel();
        try {

            final boolean b = customerModel.deleteCustomer(customerId);
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
        String customerId = txtCustomerId.getText();
        String customerName = txtCustomerName.getText();
        String address = txtAddress.getText();
        Integer contact = parseInt(txtContact.getText());
        try {
            var customerModel = new CustomerModel();

            boolean isCustomerValidated = validateCustomer();
            final boolean b = customerModel.saveCustomer(new CustomerDto(customerId, customerName, address, contact));
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

    private boolean validateCustomer() {

        // validate customer id

        String idText = txtCustomerId.getText();

        boolean isCustomerIdValidated = Pattern.compile("[C][0-9]{3,}").matcher(idText).matches();
        if (!isCustomerIdValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer id").show();
            return false;
        }

        // validate customer name


        String nameText = txtCustomerName.getText();
        boolean isCustomerNameValidated = Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();
        if (!isCustomerNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer name").show();
            return false;

        }

        String addressText = txtAddress.getText();
        boolean isCustomerAddressValidate = Pattern.compile("[A-Za-z0-9/.\\s]{3,}").matcher(addressText).matches();
        if (!isCustomerAddressValidate) {

            new Alert(Alert.AlertType.ERROR, "Invalid customer address").show();
            return false;

        }

        String contactText = txtContact.getText();
        boolean isCustomerContactValidate = Pattern.compile("(?:\\+?[0-9]+[\\s.-]?)?(?:[0-9][\\s.-]?){9,}").matcher(contactText).matches();
        if (!isCustomerContactValidate) {

            new Alert(Alert.AlertType.ERROR, "Invalid Customer contact ").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnOnActionSupplier(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OrderItemDetailsFormController.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("OrderItemDetails Manage");
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
        String customerId = txtCustomerId.getText();
        String customerName = txtCustomerName.getText();
        String address = txtAddress.getText();
        Integer contact = parseInt(txtContact.getText());
        try {
            CustomerModel customerModel = new CustomerModel();
            final boolean b = customerModel.updateCustomer(new CustomerDto(customerId, customerName, address, contact));
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
    void btnCustomerOnAction(ActionEvent event) {
         printCustomer();
    }



    private void printCustomer() {

        try {
            String Id = this.txtCustomerId.getText();
            InputStream resourceAsStream = getClass().getResourceAsStream("/report/customer.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText("SELECT * FROM customer ");
            load.setQuery(jrDesignQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }


    }


}


