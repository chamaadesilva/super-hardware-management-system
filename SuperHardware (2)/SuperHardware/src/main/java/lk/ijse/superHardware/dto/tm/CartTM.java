package lk.ijse.superHardware.dto.tm;

import javafx.scene.control.Button;

public class CartTM {
    private String itemCode;
    private String description;
    private Double unitPrice;
    private Integer qty;
    private Double total;
    private Button btnAction;

    public CartTM() {
    }

    public CartTM(String itemCode, String description, Double unitPrice, Integer qty, Double total, Button btnAction) {
        this.itemCode = itemCode;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
        this.btnAction = btnAction;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Button getBtnAction() {
        return btnAction;
    }

    public void setBtnAction(Button btnAction) {
        this.btnAction = btnAction;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", total=" + total +
                ", btnAction=" + btnAction +
                '}';
    }
}
