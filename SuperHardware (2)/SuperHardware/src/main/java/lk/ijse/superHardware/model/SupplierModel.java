package lk.ijse.superHardware.model;

import lk.ijse.superHardware.db.DbConnection;
import lk.ijse.superHardware.dto.CustomerDto;
import lk.ijse.superHardware.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public boolean saveSupplier(final SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO supplier VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getSup_id());
        pstm.setString(2, dto.getSup_name());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, String.valueOf(dto.getEmail()));

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }
    public boolean updateSupplier(final SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE supplier SET sup_name = ?, address = ?, suplier_email= ? WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getSup_name());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getEmail());
        pstm.setString(4, dto.getSup_id());

        return pstm.executeUpdate() > 0;
    }
    public SupplierDto searchSupplier(String sup_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        

        String sql = "SELECT * FROM supplier WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, sup_id);

        ResultSet resultSet = pstm.executeQuery();

        SupplierDto dto = null;

        if(resultSet.next()) {
            sup_id = resultSet.getString(1);
            String sup_name = resultSet.getString(2);
            String sup_address = resultSet.getString(3);
            String sup_email = String.valueOf(resultSet.getInt(4));

            dto = new SupplierDto() ;Dto(sup_id,sup_name,sup_address,sup_email);
        }

        return dto;
    }

    private void Dto(String sup_id, String sup_name, String sup_address, String sup_email) {

    }

    public List<SupplierDto> getAllSuppliers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<SupplierDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String sup_id = resultSet.getString(1);
            String sup_name = resultSet.getString(2);
            String sup_address = resultSet.getString(3);
            String sup_email = resultSet.getString(4);

            var dto = new SupplierDto(sup_id, sup_name, sup_address, sup_email);
            dtoList.add(dto);
        }
        return dtoList;

    }


    public boolean deleteSupplier(String sup_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,sup_id);

        return pstm.executeUpdate() > 0;
    }
    public List<SupplierDto> loadAllSuppliers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<SupplierDto> supplierList = new ArrayList<>();

        while (resultSet.next()) {
            supplierList.add(new SupplierDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return supplierList;
    }


}





