/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;


/**
 *
 * @author HP
 */
public class Close_contract extends javax.swing.JFrame{
    private javax.swing.JTable jTable_Display_Employee;
    private javax.swing.JScrollPane scrollPane;
    private JPanel panel, panel_modifier;
    private GridBagConstraints gbc, gbc_modifier;
    private JMenuBar menuBar;
    private JMenu fileMenu, archive_menu;
    private JMenuItem pdf, archive;
    private int ID,remove;
    private ArrayList<Integer> IDList;
    private ArrayList<File> work_certificate_list,account_balance_list,nominative_statement_list;
    private File file_database;
    private ArrayList<File> file_list;
    private JButton work_certificate,account_balance, nominative_statement, save;
    private InputStream work_certificate_file,account_balance_file,nominative_statement_file;
    private JLabel label, jLabel1,jLabel2,jLabel3, jLabel4,employee, work_certificate_label, account_balance_label, nominative_statement_label, wc_name,ab_name,ns_name;
    private String user;
    private Populate populate;
    private Background background;
    public Close_contract(){
        //Read_pdf();
        populate = new Populate();
        work_certificate_file =null;
        account_balance_file =null;
        nominative_statement_file = null;        
        IDList = new ArrayList<Integer>();
        work_certificate_list = new ArrayList<File>();
        account_balance_list = new ArrayList<File>();
        nominative_statement_list = new ArrayList<File>();
        file_list = new ArrayList<File>();
        
        jTable_Display_Employee = new javax.swing.JTable();
         jTable_Display_Employee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricule","Numéro CNPS","Nom", "Prénoms","Fin de contrat"
            }
        ));
         
         jTable_Display_Employee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_EmployeeMouseClicked(evt) ;
            }
        });
        
        Show_Employee_In_JTable();
        
        
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Fichier");
        pdf = new JMenuItem("Enregistrer les documents pdf");
        pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dispose();
                jLabel1.setVisible(true);
                employee.setVisible(true);
                jLabel2.setVisible(true);
                work_certificate.setVisible(true);
                work_certificate_label.setVisible(false);
                jLabel3.setVisible(true);
                account_balance.setVisible(true);
                account_balance_label.setVisible(false);
                jLabel4.setVisible(true);
                nominative_statement.setVisible(true);
                nominative_statement_label.setVisible(false);
                save.setVisible(true); 
                wc_name.setVisible(true);
                ab_name.setVisible(true);
                ns_name.setVisible(true);
            }
        });
        fileMenu.add(pdf);
        menuBar.add(fileMenu);
        archive_menu = new JMenu("Archives");
        archive=  new JMenuItem("Archiver");
        archive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Archive();
            }
        });
        archive_menu.add(archive);
        menuBar.add(archive_menu);
        jLabel1 = new JLabel("Employé sélectionné:");
        jLabel1.setVisible(false);
        employee = new JLabel();
        employee.setVisible(false);
        jLabel2 = new JLabel("Certificat de travail:");
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setVisible(false);
        work_certificate_label = new JLabel();
        work_certificate_label.setForeground(Color.WHITE);
        work_certificate_label.setVisible(false);
        wc_name = new JLabel();
        wc_name.setForeground(Color.WHITE);
        wc_name.setVisible(false);
        ab_name= new JLabel();
        ab_name.setForeground(Color.WHITE);
        ab_name.setVisible(false);
        ns_name = new JLabel();
        ns_name.setForeground(Color.WHITE);
        ns_name.setVisible(false);
        work_certificate = new RoundedJButton("choisir un fichier");
        work_certificate.setBackground(new java.awt.Color(204,204,204));
        work_certificate.setVisible(false);
        work_certificate.setFocusable(false);
        work_certificate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){  
                JFileChooser c = new JFileChooser();
                int rVal = c.showOpenDialog(Close_contract.this);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                    File file = new File("");
                    file = c.getSelectedFile();
                    wc_name.setText(file.getName());
                    try {
                        work_certificate_file = new FileInputStream(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Close_contract.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }      
         }}); 
        jLabel3 = new JLabel("Solde de tout compte:");
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setVisible(false);
        account_balance_label = new JLabel();
        account_balance_label.setForeground(Color.WHITE);
        account_balance_label.setVisible(false);
        account_balance = new RoundedJButton("Choisir un fichier");
        account_balance.setBackground(new java.awt.Color(204,204,204));
        account_balance.setFocusable(false);
        account_balance.setVisible(false);
        account_balance.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){  
                JFileChooser c = new JFileChooser();
                int rVal = c.showOpenDialog(Close_contract.this);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                    File file = new File("");
                    file = c.getSelectedFile();
                    ab_name.setText(file.getName());
                    try {
                        account_balance_file = new FileInputStream(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Close_contract.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }      
         }});
        jLabel4 = new JLabel("Relevé nominatif:");
        jLabel4.setForeground(Color.WHITE);
        jLabel4.setVisible(false);
        nominative_statement_label = new JLabel();
        nominative_statement_label.setForeground(Color.WHITE);
        nominative_statement_label.setVisible(false);
        nominative_statement = new RoundedJButton("choisir un fichier");
        nominative_statement.setBackground(new java.awt.Color(204,204,204));
        nominative_statement.setFocusable(false);
        nominative_statement.setVisible(false);
        nominative_statement.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){  
                JFileChooser c = new JFileChooser();
                int rVal = c.showOpenDialog(Close_contract.this);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                    File file = new File("");
                    file = c.getSelectedFile();
                    ns_name.setText(file.getName());
                    try {
                        nominative_statement_file = new FileInputStream(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Close_contract.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }      
         }});
        save = new RoundedJButton("Enregistrer");
        save.setBackground(Color.ORANGE);
        save.setFocusable(false);
        save.setVisible(false);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Insert_pdf();  
                Show_Employee_In_JTable(); 
                wc_name.setText("");
                ab_name.setText("");
                ns_name.setText("");
                work_certificate_file = null;
                account_balance_file =null;
                nominative_statement_file =null;
            }
        });
        jTable_Display_Employee.setForeground(Color.WHITE);
        jTable_Display_Employee.setOpaque(false);
        jTable_Display_Employee.setBackground(new java.awt.Color(0,0,153));
        jTable_Display_Employee.setSelectionBackground(Color.YELLOW);
        jTable_Display_Employee.setFocusable(false);
        scrollPane = new JScrollPane(jTable_Display_Employee);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        scrollPane.setOpaque(false);
        //scrollPane.setBackground(Color.YELLOW);
        scrollPane.getViewport().setBackground(new java.awt.Color(0,0,153));
        background = new Background("E8.jpg",2000,2000); 
        background.setLayout(new GridBagLayout());
        panel_modifier = new JPanel(new GridBagLayout());
        gbc_modifier = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
                 40, 30, 0, 0), 0, 0);
        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
                  0, 0, 0, 0), 0, 0);
        panel_modifier.setOpaque(false);
        panel_modifier.add(jLabel1, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 0;
        panel_modifier.add(employee, gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 1;
        panel_modifier.add(jLabel2, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 1;
        panel_modifier.add(work_certificate_label, gbc_modifier);
        panel_modifier.add(work_certificate, gbc_modifier);
        gbc_modifier.gridx = 2;
        gbc_modifier.gridy = 1;
        panel_modifier.add(wc_name, gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 2;
        panel_modifier.add(jLabel3, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 2;
        panel_modifier.add(account_balance_label, gbc_modifier);
        panel_modifier.add(account_balance, gbc_modifier);
        gbc_modifier.gridx = 2;
        gbc_modifier.gridy = 2;
        panel_modifier.add(ab_name, gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 3;
        panel_modifier.add(jLabel4, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 3;
        panel_modifier.add(nominative_statement_label, gbc_modifier);
        panel_modifier.add(nominative_statement, gbc_modifier);
        gbc_modifier.gridx = 2;
        gbc_modifier.gridy = 3;
        panel_modifier.add(ns_name, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 4;
        panel_modifier.add(save, gbc_modifier);
        //panel.add(panel_modifier, gbc);
        background.add(panel_modifier,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        //panel.add(scrollPane,gbc);
        background.add(scrollPane,gbc);
        ImageIcon img = new ImageIcon(getClass().getResource("LOGOEA.png"));
        this.setIconImage(img.getImage());
        this.setResizable(false);
        this.getContentPane().add(background);
        this.setJMenuBar(menuBar);
        this.setSize(2000, 2000);
        this.setVisible(true);
        this.setFocusable(true);
        Adapter adapter = new Adapter();
        this.addKeyListener(adapter);
    }
    public class Adapter extends KeyAdapter{
        @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                 if (e.getKeyCode()==KeyEvent.VK_ENTER){
                Insert_pdf();  
                Show_Employee_In_JTable(); 
                wc_name.setText("");
                ab_name.setText("");
                ns_name.setText("");
                work_certificate_file = null;
                account_balance_file =null;
                nominative_statement_file =null;                  
                 }
            }
    }
    public static void main(String[] args) {
        // TODO code application logic here
          try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Contrats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contrats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contrats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contrats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Close_contract().setVisible(true);
            }
        });
        
    }
 
   public ArrayList<employee> getEmployeeList()
   {
       ArrayList<employee> employeeList = new ArrayList<employee>();
       Connection connection = populate.getConnection();
       
       String query = "SELECT ID_employee,registration_number,cnps,surname,name,DATE_ADD(entry_date, INTERVAL length MONTH) end_date, work_certificate,account_balance,nominative_statement FROM employee WHERE (TO_DAYS(DATE_ADD(entry_date, INTERVAL length MONTH))  < TO_DAYS(CURDATE())) AND status = 1";
       Statement st;
       ResultSet rs;         
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           employee employee;
           IDList.clear();
           work_certificate_list.clear();
           nominative_statement_list.clear();
           account_balance_list.clear();
           //file_list.clear();
           //int i = 0;
           while(rs.next())
           { 
               
               //label =new JLabel("Aucun Fichier");
               file_database = new File("");
               if(rs.getBlob("work_certificate") != null){
                //File file = (File) rs.getBlob("work_certificate");
                 file_database = new File("Certificat de travail "+rs.getString("surname")+" "+rs.getString("name")+".pdf");
                /*String name = file.getName();
                String name_pdf = name.substring(0, name.length() - 3);
                name_pdf+="pdf";*/
                //file.renameTo(file2);
                Blob blob = rs.getBlob("work_certificate");
                InputStream in = blob.getBinaryStream();
                OutputStream out = new FileOutputStream(file_database);
                byte[] buff = blob.getBytes(1,(int)blob.length());
                out.write(buff);
                out.close();
                /*label.setText("<HTML><U>fichier</U></HTML>");
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    try {
                    Desktop.getDesktop().open(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    }
                }); */
                //file_list.add(i, file);
                //i++;
               }
               
               work_certificate_list.add(file_database);
               
               //label =new JLabel("Aucun Fichier");
              file_database = new File("");
               if(rs.getBlob("account_balance") != null){
                //File file = (File) rs.getBlob("work_certificate");
                file_database = new File("Solde de tout compte "+rs.getString("surname")+" "+rs.getString("name")+".pdf");
                /*String name = file.getName();
                String name_pdf = name.substring(0, name.length() - 3);
                name_pdf+="pdf";*/
                //file.renameTo(file2);
                Blob blob = rs.getBlob("account_balance");
                InputStream in = blob.getBinaryStream();
                OutputStream out = new FileOutputStream(file_database);
                byte[] buff = blob.getBytes(1,(int)blob.length());
                out.write(buff);
                out.close();
                /*label.setText("<HTML><U>fichier</U></HTML>");
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    try {
                    Desktop.getDesktop().open(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    }
                }); */
                //file_list.add(i, file);
                //i++;
               }               
               account_balance_list.add(file_database);
               //label =new JLabel("Aucun Fichier");
                file_database = new File("");
               if(rs.getBlob("nominative_statement") != null){
                //File file = (File) rs.getBlob("work_certificate");
                file_database = new File("Relevé nominatif "+rs.getString("surname")+" "+rs.getString("name")+".pdf");
                /*String name = file.getName();
                String name_pdf = name.substring(0, name.length() - 3);
                name_pdf+="pdf";*/
                //file.renameTo(file2);
                Blob blob = rs.getBlob("nominative_statement");
                InputStream in = blob.getBinaryStream();
                OutputStream out = new FileOutputStream(file_database);
                byte[] buff = blob.getBytes(1,(int)blob.length());
                out.write(buff);
                out.close();
                /*label.setText("<HTML><U>fichier</U></HTML>");
                label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    try {
                    Desktop.getDesktop().open(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    }
                });*/ 
                //file_list.add(i, file);
                //i++;
               }               
               nominative_statement_list.add(file_database);
               IDList.add(rs.getInt("ID_employee"));
               employee = new employee(rs.getString("registration_number"),rs.getString("cnps"),rs.getString("surname"),rs.getString("name"),rs.getString("end_date"),"close_contract");
               employeeList.add(employee);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return employeeList;
   }
    public void Show_Employee_In_JTable()
   {
       ArrayList<employee> list = getEmployeeList();
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Employee.getModel();
       model.setRowCount(0);
       Object[] row = new Object[5];
       for(int i = 0; i < list.size(); i++)
       {  
           row[0] = list.get(i).getRegistration_number();
           row[1] = list.get(i).getCnps();
           row[2] = list.get(i).getSurname();
           row[3] = list.get(i).getName();      
           row[4] = list.get(i).getEnd_date(); 
           //jTable_Display_Employee.getColumnModel().getColumn(5).setCellRenderer(new Renderer(fileList));
           model.addRow(row);
       }
    } 
    private void jTable_Display_EmployeeMouseClicked(java.awt.event.MouseEvent evt) { 
        panel_modifier.remove(work_certificate_label);
        panel_modifier.remove(account_balance_label);
        panel_modifier.remove(nominative_statement_label);
        panel_modifier.revalidate();
        panel_modifier.repaint();
        work_certificate_label = new JLabel();
        work_certificate_label.setForeground(Color.WHITE);
        account_balance_label= new JLabel();
        account_balance_label.setForeground(Color.WHITE);
        nominative_statement_label =new JLabel();
        nominative_statement_label.setForeground(Color.WHITE);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 1;
        panel_modifier.add(work_certificate_label, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 2;
        panel_modifier.add(account_balance_label, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 3;
        panel_modifier.add(nominative_statement_label, gbc_modifier);
        gbc.gridx = 0;
        gbc.gridy = 0;
        background.add(panel_modifier, gbc);
        int i = jTable_Display_Employee.getSelectedRow();
        ID = IDList.get(i);
        TableModel model = jTable_Display_Employee.getModel();
        jLabel1.setVisible(true);
        employee.setVisible(true);
        jLabel2.setVisible(true);
        work_certificate.setVisible(false);
        work_certificate_label.setVisible(true);
        jLabel3.setVisible(true);
        account_balance.setVisible(false);
        account_balance_label.setVisible(true);
        jLabel4.setVisible(true);
        nominative_statement.setVisible(false);
        nominative_statement_label.setVisible(true);
        save.setVisible(false);
        wc_name.setVisible(false);
        ab_name.setVisible(false);
        ns_name.setVisible(false);
        //work_certificate_list.get(remove).setVisible(false);
        //account_balance_list.get(remove).setVisible(false);
        //nominative_statement_list.get(remove).setVisible(false);
        employee.setText(model.getValueAt(i,0).toString()+ " "+ model.getValueAt(i,2).toString()+" "+model.getValueAt(i,3).toString());
        if(work_certificate_list.get(i).getName() == ""){
            work_certificate_label.setText("Aucun fichier");            
        }
        else{
            //work_certificate_list.get(i).setVisible(true);
            //work_certificate_label.setVisible(false);
            //remove=i; 
            work_certificate_label.setText("<HTML><U>"+work_certificate_list.get(i).getName()+"</U></HTML>");
            work_certificate_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            work_certificate_label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    try {
                    Desktop.getDesktop().open(work_certificate_list.get(i));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    }
                });
        }
        if(account_balance_list.get(i).getName() == ""){
            account_balance_label.setText("Aucun fichier");
            
        }
        else{
            //account_balance_list.get(i).setVisible(true);
            //remove=i;  
            account_balance_label.setText("<HTML><U>"+account_balance_list.get(i).getName()+"</U></HTML>");
            account_balance_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            account_balance_label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    try {
                    Desktop.getDesktop().open(account_balance_list.get(i));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    }
                });
        }
        if(nominative_statement_list.get(i).getName() == ""){
            nominative_statement_label.setText("Aucun fichier");
            
        }
        else{
            //nominative_statement_list.get(i).setVisible(true);
            //remove=i;
            nominative_statement_label.setText("<HTML><U>"+nominative_statement_list.get(i).getName()+"</U></HTML>");
            nominative_statement_label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            nominative_statement_label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    try {
                    Desktop.getDesktop().open(nominative_statement_list.get(i));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    }
                });
        }
    }
    public void Insert_pdf(){
        Connection connection = populate.getConnection();
        PreparedStatement ps;
        String query = "UPDATE employee SET work_certificate = ?, account_balance = ?, nominative_statement = ? where ID_employee = "+ID;
        try {
           //st = connection.createStatement(); 
            ps = connection.prepareStatement(query);
            ps.setBlob(1, work_certificate_file);
            ps.setBlob(2, account_balance_file);
            ps.setBlob(3, nominative_statement_file);
            ps.executeUpdate();
           /*if(work_certificate.isVisible())
           st.se(query_wc);
           if(account_balance.isVisible())
           st.executeUpdate(query_ab);
           if(nominative_statement.isVisible())
           st.executeUpdate(query_ns);*/
           JOptionPane.showMessageDialog(null, "Document(s) sauvegardé(s) avec succès");
        }
        catch (Exception e) {
           e.printStackTrace();
       }
    }
    public void Read_pdf(){
        Connection connection = populate.getConnection();
        Statement st;
        ResultSet rs;
        String query = "SELECT work_certificate FROM employee";
        try {
           st = connection.createStatement(); 
           rs = st.executeQuery(query);
           
           while (rs.next()) {
               if(rs.getBlob("work_certificate") != null){
                Blob blob = rs.getBlob("work_certificate");
                InputStream inputStream = blob.getBinaryStream();
                Files.copy(inputStream, Paths.get("C:\\fichiers/pdf.pdf"));
               }
           }
        }
        catch (Exception e) {
           e.printStackTrace();
       }
    }
    public void Archive(){
        Connection connection = populate.getConnection();
        Statement st;
        ResultSet rs;
        String query = "INSERT INTO archive(registration_number, surname, name, end_date,ID_contract,work_certificate, account_balance, nominative_statement, year, status,date_created, user_date_created, date_modified, user_date_modified) VALUES ((SELECT registration_number FROM employee WHERE employee.ID_employee = '"+ID+"'),(SELECT surname FROM employee WHERE employee.ID_employee = '"+ID+"'), (SELECT name FROM employee WHERE employee.ID_employee = '"+ID+"'), (SELECT DATE_ADD(entry_date, INTERVAL length MONTH) FROM employee WHERE employee.ID_employee = '"+ID+"'), (SELECT employee.ID_contract FROM employee WHERE employee.ID_employee = '"+ID+"'), (SELECT employee.work_certificate FROM employee WHERE employee.ID_employee = '"+ID+"'), (SELECT employee.account_balance FROM employee WHERE employee.ID_employee = '"+ID+"'), (SELECT employee.nominative_statement FROM employee WHERE employee.ID_employee = '"+ID+"'), (SELECT year(DATE_ADD(entry_date, INTERVAL length MONTH)) FROM employee WHERE employee.ID_employee = '"+ID+"'),1,NOW(),'"+user+"',NOW(),'"+user+"')";
        try {
           st = connection.createStatement(); 
           st.executeUpdate(query);
           JOptionPane.showMessageDialog(null, "Employé archivé avec succès");
         }
        catch (Exception e) {
           e.printStackTrace();
       }
    }
    public void getUsername(String user){
        this.user = user;
    }
}
