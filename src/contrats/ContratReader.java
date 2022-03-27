/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author h
 */
public class ContratReader extends JFrame{

    /**
     * @param args the command line arguments
     */
    static String user;
    FileChooser fileChooser;
    JButton insert;
    JButton choose;
    JLabel label;
    File file;
    private Populate populate ;
    private JComboBox jComboBox_function;
    private JTable jTable_Display_Employee;
    private JComboBox jComboBox_observation;
    private ArrayList<Integer> IDList;
    private  DefaultComboBoxModel<String> contract_model;
    private javax.swing.JComboBox <String>jComboBox_internship;
    public ContratReader(){
        populate = new Populate();
        fileChooser = new FileChooser();
        insert = new RoundedJButton("Importer");
        insert.setBackground(Color.ORANGE);
        insert.setFocusable(false);
        choose = new RoundedJButton("Choisir un fichier");
        choose.setBackground(new java.awt.Color(204,204,204));
        choose.setFocusable(false);
        label = new JLabel();
        choose.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){  
                JFileChooser c = new JFileChooser();
                int rVal = c.showOpenDialog(ContratReader.this);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                   file = new File("");
                   file = c.getSelectedFile(); 
                   label.setText(file.getName());
                } 
         }}); 
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                  50, 10, 0, 0), 0, 0);
        GridBagConstraints gbc2 = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                  0, 0, 0, 0), 0, 0);
        //String excelFilePath = "C:\\Contrat.xlsx";
         insert.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
        FileInputStream inputStream = null;
        try {
            //inputStream = new FileInputStream(new File(excelFilePath));
            if (file == null){
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un fichier au format excel");
            }
            else{
                inputStream = new FileInputStream(file);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContratReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(ContratReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sheet firstSheet = workbook.getSheetAt(0);
        Row row;
        Connection connection = populate.getConnection();
        Statement st = null;
        ResultSet rs;
        String delete_contract_query = "UPDATE contract SET status = 0";
        String delete_function_query = "UPDATE function SET status = 0";
        String delete_observation_query = "UPDATE observation SET status = 0";
        String delete_employee_query = "UPDATE employee SET status = 0";
        /*String delete_contract_query = "DELETE FROM contract";
        String delete_function_query = "DELETE FROM function";
        String delete_observation_query = "DELETE FROM observation";
        String delete_employee_query = "DELETE FROM employee";*/
        UIManager.put("OptionPane.yesButtonText", "OUI");
        UIManager.put("OptionPane.noButtonText", "NON");
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer les données existantes?","",JOptionPane.YES_NO_OPTION);
        try {
           st = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ContratReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (input == 0){
         try {           
           
           st.executeUpdate(delete_employee_query);
           st.executeUpdate(delete_function_query);
           st.executeUpdate(delete_observation_query);
           st.executeUpdate(delete_contract_query);
           
           JOptionPane.showMessageDialog(null, "Informations supprimées avec succès");
         } catch (HeadlessException | SQLException es) {
           es.printStackTrace();
           JOptionPane.showMessageDialog(null, "Erreur lors de la suppression des données");
         }
        }
        for (int i = 1; i <= firstSheet.getLastRowNum (); ++i) {
            row=(Row) firstSheet.getRow(i);
            String registration_number = row.getCell(0).getStringCellValue();
            double cnps = row.getCell(1).getNumericCellValue();
            String surname = row.getCell(2).getStringCellValue();
            String name = row.getCell(3).getStringCellValue();
            String function = row.getCell(4).getStringCellValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String entry_date= dateFormat.format(row.getCell(5).getDateCellValue());
            String lengthString = row.getCell(6).getStringCellValue();
            int index = lengthString.indexOf("M"); 
            String length = lengthString.substring(0,index);
            String observation = row.getCell(7).getStringCellValue();
            String contract = row.getCell(8).getStringCellValue();
            /*if (row.getCell(6).getStringCellValue() == null){
                observation = "";
            }
            else{ 
                observation = row.getCell(6).getStringCellValue();
            }*/
            String insert_function_query = "INSERT INTO function(description, status, date_created, user_date_created, date_modified,user_date_modified) VALUES ('"+function+"',1,NOW(),'"+user+"',NOW(),'"+user+"')";
            String verify_function_query = "SELECT * FROM function WHERE description = '"+function+"'";
            String insert_observation_query = "INSERT INTO observation(observation, status, date_created, user_date_created, date_modified,user_date_modified) VALUES ('"+observation+"',1,NOW(),'"+user+"',NOW(),'"+user+"')";
            String verify_observation_query = "SELECT * FROM observation WHERE observation = '"+observation+"'";
            String insert_contract_query = "INSERT INTO contract(contract, status, date_created, user_date_created, date_modified,user_date_modified) VALUES ('"+contract+"',1,NOW(),'"+user+"',NOW(),'"+user+"')";
            String verify_contract_query = "SELECT * FROM contract WHERE contract = '"+contract+"'";
            String insert_employee_query = "INSERT INTO employee(registration_number,cnps,surname, name, entry_date, length, status, date_created, user_date_created, date_modified,user_date_modified,ID_function,ID_observation,ID_contract) VALUES ('"+registration_number+"','"+cnps+"','"+surname+"','"+name+"','"+entry_date+"','"+length+"',1,NOW(),'"+user+"',NOW(),'"+user+"',(select ID_function from function where description = '"+function+"'),(select ID_observation from observation where observation = '"+observation+"'),(select ID_contract from contract where contract = '"+contract+"'))"; 
            try {
                rs = st.executeQuery(verify_function_query);
                if(rs.next() == false){
                    st.executeUpdate(insert_function_query);
                    //JOptionPane.showMessageDialog(null, "Fonctions enregistrées avec succès");
                }
                rs = st.executeQuery(verify_observation_query);
                if(rs.next() == false){
                    if(observation != ""){
                        st.executeUpdate(insert_observation_query);
                        //JOptionPane.showMessageDialog(null, "Observations enregistrées avec succès");
                    }
                }
                rs = st.executeQuery(verify_contract_query);
                if(rs.next() == false){
                    st.executeUpdate(insert_contract_query);
                    //JOptionPane.showMessageDialog(null, "Fonctions enregistrées avec succès");
                }
                st.executeUpdate(insert_employee_query);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "L'employé(e) "+surname+" "+name+" n'a pas pu ^être inséré(e)");
                Logger.getLogger(ContratReader.class.getName()).log(Level.SEVERE, null, ex);
            }
           /* System.out.print(registration_number+" ");
            System.out.print(surname+" ");
            System.out.print(name+" ");
            System.out.print(function+" ");
            System.out.print(entry_date+" ");
            System.out.print(end_date+" ");
            System.out.println(observation);*/
        }
            /*Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext())
            { 
                Cell cell = cells.next();
                if (cell.getCellType() == CellType.STRING){
                    System.out.print(cell.getStringCellValue());
                }
                if (cell.getCellType() == CellType.BOOLEAN){
                    System.out.print(cell.getBooleanCellValue());
                }
                if (cell.getCellType() == CellType.NUMERIC){
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        System.out.print(dateFormat.format(cell.getDateCellValue()));
                    } else {
                        System.out.print(cell.getNumericCellValue());
                    }
                    //System.out.print(cell.getNumericCellValue());
                }
                System.out.print(" - ");
            }
            System.out.println();
        }
        
       /* Iterator<Row> iterator = firstSheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
           
                if (cell.getCellType() == CellType.STRING){
                    System.out.print(cell.getStringCellValue());
                }
                if (cell.getCellType() == CellType.BOOLEAN){
                    System.out.print(cell.getBooleanCellValue());
                }
                if (cell.getCellType() == CellType.NUMERIC){
                    System.out.print(cell.getNumericCellValue());
                }
                System.out.print(" - ");
            }
            System.out.println();
        }*/
        JOptionPane.showMessageDialog(null, "Enregistrement terminé"); 
        
        try {
            workbook.close();
        } catch (IOException ex) {
            Logger.getLogger(ContratReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ContratReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        }}); 
        panel.add(choose,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(insert,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(label,gbc);
        panel.setOpaque(false);
        //this.getContentPane().setLayout(new GridBagLayout());
        Background background = new Background("500X200A.jpg",500,200);
        background.setLayout(new GridBagLayout());
        background.add(panel,gbc2);
        ImageIcon img = new ImageIcon(getClass().getResource("LOGOEA.png"));
        this.setIconImage(img.getImage());
        this.getContentPane().add(background);
        this.setSize(500, 200);
        this.setResizable(false);
        this.setFocusable(true);
        this.setVisible(true);
        Adapter adapter = new Adapter();
        this.addKeyListener(adapter);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                //new Contrats();
                populate.populate_function(jComboBox_function);
                populate.populate_observation(jComboBox_observation);
                populate.populate_contract(contract_model, jComboBox_internship);
                String query = "SELECT ID_employee,registration_number,cnps,surname,name,description,entry_date,DATE_ADD(entry_date, INTERVAL length MONTH) end_date,length,observation,e.date_created,e.user_date_created,e.date_modified,e.user_date_modified,contract FROM employee e  left join function f on e.ID_function = f.ID_function  left join observation o on o.ID_observation = e.ID_observation left join contract c on c.ID_contract = e.ID_contract  where e.status = 1";
                populate.Show_Employee_In_JTable(jTable_Display_Employee, IDList,query);
            }
        });
    }
    public class Adapter extends KeyAdapter{
        @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                 if (e.getKeyCode()==KeyEvent.VK_ENTER){
                     FileInputStream inputStream = null;
        try {
            //inputStream = new FileInputStream(new File(excelFilePath));
            if (file == null){
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un fichier au format excel");
            }
            else{
                inputStream = new FileInputStream(file);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContratReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(ContratReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        Sheet firstSheet = workbook.getSheetAt(0);
        Row row;
        Connection connection = populate.getConnection();
        Statement st = null;
        ResultSet rs;
        String delete_contract_query = "DELETE FROM contract";
        String delete_function_query = "DELETE FROM function";
        String delete_observation_query = "DELETE FROM observation";
        String delete_employee_query = "DELETE FROM employee";
         try {           
           st = connection.createStatement();
           st.executeUpdate(delete_employee_query);
           st.executeUpdate(delete_function_query);
           st.executeUpdate(delete_observation_query);
           //st.executeUpdate(delete_contract_query);
           
           JOptionPane.showMessageDialog(null, "Informations supprimées avec succès");
         } catch (HeadlessException | SQLException es) {
           es.printStackTrace();
           JOptionPane.showMessageDialog(null, "Erreur lors de la suppression des données");
         }
        for (int i = 1; i <= firstSheet.getLastRowNum (); ++i) {
            row=(Row) firstSheet.getRow(i);
            String registration_number = row.getCell(0).getStringCellValue();
            String cnps = row.getCell(1).getStringCellValue();
            String surname = row.getCell(2).getStringCellValue();
            String name = row.getCell(3).getStringCellValue();
            String function = row.getCell(4).getStringCellValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String entry_date= dateFormat.format(row.getCell(5).getDateCellValue());            
            double length = row.getCell(6).getNumericCellValue();
            String observation = row.getCell(7).getStringCellValue();
            String contract = row.getCell(8).getStringCellValue();
            /*if (row.getCell(6).getStringCellValue() == null){
                observation = "";
            }
            else{ 
                observation = row.getCell(6).getStringCellValue();
            }*/
            String insert_function_query = "INSERT INTO function(description, status, date_created, user_date_created, date_modified,user_date_modified) VALUES ('"+function+"',1,NOW(),'"+user+"',NOW(),'"+user+"')";
            String verify_function_query = "SELECT * FROM function WHERE description = '"+function+"'";
            String insert_observation_query = "INSERT INTO observation(observation, status, date_created, user_date_created, date_modified,user_date_modified) VALUES ('"+observation+"',1,NOW(),'"+user+"',NOW(),'"+user+"')";
            String verify_observation_query = "SELECT * FROM observation WHERE observation = '"+observation+"'";
            String insert_contract_query = "INSERT INTO contract(contract, status, date_created, user_date_created, date_modified,user_date_modified) VALUES ('"+contract+"',1,NOW(),'"+user+"',NOW(),'"+user+"')";
            String verify_contract_query = "SELECT * FROM contract WHERE contract = '"+contract+"'";
            String insert_employee_query = "INSERT INTO employee(registration_number,cnps,surname, name, entry_date, length, status, date_created, user_date_created, date_modified,user_date_modified,ID_function,ID_observation,ID_contract) VALUES ('"+registration_number+"','"+cnps+"','"+surname+"','"+name+"','"+entry_date+"','"+length+"',1,NOW(),'"+user+"',NOW(),'"+user+"',(select ID_function from function where description = '"+function+"'),(select ID_observation from observation where observation = '"+observation+"'),(select ID_contract from contract where contract = '"+contract+"'))"; 
            try {
                rs = st.executeQuery(verify_function_query);
                if(rs.next() == false){
                    st.executeUpdate(insert_function_query);
                    //JOptionPane.showMessageDialog(null, "Fonctions enregistrées avec succès");
                }
                rs = st.executeQuery(verify_observation_query);
                if(rs.next() == false){
                    if(observation != ""){
                        st.executeUpdate(insert_observation_query);
                        //JOptionPane.showMessageDialog(null, "Observations enregistrées avec succès");
                    }
                }
                rs = st.executeQuery(verify_contract_query);
                if(rs.next() == false){
                    st.executeUpdate(insert_contract_query);
                    //JOptionPane.showMessageDialog(null, "Fonctions enregistrées avec succès");
                }
                st.executeUpdate(insert_employee_query);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "L'employé(e) "+surname+" "+name+" n'a pas pu ^être inséré(e)");
                Logger.getLogger(ContratReader.class.getName()).log(Level.SEVERE, null, ex);
            }
           /* System.out.print(registration_number+" ");
            System.out.print(surname+" ");
            System.out.print(name+" ");
            System.out.print(function+" ");
            System.out.print(entry_date+" ");
            System.out.print(end_date+" ");
            System.out.println(observation);*/
        }
            /*Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext())
            { 
                Cell cell = cells.next();
                if (cell.getCellType() == CellType.STRING){
                    System.out.print(cell.getStringCellValue());
                }
                if (cell.getCellType() == CellType.BOOLEAN){
                    System.out.print(cell.getBooleanCellValue());
                }
                if (cell.getCellType() == CellType.NUMERIC){
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        System.out.print(dateFormat.format(cell.getDateCellValue()));
                    } else {
                        System.out.print(cell.getNumericCellValue());
                    }
                    //System.out.print(cell.getNumericCellValue());
                }
                System.out.print(" - ");
            }
            System.out.println();
        }
        
       /* Iterator<Row> iterator = firstSheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
           
                if (cell.getCellType() == CellType.STRING){
                    System.out.print(cell.getStringCellValue());
                }
                if (cell.getCellType() == CellType.BOOLEAN){
                    System.out.print(cell.getBooleanCellValue());
                }
                if (cell.getCellType() == CellType.NUMERIC){
                    System.out.print(cell.getNumericCellValue());
                }
                System.out.print(" - ");
            }
            System.out.println();
        }*/
        JOptionPane.showMessageDialog(null, "Enregistrement terminé"); 
        
        try {
            workbook.close();
        } catch (IOException ex) {
            Logger.getLogger(ContratReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            inputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ContratReader.class.getName()).log(Level.SEVERE, null, ex);
        }
                 }
            }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContratReader().setVisible(true);
            }
        });
    }
   
    public void getUsername(String user){
        this.user = user;
    }
    public void getFunction_list(JComboBox jComboBox_function){
        this.jComboBox_function = jComboBox_function;
    }
    public void getJTable_Display_Employee(JTable jTable_Display_Employee, ArrayList<Integer> IDList){
        this.jTable_Display_Employee = jTable_Display_Employee;
        this.IDList = IDList;
    }
    public void getObservation_list(JComboBox jComboBox_observation){
        this.jComboBox_observation = jComboBox_observation;
    }
    public void getContractList(DefaultComboBoxModel<String> contract_model,JComboBox <String>jComboBox_internship){
        this.contract_model = contract_model;
        this.jComboBox_internship = jComboBox_internship;
    }
}
