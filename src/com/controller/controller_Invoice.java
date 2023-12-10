
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
     public List<Invoice> getAllInvoices() throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();
        Statement statement = cnn.createStatement();
        String query = "SELECT Invoices.InvoiceId, Customers.CustomerId, Staffs.StaffId, PurchaseDate, CustomerName, Staffs.Name AS StaffName, TotalPrice AS TotalAmount " +
                   "FROM Invoices " +
                   "JOIN Customers ON Customers.CustomerId = Invoices.CustomerId " +
                   "JOIN Staffs ON Staffs.StaffId = Invoices.StaffId " +
                   "JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId";

        try {
            ResultSet re = statement.executeQuery(query);
            while (re.next()) {
                int invoiceId = re.getInt("InvoiceId");
                int customerId = re.getInt("CustomerId");
                int staffId = re.getInt("StaffId");
                String purchaseDate = re.getString("PurchaseDate");
                String customerName = re.getString("CustomerName");
                String staffName = re.getString("StaffName");
                int totalAmount = re.getInt("TotalAmount");

                Invoice invoice = new Invoice(invoiceId, customerId, staffId, purchaseDate, customerName, staffName, totalAmount);
                invoices.add(invoice);
            }

        } catch (SQLException ex) {
            // Xử lý ngoại lệ nếu cần
            ex.printStackTrace();
        }

        return invoices;
    }

     
    public void addInvoice(Invoice invoice) throws SQLException{
        Connection cnn = ConnectionDB.getConnection();
        String query = "INSERT INTO Invoices (CustomerId, StaffId, PurchaseDate, CustomerName, StaffName, TotalAmount) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = cnn.prepareStatement(query);
            pre.setInt(1, invoice.getCustomerId());
            pre.setInt(2, invoice.getStaffId());
            pre.setString(3, invoice.getPurchaseDate());
            pre.setString(4, invoice.getCustomerName());
            pre.setString(5, invoice.getStaffName());
            pre.setInt(6, invoice.getTotalAmount());

            int tmp = pre.executeUpdate();

        } catch (SQLException ex) {
            // Xử lý ngoại lệ nếu cần
            ex.printStackTrace();
        }
    } 
    
      public void editInvoice(Invoice invoice) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();

        String query="UPDATE Invoices SET  CustomerId =?,StaffId =?,PurchaseDate =? ,CustomerName=?,StaffName=?,TotalAmount = ? WHERE InvoiceId =?";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1, invoice.getCustomerId());       
            pre.setInt(2, invoice.getStaffId());
            pre.setString(3,invoice.getPurchaseDate());
            pre.setString(4,invoice.getCustomerName());
            pre.setString(5,invoice.getStaffName());
            pre.setInt(6,invoice.getTotalAmount());
            pre.setInt(7,invoice.getInvoiceId());
            
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
      
     public void deleteInvoice (int invoiceId) throws SQLException{
         Connection cnn=ConnectionDB.getConnection();
         String query="DELETE FROM Invoices WHERE InvoiceId =?";
         try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1,invoiceId);       
        }
        catch (SQLException ex) {
        }
    }  
}
