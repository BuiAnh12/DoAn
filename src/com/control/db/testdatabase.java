
package com.control.db;
import java.sql.*;

public class testdatabase {
    
    public static void setData(String id, String name,String diachi, String quequan, String sdt ,String IdHoadon) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();
        String query="insert into Customer(CMND,Name,Diachi,Quequan,Sdt,IdHoadon) values(?,?,?,?,?,?) ";

        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setString(1, id);           
            pre.setString(2, name);
            pre.setString(3, diachi);
            pre.setString(4, quequan);
            pre.setString(5, sdt);
            pre.setString(6, IdHoadon);
            pre.executeUpdate();

        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static void getData() throws SQLException{
        Connection cnn=ConnectionDB.getConnection();
        Statement statement=cnn.createStatement();
        String query="select * from Customer";
        
        try{
            ResultSet re=statement.executeQuery(query);
             while(re.next()){
            // Trong do CMND Name la thuoc tinh table Customer 
            String Id=re.getString("CMND");
            String name=re.getString("Name");
            String diachi=re.getString("Diachi");                   
            String quequan=re.getString("Quequan");
            String sdt=re.getString("Sdt");
            String hoadon=re.getString("IdHoadon");
            System.out.println(Id +" "+name +" "+diachi +" "+quequan +" "+sdt +" "+hoadon );
        }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws SQLException {
        setData("1","Nguyen Van A","QN","QN","039979912","123");
        getData();
    }
}
