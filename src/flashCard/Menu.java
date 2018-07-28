package flashCard;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;

public class Menu {
    
    RFrame frameP;
 
    
    public void start(){
        frameP = new RFrame();
        RPanel panelP = new RPanel();
        RButton btnMaker = new RButton("  >>> Builder Quiz Card",56);
        RButton btnReader = new RButton("Play <",150);
        RButton btnExit = new RButton(">>>>>>>>>>>>>>>>>>>> Exit ",59);
        btnExit.addActionListener(new btnExitListener());
        btnMaker.addActionListener(new btnMakerListener());
        btnReader.addActionListener(new btnPlayerListener());
        panelP.setLayout(new BoxLayout(panelP,BoxLayout.Y_AXIS));
        panelP.add(btnMaker);
        panelP.add(btnReader);
        panelP.add(btnExit);
        frameP.add(BorderLayout.CENTER,panelP);
        frameP.setLocationRelativeTo(null);
        frameP.setVisible(true);
    }
    
    private class btnMakerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCardBuilder q = new QuizCardBuilder();
            q.start();
            frameP.dispose();
        }
        
    }
    
    private class btnPlayerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCardPlayer p = new QuizCardPlayer();
            p.start();
            frameP.dispose();
        }
        
    }
    
    private class btnExitListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
}
