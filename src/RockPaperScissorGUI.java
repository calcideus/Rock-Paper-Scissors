//FRONTEND
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RockPaperScissorGUI extends JFrame implements ActionListener{ // so it'd act like a real gui and inherit from jframe
    //actionlistener connects the logic of the game to the gui
    // player buttons, global variable as will be working on later to add  listeners to the buttons
    JButton rockButton, paperButton, scissorButton;

    JLabel computerChoice;
    JLabel computerScoreLabel, playerScoreLabel;

    private BackgroundPanel backgroundPanel;

    RockPaperScissor rockPaperScissor; //backend object

    public RockPaperScissorGUI(){ //to put in app class
        super("Rock Paper Scissor"); //give title to app
        setSize(450, 574); //set size of the GUI
        setLayout(null); //null to disable layout management
        setLocationRelativeTo(null); // so the app is in the center of the screen

        //background panel
        backgroundPanel = new BackgroundPanel("Resource/images/bg.gif");
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        
        //initialize the backend obj
        rockPaperScissor = new RockPaperScissor();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addGUIComponents(); //add gui components
    }

    private void addGUIComponents(){ // abstraction ?? ('private')
        // computer score label
        computerScoreLabel = new JLabel("Computer: "); //make a label for score
        computerScoreLabel.setBounds(0, 43, 450, 30); // the x and y coordinates and width/height values of the label
        computerScoreLabel.setFont(new Font("Dialog", Font.BOLD, 26)); //the font and the size of the text
        computerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER); // alignment of the text
        add(computerScoreLabel); // add the label

        // computer choice labels
        computerChoice = new JLabel("?");
        computerChoice.setBounds(175, 118, 98, 81);
        computerChoice.setFont(new Font("Dialog", Font.PLAIN, 18));
        computerChoice.setHorizontalAlignment(SwingConstants.CENTER);

        computerChoice.setOpaque(true);
        computerChoice.setBackground(Color.WHITE);
        computerChoice.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // creates a border around '?'
        add(computerChoice);

        // player score label
        playerScoreLabel = new JLabel("Player: ");
        playerScoreLabel.setBounds(0, 320, 450, 30);
        playerScoreLabel.setFont(new Font("Dialog", Font.BOLD, 26));
        playerScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(playerScoreLabel);
        

        //buttons
        //rock button
        try {
            ImageIcon rockIcon = new ImageIcon("Resource/images/rock.gif"); //put image icons on buttons
            Image originalImage = rockIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(80, 60, Image.SCALE_REPLICATE);
            rockIcon = new ImageIcon(scaledImage);
            
            rockButton = new JButton(rockIcon); //instead of letters, connect it with the variable
            rockButton.setBounds(40, 387,105, 81);
            rockButton.setFont(new Font("Dialog", Font.PLAIN, 18));
            rockButton.addActionListener(this); //gui has the responsibility to respond whenever the action is pressed
            add(rockButton);
        } catch (Exception e) { //if the image thing fails
            rockButton = new JButton("Rock");
            rockButton.setBounds(40, 387,105, 81);
            rockButton.setFont(new Font("Dialog", Font.PLAIN, 18));
            rockButton.addActionListener(this); //gui has the responsibility to respond whenever the action is pressed
            add(rockButton);
        }

        // paper buttons
        try {
            ImageIcon paperIcon = new ImageIcon("Resource/images/paper.gif"); //put image icons on buttons
            Image originalImage = paperIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(80, 60, Image.SCALE_REPLICATE);
            paperIcon = new ImageIcon(scaledImage);

            paperButton = new JButton(paperIcon);
            paperButton.setBounds(165, 387, 105, 81);
            paperButton.setFont(new Font("Dialog",Font.PLAIN, 18));
            paperButton.addActionListener(this);
            add(paperButton);
        } catch (Exception e) {
            paperButton = new JButton("Paper");
            paperButton.setBounds(165, 387, 105, 81);
            paperButton.setFont(new Font("Dialog",Font.PLAIN, 18));
            paperButton.addActionListener(this);
            add(paperButton);
        }

        //scissor button
        try {
            ImageIcon scissorIcon = new ImageIcon("Resource/images/rock.gif"); //put image icons on buttons
            Image originalImage = scissorIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(80, 60, Image.SCALE_REPLICATE);
            scissorIcon = new ImageIcon(scaledImage);

            scissorButton = new JButton(scissorIcon);
            scissorButton.setBounds(290, 387, 105, 81);
            scissorButton.setFont(new Font("Dialog", Font.PLAIN, 18));
            scissorButton.addActionListener(this);
            add(scissorButton);
        } catch (Exception e) {
            scissorButton = new JButton("Scissor");
            scissorButton.setBounds(290, 387, 105, 81);
            scissorButton.setFont(new Font("Dialog", Font.PLAIN, 18));
            scissorButton.addActionListener(this);
            add(scissorButton);
        }

        //showDialog("test message"); // to test showDialog()
    }

    //display dialog is another dialog box that shows if player wins or loses and try again
    private void showDialog(String message){
        //owner represent the parent object (GUI) which is represented by 'this'
        //title  is the title on the title bar
        //modal needs the attention of the user and will block input from anything until it's closed
        JDialog resultDialog = new JDialog(this,"Result:",true);
        resultDialog.setSize(227, 124);
        resultDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        resultDialog.setResizable(false);

        //message label
        JLabel resultLabel = new JLabel(message);
        resultLabel.setFont(new Font("Dialog",Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultDialog.add(resultLabel,BorderLayout.CENTER);

        //try again button
        JButton tryAgainButton = new JButton("Try again?");
        tryAgainButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                computerChoice.setText("?"); //reverts back to '?' after choice, reset computer choice

                resultDialog.dispose(); //close dialog box
            }
        });
        resultDialog.add(tryAgainButton, BorderLayout.SOUTH); //try again button connects to result dialog
        resultDialog.setLocationRelativeTo(this); //the location of result is relative to the parent class (GUI)
        resultDialog.setVisible(true); //the result dialog is visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // get player choice
        String playerChoice = e.getActionCommand().toString();

        //play rock paper scissors and store result into string
        String result = rockPaperScissor.rpsLogic(playerChoice);

        //get computer choice
        computerChoice.setText(rockPaperScissor.getComputerChoice());

        computerScoreLabel.setText("Computer: " + rockPaperScissor.getComputerScore());
        playerScoreLabel.setText("Player: " + rockPaperScissor.getPlayerScore());

        showDialog(result);
    }

    class BackgroundPanel extends JPanel{ // put background image
        private Image backgroundImage;

        public BackgroundPanel(String imagePath){
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.out.println("Background image not found");
            }
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            if (backgroundImage != null){
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}

