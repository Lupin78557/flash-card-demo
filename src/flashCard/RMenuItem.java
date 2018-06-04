package flashCard;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuItem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maus
 */
public class RMenuItem extends JMenuItem{
    Font lactosaFont = new Font("Lactosa",Font.PLAIN,25);
    public RMenuItem(String text){
            super(text);
            this.setFont(lactosaFont);
            this.setForeground(Color.white);
            this.setBackground(new Color(15,128,193));
        }
}
