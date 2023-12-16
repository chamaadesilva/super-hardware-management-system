package lk.ijse.superHardware.model;

import lk.ijse.superHardware.db.DbConnection;
import lk.ijse.superHardware.dto.CustomerDto;
import lk.ijse.superHardware.dto.SupplierOrderDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierOrderModel {
    public boolean saveSupplierOrder(final SupplierOrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO supplier_order VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getSupplierOrderId());
        pstm.setString(2, dto.getSupplierId());
        pstm.setDate(3, (Date) dto.getDate());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean updateSupplierOrder(final SupplierOrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE supplier_order SET  sup_id = ?, date= ? WHERE supplier_order_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSupplierOrderId());
        pstm.setString(2, dto.getSupplierId());
        pstm.setDate(3, (Date) dto.getDate());

        return pstm.executeUpdate() > 0;
    }

    public SupplierOrderDto searchSupplierOrder(String supplier_order_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier_order WHERE supplier_order_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplier_order_id);

        ResultSet resultSet = pstm.executeQuery();

        SupplierOrderDto dto = null;

        if(resultSet.next()) {
            supplier_order_id = resultSet.getString(1);
            String sup_id = resultSet.getString(2);
            Date date = resultSet.getDate(3);

          dto = new SupplierOrderDto(supplier_order_id,sup_id,date);
        }

        return dto;
    }

    public List<SupplierOrderDto> getAllSupplierOrders() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier_order";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<SupplierOrderDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String supplier_order_id = resultSet.getString(1);
            String sup_id = resultSet.getString(2);
            Date date = resultSet.getDate(3);


            var dto = new SupplierOrderDto(supplier_order_id,sup_id,date);
            dtoList.add(dto);
        }
        return dtoList;

    }
    public boolean deleteSupplierOrder(String suppler_order_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM supplier_order WHERE supplier_order_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,suppler_order_id);

        return pstm.executeUpdate() > 0;
    }
    public List<SupplierOrderDto> loadAllSupplierOrders() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier_order";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<SupplierOrderDto> supplierOrderList = new ArrayList<>();

        while (resultSet.next()) {
            supplierOrderList.add(new SupplierOrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3)

            ));
        }
        return supplierOrderList;
    }

}




