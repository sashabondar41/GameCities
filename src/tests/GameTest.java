package tests;

import main.Game;
import main.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void testCheckLetter() {
        String city = "Москва";
        assertEquals(city.toLowerCase(Locale.ROOT).charAt(city.length() - 1), game.checkLetter(city));
    }

    @Test
    void testPlayersInput() {
        List<Player> players = Arrays.asList(new Player("Захар"), new Player("Саня"));
        game.gettingReady();
        assertEquals(players, game.getPlayers());
    }
}