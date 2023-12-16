package lk.ijse.superHardware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
public class OrderDto {

    private String ord_id;

    private Double ord_payment;

    private Date ord_date;

   private String cust_id;

    public OrderDto(String ord_id, Double ord_payment, Date ord_date, String cust_id) {
        this.ord_id = ord_id;
        this.ord_payment = ord_payment;
        this.ord_date = ord_date;
        this.cust_id = cust_id;
    }
}