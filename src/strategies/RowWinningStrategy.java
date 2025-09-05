package strategies;

import models.Board;
import models.Move;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategies{

    private HashMap<Integer, HashMap<Character, Integer>> rowMaps=new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {

        int row=move.getCell().getRow();
        char sym=move.getCell().getSymbol().getSymChar();

        if(!rowMaps.containsKey(row)){
            rowMaps.put(row, new HashMap<>());
        }

        HashMap<Character, Integer> map=rowMaps.get(row);

        if(!map.containsKey(sym)){
            map.put(sym, 0);
        }

        map.put(sym, map.get(sym)+1);

        if(map.get(sym)==board.getSize()){
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {

        int row=move.getCell().getRow();
        char sym=move.getCell().getSymbol().getSymChar();

        rowMaps.get(row).put(sym, rowMaps.get(row).get(sym)-1);

    }
}
