/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class End_contract extends javax.swing.JFrame{
    private javax.swing.JTable jTable_Display_Employee;
    private javax.swing.JScrollPane scrollPane;
    private JPanel panel;
    private Populate populate;
    public End_contract(){
        populate = new Populate();
        jTable_Display_Employee = new javax.swing.JTable();
         jTable_Display_Employee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricule","Numéro CNPS","Nom", "Prénoms","Date de début","Fin de contrat","Nombre de jours restants"
            }
        ));
        Show_Employee_In_JTable();
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
        Background background = new Background("E8.jpg",2000,2000);
        panel = new JPanel();
        panel.add(scrollPane);
        background.add(scrollPane);
        ImageIcon img = new ImageIcon(getClass().getResource("LOGOEA.png"));
        this.setIconImage(img.getImage());
        this.getContentPane().add(background);
        this.setResizable(false);
        this.setSize(2000, 2000);
        this.setVisible(true);
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
                new End_contract().setVisible(true);
            }
        });
    }
   public ArrayList<employee> getEmployeeList()
   {
       ArrayList<employee> employeeList = new ArrayList<employee>();
       Connection connection = populate.getConnection();
       
       String query = "SELECT registration_number,cnps,surname,name,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,(TO_DAYS(DATE_ADD(entry_date, INTERVAL length MONTH)) - TO_DAYS(NOW())) days FROM employee WHERE (TO_DAYS(DATE_ADD(entry_date, INTERVAL length MONTH)) - TO_DAYS(CURDATE()) <= 60) AND (TO_DAYS(DATE_ADD(entry_date, INTERVAL length MONTH)) > TO_DAYS(CURDATE())) AND status = 1";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           employee employee;
           while(rs.next())
           {
               employee = new employee(rs.getString("registration_number"),rs.getString("cnps"),rs.getString("surname"),rs.getString("name"),rs.getString("entry_date"),rs.getString("end_date"),rs.getInt("days"));
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
       Object[] row = new Object[7];
       for(int i = 0; i < list.size(); i++)
       {  
           row[0] = list.get(i).getRegistration_number();
           row[1] = list.get(i).getCnps();
           row[2] = list.get(i).getSurname();
           row[3] = list.get(i).getName();
           row[4] = list.get(i).getEntry_date();
           row[5] = list.get(i).getEnd_date();
           row[6] = list.get(i).getDays()+" jours";
           model.addRow(row);
       }
    } 
}
