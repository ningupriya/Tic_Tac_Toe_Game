package strategies;

import models.Board;
import models.Move;

public class DiagWinningStrategy implements WinningStrategies{

    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {

    }
}
