import java.util.Scanner;

public class TicTacToeApp {

    public static void main(String[] args) {
        System.out.println("Welcome to " + TicTacToe.gameName);
        Scanner r = new Scanner(System.in);
        System.out.println("What board size do you want to play on?");
        int boardSize = r.nextInt();
        TicTacToe game = new TicTacToe(boardSize);

        boolean player = false;
        while(true) {
            String playerName = "O";
            if (player) {
                playerName = "X";
            }
            System.out.println("It's " + playerName + "'s turn!");
            display(game);
            System.out.println("Enter X coordinate:");
            int xCoord = r.nextInt();
            System.out.println("Enter Y coordinate:");
            int yCoord = r.nextInt();
            System.out.println(game.play(xCoord, yCoord, player));
            display(game);
            player = !player;
        }

    }
    public static void display(TicTacToe game) {
        for (Boolean[] row : game.board) {
            for (Boolean item : row) {
                if (item == null) {
                    System.out.print("-");
                }
                else {
                    if (item) {
                        System.out.print("X");
                    } else {
                        System.out.print("O");
                    }
                }
            }
            System.out.println();
        }
    }

}
