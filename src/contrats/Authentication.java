/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;

import com.sun.glass.events.KeyEvent;
import com.sun.prism.paint.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
/**
 *
 * @author h
 */
public class Authentication extends javax.swing.JFrame{
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private RoundedJTextField jTextField_username;
    private  RoundedJPasswordField jTextField_password;
    //private javax.swing.JButton jButton_authenticate;
    RoundedJButton jButton_authenticate;
    private Populate populate;
    public static void main(String[] args) {
        // TODO code application logic here
          
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                //Welcome welcome =new Welcome();
                new Authentication().setVisible(true);
            }
        });
    }
    public Authentication (){
        populate = new Populate();
        Adapter adapter = new Adapter();
        jLabel1 =new JLabel();
        jLabel1.setText("Identifiant");
        jLabel1.setFont(new Font("Century Gothic", Font.BOLD, 15));
        jLabel2 =new JLabel();
        jLabel2.setText("Mot de passe");
        jLabel2.setFont(new Font("Century Gothic", Font.BOLD, 15));
        jTextField_username = new RoundedJTextField(20,"circle");
        //jTextField_username.setBackground(new java.awt.Color(0,0,0,1));
        jTextField_username.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        jTextField_username.addKeyListener(adapter);
        jTextField_password = new RoundedJPasswordField (20,"circle");
        jTextField_password.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        //jTextField_password.setEchoChar((char)0);
         
        //jTextField_password.setEchoChar('.');
        jTextField_password.addKeyListener(adapter);
        //jTextField_password.setBackground(new java.awt.Color(0,0,0,1));
        jButton_authenticate = new RoundedJButton("Connexion");
        jButton_authenticate.setFont(new Font("Century Gothic", Font.BOLD, 15));
        //jButton_authenticate.registerKeyboardAction(jButton_authenticate.getActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false)), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false), JComponent.WHEN_FOCUSED);
        jButton_authenticate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(jTextField_username.getText().toString().equals("") || jTextField_password.getText().toString().equals("")){
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
                }
                else
                Login();
            }
        });
        jButton_authenticate.addKeyListener(adapter);
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel text_panel = new JPanel(new GridBagLayout());
        //Background panel = new Background(new GridBagLayout(),"src\\Contrats\\Images\\EA1.jpg",500,500);
        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                  30, 0, 0, 0), 0, 0);
        GridBagConstraints gbc2 = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                  100, 0, 0, 0), 0, 0);
        GridBagConstraints text_gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                  10, 0, 0, 0), 0, 0);
        panel.add(jLabel1,text_gbc);
        text_gbc.gridx = 1;
        text_gbc.gridy = 0;
        panel.add(jTextField_username,text_gbc);
        text_gbc.gridx = 0;
        text_gbc.gridy = 1;
        panel.add(jLabel2,text_gbc);
        text_gbc.gridx = 1;
        text_gbc.gridy = 1;
        panel.add(jTextField_password,text_gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        //gbc.anchor =GridBagConstraints.CENTER;
        panel.add(jButton_authenticate,gbc);
        text_panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.setOpaque(false);
        Background background = new Background("500X500D.jpg",500,500);
        background.setLayout(new GridBagLayout());
        background.add(panel,gbc2);
        
        //this.getContentPane().setLayout(new GridBagLayout());
        this.getContentPane().add(background);
        
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        this.addKeyListener(adapter);
        //setFocusable(true);
        ImageIcon img = new ImageIcon(getClass().getResource("LOGOEA.png"));
        this.setIconImage(img.getImage());
        //setFocusTraversalKeysEnabled(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon login = new ImageIcon(getClass().getResource("login.png"));
        int width = jButton_authenticate.getWidth();
        int height = jButton_authenticate.getHeight();
        //System.out.println(width);
        //System.out.println(height);
        Image l = login.getImage();
        Image newImage = l.getScaledInstance(25, 20,java.awt.Image.SCALE_SMOOTH);  
        login = new ImageIcon(newImage);
        //jButton_authenticate.setIcon(login);
        //jButton_authenticate.setBorder(new OvalBorder(10,10));
        //jButton_authenticate.setBorder(new RoundedBorder(5));
        jButton_authenticate.setBackground(java.awt.Color.ORANGE);
        jButton_authenticate.setSize(100, 100);
        jButton_authenticate.setFocusable(false);

        //jButton_authenticate.setOpaque(false);
        //jButton_authenticate.setContentAreaFilled(false);
        //jButton_authenticate.setFocusPainted(false);
    }
    public class Adapter extends KeyAdapter{
        @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                 if (e.getKeyCode()==KeyEvent.VK_ENTER){
                     if(jTextField_username.getText().toString().equals("") || jTextField_password.getText().toString().equals("")){
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
                }
                else
                Login();
                 }
            }
    }
    public void Login(){
        Connection con = populate.getConnection();
        String query = "";
        ;
        query = "SELECT * FROM  user WHERE account = '"+jTextField_username.getText().toString()+"' AND password = '"+jTextField_password.getText().toString()+"' AND status = 1";
        //System.out.println(jTextField_username.getDocument().getLength());
       Statement st;
       ResultSet rs;
       try{
           st = con.createStatement();
           rs = st.executeQuery(query);
           if(rs.next() == true)
           {
               JOptionPane.showMessageDialog(null, "Connection réussie");
               dispose();
               Contrats contrat = new Contrats();
               contrat.getUsername(jTextField_username.getText().toString());
               
           }else{
               JOptionPane.showMessageDialog(null, "Mot de passe ou compte utilisateur incorrect");
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
    }
}
