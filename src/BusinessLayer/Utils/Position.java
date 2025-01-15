package BusinessLayer.Utils;

public class Position implements Comparable<Position> {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double range(Position other) // Returns the distance between two positions
    {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public Position(Position other) {
        this.x = other.x;
        this.y = other.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    
    public boolean equals(Position other) {
        return x == other.x && y == other.y;
    }

    public double distance(Position other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public Position clone() {
        return new Position(x, y);
    }

    public int compareTo(Position other) // Compare two positions
    {
        int cmp = Integer.compare(this.y, other.y);
        if (cmp != 0) {
            return cmp;
        }
        return Integer.compare(this.x, other.x);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
