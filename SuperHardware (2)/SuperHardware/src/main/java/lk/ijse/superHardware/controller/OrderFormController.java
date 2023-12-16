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
import lk.ijse.superHardware.dto.OrderDto;
import lk.ijse.superHardware.dto.tm.OrderTm;
import lk.ijse.superHardware.model.OrderModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Pattern;

import static net.sf.jasperreports.engine.JasperPrintManager.printReport;

public class OrderFormController {
    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colOrderPayment;

    @FXML
    private Label lblOrderId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<OrderTm> tblOrder;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtOrderPayment;

    @FXML
    private DatePicker txtOrderdate;


    private final OrderModel orderrModel = new OrderModel();



    @FXML
    void initialize() {

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("ord_id"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("cust_id"));
        colOrderPayment.setCellValueFactory(new PropertyValueFactory<>("ord_payment"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("ord_date"));


        setTable();
    }

    @FXML
    void orderTableOnMouseClickedAction(MouseEvent event) {//////////////new //////////////////////////////////////////
        TablePosition pos=tblOrder.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<OrderTm,?>> columns=tblOrder.getColumns();

        lblOrderId.setText(columns.get(0).getCellData(row).toString());
        txtCustomerId.setText(columns.get(1).getCellData(row).toString());
        txtOrderPayment.setText(columns.get(2).getCellData(row).toString());
        txtOrderdate.setValue(LocalDate.parse(columns.get(3).getCellData(row).toString()));
    }


    private void setTable() {
        ObservableList<OrderTm> obList = FXCollections.observableArrayList();
        OrderModel orderModel = new OrderModel();
        try {
            for (OrderDto allOrder : orderModel.getAllOrders()) {
                obList.add((new OrderTm(allOrder.getOrd_id(), allOrder.getCust_id(),allOrder.getOrd_payment(),allOrder.getOrd_date())));
            }
            tblOrder.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }



    @FXML
    void btnOnActionClear(ActionEvent event) {
        lblOrderId.setText("");
        txtCustomerId.setText("");
        txtOrderPayment.setText("");
        txtOrderdate.setValue(null);
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
        String orderId = lblOrderId.getText();
        OrderModel orderModel = new OrderModel();
        try {

            final boolean b = orderModel.deleteOrder(orderId);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "deleted").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "not deleted").show();
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

//    @FXML
//    void btnOnActionSave(ActionEvent event) {
//        String orderId = lblOrderId.getText();
//        String customerId = txtCustomerId.getText();
//        Double orderPayment = Double.valueOf(txtOrderPayment.getText());
//        Date orderDate = Date.valueOf(txtOrderdate.getValue());
//        try {
//            var orderModel = new OrderModel();
//
//            boolean isCustomerValidated = validateOrder();
//            final boolean b = orderModel.saveOrder(new OrderDto(orderId,
//                    orderPayment,
//                    orderDate,
//                    customerId)
//            );
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

    private boolean validateOrder() {

        //validate order_id
        String idText = lblOrderId.getText();

        boolean isOrderIdValidated = Pattern.compile("[O][0-9]{3,}").matcher(idText).matches();
        if (!isOrderIdValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid order id").show();
            return false;
        }

        //validate


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
        String orderId = lblOrderId.getText();
        String customerId = txtCustomerId.getText();
        Double orderPayment = Double.valueOf(txtOrderPayment.getText());
        Date orderDate = Date.valueOf(txtOrderdate.getValue());

        try {
            OrderModel orderModel = new OrderModel();
            final boolean b = orderModel.updateOrder(new OrderDto(
                    orderId,
                    orderPayment,
                    orderDate,
                    customerId
            ));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "updated").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "not updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            throw new RuntimeException(e);
        }

        setTable();

    }

    @FXML
    void btnReportOnAction(ActionEvent event) { printReport();}

    private void printReport() {

        try {
            String id = this.txtCustomerId .getText();
            InputStream resourceAsStream = getClass().getResourceAsStream("/report/Bill.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText(" select *from order_item_details \n " +"right join\n"+"orders \n "+" on order_item_details.order_id = orders.order_id;" );
            load.setQuery(jrDesignQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }


    }


}



