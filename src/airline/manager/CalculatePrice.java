package airline.manager;

import airline.flight.Seat;
import airline.flight.SeatClasses;

public class CalculatePrice {

    //JUnit testleri için ana metot
    public double calculateTicketPrice(Seat seat) {

        if (seat == null) {
            throw new IllegalArgumentException("Seat cannot be null");
        }

        double basePrice = seat.getPrice();

        if (seat.getSeatClass() == SeatClasses.BUSINESS) {
            return basePrice * 1.7;   // business
        }

        return basePrice; // economy
    }
}
