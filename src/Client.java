import controllers.GameController;
import models.*;
import strategies.ColWinningStrategy;
import strategies.RowWinningStrategy;
import strategies.WinningStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        GameController gameController = new GameController();


        // To start the game what are the things that we required.
        // 1-size of the board
        // 2-size of the players
        // 3-winning strategies.


        // Deciding the size of the board.
        int size=3;

        // Adding the number of players that we needed.
        List<Player> players = new ArrayList<Player>();
        players.add(new HumanPlayer(1,"Ningu", new Symbol('N')));
        players.add(new Bot(2, "Botty", new Symbol('B'), BotDifficultyLevel.EASY));

        // Adding the strategies  that we need to play like rules.
        List<WinningStrategies> winningStrategies=new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());

        // starting the game.
        Game game=gameController.startGame(size, players, winningStrategies);

        // Displaying the game board.
        gameController.displayTTTBoard(game);

        while(gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
            gameController.makeMove(game);
            gameController.displayTTTBoard(game);

            // If we want to make the undo after every move.
            System.out.println("Do you want to make the undo ?[Y/N]:");
            String undoAnswer=scanner.nextLine();
            if(undoAnswer.equals("Y")){
                gameController.undo(game);
                System.out.println("Congratulations! undo Done.You may now play again.");
                gameController.displayTTTBoard(game);
            }

            // Check if the game has the winner

            if(gameController.getGameState(game).equals(GameState.SUCCESS)){
                System.out.println(gameController.getWinner(game).getName()+ " has won the game!");
            }

            // Check if the match has got the draw.

            else if(gameController.getGameState(game).equals(GameState.DRAW)){
                System.out.println("Match has got Draw,Nobody won the game!");
            }






        }




    }
}
