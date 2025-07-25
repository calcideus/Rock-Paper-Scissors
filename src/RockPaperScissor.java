import java.util.*;

//BACKEND
public class RockPaperScissor {
    private static final String[] computerChoices = {"Rock", "Paper", "Scissors"}; //computer choices
    private String computerChoice; //stores the computer choice
    private Random random; //generates random number assigned to the variables
    private int computerScore, playerScore;

    public String getComputerChoice(){
        return computerChoice;
    }

    public int getComputerScore(){
        return computerScore;
    }

    public int getPlayerScore(){
        return playerScore;
    }

    public RockPaperScissor(){ //to initialize the random object
        random = new Random();
    }

    public String rpsLogic(String playerChoice){ //method to start playing, this method will return the result of the game
        computerChoice = computerChoices[random.nextInt(computerChoices.length)];
        String result; //will return the result of the game

        if(computerChoice == "Rock"){
            if(playerChoice == "Paper"){
                result = "Player Wins";
                playerScore++;
            } else if(playerChoice == "Scissors"){
              result = "Compouter Wins";
                computerScore++;
            } else {
                result = "Draw";
            }
        } else if(computerChoice == "Paper"){
            if(playerChoice == "Rock"){
                result = "Player Wins";
                playerScore++;
            }else if(playerChoice == "Scissors"){
                result = "Computer Wins";
                computerScore++;
            } else {
                result = "Draw";
            }
        } else {
            if(playerChoice == "Rock"){
                result = "Player Wins";
                playerScore++;
            }else if(playerChoice == "Paper"){
                result = "Cpmputer Wins";
                computerScore++;
            }else{
                result = "Draw";
            }
        }
        return result;
    }
}
