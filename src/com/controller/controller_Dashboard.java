
package com.controller;

import com.control.db.ConnectionDB;
import com.model.List_chart;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
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
    
    public List<BigDecimal> getRevenue(){
        // Unit 0, Total 1,Profit 2;
        List<BigDecimal> Money=new ArrayList<>();
        try(Connection cnn=ConnectionDB.getConnection()){
            Statement statement=cnn.createStatement();
            String query="SELECT SUM(UnitPrice) AS Unit, SUM(TotalPrice) AS Total, SUM(Profit) AS Profit FROM Invoice_Items";
            ResultSet re=statement.executeQuery(query);
            while(re.next()){   
                Money.add(re.getBigDecimal("Unit"));
                Money.add(re.getBigDecimal("Total"));
                Money.add(re.getBigDecimal("Profit"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return Money;
    }
    
    public String convertToMonthName(int month) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] monthNames = dfs.getMonths();
        return monthNames[month-1];
    }
    
    public List<List_chart> getMonthlyrevenue() throws SQLException{
        List<List_chart> chart =new ArrayList<>();
        
        int[] myArray = new int[12];
        for (int i = 0; i < 12; i++) {
            myArray[i] = i + 1;
        }
         
         for(int i=0;i<12;i++){   
             List_chart basic=new  List_chart(convertToMonthName(myArray[i]),BigDecimal.ZERO);
             chart.add(i, basic);
         }
         
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="SELECT MONTH(Invoices.PurchaseDate) AS 'Month', SUM(Invoice_Items.TotalPrice) AS 'Total' FROM Invoice_Items JOIN Invoices ON Invoice_Items.InvoiceId = Invoices.InvoiceId GROUP BY MONTH(Invoices.PurchaseDate)";
         try{
                ResultSet re=statement.executeQuery(query);
                while(re.next()){
                      String month=convertToMonthName(re.getInt("Month"));
                      BigDecimal total=re.getBigDecimal("Total");
                      List_chart tmp=new List_chart(month, total);
                      chart.set(re.getInt("Month")-1, tmp);
                }
              
         }   
         catch(Exception ex){
             ex.printStackTrace();
         }   
        return chart;
    }
}
