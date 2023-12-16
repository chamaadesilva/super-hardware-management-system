package lk.ijse.superHardware.dto.tm;

public class ItemStockTm {
    private String item_stock_code;

    private String item_name;

    private double item_unit_price;

    private int quantity;

    private String item_cateogry;

    public String getItem_stock_code() {
        return item_stock_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public double getItem_unit_price() {
        return item_unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItem_cateogry() {
        return item_cateogry;
    }

    public ItemStockTm(String item_stock_code, String item_name, double item_unit_price, int quantity, String item_cateogry) {
        this.item_stock_code = item_stock_code;
        this.item_name = item_name;
        this.item_unit_price = item_unit_price;
        this.quantity = quantity;
        this.item_cateogry = item_cateogry;
    }
}
