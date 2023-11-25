
package com.control.db;
import java.sql.*;

public class testdatabase {
    
    public static void setData(String Id, String Name,String Adress, String PhoneNum ,String InvoiceId) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();
        String query="insert into Customer(Id,Name,Address,PhoneNum,InvoiceId) values(?,?,?,?,?) ";

        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setString(1, Id);           
            pre.setString(2, Name);
            pre.setString(3, Adress);
            pre.setString(4, PhoneNum);
            pre.setString(5, InvoiceId);
            pre.executeUpdate();

        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static void getData() throws SQLException{
        Connection cnn=ConnectionDB.getConnection();
        Statement statement=cnn.createStatement();
        String query="select * from Customers";
        
        try{
            ResultSet re=statement.executeQuery(query);
            while(re.next()){
            // Trong do CMND Name la thuoc tinh table Customer 
            String id=re.getString("CustomerId");
            String name=re.getString("CustomerName");
            String email = re.getString("Email");
            String adress=re.getString("Address");

            System.out.println(id +" "+name +" "+email +" "+adress );
        }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws SQLException {
//        setData("23","Nguyen Van A","QN","039979912","123");
        getData();
    }
}
