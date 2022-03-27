/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author h
 */
public class Function extends javax.swing.JFrame{
    //private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_insert;
    private javax.swing.JButton jButton_update;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JTable jTable_Display_Function;
    private javax.swing.JLabel jLabel1;
    private RoundedJTextField jTextField_function;
    private ArrayList<Integer> IDList;
    private ArrayList<Integer> IDList_employee;
    private int ID;
    private String user;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JComboBox<String> jComboBox_function;
    private javax.swing.JTable jTable_Display_Employee;
    private JMenuBar menuBar;
    JMenu functionMenu;
    JMenuItem insert_function, update_function, delete_function, clear_function;
    Populate populate;
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
                new Function().setVisible(true);
            }
        });
    }
    public Function(){
        populate = new Populate();
        jTable_Display_Function = new javax.swing.JTable();
         jTable_Display_Function.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fonction","Date de création", "Créateur", "Date de modification","Modificateur"
            }
        ));
         jTable_Display_Function.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_FunctionMouseClicked(evt);
            }
        });
        Show_Function_In_JTable();
        jLabel1 =new JLabel();
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setText("Fonction:");
        jTextField_function = new RoundedJTextField(20,"circle");
        jTextField_function.setForeground(Color.WHITE);
        jTextField_function.setBackground(new java.awt.Color(0,0,153));
        menuBar = new JMenuBar();
        functionMenu = new JMenu("Fonction");
        menuBar.add(functionMenu);
        insert_function = new JMenuItem("Ajouter");
        insert_function.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertActionPerformed(evt);
            }
        });
        functionMenu.add(insert_function);
        update_function = new JMenuItem("Modifier");
        update_function.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateActionPerformed(evt);
            }
        });
        functionMenu.add(update_function);
        delete_function = new JMenuItem("Supprimer");
        delete_function.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });
        functionMenu.add(delete_function);
        clear_function = new JMenuItem("Effacer");
        clear_function.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               jTextField_function.setText("");
            }
        });
        functionMenu.add(clear_function);
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
        /*jButton_delete = new JButton("Supprimer");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });*/
        jButton_clear = new JButton("Effacer");
        jButton_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               jTextField_function.setText("");
            }
        });
        jTable_Display_Function.setForeground(Color.WHITE);
        jTable_Display_Function.setOpaque(false);
        jTable_Display_Function.setBackground(new java.awt.Color(0,0,153));
        jTable_Display_Function.setSelectionBackground(Color.YELLOW);
        jTable_Display_Function.setFocusable(false);
        scrollPane = new JScrollPane(jTable_Display_Function);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        scrollPane.setOpaque(false);
        //scrollPane.setBackground(Color.YELLOW);
        scrollPane.getViewport().setBackground(new java.awt.Color(0,0,153));
        JPanel panel = new JPanel(new GridBagLayout());
        Background background = new Background("E8.jpg",2000,2000);
        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
                  0, 0, 0, 0), 0, 0);
        JPanel panel_modifier = new JPanel(new GridBagLayout());
        GridBagConstraints gbc_modifier = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
                 70, 10, 0, 0), 0, 0);
        panel_modifier.add(jLabel1,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 0;
        panel_modifier.add(jTextField_function,gbc_modifier);
        gbc_modifier.gridx = 0;
        gbc_modifier.gridy = 1;
        //panel_modifier.add(jButton_update,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 1;
        gbc_modifier.anchor =GridBagConstraints.CENTER;
        //panel_modifier.add(jButton_insert,gbc_modifier);
        gbc_modifier.gridx = 2;
        gbc_modifier.gridy = 1;
        /*panel_modifier.add(jButton_delete,gbc_modifier);
        gbc_modifier.gridx = 1;
        gbc_modifier.gridy = 2;*/
        //panel_modifier.add(jButton_clear,gbc_modifier);
        panel.add(panel_modifier,gbc);
       panel_modifier.setOpaque(false);
       background.add(panel_modifier,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        scrollPane.setOpaque(false);
        //scrollPane.getViewport().setOpaque(false);
        panel.add(scrollPane,gbc);
        background.add(scrollPane,gbc);
        ImageIcon img = new ImageIcon(getClass().getResource("LOGOEA.png"));
        this.setIconImage(img.getImage());
        this.setJMenuBar(menuBar);
        this.getContentPane().add(background);
        this.setSize(2000, 2000);
        this.setVisible(true);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {        

                //new Contrats();
                populate.populate_function(jComboBox_function);
                String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract  where e.status = 1";
                populate.Show_Employee_In_JTable(jTable_Display_Employee,IDList_employee,query);
            }
        });
    }
    public ArrayList<Function_element> getFunctionList()
   {
       ArrayList<Function_element> functionList = new ArrayList<Function_element>();
       Connection connection = populate.getConnection();
       
       String query = "SELECT ID_function,description,date_created,user_date_created,date_modified,user_date_modified FROM function where status = 1";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           Function_element function_element;
           IDList = new ArrayList<Integer>();
           while(rs.next())
           {
               IDList.add(rs.getInt("ID_function"));
               function_element = new Function_element(rs.getString("description"),rs.getString("date_created"),rs.getString("user_date_created"),rs.getString("date_modified"),rs.getString("user_date_modified"));
               functionList.add(function_element);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return functionList;
   }
    public void Show_Function_In_JTable()
   {
       ArrayList<Function_element> list = getFunctionList();
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Function.getModel();
       Object[] row = new Object[5];
       for(int i = 0; i < list.size(); i++)
       {   
           row[0] = list.get(i).getDescription();
           row[1] = list.get(i).getDate_created(); 
           row[2] = list.get(i).getUser_date_created();
           row[3] = list.get(i).getDate_modified();
           row[4] = list.get(i).getUser_date_modified();
           model.addRow(row);
       }
    }
    private void jTable_Display_FunctionMouseClicked(java.awt.event.MouseEvent evt) {                                                  
       // Get The Index Of The Slected Row 
        int i = jTable_Display_Function.getSelectedRow();
        ID = IDList.get(i);
        TableModel model = jTable_Display_Function.getModel();
         // Display Slected Row In JTexteFields
        jTextField_function.setText(model.getValueAt(i,0).toString());
    }
    public void executeSQlQuery(String verify_query, String query, String message)
   {
       Connection con = populate.getConnection();
       Statement st;
       ResultSet rs;
       try{
           st = con.createStatement();
           rs = st.executeQuery(verify_query);
           if(rs.next() == false){
           if((st.executeUpdate(query)) == 1)
           {
               // refresh jtable data
               DefaultTableModel model = (DefaultTableModel)jTable_Display_Function.getModel();
               model.setRowCount(0);
               Show_Function_In_JTable();
               
               JOptionPane.showMessageDialog(null, "Fonction "+message+" avec succès");
           }else{
               JOptionPane.showMessageDialog(null, "Fonction non "+message);
           }
           }
           else
               JOptionPane.showMessageDialog(null, "Fonction déjà enregistrée");
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
    public void executeSQlQuery(String query, String message)
   {
       Connection con = populate.getConnection();
       Statement st;
       try{
           st = con.createStatement();
           if((st.executeUpdate(query)) == 1)
           {
               // refresh jtable data
               DefaultTableModel model = (DefaultTableModel)jTable_Display_Function.getModel();
               model.setRowCount(0);
               Show_Function_In_JTable();
               
               JOptionPane.showMessageDialog(null, "Fonction "+message+" avec succès");
           }else{
               JOptionPane.showMessageDialog(null, "Fonction non "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
    private void jButton_UpdateActionPerformed(java.awt.event.ActionEvent evt) {   
       String verify_query = "SELECT * FROM function WHERE description = '"+jTextField_function.getText()+"'";
       String query = "UPDATE function SET description = '"+jTextField_function.getText()+"',date_modified = NOW(), user_date_modified ='"+user+"' WHERE ID_function = "+ID;
       executeSQlQuery(verify_query, query, "modifiéé");
    } 
    private void jButton_InsertActionPerformed(java.awt.event.ActionEvent evt) {
        String verify_query = "SELECT * FROM function WHERE description = '"+jTextField_function.getText()+"'";
        String query = "INSERT INTO function(description, status, date_created, user_date_created, date_modified,user_date_modified) VALUES ('"+jTextField_function.getText()+"',1,NOW(),'"+user+"',NOW(),'"+user+"')";  
        executeSQlQuery(verify_query, query, "ajoutée");
    } 
    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {  
        String query = "UPDATE function SET status = 0 WHERE ID_function = "+ID;
        executeSQlQuery(query, "supprimée");
    }
    public void getUsername(String user){
        this.user = user;
    }
    public void getFunction_list(JComboBox jComboBox_function){
        this.jComboBox_function = jComboBox_function;
    }
    public void getJTable_Display_Employee(JTable jTable_Display_Employee, ArrayList<Integer> IDList ){
        this.jTable_Display_Employee = jTable_Display_Employee;
        this.IDList_employee = IDList;
    }
}
