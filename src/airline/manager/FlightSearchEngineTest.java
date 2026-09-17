package airline.manager;

import airline.flight.Flight;
import airline.flight.Plane;
import airline.flight.Route;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightSearchEngineTest {
    // Creating sample objects for testing
    Route sampleRoute1 = new Route("IST","DXB");

    Plane samplePlane1 = new Plane("TC-RDP", "Airbus A321neo", 180, 30, 6);
    Plane samplePlane2 = new Plane("TC-RDQ", "Airbus A321neo", 180, 30, 6);

    FlightSearchEngine SearchEngine = new FlightSearchEngine();

    // Testing if flights for given routes are handed back
    @Test
    public void availableRoutesFlightsShouldBeReturned() {
        List<Flight> testList = new ArrayList<>();
        // Add exactly what you need for THIS test
        testList.add(new Flight("F1", samplePlane1, new Route("IST", "DXB"), "2030-12-12", "12:00", 5.4f));
        testList.add(new Flight("F2", samplePlane2, new Route("IST", "DXB"), "2030-12-13", "12:00", 5.4f));
        testList.add(new Flight("F3", samplePlane1, new Route("IST", "DXB"), "2026-01-9", "23:00", 5.4f));

        FlightSearchEngine engine = new FlightSearchEngine();
        List<Flight> results = engine.searchEngine(testList, "IST", "DXB");

        assertEquals(3, results.size(), "Should have found exactly 3 flights");
    }
    // Testing if flights for non-available routes are not given (empty list returned)
    @Test
    public void NonAvailableRoutesShouldntBeReturned(){
        List<Flight> testList2 = new ArrayList<>();
        // Add exactly what you need for THIS test
        testList2.add(new Flight("F1", samplePlane1, new Route("IST", "DXB"), "2030-12-12", "12:00", 5.4f));
        testList2.add(new Flight("F2", samplePlane2, new Route("IST", "DXB"), "2030-12-13", "12:00", 5.4f));
        testList2.add(new Flight("F3", samplePlane1, new Route("IST", "DXB"), "2026-01-9", "23:00", 5.4f));

        FlightSearchEngine engine = new FlightSearchEngine();
        List<Flight> results = engine.searchEngine(testList2, "IST", "CAI");

        assertEquals(0, results.size());
    }
    // Testing if past flights are eliminated from search results
    @Test
    public void PastFlightsShouldntBeReturned(){
        List<Flight> testList = new ArrayList<>();
        // Add exactly what you need for THIS test
        testList.add(new Flight("F1", samplePlane1, new Route("IST", "DXB"), "2030-12-12", "12:00", 5.4f));
        testList.add(new Flight("F2", samplePlane2, new Route("IST", "DXB"), "2020-12-13", "12:00", 5.4f));
        testList.add(new Flight("F3", samplePlane1, new Route("IST", "DXB"), "2026-01-09", "13:00", 5.4f));

        FlightSearchEngine engine = new FlightSearchEngine();
        List<Flight> results = engine.searchEngine(testList, "IST", "DXB");

        assertEquals(1, results.size());
    }


    // Temp test for debugging
    @Test
    public void DebuggingTest() {
        // 1. Create a brand new local list
        List<Flight> localFlights = new ArrayList<>();

        // 2. Add the 2030 flight directly here
        localFlights.add(new Flight("TEST123", samplePlane1, new Route("IST", "DXB"), "2030-12-12", "12:00", 5.5f));

        FlightSearchEngine engine = new FlightSearchEngine();

        // 3. Search that specific local list
        List<Flight> results = engine.searchEngine(localFlights, "IST", "DXB");

        // 4. This SHOULD be 1
        assertEquals(1, results.size(), "Should find the 2030 flight");
    }


}