package flashCard;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author maus
 */
public class RLabel extends JLabel{
    Font lactosaFont = new Font("Lactosa",Font.PLAIN,25);
    
    public RLabel(String text){
        super(text);
        this.setForeground(Color.white);
        this.setFont(lactosaFont);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    }
}
