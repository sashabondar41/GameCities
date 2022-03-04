package tests;

import main.Cities;
import main.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    @BeforeEach
    void setUp() throws IOException {
        game = new Game(Cities.getCities());
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
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        game.start();
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
        System.setIn(new ByteArrayInputStream(inputs.getBytes()));
        game.start();
        assertEquals(result, game.getUsedCities());
    }

}