
package com.controller;

import com.model.Invoice;

import com.control.db.ConnectionDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class controller_Invoice {
     public List<Invoice>getAllInvoices() throws SQLException{
         List<Invoice> invoices =new ArrayList<>();
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="SELECT * FROM Invoices";
         try{
                ResultSet re=statement.executeQuery(query);
                while(re.next()){
                    int invoiceid=re.getInt("InvoiceId");
                    int id=re.getInt("CustomerId");
                    int Customerid=re.getInt("CustomerId");              
                    int Staffid=re.getInt("StaffId");
                    Date purchaseDate=re.getDate("PurchaseDate");
                    java.sql.Date createAt=re.getDate("CreatedAt");            
                    java.sql.Date updateAt=re.getDate("UpdatedAt");

                    Invoice invoice =new Invoice(Staffid, Customerid, Staffid, purchaseDate, LocalDateTime.MAX, LocalDateTime.MAX);
                    invoices.add(invoice);
                }
                
         }   
         catch(Exception ex){
             ex.printStackTrace();
         }   
        return invoices;
    }
     
    public void addInvoice(Invoice invoice) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();
        String query="INSERT INTO Invoices (CustomerId, StaffId, PurchaseDate,CreatedAt,UpdatedAt) VALUES(?,?,?,?,?)";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1, invoice.getCustomerId());                    
            pre.setInt(2, invoice.getStaffId());        
            pre.setDate(3, (java.sql.Date) invoice.getPurchaseDate());
            
            Date createAt = Date.from(invoice.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
            pre.setDate(4, (java.sql.Date) createAt);
      
            Date uppdateAt = Date.from(invoice.getUpdatedAt().atZone(ZoneId.systemDefault()).toInstant());
            pre.setDate(5, (java.sql.Date) uppdateAt);
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    } 
    
      public void editInvoice(Invoice invoice) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();

        String query="UPDATE Invoices SET  CustomerId =?,StaffId =?,PurchaseDate =? ,CreatedAt=?,UpdatedAt=?WHERE InvoiceId =?";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1, invoice.getCustomerId());       
            pre.setInt(2, invoice.getStaffId());
            pre.setDate(3, (java.sql.Date) invoice.getPurchaseDate());
            Date createAt = Date.from(invoice.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
            pre.setDate(4, (java.sql.Date) createAt);
            Date updpateAt = Date.from(invoice.getUpdatedAt().atZone(ZoneId.systemDefault()).toInstant());
            pre.setDate(5, (java.sql.Date) updpateAt);
            pre.setInt(6,invoice.getInvoiceId() );
            
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
      
     public void deleteInvoice (int invoiceId) throws SQLException{
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="DELETE FROM Invoices WHERE InvoiceId =?";
         try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1,invoiceId);       
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
}
