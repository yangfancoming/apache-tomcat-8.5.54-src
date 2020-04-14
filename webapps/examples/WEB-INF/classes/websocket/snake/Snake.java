
package websocket.snake;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.Session;

public class Snake {

    private static final int DEFAULT_LENGTH = 5;

    private final int id;
    private final Session session;

    private Direction direction;
    private int length = DEFAULT_LENGTH;
    private Location head;
    private final Deque<Location> tail = new ArrayDeque<>();
    private final String hexColor;

    public Snake(int id, Session session) {
        this.id = id;
        this.session = session;
        this.hexColor = SnakeAnnotation.getRandomHexColor();
        resetState();
    }

    private void resetState() {
        this.direction = Direction.NONE;
        this.head = SnakeAnnotation.getRandomLocation();
        this.tail.clear();
        this.length = DEFAULT_LENGTH;
    }

    private synchronized void kill() {
        resetState();
        sendMessage("{\"type\": \"dead\"}");
    }

    private synchronized void reward() {
        length++;
        sendMessage("{\"type\": \"kill\"}");
    }


    protected void sendMessage(String msg) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException ioe) {
            CloseReason cr =
                    new CloseReason(CloseCodes.CLOSED_ABNORMALLY, ioe.getMessage());
            try {
                session.close(cr);
            } catch (IOException ioe2) {
                // Ignore
            }
        }
    }

    public synchronized void update(Collection<Snake> snakes) {
        Location nextLocation = head.getAdjacentLocation(direction);
        if (nextLocation.x >= SnakeAnnotation.PLAYFIELD_WIDTH) {
            nextLocation.x = 0;
        }
        if (nextLocation.y >= SnakeAnnotation.PLAYFIELD_HEIGHT) {
            nextLocation.y = 0;
        }
        if (nextLocation.x < 0) {
            nextLocation.x = SnakeAnnotation.PLAYFIELD_WIDTH;
        }
        if (nextLocation.y < 0) {
            nextLocation.y = SnakeAnnotation.PLAYFIELD_HEIGHT;
        }
        if (direction != Direction.NONE) {
            tail.addFirst(head);
            if (tail.size() > length) {
                tail.removeLast();
            }
            head = nextLocation;
        }

        handleCollisions(snakes);
    }

    private void handleCollisions(Collection<Snake> snakes) {
        for (Snake snake : snakes) {
            boolean headCollision = id != snake.id && snake.getHead().equals(head);
            boolean tailCollision = snake.getTail().contains(head);
            if (headCollision || tailCollision) {
                kill();
                if (id != snake.id) {
                    snake.reward();
                }
            }
        }
    }

    public synchronized Location getHead() {
        return head;
    }

    public synchronized Collection<Location> getTail() {
        return tail;
    }

    public synchronized void setDirection(Direction direction) {
        this.direction = direction;
    }

    public synchronized String getLocationsJson() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("{\"x\": %d, \"y\": %d}",
                Integer.valueOf(head.x), Integer.valueOf(head.y)));
        for (Location location : tail) {
            sb.append(',');
            sb.append(String.format("{\"x\": %d, \"y\": %d}",
                    Integer.valueOf(location.x), Integer.valueOf(location.y)));
        }
        return String.format("{\"id\":%d,\"body\":[%s]}",
                Integer.valueOf(id), sb.toString());
    }

    public int getId() {
        return id;
    }

    public String getHexColor() {
        return hexColor;
    }
}
