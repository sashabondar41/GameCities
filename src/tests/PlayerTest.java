package tests;

import org.junit.jupiter.api.Test;
import main.Player;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    public void testName(){
        String name = "Захар";
        Player first = new Player(name);
        String result = first.getName();
        assertEquals(name, result);
    }
}