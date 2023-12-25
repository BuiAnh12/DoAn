/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
soidfsoidù
 */
package com.view.form;

import com.controller.controller_Customer;
import com.controller.controller_Import;
import com.controller.controller_Invoice;
import com.controller.controller_InvoiceItem;
import com.controller.controller_Product;
import com.controller.controller_Staff;
import com.model.Customer;
import com.model.Import;
import com.model.Invoice;
import com.model.InvoiceItem;
import com.model.Product;
import com.model.Staff;
import com.view.swing.ScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class Form_1 extends javax.swing.JPanel {

    List<Invoice> invoiceList = new ArrayList<>();
    List<Customer>customers =new ArrayList<>();
    List<Staff> staffList = new ArrayList<>();
    List<Product> productList ;
    List<Import> importList =new ArrayList<>();
    List<InvoiceItem> invoiceItemList = new ArrayList<>();
    controller_InvoiceItem invoiceItem_control = new controller_InvoiceItem();
    controller_Import import_control = new controller_Import();
    controller_Product product_controller = new controller_Product();
    controller_Staff staff_control =new controller_Staff(); 
    controller_Customer customer_control=new controller_Customer();
    controller_Invoice invoice_control=new controller_Invoice();
    private int selectedCustomerId;
    private String selectedCustomerName;
    private int selectedStaffId;
    private String selectedStaffName;
    private int status = 1;
    private List<Import> imports = new ArrayList();
    private List<InvoiceItem> invoiceItemListLz = new ArrayList<>();
    private List<Import> importListLz = new ArrayList();
    public void updateDetail(){
        int selectedRow = table.getSelectedRow();
        controller_InvoiceItem co = new controller_InvoiceItem();
        try {
            invoiceItemList = co.getAllInvoiceItems();
        } catch (SQLException ex) {
            Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        Invoice tmp = invoiceList.get(selectedRow);
        this.txtCustomerName.setText(tmp.getCustomerName());
        this.txtStaffName.setText(tmp.getStaffName());
        this.txtDate.setText(String.valueOf(tmp.getPurchaseDate()));
        this.totalAmountField.setValue(tmp.getTotalAmount());   
        int invoice_id = tmp.getInvoiceId();  
        DefaultTableModel model =(DefaultTableModel) tableDetail.getModel();
        model.setRowCount(0);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (InvoiceItem invoiceItem : invoiceItemList) {
                        if (invoiceItem.getInvoiceId() == invoice_id) {
                            String productName = "";
                            for (Product product : productList) {
                                if (product.getProductId() == invoiceItem.getProductId()) {
                                    productName = product.getProductName();
                                    break;
                                }
                            }
                            tableDetail.addRow(new Object[]{
                                productName,
                                invoiceItem.getQuantity(),
                                String.valueOf(decimalFormat.format(invoiceItem.getTotalPrice())+" VNĐ"),
                                
                            });
                        }
                    }
         this.txtCustomerName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14)); 
         this.txtStaffName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14)); 
         this.txtDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14)); 
         this.totalAmountField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
    }
    
    public void refreshimportList(){
        try {
            importList.clear();
            importList=controller_Import.getAllImports(status,"");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void refreshimportListLz(){
        try {
            importListLz.clear();
            importList=controller_Import.getAllImports(status,"");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
      
    
    
    public void refreshtable(){
        try {
            String searchTxt = this.txtSearch.getText();
            invoiceList=invoice_control.getAllInvoices(status,searchTxt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
          DefaultTableModel model =(DefaultTableModel) table.getModel();
        model.setRowCount(0);
       for (Invoice invoice : invoiceList) {
            table.addRow(new Object[]{invoice.getCustomerName(), invoice.getStaffName(), invoice.getPurchaseDate(), invoice.getTotalAmount()});
        }
        
    }
    public  java.sql.Date convertStringtoDate(String date){
         // Chuỗi đại diện cho ngày
        String dateString = "2023-12-07";
        java.sql.Date sqlDate = new java.sql.Date(0);
        try {
            // Định dạng cho chuỗi đầu vào
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Chuyển đổi chuỗi thành java.util.Date
            Date utilDate = dateFormat.parse(date);

            // Chuyển đổi java.util.Date thành java.sql.Date
             sqlDate = new java.sql.Date(utilDate.getTime());

            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }
    
    public Form_1() throws SQLException {
        initComponents();  
        
        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy giá trị được chọn khi có sự kiện thay đổi
                String selectedValue = sortComboBox.getSelectedItem().toString(); 
                
                if (selectedValue.equals("Sort By Customer")){
                   status=2;
                   refreshtable();
                }
                else if(selectedValue.equals("Sort By Staff")){
                    status=3;
                    refreshtable();
                }
                else if(selectedValue.equals("Sort By Total")){
                    status=1;
                    refreshtable();
                }
               
            }
        });
        
        
        try {
            staffList = staff_control.getAllStaff(1, "");
        } catch (SQLException ex) {
            Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
                    customers=customer_control.getAllCustomers(1,"");
            } catch (SQLException ex) {
                    Logger.getLogger(Form_2.class.getName()).log(Level.SEVERE, null, ex);
            }
        controller_Invoice invoices =new  controller_Invoice();
        
        try {
            productList = product_controller.getAllproduct(1,"");
        } catch (SQLException ex) {
            Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
               invoiceList = invoices.getAllInvoices(status,"");
            } catch (SQLException ex) {
            }
        
        importList = controller_Import.getAllImports(status,"");
        invoiceItemList = invoiceItem_control.getAllInvoiceItems();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        for (Invoice invoice : invoiceList) {
            table.addRow(new Object[]{invoice.getCustomerName(), invoice.getStaffName(), invoice.getPurchaseDate(), invoice.getTotalAmount()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelLeft = new javax.swing.JPanel();
        PanelButton = new javax.swing.JPanel();
        PanelSearch = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        PanelFilter = new javax.swing.JPanel();
        sortComboBox = new javax.swing.JComboBox<>();
        PanelInsert = new javax.swing.JPanel();
        insertBtn = new javax.swing.JButton();
        PanelTable = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        table = new com.view.swing.Table();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelRight = new javax.swing.JPanel();
        PanelHeader = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PanelDetail = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtCustomerName = new javax.swing.JTextField();
        txtStaffName = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        totalAmountField = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        detailSpTable = new javax.swing.JScrollPane();
        tableDetail = new com.view.swing.Table();
        PanelDUBtn = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        returnBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(22, 23, 23));
        setForeground(new java.awt.Color(22, 23, 23));
        setPreferredSize(new java.awt.Dimension(1080, 720));
        setLayout(new java.awt.BorderLayout(10, 10));

        jPanel1.setBackground(new java.awt.Color(22, 23, 23));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        PanelLeft.setBackground(new java.awt.Color(22, 23, 23));

        PanelButton.setBackground(new java.awt.Color(22, 23, 23));
        PanelButton.setLayout(new javax.swing.BoxLayout(PanelButton, javax.swing.BoxLayout.LINE_AXIS));

        PanelSearch.setBackground(new java.awt.Color(22, 23, 23));

        txtSearch.setBackground(new java.awt.Color(36, 36, 36));
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(22, 23, 23));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/view/icon/search.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelSearchLayout = new javax.swing.GroupLayout(PanelSearch);
        PanelSearch.setLayout(PanelSearchLayout);
        PanelSearchLayout.setHorizontalGroup(
            PanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelSearchLayout.setVerticalGroup(
            PanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        PanelButton.add(PanelSearch);

        PanelFilter.setBackground(new java.awt.Color(22, 23, 23));

        sortComboBox.setBackground(new java.awt.Color(36, 36, 36));
        sortComboBox.setFont(new java.awt.Font("Sitka Text", 1, 14)); // NOI18N
        sortComboBox.setForeground(new java.awt.Color(255, 255, 255));
        sortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort By Customer", "Sort By Staff", "Sort By Total" }));

        javax.swing.GroupLayout PanelFilterLayout = new javax.swing.GroupLayout(PanelFilter);
        PanelFilter.setLayout(PanelFilterLayout);
        PanelFilterLayout.setHorizontalGroup(
            PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFilterLayout.createSequentialGroup()
                .addComponent(sortComboBox, 0, 211, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelFilterLayout.setVerticalGroup(
            PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sortComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        PanelButton.add(PanelFilter);

        PanelInsert.setBackground(new java.awt.Color(22, 23, 23));

        insertBtn.setBackground(new java.awt.Color(36, 36, 36));
        insertBtn.setForeground(new java.awt.Color(255, 255, 255));
        insertBtn.setText("Insert");
        insertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInsertLayout = new javax.swing.GroupLayout(PanelInsert);
        PanelInsert.setLayout(PanelInsertLayout);
        PanelInsertLayout.setHorizontalGroup(
            PanelInsertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInsertLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(insertBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelInsertLayout.setVerticalGroup(
            PanelInsertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(insertBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        PanelButton.add(PanelInsert);

        PanelTable.setForeground(new java.awt.Color(60, 63, 65));

        spTable.setBorder(null);

        table.setForeground(new java.awt.Color(22, 23, 23));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer name", "Staff name", "Date", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout PanelTableLayout = new javax.swing.GroupLayout(PanelTable);
        PanelTable.setLayout(PanelTableLayout);
        PanelTableLayout.setHorizontalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTable)
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("ORDER");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelLeftLayout = new javax.swing.GroupLayout(PanelLeft);
        PanelLeft.setLayout(PanelLeftLayout);
        PanelLeftLayout.setHorizontalGroup(
            PanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
            .addGroup(PanelLeftLayout.createSequentialGroup()
                .addGroup(PanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelLeftLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        PanelLeftLayout.setVerticalGroup(
            PanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel1.add(PanelLeft);

        PanelRight.setBackground(new java.awt.Color(22, 23, 23));
        PanelRight.setLayout(new java.awt.BorderLayout());

        PanelHeader.setBackground(new java.awt.Color(22, 23, 23));

        jLabel3.setBackground(new java.awt.Color(22, 23, 23));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DETAIL");

        javax.swing.GroupLayout PanelHeaderLayout = new javax.swing.GroupLayout(PanelHeader);
        PanelHeader.setLayout(PanelHeaderLayout);
        PanelHeaderLayout.setHorizontalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelHeaderLayout.setVerticalGroup(
            PanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelRight.add(PanelHeader, java.awt.BorderLayout.NORTH);

        PanelDetail.setBackground(new java.awt.Color(36, 36, 36));

        jPanel7.setBackground(new java.awt.Color(36, 36, 36));

        jPanel6.setBackground(new java.awt.Color(36, 36, 36));
        jPanel6.setForeground(new java.awt.Color(36, 36, 36));

        txtCustomerName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtCustomerName.setForeground(new java.awt.Color(102, 102, 102));
        txtCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerNameActionPerformed(evt);
            }
        });

        txtStaffName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtStaffName.setForeground(new java.awt.Color(102, 102, 102));

        txtDate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        txtDate.setForeground(new java.awt.Color(102, 102, 102));

        jPanel4.setBackground(new java.awt.Color(36, 36, 36));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Customer name: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Staff name:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Date:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Amount");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        totalAmountField.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        totalAmountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalAmountFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCustomerName)
                    .addComponent(txtStaffName, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(txtDate)
                    .addComponent(totalAmountField))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtStaffName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(totalAmountField)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        detailSpTable.setBorder(null);

        tableDetail.setForeground(new java.awt.Color(22, 23, 23));
        tableDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Quantity", "Total Pricce"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDetail.setFont(new java.awt.Font("Sitka Small", 1, 13)); // NOI18N
        tableDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetailMouseClicked(evt);
            }
        });
        detailSpTable.setViewportView(tableDetail);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(detailSpTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(detailSpTable, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelDetailLayout = new javax.swing.GroupLayout(PanelDetail);
        PanelDetail.setLayout(PanelDetailLayout);
        PanelDetailLayout.setHorizontalGroup(
            PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelDetailLayout.setVerticalGroup(
            PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelRight.add(PanelDetail, java.awt.BorderLayout.CENTER);

        PanelDUBtn.setBackground(new java.awt.Color(22, 23, 23));
        PanelDUBtn.setLayout(new javax.swing.BoxLayout(PanelDUBtn, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setBackground(new java.awt.Color(22, 23, 23));

        returnBtn.setBackground(new java.awt.Color(36, 36, 36));
        returnBtn.setForeground(new java.awt.Color(255, 255, 255));
        returnBtn.setText("RETURN");
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 182, Short.MAX_VALUE)
                .addComponent(returnBtn))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(returnBtn))
        );

        PanelDUBtn.add(jPanel3);

        deleteBtn.setBackground(new java.awt.Color(36, 36, 36));
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        PanelDUBtn.add(deleteBtn);

        updateBtn.setBackground(new java.awt.Color(36, 36, 36));
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("UPDATE");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        PanelDUBtn.add(updateBtn);

        PanelRight.add(PanelDUBtn, java.awt.BorderLayout.SOUTH);

        jPanel1.add(PanelRight);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy Invoice từ danh sách
            Invoice selectedInvoice = invoiceList.get(selectedRow);
            // Tạo các trường nhập liệu
            JComboBox<Customer> customerDropdown = new JComboBox<>();
            for (Customer customer : customers) {
                customerDropdown.addItem(customer);
            }
            customerDropdown.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    if (value instanceof Customer customer) {
                        // Hiển thị tên khách hàng
                        value = customer.getCustomerName();
                    }
                    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                }
            });
            int invoiceid = selectedInvoice.getInvoiceId();
            customerDropdown.setSelectedItem(selectedInvoice.getCustomerName());
            customerDropdown.addActionListener((ActionEvent e) -> {
                Customer selectedCustomer = (Customer) customerDropdown.getSelectedItem();
                if (selectedCustomer != null) {
                    // Lấy ID và tên của khách hàng
                    selectedCustomerId = selectedCustomer.getCustomerId();
                    selectedCustomerName = selectedCustomer.getCustomerName();
                }
            });

            JComboBox<Staff> staffDropdown = new JComboBox<>();
            for (Staff staff : staffList) {
                staffDropdown.addItem(staff);
            }
            staffDropdown.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    if (value instanceof Staff staff) {
                        // Hiển thị tên nhân viên
                        value = staff.getName();
                    }
                    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                }
            });
            staffDropdown.setSelectedItem(selectedInvoice.getStaffName());
            staffDropdown.addActionListener((ActionEvent e) -> {
                Staff selectedStaff = (Staff) staffDropdown.getSelectedItem();
                if (selectedStaff != null) {
                    // Lấy ID và tên của nhân viên
                    selectedStaffId = selectedStaff.getStaffId();
                    selectedStaffName = selectedStaff.getName();
                }
            });
            JTextField dateField = new JTextField(String.valueOf(selectedInvoice.getPurchaseDate()));
            JFormattedTextField totalAmountField = new JFormattedTextField(NumberFormat.getIntegerInstance());
            totalAmountField.setValue(selectedInvoice.getTotalAmount());
            JComboBox<String> productNameField = new JComboBox<>();
            DefaultComboBoxModel<String> comboBoxModelName = new DefaultComboBoxModel<>();
            for (Product product:productList){
                comboBoxModelName.addElement(product.getProductName());
            };
            productNameField.setModel(comboBoxModelName);
            JFormattedTextField quantityField = new JFormattedTextField(NumberFormat.getIntegerInstance());
            //JFormattedTextField unitPriceField = new JFormattedTextField(NumberFormat.getIntegerInstance());
            JButton addProductButton = new JButton("Add Product");
            DefaultTableModel detailTableModel = new DefaultTableModel();
            detailTableModel.addColumn("Product Name");
            detailTableModel.addColumn("Quantity");
            detailTableModel.addColumn("Unit Price");
            detailTableModel.addColumn("Total Price");
            detailTableModel.addColumn("Mã lô hàng");
            detailTableModel.addColumn("Profit");
            detailTableModel.addColumn("productId");
            detailTableModel.addColumn("invoiceItemId");
            JTable detailTable = new JTable(detailTableModel);
            detailTable.getColumnModel().getColumn(2).setMinWidth(0);
            detailTable.getColumnModel().getColumn(2).setMaxWidth(0);
            detailTable.getColumnModel().getColumn(2).setWidth(0);
//            detailTable.getColumnModel().getColumn(4).setMinWidth(0);
//            detailTable.getColumnModel().getColumn(4).setMaxWidth(0);
//            detailTable.getColumnModel().getColumn(4).setWidth(0);
            detailTable.getColumnModel().getColumn(5).setMinWidth(0);
            detailTable.getColumnModel().getColumn(5).setMaxWidth(0);
            detailTable.getColumnModel().getColumn(5).setWidth(0);
            detailTable.getColumnModel().getColumn(6).setMinWidth(0);
            detailTable.getColumnModel().getColumn(6).setMaxWidth(0);
            detailTable.getColumnModel().getColumn(6).setWidth(0);
            detailTable.getColumnModel().getColumn(7).setMinWidth(0);
            detailTable.getColumnModel().getColumn(7).setMaxWidth(0);
            detailTable.getColumnModel().getColumn(7).setWidth(0);
            JScrollPane detailScrollPane = new JScrollPane(detailTable);
            JButton editProductButton = new JButton("Edit Product");
            JButton deleteProductButton = new JButton("Delete Product");
            // Tạo một panel chứa các trường nhập liệu và bảng chi tiết sản phẩm
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(new JLabel("Customer Name:"), gbc);
            gbc.gridy++;
            panel.add(new JLabel("Staff Name:"), gbc);
            gbc.gridy++;
            panel.add(new JLabel("Date:"), gbc);
            //gbc.gridy++;
            //panel.add(new JLabel("Total Amount:"), gbc);
            gbc.gridy++;
            panel.add(new JLabel("Product Details:"), gbc);     
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(customerDropdown, gbc);
            gbc.gridy++;
            panel.add(staffDropdown, gbc);
            gbc.gridy++;
            panel.add(dateField, gbc);
            //gbc.gridy++;
            //panel.add(totalAmountField, gbc);
            gbc.gridy++;
            gbc.gridwidth = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            panel.add(detailScrollPane, gbc);
            gbc.gridx = 1;
            gbc.gridy++;
            panel.add(new JLabel("Product Name:"), gbc);
            gbc.gridy++;
            panel.add(productNameField, gbc);
            gbc.gridy++;
            panel.add(new JLabel("Quantity:"), gbc);
            gbc.gridy++;
            panel.add(quantityField, gbc);
            gbc.gridy++;
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 0.0;
            gbc.insets = new Insets(0, 100, 0, 100);
            panel.add(addProductButton, gbc);
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(0, 0, 0, 0); 
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            panel.add(detailScrollPane, gbc);
            gbc.gridy++;
            panel.add(editProductButton, gbc);
            gbc.gridy++;
            panel.add(deleteProductButton, gbc);
            
            for (InvoiceItem invoiceItem : invoiceItemList) {
                        if (invoiceItem.getInvoiceId() == invoiceid) {
                            String productName = "";
                            for (Product product : productList) {
                                if (product.getProductId() == invoiceItem.getProductId()) {
                                    productName = product.getProductName();
                                    break;
                                }
                            }
                            detailTableModel.addRow(new Object[]{
                                productName,
                                invoiceItem.getQuantity(),
                                invoiceItem.getUnitPrice(),
                                invoiceItem.getTotalPrice(),
                                invoiceItem.getImportId(),
                                invoiceItem.getProfit(),
                                invoiceItem.getProductId(),
                                invoiceItem.getInvoiceItemId(),
                            });
                        }
                    }
            editProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = detailTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String ProductName = (String) detailTableModel.getValueAt(selectedRow, 0);
                        int quantity = (int) detailTableModel.getValueAt(selectedRow, 1);
                        BigDecimal unitPrice = (BigDecimal) detailTableModel.getValueAt(selectedRow, 2);
                        BigDecimal totalPrice = (BigDecimal) detailTableModel.getValueAt(selectedRow, 3);
                        int importId = (int) detailTableModel.getValueAt(selectedRow, 4);
                        BigDecimal profit = (BigDecimal) detailTableModel.getValueAt(selectedRow, 5);
                        int productId = (int) detailTableModel.getValueAt(selectedRow, 6);

                        // Hiển thị hộp thoại chỉnh sửa
//                        JComboBox<String> productNameComboBox = new JComboBox<>();
//                        productNameComboBox.setSelectedItem(currentProductName);
//                        for (Product product : productList) {
//                            productNameComboBox.addItem(product.getProductName());
//                        }
                        JFormattedTextField quantityField = new JFormattedTextField(NumberFormat.getIntegerInstance());
                        quantityField.setValue(quantity);

                        JPanel editPanel = new JPanel(new GridLayout(0, 2));
                        //editPanel.add(new JLabel("Tên sản phẩm:"));
                        //editPanel.add(productNameComboBox);
                        editPanel.add(new JLabel("Số lượng:"));
                        editPanel.add(quantityField);

                        int result = JOptionPane.showConfirmDialog(null, editPanel, "Chỉnh sửa số lượng sản phẩm",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if (result == JOptionPane.OK_OPTION) {
                            // Lấy thông tin đã chỉnh sửa từ các trường nhập liệu
                            //String newProductName = (String) productList.get(productNameComboBox.getSelectedIndex()).getProductName();
//                            System.out.println(newProductName);
//                            int newproductId = productList.get(productNameComboBox.getSelectedIndex()).getProductId();
                            int newQuantity = ((Number) quantityField.getValue()).intValue();
                            BigDecimal newtotalPrice = BigDecimal.ZERO;
                            BigDecimal newprofit = BigDecimal.ZERO;
                            
                            for (Import importItem : importList) {
                                if (importItem.getProductId() == productId && importItem.getImportId() == importId) {
                                    controller_Import dm = new controller_Import();
                                    if ((newQuantity - quantity) > 0){
                                        if ((newQuantity - quantity) <= importItem.getAvailableQuantity()){
                                            BigDecimal newquantityBigDecimal = new BigDecimal(newQuantity);
                                            newtotalPrice = importItem.getSellPrice().multiply(newquantityBigDecimal);
                                            newprofit = newtotalPrice.subtract(unitPrice.multiply(newquantityBigDecimal));
                                            detailTableModel.setValueAt(newQuantity, selectedRow, 1);
                                            detailTableModel.setValueAt(newtotalPrice,selectedRow,3);
                                            detailTableModel.setValueAt(newprofit,selectedRow,5);
                                            importItem.setAvailableQuantity(importItem.getAvailableQuantity() - (newQuantity - quantity));
                                            try {
                                                dm.editImport(importItem);
                                            } catch (SQLException ex) {
                                                Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            break;
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Hiện tại trong kho chỉ còn " + importItem.getAvailableQuantity() + " sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                        }  
                                    } else if((newQuantity - quantity) < 0){
                                        BigDecimal newquantityBigDecimal = new BigDecimal(newQuantity);
                                        newtotalPrice = importItem.getSellPrice().multiply(newquantityBigDecimal);
                                        newprofit = newtotalPrice.subtract(unitPrice.multiply(newquantityBigDecimal));
                                        detailTableModel.setValueAt(newQuantity, selectedRow, 1);
                                        detailTableModel.setValueAt(newtotalPrice,selectedRow,3);
                                        detailTableModel.setValueAt(newprofit,selectedRow,5);
                                        importItem.setAvailableQuantity(importItem.getAvailableQuantity() + (quantity - newQuantity));
                                        try {
                                            dm.editImport(importItem);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    refreshtable();
                                } 
                            }
                            
                            
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để chỉnh sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = detailTable.getSelectedRow();
                if (selectedRow != -1) {
                    String productName = (String) detailTableModel.getValueAt(selectedRow, 0);
                    int quantity = (int) detailTableModel.getValueAt(selectedRow, 1);
                    int invoiceItemiddd =(int) detailTableModel.getValueAt(selectedRow, 7);
                    BigDecimal unitPrice = (BigDecimal) detailTableModel.getValueAt(selectedRow, 2);
                    int importId = (int) detailTableModel.getValueAt(selectedRow, 4);
                    int confirmDelete = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản phẩm '" + productName + "' không?",
                            "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                    if (confirmDelete == JOptionPane.YES_OPTION) {
                        detailTableModel.removeRow(selectedRow);
                        controller_InvoiceItem c = new controller_InvoiceItem();
                        controller_Import b = new controller_Import();
                        for (Import importx: importList){
                            if ( importId == importx.getImportId()){
                                importx.setAvailableQuantity(importx.getAvailableQuantity()+quantity);
                                try {
                                    b.editImport(importx);
                                } catch (SQLException ex) {
                                    Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        try {
                            c.deleteInvoiceItem(invoiceItemiddd);
                            
                            System.out.println("run2");
                        } catch (SQLException ex) {
                            Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
//                    / CAN THEM O DAY NE 
//                       DefaultTableModel detail_model =(DefaultTableModel) detailTable.getModel();
//                       detail_model.setRowCount(0);
//                       refreshtable();
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
                    
            
            addProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String productName = (String) productNameField.getSelectedItem();
                    Number quantityNumber = (Number) quantityField.getValue();
                    BigDecimal totalPrice = BigDecimal.ZERO;
                    int importId = 0;
                    int productIdd = 0;
                    int quantitys = 0;
                    BigDecimal unitPrice = BigDecimal.ZERO;
                    BigDecimal profit = BigDecimal.ZERO;
                    int productId = productList.get(productNameField.getSelectedIndex()).getProductId();
                    if ( quantityNumber == null) {
                        JOptionPane.showMessageDialog(null, "Không được để trống!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int quantity = quantityNumber.intValue();
                    
                    
                    controller_Invoice find = new controller_Invoice();             
                    try{
                        imports = find.findAvailableId(productId, quantity);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    int need = quantity;
                    int conhang = 0;
                    for (Import importv : importList){
                        if(importv.getProductId() == productId){
                            conhang += importv.getAvailableQuantity();
                        }
                    }
                    int conhangtmp = 0;
                    for (Import abc : importListLz){
                        if (abc.getProductId() == productId){
                            conhangtmp += abc.getAvailableQuantity();
                        }
                    }
                    for (Import x : importListLz){
                        if (x.getImportId()==productId){
                            if(need<=conhangtmp){
                                for (Import importItem : imports) {
                                if (need>=importItem.getAvailableQuantity()){
                                    quantitys = importItem.getAvailableQuantity();
                                    need -= importItem.getAvailableQuantity();
                                } else {
                                    quantitys = need;
                                    need = 0;
                                }
                                productIdd = importItem.getProductId();
                                BigDecimal quantityBigDecimal = new BigDecimal(quantitys);
                                totalPrice = importItem.getSellPrice().multiply(quantityBigDecimal);
                                System.out.println(importItem.getSellPrice());
                                importId = importItem.getImportId();
                                unitPrice = importItem.getUnitPrice();
                                profit = totalPrice.subtract(unitPrice.multiply(quantityBigDecimal));

                                InvoiceItem invoiceItems = new InvoiceItem(productIdd,importId,unitPrice,quantitys,totalPrice,profit);
                                importItem.setAvailableQuantity(importItem.getAvailableQuantity()-quantitys);
                                importListLz.add(importItem);
                                invoiceItemListLz.add(invoiceItems);  
                                //refreshimportList();
                            }                      
                        } else {
                                JOptionPane.showMessageDialog(null, "Trong kho chỉ còn " + conhangtmp +" sản phẩm", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            break;
                        }
                    }
                    if(need<=conhang){
                        for (Import importItem : imports) {
                        if (need>=importItem.getAvailableQuantity()){
                            quantitys = importItem.getAvailableQuantity();
                            need -= importItem.getAvailableQuantity();
                        } else {
                            quantitys = need;
                            need = 0;
                        }
                        productIdd = importItem.getProductId();
                        BigDecimal quantityBigDecimal = new BigDecimal(quantitys);
                        totalPrice = importItem.getSellPrice().multiply(quantityBigDecimal);
                        System.out.println(importItem.getSellPrice());
                        importId = importItem.getImportId();
                        unitPrice = importItem.getUnitPrice();
                        profit = totalPrice.subtract(unitPrice.multiply(quantityBigDecimal));

                        InvoiceItem invoiceItems = new InvoiceItem(productIdd,importId,unitPrice,quantitys,totalPrice,profit);
                        importItem.setAvailableQuantity(importItem.getAvailableQuantity()-quantitys);
                        System.out.println("moe m"+importItem.getAvailableQuantity());
                        importListLz.add(importItem);
                        invoiceItemListLz.add(invoiceItems);  
                        //refreshimportList();
                        }                      
                    } else {
                            JOptionPane.showMessageDialog(null, "Trong kho chỉ còn " + conhang +" sản phẩm", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    

                    
                    // Thêm thông tin sản phẩm vào bảng chi tiết sản phẩm
                    detailTableModel.addRow(new Object[]{productName, quantity, unitPrice,totalPrice,importId, profit, productIdd,0});
                    // Xóa dữ liệu từ các trường nhập liệu sau khi thêm
                    refreshimportList();
                    productNameField.setSelectedIndex(-1);
                    quantityField.setValue(null);
                    
                    //unitPriceField.setValue(null);
                }
            });
            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Invoice Information",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                
                // Lấy thông tin từ các trường nhập liệu
                java.sql.Date updatedDate = convertStringtoDate(dateField.getText());
                Number updatedTotalAmount = (Number) totalAmountField.getValue();

                // Kiểm tra ràng buộc không được để trống
                if (updatedDate == null || updatedTotalAmount == null) {
                    JOptionPane.showMessageDialog(null, "Không được để trống!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Cập nhật thông tin của Invoice
                selectedInvoice.setPurchaseDate(updatedDate);
                selectedInvoice.setTotalAmount(updatedTotalAmount.intValue());
                selectedInvoice.setCustomerName(selectedCustomerName);
                selectedInvoice.setStaffName(selectedStaffName);

                // Cập nhật bảng
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setValueAt(selectedCustomerName, selectedRow, 0);
                model.setValueAt(selectedStaffName, selectedRow, 1);
                model.setValueAt(updatedDate, selectedRow, 2);
                model.setValueAt(updatedTotalAmount.intValue(), selectedRow, 3);
                // Lưu các thay đổi vào cơ sở dữ liệu hoặc thực hiện các hành động khác cần thiết
                controller_Invoice updateController = new controller_Invoice();
                try {
                    updateController.editInvoice(selectedInvoice);
                } catch (SQLException ex) {
                    Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
                }
                controller_InvoiceItem newInvoiceItem = new controller_InvoiceItem();
                for (int row = 0; row < detailTableModel.getRowCount(); row++) {
                    String productName = (String) detailTableModel.getValueAt(row, 0);
                    int quantity = (int) detailTableModel.getValueAt(row, 1);
                    BigDecimal unitPrice = (BigDecimal) detailTableModel.getValueAt(row, 2);
                    BigDecimal totalPrice = (BigDecimal) detailTableModel.getValueAt(row, 3);
                    int importId = (int) detailTableModel.getValueAt(row, 4);                   
                    BigDecimal profit = (BigDecimal) detailTableModel.getValueAt(row, 5);
                    int productIdd = (int) detailTableModel.getValueAt(row, 6);
                    int invoiceItemidd = (int) detailTableModel.getValueAt(row, 7);
                    System.out.println(invoiceItemidd);
                    System.out.println(productName);
                    
                    
                    try {
                        if (invoiceItemidd != 0) {
                            InvoiceItem invoiceItem = new InvoiceItem(invoiceid, productIdd, importId, unitPrice, quantity, totalPrice, profit,invoiceItemidd);                           
                            newInvoiceItem.editInvoiceItem(invoiceItem);
                            System.out.println(invoiceItemidd);
                            System.out.println(quantity);
                            System.out.println("run1");
                            //controller_Invoice find = new controller_Invoice();
//                            try{
//                                imports = find.findAvailableId(productIdd, quantity);
//                            } catch (Exception ex) {
//                                    ex.printStackTrace();
//                            } 
//                            if (invoiceItemList.get(invoiceItemidd).getQuantity() != quantity){
//                                
//                            }
                        }
                        
//                        else {
//                            InvoiceItem invoiceItemp = new InvoiceItem(invoiceid, productIdd, importId, unitPrice, quantity, totalPrice, profit);
//                            newInvoiceItem.addInvoiceItem(invoiceItemp);
//                            System.out.println("run3");
//                        }
                        
                    } catch (SQLException e) {
                        System.out.println("fail1");
                        e.printStackTrace();
                    }
                }
                for (InvoiceItem invoiceItem : invoiceItemListLz) {
                            //String productName = (String) detailTableModel.getValueAt(row, 0);
                            int quantity2 = invoiceItem.getQuantity();
                            BigDecimal unitPrice2 = invoiceItem.getUnitPrice();
                            BigDecimal totalPrice2 = invoiceItem.getTotalPrice();
                            int importId2 = invoiceItem.getImportId();
                            BigDecimal profit2 = invoiceItem.getProfit();
                            int productIdd2 = invoiceItem.getProductId();
                            InvoiceItem invoiceItemmm = new InvoiceItem(invoiceid,productIdd2,importId2,unitPrice2,quantity2, totalPrice2,profit2);
                            controller_InvoiceItem newInvoiceItemm = new controller_InvoiceItem();
                            try {
                                System.out.println("start try invoiceItem");
                                newInvoiceItem.addInvoiceItem(invoiceItemmm);
                            } catch (SQLException ex) {
                                Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            for (Import importt : importList){                     
                                if (importt.getImportId() == importId2){
                                    controller_Import i = new controller_Import();
                                    importt.setAvailableQuantity(importt.getAvailableQuantity() - quantity2);                              
                                        try {
                                            i.editImport(importt);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                }
                            }
                    }
            }
            invoiceItemListLz.clear();
            refreshtable();
            refreshimportListLz();
            // xoa bang
            DefaultTableModel detail_model =(DefaultTableModel) detailTable.getModel();
            detail_model.setRowCount(0);
            this.updateDetail();
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int selectedRow = table.getSelectedRow();
        controller_InvoiceItem co = new controller_InvoiceItem();
        try {
            invoiceItemList = co.getAllInvoiceItems();
        } catch (SQLException ex) {
            Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        Invoice tmp = invoiceList.get(selectedRow);
        this.txtCustomerName.setText(tmp.getCustomerName());
        this.txtStaffName.setText(tmp.getStaffName());
        this.txtDate.setText(String.valueOf(tmp.getPurchaseDate()));
        this.totalAmountField.setValue(tmp.getTotalAmount());   
        int invoice_id = tmp.getInvoiceId();  
        DefaultTableModel model =(DefaultTableModel) tableDetail.getModel();
        model.setRowCount(0);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (InvoiceItem invoiceItem : invoiceItemList) {
                        if (invoiceItem.getInvoiceId() == invoice_id) {
                            String productName = "";
                            for (Product product : productList) {
                                if (product.getProductId() == invoiceItem.getProductId()) {
                                    productName = product.getProductName();
                                    break;
                                }
                            }
                            tableDetail.addRow(new Object[]{
                                productName,
                                invoiceItem.getQuantity(),
                                String.valueOf(decimalFormat.format(invoiceItem.getTotalPrice())+" VNĐ"),
                                
                            });
                        }
                    }
         this.txtCustomerName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14)); 
         this.txtStaffName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14)); 
         this.txtDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14)); 
         this.totalAmountField.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14)); 
    }//GEN-LAST:event_tableMouseClicked

    private void tableDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailMouseClicked
    
    }//GEN-LAST:event_tableDetailMouseClicked

    @SuppressWarnings("empty-statement")
    private void insertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertBtnActionPerformed

        JComboBox<Customer> customerDropdown = new JComboBox<>();
        for (Customer customer : customers) {
            customerDropdown.addItem(customer);
        }
        customerDropdown.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Customer customer) {
                    // Hiển thị tên khách hàng
                    value = customer.getCustomerName();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        customerDropdown.addActionListener((ActionEvent e) -> {
            Customer selectedCustomer = (Customer) customerDropdown.getSelectedItem();
            if (selectedCustomer != null) {
                // Lấy ID và tên của khách hàng
                selectedCustomerId = selectedCustomer.getCustomerId();
                selectedCustomerName = selectedCustomer.getCustomerName();
            }
        });
        JComboBox<Staff> staffDropdown = new JComboBox<>();
        for (Staff staff : staffList) {
            staffDropdown.addItem(staff);
        }
        staffDropdown.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Staff staff) {
                    // Hiển thị tên nhân viên
                    value = staff.getName();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
        staffDropdown.addActionListener((var e) -> {
            Staff selectedStaff = (Staff) staffDropdown.getSelectedItem();
            if (selectedStaff != null) {
                // Lấy ID và tên của nhân viên
                selectedStaffId = selectedStaff.getStaffId();
                selectedStaffName = selectedStaff.getName();
            }
        });

        JTextField dateField = new JTextField();
        //JFormattedTextField totalAmountField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        JComboBox<String> productNameField = new JComboBox<>();
        DefaultComboBoxModel<String> comboBoxModelName = new DefaultComboBoxModel<>();
        for (Product product:productList){
            comboBoxModelName.addElement(product.getProductName());
        };
        productNameField.setModel(comboBoxModelName);
        JFormattedTextField quantityField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        //JFormattedTextField unitPriceField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        JButton addProductButton = new JButton("Add Product");
        DefaultTableModel detailTableModel = new DefaultTableModel();
        
        detailTableModel.addColumn("Product Name");
        detailTableModel.addColumn("Quantity");
        detailTableModel.addColumn("Unit Price");
        detailTableModel.addColumn("Total Price");
        detailTableModel.addColumn("ImportId");
        detailTableModel.addColumn("Profit");
        detailTableModel.addColumn("productId");
        JTable detailTable = new JTable(detailTableModel);

        // Ẩn các cột không muốn hiển thị
        detailTable.getColumnModel().getColumn(2).setMinWidth(0);
        detailTable.getColumnModel().getColumn(2).setMaxWidth(0);
        detailTable.getColumnModel().getColumn(2).setWidth(0);
        detailTable.getColumnModel().getColumn(4).setMinWidth(0);
        detailTable.getColumnModel().getColumn(4).setMaxWidth(0);
        detailTable.getColumnModel().getColumn(4).setWidth(0);
        detailTable.getColumnModel().getColumn(5).setMinWidth(0);
        detailTable.getColumnModel().getColumn(5).setMaxWidth(0);
        detailTable.getColumnModel().getColumn(5).setWidth(0);
        detailTable.getColumnModel().getColumn(6).setMinWidth(0);
        detailTable.getColumnModel().getColumn(6).setMaxWidth(0);
        detailTable.getColumnModel().getColumn(6).setWidth(0);

        // Thêm bảng vào JScrollPane hoặc panel của bạn
        JScrollPane detailScrollPane = new JScrollPane(detailTable);
        

        // Tạo một panel chứa các trường nhập liệu và bảng chi tiết sản phẩm
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Customer Name:"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Staff Name:"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Date:"), gbc);
        //gbc.gridy++;
        //panel.add(new JLabel("Total Amount:"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("Product Details:"), gbc);     
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(customerDropdown, gbc);
        gbc.gridy++;
        panel.add(staffDropdown, gbc);
        gbc.gridy++;
        panel.add(dateField, gbc);
        //gbc.gridy++;
        //panel.add(totalAmountField, gbc);
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(detailScrollPane, gbc);
        gbc.gridx = 1;
        gbc.gridy++;
        panel.add(new JLabel("Product Name:"), gbc);
        gbc.gridy++;
        panel.add(productNameField, gbc);
        gbc.gridy++;
        panel.add(new JLabel("Quantity:"), gbc);
        gbc.gridy++;
        panel.add(quantityField, gbc);
        //gbc.gridy++;
        //panel.add(new JLabel("Unit Price:"), gbc);
        //gbc.gridy++;
        //panel.add(unitPriceField, gbc);
        gbc.gridy++;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(0, 100, 0, 100);
        panel.add(addProductButton, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 0, 0); 
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(detailScrollPane, gbc);
        invoiceItemListLz.clear();
        
        // Bắt sự kiện khi người dùng nhấn nút "Add Product"
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy thông tin từ các trường nhập liệu
                String productName = (String) productNameField.getSelectedItem();
                Number quantityNumber = (Number) quantityField.getValue();
                BigDecimal totalPrice = BigDecimal.ZERO;
                int importId = 0;
                int productIdd = 0;
                int quantitys = 0;
                BigDecimal unitPrice = BigDecimal.ZERO;
                BigDecimal profit = BigDecimal.ZERO;

                // Kiểm tra ràng buộc không được để trống
                if ( quantityNumber == null) {
                    JOptionPane.showMessageDialog(null, "Không được để trống!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int productId = productList.get(productNameField.getSelectedIndex()).getProductId();
                
                int quantity = quantityNumber.intValue();                               
                controller_Invoice find = new controller_Invoice();             
                try{
                    imports = find.findAvailableId(productId, quantity);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                int need = quantity;
                int conhang = 0;
                for (Import importv : importList){
                    if(importv.getProductId() == productId){
                        conhang += importv.getAvailableQuantity();
                    }
                }
                
                int conhangtmp = 0;
                for (Import abc : importListLz){
                    if (abc.getProductId() == productId){
                        conhangtmp += abc.getAvailableQuantity();
                    }
                }
                for (Import x : importListLz){
                    if (x.getImportId()==productId){
                        if(need<=conhangtmp){
                            for (Import importItem : imports) {
                            if (need>=importItem.getAvailableQuantity()){
                                quantitys = importItem.getAvailableQuantity();
                                need -= importItem.getAvailableQuantity();
                            } else {
                                quantitys = need;
                                need = 0;
                            }
                            productIdd = importItem.getProductId();
                            BigDecimal quantityBigDecimal = new BigDecimal(quantitys);
                            totalPrice = importItem.getSellPrice().multiply(quantityBigDecimal);
                            System.out.println(importItem.getSellPrice());
                            importId = importItem.getImportId();
                            unitPrice = importItem.getUnitPrice();
                            profit = totalPrice.subtract(unitPrice.multiply(quantityBigDecimal));

                            InvoiceItem invoiceItems = new InvoiceItem(productIdd,importId,unitPrice,quantitys,totalPrice,profit);
                            importItem.setAvailableQuantity(importItem.getAvailableQuantity()-quantitys);
                            importListLz.add(importItem);
                            invoiceItemListLz.add(invoiceItems);  
                            //refreshimportList();
                        }                      
                    } else {
                            JOptionPane.showMessageDialog(null, "Trong kho chỉ còn " + conhangtmp +" sản phẩm", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        break;
                    }
                }
                if (need <= conhang){
                    for (Import importItem : imports) {
                        System.out.println("import ne "+importItem.getAvailableQuantity());
                        if (need>=importItem.getAvailableQuantity()){
                            quantitys = importItem.getAvailableQuantity();
                            need -= importItem.getAvailableQuantity();
                        } else {
                            quantitys = need;
                            need = 0;
                        }                   
                        //conhang += quantitys;
    //                    System.out.println("need ne"+need);
    //                    if (need == 0) {
                            productIdd = importItem.getProductId();
                            BigDecimal quantityBigDecimal = new BigDecimal(quantitys);
                            totalPrice = importItem.getSellPrice().multiply(quantityBigDecimal);
                            System.out.println(importItem.getSellPrice());
                            importId = importItem.getImportId();
                            unitPrice = importItem.getUnitPrice();
                            profit = totalPrice.subtract(unitPrice.multiply(quantityBigDecimal));

                            InvoiceItem invoiceItems = new InvoiceItem(productIdd,importId,unitPrice,quantitys,totalPrice,profit);
                            invoiceItemListLz.add(invoiceItems);
                            importItem.setAvailableQuantity(importItem.getAvailableQuantity()-quantitys);
                            System.out.println("moe m"+importItem.getAvailableQuantity());
                            importListLz.add(importItem);

                    }
                }else {
                            JOptionPane.showMessageDialog(null, "Trong kho chỉ còn " + conhang +" sản phẩm", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                              

                // Thêm thông tin sản phẩm vào bảng chi tiết sản phẩm
                detailTableModel.addRow(new Object[]{productName, quantity, unitPrice,totalPrice,importId, profit, productIdd});

                // Xóa dữ liệu từ các trường nhập liệu sau khi thêm
                productNameField.setSelectedIndex(-1);
                quantityField.setValue(null);
                //unitPriceField.setValue(null);
            }
        });
        // Hiển thị hộp thoại và kiểm tra nút bấm
        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Invoice Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Nếu người dùng nhấn OK
        if (result == JOptionPane.OK_OPTION) {
                java.sql.Date dateText = convertStringtoDate(dateField.getText());
                Number totalAmountNumber = (Number) totalAmountField.getValue();
                // Kiểm tra ràng buộc không được để trống
                if (dateText == null) {
                    JOptionPane.showMessageDialog(null, "Không được để trống!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //int totalAmount = totalAmountNumber.intValue();
                LocalDateTime createdAt = null;
                LocalDateTime updateAt = null;

                // Tạo một đối tượng Invoice từ thông tin vừa nhập
                Invoice newInvoice = new Invoice( selectedCustomerId,selectedStaffId,dateText);
                int invoiceId = 0;

                // Thêm vào danh sách hóa đơn và cập nhật bảng
               
                invoiceList.add(newInvoice);
                table.addRow(new Object[]{selectedCustomerName,selectedStaffName, newInvoice.getPurchaseDate(),totalAmountNumber});
                
                controller_Invoice controller = new controller_Invoice();
                try {
                    System.out.println("start try invoice");
                    invoiceId = controller.addInvoice(newInvoice);
                    } catch (SQLException ex) {
                }
                System.out.println(invoiceId);
                for (InvoiceItem invoiceItem : invoiceItemListLz) {
                    //String productName = (String) detailTableModel.getValueAt(row, 0);
                    int quantity = invoiceItem.getQuantity();
                    BigDecimal unitPrice = invoiceItem.getUnitPrice();
                    BigDecimal totalPrice = invoiceItem.getTotalPrice();
                    int importId = invoiceItem.getImportId();
                    BigDecimal profit = invoiceItem.getProfit();
                    int productIdd = invoiceItem.getProductId();
                    InvoiceItem invoiceItemm = new InvoiceItem(invoiceId,productIdd,importId,unitPrice,quantity, totalPrice,profit);
                    controller_InvoiceItem newInvoiceItem = new controller_InvoiceItem();
                    try {
                        System.out.println("start try invoiceItem");
                        newInvoiceItem.addInvoiceItem(invoiceItemm);
                    } catch (SQLException ex) {
                        Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //controller_Invoice find = new controller_Invoice();
//                    try{
//                        imports = find.findAvailableId(productIdd, quantity);
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                    }
                    for (Import importt : importList){                     
                        if (importt.getImportId() == importId){
                            controller_Import i = new controller_Import();
                            importt.setAvailableQuantity(importt.getAvailableQuantity() - quantity);                              
                                try {
                                    i.editImport(importt);
                                } catch (SQLException ex) {
                                    Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        }
                    }
//                    int need = quantity;
//                    for (Import importItem : imports) {                 
//                        controller_Import i = new controller_Import();
//                        if (need > 0) {
//                            if (need > importItem.getAvailableQuantity()) {
//                                need -=importItem.getAvailableQuantity();
//                                importItem.setAvailableQuantity(0);                              
//                                try {
//                                    i.editImport(importItem);
//                                } catch (SQLException ex) {
//                                    Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            } else {
//                                importItem.setAvailableQuantity(importItem.getAvailableQuantity() - need);
//                                need = 0;
//                                try {
//                                    i.editImport(importItem);
//                                } catch (SQLException ex) {
//                                    Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                        }
//                }
            }
            
        }
        refreshimportListLz();
        refreshtable();
    }//GEN-LAST:event_insertBtnActionPerformed

    private void txtCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNameActionPerformed

    private void totalAmountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalAmountFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalAmountFieldActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this invoice?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                
                int invoiceId = invoiceList.get(selectedRow).getInvoiceId();
                System.out.println("in ra invoice id :"+invoiceId);
                controller_Invoice controller = new controller_Invoice();
                controller_InvoiceItem controllerr = new controller_InvoiceItem();
                

                try {
                    controller.deleteInvoice(invoiceId);
                    System.out.println("da chay toi luc xoa invoice id ");
                    controllerr.deleteInvoiceItemId(invoiceId);
                    refreshtable();                
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select an invoice to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed


    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to return this invoice?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                int invoiceId = invoiceList.get(selectedRow).getInvoiceId();
                controller_Invoice controller = new controller_Invoice();
                controller_InvoiceItem controllerr = new controller_InvoiceItem();
                controller_Import controllerrr = new controller_Import();
                for (InvoiceItem invoiceItem : invoiceItemList){
                    if (invoiceItem.getInvoiceId() == invoiceId){
                        for (Import imported : importList){
                            if (imported.getImportId()==invoiceItem.getImportId()){
                                imported.setAvailableQuantity(imported.getAvailableQuantity()+invoiceItem.getQuantity());
                                try {
                                    controllerrr.editImport(imported);
                                } catch (SQLException ex) {
                                    Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }                  
                }
            
                try {
                    controller.deleteInvoice(invoiceId);
                    controllerr.deleteInvoiceItemId(invoiceId);
                    refreshtable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select an invoice to return!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_returnBtnActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        String searchTxt = this.txtSearch.getText();
        refreshtable();
    }//GEN-LAST:event_txtSearchKeyTyped



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelButton;
    private javax.swing.JPanel PanelDUBtn;
    private javax.swing.JPanel PanelDetail;
    private javax.swing.JPanel PanelFilter;
    private javax.swing.JPanel PanelHeader;
    private javax.swing.JPanel PanelInsert;
    private javax.swing.JPanel PanelLeft;
    private javax.swing.JPanel PanelRight;
    private javax.swing.JPanel PanelSearch;
    private javax.swing.JPanel PanelTable;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JScrollPane detailSpTable;
    private javax.swing.JButton insertBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton returnBtn;
    private javax.swing.JComboBox<String> sortComboBox;
    private javax.swing.JScrollPane spTable;
    private com.view.swing.Table table;
    private com.view.swing.Table tableDetail;
    private javax.swing.JFormattedTextField totalAmountField;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStaffName;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
