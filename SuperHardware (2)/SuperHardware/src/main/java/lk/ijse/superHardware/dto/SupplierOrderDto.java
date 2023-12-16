package lk.ijse.superHardware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierOrderDto {

   private String supplier_order_id;

   private Date date;

   private String sup_id;

   public SupplierOrderDto(String supplier_order_id, String sup_id, java.sql.Date date) {
   }

   public String getSupplierOrderId() {
      return getSupplierOrderId();
   }

   public String getSupplierId() {
      return getSupplierId();
   }
}
