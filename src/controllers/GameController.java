package controllers;

import models.Board;
import models.Game;
import models.GameState;
import models.Player;
import strategies.WinningStrategies;

import java.util.List;

public class GameController {

    public Game startGame(
            Integer size,
            List<Player> players,
            List<WinningStrategies> winningStrategies
    ){

        try{
            return Game.gameBuilder().
                    setSize(size)
                    .setPlayers(players)
                    .setWinningStrategies(winningStrategies)
                    .build();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Sorry, Please try again with once again");
        }

        return null;
    }


    public GameState getGameState(Game game){

        return game.getGameState();
    }

    public void displayTTTBoard(Game game){

        game.displayBoard();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public void undo(Game game){
        game.undoMove();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }










}
