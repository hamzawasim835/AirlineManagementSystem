package airline.flight;

public class Route {
    // Defining attributes
    private final String origin;
    private final String destination;

    // Defining Constructor
    public Route(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    // Getters
    public String getOrigin() {
        return origin;
    }
    public String getDestination() {
        return destination;
    }
    /* No setters. A route is a data item in and of itself. Updating the route
    Means practically picking a new route*/

    // Others
    @Override
    public String toString() {
        return origin + " to " + destination;
    }
}