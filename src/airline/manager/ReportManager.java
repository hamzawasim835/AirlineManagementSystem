package airline.manager;

import airline.flight.Flight;
import java.util.ArrayList;
import java.util.Scanner;


public class ReportManager {
    private boolean isProcessing = false;


    public void startAsyncReport(ArrayList<Flight> sharedFlights) {
        if (isProcessing) {
            System.out.println("Wait! A report is already being generated.");
            return;
        }

        isProcessing = true;
        System.out.println("Status: Preparing report...");

        // Fire and forget - this is the asynchronous part

        Thread thread = new Thread(new AsyncReportGenerator(this, sharedFlights));
        thread.start();
    }

    // This method is called by the thread when it finishes
    public void onReportComplete(String result) {
        isProcessing = false;
        System.out.println("\n--- NEW REPORT RECEIVED ---");
        System.out.println(result);
        System.out.println("---------------------------");
        System.out.print("Selection: "); // Refreshes the prompt for the CLI user
    }

    public static void main(String[] args) {
        ArrayList<Flight> myFlights = new ArrayList<>();

        // Creating flights to populate list to actually test it
        myFlights.add(Flight.createSampleFlight("LH101"));
        myFlights.add(Flight.createSampleFlight("LH202"));

        // Populating the flights
        myFlights.get(0).getPlane().getSeatMap()[0][0].reserveSeat();
        myFlights.get(0).getPlane().getSeatMap()[0][1].reserveSeat();
        myFlights.get(0).getPlane().getSeatMap()[0][2].reserveSeat();
        myFlights.get(1).getPlane().getSeatMap()[0][0].reserveSeat();
        myFlights.get(1).getPlane().getSeatMap()[0][1].reserveSeat();
        myFlights.get(1).getPlane().getSeatMap()[0][2].reserveSeat();

        ReportManager manager = new ReportManager();
        manager.startAsyncReport(myFlights);
    }
}