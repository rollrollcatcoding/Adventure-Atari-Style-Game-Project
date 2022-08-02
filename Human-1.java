import java.awt.*;

public class Human {
    private Vector center;
    private Color color;
    private int laps;
    private String name;
    private double radius;
    private Vector velocity;

    /**
     * Returns the name and lap count
     */
    public String announceLaps() {
        return name + ": " + getLaps();
    }

    /**
     * Returns the laps
     */
    public int getLaps() {
        return laps / 2;
    }

    /**
     * Counts when the car passes the next checkpoint
     */
    public void lap(int line) {
        if ((laps + 1) % 2 == line) {
            laps++;
        }
    }

    /**
     * Returns the velocity
     */
    public Vector getVelocity() {
        return velocity;
    }

    /**
     * Returns the center
     */
    public Vector getCenter() {
        return center;
    }

    /**
     * Returns the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the color
     */
    public Color getColor() {
        return color;
    }

    public void shift (Vector v) {
        center = center.plus(v);
    }


    /**
     * Constructs a car at (x, y) with the given name and color
     */
    public Human (double x, double y, String name, Color color) {
        radius = 0.01;
        laps = -1;
        center = new Vector(x, y);
        velocity = new Vector();
        this.color = color;
        this.name = name;
    }

    /**
     * Changes the velocity by the given vector
     */
    public void accelerate(Vector v) {
        velocity = velocity.plus(v);
    }

    /**
     * Change the position by the velocity
     */
    public void drift() {
        center = center.plus(velocity);
    }

    /**
     * Returns whether the human touches the given line segment
     * Adapted from <https://stackoverflow.com/a/34951168c/1435803>
     */
    public boolean intersects(LineSegment line) {
        Vector a = line.getA();
        Vector b = line.getB();
        Vector v = b.minus(a);
        Vector w = center.minus(a);
        double c1 = w.dot(v);
        double c2 = v.dot(v);
        Vector projection; // Projection of center onto line
        if (c1 <= 0) { // Projection is beyond point a
            projection = a;
        } else if (c2 <= c1) { // Projection is beyond point b
            projection = b;
        } else { // Projection is within line segment
            projection = a.plus(v.times(c1 / c2));
        }
        return center.distanceTo(projection) <= radius;
    }
}
