/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package letters_game;

import javax.swing.JOptionPane;

public class login_admin extends javax.swing.JFrame {

    /**
     * Creates new form login_admin
     */
    public login_admin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Admin_Login = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        Login_btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Admin_mdp = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Letters Game");
        setMinimumSize(new java.awt.Dimension(700, 450));
        setPreferredSize(new java.awt.Dimension(700, 450));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_Login.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Admin_Login.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Admin_Login.setBorder(null);
        Admin_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Admin_LoginActionPerformed(evt);
            }
        });
        getContentPane().add(Admin_Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 290, 40));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 0, -1, 791));

        Login_btn.setOpaque(false);
        Login_btn.setContentAreaFilled(false);
        Login_btn.setBorderPainted(false);
        Login_btn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Login_btn.setForeground(new java.awt.Color(255, 255, 255));
        Login_btn.setText("ok");
        Login_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login_btnActionPerformed(evt);
            }
        });
        getContentPane().add(Login_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 180, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        Admin_mdp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Admin_mdp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Admin_mdp.setText("jPasswordField1");
        Admin_mdp.setBorder(null);
        getContentPane().add(Admin_mdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 290, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/letters_game/images/login.png"))); // NOI18N
        jLabel1.setText("Name");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setAlignmentX(0.5F);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Admin_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Admin_LoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Admin_LoginActionPerformed

    private void Login_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Login_btnActionPerformed
        if ((Admin_Login.getText().equals("admin")) && ( String.valueOf(Admin_mdp.getPassword()).equals("admin"))){
            this.setVisible(false);
            new dictio().setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(this, "Veuillez vérifier vos données!");
        }
    }//GEN-LAST:event_Login_btnActionPerformed

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
            java.util.logging.Logger.getLogger(login_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login_admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Admin_Login;
    private javax.swing.JPasswordField Admin_mdp;
    private javax.swing.JButton Login_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
