package strategies;

import models.Board;
import models.Move;

public interface WinningStrategies {

    boolean checkWinner(Board board, Move move);
    void handleUndo(Board board, Move move);


}
