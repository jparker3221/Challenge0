/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parker_7_arraygame;

/**
 *
 * @author jparker3221
 */
public class Treasure {
    int x;
    int y;
    boolean isAlive;
    int scoreGiven; //This is my treasure class, with an int scoregiven which is applied to a random to give each chest a different point value 5 pts
    char symbol;
    String coordinates;
    Treasure(int a, int b, boolean c, int d, char e) {
        this.x = a;
        this.y = b;
        this.isAlive = c;
        this.scoreGiven = d;
        this.symbol = e;
    }
}
