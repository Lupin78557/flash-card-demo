package flashCard;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import javax.swing.BoxLayout;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
/**
 *
 * @author maus
 */
public class QuizCardPlayer {
    
    RFrame frame ;
    RPanel panel;
    RButton btnShow;
    ArrayList<Card> cardList = new ArrayList<Card>();
    Card card;
    RLabel lblQuestion = new RLabel("Guess the answer");
    RTextArea question;
    RTextArea answer;
    RScrollPane qScroller;
    RScrollPane aScroller;
    RMenuBar menuBar;
    RMenu fileMenu;
    RMenu optionsMenu;
    RMenuItem openMenuItem;
    RMenuItem exitMenuItem;
    RMenuItem randomMenuItem;
    RMenuItem plainMenuItem;
    File openedFile;
    boolean isShowAnswer;
    int index=0;
    
    public void start(){
        frame = new RFrame();
        panel = new RPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        btnShow = new RButton("Show Answer",30);
        btnShow.addActionListener(new btnShowListener());
        question = new RTextArea(4,10,new Color(77,165,110));
        question.setEditable(false);
        answer = new RTextArea(4,10,new Color(237,127,54));  
        answer.setEditable(false);
        qScroller = new RScrollPane(question);
        aScroller = new RScrollPane(answer);
        //aScroller.setVisible(false);
        
        menuBar = new RMenuBar();
        fileMenu = new RMenu("File");
        openMenuItem = new RMenuItem("Open");
        openMenuItem.addActionListener(new openMenuItemListener());
        exitMenuItem = new RMenuItem("Quit");
        exitMenuItem.addActionListener(new exitMenuItemListener());
        fileMenu.add(openMenuItem);
        fileMenu.add(exitMenuItem);
        optionsMenu = new RMenu("Options");
        randomMenuItem = new RMenuItem("Random");
        randomMenuItem.addActionListener(new randomMenuItemListener());
        plainMenuItem = new RMenuItem("Plain");
        plainMenuItem.addActionListener(new plainMenuItemListener());
        optionsMenu.add(plainMenuItem);
        optionsMenu.add(randomMenuItem);
        
        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        frame.setJMenuBar(menuBar);
        panel.add(lblQuestion);
        panel.add(qScroller);
        panel.add(btnShow);
        panel.add(aScroller);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private class btnShowListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(!isShowAnswer){
                answer.setText(card.getAnswer());
                btnShow.setText("Next Question");
                isShowAnswer=true;
            }else{
                answer.setText("");
                if(index<cardList.size()){
                    showNextCard();
                }else{
                    question.setText("We are sorry, that was the last card");
                    btnShow.setEnabled(false);
                    isShowAnswer=true;
                }
            }
        }
    }
    
    private void showNextCard(){
        card=cardList.get(index);
        question.setText(card.getQuestion());
        btnShow.setText("Show Answer");
        isShowAnswer=false;
        index++;
    }
    
    private class openMenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JFileChooser fs = new JFileChooser();
            fs.showOpenDialog(frame);
            openedFile = fs.getSelectedFile();
            loadFile(openedFile);
        }
    }
    
    public void loadFile(File f){
        
        try {
            cardList.clear();
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line = null;
            while((line=br.readLine())!=null){
                makeCard(line);
            }
            br.close();
            index=0;
            showNextCard();
            btnShow.setEnabled(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void makeCard(String line){
        String[] part = line.split("/");
        card = new Card(part[0],part[1]);
        cardList.add(card);
    }
    
    private class randomMenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            aScroller.setVisible(true);
        }
    }
    
    private class plainMenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            aScroller.setVisible(true);
        }
    }
    
    private class exitMenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Menu m = new Menu();
            m.start();
            frame.dispose();
        }
    }
    
}
