package lk.ijse.superHardware.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor

public class DeliveryDto {
    private String delivery_id;

    private String emp_id;

    private String ord_id;

    private String location;

    private Date delivered_date;

    public String getDelivery_id() {

        return delivery_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public String getOrd_id() {

        return ord_id;
    }

    public String getLocation() {

        return location;
    }

    public String getDelivered_date() {

        return String.valueOf(delivered_date);
    }
}










