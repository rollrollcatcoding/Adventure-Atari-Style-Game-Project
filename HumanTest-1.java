import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class HumanTest {
    static final double x = 2;
    static final double y = 3;
    static final Color color = Color.GREEN;
    static Human human;

    @BeforeEach
    void createCar() {
        human = new Human(x, y, "name", color);
    }

    @Test
    void keepsTrackOfLaps() {
        assertEquals(0, human.getLaps());
        human.lap(0);
        human.lap(1);
        human.lap(0);
        assertEquals(1, human.getLaps());
        assertEquals("name: 1", human.announceLaps());
    }

    @Test
    void gettersWork(){
        assertEquals(new Vector(), human.getVelocity());
        assertEquals(color, human.getColor());
        assertTrue(human.getRadius() > 0);
        assertEquals(new Vector(x, y), human.getCenter());
    }

    @Test
    void shifts(){
        Vector a = new Vector(1,2);
        human.shift(a);
        assertEquals(new Vector(3,4), human.getCenter());
    }

    @Test
    void accelerates() {
        Vector a = new Vector(1, -2);
        human.accelerate(a);
        assertEquals(a, human.getVelocity());
    }

    @Test
    void drifts() {
        Vector a = new Vector(1, -2);
        human.accelerate(a);
        human.drift();
        assertEquals(new Vector(3, 1), human.getCenter());
    }
}
