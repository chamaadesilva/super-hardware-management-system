package lk.ijse.superHardware.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemDetailsDto {
   private String order_id;
   private List<CartDTO> cartDTOList;
}
