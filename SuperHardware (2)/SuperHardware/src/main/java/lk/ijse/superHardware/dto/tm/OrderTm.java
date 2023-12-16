package lk.ijse.superHardware.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderTm {
    private String ord_id;
    private String cust_id;

    private double ord_payment;

    private Date Ord_date;
}
