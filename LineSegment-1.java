public class LineSegment {
    private Vector start;
    private Vector end;
    boolean isoHrizontal;
    /**
     * Constructs a line segment from (x1, y1) to (x2, y2)
     */
    public LineSegment(double x1, double y1, double x2, double y2) {
        start = new Vector(x1, y1);
        end = new Vector(x2, y2);

    }



    /**
     * Returns the start point
     */
    public Vector getA() {
        return start;
    }

    /**
     * Returns the end point
     */
    public Vector getB() {
        return end;
    }
}
