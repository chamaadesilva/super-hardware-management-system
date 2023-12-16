package lk.ijse.superHardware.model;

import lk.ijse.superHardware.db.DbConnection;
import lk.ijse.superHardware.dto.UserDto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    private static DbConnection Dbconnection;
    private static String name;

    //   public static boolean getUser(String userName, String password) throws SQLException {

//        Connection connection= Dbconnection.getInstance().getConnection();
//
//        String sql="SELECT * FROM user WHERE userName=? AND password=?";
//
//        PreparedStatement pstm=connection.prepareStatement(sql);
//        pstm.setString(1,userName);
//        pstm.setString(2,password);
//
//        ResultSet resultSet=pstm.executeQuery();
//
//        if(resultSet.next()){
//            return true;
//        }
//        return false;
//    }

    public static String searchUser(String name) throws SQLException {

        String result = null;

        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM user WHERE user_id=?");
        pstm.setString(1, name);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            result = resultSet.getString(3);
        }
        return result;
    }

    public boolean saveUser() throws SQLException {
        return saveUser(null);
    }

    public boolean saveUser(UserDto userDto) throws SQLException {
        Connection connection =DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("INSERT INTO user VALUES (?,?,?) ");
        pstm.setString(1, userDto.getU_id());
        pstm.setString(2, userDto.getName());
        pstm.setString(3, userDto.getPassword());



        return pstm.executeUpdate()>0;
    }
    public static boolean isCurrect(String name, String password) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM user WHERE name = ? && password = ? ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,name);
        pstm.setObject(2,password);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            return true;
        }else {
            return false;
        }

       /* if (resultSet.next()){
            if (uerName.equals(resultSet.getString(1))){
                if (password.equals(resultSet.getString(2))){
                    return true;
                }
                else {
                    new Alert(Alert.AlertType.INFORMATION,"Wrong Password").show();
                }
            }
            else {
                new Alert(Alert.AlertType.INFORMATION,"Wrong Username").show();
            }
        }
        return false;*/
    }



}
