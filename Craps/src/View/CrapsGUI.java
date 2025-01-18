/*
TCSS 305A
 */
package View;

import Controller.Main;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

/**
 * Contains all GUI and listener components for game.
 * Buttons, listeners, text fields, are all implemented
 * along with their appropriate behavior and properties.
 *
 * @author Eva Howard
 * @version Autumn 2023
 */
public class CrapsGUI {
    /** main frame for game. */
    private static final JFrame myFrame = new JFrame();
    /** boolean to keep track of whether it's the player's first roll. */
    private boolean myFirstRoll = true;
    /** text field for bank. */
    private static final JTextField myBankText = new JTextField();
    /** text field for bet. */
    private static final JTextField myBetText = new JTextField();
    /** player wins. */
    private static int myWins = 0;
    /** house wins. */
    private static int myHouseWins = 0;
    /** roll dice button. */
    private static final JButton myRollButton = new JButton("Roll Dice");
    /** play again button. */
    private static final JButton myPlayAgain = new JButton("Play Again");
    /** set bank and bet button. */
    private static final JButton mySetBank = new JButton("Set Bank And Bet");
    /** label for user wins. */
    private static final JLabel myWinLabel = new JLabel();
    /** label for player losses. */
    private static final JLabel myHouseLabel = new JLabel();
    /** label to displays win/lose after each round. */
    private static final JLabel myResult = new JLabel();
    /** label for dice 1. */
    private static final JLabel myD1Label = new JLabel();
    /** label for dice 2. */
    private static final JLabel myD2Label = new JLabel();
    /** label for sum. */
    private static final JLabel mySumLabel = new JLabel();
    /** label for point. */
    private static final JLabel myPointLabel = new JLabel();
    /** label for dice icon 1. */
    private static final JLabel myDiceImage1 = new JLabel();
    /** label for dice icon 2. */
    private static final JLabel myDiceImage2 = new JLabel();
    private static final JButton add5 = new JButton("+$5");
    private static final JButton add10 = new JButton("+$10");
    private static final JButton add20 = new JButton("+$20");
    private static final JButton add50 = new JButton("+$50");
    private static final JButton add100 = new JButton("+$100");

    /**
     * Constructor creates frame and calls methods that add
     * components to frame.
     */
    public CrapsGUI() {
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.LIGHT_GRAY);
        gamePanel.setBounds(0,0,800, 600);

        myFrame.setDefaultCloseOperation(myFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(null);
        myFrame.setSize(1050, 600);
        myFrame.setResizable(false);
        myFrame.setTitle("Craps");
        myFrame.setBackground(Color.lightGray);

        addMenuBar();
        addButtons();
        addLabels();
        addBank();
        addBetButtons();
        myFrame.setVisible(true);
        myFrame.add(gamePanel);
    }

    /**
     * Adds menu bar and menu items and wires in
     * properties and behavior of all menu items.
     */
    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        JMenuItem start = new JMenuItem("Start Alt+S");
        JMenuItem reset = new JMenuItem("Reset Alt+R");
        JMenuItem exit = new JMenuItem("Exit Alt+E");
        JMenuItem rules = new JMenuItem("Rules Alt+Q");
        JMenuItem about = new JMenuItem("About Alt+A");
        JMenuItem shortcuts = new JMenuItem("Shortcuts Alt+C");

        start.setMnemonic(KeyEvent.VK_S);
        reset.setMnemonic(KeyEvent.VK_R);
        exit.setMnemonic(KeyEvent.VK_E);
        rules.setMnemonic(KeyEvent.VK_Q);
        about.setMnemonic(KeyEvent.VK_A);
        shortcuts.setMnemonic(KeyEvent.VK_C);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        gameMenu.add(start);
        gameMenu.add(reset);
        gameMenu.add(exit);
        helpMenu.add(rules);
        helpMenu.add(about);
        helpMenu.add(shortcuts);

        //Listeners for menu bar
        start.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(null, "Please enter bank and bet amount.");
            myBankText.setEnabled(true);
            myBetText.setEnabled(true);
            mySetBank.setEnabled(true);
            editBetButtons(true);
            start.setEnabled(false);

        });
        reset.addActionListener(theEvent -> {
            myWinLabel.setText("Your Wins: 0");
            myHouseLabel.setText("House Wins: 0");
            myPointLabel.setText("Point: 0");
            myBankText.setText("");
            myBankText.setEnabled(true);
            myBetText.setText("");
            myBetText.setEnabled(true);
            myPlayAgain.setEnabled(false);
            mySetBank.setEnabled(true);
            editBetButtons(true);
            myResult.setText("");
        });
        exit.addActionListener(theEvent -> {
            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                    "Exit", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                System.exit(0);
            }
        });
        rules.addActionListener(theEvent -> JOptionPane.showMessageDialog(null, """
                Rules:
                Player rolls two 6-sided die. On the first roll if the sum of the die
                is 7 or 12 the player wins. If the sum is 2, 3, or 12 the house wins.
                If none of these, the sum becomes the player's "point" the player then
                has to keep rolling the die until the sum equals their point or they
                roll a 7 in which case they lose and the house wins."""));
        about.addActionListener(theEvent -> JOptionPane.showMessageDialog(null, """
                Name: Eva Howard
                App Version: IntelliJ IDEA Community Edition
                Java Version: Java 20"""));
        shortcuts.addActionListener(theEvent -> JOptionPane.showMessageDialog(null, """
                Start Alt+S
                Reset Alt+R
                Exit Alt+E
                Rules Alt+Q
                About Alt+A
                Shortcuts Alt+C
                Roll dice button Alt+D
                Play again button Alt+P
                Set Bank and Bet button Alt+B"""));
        myFrame.setJMenuBar(menuBar);
    }

    /**
     * Adds roll button and play again button to frame.
     */
    private void addButtons() {
        myRollButton.setBounds(325, 425, 150, 50);
        myRollButton.setFocusable(false);
        myRollButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
        myRollButton.setEnabled(false);
        myRollButton.setMnemonic(KeyEvent.VK_D);
        myRollButton.addActionListener(theEvent -> {
            Controller.Main.diceResults(myFirstRoll);
            myFirstRoll = false;
            myBankText.setEnabled(false);
            myBetText.setEnabled(false);
            try {
                playSound("dice.wav");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException();
            }
        });
        myPlayAgain.setBounds(675,10, 110, 40);
        myPlayAgain.setFocusable(false);
        myPlayAgain.setFont(new Font("Times New Roman", Font.BOLD, 15));
        myPlayAgain.setEnabled(false);
        myPlayAgain.setMnemonic(KeyEvent.VK_P);
        myPlayAgain.addActionListener(theEvent -> {
            myFirstRoll = true;
            myResult.setText("");
            myPlayAgain.setEnabled(false);
            myBetText.setEnabled(true);
            myPointLabel.setText("Point: 0");
            mySetBank.setEnabled(true);
            editBetButtons(true);
        });
        myFrame.add(myRollButton);
        myFrame.add(myPlayAgain);
    }

    /**
     * Adds all labels and properties to frame.
     */
    private static void addLabels() {
        myWinLabel.setBounds(130,5, 150, 50);
        myWinLabel.setText("Your Wins: 0");
        myWinLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

        myHouseLabel.setBounds(300, 5, 200, 50);
        myHouseLabel.setText("House Wins: 0");
        myHouseLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

        myResult.setFont(new Font("Times New Roman", Font.BOLD, 30));
        myResult.setForeground(Color.RED);

        myD1Label.setBounds(125, 275, 100, 100);
        myD1Label.setText("Die 1: 5");
        myD1Label.setFont(new Font("Times New Roman", Font.BOLD, 20));

        myD2Label.setBounds(600, 275, 100, 100);
        myD2Label.setText("Die 2: 3");
        myD2Label.setFont(new Font("Times New Roman", Font.BOLD, 20));

        mySumLabel.setBounds(370, 325, 100, 100);
        mySumLabel.setText("Sum: 8");
        mySumLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        myPointLabel.setBounds(10, 5, 200, 50);
        myPointLabel.setText("Point: 0");
        myPointLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

        myDiceImage1.setIcon(getImage(5));
        myDiceImage2.setIcon(getImage(3));

        myDiceImage1.setBounds(95, 175, 125, 125);
        myDiceImage2.setBounds(570, 175, 125, 125);

        myFrame.add(myWinLabel);
        myFrame.add(myHouseLabel);
        myFrame.add(myResult);
        myFrame.add(myD1Label);
        myFrame.add(myD2Label);
        myFrame.add(mySumLabel);
        myFrame.add(myPointLabel);
        myFrame.add(myDiceImage1);
        myFrame.add(myDiceImage2);
    }

    /**
     * Gets data from model via controller and updates fields and labels
     * based on data passed in from when dice are rolled.
     * @param theD1 dice 1
     * @param theD2 dice 2
     * @param theSum sum of die
     * @param thePoint the point
     */
    public static void setRollResults(int theD1, int theD2, int theSum, int thePoint) {
        myD1Label.setText("Die 1: " + theD1);
        myD2Label.setText("Die 2: " + theD2);
        mySumLabel.setText("Sum: " + theSum);
        myPointLabel.setText("Point: " + thePoint);

        myDiceImage1.setIcon(getImage(theD1));
        myDiceImage2.setIcon(getImage(theD2));

    }

    /**
     * Creates new image icon that is resized.
     * @param theDie die number
     * @return image icon of die
     */
    private static ImageIcon getImage(int theDie) {
        ImageIcon diceImage = new ImageIcon(theDie + ".jpg");
        return resizeImage(diceImage);
    }

    /**
     * Resizes image of die.
     * @param theImage Image to resize
     * @return resized Image icon
     */
    private static ImageIcon resizeImage(ImageIcon theImage) {
        Image resized = theImage.getImage().getScaledInstance(125,125,Image.SCALE_DEFAULT);
        return new ImageIcon(resized);
    }

    /**
     * Updates fields and labels when player wins or loses.
     * @param theWin whether player won or not
     * @param theBank new bank value
     */
    public static void updateScore(boolean theWin, int theBank) {
        myRollButton.setEnabled(false);
        myPlayAgain.setEnabled(true);
        if (theWin) {
            try {
                playSound("win.wav");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException();
            }
            myWins++;
            myWinLabel.setText("Your Wins: " + myWins);
            myResult.setBounds(165, 10, 600, 200);
            myResult.setText("CONGRATULATIONS YOU WON!");
        } else {
            try {
                playSound("lose.wav");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException();
            }
            myHouseWins++;
            myHouseLabel.setText("House Wins: " + myHouseWins);
            myResult.setBounds(210, 10, 600, 200);
            myResult.setText("Wow you have the worst luck");
        }
        myBankText.setText(String.valueOf(theBank));
        myBetText.setText("");
        myBetText.setEnabled(true);
    }

    /**
     * Adds all bank and betting components, bank and bet labels,
     * and set bank button.
     */
    private static void addBank() {
        JLabel bank = new JLabel("Bank $");
        bank.setBounds(840, 55, 75, 20);
        bank.setFont(new Font("Times New Roman", Font.BOLD, 18));

        JLabel bet = new JLabel("Bet $");
        bet.setBounds(840, 205, 75, 20);
        bet.setFont(new Font("Times New Roman", Font.BOLD, 18));
        myBankText.setBounds(900, 50, 100, 30);
        myBankText.setEnabled(false);

        mySetBank.setEnabled(false);
        mySetBank.setFocusable(false);
        mySetBank.setBounds(830, 120, 175, 35);
        mySetBank.setFont(new Font("Times New Roman", Font.BOLD, 15));
        mySetBank.setMnemonic(KeyEvent.VK_B);

        myBetText.setBounds(885, 200, 110, 30);
        myBetText.setEnabled(false);

        mySetBank.addActionListener(theEvent -> {
            try {
                int bankNum = Integer.parseInt(myBankText.getText().trim());
                int betNum = Integer.parseInt(myBetText.getText().trim());
                boolean isOk = Main.bankOk(bankNum, betNum);
                if (!isOk) {
                    JOptionPane.showMessageDialog(null, "Your bank and bet must be greater than 0.\n"
                    + "Your bet must be equal to or smaller than your bank.");
                } else {
                    myBankText.setText(String.valueOf(bankNum - betNum));
                    myBankText.setEnabled(false);
                    myBetText.setEnabled(false);
                    myRollButton.setEnabled(true);
                    editBetButtons(false);
                    mySetBank.setEnabled(false);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Bank and bet must be a number.");
            }
        });
        myFrame.add(bank);
        myFrame.add(bet);
        myFrame.add(myBankText);
        myFrame.add(mySetBank);
        myFrame.add(myBetText);
    }

    /**
     * Creates and implements money buttons for betting,
     * buttons that add $5 to the bet, $10, etc.
     */
    private static void addBetButtons() {
        editBetButtons(false);

        add5.setFocusable(false);
        add10.setFocusable(false);
        add20.setFocusable(false);
        add50.setFocusable(false);
        add100.setFocusable(false);

        add5.setBounds(870, 260, 110, 30);
        add10.setBounds(870, 310, 110, 30);
        add20.setBounds(870, 360, 110, 30);
        add50.setBounds(870, 410, 110, 30);
        add100.setBounds(870, 460, 110, 30);


        add5.addActionListener(theEvent -> {
            editBet(5);
        });
        add10.addActionListener(theEvent -> {
            editBet(10);
        });
        add20.addActionListener(theEvent -> {
            editBet(20);
        });
        add50.addActionListener(theEvent -> {
            editBet(50);
        });
        add100.addActionListener(theEvent -> {
            editBet(100);
        });

        myFrame.add(add5);
        myFrame.add(add10);
        myFrame.add(add20);
        myFrame.add(add50);
        myFrame.add(add100);
    }

    /**
     * Adds specific amounts of money to the bet text field.
     * @param theNum the amount to add to text field
     */
    private static void editBet(int theNum) {
        int bet;
        if (myBetText.getText().isEmpty()) {
            bet = 0;
        } else {
            bet = Integer.parseInt(myBetText.getText());
        }
        myBetText.setText(String.valueOf(bet + theNum));
    }

    /**
     * Helper method that enables and disables all money bet buttons.
     * @param theStatus status of buttons
     */
    private static void editBetButtons(boolean theStatus) {
        add5.setEnabled(theStatus);
        add10.setEnabled(theStatus);
        add20.setEnabled(theStatus);
        add50.setEnabled(theStatus);
        add100.setEnabled(theStatus);
    }

    /**
     * Plays audio clip when player wins/loses.
     * @param theFile name of wav file
     * @throws UnsupportedAudioFileException if file is not supported
     * @throws IOException if file is not found
     * @throws LineUnavailableException if file is not able to be played
     */
    private static void playSound(String theFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream sound = AudioSystem.getAudioInputStream(new File(theFile));
        Clip clip = AudioSystem.getClip();
        clip.open(sound);
        clip.start();
    }
}