package lk.ijse.superHardware.model;

import lk.ijse.superHardware.db.DbConnection;
import lk.ijse.superHardware.dto.CustomerDto;
import lk.ijse.superHardware.dto.DeliveryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryModel {

    public boolean saveDelivery(final DeliveryDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO delivery VALUES(?, ?, ?, ? ,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getDelivery_id());
        pstm.setString(2, dto.getEmp_id());
        pstm.setString(3, dto.getOrd_id());
        pstm.setString(4, dto.getLocation());
        pstm.setDate(5,Date.valueOf(dto.getDelivered_date()));

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean updateDelivery(final DeliveryDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE delivery SET emp_id = ?, ord_id = ?, location = ?,delivered_date =? WHERE delivery_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEmp_id());
        pstm.setString(2, dto.getOrd_id());
        pstm.setString(3, dto.getLocation());
        pstm.setDate(4, Date.valueOf(dto.getDelivered_date()));
        pstm.setString(5,  dto.getDelivery_id());

        return pstm.executeUpdate() > 0;
    }

    public DeliveryDto searchDelivery(String delivery_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM delivery WHERE delivery_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, delivery_id);

        ResultSet resultSet = pstm.executeQuery();

        DeliveryDto dto = null;

        if (resultSet.next()) {
            delivery_id = resultSet.getString(1);
            String emp_id = resultSet.getString(2);
            String ord_id = resultSet.getString(3);
            String location = resultSet.getString(4);
            Date delivered_date = resultSet.getDate(5);


            dto = new DeliveryDto(delivery_id, emp_id, ord_id, location, delivered_date);

            return dto;
        }
        return dto;
    }

    public List<DeliveryDto> getAllDeliveries() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM delivery";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<DeliveryDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String delivery_id = resultSet.getString(1);
            String emp_id = resultSet.getString(2);
            String ord_id = resultSet.getString(3);
            String location = resultSet.getString(4);
            Date delivered_date = resultSet.getDate(5);

            var dto = new DeliveryDto(delivery_id,emp_id,ord_id,location,delivered_date);
            dtoList.add(dto);
        }
        return dtoList;

    }
    public boolean deleteDelivery(String delivery_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM delivery WHERE delivery_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,delivery_id);

        return pstm.executeUpdate() > 0;
    }

    public List<DeliveryDto> loadAllDeliveries() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM delivery";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<DeliveryDto> deliveryList = new ArrayList<>();

        while (resultSet.next()) {
            deliveryList.add(new DeliveryDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDate(5)
            ));
        }
        return deliveryList;
    }
}























