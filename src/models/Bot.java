package models;

import strategies.BotPlayingStrategyFactory;

public class Bot extends Player {

    private BotDifficultyLevel difficultyLevel;

    public Bot(Integer id, String name , Symbol symbol, BotDifficultyLevel difficultyLevel) {
        super(id, name, PlayerType.BOT_PLAYER, symbol);
        this.difficultyLevel = difficultyLevel;
    }

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {

        return BotPlayingStrategyFactory
                .getBotPlayingStrategy(difficultyLevel)
                .makeMove(board);
    }
}
