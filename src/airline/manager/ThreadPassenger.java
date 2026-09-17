package airline.manager;

import airline.flight.Flight;
import airline.reservation.Passenger;
import airline.reservation.Reservation;

import static airline.manager.SeatUtil.randomSeatNum;

//import static airline.manager.MultiThreadingSimulator.randomSeatNum;

public class ThreadPassenger implements Runnable {
    // Defining Attributes
    private Passenger passenger;
    private Flight flight;
    private String seatNum;
    private boolean synchControl;
    private ReservationManager reservationManager;

    // Defining Constructor
    public ThreadPassenger(Passenger passenger, Flight flight, String seatNum, boolean synchControl, ReservationManager reservationManager) {
        this.passenger = passenger;
        this.flight = flight;
        this.seatNum = seatNum;
        this.synchControl = synchControl;
        this.reservationManager = reservationManager;
    }

    // Defining Methods
    @Override
    public void run() {
        try {
            int current_attempts = 0;
            int max_attempts = 180;
            String toTry;
            // modify here to keep trying till success
            while(current_attempts < max_attempts) {
                if (current_attempts == 0) {
                    toTry = seatNum;

                }
                else{
                    toTry = randomSeatNum();
                }
                Reservation r = reservationManager.makeReservation(passenger, flight, toTry, synchControl);
                if (r != null){
                    return;
                }
                current_attempts++;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}