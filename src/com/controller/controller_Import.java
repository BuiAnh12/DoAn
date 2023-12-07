
package com.controller;

import com.model.Import;

import com.control.db.ConnectionDB;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;


public class controller_Import {
       
      public static List<Import>getAllImports(int status) throws SQLException{
            List<Import> imports =new ArrayList<>();
            Connection cnn=ConnectionDB.getConnection();
            Statement statement=cnn.createStatement();
            String query="";
            if(status==1){
                query="SELECT * FROM Products, Imports WHERE Products.ProductId = Imports.ProductId ORDER BY ProductName;";
            }
            else if(status==2){
                query="SELECT * FROM Products, Imports WHERE Products.ProductId = Imports.ProductId ORDER BY ImportQuantity;";
            }
            else if(status==3){
                query="SELECT * FROM Products, Imports WHERE Products.ProductId = Imports.ProductId ORDER BY AvailableQuantity;";
            }
            try{
                   ResultSet re=statement.executeQuery(query);
                   imports.clear();
                   while(re.next()){
                         int productid=re.getInt("ProductId");              
                        Date manuDate=re.getDate("ManufacturingDate");
                        Date exDate=re.getDate("ExpiryDate");
                        Date imDate=re.getDate("ImportDate");
                        int importQuanity=re.getInt("ImportQuantity");
                        int avaiableQuanity=re.getInt("AvailableQuantity");
                        BigDecimal unitprice=re.getBigDecimal("UnitPrice");
                        BigDecimal sellprice=re.getBigDecimal("SellPrice");
                        int id=re.getInt("ImportId");
                        String name=re.getString("ProductName");
                        String category=re.getString("Category");

                        Import importss =new Import(id, productid, manuDate, exDate, imDate, importQuanity, avaiableQuanity,unitprice, sellprice,name,category);
                        imports.add(importss);
                   }
                 
            }   
            catch(Exception ex){
                ex.printStackTrace();
            }   
           return imports;
    }

     public void addImport(Import importss) throws SQLException{
            Connection cnn=ConnectionDB.getConnection();
            String query="INSERT INTO Imports (ProductId, ManufacturingDate, ExpiryDate, ImportDate, ImportQuantity, AvailableQuantity, UnitPrice, SellPrice) VALUES(?,?,?,?,?,?,?,?)";
            try{
                PreparedStatement pre=cnn.prepareStatement(query);
                pre.setInt(1, importss.getProductId());       
                pre.setDate(2, (java.sql.Date) importss.getManufacturingDate());
                pre.setDate(3, (java.sql.Date) importss.getExpiryDate());
                pre.setDate(4, (java.sql.Date) importss.getImportDate());             
                pre.setInt(5, importss.getImportQuantity());
                pre.setInt(6, importss.getAvailableQuantity());
                pre.setBigDecimal(7, importss.getUnitPrice());
                pre.setBigDecimal(8, importss.getSellPrice());
                int tmp=pre.executeUpdate();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
    }  
    
      public void editImport(Import importss) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();

        String query="UPDATE Imports  SET ProductId =?, ManufacturingDate =?, ExpiryDate =?,"
                + "ImportDate = ?, ImportQuantity = ?, AvailableQuantity = ?,"
                + "UnitPrice = ?, SellPrice = ? WHERE ImportId = ?";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
             pre.setInt(1, importss.getProductId());       
             pre.setDate(2, (java.sql.Date) importss.getManufacturingDate());
             pre.setDate(3, (java.sql.Date) importss.getExpiryDate());
             pre.setDate(4, (java.sql.Date) importss.getImportDate());             
             pre.setInt(5, importss.getImportQuantity());
             pre.setInt(6, importss.getAvailableQuantity());
             pre.setBigDecimal(7, importss.getUnitPrice());
             pre.setBigDecimal(8, importss.getSellPrice());
             pre.setInt(9, importss.getImportId());
             int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        public void deleteImport(int id) throws SQLException{
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="DELETE FROM Imports WHERE ImportId =?";
         try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1,id);       
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
      
}
