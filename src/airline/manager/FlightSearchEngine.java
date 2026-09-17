
package airline.manager;

import airline.flight.Flight;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchEngine {
    public List<Flight> searchEngine(List<Flight> allFlights, String origin, String dest) {
        List<Flight> results = new ArrayList<>();

        // Getting current date and time and storing them in a string
        LocalDateTime now = LocalDateTime.now();
        String currentMoment = now.format(DateTimeFormatter.ofPattern("'D'yyyy-MM-dd'T'HH:mm"));

        // Iterating over flights
        for (Flight f : allFlights) {

            // Checking route
            boolean routeMatch = f.getRoute().getOrigin().trim().equalsIgnoreCase(origin) &&
                    f.getRoute().getDestination().trim().equalsIgnoreCase(dest);

            // Checking Time
            String flightMoment = "D" + f.getDepartureDate() + "T" + f.getDepartureTime();
            boolean isFuture = flightMoment.compareTo(currentMoment) >= 0;

            // If we know the route and time and date are all right, we proceed to adding
            // current flight to list of results
            if (routeMatch && isFuture) {
                results.add(f);
            }
        }
        return results;
    }
}
