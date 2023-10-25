/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package letters_game;

import javax.swing.JOptionPane;

public class home extends javax.swing.JFrame {

private static String Joueur1_txt , Joueur2_txt;
   
    public static String getJoueur1_txt() {
        return Joueur1_txt;
    }

    public static String getJoueur2_txt() {
        return Joueur2_txt;
    }
    
    public home() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Joueur1_name = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        login_admin = new javax.swing.JButton();
        Joueur2_name = new javax.swing.JTextField();
        play_btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Letters Game");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Joueur1_name.setOpaque(false);
        Joueur1_name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Joueur1_name.setForeground(new java.awt.Color(255, 255, 255));
        Joueur1_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Joueur1_name.setBorder(null);
        Joueur1_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Joueur1_nameActionPerformed(evt);
            }
        });
        getContentPane().add(Joueur1_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 230, 60));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1204, 0, -1, -1));

        login_admin.setOpaque(false);
        login_admin.setContentAreaFilled(false);
        login_admin.setBorderPainted(false);
        login_admin.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        login_admin.setForeground(new java.awt.Color(255, 255, 255));
        login_admin.setText("LOGIN");
        login_admin.setBorder(null);
        login_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_adminActionPerformed(evt);
            }
        });
        getContentPane().add(login_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, 220, 70));

        Joueur2_name.setOpaque(false);
        Joueur2_name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Joueur2_name.setForeground(new java.awt.Color(255, 255, 255));
        Joueur2_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Joueur2_name.setBorder(null);
        Joueur2_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Joueur2_nameActionPerformed(evt);
            }
        });
        getContentPane().add(Joueur2_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 300, 230, 50));

        play_btn.setOpaque(false);
        play_btn.setContentAreaFilled(false);
        play_btn.setBorderPainted(false);
        play_btn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        play_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play_btnActionPerformed(evt);
            }
        });
        getContentPane().add(play_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 50, 170));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/letters_game/images/home_bg.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1210, 690));

        jButton1.setText("jButton1");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 40, -1, -1));
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, -1, 180));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Joueur1_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Joueur1_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Joueur1_nameActionPerformed

    private void Joueur2_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Joueur2_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Joueur2_nameActionPerformed

    private void play_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play_btnActionPerformed
        if(!Joueur1_name.getText().trim().isEmpty()  && !Joueur2_name.getText().trim().isEmpty() ) {
            Joueur1_txt = Joueur1_name.getText();
            Joueur2_txt = Joueur2_name.getText();
            this.setVisible(false);
            new play().setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(this, "Veuillez entrer les noms des joueurs!");
        }

    }//GEN-LAST:event_play_btnActionPerformed

    private void login_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_adminActionPerformed
        this.setVisible(false);
            new login_admin().setVisible(true);
    }//GEN-LAST:event_login_adminActionPerformed

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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new play().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Joueur1_name;
    private javax.swing.JTextField Joueur2_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton login_admin;
    private javax.swing.JButton play_btn;
    // End of variables declaration//GEN-END:variables
}
