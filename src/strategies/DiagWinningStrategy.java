package strategies;

import models.Board;
import models.Move;

import java.util.HashMap;

public class DiagWinningStrategy implements WinningStrategies{

    private HashMap<Character, Integer> primaryDiag=new HashMap<>();
    private HashMap<Character, Integer> secondaryDiag=new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {

        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        char sym=move.getCell().getSymbol().getSymChar();

        if(row==col){
            primaryDiag.put(sym,primaryDiag.getOrDefault(sym,0)+1);
            if(primaryDiag.get(sym)==board.getSize()){
                return true;
            }
        }

        if(row+col==board.getSize()-1){
            secondaryDiag.put(sym,secondaryDiag.getOrDefault(sym,0)+1);
            if(secondaryDiag.get(sym)==board.getSize()){
                return true;
            }
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {

        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        char sym=move.getCell().getSymbol().getSymChar();

        // undo primary diagonal
        if(row==col){
            primaryDiag.put(sym,primaryDiag.get(sym)-1);
        }

        //undo the secondary diagonal
        if(row+col==board.getSize()-1){
            secondaryDiag.put(sym,secondaryDiag.get(sym)-1);
        }



    }
}
