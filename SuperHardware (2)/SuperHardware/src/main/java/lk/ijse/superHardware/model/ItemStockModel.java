package lk.ijse.superHardware.model;

import lk.ijse.superHardware.db.DbConnection;
import lk.ijse.superHardware.dto.CartDTO;
import lk.ijse.superHardware.dto.CustomerDto;
import lk.ijse.superHardware.dto.ItemStockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemStockModel {
    public static List<String> loadItemCodes() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT item_stock_code FROM item_stock";
        PreparedStatement pstm = connection.prepareStatement(sql);
        List<String> allItemCodes = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            allItemCodes.add(resultSet.getString(1));
        }
        return allItemCodes;
    }

    public static ItemStockDto searchByItemCode(String code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM item_stock WHERE item_stock_code= ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return new ItemStockDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }

    public static boolean updateQty(List<CartDTO> cartDTOList) throws SQLException {
        for(CartDTO dto : cartDTOList){
            if(!updateQty(dto)){
                return false;
            }
        }
        return true;
    }
    private static boolean updateQty(CartDTO dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE item_stock SET quantity = (quantity - ?) WHERE item_stock_code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, dto.getItemQty());
        pstm.setString(2, dto.getItemCode());

        return pstm.executeUpdate()>0;
    }

    ///////////////////////new ///////////////////////////////////////////////////////////////////////////

    public boolean saveItemStock(ItemStockDto itemStockDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO item_stock VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, itemStockDto.getItem_stock_code());
        pstm.setString(2, itemStockDto.getItem_name());
        pstm.setDouble(3, itemStockDto.getItem_unit_price());
        pstm.setInt(4, itemStockDto.getQuantity());
        pstm.setString(5, itemStockDto.getItem_cateogry());
        return pstm.executeUpdate() > 0;
    }

    public ItemStockDto searchItemStock(String item_stock_code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item WHERE item_stock_code = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, item_stock_code);

        ResultSet resultSet = pstm.executeQuery();

        ItemStockDto dto = null;

        if (resultSet.next()) {
                dto = new ItemStockDto(
                        resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getDouble(3),
            resultSet.getInt(4),
            resultSet.getString(5));

        }
        return dto;
    }

    public boolean deleteItemStock(String item_stock_code) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM item_stock WHERE item_stock_code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, item_stock_code);

        return pstm.executeUpdate() > 0;
    }

    public List<ItemStockDto> loadAllItemStocks() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM item_stock";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ItemStockDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var dto = new ItemStockDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            );

            dtoList.add(dto);
        }

        return dtoList;
    }


    public boolean updateItemStock(ItemStockDto itemStockDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE item_stock SET item_name = ?, item_unit_price = ?, quantity = ?, item_cateogry=? WHERE item_stock_code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,itemStockDto.getItem_name());
        pstm.setDouble(2, itemStockDto.getItem_unit_price());
        pstm.setDouble(3, itemStockDto.getQuantity());
        pstm.setString(4, itemStockDto.getItem_cateogry());
        pstm.setString(5, itemStockDto.getItem_stock_code());

        return pstm.executeUpdate() > 0;
    }

    public List<ItemStockDto> getAllItemStocks() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM item_stock";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<ItemStockDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String item_Stock_code = resultSet.getString(1);
            String item_name = resultSet.getString(2);
            double item_unit_price = resultSet.getDouble(3);
            int quantity = resultSet.getInt(4);
            String item_cateogry = resultSet.getString(5);

            var dto = new ItemStockDto(item_Stock_code,item_name ,item_unit_price,quantity,item_cateogry);
            dtoList.add(dto);
        }
        return dtoList;

    }
}

   // public boolean updateItem(List<CartTm> tmList) throws SQLException {
        //for (CartTm cartTm : tmList) {
            //if(!updateQty(cartTm)) {
               // return false;
           // }
       // }
       // return true;
  //  }

    //private boolean updateQty(CartTm cartTm) throws SQLException {
       // Connection connection = DbConnection.getInstance().getConnection();

        //String sql = "UPDATE item SET qty_on_hand = qty_on_hand - ? WHERE code = ?";
       // PreparedStatement pstm = connection.prepareStatement(sql);
        //pstm.setInt(1, cartTm.getQty());
        //pstm.setString(2, cartTm.getCode());

       // return pstm.executeUpdate() > 0; //true
   // }
//}

//}
