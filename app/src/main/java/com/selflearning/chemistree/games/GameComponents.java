package com.selflearning.chemistree.games;

public class GameComponents {

    private String nameOfGame;
    private int back;
    private int id;

    public GameComponents(int id, String nameOfGame, int background) {
        this.id = id;
        this.nameOfGame = nameOfGame;
        this.back = background;
    }

    public int getId() {
        return id;
    }

    public String getNameOfGame() {
        return nameOfGame;
    }

    public int getBack() {
        return back;
    }
}
