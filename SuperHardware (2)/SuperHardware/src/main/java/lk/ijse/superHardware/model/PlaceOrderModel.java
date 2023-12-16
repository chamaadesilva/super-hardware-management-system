package lk.ijse.superHardware.model;

import lk.ijse.superHardware.db.DbConnection;
import lk.ijse.superHardware.dto.CartDTO;
import lk.ijse.superHardware.dto.OrderDto;
import lk.ijse.superHardware.dto.OrderItemDetailsDto;
import lk.ijse.superHardware.model.ItemStockModel;
import lk.ijse.superHardware.model.OrderItemDetailsModel;
import lk.ijse.superHardware.model.OrderModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderModel {

    public static boolean placeOrder(String ordrId, String customerId, Date date, double netTotal, List<CartDTO> cartDTOList) throws SQLException {
        Connection con = null;

        try{
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            OrderDto orderDto = new OrderDto(ordrId, netTotal, date, customerId);
            boolean isSaved = OrderModel.save(orderDto, con);
            if(isSaved){
                boolean isUpdate = ItemStockModel.updateQty(cartDTOList);
                if(isUpdate){
                    OrderItemDetailsDto detailsDto = new OrderItemDetailsDto(ordrId, cartDTOList);
                    boolean isOrdered = OrderItemDetailsModel.save(detailsDto);
                    if(isOrdered){
                            con.commit();
                            return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            System.out.println("place order= "+er);
            con.rollback();
            return false;
        } finally {
            con.setAutoCommit(true);
        }
    }
}
