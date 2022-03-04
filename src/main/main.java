package main;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        try {
            Game game = new Game(Cities.getCities());
            game.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
