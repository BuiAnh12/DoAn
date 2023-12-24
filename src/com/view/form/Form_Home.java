package com.view.form;

import com.controller.controller_Dashboard;
import com.view.model.Model_Card;
import com.view.model.StatusType;
import com.view.swing.ScrollBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Form_Home extends javax.swing.JPanel {
        private Form_chart formchart;
        
        
        public Form_Home() {
        initComponents();
        controller_Dashboard tmp=new controller_Dashboard();
        List<BigDecimal>money=tmp.getRevenue();
            try {
                formchart=new Form_chart();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/view/icon/stock.png")), "Quanity",String.valueOf(tmp.getSoldQuanity())));
                card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/view/icon/profit.png")), "Revenue", String.valueOf(decimalFormat.format(money.get(1)))+" VNĐ"));
                card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/view/icon/Customer1.png")), "Customer", String.valueOf(tmp.getTotalCustomer())));
            //  add row table
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            Chart_panel.add(formchart.getContentPane());
            

            setVisible(true);
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.view.component.Card();
        card2 = new com.view.component.Card();
        card3 = new com.view.component.Card();
        Chart_panel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(22, 23, 23));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        Chart_panel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Chart_panelLayout = new javax.swing.GroupLayout(Chart_panel);
        Chart_panel.setLayout(Chart_panelLayout);
        Chart_panelLayout.setHorizontalGroup(
            Chart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Chart_panelLayout.setVerticalGroup(
            Chart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Chart_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 875, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Chart_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        Chart_panel.getAccessibleContext().setAccessibleParent(Chart_panel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Chart_panel;
    private com.view.component.Card card1;
    private com.view.component.Card card2;
    private com.view.component.Card card3;
    private javax.swing.JLayeredPane panel;
    // End of variables declaration//GEN-END:variables

//    private void setForm(Form_chart formchart) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
