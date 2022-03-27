/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contrats;

import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.util.TimerTask;
import javax.swing.JFrame;
import java.util.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author h
 */
public class Welcome extends JFrame {
    public static void main(String[] args) {
        // TODO code application logic here
          
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Welcome().setVisible(true);
            }
        });
    }
    public void Show_frame(){
        Background background = new Background("welcome.jpg",500,500);
        background.setLayout(new BorderLayout());
        JPanel panel =new JPanel();
        button.setOpaque(false);
        button.setSize(100, 100);
        button.setBackground(Color.ORANGE);
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hide_frame();
            }
        });
         Adapter adapter = new Adapter();
        button.setFocusable(false);
        button.addKeyListener(adapter);
        panel.add(button);
        panel.setOpaque(false);
        background.add(panel,BorderLayout.NORTH);
        copyright.setText("                       Développé par Optimum International(C) 2019 Tous droits réservés");
        copyright.setForeground(Color.WHITE);
        //textField.setBackground(new java.awt.Color(0,0,0,1));
        background.add(copyright,BorderLayout.PAGE_END);
        this.getContentPane().add(background);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        ImageIcon img = new ImageIcon(getClass().getResource("LOGOEA.png"));
        this.setIconImage(img.getImage());
        //setFocusable(true);
        this.addKeyListener(adapter);
    }
    public void Hide_frame(){
        this.dispose();
        Authentication authentication = new Authentication();
    }
    public class Adapter extends KeyAdapter{
        @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    Hide_frame();
                }
            }
    }
    RoundedJButton button = new RoundedJButton("Se Connecter");
    JLabel label = new JLabel();
    JLabel copyright = new JLabel()/*{
         @Override
    protected void paintBorder(Graphics g) {
         g.setColor(getBackground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, getWidth() - 1, getHeight());

    }
    }*/;
    static Timer timer;
    static int seconds = 0;
    public Welcome(){
         label.setText("<HTML><U>Se Connecter</U></HTML>");
         label.setForeground(Color.ORANGE);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*TimerTask task;
        timer = new Timer();

        task = new TimerTask() {
            @Override
            public void run() { 
                if  (seconds < 6) {*/
                    Show_frame();
                    seconds++;
                /*}
                else{*/
                    
                    //cancel();
                /*}
           }
        };
         timer.schedule(task, 0,1000);*/
    }
}
