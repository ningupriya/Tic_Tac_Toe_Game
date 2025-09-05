package strategies;

import models.Board;
import models.Cell;
import models.CellState;
import models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {


    @Override
    public Move makeMove(Board board) {

        for(List<Cell> row : board.getGrid()){
            for(Cell cell : row){
                if(cell.getCellState().equals(CellState.EMPTY)){

                    // Bot should make move in this cell
                    return new Move(null,new Cell(cell.getRow(), cell.getCol()));
                }
            }
        }
        return null;
    }
}
