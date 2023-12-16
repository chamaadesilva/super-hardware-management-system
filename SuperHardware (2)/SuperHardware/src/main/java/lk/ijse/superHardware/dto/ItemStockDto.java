package lk.ijse.superHardware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemStockDto {
   private String item_stock_code;
   private String item_name;
   private double item_unit_price;
   private int quantity;
   private String item_cateogry;


}