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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author h
 */
public class Contract extends JFrame{
    private javax.swing.JTable jTable_Display_Contract;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField_contract;
    private ArrayList<Integer> IDList;
    private ArrayList<Integer> IDList_employee;
    private int ID;
    private String user;
    private javax.swing.JScrollPane scrollPane;
    private  DefaultComboBoxModel<String> contract_model;
    private javax.swing.JComboBox <String>jComboBox_internship;
    private javax.swing.JTable jTable_Display_Employee;
    private JMenuBar menuBar;
    JMenu contractMenu;
    JMenuItem insert_contract, update_contract, delete_contract, clear_contract;
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
                new Contract().setVisible(true);
            }
        });
    }
    public Contract(){
        populate = new Populate();
        jTable_Display_Contract = new javax.swing.JTable();
         jTable_Display_Contract.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type de Contract","Type de Stage","Date de création", "Créateur", "Date de modification","Modificateur"
            }
        ));
        jTable_Display_Contract.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_ContractMouseClicked(evt);
            }
        });
        Show_Contract_In_JTable();
        jLabel1 =new JLabel();
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setText("Contrat:");
        jTextField_contract = new RoundedJTextField(20,"circle");
        jTextField_contract.setForeground(Color.WHITE);
        jTextField_contract.setBackground(new java.awt.Color(0,0,153));
        menuBar = new JMenuBar();
        contractMenu = new JMenu("Contrat");
        menuBar.add(contractMenu);
        insert_contract = new JMenuItem("Ajouter");
        insert_contract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertActionPerformed(evt);
            }
        });
        contractMenu.add(insert_contract);
        update_contract = new JMenuItem("Modifier");
        update_contract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateActionPerformed(evt);
            }
        });
        contractMenu.add(update_contract);
        delete_contract = new JMenuItem("Supprimer");
        delete_contract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });
        contractMenu.add(delete_contract);
        clear_contract = new JMenuItem("Effacer");
        clear_contract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               jTextField_contract.setText("");
            }
        });
        contractMenu.add(clear_contract);
        jTable_Display_Contract.setForeground(Color.WHITE);
        jTable_Display_Contract.setOpaque(false);
        jTable_Display_Contract.setBackground(new java.awt.Color(0,0,153));
        jTable_Display_Contract.setSelectionBackground(Color.YELLOW);
        jTable_Display_Contract.setFocusable(false);
        scrollPane = new JScrollPane(jTable_Display_Contract);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        scrollPane.setOpaque(false);
        //scrollPane.setBackground(Color.YELLOW);
        scrollPane.getViewport().setBackground(new java.awt.Color(0,0,153));
        Background background = new Background("E8.jpg",2000,2000);
        background.setLayout(new GridBagLayout());
        JPanel panel = new JPanel(new GridBagLayout());
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
        panel_modifier.add(jTextField_contract,gbc_modifier);
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
        background.add(panel_modifier,gbc);        
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(scrollPane,gbc);
        background.add(scrollPane,gbc);
        panel_modifier.setOpaque(false);
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
                populate.populate_contract(contract_model,jComboBox_internship);
                String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract  where e.status = 1";
                populate.Show_Employee_In_JTable(jTable_Display_Employee,IDList_employee,query);
            }
        });
    }
     public ArrayList<Contract_element> getContractList()
   {
       ArrayList<Contract_element> contractList = new ArrayList<Contract_element>();
       Connection connection = populate.getConnection();
       
       String query = "SELECT ID_contract,contract,date_created,user_date_created,date_modified,user_date_modified FROM contract where status = 1";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           Contract_element contract_element;
           IDList = new ArrayList<Integer>();
           while(rs.next())
           {
               IDList.add(rs.getInt("ID_contract"));
               String contract;
               String internship;
               if(rs.getString("contract").contains("STAGE")){
                   contract = "CONTRAT DE STAGE";
                   internship = rs.getString("contract");
               }
               else{
                   contract = rs.getString("contract");
                   internship = "";
               }
               contract_element = new Contract_element(contract,internship,rs.getString("date_created"),rs.getString("user_date_created"),rs.getString("date_modified"),rs.getString("user_date_modified"));
               contractList.add(contract_element);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return contractList;
   }
    public void Show_Contract_In_JTable()
   {
       ArrayList<Contract_element> list = getContractList();
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Contract.getModel();
       Object[] row = new Object[6];
       for(int i = 0; i < list.size(); i++)
       {   
           row[0] = list.get(i).getContract();
           row[1] = list.get(i).getInternship();
           row[2] = list.get(i).getDate_created(); 
           row[3] = list.get(i).getUser_date_created();
           row[4] = list.get(i).getDate_modified();
           row[5] = list.get(i).getUser_date_modified();
           model.addRow(row);
       }
    }
    private void jTable_Display_ContractMouseClicked(java.awt.event.MouseEvent evt) {                                                  
       // Get The Index Of The Slected Row 
        int i = jTable_Display_Contract.getSelectedRow();
        ID = IDList.get(i);
        TableModel model = jTable_Display_Contract.getModel();
         // Display Slected Row In JTexteFields
        if(model.getValueAt(i,0).toString() == "CONTRAT DE STAGE")
            jTextField_contract.setText(model.getValueAt(i,1).toString());
        else
            jTextField_contract.setText(model.getValueAt(i,0).toString());
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
               DefaultTableModel model = (DefaultTableModel)jTable_Display_Contract.getModel();
               model.setRowCount(0);
               Show_Contract_In_JTable();
               
               JOptionPane.showMessageDialog(null, "Contrat "+message+" avec succès");
           }else{
               JOptionPane.showMessageDialog(null, "Contrat non "+message);
           }
           }
           else
               JOptionPane.showMessageDialog(null, "Contrat déjà enregistré");
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
               DefaultTableModel model = (DefaultTableModel)jTable_Display_Contract.getModel();
               model.setRowCount(0);
               Show_Contract_In_JTable();
               
               JOptionPane.showMessageDialog(null, "Contrat "+message+" avec succès");
           }else{
               JOptionPane.showMessageDialog(null, "Contrat non "+message);
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
    private void jButton_UpdateActionPerformed(java.awt.event.ActionEvent evt) {      
       String verify_query = "SELECT * FROM contract WHERE contract = '"+jTextField_contract.getText()+"'";
       String query = "UPDATE contract SET contract = '"+jTextField_contract.getText()+"',date_modified = NOW(), user_date_modified ='"+user+"' WHERE ID_contract = "+ID;
       executeSQlQuery(verify_query, query, "modifié");
    } 
    private void jButton_InsertActionPerformed(java.awt.event.ActionEvent evt) { 
        String verify_query = "SELECT * FROM contract WHERE contract = '"+jTextField_contract.getText()+"'";
        String query = "INSERT INTO contract(contract, status, date_created, user_date_created, date_modified,user_date_modified) VALUES ('"+jTextField_contract.getText()+"',1,NOW(),'"+user+"',NOW(),'"+user+"')";  
        executeSQlQuery(verify_query, query, "ajouté");
    } 
        private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {  
        String query = "UPDATE contract SET status = 0 WHERE ID_contract = "+ID;
        executeSQlQuery(query, "supprimée");
    }
     public void getUsername(String user){
        this.user = user;
    }
    public void getContract_list(DefaultComboBoxModel<String> contract_model,JComboBox <String>jComboBox_internship){
        this.contract_model = contract_model;
        this.jComboBox_internship = jComboBox_internship;
    }
    public void getJTable_Display_Employee(JTable jTable_Display_Employee, ArrayList<Integer> IDList ){
        this.jTable_Display_Employee = jTable_Display_Employee;
        this.IDList_employee = IDList;
    }
}
