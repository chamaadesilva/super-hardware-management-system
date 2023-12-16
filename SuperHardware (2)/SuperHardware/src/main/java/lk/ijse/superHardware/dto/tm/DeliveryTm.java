package lk.ijse.superHardware.dto.tm;

import java.util.Date;

public class DeliveryTm {
    private String delivery_id;

    private String emp_id;

    private String ord_id;

    private String location;

    private String delivered_date;

    public DeliveryTm(String delivery_id, String emp_id, String ord_id, String location, String delivered_date) {
        this.delivery_id = delivery_id;
        this.emp_id = emp_id;
        this.ord_id = ord_id;
        this.location = location;
        this.delivered_date = delivered_date;
    }



    public String getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(String delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getOrder_id() {
        return ord_id;
    }

    public void setOrd_id(String ord_id) {
        this.ord_id = ord_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {

        this.location = location;
    }

    public String getDelivered_date() {

        return delivered_date;
    }

    public void setDelivered_date(String  delivered_date) {
        this.delivered_date = delivered_date;
    }



}
