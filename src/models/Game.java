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


    public void makeMove(){

        Player currentPlayer=players.get(nextPlayerIndex);
        System.out.println("it's "+currentPlayer.getName()+" move, Please make the move");

        Move move=currentPlayer.makeMove(board);

        if(!validateMove(move)){   // check in down.
            System.out.println("It's a wrong move, Please make the valid move");
            return;
        }

        updateGameState(move,currentPlayer);  // check in down.

        if(checkWinner(move)){ // check in down.
            winner=currentPlayer;
            gameState=GameState.SUCCESS;

        }

        else if(moves.size()==board.getSize()*board.getSize()){
            gameState=GameState.DRAW;
        }

    }

    public void undoMove(){

        if(moves.isEmpty()){
            System.out.println("There are no moves to make undo");
            return;
        }

        Move lastMove=moves.get(moves.size()-1);
        moves.remove(moves.size()-1);

        lastMove.getCell().setCellState(CellState.EMPTY);
        lastMove.setPlayer(null);

        nextPlayerIndex--;
        nextPlayerIndex=(nextPlayerIndex+players.size())%players.size();

        for(WinningStrategies strategies:winningStrategies){
            strategies.handleUndo(board,lastMove);
        }

        gameState=GameState.IN_PROGRESS;
        setWinner(null);


    }

    public boolean validateMove(Move move){

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row<0 || col<0 || row>=board.getSize() || col>=board.getSize()){
            return false;
        }

        return board.getGrid().get(row).get(col).getCellState().equals(CellState.EMPTY);

    }

    public void updateGameState(Move move, Player currentPlayer){

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cellToChange=board.getGrid().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setSymbol(currentPlayer.getSymbol());

        move.setCell(cellToChange);
        move.setPlayer(currentPlayer);
        moves.add(move);

        nextPlayerIndex++;
        nextPlayerIndex%=players.size();
    }

    public boolean checkWinner(Move move){

        for(WinningStrategies strategies: winningStrategies){
            if(strategies.checkWinner(board, move)){
                return true;
            }
        }
        return false;
    }


}
