
package arraychallenge;
import java.util.Random;
import java.util.Scanner;

import java.util.Scanner;

public class ArrayChallenge {

    public static int x, y, nx, ny, maxX, maxY, score, counter;
    static String dinput;
    static String direction;
    static Scanner sc = new Scanner(System.in);
    public static boolean game, enemy1, enemy2, enemy3, stun, stun1, stun2, stun3;
    public static int cooldown;

    public static void main(String[] args) {
        game = true;
        enemy1 = true;
        enemy2 = true;
        enemy3 = true;
        score = 0;
        maxX = 15;
        maxY = 15;
        counter = 0;
        stun = true;

        String[][] map = new String[maxX][maxY];
        boolean[][] trap = new boolean[maxX][maxY];
        boolean[][] treasure = new boolean[maxX][maxY];
        int[][] enemy = new int[maxX][maxY];

        x = 4;
        y = 4;

        if (trap[y][x] == true) {
            x = (int) Math.floor(Math.random() * maxX);
            y = (int) Math.floor(Math.random() * maxY);
        }
        for (int t = 0; t < 50; t++) {
            int trapx = (int) Math.floor(Math.random() * maxX);
            int trapy = (int) Math.floor(Math.random() * maxY);
            trap[trapx][trapy] = true;
        }
        for (int t = 0; t < 11; t++) {
            int treasurex = (int) Math.floor(Math.random() * maxX);
            int treasurey = (int) Math.floor(Math.random() * maxY);
            treasure[treasurex][treasurey] = true;
        }
        for (int e = 0; e < 2; e++) {
            int enemyx = (int) Math.floor(Math.random() * maxX);
            int enemyy = (int) Math.floor(Math.random() * maxY);
            enemy[e][1] = enemyx;
            enemy[e][0] = enemyy;
        }
        nx = x;
        ny = y;
        System.out.println("Traps: ○, Enemies: ☢, Treasure: ◎" + "\n" + "Collect 10 points to win.");
        while (game) {
            input(map, trap, treasure, enemy);
            enemies(map, trap, treasure, enemy);
            grid(map, trap, treasure, enemy);
            if (stun) {
                counter++;
            }
            clear(map);
            if (score == 10) {
                System.out.println("YOU WIN! gg wp");
                game = false;
            }
        }
        if (!game && score < 10) {
            System.out.println("game over, your score was " + score);
        }
    } // end of main

    public static void grid(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy) {

        for (int i = 0; i <= map[0].length - 1; i++) {
            for (int j = 0; j <= map[1].length - 1; j++) {
                if (trap[j][i]
                        && map[i][j] != "^^" && map[i][j] != "vv" && map[i][j] != ">>" && map[i][j] != "<<"
                        && map[i][j] != "\\" && map[i][j] != "//") {
                    map[i][j] = "○";
                }
//                if (enemy[0][0] == j && enemy[0][1] == i
//                        || enemy[1][0] == j && enemy[1][1] == i
//                        || enemy[2][0] == j && enemy[2][1] == i) {
//                    map[i][j] = "XK";
//                }
                if (treasure[j][i]) {
                    map[i][j] = "◎";
                }
                if (trap[j][i] && treasure[j][i]) {
                    trap[j][i] = false;
                }

                if (j < map[1].length - 1) {
                    if (map[i][j] != "●" && map[i][j] != "○" && map[i][j] != "◎" && map[i][j] != "☢"
                            && map[i][j] != "^^" && map[i][j] != "vv" && map[i][j] != ">>" && map[i][j] != "<<"
                            && map[i][j] != "\\" && map[i][j] != "//" && map[i][j] != "**") {
                        System.out.print("-");
                    } else {
                        System.out.print(map[i][j]);
                    }

                } else if (map[i][j] != "●" && map[i][j] != "○" && map[i][j] != "◎" && map[i][j] != "☢"
                        && map[i][j] != "^^" && map[i][j] != "vv" && map[i][j] != ">>" && map[i][j] != "<<"
                        && map[i][j] != "\\" && map[i][j] != "//" && map[i][j] != "**") {
                    System.out.println("-");
                } else {
                    System.out.println(map[i][j]);
                }
            }
        }

    } //end of grid

    public static void enemies(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy) {
        if (enemy[0][0] > x && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "-";
            enemy[0][0]--;
        }
        if (enemy[0][0] < x && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "-";
            enemy[0][0]++;
        }
        if (enemy[0][1] > y && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "-";
            enemy[0][1]--;
        }
        if (enemy[0][1] < y && !stun1 && enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "-";
            enemy[0][1]++;
        }
        if (enemy[1][0] > x && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "-";
            enemy[1][0]--;
        }
        if (enemy[1][0] < x && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "-";
            enemy[1][0]++;
        }
        if (enemy[1][1] > y && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "-";
            enemy[1][1]--;
        }
        if (enemy[1][1] < y && !stun2 && enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "-";
            enemy[1][1]++;
        }
        if (enemy[2][0] > x && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "-";
            enemy[2][0]--;
        }
        if (enemy[2][0] < x && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "-";
            enemy[2][0]++;
        }
        if (enemy[2][1] > y && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "-";
            enemy[2][1]--;
        }
        if (enemy[2][1] < y && !stun3 && enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "-";
            enemy[2][1]++;
        }
        if (map[enemy[0][1]][enemy[0][0]] == "*" && stun) {
            stun1 = true;
        }
        if (map[enemy[1][1]][enemy[1][0]] == "*" && stun) {
            stun2 = true;
        }
        if (map[enemy[2][1]][enemy[2][0]] == "*" && stun) {
            stun3 = true;
        }
        if (enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "☢";
        } else if (!enemy1) {
            map[enemy[0][1]][enemy[0][0]] = "-";
        }
        if (enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "☢";
        } else if (!enemy2) {
            map[enemy[1][1]][enemy[1][0]] = "-";
        }
        if (enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "☢";
        } else if (!enemy3) {
            map[enemy[2][1]][enemy[2][0]] = "-";
        }

        if (enemy[0][1] == y && enemy[0][0] == x && enemy1
                || enemy[1][1] == y && enemy[1][0] == x && enemy2
                || enemy[2][1] == y && enemy[2][0] == x && enemy3) {
            game = false;
        }

        if (trap[enemy[0][1]][enemy[0][0]]) {
            enemy1 = false;
        }
        if (trap[enemy[1][1]][enemy[1][0]]) {
            enemy2 = false;
        }
        if (trap[enemy[2][1]][enemy[2][0]]) {
            enemy3 = false;
        }

    }

    public static void move(String[][] map, int movex, int movey, boolean[][] trap, boolean[][] treasure, int[][] enemy) {
        map[y][x] = "-";
        nx += movex;
        ny += movey;
        if (trap[nx][ny]) {
            game = false;
        }
        if (treasure[nx][ny]) {
            map[nx][ny] = "-";
            score++;
            treasure[nx][ny] = false;
        }
        x += movex;
        y += movey;
    }

    public static void clear(String[][] map) {
        for (int i = 0; i <= map[0].length - 1; i++) {
            for (int j = 0; j <= map[1].length - 1; j++) {
                if (map[i][j] == "^^" || map[i][j] == "vv" || map[i][j] == ">>" || map[i][j] == "<<"
                        || map[i][j] == "\\" || map[i][j] == "//") {
                    map[i][j] = "-";
                }
                if (map[i][j] == "**" && counter == 3) {
                    map[i][j] = "-";
                    stun = false;
                    stun1 = false;
                    stun2 = false;
                    stun3 = false;
                }
            }
        }
    }

    public static void input(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy) {
        System.out.print("Enter direction (W, A, S, D or diagonals), F to attack, G to stun, your score is " + score + ": ");
        dinput = sc.nextLine();

        if (dinput.equalsIgnoreCase("w") && y != maxX - 1) {
            move(map, 0, -1, trap, treasure, enemy);
            direction = "w";
        } else if (dinput.equalsIgnoreCase("d") && x != maxX - 1) {
            move(map, 1, 0, trap, treasure, enemy);
            direction = "d";
        } else if (dinput.equalsIgnoreCase("s") && y != maxX - 1) {
            move(map, 0, 1, trap, treasure, enemy);
            direction = "s";
        } else if (dinput.equalsIgnoreCase("a") && x != 0) {
            move(map, -1, 0, trap, treasure, enemy);
            direction = "a";
        } else if (dinput.equalsIgnoreCase("wd") && y != 0 && x != maxX - 1 || dinput.equalsIgnoreCase("dw") && y != 0 && x != maxX - 1) {
            move(map, 1, -1, trap, treasure, enemy);
            direction = "wd";
        } else if (dinput.equalsIgnoreCase("wa") && y != 0 && x != 0 || dinput.equalsIgnoreCase("aw") && y != 0 && x != 0) {
            move(map, -1, -1, trap, treasure, enemy);
            direction = "wa";
        } else if (dinput.equalsIgnoreCase("sd") && y != maxX - 1 && x != maxX - 1 || dinput.equalsIgnoreCase("ds") && y != maxX - 1 && x != maxX - 1) {
            move(map, 1, 1, trap, treasure, enemy);
            direction = "sd";
        } else if (dinput.equalsIgnoreCase("sa") && y != maxX - 1 && x != 0 || dinput.equalsIgnoreCase("as") && y != maxX - 1 && x != 0) {
            move(map, -1, 1, trap, treasure, enemy);
            direction = "sa";
        } else if (dinput.equalsIgnoreCase("f") && direction == "w") {
            map[y - 1][x] = "^^";
            trap[y - 1][x] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "s") {
            map[y + 1][x] = "vv";
            trap[y + 1][x] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "d") {
            map[y][x + 1] = ">>";
            trap[y][x + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "a") {
            map[y][x - 1] = "<<";
            trap[y][x - 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "wd") {
            map[y - 1][x + 1] = "//";
            trap[y - 1][x + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "wa") {
            map[y - 1][x - 1] = "\\";
            trap[y - 1][x - 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "sd") {
            map[y + 1][x + 1] = "\\";
            trap[y + 1][x + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "sa") {
            map[y + 1][x - 1] = "//";
            trap[y + 1][x - 1] = true;
        } else if (dinput.equalsIgnoreCase("g") && cooldown >= 5){ {
//            stun = true;
            
                cooldown = 0;
                if(y > enemy[0][0]){
                enemy[0][0] = enemy[0][0] - 4;
                }
                if(y < enemy[0][0]){
                    enemy[0][0] = enemy[0][0] + 4;
                }
                if(x > enemy[0][1]){
                    enemy[0][1] = enemy[0][1] - 4;
                }
                if(x < enemy[0][1]){
                    enemy[0][1] = enemy[0][1] + 4;
                
        } else {
            System.out.println("Sorry, wrong input or reached map edge");
            input(map, trap, treasure, enemy);
        }
        if (game) {
            map[y][x] = "●";
        } else {
            System.out.println("Game over");
        }
    } //end of input
}
    }
}
/**
 *
 * @author josephjarzombek
 */
/*public class ArrayChallenge {
static String whichWay;
static Random random = new Random();
public static int Yblocked;
public static int Xblocked;
static int Y = 4;
static int X = 4;
static int e1Y = 2;
static int e1X = 2;
static int cooldown = 15;
static boolean isAlive = true;
    public static void main(String[] args) {
        game();
    }
    public static void game(){
        /*
	int[][] numbers=new int[10][10];       
	for(int row=0;row<numbers.length;row++){
	for(int col=0;col<numbers[row].length;col++){
	numbers[row][col]=1+col;
	}
	}
	for(int row=0;row<numbers.length;row++){
	for(int col=0;col<numbers[row].length;col++){
	System.out.print(" x");
	}
	System.out.println();
	}
	}
         */  
/*
        System.out.println("Type go to begin");
        while(isAlive == true){
                Scanner movement = new Scanner(System.in);
                whichWay = movement.nextLine();
                                if(Y > e1Y){
                    e1Y = e1Y + 1;
                }
                if(Y < e1Y){
                    e1Y = e1Y - 1;
                }
                if(X > e1X){
                    e1X = e1X + 1;
                }
                if(X < e1X){
                    e1X = e1X - 1;
                }
                
               if(whichWay.contains("n")){
                    Y = Y - 1;
                        if(Y == Yblocked && X == Xblocked){
                        Y = Y + 1;
                    }
                }
                if(whichWay.contains("s")){
                    Y = Y + 1;
                    if(Y == 6 && X==6){
                    Y = Y - 1;
                    }
                }
                if(whichWay.contains("w")){
                    X = X - 1;
                    if(X == 6 && Y == 6){
                    X = X +1;
                    }
                }
                if(whichWay.contains("e")){
                    X = X + 1;
                    if(X == 6 && Y == 6){
                    X = X -1;
                    }
                }
                if(whichWay.contains("ult") && cooldown >= 5){
                cooldown = 0;
                if(Y > e1Y){
                e1Y = e1Y - 4;
                }
                if(Y < e1Y){
                    e1Y = e1Y + 4;
                }
                if(X > e1X){
                    e1X = e1X - 4;
                }
                if(X < e1X){
                    e1X = e1X + 4;
                }
        }
                     
                                
            char[][] map;           
            map = new char[8][8];
            map[Y][X] = '●';
            map[2][2] = '◎';
            map[e1Y][e1X] = '☢';
            for(int w = 0; w <= 10; w++){
            walls();
            map[Yblocked][Xblocked] = '○';
            }
            for (int i = 0; i <= map[0].length - 1; i++) {
                for (int j = 0; j <= map[1].length - 1; j++) {
                    if (j < map[1].length - 1) {
                        if (map[i][j] != '●' && map[i][j] != '◎' && map[i][j] != '☢' && map[i][j] != '○') {
                            System.out.print("-");
                        } else {
                        System.out.print(map[i][j]);
                    }
                } else {
                    if (map[i][j] != '●' && map[i][j] != '☢') {
                        System.out.println("-");
                    } else {
                        System.out.println(map[i][j]);
                    }
                }
            }
        }
                    cooldown = cooldown + 1;
                    if(X == 2 && Y == 2){
                    System.out.println("You Got the Treasure. Joo Wheen.");
                    isAlive = false;
                } 
                    if(e1X == X && e1Y == Y){
                        System.out.println("You got caught");
                        isAlive = false;
                    }
                    if(X == 4 && Y == 8){
                    System.out.println("You activated my trap card. U R DED");
                    isAlive = false;
           } 
        }
    }
    public static void walls(){
        Xblocked=random.nextInt(1);
        Yblocked=random.nextInt(1);
        
    }
}*/