/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.form;

import com.view.swing.ScrollBar;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author RAVEN
 */
public class Form_2 extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    public Form_2() {
        initComponents();
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        table.addRow(new Object[]{"NPK 16-10-6+13S+1,5CA+1MG", "Công ty Cổ Phân Phân bón Việt Nhật", "Đạm tổng số (Nts): 16%, Lân hữu hiệu (P2O5hh): 10%, Kali hữu hiệu (K2Ohh): 6%, Lưu huỳnh (S): 13%, Canxi (Ca): 1.5%, Magie (Mg): 1%, Độ ẩm: 2%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK 16-8-8+13S", "Công ty Cổ Phân Phân bón Việt Nhật", "Đạm tổng số (Nts): 16%, Lân hữu hiệu (P2O5hh): 8%, Kali hữu hiệu (K2Ohh): 8%, Lưu huỳnh (S): 13%, Độ ẩm: 2%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK 16-8-14+12S", "Công ty Cổ Phân Phân bón Việt Nhật", "Đạm tổng số (Nts): 16%, Lân hữu hiệu (P2O5hh): 8%, Kali hữu hiệu (K2Ohh): 14%, Lưu huỳnh (S): 12%, Độ ẩm: 2%", "Phân Vô cơ"});
        table.addRow(new Object[]{"Ure 00056", "Tập Đoàn Dầu khí quốc gia Việt Nam", "Đạm Tổng số (Nts): 46.3%; Biuret: 1%; Độ ẩm: 0.4%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK ĐẦU TRÂU 16-16-8+13S sọc xanh", "Công ty Cổ Phân Phân bón Bình Điền", "Đạm tổng số (Nts): 16%; Lân hữu hiệu (P2O5hh): 16%; Kali hữu hiệu (K2Ohh): 8%; Lưu huỳnh (S): 13%; Độ ẩm: ≤2.5%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NP ĐẦU TRÂU 20-20", "Công ty Cổ Phân Phân bón Bình Điền", "Đạm tổng số (Nts): 20%; Lân hữu hiệu (P2O5hh): 20%; Độ ẩm: ≤2.5%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NUTRIFERT 4-3-3 OM", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 4%, lân (P2O5): 3.2%, kali (K2O): 2.8%.", "Phân hữu cơ"});
        table.addRow(new Object[]{"NPK Amazon cao su - 40kg", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 17.07%, lân (P2O5): 7.07%, kali (K2O): 20%.", "Phân Vô cơ"});
        table.addRow(new Object[]{"URE 46TE - 25kg", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 46%, màu trắng, tan tốt trong nước.", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK Big one mùa khô-40kg", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 18.16%, lân (P2O5): 5.15%, kali (K2O): 4.16%.", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK 16-10-6+13S+1,5CA+1MG", "Công ty Cổ Phân Phân bón Việt Nhật", "Đạm tổng số (Nts): 16%, Lân hữu hiệu (P2O5hh): 10%, Kali hữu hiệu (K2Ohh): 6%, Lưu huỳnh (S): 13%, Canxi (Ca): 1.5%, Magie (Mg): 1%, Độ ẩm: 2%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK 16-8-8+13S", "Công ty Cổ Phân Phân bón Việt Nhật", "Đạm tổng số (Nts): 16%, Lân hữu hiệu (P2O5hh): 8%, Kali hữu hiệu (K2Ohh): 8%, Lưu huỳnh (S): 13%, Độ ẩm: 2%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK 16-8-14+12S", "Công ty Cổ Phân Phân bón Việt Nhật", "Đạm tổng số (Nts): 16%, Lân hữu hiệu (P2O5hh): 8%, Kali hữu hiệu (K2Ohh): 14%, Lưu huỳnh (S): 12%, Độ ẩm: 2%", "Phân Vô cơ"});
        table.addRow(new Object[]{"Ure 00056", "Tập Đoàn Dầu khí quốc gia Việt Nam", "Đạm Tổng số (Nts): 46.3%; Biuret: 1%; Độ ẩm: 0.4%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK ĐẦU TRÂU 16-16-8+13S sọc xanh", "Công ty Cổ Phân Phân bón Bình Điền", "Đạm tổng số (Nts): 16%; Lân hữu hiệu (P2O5hh): 16%; Kali hữu hiệu (K2Ohh): 8%; Lưu huỳnh (S): 13%; Độ ẩm: ≤2.5%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NP ĐẦU TRÂU 20-20", "Công ty Cổ Phân Phân bón Bình Điền", "Đạm tổng số (Nts): 20%; Lân hữu hiệu (P2O5hh): 20%; Độ ẩm: ≤2.5%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NUTRIFERT 4-3-3 OM", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 4%, lân (P2O5): 3.2%, kali (K2O): 2.8%.", "Phân hữu cơ"});
        table.addRow(new Object[]{"NPK Amazon cao su - 40kg", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 17.07%, lân (P2O5): 7.07%, kali (K2O): 20%.", "Phân Vô cơ"});
        table.addRow(new Object[]{"URE 46TE - 25kg", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 46%, màu trắng, tan tốt trong nước.", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK Big one mùa khô-40kg", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 18.16%, lân (P2O5): 5.15%, kali (K2O): 4.16%.", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK 16-10-6+13S+1,5CA+1MG", "Công ty Cổ Phân Phân bón Việt Nhật", "Đạm tổng số (Nts): 16%, Lân hữu hiệu (P2O5hh): 10%, Kali hữu hiệu (K2Ohh): 6%, Lưu huỳnh (S): 13%, Canxi (Ca): 1.5%, Magie (Mg): 1%, Độ ẩm: 2%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK 16-8-8+13S", "Công ty Cổ Phân Phân bón Việt Nhật", "Đạm tổng số (Nts): 16%, Lân hữu hiệu (P2O5hh): 8%, Kali hữu hiệu (K2Ohh): 8%, Lưu huỳnh (S): 13%, Độ ẩm: 2%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK 16-8-14+12S", "Công ty Cổ Phân Phân bón Việt Nhật", "Đạm tổng số (Nts): 16%, Lân hữu hiệu (P2O5hh): 8%, Kali hữu hiệu (K2Ohh): 14%, Lưu huỳnh (S): 12%, Độ ẩm: 2%", "Phân Vô cơ"});
        table.addRow(new Object[]{"Ure 00056", "Tập Đoàn Dầu khí quốc gia Việt Nam", "Đạm Tổng số (Nts): 46.3%; Biuret: 1%; Độ ẩm: 0.4%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK ĐẦU TRÂU 16-16-8+13S sọc xanh", "Công ty Cổ Phân Phân bón Bình Điền", "Đạm tổng số (Nts): 16%; Lân hữu hiệu (P2O5hh): 16%; Kali hữu hiệu (K2Ohh): 8%; Lưu huỳnh (S): 13%; Độ ẩm: ≤2.5%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NP ĐẦU TRÂU 20-20", "Công ty Cổ Phân Phân bón Bình Điền", "Đạm tổng số (Nts): 20%; Lân hữu hiệu (P2O5hh): 20%; Độ ẩm: ≤2.5%", "Phân Vô cơ"});
        table.addRow(new Object[]{"NUTRIFERT 4-3-3 OM", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 4%, lân (P2O5): 3.2%, kali (K2O): 2.8%.", "Phân hữu cơ"});
        table.addRow(new Object[]{"NPK Amazon cao su - 40kg", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 17.07%, lân (P2O5): 7.07%, kali (K2O): 20%.", "Phân Vô cơ"});
        table.addRow(new Object[]{"URE 46TE - 25kg", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 46%, màu trắng, tan tốt trong nước.", "Phân Vô cơ"});
        table.addRow(new Object[]{"NPK Big one mùa khô-40kg", "Công Ty Cổ Phần Phân Bón Hà Lan", "Đạm tổng số (N): 18.16%, lân (P2O5): 5.15%, kali (K2O): 4.16%.", "Phân Vô cơ"});
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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        PanelFilter = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        PanelInsert = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        PanelTable = new javax.swing.JPanel();
        spTable = new javax.swing.JScrollPane();
        table = new com.view.swing.Table();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelRight = new javax.swing.JPanel();
        PanelHeader = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        PanelDetail = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        NameText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ManufactureText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CategorySelect = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        DescriptionSp = new javax.swing.JScrollPane();
        DescriptionTextFile = new javax.swing.JTextArea();
        PanelDUBtn = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(22, 23, 23));
        setForeground(new java.awt.Color(22, 23, 23));
        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setPreferredSize(new java.awt.Dimension(1080, 720));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(22, 23, 23));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        PanelLeft.setBackground(new java.awt.Color(22, 23, 23));

        PanelButton.setBackground(new java.awt.Color(22, 23, 23));
        PanelButton.setLayout(new javax.swing.BoxLayout(PanelButton, javax.swing.BoxLayout.LINE_AXIS));

        PanelSearch.setBackground(new java.awt.Color(22, 23, 23));

        jTextField1.setBackground(new java.awt.Color(36, 36, 36));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("Search");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(22, 23, 23));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/view/icon/search.png"))); // NOI18N

        javax.swing.GroupLayout PanelSearchLayout = new javax.swing.GroupLayout(PanelSearch);
        PanelSearch.setLayout(PanelSearchLayout);
        PanelSearchLayout.setHorizontalGroup(
            PanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelSearchLayout.setVerticalGroup(
            PanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        PanelButton.add(PanelSearch);

        PanelFilter.setBackground(new java.awt.Color(22, 23, 23));

        jComboBox1.setBackground(new java.awt.Color(36, 36, 36));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort by Name", "Sort by Manufacture", "Sort byCategory", " " }));
        jComboBox1.setToolTipText("");

        javax.swing.GroupLayout PanelFilterLayout = new javax.swing.GroupLayout(PanelFilter);
        PanelFilter.setLayout(PanelFilterLayout);
        PanelFilterLayout.setHorizontalGroup(
            PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFilterLayout.createSequentialGroup()
                .addComponent(jComboBox1, 0, 199, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelFilterLayout.setVerticalGroup(
            PanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        PanelButton.add(PanelFilter);

        PanelInsert.setBackground(new java.awt.Color(22, 23, 23));

        jButton1.setBackground(new java.awt.Color(36, 36, 36));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Insert");

        javax.swing.GroupLayout PanelInsertLayout = new javax.swing.GroupLayout(PanelInsert);
        PanelInsert.setLayout(PanelInsertLayout);
        PanelInsertLayout.setHorizontalGroup(
            PanelInsertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInsertLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelInsertLayout.setVerticalGroup(
            PanelInsertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        PanelButton.add(PanelInsert);

        PanelTable.setForeground(new java.awt.Color(60, 63, 65));

        spTable.setBorder(null);

        table.setForeground(new java.awt.Color(22, 23, 23));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Manufacture", "Description", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout PanelTableLayout = new javax.swing.GroupLayout(PanelTable);
        PanelTable.setLayout(PanelTableLayout);
        PanelTableLayout.setHorizontalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(22, 23, 23));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("PRODUCT");
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
            .addComponent(PanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        PanelDetail.setForeground(new java.awt.Color(36, 36, 36));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Name");

        NameText.setText("jTextField3");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Manufacture");

        ManufactureText.setText("jTextField3");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Category");

        CategorySelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phân hữu cơ", "Phân vô cơ", " " }));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Decscription");

        DescriptionTextFile.setColumns(20);
        DescriptionTextFile.setRows(5);
        DescriptionSp.setViewportView(DescriptionTextFile);

        javax.swing.GroupLayout PanelDetailLayout = new javax.swing.GroupLayout(PanelDetail);
        PanelDetail.setLayout(PanelDetailLayout);
        PanelDetailLayout.setHorizontalGroup(
            PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelDetailLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CategorySelect, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ManufactureText)
                            .addComponent(NameText)
                            .addComponent(DescriptionSp, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE))))
                .addGap(0, 37, Short.MAX_VALUE))
        );
        PanelDetailLayout.setVerticalGroup(
            PanelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ManufactureText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CategorySelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(DescriptionSp, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        PanelRight.add(PanelDetail, java.awt.BorderLayout.CENTER);

        PanelDUBtn.setBackground(new java.awt.Color(22, 23, 23));
        PanelDUBtn.setLayout(new javax.swing.BoxLayout(PanelDUBtn, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setBackground(new java.awt.Color(22, 23, 23));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        PanelDUBtn.add(jPanel3);

        jButton3.setBackground(new java.awt.Color(36, 36, 36));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("DELETE");
        PanelDUBtn.add(jButton3);

        jButton2.setBackground(new java.awt.Color(36, 36, 36));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("UPUDATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        PanelDUBtn.add(jButton2);

        PanelRight.add(PanelDUBtn, java.awt.BorderLayout.SOUTH);

        jPanel1.add(PanelRight);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CategorySelect;
    private javax.swing.JScrollPane DescriptionSp;
    private javax.swing.JTextArea DescriptionTextFile;
    private javax.swing.JTextField ManufactureText;
    private javax.swing.JTextField NameText;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JScrollPane spTable;
    private com.view.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
