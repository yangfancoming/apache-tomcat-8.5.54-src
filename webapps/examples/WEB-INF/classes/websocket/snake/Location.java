
package websocket.snake;

public class Location {

    public int x;
    public int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location getAdjacentLocation(Direction direction) {
        switch (direction) {
            case NORTH:
                return new Location(x, y - SnakeAnnotation.GRID_SIZE);
            case SOUTH:
                return new Location(x, y + SnakeAnnotation.GRID_SIZE);
            case EAST:
                return new Location(x + SnakeAnnotation.GRID_SIZE, y);
            case WEST:
                return new Location(x - SnakeAnnotation.GRID_SIZE, y);
            case NONE:
                // fall through
            default:
                return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        if (y != location.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
