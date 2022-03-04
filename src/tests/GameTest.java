package tests;

import main.Game;
import main.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collections;
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
        List<String> players = Arrays.asList("Захар","Саня");
        String inputs = "2\nЗахар\nСаня";
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        game.gettingReady();
        assertEquals(players, game.getPlayersNames());
    }

    @Test
    void testRun1() {
        List<String> result = Arrays.asList("москва","архангельск","курск","казань","норильск");
        String inputs = """
                2
                Захар
                Саня
                Москва
                Архангельск
                пыкпвкпка
                Курск
                Курск
                уыпыкпып
                Воркута
                Казань
                Норильск
                """;
        game.run();
        assertEquals(result, game.getUsedCities());
    }

    @Test
    void testRun2() {
        List<String> result = Collections.singletonList("тверь");
        String inputs = """
                2
                Захар
                Саня
                Тверь
                """;
        game.run();
        assertEquals(result, game.getUsedCities());
    }

}