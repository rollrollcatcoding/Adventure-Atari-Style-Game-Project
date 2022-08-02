
import java.util.Objects;

public class Vector {
    private double x;
    private double y;

    /**
     * Constructs a vector <0, 0>
     */
    public Vector() {}

    /**
     * Constructs a vector with the given components
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-component
     */
    public double getX() {
        return x;
    }
    /**
     * Returns the y-component
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x-component
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Sets the y-component
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns the vector sum with the given vector
     */
    public Vector plus(Vector v) {
        double x = this.x + v.getX();
        double y = this.y + v.getY();
        return new Vector(x, y);
    }

    /**
     * Returns the difference between this vector and the given vector
     */
    public Vector minus(Vector v) {
        double x = this.x - v.getX();
        double y = this.y - v.getY();
        return new Vector(x, y);
    }

    /**
     * Returns the element-wise product with the given vector
     */
    public Vector times(Vector v) {
        double x = this.x * v.getX();
        double y = this.y * v.getY();
        return new Vector(x, y);
    }

    /**
     * Returns a the scalar product with the given scalar
     */
    public Vector times(double scalar) {
        double x = this.x * scalar;
        double y = this.y * scalar;
        return new Vector(x, y);
    }

    /**
     * Returns the dot product with the given vector
     */
    public double dot(Vector v) {
        Vector product = times(v);
        return product.getX() + product.getY();
    }

    /**
     * Returns the distance to the given point
     */
    public double distanceTo(Vector v) {
        Vector difference = minus(v);
        double dotProduct = difference.dot(difference);
        return Math.sqrt(dotProduct);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Double.compare(vector.x, x) == 0 &&
                Double.compare(vector.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("<%s, %s>", x, y);
    }
}
