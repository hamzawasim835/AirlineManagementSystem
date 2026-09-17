package airline.manager;

import java.util.concurrent.ThreadLocalRandom;

public class SeatUtil {
    public static String randomSeatNum() {
        int row = ThreadLocalRandom.current().nextInt(1, 31); // 1..30
        char col = (char) ('A' + ThreadLocalRandom.current().nextInt(6)); // A..F
        return row + String.valueOf(col);
    }
}