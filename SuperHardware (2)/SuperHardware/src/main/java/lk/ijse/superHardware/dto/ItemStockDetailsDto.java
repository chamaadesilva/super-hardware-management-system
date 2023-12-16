package lk.ijse.superHardware.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class ItemStockDetailsDto {

   private String item_stock_code;

   private String ord_id;

   private int ord_quantity;

}
