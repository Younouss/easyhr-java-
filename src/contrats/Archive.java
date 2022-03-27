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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author h
 */
public class Archive extends javax.swing.JFrame {
    private javax.swing.JTable jTable_Display_Employee;
    private javax.swing.JScrollPane scrollPane;
    private JPanel panel, panel_modifier;
    private GridBagConstraints gbc, gbc_modifier;
    private JLabel jLabel1,jLabel2,jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8,jLabel9,jLabel10,work_certificate_label, account_balance_label, nominative_statement_label;
    private JTextField year;
    private JButton enter,search_button;
    private ArrayList<Integer> IDList;
    private ArrayList<File> work_certificate_list,account_balance_list,nominative_statement_list;
    private File file_database;
    private int ID;
    private JTextField registration_number, name, surname;
    private javax.swing.JComboBox jComboBox_contract;
    private javax.swing.JComboBox <String>jComboBox_internship;
    private JDatePickerImpl end_date;
    private JMenuBar menuBar;
    private String year_string;
    private Populate populate;
    JMenu employeeMenu,search,dataMenu;
    JMenuItem delete,update, search_surname_name_rn,search_end_date, search_contract,export_data;
    Boolean boolean_search_surname_name_rn, boolean_search_end_date, boolean_search_contract;
    Background background;
    public Archive(){
        populate = new Populate();
        boolean_search_surname_name_rn =false;
        boolean_search_end_date =false;
        IDList = new ArrayList<Integer>();
        work_certificate_list = new ArrayList<File>();
        account_balance_list = new ArrayList<File>();
        nominative_statement_list = new ArrayList<File>();
        jTable_Display_Employee = new javax.swing.JTable();
        jTable_Display_Employee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricule","Nom", "Prénoms","Fin de contrat","Type de contrat"
            }
        ));
        jTable_Display_Employee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_EmployeeMouseClicked(evt) ;
            }
        });
        //Show_Employee_In_JTable();
        menuBar = new JMenuBar();
        employeeMenu = new JMenu("Employé");
        delete = new JMenuItem("Supprimer");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
                jLabel1.setVisible(false);
                work_certificate_label.setVisible(false);
                jLabel2.setVisible(false);
                account_balance_label.setVisible(false);
                jLabel3.setVisible(false);
                nominative_statement_label.setVisible(false);
                jLabel4.setVisible(true);
                year.setVisible(true);
                jLabel5.setVisible(false);
                registration_number.setVisible(false);
                jLabel6.setVisible(false);
                surname.setVisible(false);
                jLabel7.setVisible(false);
                name.setVisible(false);
                jLabel8.setVisible(false);
                end_date.setVisible(false);
                jLabel9.setVisible(false);
                jComboBox_contract.setVisible(false);
                jLabel10.setVisible(false);
                jComboBox_internship.setVisible(false);
                enter.setVisible(true);
                search_button.setVisible(false);
                boolean_search_surname_name_rn =false;
                boolean_search_end_date =false;
                boolean_search_contract = false;
            }
        });
        employeeMenu.add(delete);
        update = new JMenuItem("Actualiser");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate,account_balance,nominative_statement FROM archive a left join contract c on c.ID_contract = a.ID_contract WHERE year = '"+year.getText().toString()+"' AND a.status = 1";
                
                Show_Employee_In_JTable(query);
                jLabel1.setVisible(false);
                work_certificate_label.setVisible(false);
                jLabel2.setVisible(false);
                account_balance_label.setVisible(false);
                jLabel3.setVisible(false);
                nominative_statement_label.setVisible(false);
                jLabel4.setVisible(true);
                year.setVisible(true);
                jLabel5.setVisible(false);
                registration_number.setVisible(false);
                jLabel6.setVisible(false);
                surname.setVisible(false);
                jLabel7.setVisible(false);
                name.setVisible(false);
                jLabel8.setVisible(false);
                end_date.setVisible(false);
                jLabel9.setVisible(false);
                jComboBox_contract.setVisible(false);
                jLabel10.setVisible(false);
                jComboBox_internship.setVisible(false);
                enter.setVisible(true);
                search_button.setVisible(false);
                boolean_search_surname_name_rn =false;
                boolean_search_end_date =false;
                boolean_search_contract = false;
            }
        });
        employeeMenu.add(update);
        menuBar.add(employeeMenu);
        search = new JMenu("Recherche");
        search_surname_name_rn = new JMenuItem("Rechercher par Matricule, Nom et Prénoms");
        search_surname_name_rn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabel1.setVisible(false);
                work_certificate_label.setVisible(false);
                jLabel2.setVisible(false);
                account_balance_label.setVisible(false);
                jLabel3.setVisible(false);
                nominative_statement_label.setVisible(false);
                jLabel4.setVisible(true);
                year.setVisible(true);
                jLabel5.setVisible(true);
                registration_number.setVisible(true);
                jLabel6.setVisible(true);
                surname.setVisible(true);
                jLabel7.setVisible(true);
                name.setVisible(true);
                jLabel8.setVisible(false);
                end_date.setVisible(false);
                jLabel9.setVisible(false);
                jComboBox_contract.setVisible(false);
                jLabel10.setVisible(false);
                jComboBox_internship.setVisible(false);
                enter.setVisible(false);
                search_button.setVisible(true);
                boolean_search_surname_name_rn = true;
                boolean_search_end_date = false;
                boolean_search_contract = false;
            }
        });
        search.add(search_surname_name_rn);
        search_end_date = new JMenuItem("Rechercher par Date de fin de contrat");
        search_end_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabel1.setVisible(false);
                work_certificate_label.setVisible(false);
                jLabel2.setVisible(false);
                account_balance_label.setVisible(false);
                jLabel3.setVisible(false);
                nominative_statement_label.setVisible(false);
                jLabel4.setVisible(true);
                year.setVisible(true);
                jLabel5.setVisible(false);
                registration_number.setVisible(false);
                jLabel6.setVisible(false);
                surname.setVisible(false);
                jLabel7.setVisible(false);
                name.setVisible(false);
                jLabel8.setVisible(true);
                end_date.setVisible(true);
                jLabel9.setVisible(false);
                jComboBox_contract.setVisible(false);
                jLabel10.setVisible(false);
                jComboBox_internship.setVisible(false);
                enter.setVisible(false);
                search_button.setVisible(true);
                boolean_search_surname_name_rn = false;
                boolean_search_end_date = true;
                boolean_search_contract = false;
            }
         });
        search.add(search_end_date);
        search_contract = new JMenuItem("Rechercher par Type de Contrat");
        search_contract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabel1.setVisible(false);
                work_certificate_label.setVisible(false);
                jLabel2.setVisible(false);
                account_balance_label.setVisible(false);
                jLabel3.setVisible(false);
                nominative_statement_label.setVisible(false);
                jLabel4.setVisible(true);
                year.setVisible(true);
                jLabel5.setVisible(false);
                registration_number.setVisible(false);
                jLabel6.setVisible(false);
                surname.setVisible(false);
                jLabel7.setVisible(false);
                name.setVisible(false);
                jLabel8.setVisible(false);
                end_date.setVisible(false);
                jLabel9.setVisible(true);
                jComboBox_contract.setVisible(true);
                jLabel10.setVisible(true);
                jComboBox_internship.setVisible(true);
                enter.setVisible(false);
                search_button.setVisible(true);
                boolean_search_surname_name_rn = false;
                boolean_search_end_date = false;
                boolean_search_contract = true;
            }
         });
        search.add(search_contract);
        menuBar.add(search);
        dataMenu = new JMenu("Données");
        export_data = new JMenuItem("Exporter des données");
        export_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContratWriter contratWriter = new ContratWriter();
                contratWriter.getJTable_Display_Employee(jTable_Display_Employee);
                contratWriter.setArchive(true);
            }
        });
        dataMenu.add(export_data);
        menuBar.add(dataMenu);
        jLabel1 = new JLabel("Certificat de travail:");
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setVisible(false);
        work_certificate_label = new JLabel();
        work_certificate_label.setForeground(Color.WHITE);
        work_certificate_label.setVisible(false);
        jLabel2 = new JLabel("Solde de tout compte:");
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setVisible(false);
        account_balance_label = new JLabel();
        account_balance_label.setForeground(Color.WHITE);
        account_balance_label.setVisible(false);
        jLabel3 = new JLabel("Relevé nominatif:");
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setVisible(false);
        nominative_statement_label = new JLabel();
        nominative_statement_label.setForeground(Color.WHITE);
        nominative_statement_label.setVisible(false);
        jLabel5 = new JLabel("Matricule:");
        jLabel5.setForeground(Color.WHITE);
        jLabel5.setVisible(false);
        Adapter adapter = new Adapter();
        registration_number = new RoundedJTextField(20,"circle");
        registration_number.addKeyListener(adapter);
        registration_number.setVisible(false);
        registration_number.setForeground(Color.WHITE);
        //jTextField_length.setBackground(new java.awt.Color(0,0,0,1));
        registration_number.setBackground(new java.awt.Color(0,0,153));
        jLabel6 = new JLabel("Nom:");
        jLabel6.setForeground(Color.WHITE);
        jLabel6.setVisible(false);
        surname = new RoundedJTextField(20,"circle");
        surname.addKeyListener(adapter);
        surname.setForeground(Color.WHITE);
        //jTextField_length.setBackground(new java.awt.Color(0,0,0,1));
        surname.setBackground(new java.awt.Color(0,0,153));
        surname.setVisible(false);
        jLabel7 = new JLabel("Prénoms:");
        jLabel7.setForeground(Color.WHITE);
        jLabel7.setVisible(false);
        name = new RoundedJTextField(20,"circle");
        name.addKeyListener(adapter);
        name.setForeground(Color.WHITE);
        //jTextField_length.setBackground(new java.awt.Color(0,0,0,1));
        name.setBackground(new java.awt.Color(0,0,153));
        name.setVisible(false);
        jLabel8 = new JLabel("Fin de contrat:");
        jLabel8.setForeground(Color.WHITE);
        jLabel8.setVisible(false);
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        end_date = new JDatePickerImpl(datePanel, new Archive.DateLabelFormatter());
        end_date.addKeyListener(adapter);
        end_date.setVisible(false);
        end_date.getJFormattedTextField().setForeground(Color.WHITE);
        end_date.getJFormattedTextField().setBackground(new java.awt.Color(0,0,153));
        //datePicker_entry_date.setBackground(new java.awt.Color(0,0,0,1));
        end_date.setBackground(new java.awt.Color(0,0,153));
        //datePicker_entry_date.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        end_date.getJFormattedTextField().setOpaque(false);
        jLabel9 = new JLabel("Type de contrat:");
        jLabel9.setForeground(Color.WHITE);
        jLabel9.setVisible(false);
        DefaultComboBoxModel contract_model = new DefaultComboBoxModel<String>();
        jComboBox_contract = new JComboBox(contract_model);
        jComboBox_contract.addKeyListener(adapter);
        Dimension preferredSize = jComboBox_contract.getPreferredSize();
        preferredSize.width = 230;
        jComboBox_contract.setPreferredSize(preferredSize);
        jComboBox_contract.setBackground(new java.awt.Color(0,0,153));
        //jComboBox_contract.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jComboBox_contract.setOpaque(false);
        jComboBox_contract.setForeground(Color.WHITE);
        jComboBox_contract.setVisible(false);
        jLabel10 = new JLabel("Type de stage:");
        jLabel10.setForeground(Color.WHITE);
        jLabel10.setVisible(false);
        jComboBox_internship = new JComboBox<String>();
        jComboBox_internship.addKeyListener(adapter);
        jComboBox_internship.setBackground(new java.awt.Color(0,0,153));
        //jComboBox_internship.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jComboBox_internship.setOpaque(false);
        jComboBox_internship.setForeground(Color.WHITE);
        jComboBox_internship.setPreferredSize(preferredSize);
        jComboBox_internship.setVisible(false);
        populate.populate_contract(contract_model,jComboBox_internship);
        jComboBox_contract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
             Contract();
            }
        }); 
        jComboBox_internship.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
             Contract();
            }
        }); 
        jLabel4 = new JLabel("Année:");
        year = new RoundedJTextField(10,"circle");
        year.addKeyListener(adapter);
        enter = new RoundedJButton("Entrer");
        enter.setBackground(Color.ORANGE);
        enter.setFocusable(false);
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate,account_balance,nominative_statement FROM archive a left join contract c on c.ID_contract = a.ID_contract WHERE year = '"+year.getText().toString()+"' AND a.status = 1";
                Show_Employee_In_JTable(query);
                jLabel1.setVisible(false);
                work_certificate_label.setVisible(false);
                jLabel2.setVisible(false);
                account_balance_label.setVisible(false);
                jLabel3.setVisible(false);
                nominative_statement_label.setVisible(false);
                jLabel4.setVisible(true);
                year.setVisible(true);
                jLabel5.setVisible(false);
                registration_number.setVisible(false);
                jLabel6.setVisible(false);
                surname.setVisible(false);
                jLabel7.setVisible(false);
                name.setVisible(false);
                jLabel8.setVisible(false);
                jLabel9.setVisible(false);
                jComboBox_contract.setVisible(false);
                jLabel10.setVisible(false);
                jComboBox_internship.setVisible(false);
                end_date.setVisible(false);
                enter.setVisible(true);
                search_button.setVisible(false);
                year_string = year.getText().toString();
                boolean_search_surname_name_rn =false;
                boolean_search_end_date =false;
                boolean_search_contract = false;
            }
        });
        search_button = new RoundedJButton("Rechercher");
        search_button.setBackground(Color.ORANGE);
        search_button.setFocusable(false);
        search_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search();
            }
        });
        search_button.setVisible(false);
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
        panel_modifier.setOpaque(false);
        gbc_modifier = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
                 40, 30, 0, 0), 0, 0);
        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
                  0, 0, 0, 0), 0, 0);
        panel_modifier.add(jLabel1, gbc_modifier);
        panel_modifier.add(jLabel5, gbc_modifier);
        panel_modifier.add(jLabel9, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 0;
        panel_modifier.add(work_certificate_label, gbc_modifier);
        panel_modifier.add(registration_number, gbc_modifier);
        panel_modifier.add(jComboBox_contract, gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 1;
        panel_modifier.add(jLabel2, gbc_modifier);
        panel_modifier.add(jLabel6, gbc_modifier);
        panel_modifier.add(jLabel10, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 1;
        panel_modifier.add(account_balance_label, gbc_modifier);
        panel_modifier.add(surname, gbc_modifier);
        panel_modifier.add(jComboBox_internship, gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 2;
        panel_modifier.add(jLabel3, gbc_modifier);
        panel_modifier.add(jLabel7, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 2;
        panel_modifier.add(nominative_statement_label, gbc_modifier);
        panel_modifier.add(name, gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 3;
        panel_modifier.add(jLabel8, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 3;
        panel_modifier.add(end_date, gbc_modifier);        
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 4;
        panel_modifier.add(jLabel4, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 4;
        gbc_modifier.anchor =GridBagConstraints.CENTER;
        panel_modifier.add(year, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 5;
        panel_modifier.add(enter, gbc_modifier);
        panel_modifier.add(search_button, gbc_modifier);
        panel.add(panel_modifier, gbc);
        background.add(panel_modifier,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(scrollPane,gbc);
        background.add(scrollPane,gbc);
        ImageIcon img = new ImageIcon(getClass().getResource("LOGOEA.png"));
        this.setIconImage(img.getImage());
        this.setResizable(false);
        this.setJMenuBar(menuBar);
        this.getContentPane().add(background);
        this.setSize(2000, 2000);
        this.setVisible(true);
    }
    public class Adapter extends KeyAdapter{
        @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                 if (e.getKeyCode()==KeyEvent.VK_ENTER){
                     if(enter.isVisible() && search_button.isVisible() == false){
                         String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate,account_balance,nominative_statement FROM archive a left join contract c on c.ID_contract = a.ID_contract WHERE year = '"+year.getText().toString()+"' AND a.status = 1";
                Show_Employee_In_JTable(query);
                jLabel1.setVisible(false);
                work_certificate_label.setVisible(false);
                jLabel2.setVisible(false);
                account_balance_label.setVisible(false);
                jLabel3.setVisible(false);
                nominative_statement_label.setVisible(false);
                jLabel4.setVisible(true);
                year.setVisible(true);
                jLabel5.setVisible(false);
                registration_number.setVisible(false);
                jLabel6.setVisible(false);
                surname.setVisible(false);
                jLabel7.setVisible(false);
                name.setVisible(false);
                jLabel8.setVisible(false);
                jLabel9.setVisible(false);
                jComboBox_contract.setVisible(false);
                jLabel10.setVisible(false);
                jComboBox_internship.setVisible(false);
                end_date.setVisible(false);
                enter.setVisible(true);
                search_button.setVisible(false);
                year_string = year.getText().toString();
                boolean_search_surname_name_rn =false;
                boolean_search_end_date =false;
                boolean_search_contract = false;
                     }
                     if(enter.isVisible() == false && search_button.isVisible()){
                         Search();
                     }
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
                new Archive().setVisible(true);
            }
        });    
    }
   public ArrayList<employee> getEmployeeList(String query)
   {
       ArrayList<employee> employeeList = new ArrayList<employee>();
       Connection connection = populate.getConnection();
       
       
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
               employee = new employee(rs.getString("registration_number"),rs.getString("surname"),rs.getString("name"),rs.getString("end_date"),rs.getString("contract"));
               employeeList.add(employee);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return employeeList;
   }
    public void Show_Employee_In_JTable(String query)
   {
       ArrayList<employee> list = getEmployeeList(query);
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Employee.getModel();
       model.setRowCount(0);
       Object[] row = new Object[5];
       for(int i = 0; i < list.size(); i++)
       {  
           row[0] = list.get(i).getRegistration_number();          
           row[1] = list.get(i).getSurname();
           row[2] = list.get(i).getName();      
           row[3] = list.get(i).getEnd_date(); 
           row[4] = list.get(i).getContract();
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
        gbc_modifier.gridy = 0;
        panel_modifier.add(work_certificate_label, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 1;
        panel_modifier.add(account_balance_label, gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 2;
        panel_modifier.add(nominative_statement_label, gbc_modifier);
        gbc.gridx = 0;
        gbc.gridy = 0;
        background.add(panel_modifier, gbc);
        int i = jTable_Display_Employee.getSelectedRow();
        ID = IDList.get(i);
        TableModel model = jTable_Display_Employee.getModel();
        jLabel1.setVisible(true);
        work_certificate_label.setVisible(true);
        jLabel2.setVisible(true);
        account_balance_label.setVisible(true);
        jLabel3.setVisible(true);
        nominative_statement_label.setVisible(true);
        jLabel4.setVisible(false);
        year.setVisible(false);
        jLabel5.setVisible(false);
        registration_number.setVisible(false);
        jLabel6.setVisible(false);
        surname.setVisible(false);
        jLabel7.setVisible(false);
        name.setVisible(false);
        jLabel8.setVisible(false);
        end_date.setVisible(false);
        enter.setVisible(false);
        search_button.setVisible(false);
        //work_certificate_list.get(remove).setVisible(false);
        //account_balance_list.get(remove).setVisible(false);
        //nominative_statement_list.get(remove).setVisible(false);
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
    private void Search(){
       if (boolean_search_surname_name_rn == true){
           if (registration_number.getText().isEmpty() == false && surname.getText().isEmpty() && name.getText().isEmpty()){
               String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate, account_balance, nominative_statement FROM archive a left join contract c on a.ID_contract = c.ID_contract WHERE a.status = 1 AND year = '"+year.getText().toString()+"' AND registration_number = '"+registration_number.getText().toString()+"'";
               Show_Employee_In_JTable(query);
           }
           if (registration_number.getText().isEmpty() && surname.getText().isEmpty()== false && name.getText().isEmpty()){
               String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate, account_balance, nominative_statement FROM archive a left join contract c on a.ID_contract = c.ID_contract WHERE a.status = 1 AND year = '"+year.getText().toString()+"' AND surname = '"+surname.getText().toString()+"'";
               Show_Employee_In_JTable(query);
           }
           if (registration_number.getText().isEmpty() && surname.getText().isEmpty() && name.getText().isEmpty() == false){
               String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate, account_balance, nominative_statement FROM archive a left join contract c on a.ID_contract = c.ID_contract WHERE a.status = 1 AND year = '"+year.getText().toString()+"' AND name = '"+name.getText().toString()+"'";
               Show_Employee_In_JTable(query);
           }
           if (registration_number.getText().isEmpty() && surname.getText().isEmpty()== false && name.getText().isEmpty() == false){
               String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate, account_balance, nominative_statement FROM archive a left join contract c on a.ID_contract = c.ID_contract WHERE a.status = 1 AND year = '"+year.getText().toString()+"' AND surname = '"+surname.getText().toString()+"' AND name = '"+name.getText().toString()+"'";
               Show_Employee_In_JTable(query);
           }
           if (registration_number.getText().isEmpty() == false && surname.getText().isEmpty() && name.getText().isEmpty() == false){
               String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate, account_balance, nominative_statement FROM archive a left join contract c on a.ID_contract = c.ID_contract WHERE a.status = 1 AND year = '"+year.getText().toString()+"' AND registration_number = '"+registration_number.getText().toString()+"' AND name = '"+name.getText().toString()+"'";
               Show_Employee_In_JTable(query);
           }
           if (registration_number.getText().isEmpty() == false && surname.getText().isEmpty() == false && name.getText().isEmpty()){
               String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate, account_balance, nominative_statement FROM archive a left join contract c on a.ID_contract = c.ID_contract WHERE a.status = 1 AND year = '"+year.getText().toString()+"' AND registration_number = '"+registration_number.getText().toString()+"' AND surname = '"+surname.getText().toString()+"'";
               Show_Employee_In_JTable(query);
           }
           if(registration_number.getText().isEmpty() == false && surname.getText().isEmpty() == false && name.getText().isEmpty() == false){
                String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate, account_balance, nominative_statement FROM archive a left join contract c on a.ID_contract = c.ID_contract WHERE a.status = 1 AND year = '"+year.getText().toString()+"' AND registration_number = '"+registration_number.getText().toString()+"' AND surname = '"+surname.getText().toString()+"' AND name = '"+name.getText().toString()+"'";
                Show_Employee_In_JTable(query);
           }
       }
       if(boolean_search_end_date == true){
           String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate, account_balance, nominative_statement FROM archive a left join contract c on a.ID_contract = c.ID_contract WHERE a.status = 1 AND year = '"+year.getText().toString()+"' AND end_date = '"+end_date.getJFormattedTextField().getText().toString()+"'";
           Show_Employee_In_JTable(query);
       }
       if(boolean_search_contract == true){
           String contract;
            if(jComboBox_contract.getSelectedItem().equals("CONTRAT DE STAGE"))
                contract = jComboBox_internship.getSelectedItem().toString();
            else{
                contract = jComboBox_contract.getSelectedItem().toString();
            }
           String query = "SELECT ID_employee,registration_number,surname,name,end_date,contract,work_certificate, account_balance, nominative_statement FROM archive a left join contract c on a.ID_contract = c.ID_contract WHERE a.status = 1 AND year = '"+year.getText().toString()+"' AND contract = '"+contract+"'";
           Show_Employee_In_JTable(query);
       }
   }
    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}
  public void Contract(){
    if(jComboBox_contract.getSelectedItem().equals("CONTRAT DE STAGE")){
        jComboBox_internship.setEnabled(true);
    }
    if(jComboBox_contract.getSelectedItem().equals("CONTRAT DE STAGE") == false){
        jComboBox_internship.setSelectedItem("");
        jComboBox_internship.setEnabled(false);
    }
    if(jComboBox_internship.getSelectedItem().toString().contains("STAGE")){
        jComboBox_contract.setSelectedItem("CONTRAT DE STAGE");
    }
}
  private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {  
        String query = "UPDATE archive SET status = 0 WHERE ID_employee = "+ID;
        Connection con = populate.getConnection();
       Statement st;
       try{
           st = con.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               Show_Employee_In_JTable(query);
               
               JOptionPane.showMessageDialog(null, "archive supprimée avec succès");
           }else{
               JOptionPane.showMessageDialog(null, "archive non supprimée");
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
    }
}
