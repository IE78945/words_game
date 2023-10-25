/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package letters_game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class dictio extends javax.swing.JFrame {

    Connection c;
    
    public dictio() {
        initComponents();
        Afficher();
    }
  private void Afficher(){
        int i=0;
        c = BD_Connexion.getConnection();
        DefaultTableModel model = (DefaultTableModel) Mots_table.getModel();
        model.setRowCount(0);

        if(select.getSelectedItem().toString().equals("par defaut")){
            try {
                Statement st = c.createStatement();
                String s = "SELECT * FROM dictionnaire ORDER BY mot;";
                ResultSet result = st.executeQuery(s);
                while (result.next())
                    model.addRow(new Object[]{result.getString(1)});
            }
            catch (Exception e) {
                System.out.println(e);
            } 
        }
        else 
        {
            try {
                Statement st = c.createStatement();
                String s = "SELECT * FROM dictionnaire where mot like '"+select.getSelectedItem().toString().toLowerCase()+"%';";
                ResultSet result = st.executeQuery(s);
                while (result.next())
                    model.addRow(new Object[]{result.getString(1)});
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        
        
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Ajouter_btn = new javax.swing.JButton();
        Chercher_btn = new javax.swing.JButton();
        Supprimer_btn = new javax.swing.JButton();
        mot_cle_zone = new javax.swing.JTextField();
        Deconnecter_btn = new javax.swing.JButton();
        select = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        Mots_table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Letters Game");
        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(800, 537));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Ajouter_btn.setOpaque(false);
        Ajouter_btn.setContentAreaFilled(false);
        Ajouter_btn.setBorderPainted(false);
        Ajouter_btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Ajouter_btn.setText("ADD");
        Ajouter_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ajouter_btnActionPerformed(evt);
            }
        });
        jPanel1.add(Ajouter_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 160, 50));

        Chercher_btn.setOpaque(false);
        Chercher_btn.setContentAreaFilled(false);
        Chercher_btn.setBorderPainted(false);
        Chercher_btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Chercher_btn.setText("SEARCH");
        Chercher_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Chercher_btnActionPerformed(evt);
            }
        });
        jPanel1.add(Chercher_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 290, 160, 50));

        Supprimer_btn.setOpaque(false);
        Supprimer_btn.setContentAreaFilled(false);
        Supprimer_btn.setBorderPainted(false);
        Supprimer_btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Supprimer_btn.setText("DELETE");
        Supprimer_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supprimer_btnActionPerformed(evt);
            }
        });
        jPanel1.add(Supprimer_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, 160, 50));

        mot_cle_zone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mot_cle_zoneActionPerformed(evt);
            }
        });
        jPanel1.add(mot_cle_zone, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 200, 40));

        Deconnecter_btn.setOpaque(false);
        Deconnecter_btn.setContentAreaFilled(false);
        Deconnecter_btn.setBorderPainted(false);
        Deconnecter_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Deconnecter_btnActionPerformed(evt);
            }
        });
        jPanel1.add(Deconnecter_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 86));

        select.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        select.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "par defaut", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel1.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 170, 40));

        Mots_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MOTS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(Mots_table);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 47, 300, 420));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/letters_game/images/dictionnaire_bg_1.png"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Ajouter_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ajouter_btnActionPerformed
        Connection c = BD_Connexion.getConnection();
        PreparedStatement ps ;
        try {
            ps = c.prepareStatement("INSERT INTO dictionnaire (mot) VALUES(?);");
            ps.setString(1, mot_cle_zone.getText());
            int nb = ps.executeUpdate();
            if (nb != 0) {
                JOptionPane.showMessageDialog(this,"le mot est inséré");
                Afficher();
            }
        } 
        catch (Exception e) {e.printStackTrace(); JOptionPane.showMessageDialog(this,"le mot est déja inséré");}
        
    }//GEN-LAST:event_Ajouter_btnActionPerformed

     
    
    private void Chercher_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Chercher_btnActionPerformed
        // TODO add your handling code here:
        String s=null;
        Connection c = BD_Connexion.getConnection();
        PreparedStatement ps ;
        try {
            ps = c.prepareStatement("SELECT * FROM dictionnaire WHERE mot = ?");
            ps.setString(1, mot_cle_zone.getText());
            ResultSet result = ps.executeQuery();
            while (result.next())
            s = result.getString(1);
            if (s!=null) JOptionPane.showMessageDialog(this,"le mot existe");
            else JOptionPane.showMessageDialog(this,"le mot n'existe pas");
        }
        catch (Exception e) {e.printStackTrace();}

    }//GEN-LAST:event_Chercher_btnActionPerformed

       
    
    private void mot_cle_zoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mot_cle_zoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mot_cle_zoneActionPerformed

    private void Deconnecter_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Deconnecter_btnActionPerformed
        new home().setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_Deconnecter_btnActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        Afficher();
    }//GEN-LAST:event_selectActionPerformed

    private void Supprimer_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supprimer_btnActionPerformed
        Connection c = BD_Connexion.getConnection();
        PreparedStatement ps ;
        try {
            ps= c.prepareStatement("DELETE FROM dictionnaire WHERE mot = ?;");
            ps.setString(1, mot_cle_zone.getText());
            int nb = ps.executeUpdate();
            if (nb !=0) 
            {
                JOptionPane.showMessageDialog(this,"le mot est supprimé");
                Afficher();
            }
        }
        catch (Exception e) {e.printStackTrace();}
    }//GEN-LAST:event_Supprimer_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dictio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dictio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dictio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dictio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dictio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ajouter_btn;
    private javax.swing.JButton Chercher_btn;
    private javax.swing.JButton Deconnecter_btn;
    private javax.swing.JTable Mots_table;
    private javax.swing.JButton Supprimer_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField mot_cle_zone;
    private javax.swing.JComboBox<String> select;
    // End of variables declaration//GEN-END:variables
}
