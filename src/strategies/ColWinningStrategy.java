package strategies;

import models.Board;
import models.Move;

import java.util.HashMap;

public class ColWinningStrategy implements WinningStrategies{

    private HashMap<Integer , HashMap<Character, Integer>> colMaps=new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {

        int col=move.getCell().getCol();
        char sym=move.getCell().getSymbol().getSymChar();

        if(!colMaps.containsKey(col)){
            colMaps.put(col, new HashMap<>());
        }

        HashMap<Character, Integer> maps=colMaps.get(col);

        if(!maps.containsKey(sym)){
            maps.put(sym, 0);
        }

        maps.put(sym, maps.get(sym) + 1);

        if(maps.get(sym)==board.getSize()){
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {

        int col=move.getCell().getCol();
        char sym=move.getCell().getSymbol().getSymChar();

        colMaps.get(col).put(sym, colMaps.get(col).get(sym) - 1);

    }
}
