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
import lk.ijse.superHardware.dto.ItemStockDto;
import lk.ijse.superHardware.dto.tm.CustomerTm;
import lk.ijse.superHardware.dto.tm.ItemStockTm;
import lk.ijse.superHardware.model.CustomerModel;
import lk.ijse.superHardware.model.ItemStockModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.regex.Pattern;

import static java.lang.Double.valueOf;

public class ItemStockFormController {
    public TextField txtItemStockCode;
    public TextField txtItemName;
    public TextField txtItemUnitPrice;
    public TextField txtQuantity;
    public TextField txtItemCateogry;

    public TableColumn colItemStockCode;
    public TableColumn colItemName;
    public TableColumn colItemUnitPrice;
    public TableColumn colQuantity;
    public TableColumn colItemCateogry;
    public TableView tblItemStock;
    @FXML
    private AnchorPane root;

    private final ItemStockModel itemStockModel = new ItemStockModel();


    @FXML
    void initialize() {

        colItemStockCode.setCellValueFactory(new PropertyValueFactory<>("item_stock_code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("item_unit_price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colItemCateogry.setCellValueFactory(new PropertyValueFactory<>("item_cateogry"));


        setTable();
    }

    private void setTable() {
        ObservableList<ItemStockTm> obList = FXCollections.observableArrayList();
        ItemStockModel itemStockModel = new ItemStockModel();
        try {
            for (ItemStockDto allItemStock : itemStockModel.getAllItemStocks()) {
                obList.add(new ItemStockTm(allItemStock.getItem_stock_code(), allItemStock.getItem_name(), allItemStock.getItem_unit_price(), allItemStock.getQuantity(),allItemStock.getItem_cateogry()));
            }
            tblItemStock.setItems(obList);
        } catch (SQLException e) {


        }
    }

    @FXML
    void btnOnActionClear(ActionEvent event) {
        txtItemStockCode.setText("");
        txtItemName.setText("");
        txtItemUnitPrice.setText("");
        txtQuantity.setText("");
        txtItemCateogry.setText("");
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

        String itemStockCode = txtItemStockCode.getText();
        ItemStockModel itemStockModel = new ItemStockModel();
        try {

            final boolean b = itemStockModel.deleteItemStock(itemStockCode);
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

        String itemStockCode = txtItemStockCode.getText();
        String itemName = txtItemName.getText();
        Double itemUnitPrice = valueOf(txtItemUnitPrice.getText());
        Integer quantity = Integer.parseInt(txtQuantity.getText());
        String itemCateogry = txtItemCateogry.getText();
        try {
            var itemStockModel = new ItemStockModel();

            boolean isItemStockValidated = validateItemStock();
            final boolean b = itemStockModel.saveItemStock(new ItemStockDto(itemStockCode,itemName,itemUnitPrice,quantity,itemCateogry));
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

    private boolean validateItemStock() {

        //validate item_stock_code

        String idText = txtItemStockCode.getText();

        boolean isItemStockCodeValidated = Pattern.compile("[I][0-9]{3,}").matcher(idText).matches();
        if (!isItemStockCodeValidated){
            new Alert(Alert.AlertType.ERROR,"Invalid item_stock_id").show();
            return false;
        }

        //validate item stock name

        String nameText = txtItemName.getText();
        boolean isItemNameValidated = Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();
        if (!isItemNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid item name").show();
        }

        //validate quantity
        String quantityText  = txtQuantity.getText();
        boolean isQuantityValidated = Pattern.compile("[0-9]").matcher(nameText).matches();
        if (!isQuantityValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid item_cateogry name").show();
        }

        //validate description
        String descriptionText  = txtQuantity.getText();
        boolean isDescriptionValidated = Pattern.compile("[A-Za-z]").matcher(nameText).matches();
        if (!isDescriptionValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid descrription").show();
        }


        //validate item_cateogry

        String cateogryText  = txtItemCateogry.getText();
        boolean isItemCateogryValidated = Pattern.compile("[A-Za-z]").matcher(nameText).matches();
        if (!isItemCateogryValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid item_cateogry").show();
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
        String itemStockCode = txtItemStockCode.getText();
        String itemName = txtItemName.getText();
        Double itemUnitPrice = valueOf(txtItemUnitPrice.getText());
        Integer quantity = Integer.parseInt(txtQuantity.getText());
        String itemCateogry = txtItemCateogry.getText();
        try {
            ItemStockModel itemStockModel = new ItemStockModel();
            final boolean b = itemStockModel.updateItemStock(new ItemStockDto(itemStockCode,itemName,itemUnitPrice,quantity,itemCateogry));
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
    void btnItemStockOnAction(ActionEvent event)  { printOrder();}







//        String Id = this.txtItemStockCode.getText();
//        InputStream resourceAsStream = getClass().getResourceAsStream("/report/cherry.jpg");
//        JasperDesign load = JRXmlLoader.load(resourceAsStream);
//        JRDesignQuery jrDesignQuery = new JRDesignQuery();
//        jrDesignQuery.setText("SELECT * FROM item_stock WHERE item_stock_code = "+"\""+Id+"\"");
//        load.setQuery(jrDesignQuery);
//
//        JasperReport jasperReport = JasperCompileManager.compileReport(load);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getInstance().getConnection());
//        JasperViewer.viewReport(jasperPrint,false);
private void printOrder()  {

        try {
            String item_stock_code = this.txtItemStockCode.getText();
            InputStream resourceAsStream = getClass().getResourceAsStream("/report/item_stock.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText("SELECT * FROM item_stock ");
            load.setQuery(jrDesignQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void tblOnMouseClickedAction(MouseEvent mouseEvent) {
        TablePosition pos= (TablePosition) tblItemStock.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<ItemStockTm,?>> columns=tblItemStock.getColumns();

        txtItemStockCode.setText(columns.get(0).getCellData(row).toString());
        txtItemName.setText(columns.get(1).getCellData(row).toString());
        txtItemUnitPrice.setText(columns.get(4).getCellData(row).toString());
        txtQuantity.setText(columns.get(2).getCellData(row).toString());
        txtItemCateogry.setText(columns.get(3).getCellData(row).toString());

    }
}











