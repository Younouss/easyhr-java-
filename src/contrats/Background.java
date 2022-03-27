/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;

import static com.sun.webkit.graphics.WCImage.getImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.nio.file.Path;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class Background extends JPanel{
    private Image img;
    private int width;
    private int height;

  /*public Background(String img) {
    this(new ImageIcon(img).getImage());
  }*/

  public Background(String path, int width, int height) {
    this.img = Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
    this.width = width;
    this.height = height;
    //this.img = img;
  }

  @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0,width,height, this);
    }

}
