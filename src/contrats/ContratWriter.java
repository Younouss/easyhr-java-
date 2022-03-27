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
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HP
 */
public class ContratWriter extends JFrame{
    
    private JTable jTable_Display_Employee;
    FileChooser fileChooser;
    JButton export;
    JButton choose;
    JLabel label;
    File file;
    Path path;
    Boolean contrats,archive;
    public ContratWriter(){
        contrats = false;
        archive =false;
        fileChooser = new FileChooser();
        export = new RoundedJButton("Exporter");
        export.setBackground(Color.ORANGE);
        export.setFocusable(false);
        choose = new RoundedJButton("Choisir un fichier");
        choose.setFocusable(false);
        choose.setBackground(new java.awt.Color(204,204,204));
        label = new JLabel();
        choose.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){  
                JFileChooser c = new JFileChooser();
                int rVal = c.showOpenDialog(ContratWriter.this);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                   file = new File("");
                   file = c.getSelectedFile(); 
                   label.setText(file.getName());
                   path = Paths.get(file.getPath());
                } 
         }}); 
        export.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    writeToExcell(jTable_Display_Employee,path);
                    //JOptionPane.showMessageDialog(null, "Données exportées avec succès");
                } catch (IOException ex) {
                    Logger.getLogger(Contrats.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Problème lors de l'exportation des données");
                }
            }
        });
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                  50, 10, 0, 0), 0, 0);
        GridBagConstraints gbc2 = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
                  0, 0, 0, 0), 0, 0);
        panel.add(choose,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(export,gbc);
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
        this.setVisible(true);
        Adapter adapter = new Adapter();
        this.addKeyListener(adapter);
        
    }
    public class Adapter extends KeyAdapter{
        @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                 if (e.getKeyCode()==KeyEvent.VK_ENTER){
                      try {
                    writeToExcell(jTable_Display_Employee,path);
                    //JOptionPane.showMessageDialog(null, "Données exportées avec succès");
                } catch (IOException ex) {
                    Logger.getLogger(Contrats.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Problème lors de l'exportation des données");
                }
                 }
            }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContratWriter().setVisible(true);
            }
        });
        
    }
    public void getJTable_Display_Employee(JTable jTable_Display_Employee){
        this.jTable_Display_Employee = jTable_Display_Employee;
    }
    public void setContrats(Boolean contrats){
        this.contrats = contrats;
    }
    public void setArchive(Boolean archive){
        this.archive = archive;
    }
    private  void writeToExcell(JTable table, Path path) throws FileNotFoundException, IOException {
    new WorkbookFactory();
    Workbook wb = new XSSFWorkbook(); //Excell workbook
    Sheet sheet = wb.createSheet(); //WorkSheet
    Row row = sheet.createRow(1); //Row created at line 2
    TableModel model = table.getModel(); //Table model


    Row headerRow = sheet.createRow(0); //Create row at line 0
    if (contrats == true){
    for(int headings = 0; headings < 10; headings++){ //For each column
        headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
    }
    //headerRow.createCell(7).setCellValue(model.getColumnName(11));
    for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        for(int cols = 0; cols < 10; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }
        //row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((rows + 2)); 
    }
    }
    if (archive == true){
        for(int headings = 0; headings < 5; headings++){ //For each column
        headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
    }
    //headerRow.createCell(7).setCellValue(model.getColumnName(11));
    for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        for(int cols = 0; cols < 5; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }
        //row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((rows + 2)); 
    }
    }
    wb.write(new FileOutputStream(path.toString()));//Save the file 
    JOptionPane.showMessageDialog(null, "Données exportées ");    
}
}
