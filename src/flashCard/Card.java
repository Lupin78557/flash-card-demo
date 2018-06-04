package flashCard;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maus
 */
public class Card {
    
    private String question;
    private String answer;
    
    public Card(String question,String answer){
        this.question=question;
        this.answer= answer;
    }

    String getQuestion() {
        return question;
    }

    String getAnswer() {
        return answer;
    }
    
    
}
