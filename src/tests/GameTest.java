package tests;

import main.Cities;
import main.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


class GameTest {
    private Game game;

    @BeforeEach
    void setUp() throws IOException {
        game = new Game(Cities.getCities());
    }
}