package models;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private Integer coins;
    private Integer level;
    private Scanner sc ;

    public HumanPlayer(Integer id, String name , Symbol symbol) {

        super(id,name,PlayerType.HUMAN_PLAYER,symbol);
        this.coins = 0;
        this.level = 1;
        sc = new Scanner(System.in);
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

        System.out.println("Please enter the row you want to make move");
        int row=sc.nextInt();

        System.out.println("Please enter the column you want to make move");
        int col=sc.nextInt();

        return new Move(this, new Cell(row, col));

    }
}
