/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package letters_game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class play extends javax.swing.JFrame {

     int PlayerTurn=1,TextFieldNumber, RowCountDB , SelectedRowNb,j , partie=1 , b=0 , k =0;

    String GivenLetters ="", Player1Word = "" , Player2Word="", GivenLetters1,GivenLetters2, SelectedWord;
    
    static String WinnerName;

    public static String getWinnerName() {
        return WinnerName;
    }
    
    
    public play() {
        initComponents();
        //set player 1 and 2 names
        Player1Name.setText(home.getJoueur1_txt());
        Player2Name.setText(home.getJoueur2_txt());
        //player 1 turn
        Joueur1Play();
    }

    public void InitialiserLetters() {
        GivenLetters="";
        // get mot row count 
        Connection c = BD_Connexion.getConnection();
        try {
            Statement st = c.createStatement();
            String s = "SELECT COUNT(mot) FROM dictionnaire;";
            ResultSet result = st.executeQuery(s);
            while (result.next()){RowCountDB = result.getInt(1);}
            //System.out.println(RowCountDB);
        }
        catch (Exception e) {System.out.println(e);}

        // get random number between 1 and rowCount with random.nextInt(max - min) + min 
        Random random = new Random();
        SelectedRowNb = random.nextInt(RowCountDB-1)+1;
        //System.out.println(SelectedRowNb);       
        
        //get the word in the position SelectedRowNb
        try {
            Statement st = c.createStatement();
            String s = "SELECT *FROM dictionnaire;";
            ResultSet result = st.executeQuery(s);
            j=1;
            while (result.next()){
                if (j == SelectedRowNb) {
                    SelectedWord = result.getString(1);
                    System.out.println("Word from DB : "+SelectedWord);
                    break;
                }
                j++ ;      
            }
        }
        catch (Exception e) {System.out.println(e);}
        
        //add missing char (12 char are needed )
        while (SelectedWord.length()<12) {
            char ch =(char)(new Random().nextInt(26) + 'a');
            SelectedWord = SelectedWord + ch ;
        }
        
        
        // shuffle letters
        ArrayList<String> l = new ArrayList<String>();
        for (int i=0;i<12;i++){ l.add(String.valueOf(SelectedWord.charAt(i)));}
            // sort alphapitique
            Collections.sort(l);
            // shuffle with random
            Collections.shuffle(l, new Random(3));
            
          
        // Display letters in labes
        Component[] comp1 = PanelGivenLettersJoueur1.getComponents();
        Component[] comp2 = PanelGivenLettersJoueur2.getComponents();
        for (int i = 0;i<comp1.length;i++) {
            ((JLabel)comp1[i]).setText(l.get(i));
            ((JLabel)comp2[i]).setText(l.get(i));
            GivenLetters = GivenLetters + l.get(i);}
            GivenLetters1 = GivenLetters ;
            GivenLetters2 = GivenLetters ;
            System.out.println("GivenLetters: " + GivenLetters);
   
    }

    private void Joueur1Play() {
        InitialiserLetters();
        Player1Word ="";
        //enable player 1 buttons
        ValiderBtn1.setEnabled(true);
        Annuler1.setEnabled(true);
        //disable player 2 buttons and fields
        ValiderBtn2.setEnabled(false);
        Annuler2.setEnabled(false);
        Component[] comp2 = Player2TextFields.getComponents();
        for (int i = 0;i<comp2.length;i++) {((JTextField)comp2[i]).setEnabled(false);}

        GetKeyPressedLetter1();     
    }
   
    private void Joueur2Play() {
        Player2Word ="";
        //disable player 1 buttons and fields
        ValiderBtn1.setEnabled(false);
        Annuler1.setEnabled(false);
        Component[] comp1 = Player1TextFields.getComponents();
        for (int i = 0;i<comp1.length;i++) {((JTextField)comp1[i]).setEnabled(false);}
        //enable player 2 buttons and fields
        ValiderBtn2.setEnabled(true);
        Annuler2.setEnabled(true);
        Component[] comp2 = Player2TextFields.getComponents();
        for (int i = 0;i<comp2.length;i++) {((JTextField)comp2[i]).setEnabled(true);}
        GetKeyPressedLetter2();
        
    }

    public void GetKeyPressedLetter1(){
        TextFieldNumber = 0;
        Component[] comp1 = Player1TextFields.getComponents();
        //reinitialize allowed letters if we come from anuller btn
        GivenLetters1 = GivenLetters ;
        //disable all player 1 text field exept the first one
        for (int i = 1;i<comp1.length;i++) 
            comp1[i].setEnabled(false);
        //set cursor in the first textfield
        comp1[0].setEnabled(true);
        comp1[0].requestFocusInWindow();
        // parcourir tout les textfield
        for (int i = b;i<comp1.length;i++) {
            b++;
            comp1[i].addKeyListener(new KeyListener() {
                @Override
                public void keyReleased(KeyEvent e) {
                    // check if typed letter exist in the list
                    if(GivenLetters1.indexOf(e.getKeyChar()) != -1 || GivenLetters1.toUpperCase().indexOf(e.getKeyChar()) != -1){
                        // delete used letter from the list
                        GivenLetters1 = GivenLetters1.replaceFirst(String.valueOf(e.getKeyChar()).toLowerCase(), "");
                        // print letter in textfield
                        ((JTextField)(comp1[TextFieldNumber])).setText(String.valueOf(e.getKeyChar()));
                        //disable textfield
                        comp1[TextFieldNumber].setEnabled(false);
                        //move to the next textfield if it's not the last textField
                        if (TextFieldNumber != 11) {
                            TextFieldNumber ++ ;
                            comp1[TextFieldNumber].setEnabled(true);
                            comp1[TextFieldNumber].requestFocusInWindow();
                        }
                        
                    }
                    else {
                        // if letter doesn't in the list don't print letter
                        ((JTextField)(comp1[TextFieldNumber])).setText("");
                    } 
                }
                
                @Override
                public void keyTyped(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent arg0) {}
            });
        }
    }
    
    public void GetKeyPressedLetter2(){
        TextFieldNumber = 0;
        Component[] comp2 = Player2TextFields.getComponents();
        //reinitialize allowed letters if we come from anuller btn
        GivenLetters2 = GivenLetters ;
        //disable all textfield except the first one
        for (int i = 1;i<comp2.length;i++)
            comp2[i].setEnabled(false);
        //set cursor in the first textfield
        comp2[0].setEnabled(true);
        comp2[0].requestFocusInWindow();
        // parcourir tout les textfield
        for (int i = k;i<comp2.length;i++) {
            k++;
            comp2[i].addKeyListener(new KeyListener() {
                @Override
                public void keyReleased(KeyEvent e) {
                    // check if typed letter exist in the list
                    if(GivenLetters2.indexOf(e.getKeyChar()) != -1 || GivenLetters2.toUpperCase().indexOf(e.getKeyChar()) != -1){
                        // delete used letter from the list
                        GivenLetters2 = GivenLetters2.replaceFirst(String.valueOf(e.getKeyChar()).toLowerCase(), "");
                        // print letter in textfield
                        ((JTextField)(comp2[TextFieldNumber])).setText(String.valueOf(e.getKeyChar()));
                        //disable textfield
                        comp2[TextFieldNumber].setEnabled(false);
                        //move to the next textfield if it's not the last textfield
                        if (TextFieldNumber != 11) {
                        TextFieldNumber ++ ;
                        comp2[TextFieldNumber].setEnabled(true);
                        comp2[TextFieldNumber].requestFocusInWindow();}
                    }
                    else {
                        // if letter doesn't in the list don't print letter
                        ((JTextField)(comp2[TextFieldNumber])).setText("");
                    } 
                }
                
                @Override
                public void keyTyped(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent arg0) {}
            });
        }
    }
    
    public void VerifyScore(){
        Connection c = BD_Connexion.getConnection();
        Boolean word1found=false,word2found=false;
        try {
                Statement st = c.createStatement();
                String s1 = "SELECT * FROM dictionnaire where mot = '"+Player1Word.toLowerCase()+"';";
                ResultSet result1 = st.executeQuery(s1);
                while (result1.next()){word1found=true;}
                
                String s2 = "SELECT * FROM dictionnaire where mot = '"+Player2Word.toLowerCase()+"';";
                ResultSet result2 = st.executeQuery(s2);
                while (result2.next()){word2found=true;}
                
                DefaultTableModel model1 = (DefaultTableModel) ResumeTable1.getModel();
                DefaultTableModel model2 = (DefaultTableModel) ResumeTable2.getModel();
                
                if (word1found==false && word2found==false) {
                    //diplay score in table player 1
                    model1.addRow(new Object[]{"Round"+partie,Player1Word,0});
                    //diplay score in table player 2
                    model2.addRow(new Object[]{"Round"+partie,Player2Word,0});
                }
                
                
                else if (word1found==true && word2found==false){
                    System.out.println("hi");
                    //diplay score in table player 1
                    model1.addRow(new Object[]{"Round"+partie,Player1Word,Player1Word.length()});
                    //diplay score in table player 2
                    model2.addRow(new Object[]{"Round"+partie,Player2Word,0});
                } 
                else if (word1found==false && word2found==true) {
                   //diplay score in table player 1
                    model1.addRow(new Object[]{"Round"+partie,Player1Word,0});
                    //diplay score in table player 2
                    model2.addRow(new Object[]{"Round"+partie,Player2Word,Player2Word.length()});
                } 
                
                else if (word1found==true && word2found==true && Player1Word.length()>Player2Word.length()) {
                    //diplay score in table player 1
                    model1.addRow(new Object[]{"Round"+partie,Player1Word,Player1Word.length()});
                   //diplay score in table player 2
                    model2.addRow(new Object[]{"Round"+partie,Player2Word,0});
                } 
                
                else if (word1found==true && word2found==true && Player1Word.length()<Player2Word.length()) {
                    //diplay score in table player 1
                    model1.addRow(new Object[]{"Round"+partie,Player1Word,0});
                   //diplay score in table player 2
                    model2.addRow(new Object[]{"Round"+partie,Player2Word,Player2Word.length()});
                }
                
                else if (word1found==true && word2found==true && Player1Word.length()== Player2Word.length()) {
                    //diplay score in table player 1
                    model1.addRow(new Object[]{"Round"+partie,Player1Word,Player1Word.length()});
                   //diplay score in table player 2
                    model2.addRow(new Object[]{"Round"+partie,Player2Word,Player2Word.length()});
                }
                
                
                //total
                scorePlayer1.setText(String.valueOf(Integer.parseInt(scorePlayer1.getText())+ (int) (ResumeTable1.getValueAt(partie-1, 2))));
                scorePlayer2.setText(String.valueOf(Integer.parseInt(scorePlayer2.getText())+ (int) (ResumeTable2.getValueAt(partie-1,2))));
                
               
                
                
                if(Integer.parseInt(scorePlayer1.getText()) != 10 && Integer.parseInt(scorePlayer2.getText()) != 10){partie++;Joueur1Play();}
                if(Integer.parseInt(scorePlayer1.getText()) >= 10) {WinnerName=home.getJoueur1_txt();this.setVisible(false);new end().setVisible(true);}
                if(Integer.parseInt(scorePlayer2.getText()) >= 10) {WinnerName=home.getJoueur2_txt();this.setVisible(false);new end().setVisible(true);}
                
                
            }
            catch (Exception e) {
                System.out.println(e);
            }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PanelGivenLettersJoueur1 = new javax.swing.JPanel();
        Player1GivenLetter12 = new javax.swing.JLabel();
        Player1GivenLetter11 = new javax.swing.JLabel();
        Player1GivenLetter10 = new javax.swing.JLabel();
        Player1GivenLetter9 = new javax.swing.JLabel();
        Player1GivenLetter8 = new javax.swing.JLabel();
        Player1GivenLetter7 = new javax.swing.JLabel();
        Player1GivenLetter6 = new javax.swing.JLabel();
        Player1GivenLetter5 = new javax.swing.JLabel();
        Player1GivenLetter4 = new javax.swing.JLabel();
        Player1GivenLetter3 = new javax.swing.JLabel();
        Player1GivenLetter2 = new javax.swing.JLabel();
        Player1GivenLetter1 = new javax.swing.JLabel();
        Player1TextFields = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        Player1Name = new javax.swing.JLabel();
        Player1Name1 = new javax.swing.JLabel();
        scorePlayer1 = new javax.swing.JTextField();
        ValiderBtn1 = new javax.swing.JButton();
        Annuler1 = new javax.swing.JButton();
        ValiderBtn2 = new javax.swing.JButton();
        Annuler2 = new javax.swing.JButton();
        Player2Name = new javax.swing.JLabel();
        Player1Name3 = new javax.swing.JLabel();
        scorePlayer2 = new javax.swing.JTextField();
        PanelGivenLettersJoueur2 = new javax.swing.JPanel();
        Player2GivenLetter1 = new javax.swing.JLabel();
        Player2GivenLetter2 = new javax.swing.JLabel();
        Player2GivenLetter3 = new javax.swing.JLabel();
        Player2GivenLetter4 = new javax.swing.JLabel();
        Player2GivenLetter5 = new javax.swing.JLabel();
        Player2GivenLetter6 = new javax.swing.JLabel();
        Player2GivenLetter7 = new javax.swing.JLabel();
        Player2GivenLetter8 = new javax.swing.JLabel();
        Player2GivenLetter9 = new javax.swing.JLabel();
        Player2GivenLetter10 = new javax.swing.JLabel();
        Player2GivenLetter11 = new javax.swing.JLabel();
        Player2GivenLetter12 = new javax.swing.JLabel();
        Player2TextFields = new javax.swing.JPanel();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        ResumeTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        ResumeTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Letters Game");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1671, 791, -1, -1));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1529, 862, -1, -1));

        PanelGivenLettersJoueur1.setBackground(new java.awt.Color(0, 0, 0));

        Player1GivenLetter12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter12.setOpaque(true);

        Player1GivenLetter11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter11.setOpaque(true);

        Player1GivenLetter10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter10.setOpaque(true);

        Player1GivenLetter9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter9.setOpaque(true);

        Player1GivenLetter8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter8.setOpaque(true);

        Player1GivenLetter7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter7.setOpaque(true);

        Player1GivenLetter6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter6.setOpaque(true);

        Player1GivenLetter5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter5.setOpaque(true);

        Player1GivenLetter4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter4.setOpaque(true);

        Player1GivenLetter3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter3.setOpaque(true);

        Player1GivenLetter2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter2.setOpaque(true);

        Player1GivenLetter1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1GivenLetter1.setOpaque(true);

        javax.swing.GroupLayout PanelGivenLettersJoueur1Layout = new javax.swing.GroupLayout(PanelGivenLettersJoueur1);
        PanelGivenLettersJoueur1.setLayout(PanelGivenLettersJoueur1Layout);
        PanelGivenLettersJoueur1Layout.setHorizontalGroup(
            PanelGivenLettersJoueur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGivenLettersJoueur1Layout.createSequentialGroup()
                .addComponent(Player1GivenLetter1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player1GivenLetter2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player1GivenLetter3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player1GivenLetter4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player1GivenLetter5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player1GivenLetter6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player1GivenLetter7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player1GivenLetter8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player1GivenLetter9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player1GivenLetter10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player1GivenLetter11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player1GivenLetter12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PanelGivenLettersJoueur1Layout.setVerticalGroup(
            PanelGivenLettersJoueur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGivenLettersJoueur1Layout.createSequentialGroup()
                .addGroup(PanelGivenLettersJoueur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Player1GivenLetter2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player1GivenLetter1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player1GivenLetter3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player1GivenLetter4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player1GivenLetter5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player1GivenLetter6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player1GivenLetter7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player1GivenLetter8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player1GivenLetter10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player1GivenLetter9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player1GivenLetter11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player1GivenLetter12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(PanelGivenLettersJoueur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, 30));

        Player1TextFields.setBackground(new java.awt.Color(0, 0, 0));

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout Player1TextFieldsLayout = new javax.swing.GroupLayout(Player1TextFields);
        Player1TextFields.setLayout(Player1TextFieldsLayout);
        Player1TextFieldsLayout.setHorizontalGroup(
            Player1TextFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player1TextFieldsLayout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Player1TextFieldsLayout.setVerticalGroup(
            Player1TextFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player1TextFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(Player1TextFields, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        Player1Name.setBackground(new java.awt.Color(0, 0, 0));
        Player1Name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Player1Name.setForeground(new java.awt.Color(204, 102, 255));
        Player1Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1Name.setText("Joueur 1");
        Player1Name.setToolTipText("");
        Player1Name.setOpaque(true);
        jPanel1.add(Player1Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 140, -1));

        Player1Name1.setBackground(new java.awt.Color(65, 6, 233));
        Player1Name1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Player1Name1.setForeground(new java.awt.Color(255, 255, 255));
        Player1Name1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1Name1.setText("Score");
        Player1Name1.setOpaque(true);
        jPanel1.add(Player1Name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 100, 20));

        scorePlayer1.setEditable(false);
        scorePlayer1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        scorePlayer1.setText("0");
        scorePlayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scorePlayer1ActionPerformed(evt);
            }
        });
        jPanel1.add(scorePlayer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 50, 40));

        ValiderBtn1.setOpaque(false);
        ValiderBtn1.setContentAreaFilled(false);
        ValiderBtn1.setBorderPainted(false);
        ValiderBtn1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ValiderBtn1.setForeground(new java.awt.Color(255, 255, 255));
        ValiderBtn1.setText("Valider");
        ValiderBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValiderBtn1ActionPerformed(evt);
            }
        });
        jPanel1.add(ValiderBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 485, 190, 60));

        Annuler1.setOpaque(false);
        Annuler1.setContentAreaFilled(false);
        Annuler1.setBorderPainted(false);
        Annuler1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Annuler1.setForeground(new java.awt.Color(255, 255, 255));
        Annuler1.setText("Annuler");
        Annuler1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Annuler1ActionPerformed(evt);
            }
        });
        jPanel1.add(Annuler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 550, 190, 60));

        ValiderBtn2.setOpaque(false);
        ValiderBtn2.setContentAreaFilled(false);
        ValiderBtn2.setBorderPainted(false);
        ValiderBtn2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ValiderBtn2.setForeground(new java.awt.Color(255, 255, 255));
        ValiderBtn2.setText("Valider");
        ValiderBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValiderBtn2ActionPerformed(evt);
            }
        });
        jPanel1.add(ValiderBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 490, 190, 50));

        Annuler2.setOpaque(false);
        Annuler2.setContentAreaFilled(false);
        Annuler2.setBorderPainted(false);
        Annuler2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Annuler2.setForeground(new java.awt.Color(255, 255, 255));
        Annuler2.setText("Annuler");
        Annuler2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Annuler2ActionPerformed(evt);
            }
        });
        jPanel1.add(Annuler2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 550, 190, 50));

        Player2Name.setBackground(new java.awt.Color(0, 0, 0));
        Player2Name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Player2Name.setForeground(new java.awt.Color(204, 102, 255));
        Player2Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2Name.setText("Joueur 2");
        Player2Name.setOpaque(true);
        jPanel1.add(Player2Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(944, 70, 150, -1));

        Player1Name3.setBackground(new java.awt.Color(65, 6, 233));
        Player1Name3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Player1Name3.setForeground(new java.awt.Color(255, 255, 255));
        Player1Name3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1Name3.setText("Score");
        Player1Name3.setOpaque(true);
        jPanel1.add(Player1Name3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 110, 20));

        scorePlayer2.setEditable(false);
        scorePlayer2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        scorePlayer2.setText("0");
        scorePlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scorePlayer2ActionPerformed(evt);
            }
        });
        jPanel1.add(scorePlayer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 50, 50, 40));

        PanelGivenLettersJoueur2.setBackground(new java.awt.Color(0, 0, 0));

        Player2GivenLetter1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter1.setOpaque(true);

        Player2GivenLetter2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter2.setOpaque(true);

        Player2GivenLetter3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter3.setOpaque(true);

        Player2GivenLetter4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter4.setOpaque(true);

        Player2GivenLetter5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter5.setOpaque(true);

        Player2GivenLetter6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter6.setOpaque(true);

        Player2GivenLetter7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter7.setOpaque(true);

        Player2GivenLetter8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter8.setOpaque(true);

        Player2GivenLetter9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter9.setOpaque(true);

        Player2GivenLetter10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter10.setOpaque(true);

        Player2GivenLetter11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter11.setOpaque(true);

        Player2GivenLetter12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2GivenLetter12.setOpaque(true);

        javax.swing.GroupLayout PanelGivenLettersJoueur2Layout = new javax.swing.GroupLayout(PanelGivenLettersJoueur2);
        PanelGivenLettersJoueur2.setLayout(PanelGivenLettersJoueur2Layout);
        PanelGivenLettersJoueur2Layout.setHorizontalGroup(
            PanelGivenLettersJoueur2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGivenLettersJoueur2Layout.createSequentialGroup()
                .addComponent(Player2GivenLetter1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player2GivenLetter2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player2GivenLetter3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player2GivenLetter4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player2GivenLetter5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player2GivenLetter6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player2GivenLetter7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player2GivenLetter8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player2GivenLetter9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player2GivenLetter10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player2GivenLetter11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Player2GivenLetter12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelGivenLettersJoueur2Layout.setVerticalGroup(
            PanelGivenLettersJoueur2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Player2GivenLetter2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Player2GivenLetter1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Player2GivenLetter3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Player2GivenLetter4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Player2GivenLetter5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Player2GivenLetter6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Player2GivenLetter7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Player2GivenLetter8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Player2GivenLetter10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Player2GivenLetter9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Player2GivenLetter11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Player2GivenLetter12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.add(PanelGivenLettersJoueur2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, -1, -1));

        Player2TextFields.setBackground(new java.awt.Color(0, 0, 0));

        jTextField16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField23.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField24.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField25.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField26.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField27ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Player2TextFieldsLayout = new javax.swing.GroupLayout(Player2TextFields);
        Player2TextFields.setLayout(Player2TextFieldsLayout);
        Player2TextFieldsLayout.setHorizontalGroup(
            Player2TextFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player2TextFieldsLayout.createSequentialGroup()
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Player2TextFieldsLayout.setVerticalGroup(
            Player2TextFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player2TextFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(Player2TextFields, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, -1, -1));

        ResumeTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Round", "Word", "Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ResumeTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        ResumeTable1.setEnabled(false);
        jScrollPane2.setViewportView(ResumeTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 230, 210));

        ResumeTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Round", "Word", "Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ResumeTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(ResumeTable2);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 440, 230, 210));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/letters_game/images/play_bg.png"))); // NOI18N
        jLabel3.setAutoscrolls(true);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void scorePlayer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scorePlayer1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scorePlayer1ActionPerformed

    private void ValiderBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValiderBtn1ActionPerformed

        Player1Word="";
        // disable player 1 buttons
        ValiderBtn1.setEnabled(false);
        Annuler1.setEnabled(false);

        // read player 1 word
        Component[] comp1 = Player1TextFields.getComponents();
        for (int i = 0;i<comp1.length;i++) {
            Player1Word = Player1Word + ((JTextField)comp1[i]).getText();
            //clear textfield to prevent cheating
            ((JTextField)comp1[i]).setText("");
            ((JTextField)comp1[i]).setEnabled(false);
        }
        Player1Word.trim();
        System.out.println("Player1Word : "+Player1Word);

        //move to player 2 turn
        Joueur2Play();

    }//GEN-LAST:event_ValiderBtn1ActionPerformed

    private void Annuler1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Annuler1ActionPerformed
        Component[] comp1 = Player1TextFields.getComponents();
        for (int i = 0;i<comp1.length;i++) {
            ((JTextField)comp1[i]).setText("");
        }
        GivenLetters1 = GivenLetters ;
        GetKeyPressedLetter1();
    }//GEN-LAST:event_Annuler1ActionPerformed

    private void ValiderBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValiderBtn2ActionPerformed
        Player2Word ="";
        // disable player 2 buttons
        ValiderBtn2.setEnabled(false);
        Annuler2.setEnabled(false);

        // read player 2 word
        Component[] comp2 = Player2TextFields.getComponents();
        for (int i = 0;i<comp2.length;i++) {
            Player2Word = Player2Word + ((JTextField)comp2[i]).getText();
            //clear textfield to prevent cheating
            ((JTextField)comp2[i]).setText("");
            ((JTextField)comp2[i]).setEnabled(false);
        }
        Player2Word.trim();

        System.out.println("Player2Word : "+Player2Word);

        //Verify Score
        VerifyScore();
    }//GEN-LAST:event_ValiderBtn2ActionPerformed

    private void Annuler2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Annuler2ActionPerformed
        Component[] comp2 = Player2TextFields.getComponents();
        for (int i = 0;i<comp2.length;i++) {
            ((JTextField)comp2[i]).setText("");
        }
        GivenLetters2 = GivenLetters ;
        GetKeyPressedLetter2();
    }//GEN-LAST:event_Annuler2ActionPerformed

    private void scorePlayer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scorePlayer2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scorePlayer2ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annuler1;
    private javax.swing.JButton Annuler2;
    private javax.swing.JPanel PanelGivenLettersJoueur1;
    private javax.swing.JPanel PanelGivenLettersJoueur2;
    private javax.swing.JLabel Player1GivenLetter1;
    private javax.swing.JLabel Player1GivenLetter10;
    private javax.swing.JLabel Player1GivenLetter11;
    private javax.swing.JLabel Player1GivenLetter12;
    private javax.swing.JLabel Player1GivenLetter2;
    private javax.swing.JLabel Player1GivenLetter3;
    private javax.swing.JLabel Player1GivenLetter4;
    private javax.swing.JLabel Player1GivenLetter5;
    private javax.swing.JLabel Player1GivenLetter6;
    private javax.swing.JLabel Player1GivenLetter7;
    private javax.swing.JLabel Player1GivenLetter8;
    private javax.swing.JLabel Player1GivenLetter9;
    private javax.swing.JLabel Player1Name;
    private javax.swing.JLabel Player1Name1;
    private javax.swing.JLabel Player1Name3;
    private javax.swing.JPanel Player1TextFields;
    private javax.swing.JLabel Player2GivenLetter1;
    private javax.swing.JLabel Player2GivenLetter10;
    private javax.swing.JLabel Player2GivenLetter11;
    private javax.swing.JLabel Player2GivenLetter12;
    private javax.swing.JLabel Player2GivenLetter2;
    private javax.swing.JLabel Player2GivenLetter3;
    private javax.swing.JLabel Player2GivenLetter4;
    private javax.swing.JLabel Player2GivenLetter5;
    private javax.swing.JLabel Player2GivenLetter6;
    private javax.swing.JLabel Player2GivenLetter7;
    private javax.swing.JLabel Player2GivenLetter8;
    private javax.swing.JLabel Player2GivenLetter9;
    private javax.swing.JLabel Player2Name;
    private javax.swing.JPanel Player2TextFields;
    private javax.swing.JTable ResumeTable1;
    private javax.swing.JTable ResumeTable2;
    private javax.swing.JButton ValiderBtn1;
    private javax.swing.JButton ValiderBtn2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField scorePlayer1;
    private javax.swing.JTextField scorePlayer2;
    // End of variables declaration//GEN-END:variables
}
