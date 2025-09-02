package models;

import strategies.WinningStrategies;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> players;
    private Player winner;
    private Integer nextPlayerIndex;
    private List<Move> moves;
    private List<WinningStrategies> winningStrategies;
    private GameState gameState;

    public Game(Integer size, List<Player> players, List<WinningStrategies> winningStrategies) {

        this.gameState=GameState.IN_PROGRESS;
        this.board=new Board(size);
        this.players=players;
        this.winner=null;
        this.nextPlayerIndex=0;
        this.moves=new ArrayList<>();
        this.winningStrategies=winningStrategies;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<WinningStrategies> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategies> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(Integer nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public static  GameBuilder gameBuilder(){
        return new GameBuilder();
    }

    public static class GameBuilder {

        // We will write the attributes which we want from the user here.

        private Integer size;
        private List<Player> players;
        private List<WinningStrategies> winningStrategies;

        public GameBuilder setSize(int size){
            this.size = size;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }

        public GameBuilder setWinningStrategies(List<WinningStrategies> strategies){
            this.winningStrategies = strategies;
            return this;
        }

        public void validate(){

        }

        public Game build(){
            validate();
            return new Game(size, players, winningStrategies);
        }
    }

    public void displayBoard(){
        board.display();
    }


}
