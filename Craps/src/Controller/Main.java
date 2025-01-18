/*
TCSS 305A
 */
package Controller;

import Model.Logic;
import View.CrapsGUI;

import javax.swing.*;

/**
 * Contains main, creates CrapsGUI object and starts the game. Also moves bet/bank data and game results
 * between model and view.
 *
 * @author Eva Howard
 * @version Autumn 2023
 */

public class Main {
    /**
     * Main creates new CrapsGUI and runs game. After instantiating game, JOptionPane pops up to tell user
     * that to start the game they must hit start in the game menu.
     * @param theArgs executable command line prompt
     */
    public static void main(String[] theArgs) {
        new CrapsGUI();
        JOptionPane.showMessageDialog(null, "Welcome!\nTo play Craps please select start in the game menu.");
    }

    /**
     * Gets data from bank and bet text fields in view and passes that data to model.
     * Model looks at data and sees if it's valid, if it is then it will return a boolean,
     * that boolean is then passed back to the view to tell the view whether the data based in
     * valid or not.
     * @param theBank bank amount
     * @param theBet bet amount
     * @return boolean if bank and bet amount are valid
     */
    public static boolean bankOk(int theBank, int theBet) {
        return Logic.setBank(theBank, theBet);
    }

    /**
     * Returns dice results to view, whether player won or lost
     * on that roll.c
     * @param theFirstRoll boolean whether it's the first roll or not
     */
    public static void diceResults(boolean theFirstRoll)  {
        int win = Logic.rollDice(theFirstRoll);
        if (win == 1) {
            CrapsGUI.updateScore(true, Logic.getBank());
        } else if (win == -1) {
            CrapsGUI.updateScore(false, Logic.getBank());
        }
        CrapsGUI.setRollResults(Logic.getD1(), Logic.getD2(), Logic.getSum(), Logic.getPoint());
    }
}
