/*
TCSS 305A
 */
package Model;

import java.util.Random;

/**
 * This class handles all the logic for the game,
 * handles bank and bet behavior and also rolls
 * dice and has getters for game data.
 *
 * @author Eva Howard
 * @version Autumn 2023
 */
public class Logic {
    /** dice 1. */
    private static int myD1;
    /** dice 2. */
    private static int myD2;
    /** sum of 2 die. */
    private static int mySum;
    /** game point. */
    private static int myPoint;
    /** player's bank for game. */
    private static int myBank;
    /** player's bet that round. */
    private static int myBet;

    /**
     * Rolls dice and updates fields. If it's the first roll, method will
     * check to see if player wins or loses first roll or updates myPoint.
     * If it's not the player's first roll method will check to see if the
     * sum is 7 or matches point. If player wins, updates bank.
     * @param theFirstRoll whether it's player's first roll or not.
     * @return number indicating result, 1 if player wins, -1 if player
     * loses and 0 if player doesn't win or lose when dice are rolled.
     */
    public static int rollDice(boolean theFirstRoll) {
        Random rand = new Random();
        myD1 = rand.nextInt(6) + 1;
        myD2 = rand.nextInt(6) + 1;
        mySum = myD1 + myD2;

        int result = 0;
        if (theFirstRoll) {
            if (mySum == 7 || mySum == 11) {
                result = 1;
            } else if (mySum == 2 || mySum == 3 || mySum == 12) {
                result = -1;
            } else {
                myPoint = mySum;
            }
        } else {
            if (mySum == 7) {
                result = -1;
            } else if (mySum == myPoint) {
                result = 1;
            }
        }
        if (result == 1) {
            myBank += myBet * 2;
        }
        return result;
    }

    /**
     * Getter for dice 1.
     * @return dice 1
     */
    public static int getD1() {
        return myD1;
    }

    /**
     * Getter for dice 2.
     * @return dice 2
     */
    public static int getD2() {
        return myD2;
    }

    /**
     * Getter for sum.
     * @return sum
     */
    public static int getSum(){
        return mySum;
    }

    /**
     * Getter for bank.
     * @return bank
     */
    public static int getBank() {
        return myBank;
    }

    /**
     * Getter for bet.
     * @return bet
     */
    public static int getBet() {
        return myBet;
    }

    /**
     * Getter for point.
     * @return point
     */
    public static int getPoint() {
        return myPoint;
    }

    /**
     * Checks that bank and bet are greater than 0. Bet must be
     * less than or equal to bank, and if these requirements are met
     * then it updates fields and returns true to controller to let it
     * know that bank values are ok.
     * @param theBank bank amount
     * @param theBet bet amount
     * @return boolean whether bank and bet numbers are valid
     */
    public static boolean setBank(int theBank, int theBet) {
        if (theBet <= theBank && theBet > 0) {
            myBank = theBank;
            myBet = theBet;
            myBank -= theBet;
            return true;
        }
        return false;
    }
}
