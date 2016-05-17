
//package arraychallenge;
import java.util.Random;
import java.util.Scanner;

import java.util.Scanner;

public class ArrayChallenge {

    public static int x, y, nx, ny, maxX, maxY, score, counter;
    public static int health = 100;
    static String dinput;
    static String direction;
    static Scanner sc = new Scanner(System.in);
    public static boolean game, enemy1, enemy2, enemy3, stun, stun1, stun2, stun3;
    public static int cooldown;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
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
        for (int t = 0; t < 15; t++) {
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
        System.out.println("You have spilled all your coins! but enemies are trying to take it! protect your wealth!");
        System.out.println("Traps: ○, Enemies: ☢, Treasure: ◎" + "\n" + "Collect 10 points to win.");
        grid(map, trap, treasure, enemy);
        while (game) {
            input(map, trap, treasure, enemy);
            enemies(map, trap, treasure, enemy);
            grid(map, trap, treasure, enemy);
            if (stun) {
                counter++;
            }
            clear(map);
            if (score == 10) {
                System.out.println("You won!!!!!");
                System.out.println(ANSI_YELLOW+"░░░░░░░▐█▀█▄░░░░░░░░░░▄█▀█▌ ");
                System.out.println("░░░░░░░█▐▓░█▄░░░░░░░▄█▀▄▓▐█ ");
                System.out.println("░░░░░░░█▐▓▓░████▄▄▄█▀▄▓▓▓▌█ ");
                System.out.println("░░░░░▄█▌▀▄▓▓▄▄▄▄▀▀▀▄▓▓▓▓▓▌█ ");
                System.out.println("░░░▄█▀▀▄▓█▓▓▓▓▓▓▓▓▓▓▓▓▀░▓▌█ ");
                System.out.println("░░█▀▄▓▓▓███▓▓▓███▓▓▓▄░░▄▓▐█▌");
                System.out.println("░█▌▓▓▓▀▀▓▓▓▓███▓▓▓▓▓▓▓▄▀▓▓▐█ ");
                System.out.println("▐█▐██▐░▄▓▓▓▓▓▀▄░▀▓▓▓▓▓▓▓▓▓▌█▌ ");
                System.out.println("█▌███▓▓▓▓▓▓▓▓▐░░▄▓▓███▓▓▓▄▀▐█ ");
                System.out.println("█▐█▓▀░░▀▓▓▓▓▓▓▓▓▓██████▓▓▓▓▐█▌ ");
                System.out.println("▓▄▌▀░▀░▐▀█▄▓▓██████████▓▓▓▌█"+ANSI_RESET);
                game = false;
            }
        }
        if (!game && score < 10) {
            System.out.println("game over, your score was " + score);
        }
    } // end of main

    public static void grid(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy) {
        if (game) {
            map[y][x] = ANSI_PURPLE+"●"+ANSI_RESET;
        } else {
            System.out.println("Game over");
            System.out.println("  __           _     _               _                       ");
            System.out.println(" / _| ___  ___| |___| |__   __ _  __| |_ __ ___   __ _ _ __  ");
            System.out.println("| |_ / _ \\/ _ \\ / __| '_ \\ / _` |/ _` | '_ ` _ \\ / _` | '_ \\ ");
            System.out.println("|  _|  __/  __/ \\__ \\ |_) | (_| | (_| | | | | | | (_| | | | |");
            System.out.println("|_|  \\___|\\___|_|___/_.__/ \\__,_|\\__,_|_| |_| |_|\\__,_|_| |_|");
            
        }

        for (int i = 0; i <= map[0].length - 1; i++) {
            for (int j = 0; j <= map[1].length - 1; j++) {
                if (trap[j][i]
                        && map[i][j] != ANSI_CYAN + "^^" +ANSI_RESET && map[i][j] != ANSI_CYAN + "vv"+ANSI_RESET && map[i][j] != ANSI_CYAN + ">>"+ANSI_RESET && map[i][j] != ANSI_CYAN + "<<"+ANSI_RESET
                        && map[i][j] != ANSI_CYAN + "\\" +ANSI_RESET&& map[i][j] != ANSI_CYAN + "//"+ANSI_RESET) {
                    map[i][j] = ANSI_RED+"○"+ANSI_RESET;
                }
                if (treasure[j][i]) {
                    map[i][j] = ANSI_WHITE+"◎"+ANSI_RESET;
                }
                if (trap[j][i] && treasure[j][i]) {
                    trap[j][i] = false;
                }

                if (j < map[1].length - 1) {
                    if (map[i][j] != ANSI_PURPLE+"●"+ANSI_RESET && map[i][j] != ANSI_RED+"○"+ANSI_RESET && map[i][j] != ANSI_WHITE+"◎"+ANSI_RESET && map[i][j] != ANSI_GREEN+"☢"+ANSI_RESET
                            && map[i][j] != ANSI_CYAN + "^^"+ANSI_RESET && map[i][j] != ANSI_CYAN + "vv"+ANSI_RESET && map[i][j] !=ANSI_CYAN +  ">>"+ANSI_RESET && map[i][j] != ANSI_CYAN + "<<"+ANSI_RESET
                            && map[i][j] != ANSI_CYAN + "\\"+ANSI_RESET && map[i][j] != ANSI_CYAN + "//"+ANSI_RESET && map[i][j] != "**") {
                        System.out.print(ANSI_BLACK+"-"+ANSI_RESET);
                    } else {
                        System.out.print(map[i][j]);
                    }

                } else if (map[i][j] != ANSI_PURPLE+"●"+ANSI_RESET && map[i][j] != ANSI_RED+"○"+ANSI_RESET && map[i][j] != ANSI_WHITE+"◎"+ANSI_RESET && map[i][j] != ANSI_GREEN+"☢"+ANSI_RESET
                        && map[i][j] != ANSI_CYAN + "^^"+ANSI_RESET && map[i][j] != ANSI_CYAN + "vv"+ANSI_RESET && map[i][j] != ANSI_CYAN + ">>"+ANSI_RESET && map[i][j] != ANSI_CYAN + "<<"+ANSI_RESET
                        && map[i][j] != ANSI_CYAN + "\\" +ANSI_RESET&& map[i][j] != ANSI_CYAN + "//" +ANSI_RESET&& map[i][j] != "**") {
                    System.out.println(ANSI_BLACK+"-"+ANSI_RESET);
                } else {
                    System.out.println(map[i][j]);
                }
            }
        }

    } 

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
            map[enemy[0][1]][enemy[0][0]] = ANSI_GREEN+"☢"+ANSI_RESET;
        } else if (!enemy1) {
            map[enemy[0][1]][enemy[0][0]] = ANSI_BLACK+"-"+ANSI_RESET;
        }
        if (enemy2) {
            map[enemy[1][1]][enemy[1][0]] = ANSI_GREEN+"☢"+ANSI_RESET;
        } else if (!enemy2) {
            map[enemy[1][1]][enemy[1][0]] = ANSI_BLACK+"-"+ANSI_RESET;
        }
        if (enemy3) {
            map[enemy[2][1]][enemy[2][0]] = ANSI_GREEN+"☢"+ANSI_RESET;
        } else if (!enemy3) {
            map[enemy[2][1]][enemy[2][0]] = ANSI_BLACK+"-"+ANSI_RESET;
        }

        if (enemy[0][1] == y && enemy[0][0] == x && enemy1
                || enemy[1][1] == y && enemy[1][0] == x && enemy2
                || enemy[2][1] == y && enemy[2][0] == x && enemy3) {
            health= health - 50;
            if(health == 0){
            game = false;
            }
            for (int e = 0; e < 2; e++) {
            int enemyx = (int) Math.floor(Math.random() * maxX);
            int enemyy = (int) Math.floor(Math.random() * maxY);
            enemy[e][1] = enemyx;
            enemy[e][0] = enemyy;
            }
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
            health= health -15;
            if(health == 0){
            game = false;
            }
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
                if (map[i][j] == ANSI_CYAN + "^^"+ANSI_RESET || map[i][j] == ANSI_CYAN + "vv" +ANSI_RESET|| map[i][j] == ANSI_CYAN + ">>"+ANSI_RESET || map[i][j] == ANSI_CYAN + "<<"+ANSI_RESET
                        || map[i][j] == ANSI_CYAN + "\\"+ANSI_RESET || map[i][j] == ANSI_CYAN + "//"+ANSI_RESET) {
                    map[i][j] = "-";
                }
//                if (map[i][j] == "**" && counter == 3) {
//                    map[i][j] = "-";
//                    stun = false;
//                    stun1 = false;
//                    stun2 = false;
//                    stun3 = false;
//                }
            }
        }
    }

    public static void input(String[][] map, boolean[][] trap, boolean[][] treasure, int[][] enemy) {
        System.out.print("Enter direction (N, E, S, W or diagonals), F to attack, G to stun;");
                System.out.print("your score is " + score + ", your health is " + health + ": ");
        dinput = sc.nextLine();

        if (dinput.equalsIgnoreCase("n") && y != maxX - 1) {
            move(map, 0, -1, trap, treasure, enemy);
            direction = "n";
        } else if (dinput.equalsIgnoreCase("e") && x != maxX - 1) {
            move(map, 1, 0, trap, treasure, enemy);
            direction = "e";
        } else if (dinput.equalsIgnoreCase("s") && y != maxX - 1) {
            move(map, 0, 1, trap, treasure, enemy);
            direction = "s";
        } else if (dinput.equalsIgnoreCase("w") && x != 0) {
            move(map, -1, 0, trap, treasure, enemy);
            direction = "w";
        } else if (dinput.equalsIgnoreCase("ne") && y != 0 && x != maxX - 1 || dinput.equalsIgnoreCase("dw") && y != 0 && x != maxX - 1) {
            move(map, 1, -1, trap, treasure, enemy);
            direction = "ne";
        } else if (dinput.equalsIgnoreCase("nw") && y != 0 && x != 0 || dinput.equalsIgnoreCase("aw") && y != 0 && x != 0) {
            move(map, -1, -1, trap, treasure, enemy);
            direction = "nw";
        } else if (dinput.equalsIgnoreCase("se") && y != maxX - 1 && x != maxX - 1 || dinput.equalsIgnoreCase("ds") && y != maxX - 1 && x != maxX - 1) {
            move(map, 1, 1, trap, treasure, enemy);
            direction = "se";
        } else if (dinput.equalsIgnoreCase("sw") && y != maxX - 1 && x != 0 || dinput.equalsIgnoreCase("as") && y != maxX - 1 && x != 0) {
            move(map, -1, 1, trap, treasure, enemy);
            direction = "sw";
        } else if (dinput.equalsIgnoreCase("f") && direction == "w") {
            map[y - 1][x] = ANSI_CYAN + "^^"+ANSI_RESET;
            trap[y - 1][x] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "s") {
            map[y + 1][x] = ANSI_CYAN + "vv"+ANSI_RESET;
            trap[y + 1][x] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "d") {
            map[y][x + 1] = ANSI_CYAN + ">>"+ANSI_RESET;
            trap[y][x + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "a") {
            map[y][x - 1] = ANSI_CYAN + "<<"+ANSI_RESET;
            trap[y][x - 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "wd") {
            map[y - 1][x + 1] = ANSI_CYAN + "//"+ANSI_RESET;
            trap[y - 1][x + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "wa") {
            map[y - 1][x - 1] = ANSI_CYAN + "\\"+ANSI_RESET;
            trap[y - 1][x - 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "sd") {
            map[y + 1][x + 1] = "\\";
            trap[y + 1][x + 1] = true;
        } else if (dinput.equalsIgnoreCase("f") && direction == "sa") {
            map[y + 1][x - 1] = "//";
            trap[y + 1][x - 1] = true;
        } else if (dinput.equalsIgnoreCase("g") && cooldown >= 5){ {
          
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
                if(y > enemy[1][0]){
                    enemy[1][0] = enemy[1][0] - 4;
                }
                if(y < enemy[1][0]){
                    enemy[1][0] = enemy[1][0] + 4;
                }
                if(x > enemy[1][1]){
                    enemy[1][1] = enemy[1][1] - 4;
                }
                if(x < enemy[1][1]){
                    enemy[1][1] = enemy[1][1] + 4;
                if(y > enemy[2][0]){
                    enemy[2][0] = enemy[2][0] - 4;
                }
                if(y < enemy[2][0]){
                    enemy[2][0] = enemy[2][0] + 4;
                }
                if(x > enemy[2][1]){
                    enemy[2][1] = enemy[2][1] - 4;
                }
                if(x < enemy[2][1]){
                    enemy[2][1] = enemy[2][1] + 4;
                
        } else {
            System.out.println("Sorry, wrong input or reached map edge");
            input(map, trap, treasure, enemy);
        }
        
    } 
}
    }
}
    }
}
