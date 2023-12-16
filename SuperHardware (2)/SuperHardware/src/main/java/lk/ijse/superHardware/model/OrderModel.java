package lk.ijse.superHardware.model;

import lk.ijse.superHardware.db.DbConnection;
import lk.ijse.superHardware.dto.CustomerDto;
import lk.ijse.superHardware.dto.OrderDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    public static String getNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1";

        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("OD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "OD-" + digit;
        }
        return "OD-001";
    }

    public static boolean save(OrderDto dto, Connection dbConnection) throws SQLException {
        Connection connection = dbConnection;

        String sql = "INSERT INTO orders VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getOrd_id());
        pstm.setString(2, dto.getCust_id());
        pstm.setDouble(3, dto.getOrd_payment());
        pstm.setDate(4, dto.getOrd_date());

        return pstm.executeUpdate() > 0;
    }

    ////////////////////////place order//////////////////////////////////////////////////////////////////////////////

    public static boolean saveOrder(OrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getOrd_id());
        pstm.setString(2, dto.getCust_id());
        pstm.setDouble(3, dto.getOrd_payment());
        pstm.setDate(4, dto.getOrd_date());

        return pstm.executeUpdate() > 0;
    }


//    public String generateNextOrderId() throws SQLException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "SELECT ord_id FROM orders ORDER BY ord_id DESC LIMIT 1";
//        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
//
//        String currentOrderId = null;
//
//        if (resultSet.next()) {
//            currentOrderId = resultSet.getString(1);
//            return splitOrderId(currentOrderId);
//        }
//        return splitOrderId(null);
//    }
//
//    private String splitOrderId(String currentOrderId) {
//        if (currentOrderId != null) {
//            String[] split = currentOrderId.split("O");
//            int cust_id = Integer.parseInt(split[1]);
//           cust_id++;
//            return "O00" + cust_id;
//        }
//        return "O001";
//    }

    public boolean updateOrder(final OrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE orders SET cust_id = ?,payment =?,ord_date = ? WHERE order_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCust_id());
        pstm.setDouble(2, dto.getOrd_payment());
        pstm.setDate(3, (Date) dto.getOrd_date());
        pstm.setString(4, dto.getOrd_id());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteOrder(String ord_id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM orders WHERE order_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,ord_id);

        return pstm.executeUpdate() > 0;
    }

    public List<OrderDto> getAllOrders() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM orders";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<OrderDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String ord_id = resultSet.getString(1);
            String cust_id = resultSet.getString(2);
            double ord_payment = resultSet.getDouble(3);
            Date ord_date = resultSet.getDate(4);

            OrderDto dto = new OrderDto(ord_id,ord_payment,ord_date,cust_id);
            dtoList.add(dto);
        }
        return dtoList;

    }


}