package airline.manager;

import airline.flight.Flight;
import java.util.ArrayList;

public class AsyncReportGenerator implements Runnable {
    // Attribute
    private final ReportManager manager;
    private final ArrayList<Flight> flights;

    // Constructor
    public AsyncReportGenerator(ReportManager manager, ArrayList<Flight> flights) {
        this.manager = manager;
        this.flights = flights;
    }

    @Override
    public void run() {
        // error checking before jumping in
        if (flights.size() == 0) {
            System.out.println("No flights found to calculate based on");
            return;
        }

        int listSize = flights.size();
        int totalSpots = 0;
        int actualBookings = 0;

        for (int i = 0; i < this.flights.size(); i++) {
            totalSpots += this.flights.get(i).getPlane().getCapacity();
            actualBookings += this.flights.get(i).getPlane().reservedSeatCounter();
        }
        float percent = ((float)actualBookings / (float)totalSpots) * 100;

        String result = "Occupancy Rate: " + percent + "%"; // Placeholder for your math later

        // Notify the manager that we are done
        manager.onReportComplete(result);

    }
}