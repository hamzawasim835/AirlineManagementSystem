package airline.flight;

public class Flight {

    // Defining attributes
    private final String flightNum;
    private Plane plane;
    private Route route;
    private String departureDate;
    private String departureTime;
    private float duration;

    public Flight(String flightNum,
                  Plane plane,
                  Route route,
                  String departureDate,
                  String departureTime,
                  float duration) {

        this.flightNum = flightNum;
        this.plane = plane;
        this.route = route;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.duration = duration;
    }

    //admin panel için
    public Flight(String flightNum, Plane plane, Route route) {
        this(
                flightNum,
                plane,
                route,
                "2026-01-01",
                "12:00",
                1.0f
        );
    }


    public String getFlightNum() {
        return flightNum;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }



    public boolean isFlightFull() {
        return plane.reservedSeatCounter() >= plane.getCapacity();
    }

    public boolean bookSeatBySeatNum(String label) {
        if (plane != null) {
            Seat seat = plane.getSeatBySeatNum(label);
            if (seat != null && !seat.getReserveStatus()) {
                seat.setReserveStatus(true);
                return true;
            }
            return false;
        }
        return false;
    }

    public static Flight createSampleFlight(String number) {
        Route route = new Route("JFK", "LHR");
        Plane plane = new Plane("P1", "Boeing 747", 180, 30, 6);
        return new Flight(number, plane, route, "2026-01-08", "14:00", 7.5f);
    }

    @Override
    public String toString() {
        return String.format(
                "Flight %s: %s to %s | Date: %s | Time: %s | Duration: %.2f hrs",
                flightNum,
                route.getOrigin(),
                route.getDestination(),
                departureDate,
                departureTime,
                duration
        );
    }
}
