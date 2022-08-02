import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreasureTest {

    @Test
    void testToString() {
        Vector vector = new Vector(2,2);
        Treasure treasure = new Treasure("Gold", Color.WHITE, vector, 0.3);
        assertEquals("Gold", treasure.toString());


    }

    @Test
    void testEquals() {
        Vector vector = new Vector();
        Treasure treasure1 = new Treasure("Diamond", Color.BLUE, vector,0.3);
        Treasure treasure2 = new Treasure("Silver", Color.WHITE,vector,0.4);
        assertEquals(treasure1.equals(treasure2),false);

    }


    @Test
    void testHashCode() {
        Vector vector = new Vector();
        Treasure treasure1 = new Treasure("Somon", Color.BLUE, vector,0.3);
        assertEquals(treasure1.hashCode(), Objects.hash("Somon"));
    }

    @Test
    void getColor() {
        Vector vector = new Vector();
        Treasure treasure1 = new Treasure("Diamond", Color.BLUE, vector,0.3);
        assertEquals(treasure1.getColor(), Color.BLUE);

    }

    @Test
    void getLocation() {
        Vector vector = new Vector();
        Treasure treasure1 = new Treasure("Diamond", Color.BLUE, vector,0.3);
        assertEquals(treasure1.getLocation(), vector);

    }

    @Test
    void getRadius() {
        Vector vector = new Vector();
        Treasure treasure1 = new Treasure("Diamond", Color.BLUE, vector,0.3);
        assertEquals(treasure1.getRadius(), 0.3);

    }
    @Test
    void setLocations(){
        Vector vector = new Vector();
        Treasure treasure1 = new Treasure("Diamond", Color.BLUE, vector,0.3);
        assertEquals("Diamond", treasure1.getName());
        assertEquals(Color.BLUE, treasure1.getColor());
        assertEquals("<0.0, 0.0>", treasure1.getLocation().toString());
    }
}
