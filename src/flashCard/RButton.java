package flashCard;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
/**
 *
 * @author maus
 */
public class RButton extends JButton{
    
    public RButton(String text,int t){
        super(text);
        this.setBackground(Color.red);
        this.setOpaque(false);
        this.setForeground(Color.white);
        this.setFont(new Font("Alba Super",Font.BOLD,t));
        this.setBorderPainted(false);
    }
}
