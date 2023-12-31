package com.controller;

import com.model.Customer;

import com.control.db.ConnectionDB;
import com.model.Detail_Customer;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class controller_Customer {
    //
      public List<Customer>getAllCustomers(int status ,String name) throws SQLException{
         List<Customer> customers =new ArrayList<>();
         Connection cnn=ConnectionDB.getConnection();
         String query="";
         
         if (status == 1) {
            query = "SELECT Customers.CustomerId, Customers.CustomerName, Customers.Email, Customers.Address, SUM(Invoice_Items.TotalPrice) AS TotalAmount " +
                    "FROM Customers " +
                    "LEFT JOIN Invoices ON Customers.CustomerId = Invoices.CustomerId " +
                    "LEFT JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId " +
                    "GROUP BY Customers.CustomerId, Customers.CustomerName, Customers.Email, Customers.Address " +
                    "HAVING Customers.CustomerName LIKE ? ORDER BY CustomerName";
        } else if (status == 2) {
            query = "SELECT Customers.CustomerId, Customers.CustomerName, Customers.Email, Customers.Address, SUM(Invoice_Items.TotalPrice) AS TotalAmount " +
                    "FROM Customers " +
                    "LEFT JOIN Invoices ON Customers.CustomerId = Invoices.CustomerId " +
                    "LEFT JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId " +
                    "GROUP BY Customers.CustomerId, Customers.CustomerName, Customers.Email, Customers.Address " +
                    "HAVING Customers.CustomerName LIKE ? ORDER BY Email";
        } else if (status == 3) {
            query = "SELECT Customers.CustomerId, Customers.CustomerName, Customers.Email, Customers.Address, SUM(Invoice_Items.TotalPrice) AS TotalAmount " +
                    "FROM Customers " +
                    "LEFT JOIN Invoices ON Customers.CustomerId = Invoices.CustomerId " +
                    "LEFT JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId " +
                    "GROUP BY Customers.CustomerId, Customers.CustomerName, Customers.Email, Customers.Address " +
                    "HAVING Customers.CustomerName LIKE ? ORDER BY TotalAmount";
        }
         String searchTerm = "%" + name + "%";
       
         try{
                customers.clear();
                PreparedStatement statement = cnn.prepareStatement(query);
                statement.setString(1, searchTerm);
                ResultSet re = statement.executeQuery();
                
                while(re.next()){
                    int id=re.getInt("CustomerId");  
                    String customername=re.getString("CustomerName");
                    String email=re.getString("Email");
                    String address=re.getString("Address");
                    int totalAmount = re.getInt("TotalAmount");
                    Customer customer =new Customer(id, customername, email, address,totalAmount);
                    customers.add(customer);
                }
              
         }   
         catch (SQLException ex) {
         ex.printStackTrace();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
                return customers;
    }
    //
    public void addCustomer(Customer customer) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();
        String query="INSERT INTO Customers (CustomerName, Email, Address) VALUES(?,?,?)";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setString(1, customer.getCustomerName());       
            pre.setString(2, customer.getEmail());
            pre.setString(3, customer.getAddress());
            int tmp=pre.executeUpdate();
            pre.close();
            cnn.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
    //
     public void editCustomer(Customer customer) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();

        String query="UPDATE Customers SET  CustomerName =?,Email =?,Address =? WHERE CustomerId =?";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setString(1, customer.getCustomerName());       
            pre.setString(2, customer.getEmail());
            pre.setString(3, customer.getAddress());
            pre.setInt(4, customer.getCustomerId());
            int tmp=pre.executeUpdate();
            pre.close();
            cnn.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //
       public void deleteCustomer(int CustomerId) throws SQLException{
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="DELETE FROM Customers WHERE CustomerId = ?";
         try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1,CustomerId);       
            int tmp=pre.executeUpdate();

          statement.close();
          cnn.close();
        }
          
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
       
    public List<Customer> findListCustomer(String name) throws SQLException{
        List<Customer> customers = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();

        String query = "SELECT Customers.CustomerId, Customers.CustomerName, Customers.Email, Customers.Address, SUM(Invoice_Items.TotalPrice) AS TotalAmount " +
                   "FROM Customers " +
                   "JOIN Invoices ON Customers.CustomerId = Invoices.CustomerId " +
                   "JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId WHERE Customers.CustomerName LIKE ?" +
                   "GROUP BY Customers.CustomerId, Customers.CustomerName, Customers.Email, Customers.Address";
        String searchTerm = "%" + name + "%";

        try {
            customers.clear();
            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setString(1, searchTerm);

            ResultSet re = statement.executeQuery();

            while (re.next()) {
                int id=re.getInt("CustomerId");
                    String customerName=re.getString("CustomerName");
                    String email=re.getString("Email");
                    String address=re.getString("Address");
                    int totalAmount = re.getInt("TotalAmount");
                    Customer customer =new Customer(id, customerName, email, address,totalAmount);
                    customers.add(customer);
            }

            statement.close();
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the SQL exception (show a message dialog, log the error, etc.)
        }
        return customers;
    }

    public List<Detail_Customer> getDetail_Customers (int id) throws SQLException{
        List<Detail_Customer>details =new ArrayList<>();
         Connection cnn = ConnectionDB.getConnection();
         String query="SELECT Products.ProductName, Invoice_Items.Quantity, Imports.SellPrice, Invoice_Items.Quantity * Imports.SellPrice AS Total " +
                        "FROM Invoice_Items " +
                        "JOIN Products ON Products.ProductId = Invoice_Items.ProductId " +
                        "JOIN Invoices ON Invoices.InvoiceId = Invoice_Items.InvoiceId " +
                        "JOIN Imports ON Invoice_Items.ImportId = Imports.ImportId " +
                        "WHERE Invoices.CustomerId = ?";

         try {
            details.clear();
            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setString(1,String.valueOf(id));

            ResultSet re = statement.executeQuery();

            while (re.next()) {
                String productName=re.getString("ProductName");
                int quanity=re.getInt("Quantity");
                BigDecimal sellPrice=re.getBigDecimal("SellPrice");
                BigDecimal total=re.getBigDecimal("Total");
                Detail_Customer customer =new Detail_Customer(productName, quanity, sellPrice, total);
                details.add(customer);
            }

            statement.close();
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();   
        }
         
        return details;
    }
    
    
}
