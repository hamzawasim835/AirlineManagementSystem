package airline.manager;

import airline.flight.Flight;
import airline.flight.Plane;
import airline.flight.Route;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightManager {
    // Defining attributes
    private ArrayList<Flight> flights;
    private ArrayList<Plane> fleet;

    // Defining Constructor
    public FlightManager() {
        this.flights = new ArrayList<>();
        this.fleet = new ArrayList<>();
    }

    // Search method for later use
    public ArrayList<Flight> searchByDestination(String destination) {
        ArrayList<Flight> results = new ArrayList<>();
        for (Flight f : flights) {
            if (f.getRoute().getDestination().equalsIgnoreCase(destination)) {
                results.add(f);
            }
        }
        return results;
    }

    // Getter for the planes list. Will be helpful later on in the GUI stage
    public ArrayList<Plane> getAvailableFleet() {
        return fleet;
    }
    
    //adminpanel
    public Plane getAnyPlane() {
        if (fleet.isEmpty()) return null;
        return fleet.get(0);
    }

    // Plane loading
    public void loadPlanes(String filename) {

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                // Format: ID, Model, Capacity, Rows, Cols
                Plane p = new Plane(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]));
                fleet.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error loading fleet: " + e.getMessage());
        }
    }

    // Loading flight from file and saving it to file
    public void saveFlights(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Flight f : flights) {
                // Format: ID, Origin, Dest, Date, Time, Duration, PlaneID
                writer.println(f.getFlightNum() + "," +
                        f.getRoute().getOrigin() + "," +
                        f.getRoute().getDestination() + "," +
                        f.getDepartureDate() + "," +
                        f.getDepartureTime() + "," +
                        f.getDuration() + "," +
                        f.getPlane().getPlaneID());
            }
        } catch (IOException e) {
            System.out.println("Error saving flights: " + e.getMessage());
        }
    }

    public void loadFlights(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");

                // Rebuilding Route
                Route route = new Route(parts[1], parts[2]);

                // Linking to existing Plane in fleet
                Plane plane = findPlaneByID(parts[6]);

                if (plane != null) {
                    // Constructing object from file then adding it to array list
                    Flight f = new Flight(parts[0], plane, route, parts[3], parts[4], Float.parseFloat(parts[5]));
                    flights.add(f);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading flights: " + e.getMessage());
        }
    }

    public Plane findPlaneByID(String id) {
        for (Plane plane : fleet) {
            if (plane.getPlaneID().equalsIgnoreCase(id)) {
                return plane;
            }
        }
        return null;
    }
    public void addFlight(Flight f) {
        flights.add(f);
    }

    // getFlights method

    public ArrayList<Flight> getFlights() {
        return flights;
    }
}