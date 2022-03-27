
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author h
 */
public class Contrats extends javax.swing.JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //</editor-fold>
        
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
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Contrats().setVisible(true);
            }
        });
        
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_insert;
    private javax.swing.JButton jButton_update;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_function;
    private javax.swing.JButton jButton_observation;
    private javax.swing.JButton jButton_end_contract;
    private javax.swing.JButton jButton_close_contract;
    private javax.swing.JButton jButton_insert_excel;
    private javax.swing.JButton jButton_export_excel;
    private RoundedJButton jButton_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9, jLabel10, jLabel11;
    private javax.swing.JTable jTable_Display_Employee;
    private JTextField jTextField_registration_number;
    private RoundedJTextField jTextField_cnps;
    private RoundedJTextField jTextField_name;
    private RoundedJTextField jTextField_surname;
    private RoundedJTextField jTextField_length;
    private RoundedJTextField jTextField_user;
    private JComboBox<String> jComboBox_function;
    private JDatePickerImpl datePicker_entry_date;
    private JDatePickerImpl datePicker_end_date;
    private javax.swing.JComboBox <String>jComboBox_observation;
    private javax.swing.JComboBox jComboBox_contract;
    private  DefaultComboBoxModel<String> contract_model,internship_model;
    private javax.swing.JComboBox <String>jComboBox_internship;
    private javax.swing.JScrollPane scrollPane;
    private ArrayList<Integer> IDList;
    private int ID;
    private String user;
    private JMenuBar menuBar;
    private Populate populate;
    JMenu employeeMenu,functionMenu,contractMenu,observationMenu, contratMenu,dataMenu, search,archive_menu;
    JMenuItem insert_employee, update_employee, delete_employee, clear_employee, update, update_function,update_contract,update_observation, end_contract, close_contract, import_data, export_data,search_surname_name_rn,search_entry_date,search_user, search_contract,archive;
    boolean boolean_search_surname_name_rn, boolean_search_entry_date, boolean_search_user, boolean_search_contract;
    public Contrats(){
        
        boolean_search_surname_name_rn =false;
        boolean_search_entry_date =false;
        boolean_search_user =false;
        boolean_search_contract=false;
        populate = new Populate();
        IDList = new ArrayList<Integer>();
        jTable_Display_Employee = new javax.swing.JTable();
        jTable_Display_Employee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricule", "Numéro CNPS", "Nom", "Prénoms", "Fonction", "Date de début", "Fin de contrat","Durée du contrat","Type de contrat","Observations","Date de création","Créateur","Date de modication","Modificateur"
            }
        ));  
        jTable_Display_Employee.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable_Display_Employee.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable_Display_Employee.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable_Display_Employee.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable_Display_Employee.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTable_Display_Employee.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTable_Display_Employee.getColumnModel().getColumn(6).setPreferredWidth(200);
        jTable_Display_Employee.getColumnModel().getColumn(7).setPreferredWidth(200);
        jTable_Display_Employee.getColumnModel().getColumn(8).setPreferredWidth(100);
        jTable_Display_Employee.getColumnModel().getColumn(9).setPreferredWidth(200);
        jTable_Display_Employee.getColumnModel().getColumn(10).setPreferredWidth(100);
        jTable_Display_Employee.getColumnModel().getColumn(11).setPreferredWidth(100);
        jTable_Display_Employee.getColumnModel().getColumn(12).setPreferredWidth(100);
        jTable_Display_Employee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_EmployeeMouseClicked(evt) ;
            }
        });
        String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract  where e.status = 1";
        populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList,query);
        Adapter adapter =new Adapter();
        jLabel1 =new JLabel();
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setText("Matricule:");
        jTextField_registration_number = new RoundedJTextField(20,"circle");
        jTextField_registration_number.addKeyListener(adapter);
        jTextField_registration_number.setForeground(Color.WHITE);
        jTextField_registration_number.setOpaque(false);
        //jTextField_registration_number.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jTextField_registration_number.setUI(new BasicTextFieldUI());
        //jTextField_registration_number
   //jTextField_registration_number.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        //jTextField_registration_number.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        //jTextField_registration_number.setBackground(new java.awt.Color(0,0,0,1));
        jTextField_registration_number.setBackground(new java.awt.Color(0,0,153));
        //jTextField_registration_number.setForeground(Color.ORANGE);
        //jTextField_registration_number
        //jTextField_registration_number.setForeground(Color.WHITE);
        jLabel8 =new JLabel();
        jLabel8.setForeground(Color.WHITE);
        jLabel8.setText("Numéro CNPS:");
        jTextField_cnps = new RoundedJTextField(20,"circle");
        jTextField_cnps.addKeyListener(adapter);
        jTextField_cnps.setForeground(Color.WHITE);
        jTextField_cnps.setBackground(new java.awt.Color(0,0,153));
        //jTextField_cnps.setBackground(new java.awt.Color(0,0,0,1));
        jLabel2 =new JLabel();
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setText("Nom:");
        jTextField_surname = new RoundedJTextField(20,"circle");
        jTextField_surname.addKeyListener(adapter);
        jTextField_surname.setForeground(Color.WHITE);
        //jTextField_surname.setBackground(new java.awt.Color(0,0,0,1));
        jTextField_surname.setBackground(new java.awt.Color(0,0,153));
        jLabel3 =new JLabel();
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setText("Prénoms:");
        jTextField_name = new RoundedJTextField(20,"circle");
        jTextField_name.addKeyListener(adapter);
        jTextField_name.setForeground(Color.WHITE);
        //jTextField_name.setBackground(new java.awt.Color(0,0,0,1));
        jTextField_name.setBackground(new java.awt.Color(0,0,153));
        jLabel4 =new JLabel();
        jLabel4.setForeground(Color.WHITE);
        jLabel4.setText("Fonction:");
        //UIManager.put("ComboBox.Border", BorderFactory.createLineBorder(Color.blue, 5));
        jComboBox_function = new JComboBox<String>();
        jComboBox_function.addKeyListener(adapter);
        jComboBox_function.setForeground(Color.WHITE);
        //jComboBox_function.setUI(new BasicComboBoxUI());
        //jComboBox_function.setBackground(new java.awt.Color(0,0,0,1));
        jComboBox_function.setBackground(new java.awt.Color(0,0,153));
        //jComboBox_function.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jComboBox_function.setOpaque(false);
        Dimension preferredSize = jComboBox_function.getPreferredSize();
        preferredSize.width = 230;
        jComboBox_function.setPreferredSize(preferredSize);
        populate.populate_function(jComboBox_function);
        jLabel5 =new JLabel();
        jLabel5.setForeground(Color.WHITE);
        jLabel5.setText("Date de début:");
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        datePicker_entry_date = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker_entry_date.addKeyListener(adapter);
        datePicker_entry_date.getJFormattedTextField().setForeground(Color.WHITE);
        datePicker_entry_date.getJFormattedTextField().setBackground(new java.awt.Color(0,0,153));
        //datePicker_entry_date.setBackground(new java.awt.Color(0,0,0,1));
        datePicker_entry_date.setBackground(new java.awt.Color(0,0,153));
        //datePicker_entry_date.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        datePicker_entry_date.getJFormattedTextField().setOpaque(false);
        jLabel6 =new JLabel();
        jLabel6.setForeground(Color.WHITE);
        jLabel6.setText("Durée de contrat:");
        UtilDateModel model2 = new UtilDateModel();
        Properties p2 = new Properties();
        p2.put("text.today", "Today");
        p2.put("text.month", "Month");
        p2.put("text.year", "Year");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2,p2);
        datePicker_end_date = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        jTextField_length = new RoundedJTextField(10,"circle");
        jTextField_length.addKeyListener(adapter);
        jTextField_length.setForeground(Color.WHITE);
        //jTextField_length.setBackground(new java.awt.Color(0,0,0,1));
        jTextField_length.setBackground(new java.awt.Color(0,0,153));
        jLabel10 = new JLabel("Type de contrat:");
        jLabel10.setForeground(Color.WHITE);
        contract_model = new DefaultComboBoxModel<String>();
        jComboBox_contract = new JComboBox(contract_model);
        jComboBox_contract.addKeyListener(adapter);
        //jComboBox_contract.setUI(new BasicComboBoxUI());
        //jComboBox_contract.setBackground(new java.awt.Color(0,0,0,1));
        jComboBox_contract.setBackground(new java.awt.Color(0,0,153));
        //jComboBox_contract.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jComboBox_contract.setOpaque(false);
        jComboBox_contract.setForeground(Color.WHITE);
        jComboBox_contract.setPreferredSize(preferredSize);
        jLabel11 = new JLabel("Type de stage:");
        jLabel11.setForeground(Color.WHITE);
        jComboBox_internship = new JComboBox<String>();
        jComboBox_internship.addKeyListener(adapter);
        //jComboBox_internship.setUI(new BasicComboBoxUI());
        //jComboBox_internship.setBackground(new java.awt.Color(0,0,0,1));
        jComboBox_internship.setBackground(new java.awt.Color(0,0,153));
        //jComboBox_internship.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jComboBox_internship.setOpaque(false);
        jComboBox_internship.setForeground(Color.WHITE);
        //jComboBox_internship.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jComboBox_internship.setPreferredSize(preferredSize);
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
        jLabel7 =new JLabel();
        jLabel7.setForeground(Color.WHITE);
        jLabel7.setText("Observations:");
        jComboBox_observation = new JComboBox<String>();
        jComboBox_observation.addKeyListener(adapter);
        jComboBox_observation.setBackground(new java.awt.Color(0,0,153));
        //jComboBox_observation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //jComboBox_observation.setBackground(new java.awt.Color(0,0,0,1));
        jComboBox_observation.setOpaque(false);
        //jComboBox_observation.setForeground(Color.WHITE);
        jComboBox_observation.setPreferredSize(preferredSize);
        populate.populate_observation(jComboBox_observation);
        jLabel9 = new JLabel("Créateur:");
        jLabel9.setForeground(Color.WHITE);
        jLabel9.setVisible(false);
        jTextField_user = new RoundedJTextField(20,"circle");
        jTextField_user.addKeyListener(adapter);
        jTextField_user.setForeground(Color.WHITE);;
        //jTextField_user.setBackground(new java.awt.Color(0,0,0,1));
        jTextField_user.setBackground(new java.awt.Color(0,0,153));
        jTextField_user.setVisible(false);
        menuBar = new JMenuBar();
        employeeMenu = new JMenu("Employé");
        insert_employee = new JMenuItem("Ajouter");
        insert_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertActionPerformed(evt);
                Clear();
            }
        });
        update_employee = new JMenuItem("Modifier");
        update_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateActionPerformed(evt);
                Clear();
            }
        });
        delete_employee = new JMenuItem("Supprimer");
        delete_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
                Clear();
            }
        });
        clear_employee = new JMenuItem("Effacer");
        clear_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               Clear();
            }
        });
        update = new JMenuItem("Actualiser");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabel1.setVisible(true);
                jTextField_registration_number.setVisible(true);
                jLabel8.setVisible(true);
                jTextField_cnps.setVisible(true);
                jLabel2.setVisible(true);
                jTextField_surname.setVisible(true);
                jLabel3.setVisible(true);
                jTextField_name.setVisible(true);
                jLabel4.setVisible(true);
                jComboBox_function.setVisible(true);
                jLabel5.setVisible(true);
                datePicker_entry_date.setVisible(true);
                jLabel6.setVisible(true);
                jTextField_length.setVisible(true);
                jLabel10.setVisible(true);
                jComboBox_contract.setVisible(true);
                jLabel11.setVisible(true);
                jComboBox_internship.setVisible(true);
                jLabel7.setVisible(true);
                jComboBox_observation.setVisible(true);
                jLabel9.setVisible(false);
                jTextField_user.setVisible(false);
                jButton_search.setVisible(false);
                boolean_search_surname_name_rn = false;
                boolean_search_entry_date =false;
                boolean_search_user =false;
                boolean_search_contract = false;
                populate = new Populate();
                Clear();
                String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract  where e.status = 1";
                populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList, query);
                alert_end_contract();
            }
        });
        employeeMenu.add(insert_employee);
        employeeMenu.add(update_employee);
        employeeMenu.add(delete_employee);
        employeeMenu.add(clear_employee);
        employeeMenu.add(update);
        functionMenu = new JMenu("Fonction");
        update_function = new JMenuItem("Modifier Fonction");
        update_function.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dispose();
                Function function = new Function();
                function.getUsername(user);
                function.getFunction_list(jComboBox_function);
                function.getJTable_Display_Employee(jTable_Display_Employee,IDList);
            }
        });
        functionMenu.add(update_function);
        contractMenu = new JMenu("Type de Contrat");
        update_contract = new JMenuItem("Modifier Type de Contrat");
        update_contract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dispose();
                Contract contract = new Contract();
                contract.getUsername(user);
                contract.getContract_list(contract_model,jComboBox_internship);
                contract.getJTable_Display_Employee(jTable_Display_Employee,IDList);
            }
        });
        contractMenu.add(update_contract);
        observationMenu = new JMenu("Observations");
        update_observation = new JMenuItem("Modifier Observations");
        update_observation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dispose();
                Observation observation = new Observation();
                observation.getUsername(user);
                observation.getObservation_list(jComboBox_observation);
                observation.getJTable_Display_Employee(jTable_Display_Employee, IDList);
            }
        });
        observationMenu.add(update_observation);
        contratMenu = new JMenu("Contrats");
        end_contract = new JMenuItem("Expirations en cours");
        end_contract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new End_contract();
            }
        });
        close_contract = new JMenuItem("Contrats terminés");
        close_contract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               Close_contract close_contract = new Close_contract();
               close_contract.getUsername(user);
            }
        });
        contratMenu.add(end_contract);
        contratMenu.add(close_contract);
        dataMenu = new JMenu("Données");
        import_data =new JMenuItem("Importer des données");
        import_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContratReader reader = new ContratReader();
                reader.getUsername(user);
                reader.getObservation_list(jComboBox_observation);
                reader.getJTable_Display_Employee(jTable_Display_Employee,IDList);
                reader.getFunction_list(jComboBox_function);
                reader.getContractList(contract_model, jComboBox_internship);
            }
        });
        export_data = new JMenuItem("Exporter des données");
        export_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContratWriter contratWriter = new ContratWriter();
                contratWriter.getJTable_Display_Employee(jTable_Display_Employee);
                contratWriter.setContrats(true);
            }
        });
        dataMenu.add(import_data);
        dataMenu.add(export_data);
        search = new JMenu("Recherche");
        search_surname_name_rn = new JMenuItem("Rechercher par Matricule, Nom et Prénoms");
        search_surname_name_rn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear();
                jLabel1.setVisible(true);
                jTextField_registration_number.setVisible(true);
                jLabel8.setVisible(false);
                jTextField_cnps.setVisible(false);
                jLabel2.setVisible(true);
                jTextField_surname.setVisible(true);
                jLabel3.setVisible(true);
                jTextField_name.setVisible(true);
                jLabel4.setVisible(false);
                jComboBox_function.setVisible(false);
                jLabel5.setVisible(false);
                datePicker_entry_date.setVisible(false);
                jLabel6.setVisible(false);
                jTextField_length.setVisible(false);
                jLabel10.setVisible(false);
                jComboBox_contract.setVisible(false);
                jLabel11.setVisible(false);
                jComboBox_internship.setVisible(false);
                jLabel7.setVisible(false);
                jComboBox_observation.setVisible(false);
                jLabel9.setVisible(false);
                jTextField_user.setVisible(false);
                jButton_search.setVisible(true);
                boolean_search_surname_name_rn = true;
                boolean_search_entry_date =false;
                boolean_search_user =false;   
                boolean_search_contract = false;
            }
        });
        search.add(search_surname_name_rn);
        search_entry_date = new JMenuItem("Rechercher par Date de début de contrat");
        search_entry_date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear();
                jLabel1.setVisible(false);
                jTextField_registration_number.setVisible(false);
                jLabel8.setVisible(false);
                jTextField_cnps.setVisible(false);
                jLabel2.setVisible(false);
                jTextField_surname.setVisible(false);
                jLabel3.setVisible(false);
                jTextField_name.setVisible(false);
                jLabel4.setVisible(false);
                jComboBox_function.setVisible(false);
                jLabel5.setVisible(true);
                datePicker_entry_date.setVisible(true);
                jLabel6.setVisible(false);
                jTextField_length.setVisible(false);
                jLabel10.setVisible(false);
                jComboBox_contract.setVisible(false);
                jLabel11.setVisible(false);
                jComboBox_internship.setVisible(false);
                jLabel7.setVisible(false);
                jComboBox_observation.setVisible(false);
                jLabel9.setVisible(false);
                jTextField_user.setVisible(false);
                jButton_search.setVisible(true);
                boolean_search_surname_name_rn = false;
                boolean_search_entry_date =true;
                boolean_search_user =false;
                boolean_search_contract = false;
            }
        });
        search.add(search_entry_date);
        search_user = new JMenuItem("Rechercher par Créateur");
        search_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear();
                jLabel1.setVisible(false);
                jTextField_registration_number.setVisible(false);
                jLabel8.setVisible(false);
                jTextField_cnps.setVisible(false);
                jLabel2.setVisible(false);
                jTextField_surname.setVisible(false);
                jLabel3.setVisible(false);
                jTextField_name.setVisible(false);
                jLabel4.setVisible(false);
                jComboBox_function.setVisible(false);
                jLabel5.setVisible(false);
                datePicker_entry_date.setVisible(false);
                jLabel6.setVisible(false);
                jTextField_length.setVisible(false);
                jLabel10.setVisible(false);
                jComboBox_contract.setVisible(false);
                jLabel11.setVisible(false);
                jComboBox_internship.setVisible(false);
                jLabel7.setVisible(false);
                jComboBox_observation.setVisible(false);
                jLabel9.setVisible(true);
                jTextField_user.setVisible(true);
                jButton_search.setVisible(true);
                boolean_search_surname_name_rn = false;
                boolean_search_entry_date =false;
                boolean_search_user =true;
                boolean_search_contract = false;
            }
        });
        search.add(search_user);
        search_contract = new JMenuItem("Rechercher par Type de Contrat");
        search_contract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear();
                jLabel1.setVisible(false);
                jTextField_registration_number.setVisible(false);
                jLabel8.setVisible(false);
                jTextField_cnps.setVisible(false);
                jLabel2.setVisible(false);
                jTextField_surname.setVisible(false);
                jLabel3.setVisible(false);
                jTextField_name.setVisible(false);
                jLabel4.setVisible(false);
                jComboBox_function.setVisible(false);
                jLabel5.setVisible(false);
                datePicker_entry_date.setVisible(false);
                jLabel6.setVisible(false);
                jTextField_length.setVisible(false);
                jLabel10.setVisible(true);
                jComboBox_contract.setVisible(true);
                jLabel11.setVisible(true);
                jComboBox_internship.setVisible(true);
                jLabel7.setVisible(false);
                jComboBox_observation.setVisible(false);
                jLabel9.setVisible(false);
                jTextField_user.setVisible(false);
                jButton_search.setVisible(true);
                boolean_search_surname_name_rn = false;
                boolean_search_entry_date =false;
                boolean_search_user =false;
                boolean_search_contract = true;
            }
        });
        search.add(search_contract);
        menuBar.add(employeeMenu);
        menuBar.add(functionMenu);
        menuBar.add(contractMenu);
        menuBar.add(observationMenu);
        menuBar.add(contratMenu);
        menuBar.add(dataMenu);
        menuBar.add(search);
        archive_menu = new JMenu("Archives");
        archive=  new JMenuItem("Consulter la liste des employés archivés");
        archive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               new Archive();
            }
        });
        archive_menu.add(archive);
        menuBar.add(archive_menu);
        jButton_search = new RoundedJButton("Rechercher");
        jButton_search.setFocusable(false);
        jButton_search.addKeyListener(adapter);
        jButton_search.setBackground(Color.ORANGE);
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search();
                jLabel1.setVisible(true);
                jTextField_registration_number.setVisible(true);
                jLabel8.setVisible(true);
                jTextField_cnps.setVisible(true);
                jLabel2.setVisible(true);
                jTextField_surname.setVisible(true);
                jLabel3.setVisible(true);
                jTextField_name.setVisible(true);
                jLabel4.setVisible(true);
                jComboBox_function.setVisible(true);
                jLabel5.setVisible(true);
                datePicker_entry_date.setVisible(true);
                jLabel6.setVisible(true);
                jTextField_length.setVisible(true);
                jLabel10.setVisible(true);
                jComboBox_contract.setVisible(true);
                jLabel11.setVisible(true);
                jComboBox_internship.setVisible(true);
                jLabel7.setVisible(true);
                jComboBox_observation.setVisible(true);
                jLabel9.setVisible(false);
                jTextField_user.setVisible(false);
                jButton_search.setVisible(false);
                Clear();
                /*boolean_search_surname_name_rn = false;
                boolean_search_entry_date =false;
                boolean_search_user =false;
                boolean_search_contract = false;*/        
                
            }
        });
        jButton_search.setVisible(false);
        jButton_update = new JButton("Modifier");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateActionPerformed(evt);
            }
        });
        jButton_insert = new JButton("Ajouter");
         jButton_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertActionPerformed(evt);
            }
        });
        jButton_delete = new JButton("Supprimer");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });
        jButton_clear = new JButton("Effacer");
        jButton_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               jTextField_registration_number.setText("");
               jTextField_surname.setText("");
               jTextField_name.setText("");
               jComboBox_function.setSelectedIndex(0);
               datePicker_entry_date.getJFormattedTextField().setText("");
               datePicker_end_date.getJFormattedTextField().setText("");
               jComboBox_observation.setSelectedIndex(0);
            }
        });
        jButton_function = new JButton("Modifier fonction");
        jButton_function.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dispose();
                Function function = new Function();
                function.getUsername(user);
                function.getFunction_list(jComboBox_function);
                function.getJTable_Display_Employee(jTable_Display_Employee,IDList);
            }
        });
        jButton_observation = new JButton("Modifier observation");
        jButton_observation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //dispose();
                Observation observation = new Observation();
                observation.getUsername(user);
                observation.getObservation_list(jComboBox_observation);
                observation.getJTable_Display_Employee(jTable_Display_Employee,IDList);
            }
        });
        jButton_end_contract = new JButton("Expirations en cours");
        jButton_end_contract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new End_contract();
            }
        });
        jButton_close_contract = new JButton("Contrats terminés");
        jButton_close_contract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new Close_contract();
            }
        });
        jButton_insert_excel = new JButton("Insérer les données");
        jButton_insert_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContratReader reader = new ContratReader();
                reader.getUsername(user);
                reader.getObservation_list(jComboBox_observation);
                reader.getJTable_Display_Employee(jTable_Display_Employee,IDList);
                reader.getFunction_list(jComboBox_function);
            }
        });
        
        JPanel panel_modifier = new JPanel(new GridBagLayout());
        GridBagConstraints gbc_modifier = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
                 30, 30, 0, 0), 0, 0);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
                  0, 0, 0, 0), 0, 0);
        JPanel panel_button1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc_button1 = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
                  30, 50, 0, 0), 0, 0);
        JPanel panel_button2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc_button2 = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
                  30, 50, 0, 0), 0, 0);
        jTable_Display_Employee.setForeground(Color.WHITE);
        jTable_Display_Employee.setOpaque(false);
        jTable_Display_Employee.setBackground(new java.awt.Color(0,0,153));
        jTable_Display_Employee.setSelectionBackground(Color.YELLOW);
        jTable_Display_Employee.setFocusable(false);
        //((DefaultTableCellRenderer)jTable_Display_Employee.getDefaultRenderer(Object.class)).setOpaque(false);
        //((DefaultTableCellRenderer)jTable_Display_Employee.getDefaultRenderer(Object.class)).setBackground(Color.BLUE);
        scrollPane = new JScrollPane(jTable_Display_Employee);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        panel_modifier.add(jLabel1,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 0;
        panel_modifier.add(jTextField_registration_number,gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 1;
        panel_modifier.add(jLabel8,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 1;
        panel_modifier.add(jTextField_cnps,gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 2;
        panel_modifier.add(jLabel2,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 2;
        panel_modifier.add(jTextField_surname,gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 3;
        panel_modifier.add(jLabel3,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 3;
        panel_modifier.add(jTextField_name,gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 4;
        panel_modifier.add(jLabel4,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 4;
        panel_modifier.add(jComboBox_function,gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 5;
        panel_modifier.add(jLabel5,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 5;
        panel_modifier.add(datePicker_entry_date,gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 6;
        panel_modifier.add(jLabel6,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 6;
        panel_modifier.add(jTextField_length,gbc_modifier);
        //panel_modifier.add(datePicker_end_date,gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 7;
        panel_modifier.add(jLabel10,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 7;
        panel_modifier.add(jComboBox_contract,gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 8;
        panel_modifier.add(jLabel11,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 8;
        panel_modifier.add(jComboBox_internship,gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 9;
        panel_modifier.add(jLabel7,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 9;
        panel_modifier.add(jComboBox_observation,gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 10;
        panel_modifier.add(jLabel9,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 10;
        panel_modifier.add(jTextField_user,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 11;
        gbc_modifier.anchor =GridBagConstraints.CENTER;
        panel_modifier.add(jButton_search,gbc_modifier);
        panel_modifier.setOpaque(false);
        panel.add(panel_modifier,gbc);
        panel_button1.add(jButton_update,gbc_button1);
        gbc_button1.gridx = 1;
        gbc_button1.gridy = 0;
        panel_button1.add(jButton_insert,gbc_button1);
        gbc_button1.gridx = 2;
        gbc_button1.gridy = 0;
        panel_button1.add(jButton_delete,gbc_button1);
        gbc_button1.gridx = 1;
        gbc_button1.gridy = 1;
        panel_button1.add(jButton_clear,gbc_button1);
        panel_button2.add(jButton_function,gbc_button2);
        gbc_button2.gridx = 1;
        gbc_button2.gridy = 0;
        panel_button2.add(jButton_observation,gbc_button2);
        gbc_button2.gridx = 2;
        gbc_button2.gridy = 0;
        panel_button2.add(jButton_end_contract,gbc_button2);
        gbc_button2.gridx = 1;
        gbc_button2.gridy = 1;
        gbc_button2.anchor =GridBagConstraints.CENTER;
        //panel_button2.add(jButton_export_excel,gbc_button2);
        gbc_button2.gridx = 0;
        gbc_button2.gridy = 1;
        panel_button2.add(jButton_insert_excel,gbc_button2);
        gbc_button2.gridx = 2;
        gbc_button2.gridy = 1;
        panel_button2.add(jButton_close_contract,gbc_button2);
        gbc.gridx = 1;
        gbc.gridy = 0;
        scrollPane.setOpaque(false);
        //scrollPane.setBackground(Color.YELLOW);
        scrollPane.getViewport().setBackground(new java.awt.Color(0,0,153));
        //scrollPane.getViewport().setOpaque(false);
        panel.add(scrollPane,gbc);
        /*gbc.gridx = 0;
        gbc.gridy = 1;
        //panel.add(panel_button1,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;*/
        //panel.add(panel_button2,gbc);
        panel.setOpaque(false);
        this.setJMenuBar(menuBar);
        Background background = new Background("E8.jpg",2000,2000); 
        background.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        background.add(panel_modifier,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        scrollPane.setOpaque(false);
        background.add(scrollPane,gbc);
        //background.add(panel);
        this.getContentPane().add(background);
        //jComboBox_function.setBounds(new Rectangle(17, 13, 326, 26));
        ImageIcon img = new ImageIcon(getClass().getResource("LOGOEA.png"));
        this.setIconImage(img.getImage());
        this.setResizable(false);
        this.pack();
        this.setSize(2000, 2000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        alert_end_contract();
    }
    public class Adapter extends KeyAdapter{
        @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                Search();
                jLabel1.setVisible(true);
                jTextField_registration_number.setVisible(true);
                jLabel8.setVisible(true);
                jTextField_cnps.setVisible(true);
                jLabel2.setVisible(true);
                jTextField_surname.setVisible(true);
                jLabel3.setVisible(true);
                jTextField_name.setVisible(true);
                jLabel4.setVisible(true);
                jComboBox_function.setVisible(true);
                jLabel5.setVisible(true);
                datePicker_entry_date.setVisible(true);
                jLabel6.setVisible(true);
                jTextField_length.setVisible(true);
                jLabel10.setVisible(true);
                jComboBox_contract.setVisible(true);
                jLabel11.setVisible(true);
                jComboBox_internship.setVisible(true);
                jLabel7.setVisible(true);
                jComboBox_observation.setVisible(true);
                jLabel9.setVisible(false);
                jTextField_user.setVisible(false);
                jButton_search.setVisible(false);
                Clear();
                /*boolean_search_surname_name_rn = false;
                boolean_search_entry_date =false;
                boolean_search_user =false;
                boolean_search_contract = false;*/        
                }
            }
    }
    public class DateLabelFormatter extends AbstractFormatter {

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
  private void jTable_Display_EmployeeMouseClicked(java.awt.event.MouseEvent evt) {                                                  
       // Get The Index Of The Slected Row 
        jComboBox_internship.setEnabled(true);
        int i = jTable_Display_Employee.getSelectedRow();
        ID = IDList.get(i);
        TableModel model = jTable_Display_Employee.getModel();
         // Display Slected Row In JTexteFields
        jTextField_registration_number.setText(model.getValueAt(i,0).toString());
        jTextField_cnps.setText(model.getValueAt(i,1).toString());
        jTextField_surname.setText(model.getValueAt(i,2).toString());
        jTextField_name.setText(model.getValueAt(i,3).toString());
        jComboBox_function.setSelectedItem(model.getValueAt(i,4).toString());
        datePicker_entry_date.getJFormattedTextField().setText(model.getValueAt(i,5).toString());
        String length = model.getValueAt(i,7).toString();
        //length.replace("mois", "");
        int index = length.indexOf("M");
        jTextField_length.setText(length.substring(0,index));
        String internship = "STAGE";
        
        if(model.getValueAt(i,8).toString().contains(internship)){
            jComboBox_contract.setSelectedItem("CONTRAT DE STAGE");
            jComboBox_internship.setSelectedItem(model.getValueAt(i,8).toString());   
        }
        else{
            jComboBox_contract.setSelectedItem(model.getValueAt(i,8).toString());
            jComboBox_internship.setSelectedItem(""); 
            jComboBox_internship.setEnabled(false);
        }
        //datePicker_end_date.getJFormattedTextField().setText(model.getValueAt(i,5).toString());
        jComboBox_observation.setSelectedItem(model.getValueAt(i,9).toString());
    } 
   private void jButton_UpdateActionPerformed(java.awt.event.ActionEvent evt) {  
       String contract;
       if(jComboBox_contract.getSelectedItem().equals("CONTRAT DE STAGE"))
           contract = jComboBox_internship.getSelectedItem().toString();
       else{
           contract = jComboBox_contract.getSelectedItem().toString();
       }
       String query = "UPDATE employee SET registration_number='"+jTextField_registration_number.getText()+"',cnps='"+jTextField_cnps.getText()+"',surname = '"+jTextField_surname.getText()+"',name = '"+jTextField_name.getText()+"', ID_function = (select ID_function from function where description = '"+jComboBox_function.getSelectedItem()+"'),entry_date = '"+datePicker_entry_date.getJFormattedTextField().getText()+"', length = '"+jTextField_length.getText()+"', ID_contract = (select ID_contract from contract where contract = '"+contract+"'), ID_observation = (select ID_observation from observation where observation = '"+jComboBox_observation.getSelectedItem()+"'), date_modified = NOW(), user_date_modified ='"+user+"' WHERE ID_employee = "+ID;
       executeSQlQuery(query, "modifié");
    }   
   private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {                                               
        String query = "UPDATE employee SET status = 0 WHERE ID_employee = "+ID;
         executeSQlQuery(query, "supprimé");
    }  
   private void jButton_InsertActionPerformed(java.awt.event.ActionEvent evt) {
       String contract;
       if(jComboBox_contract.getSelectedItem().equals("CONTRAT DE STAGE"))
           contract = jComboBox_internship.getSelectedItem().toString();
       else{
           contract = jComboBox_contract.getSelectedItem().toString();
       }
        String query = "INSERT INTO employee(registration_number,cnps, surname, name, entry_date, length, status, date_created, user_date_created, date_modified,user_date_modified,ID_function,ID_observation, ID_contract) VALUES ('"+jTextField_registration_number.getText()+"','"+jTextField_cnps.getText()+"','"+jTextField_surname.getText()+"','"+jTextField_name.getText()+"','"+datePicker_entry_date.getJFormattedTextField().getText()+"','"+jTextField_length.getText()+"',1,NOW(),'"+user+"',NOW(),'"+user+"',(select ID_function from function where description = '"+jComboBox_function.getSelectedItem()+"'),(select ID_observation from observation where observation = '"+jComboBox_observation.getSelectedItem()+"'),(select ID_contract from contract where contract = '"+contract+"'))";  
        executeSQlQuery(query, "ajouté");
    }    
   public void executeSQlQuery(String query, String message)
   {
       Connection con = populate.getConnection();
       Statement st;
       try{
           st = con.createStatement();
           {
           if((st.executeUpdate(query)) == 1){
               JOptionPane.showMessageDialog(null, "Employé "+message+" avec succès");
               // refresh jtable data
               //DefaultTableModel model = (DefaultTableModel)jTable_Display_Employee.getModel();
               //model.setRowCount(0);
               if(boolean_search_surname_name_rn == true || boolean_search_entry_date == true || boolean_search_user == true)
               Search();
               else{
                    String query_populate = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract  where e.status = 1";
                    populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList,query_populate);
               }
               Clear();
               alert_end_contract();
           }else{
               JOptionPane.showMessageDialog(null, "Employé non "+message);
           }
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
   public void alert_end_contract(){
       Connection connection = populate.getConnection();
       String query = "SELECT * FROM  employee where (TO_DAYS(DATE_ADD(entry_date, INTERVAL length MONTH)) - TO_DAYS(CURDATE()) <= 60) AND (TO_DAYS(DATE_ADD(entry_date, INTERVAL length MONTH)) > TO_DAYS(CURDATE())) AND status = 1";
       Statement st;
       ResultSet rs;
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           if(rs.next() == true)
           {
               JOptionPane.showMessageDialog(null, "Certains employés ont leur contrat qui finit dans deux mois ou moins veuillez consulter la liste de ceux-ci");
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   public void getUsername(String user){
        this.user = user;
    }
   private static void writeToExcell(JTable table, Path path) throws FileNotFoundException, IOException {
    new WorkbookFactory();
    Workbook wb = new XSSFWorkbook(); //Excell workbook
    Sheet sheet = wb.createSheet(); //WorkSheet
    Row row = sheet.createRow(1); //Row created at line 2
    TableModel model = table.getModel(); //Table model


    Row headerRow = sheet.createRow(0); //Create row at line 0
    for(int headings = 0; headings < 7; headings++){ //For each column
        headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
    }
    headerRow.createCell(7).setCellValue(model.getColumnName(11));
    for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        for(int cols = 0; cols < 7; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }
        row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((rows + 2)); 
    }
    wb.write(new FileOutputStream(path.toString()));//Save the file 
    JOptionPane.showMessageDialog(null, "Données exportées ");    
}
   private void Search(){
       if (boolean_search_surname_name_rn == true){
           if (jTextField_registration_number.getText().isEmpty() == false && jTextField_surname.getText().isEmpty() && jTextField_name.getText().isEmpty()){
               String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract where e.status = 1 AND registration_number = '"+jTextField_registration_number.getText().toString()+"'";
               populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList, query);
           }
           if (jTextField_registration_number.getText().isEmpty() && jTextField_surname.getText().isEmpty()== false && jTextField_name.getText().isEmpty()){
               String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract where e.status = 1 AND surname = '"+jTextField_surname.getText().toString()+"'";
               populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList, query);
           }
           if (jTextField_registration_number.getText().isEmpty() && jTextField_surname.getText().isEmpty() && jTextField_name.getText().isEmpty() == false){
               String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract where e.status = 1 AND name = '"+jTextField_name.getText().toString()+"'";
               populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList, query);
           }
           if (jTextField_registration_number.getText().isEmpty() && jTextField_surname.getText().isEmpty()== false && jTextField_name.getText().isEmpty() == false){
               String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract where e.status = 1 AND surname = '"+jTextField_surname.getText().toString()+"' AND name = '"+jTextField_name.getText().toString()+"'";
               populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList, query);
           }
           if (jTextField_registration_number.getText().isEmpty() == false && jTextField_surname.getText().isEmpty() && jTextField_name.getText().isEmpty() == false){
               String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract where e.status = 1 AND registration_number = '"+jTextField_registration_number.getText().toString()+"' AND name = '"+jTextField_name.getText().toString()+"'";
               populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList, query);
           }
           if (jTextField_registration_number.getText().isEmpty() == false && jTextField_surname.getText().isEmpty() == false && jTextField_name.getText().isEmpty()){
               String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract where e.status = 1 AND registration_number = '"+jTextField_registration_number.getText().toString()+"' AND surname = '"+jTextField_surname.getText().toString()+"'";
               populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList, query);
           }
           if(jTextField_registration_number.getText().isEmpty() == false && jTextField_surname.getText().isEmpty() == false && jTextField_name.getText().isEmpty() == false){
                String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract where e.status = 1 AND registration_number = '"+jTextField_registration_number.getText().toString()+"' AND surname = '"+jTextField_surname.getText().toString()+"' AND name = '"+jTextField_name.getText().toString()+"'";
                populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList, query);
           }
       }
       if(boolean_search_entry_date == true){
           String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract where e.status = 1 AND entry_date = '"+datePicker_entry_date.getJFormattedTextField().getText().toString()+"'";
           populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList, query);
       }
       if(boolean_search_user == true){
           String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract where e.status = 1 AND e.user_date_created = '"+jTextField_user.getText().toString()+"'";
           populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList, query);
       }
       if(boolean_search_contract == true){
           String contract;
            if(jComboBox_contract.getSelectedItem().equals("CONTRAT DE STAGE"))
                contract = jComboBox_internship.getSelectedItem().toString();
            else{
                contract = jComboBox_contract.getSelectedItem().toString();
            }
           String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract where e.status = 1 AND contract = '"+contract+"'";
           populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList, query);
       }
   }
   public void Clear(){
       jTextField_registration_number.setText("");
       jTextField_cnps.setText("");
       jTextField_surname.setText("");
       jTextField_name.setText("");
       jComboBox_function.setSelectedIndex(0);
       datePicker_entry_date.getJFormattedTextField().setText("");
       jTextField_length.setText("");
       jComboBox_contract.setSelectedIndex(0);
       jComboBox_internship.setSelectedIndex(0);
       //datePicker_end_date.getJFormattedTextField().setText("");
       jComboBox_observation.setSelectedIndex(0);
       jTextField_user.setText("");
   }
   public void Contract(){
    if(jComboBox_contract.getSelectedItem() != null){
        if(jComboBox_contract.getSelectedItem().equals("CONTRAT DE STAGE")){
            jComboBox_internship.setEnabled(true);
        }
        if(jComboBox_contract.getSelectedItem().equals("CONTRAT DE STAGE") == false){
            jComboBox_internship.setSelectedItem("");
            jComboBox_internship.setEnabled(false);
        }
        if(jComboBox_internship.getSelectedItem() != null){
            if(jComboBox_internship.getSelectedItem().toString().contains("STAGE")){
                jComboBox_contract.setSelectedItem("CONTRAT DE STAGE");
            }
        }
    }
  }
}
