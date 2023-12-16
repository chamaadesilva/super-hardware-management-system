package lk.ijse.superHardware.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.superHardware.dto.CartDTO;
import lk.ijse.superHardware.dto.CustomerDto;
import lk.ijse.superHardware.dto.ItemStockDto;
import lk.ijse.superHardware.dto.tm.CartTM;
import lk.ijse.superHardware.dto.tm.OrderTm;
import lk.ijse.superHardware.util.ValidateController;
import lk.ijse.superHardware.model.CustomerModel;
import lk.ijse.superHardware.model.ItemStockModel;
//import lk.ijse.superHardware.model.OrderModel;
import lk.ijse.superHardware.model.OrderModel;
import lk.ijse.superHardware.model.PlaceOrderModel;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class OrderItemDetailsFormController {
    public Label lblQtyOnHand;
    public Label txtTotal;
    public Label lblCustname;
    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private ComboBox<String> cmbCustId;

    @FXML
    private ComboBox<String> cmbitemCode;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colitemCode;


    @FXML
    private Label lblItemDescription;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;


    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CartTM> tblPlaceOrder;

    @FXML
    private TextField txtQty;

    private ObservableList<CartTM> obList = FXCollections.observableArrayList();
    private ItemStockDto itemStockDto;


    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

        if (lblNetTotal.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Cart is empty").show();
        }else {

            String ordrId = lblOrderId.getText();
            String customerId = cmbCustId.getValue();

            double netTotal = Double.parseDouble(lblNetTotal.getText());

            Calendar calendar = Calendar.getInstance();
            Date date = new Date(calendar.getTimeInMillis());

            List<CartDTO> cartDTOList = new ArrayList<>();

            for (int i = 0; i < tblPlaceOrder.getItems().size(); i++) {
                CartTM cartTM = obList.get(i);

                CartDTO dto = new CartDTO(
                        cartTM.getItemCode(),
                        cartTM.getQty(),
                        cartTM.getTotal()
                );
                cartDTOList.add(dto);
            }

            try {
                boolean isPlaced = PlaceOrderModel.placeOrder(ordrId, customerId, date, netTotal, cartDTOList);
                if (isPlaced) {
                    generateNextOrderId();
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Placed").show();


                    clearTxtFields();
                    tblPlaceOrder.getItems().clear();
                    lblCustname.setText("");
                    cmbCustId.setValue(null);
                    lblNetTotal.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Order Not Placed").show();
            }
        }
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        if(lblCustname.getText().isEmpty() || lblItemDescription.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please make sure to fill out all the required fields.").show();
        }else{
            if(!txtQty.getText().isEmpty()){
                if(ValidateController.intValueCheck(txtQty.getText())){

                    String code = cmbitemCode.getValue();
                    String itemName = lblItemDescription.getText();
                    int qty = Integer.parseInt(txtQty.getText());
                    double unitPrice = Double.parseDouble(lblUnitPrice.getText());

                    double total = unitPrice*qty;

                    Button btnRemove = new Button("Remove");
                    btnRemove.setCursor(Cursor.HAND);

                    setRemoveBtnOnAction(btnRemove);

                    if(qty>itemStockDto.getQuantity()){
                        new Alert(Alert.AlertType.ERROR, "Item "+itemName+" out of stock or not enough stock").show();
                    }else {
                        clearTxtFields();
                        txtQty.setText("");

                        if (!obList.isEmpty()) {
                            for (int i = 0; i < tblPlaceOrder.getItems().size(); i++) {
                                if (colitemCode.getCellData(i).equals(code)) {
                                    qty += (int) colQty.getCellData(i);
                                    total = qty * unitPrice;

                                    obList.get(i).setQty(qty);
                                    obList.get(i).setTotal(total);

                                    tblPlaceOrder.refresh();
                                    calculateNetTotal();
                                    return;
                                }
                            }
                        }

                        CartTM tm = new CartTM(code, itemName, unitPrice, qty, total, btnRemove);

                        obList.add(tm);
                        tblPlaceOrder.setItems(obList);
                        calculateNetTotal();
                        //clearTxtFields();
                        //txtQty.setText("");
                        setTotal();
                    }

                }else {
                    new Alert(Alert.AlertType.ERROR, "Invalid").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Empty").show();
            }
        }
    }

    private void setTotal() {
        double  total =0;
        for (CartTM  tm:obList){
            total+=tm.getTotal();
        }
        txtTotal.setText(String.valueOf(total));
    }

    private void setRemoveBtnOnAction(Button btnRemove) {
        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblPlaceOrder.getSelectionModel().getSelectedIndex();
                System.out.println(index);
                obList.remove(index);

                tblPlaceOrder.refresh();
                calculateNetTotal();
            }

        });
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblPlaceOrder.getItems().size(); i++) {
            double total = (double) colTotal.getCellData(i);
            netTotal += total;
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    private void loadCustomerIds(){
        try {
            ObservableList<String> custIds = FXCollections.observableArrayList();
            List<String> ids = CustomerModel.loadCustomerIds();

            for(String id : ids){
                custIds.add(id);
            }

            cmbCustId.setItems(custIds);
        } catch (SQLException throwables) {
            System.out.println("loadCustomerIds = "+throwables);
        }
    }
    private void loadItemCodes() {
        ObservableList<String> itemCodes = FXCollections.observableArrayList();
        try {
            List<String> cods = ItemStockModel.loadItemCodes();

            for(String cod : cods){
                itemCodes.add(cod);
            }
            cmbitemCode.setItems(itemCodes);
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            System.out.println("loadItemCodes = "+throwables);
        }

    }

    private void generateNextOrderId() {
        try {
            String id = OrderModel.getNextOrderId();
            System.out.println(id);
            lblOrderId.setText(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void cmbCustIdOnAction(ActionEvent actionEvent) {
        String id = cmbCustId.getValue();
        try {
            CustomerDto customer = CustomerModel.searchByCustId(id);

            lblCustname.setText(customer.getCust_name());

        } catch (Exception e) {
            //e.printStackTrace();
            //System.out.println("custId "+e);
        }
    }

    public void cmbItemCodeOnAction(ActionEvent actionEvent) {
        String code = cmbitemCode.getValue();

        try {
            itemStockDto = ItemStockModel.searchByItemCode(code);
            lblItemDescription.setText(itemStockDto.getItem_name());
            lblUnitPrice.setText(String.valueOf(itemStockDto.getItem_unit_price()));
            lblQtyOnHand.setText(String.valueOf(itemStockDto.getQuantity()));

            if(itemStockDto.getQuantity()>0){
                lblQtyOnHand.setText(String.valueOf(itemStockDto.getQuantity()));
            }else{
                lblQtyOnHand.setText("Out Of Stock");
                //AlertController.errormessage("item "+itemStockDto.getItem_name()+" out of stock");
                new Alert(Alert.AlertType.ERROR, "item "+itemStockDto.getItem_name()+" out of stock").show();
            }
        } catch (Exception e) {
            //throwables.printStackTrace();
            //System.out.println("item code " +e);
        }
    }

    public void clearTxtFields(){
        cmbitemCode.setValue(null);
        lblItemDescription.setText("");
        lblUnitPrice.setText("");
        lblQtyOnHand.setText("");
    }

    private void setCellValueFactory() {
        colitemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnAction"));
    }

    @FXML
    void initialize(){
        loadCustomerIds();
        loadItemCodes();
        generateNextOrderId();
        setCellValueFactory();

        lblOrderDate.setText(String.valueOf(LocalDate.now()));
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
}

