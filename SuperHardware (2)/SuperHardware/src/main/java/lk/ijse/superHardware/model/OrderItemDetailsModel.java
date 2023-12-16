package lk.ijse.superHardware.model;

import lk.ijse.superHardware.db.DbConnection;
import lk.ijse.superHardware.dto.CartDTO;
import lk.ijse.superHardware.dto.OrderItemDetailsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDetailsModel {

    public static boolean save(OrderItemDetailsDto detailsDto) throws SQLException {
        for(CartDTO dto : detailsDto.getCartDTOList()){
            if(!save(detailsDto.getOrder_id(), dto)){
                return false;
            }
        }
        return true;
    }
    public static boolean save(String orderId, CartDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO order_item_details(item_stock_code, order_id, ord_quantity)" +
                "VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getItemCode());
        pstm.setString(2, orderId);
        pstm.setInt(3, dto.getItemQty());

        return pstm.executeUpdate()>0;
    }
}
