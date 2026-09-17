package airline.manager;

import airline.flight.Flight;
import airline.flight.Seat;

public class SeatManager {

    
    public Seat getSeat(Flight flight, String seatNum) {
        Seat seat = flight.getPlane().getSeatBySeatNum(seatNum);

        if (seat == null) {
            throw new IllegalArgumentException(
                    "Seat does not exist: " + seatNum
            );
        }
        return seat;
    }

  
    public synchronized void reserveSeat(Flight flight, String seatNum) {
        Seat seat = getSeat(flight, seatNum);

        if (seat.isReserved()) {
            throw new IllegalStateException(
                    "Seat already reserved: " + seatNum
            );
        }

        seat.reserveSeat();
    }

    //JUnit testlerinde kullanılacak
     
    public int emptySeatsCount(Flight flight) {
        int empty = 0;

        for (Seat seat : flight.getPlane().getAllSeats()) {
            if (!seat.isReserved()) {
                empty++;
            }
        }
        return empty;
    }
}