package dbgui;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * Created by Michael's Account on 11/18/2015.
 */
public final class user {

    private  static String userName;
    private static String password;
    private static Connection conn;
    private static String jdbcUrl;


    public user(){

       //Main Constructor
    }

    public  static String getUserName(){
        return userName;
    }

    public static void setUserName(String userName){
        user.userName = userName;
    }

    public static String getPassword(){
        return password;
    }
    public static void setPassword(String password){
        user.password = password;
    }

    public static Connection getConn(){
        return conn;
    }

    public static void setConn(Connection conn){
        user.conn=conn;
    }

    public static String getJdbcUrl(){
        return jdbcUrl;
    }

    public static void  setJdbcUrl(String jdbcUrl){
        user.jdbcUrl=jdbcUrl;
    }



}
