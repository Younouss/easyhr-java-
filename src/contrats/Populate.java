/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author h
 */
public class Populate {
    public  Connection getConnection()
    {
        Connection con;
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            //con = DriverManager.getConnection("jdbc:mysql://localhost/contrat", "root","");
            con = DriverManager.getConnection("jdbc:mysql://localhost/contrat", "root","");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
       }
   }
     public ArrayList<employee> getEmployeeList(ArrayList<Integer> IDList,String query)
   {
       ArrayList<employee> employeeList = new ArrayList<employee>();
       Connection connection = getConnection();
       
       //String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation where e.status = 1";
       Statement st;
       ResultSet rs,rs2;
       
       try {
           
           st = connection.createStatement();   
           rs = st.executeQuery(query);
           employee employee;
           IDList.clear();
           String contract;
           while(rs.next())
           {
               IDList.add(rs.getInt("ID_employee"));
               employee = new employee(rs.getString("registration_number"),rs.getString("cnps"),rs.getString("surname"),rs.getString("name"),rs.getString("description"),rs.getString("entry_date"),rs.getString("end_date"),rs.getInt("length"),rs.getString("contract"),rs.getString("observation"),rs.getString("date_created"),rs.getString("user_date_created"),rs.getString("date_modified"),rs.getString("user_date_modified"));
               employeeList.add(employee);
           }
       } catch (Exception e) {
                  
           e.printStackTrace();
       }
       return employeeList;
   }

    public void Show_Employee_In_JTable(JTable jTable_Display_Employee, ArrayList<Integer> IDList, String query)
   {
       ArrayList<employee> list = getEmployeeList(IDList,query);
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Employee.getModel();
       model.setRowCount(0);
       Object[] row = new Object[14];
       for(int i = 0; i < list.size(); i++)
       {   if(list.get(i).getRegistration_number() == null){
                row[0]= "";
           }
           else{
              row[0] = list.get(i).getRegistration_number();
           }
           if(list.get(i).getCnps() == null){
                row[1]= "";
           }
           else{
              row[1] = list.get(i).getCnps();
           }
           row[2] = list.get(i).getSurname();
           row[3] = list.get(i).getName();
           row[4] = list.get(i).getDescription();
           row[5] = list.get(i).getEntry_date();
           row[6] = list.get(i).getEnd_date();
           row[7] = list.get(i).getLength()+" MOIS";
           row[8] = list.get(i).getContract();
           if(list.get(i).getObservation() == null){
              row[9]= "";
           }
           else{
              row[9] = list.get(i).getObservation();
           }
           row[10] = list.get(i).getDate_created(); 
           row[11] = list.get(i).getUser_date_created();
           row[12] = list.get(i).getDate_modified();
           row[13] = list.get(i).getUser_date_modified();
           model.addRow(row);
       }
    }
    public void populate_observation(JComboBox<String> jComboBox_observation){
           jComboBox_observation.removeAllItems();
    Connection connection = getConnection();
    String query = "SELECT observation FROM  observation where status = 1 ";
     Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           jComboBox_observation.addItem("");
           while(rs.next())
           {
               jComboBox_observation.addItem(rs.getString("observation"));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
   public void populate_function(JComboBox<String> jComboBox_function){
        jComboBox_function.removeAllItems();
    Connection connection = getConnection();
    String query = "SELECT description FROM  function where status = 1 ";
     Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           while(rs.next())
           {
               jComboBox_function.addItem(rs.getString("description"));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
    public void populate_contract(DefaultComboBoxModel<String> contract_model, JComboBox<String> jComboBox_internship){
        contract_model.removeAllElements();
        jComboBox_internship.removeAllItems();
        Connection connection = getConnection();
        String query = "SELECT contract FROM  contract where status = 1 ";
        Statement st;
        ResultSet rs;
       
        try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           jComboBox_internship.addItem("");
           while(rs.next())
           {
               String internship = "STAGE";
               String contract = "CONTRAT DE STAGE";
               if(rs.getString("contract").contains(internship)){
                   jComboBox_internship.addItem(rs.getString("contract"));
                    if(contract_model.getIndexOf(contract) == -1){ 
                        contract_model.addElement(contract);
                    }
               }
               if(rs.getString("contract").contains(internship) == false){
                   contract_model.addElement(rs.getString("contract"));
               }
               
               //jComboBox_observation.addItem(rs.getString("observation"));
           }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
