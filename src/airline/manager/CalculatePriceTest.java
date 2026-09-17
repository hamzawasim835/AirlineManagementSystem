package airline.manager;

import airline.flight.Seat;
import airline.flight.SeatClasses;
import org.junit.jupiter.api.Test;
import  airline.flight.SeatClasses;
import airline.manager.CalculatePrice;

import static org.junit.jupiter.api.Assertions.*;

class CalculatePriceTest {

    @Test
    public void BusinessShouldBePricierThanEconomyBySpecificMargin() {
        CalculatePrice calc = new CalculatePrice();
        // Create 2 seat objects
        Seat seatE = new Seat("15A", SeatClasses.ECONOMY, 100, false);
        Seat seatB = new Seat("16A",  SeatClasses.BUSINESS, 100, false);
        // Create price calculator object
        CalculatePrice calculator = new CalculatePrice();
        // Assert statement
        double priceE = calculator.calculateTicketPrice(seatE)* 1.7;
        double priceB = calculator.calculateTicketPrice(seatB);
        assertEquals(priceE,priceB );
    }
    // Base Economy pricing test
    @Test
    public void EconomyPriceShouldntBeShiftedBySpecificMargin() {
        CalculatePrice calc = new CalculatePrice();
        Seat seat =  new Seat("15A", SeatClasses.ECONOMY, 100, false);
        double CalcOutput = calc.calculateTicketPrice(seat);
        assertEquals(100, CalcOutput);
    }
    // Base Business pricing test
    @Test
    public void BusinessPriceShouldBeShiftedBySpecificMargin() {
        CalculatePrice calc = new CalculatePrice();
        Seat seat =   new Seat("15A", SeatClasses.BUSINESS, 100, false);
        double CalcOutput = calc.calculateTicketPrice(seat);
        assertEquals(170, CalcOutput);
    }
    @Test
    public void ShouldGuardAgainstIllegalPricing(){
        // Creating testing objects
        Seat seat = new Seat("15A", SeatClasses.ECONOMY, -100, false);
        CalculatePrice calc = new CalculatePrice();

        assertThrows(IllegalArgumentException.class, () -> calc.calculateTicketPrice(seat));
    }

}