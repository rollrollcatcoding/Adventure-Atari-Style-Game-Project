import java.awt.*;
import java.util.Objects;

public class Treasure {

    private String name;
    private Color color;
    private Vector location;
    private double radius;

    public Treasure(String name, Color color, Vector location, double radius) {
        this.name = name;
        this.color = color;
        this.location = location;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return name;
    }


    public void setLocation(Vector location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Treasure treasure = (Treasure) o;
        return Objects.equals(name, treasure.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Color getColor() {
        return color;
    }

    public Vector getLocation() {
        return location;
    }

    public double getRadius() {
        return radius;
    }
}