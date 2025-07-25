import javax.swing.*;

public class App{
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() { //allow gui to be created and updated in thread-safe manner
            @Override
            public void run(){
                RockPaperScissorGUI rockPaperScissorGui = new RockPaperScissorGUI(); //instantiate a rps gui obj
                rockPaperScissorGui.setVisible(true); //make the gui visible
            }
        });
    }
}
