package models;

public class HumanPlayer extends Player {

    private Integer coins;
    private Integer level;

    public HumanPlayer(Integer id, String name , Symbol symbol) {

        super(id,name,PlayerType.HUMAN_PLAYER,symbol);
        this.coins = 0;
        this.level = 1;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public Move makeMove(Board board) {
        return null;
    }
}
