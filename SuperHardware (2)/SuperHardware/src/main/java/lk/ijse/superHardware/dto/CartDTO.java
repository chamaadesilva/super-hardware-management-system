package lk.ijse.superHardware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDTO {
    private String itemCode;
    private Integer itemQty;
    private Double itemTotal;

}
