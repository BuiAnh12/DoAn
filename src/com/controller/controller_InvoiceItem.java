/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import com.model.InvoiceItem;

import com.control.db.ConnectionDB;
import java.math.BigDecimal;
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

public class controller_InvoiceItem {
      public List<InvoiceItem>getAllInvoiceItems() throws SQLException{
         List<InvoiceItem> invoiceItems =new ArrayList<>();
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="SELECT * FROM Invoice_Items";
         try{
                ResultSet re=statement.executeQuery(query);
                while(re.next()){
                    int invoiceitemId=re.getInt("InvoiceItemId");
                    int invoiceid=re.getInt("InvoiceId");               
                    int productid=re.getInt("ProductId");
                    int importid=re.getInt("ImportId");
                    BigDecimal unitprice=re.getBigDecimal("UnitPrice");
                    int quanity=re.getInt("Quantity");
                    BigDecimal totalprice=re.getBigDecimal("TotalPrice");
                    BigDecimal profit=re.getBigDecimal("Profit");
                     java.sql.Date createAt=re.getDate("CreatedAt");            
                    java.sql.Date updateAt=re.getDate("UpdatedAt");
                     InvoiceItem invoiceitem=new InvoiceItem(invoiceitemId, invoiceid, productid, importid, unitprice, quanity, totalprice, profit, LocalDateTime.MAX, LocalDateTime.MAX);
                    invoiceItems.add(invoiceitem);
                }
               
         }   
         catch(Exception ex){
             ex.printStackTrace();
         }   
        return invoiceItems;
    }
      
       public void addInvoiceItem(InvoiceItem invoiceitem) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();
        String query="INSERT INTO Invoice_Items (InvoiceId, ProductId, ImportId, UnitPrice, Quantity, TotalPrice, Profit, CreatedAt, UpdatedAt) VALUES(?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1, invoiceitem.getInvoiceId());                    
            pre.setInt(2, invoiceitem.getProductId());  
            pre.setInt(3, invoiceitem.getImportId());
            pre.setBigDecimal(4, invoiceitem.getUnitPrice());
            pre.setInt(5, invoiceitem.getQuantity());
            pre.setBigDecimal(6, invoiceitem.getTotalPrice());
            pre.setBigDecimal(7, invoiceitem.getProfit());
            
            Date createAt = Date.from(invoiceitem.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
            pre.setDate(8, (java.sql.Date) createAt);
      
            Date uppdateAt = Date.from(invoiceitem.getUpdatedAt().atZone(ZoneId.systemDefault()).toInstant());
            pre.setDate(9, (java.sql.Date) uppdateAt);
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    } 
     public void editInvoiceItem(InvoiceItem invoiceitem) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();

        String query="UPDATE Invoice_Items SET  InvoiceId=?, ProductId=?, ImportId=?, UnitPrice=?, Quantity=?, TotalPrice=?, Profit=?, CreatedAt=?, UpdatedAt=?WHERE InvoiceItemId =?";
        try{
           PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1, invoiceitem.getInvoiceId());                    
            pre.setInt(2, invoiceitem.getProductId());  
            pre.setInt(3, invoiceitem.getImportId());
            pre.setBigDecimal(4, invoiceitem.getUnitPrice());
            pre.setInt(5, invoiceitem.getQuantity());
            pre.setBigDecimal(6, invoiceitem.getTotalPrice());
            pre.setBigDecimal(7, invoiceitem.getProfit());
            
            Date createAt = Date.from(invoiceitem.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
            pre.setDate(8, (java.sql.Date) createAt);
      
            Date uppdateAt = Date.from(invoiceitem.getUpdatedAt().atZone(ZoneId.systemDefault()).toInstant());
            pre.setDate(9, (java.sql.Date) uppdateAt);
            pre.setInt(10,invoiceitem.getInvoiceItemId() );
            
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }   
    public void deleteInvoiceItem (int invoiceItemId) throws SQLException{
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="DELETE FROM Invoice_Items WHERE InvoiceItemId =?";
         try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1,invoiceItemId);       
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }     
      
}
