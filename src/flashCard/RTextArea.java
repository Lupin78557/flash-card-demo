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
public class RTextArea extends JTextArea{
     Font jakartaFont = new Font("High Jakarta",Font.PLAIN,20);
    public RTextArea(int h,int v,Color c){
        super(h,v);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setFont(jakartaFont);
        this.setForeground(c);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    this.setBackground(new Color(184,219,239));
    }
}
