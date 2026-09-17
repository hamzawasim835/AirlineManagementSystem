package airline.reservation;

import airline.flight.*;
import java.time.LocalDate;

public class Reservation {
    private String reservationCode;
    private Flight flight;
    private Passenger passenger;
    private Seat seat;
    private LocalDate dateOfReservation;

    public Reservation(String reservationCode, Flight flight,
                       Passenger passenger, Seat seat) {
        this.reservationCode = reservationCode;
        this.flight = flight;
        this.passenger = passenger;
        this.seat = seat;
        this.dateOfReservation = LocalDate.now();
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Seat getSeat() {
        return seat;
    }

    public LocalDate getDateOfReservation() {
        return dateOfReservation;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "code='" + reservationCode + '\'' +
                ", passenger=" + passenger +
                ", seat=" + seat.getSeatNum() +
                '}';
    }
}
