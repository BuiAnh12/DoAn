package com.control.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
    public static Connection getConnection() throws SQLException {
        Connection a = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DOAN_PHANBON;encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String password = "n21dccn003#12042003";
            a = (Connection) DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return a;
    }

    public static void main(String[] args) throws SQLException {
        Connection cnn = getConnection();
        if (cnn != null) {
            System.out.println("True");
        }
    }
}
