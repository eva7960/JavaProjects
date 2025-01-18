/*
TCSS 305A
 */
package Tests;

import Model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests game logic: bank/bet set up, checks getter
 * methods and roll dice method.
 *
 * @author Eva Howard
 * @version Autumn 2023
 */
class LogicTest {
    /**
     * Because roll dice are random integers, method
     * keeps rolling dice until player wins. 3 methods
     * to test all 3 outcomes of roll dice method.
     */
    @Test
    void rollDiceWin() {
        int result;
        do {
            result = Logic.rollDice(true);
        } while (result != 1);
        assertEquals(1, result);
    }

    /**
     * Rolls dice until sum isn't 7 and also doesn't match point.
     */
    @Test
    void rollDice0() {
        int result;
        do {
            result = Logic.rollDice(true);
        } while (result != 0);
        assertEquals(0, result);
    }

    /**
     * Rolls dice until player loses.
     */
    @Test
    void rollDiceLose() {
        int result;
        do {
            result = Logic.rollDice(true);
        } while (result != -1);
        assertEquals(-1, result);
    }

    /**
     * Checks getter for dice 1.
     */
    @Test
    void getD1() {
        do {
            Logic.rollDice(false);
        } while (Logic.getD1() != 1);
        assertEquals(1, Logic.getD1());
    }

    /**
     * Checks getter for dice 2.
     */
    @Test
    void getD2() {
        do {
            Logic.rollDice(false);
        } while (Logic.getD2() != 3);
        assertEquals(3, Logic.getD2());
    }

    /**
     * Checks getter for sum.
     */
    @Test
    void getSum() {
        do {
            Logic.rollDice(false);
        } while (Logic.getSum() != 7);
        assertEquals(7, Logic.getSum());
    }

    /**
     * Checks getter for point.
     */
    @Test
    void getPoint() {
        do {
            Logic.rollDice(true);
        } while (Logic.getPoint() != 9);
        assertEquals(9, Logic.getPoint());
    }

    /**
     * Checks that method will check bank and bet appropriately
     * and subtract bet from bank.
     */
    @Test
    void setBank() {
        assertTrue(Logic.setBank(1000,10));
        assertFalse(Logic.setBank(-100, 10));
        assertEquals(990, Logic.getBank());
        assertEquals(10, Logic.getBet());
    }
}