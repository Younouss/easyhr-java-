/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;

/**
 *
 * @author HP
 */
import javax.swing.border.*;
import java.awt.*;
import org.apache.poi.xssf.model.Styles;
public class OvalBorder implements Border {
 
protected int m_w = 6;
protected int m_h = 6;
protected Color m_topColor = Color.gray;
protected Color m_bottomColor = Color.gray;
 
public OvalBorder() {
m_w = 6;
m_h = 6;
}
 
public OvalBorder(int w, int h) {
m_w = w;
m_h = h;
}
public OvalBorder(int w, int h, Color topColor, Color bottomColor) {
m_w = w;
m_h = h;
m_topColor = topColor;
m_bottomColor = bottomColor;
}
@Override 
public Insets getBorderInsets(Component c) {
return new Insets(m_h,m_w,m_h,m_w);
}
 @Override 
public boolean isBorderOpaque() {
return false;
}
@Override 
public void paintBorder(Component c,Graphics g, int x, int y, int w, int h) {
w--;
h--;
g.setColor(m_topColor);
g.drawLine(x, y+h-m_h, x, y+m_h);
g.drawArc(x,y, 2*m_w,2*m_h,180,-90);
g.drawLine(x+m_w,y, x+w-m_w,y);
g.drawArc(x+w-2*m_w, y, 2*m_w,2*m_h,90,-90);
 
g.setColor(m_bottomColor);
g.drawLine(x+w, y+m_h, x+w, y+h-m_h);
g.drawArc(x+w-2*m_w, y+h-2*m_h, 2*m_w, 2*m_h, 0,-90);
g.drawLine(x+m_w, y+h, x+w-m_w, y+h);
g.drawArc(x, y+h-2*m_h, 2*m_w, 2*m_h, -90,-90);
}
}