package lk.ijse.superHardware.common;

import lk.ijse.superHardware.db.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IDController {
    public static String getLastID(String tableName, String colName) throws SQLException, ClassNotFoundException {
        String sql = "select " + colName + " from " + tableName + " order by 1 desc limit 1";
        Connection conn = DbConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
