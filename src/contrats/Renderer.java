/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author h
 */
 class Renderer extends DefaultTableCellRenderer {
  ArrayList<JLabel> list;
  public Renderer(ArrayList<JLabel> list){
    this.list = list;
  }
 //ImageIcon icon = new ImageIcon(getClass().getResource("sample.png"));

 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
  boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        label = list.get(row);
//lbl.setIcon(icon);
return label;
}
}