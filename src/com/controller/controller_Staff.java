package com.controller;

import com.control.db.ConnectionDB;
import com.model.Staff;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class controller_Staff {
     public List<Staff>getAllStaff() throws SQLException{
         List<Staff> staffs =new ArrayList<>();
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="SELECT * FROM Staffs";
         try{
                ResultSet re=statement.executeQuery(query);
                while(re.next()){
                    int id=re.getInt("StaffId");
                    String name=re.getString("Name");
                    int age=re.getInt("Age");
                    String email=re.getString("Email");
                    String address=re.getString("Address");
                    Staff staff =new Staff(id, name, age, email, address);
                    staffs.add(staff);
                }
                
         }   
         catch(Exception ex){
             ex.printStackTrace();
         }   
        return staffs;
    }
    // 
     public void addStaff(Staff staff) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();
        String query="INSERT INTO Staffs (Name, Age, Email,Address) VALUES(?,?,?,?)";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setString(1, staff.getName());   
            pre.setInt(2, staff.getAge());       
            pre.setString(3, staff.getEmail());
            pre.setString(4, staff.getAddress());
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     //
        public void editStaff(Staff staff) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();

        String query="UPDATE Staffs SET  Name =?,Age =?,Email =?,Address=? WHERE StaffId =?";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setString(1, staff.getName());       
            pre.setInt(2, staff.getAge());
            pre.setString(3, staff.getEmail());
            pre.setString(4, staff.getAddress());
            pre.setInt(5, staff.getStaffId());
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //
         public void deleteStaff(int staffId) throws SQLException{
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="DELETE FROM Staffs WHERE StaffId =?";
         try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1,staffId);       
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
