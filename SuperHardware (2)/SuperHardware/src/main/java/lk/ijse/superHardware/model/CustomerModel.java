package lk.ijse.superHardware.model;

import lk.ijse.superHardware.db.DbConnection;
import lk.ijse.superHardware.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public static List<String> loadCustomerIds() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT cust_id FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        List<String> allCustIds = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            allCustIds.add(resultSet.getString(1));
        }
        return allCustIds;
    }
    public static CustomerDto searchByCustId(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer WHERE cust_id= ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()){
            return new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    //////////////////////////new ////////////////////////////////////////////////////////////////////////////

    public boolean saveCustomer(final CustomerDto dto) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1,dto.getCust_id());
            pstm.setString(2, dto.getCust_name());
            pstm.setString(3, dto.getAddress());
            pstm.setInt(4, dto.getContact());

            boolean isSaved = pstm.executeUpdate() > 0;

            return isSaved;
        }

    public boolean updateCustomer(final CustomerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE customer SET cust_name = ?, address = ?, contact= ? WHERE cust_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCust_name());
        pstm.setString(2, dto.getAddress());
        pstm.setInt(3, dto.getContact());
        pstm.setString(4, dto.getCust_id());

        return pstm.executeUpdate() > 0;
    }
    public CustomerDto searchCustomer(String cust_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer WHERE cust_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, cust_id);

        ResultSet resultSet = pstm.executeQuery();

        CustomerDto dto = null;

        if(resultSet.next()) {
            cust_id = resultSet.getString(1);
            String cust_name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int contact = resultSet.getInt(4);



            dto = new CustomerDto(cust_id,cust_name,address,contact);
        }

        return dto;
    }

    public List<CustomerDto> getAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<CustomerDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String cust_id = resultSet.getString(1);
            String cust_name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int contact = resultSet.getInt(4);

            var dto = new CustomerDto(cust_id, cust_name, address, contact);
            dtoList.add(dto);
        }
        return dtoList;

    }

    public boolean deleteCustomer(String cust_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM customer WHERE cust_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,cust_id);

        return pstm.executeUpdate() > 0;
    }


    public List<CustomerDto> loadAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<CustomerDto> custList = new ArrayList<>();

        while (resultSet.next()) {
            custList.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)
            ));
        }
        return custList;
    }

}







