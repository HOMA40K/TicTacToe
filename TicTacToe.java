// Authors: Arseni Vostrikov
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicTacToe {
    // поменять переменные на private и final
    // final переменные с большой буквы
    static char[][] board;
    final static int BoardSize = createBoard();
    final static char Cross = 'X';
    final static char Nought = '0';
    final static String Regex = "^[1-"+BoardSize+"]";

    public static void main(String[] args) {
        while (true) {

            makeUserMove();
            printBoard();

            if (checkWin(Cross)) {
                break;
            }
            if (checkDraw()) {
                break;
            }
            makeBotMove();
            printBoard();
            if (checkWin(Nought)) {
                break;
            }
        }
    }

    public static int createBoard() {
        sayHello();
        System.out.println("enter the size of the board");
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        board = new char[size][size];
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
        printBoard();
        return size;
    }

    public static void sayHello() {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("You are X and the bot is 0");
    }
    public static void printBoard() {

        System.out.println("_________________________");
        for (char[] row : board) {
            int i = 0;
            //for стрим
            for (char c : row) {
                // сократить код if else
                //System.out.print("|");
                if (i == row.length - 1) {
                    System.out.print(c);
                }
                else {
                    System.out.print(c + "|");
                }

                i++;
            }
            System.out.println();
            for (char c : row) {
                System.out.print("-+");
            }
            System.out.println();
        }
        System.out.println("_________________________");
    }

    public static void placeMove(int moveRow, int moveColumn, char symbol) {
        board[moveRow][moveColumn] = symbol;
    }

    public static void makeBotMove() {
        Random rand = new Random();
        int moveRow;
        int moveColumn;
        do {
            moveRow = (rand.nextInt(BoardSize));
            moveColumn = (rand.nextInt(BoardSize));
        } while (board[moveRow][moveColumn] != ' ');
        placeMove(moveRow, moveColumn, Nought);
    }

    public static void makeUserMove() {

        Scanner scan = new Scanner(System.in);
        int moveRow;
        int moveColumn;
        String moveRowString;
        String moveColumnString;
        boolean suitableInt = false;

        while (!suitableInt) {
            System.out.println("Please select row between 1 and " + BoardSize + "!");
            moveRowString = scan.next();

            if (Pattern.matches(Regex, String.valueOf(moveRowString))) {
                moveRow = Integer.parseInt(moveRowString) - 1;
                System.out.println("Please select column between 1 and " + BoardSize + "!");
                moveColumnString = scan.next();

                if (Pattern.matches(Regex, String.valueOf(moveColumnString))) {
                    moveColumn = Integer.parseInt(moveColumnString) - 1;

                    if (board[moveRow][moveColumn] != ' ') {
                        System.out.println("That position is taken!");

                    } else {
                        System.out.println("Thank you! Got " + moveRow + " " + moveColumn);
                        placeMove(moveRow, moveColumn, Cross);
                        suitableInt = true;
                    }
                }
            }
        }
    }


    public static boolean checkWin(char symbol) {

        if (checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol) || checkDiagonals2(symbol)) {
            System.out.println(symbol + " wins!");
            return true;
        }
        return false;
    }

    public static boolean checkRows(char symbol) {
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (j + 2 < board.length){
                    if (board[i][j] == symbol && board[i][j + 1] == symbol && board[i][j + 2] == symbol){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean checkColumns(char symbol) {
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (j + 2 < board.length){
                    if (board[j][i] == symbol && board[j + 1][i] == symbol && board[j + 2][i] == symbol){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean checkDiagonals(char symbol) {
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                if (j + 2 < board.length && i + 2 < board.length){
                    if (board[j][i] == symbol && board[j + 1][i + 1] == symbol && board[j + 2][i + 2] == symbol){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean checkDiagonals2(char symbol) {
        for (int i = 0; i < board.length; i++){
            for (int j = board.length - 1; j >= 0; j--){
                if (j - 2 >= 0 && i + 2 < board.length){
                    if (board[j][i] == symbol && board[j - 1][i + 1] == symbol && board[j - 2][i + 2] == symbol){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean checkDraw() {
        for (char[] row : board) {
            for (char c : row) {
                if (c == ' ') {
                    return false;
                }
            }
        }
        System.out.println("Draw!");
        return true;
    }
}
