
package com.controller;

import com.control.db.ConnectionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class controller_Dashboard {
 
    public int getSoldQuanity() throws SQLException{
        int Quanity=0;
        try(Connection cnn=ConnectionDB.getConnection()){
            Statement statement=cnn.createStatement();
            String query="SELECT SUM(Quantity) AS TotalQuanity FROM Invoice_Items";
            ResultSet re=statement.executeQuery(query);
            while(re.next()){
                Quanity=re.getInt("TotalQuanity");
            }
       }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return Quanity;
    }
    
    public int getTotalCustomer(){
        int Total=0;
        try(Connection cnn=ConnectionDB.getConnection()){
            Statement statement=cnn.createStatement();
            String query="SELECT COUNT(CustomerId) AS TotalCustomer FROM Customers";
            ResultSet re=statement.executeQuery(query);
            while(re.next()){
                Total=re.getInt("TotalCustomer");
            }
       }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return Total;
    }
    
    public List<Double> getRevenue(){
        // Unit 0, Total 1,Profit 2;
        List<Double> Money=new ArrayList<>();
        try(Connection cnn=ConnectionDB.getConnection()){
            Statement statement=cnn.createStatement();
            String query="SELECT SUM(UnitPrice) AS Unit, SUM(TotalPrice) AS Total, SUM(Profit) AS Profit FROM Invoice_Items";
            ResultSet re=statement.executeQuery(query);
            while(re.next()){   
                Money.add(re.getDouble("Unit"));
                Money.add(re.getDouble("Total"));
                Money.add(re.getDouble("Profit"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return Money;
    }
    
}
