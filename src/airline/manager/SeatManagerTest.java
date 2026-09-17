package airline.manager;

import airline.flight.Flight;
import airline.flight.Plane;
import airline.flight.Route;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatManagerTest {
    // Creating sample flight for testing purposes
    Route sampleRoute = new Route("IST","DXB");
    Plane samplePlane = new Plane("TC-RDP", "Airbus A321neo", 180, 30, 6);
    Flight sampleFlight = new Flight("PC748", samplePlane, sampleRoute,"09-01-2026" ,"15:30" ,4.3f);
    SeatManager seatManager = new SeatManager();
    // Testing that empty seat count falls after booking
    @Test
    public void EmptySeatCountShouldDropPostReservation() {
        int before = seatManager.emptySeatsCount(sampleFlight);
        seatManager.reserveSeat(sampleFlight, "12B");
        int after = seatManager.emptySeatsCount(sampleFlight);
        assertEquals(before - 1, after);
    }
    // Testing exception when illegal seat number is booked
    @Test
    public void ExceptionShouldBeThrownWhenIllegalRowIsGiven() {
        assertThrows(IllegalArgumentException.class, () -> seatManager.reserveSeat(sampleFlight, "31A"));
    }
    @Test
    public void ExceptionShouldBeThrownWhenIllegalColumnIsGiven() {
        assertThrows(IllegalArgumentException.class, () -> seatManager.reserveSeat(sampleFlight, "31G"));
    }
    @Test
    public void ExceptionShouldBeThrownWhenWhollyIllegalSeatNumberIsGiven() {
        assertThrows(IllegalArgumentException.class, () -> seatManager.reserveSeat(sampleFlight, "32Z"));
    }

}