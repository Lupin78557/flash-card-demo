package flashCard;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class QuizCardBuilder {
    
    private RFrame frame;
    private RTextArea answer;
    private RTextArea question;
    private ArrayList<Card> cardList = new ArrayList<Card>();
    Font jakartaFont = new Font("High Jakarta",Font.PLAIN,20);
    Font lactosaFont = new Font("Lactosa",Font.PLAIN,25);
    File openedFile;
    Card c;
    RMenuItem saveChangesMenuItem;
    RMenuItem saveMenuItem;
 
    public void start(){
        
        frame = new RFrame();
        RPanel panel = new RPanel();
        question = new RTextArea(4,10,new Color(77,165,110));
        RScrollPane qScroller = new RScrollPane(question);
        answer = new RTextArea(4,10,new Color(237,127,54));
        RScrollPane aScroller = new RScrollPane(answer);
        RButton btnNext = new RButton("                    Next Card                    ",30);
        btnNext.addActionListener(new btnNextListener());
        RLabel lblQuestion = new RLabel("Question:");
        RLabel lblAnswer = new RLabel("Answer:");
 
        
        RMenuBar menuBar = new RMenuBar();
        RMenu fileMenu = new RMenu("File");
        
        RMenuItem newMenuItem = new RMenuItem("New");
        newMenuItem.addActionListener(new newMenuItemListener());
        saveMenuItem = new RMenuItem("Save");
        saveMenuItem.addActionListener(new saveMenuItemListener());
        //saveMenuItem.setVisible(false);
        saveChangesMenuItem = new RMenuItem("Save Changes");
        saveChangesMenuItem.addActionListener(new saveChangesMenuItemListener());
        saveChangesMenuItem.setVisible(false);
        RMenuItem openMenuItem = new RMenuItem("Open");
        openMenuItem.addActionListener(new openMenuItemListener());
        RMenuItem exitMenuItem = new RMenuItem("Quit");
        exitMenuItem.addActionListener(new exitMenuItemListener());
        
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveChangesMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(exitMenuItem);
       
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
    
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(lblQuestion);
        panel.add(qScroller);
        panel.add(lblAnswer);
        panel.add(aScroller);
        panel.add(btnNext);
        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class btnNextListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            c = new Card(question.getText(),answer.getText());
            cardList.add(c);
            cleanCard();
        }
    }

    private class newMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardList.clear();
            cleanCard();
        }
    }

    private class saveMenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
           c = new Card(question.getText(),answer.getText());
           cardList.add(c);
           cleanCard();
           JFileChooser fs = new JFileChooser();
           fs.showSaveDialog(frame);
           saveFile(fs.getSelectedFile());
           cardList.clear();
        }
    }
    
    private void saveFile(File f){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            for(Card c: cardList){
                writer.write(c.getQuestion()+"/");
                writer.write(c.getAnswer()+"\n");
            }
            writer.close();
            
        }catch(IOException ex){
            System.out.println("screw you");
            ex.printStackTrace();
        }
    }
    
    private void cleanCard(){
        question.setText("");
        answer.setText("");
    
    }
    
    private class exitMenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Menu m = new Menu();
            frame.dispose();
            m.start();
        }
    }
    
    private class openMenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            saveChangesMenuItem.setVisible(true);
            saveMenuItem.setVisible(false);
            JFileChooser fs = new JFileChooser();
            fs.showOpenDialog(frame);
            openedFile=fs.getSelectedFile();
            cardList.clear();
            loadFile(openedFile);    
        }
    }
    
    public void loadFile(File f){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = null;
            while((line=reader.readLine())!=null){
                makeCard(line);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void makeCard(String line){
        String text[] = line.split("/");
        c=new Card(text[0],text[1]);
        cardList.add(c);
    }
    
    private class saveChangesMenuItemListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            c = new Card(question.getText(),answer.getText());
            cardList.add(c);
            cleanCard();
            saveFile(openedFile);
            saveChangesMenuItem.setVisible(false);
            saveMenuItem.setVisible(true);
            cardList=null;
        }
    }
}
