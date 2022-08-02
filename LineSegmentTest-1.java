import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineSegmentTest {
    @Test
    void gettersWork() {
        Vector start = new Vector(4, 5);
        Vector end = new Vector(6, 7);
        LineSegment line = new LineSegment(4, 5, 6, 7);
        assertEquals(start, line.getA());
        assertEquals(end, line.getB());
    }
}

