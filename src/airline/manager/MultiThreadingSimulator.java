package airline.manager;

import airline.flight.Flight;
import airline.flight.Plane;
import airline.flight.Seat;
import airline.reservation.Passenger;
import airline.reservation.Reservation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MultiThreadingSimulator {

    public static void startSimulation(Flight flight, boolean synchControl)
            throws InterruptedException {

        Plane plane = flight.getPlane();

        List<Seat> freeSeats = new ArrayList<>();

        for (Seat[] row : plane.getSeatMap()) {
            for (Seat s : row) {
                if (s != null && !s.isReserved()) {
                    freeSeats.add(s);
                }
            }
        }

        int targetPassengers = 90;
        int alreadyReserved = plane.reservedSeatCounter();

        int threadCount = Math.min(
                targetPassengers - alreadyReserved,
                freeSeats.size()
        );
       
        Collections.shuffle(freeSeats);

        Passenger[] passengers = new Passenger[threadCount];
        for (int i = 0; i < threadCount; i++) {
            int k = i + 1;
            passengers[i] = new Passenger(
                    String.format("P%04d", k),
                    "Name" + k,
                    "Surname" + k,
                    "contact" + k + "@example.com"
            );
        }

        String[] seatNums = new String[threadCount];
        for (int i = 0; i < threadCount; i++) {
            seatNums[i] = freeSeats.get(i).getSeatNum();
        }

        ReservationManager reservationManager = new ReservationManager();
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(
                    new ThreadPassenger(
                            passengers[i],
                            flight,
                            seatNums[i],
                            synchControl,
                            reservationManager
                    )
            );
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        int count = plane.reservedSeatCounter();
        System.out.println("Booked seat count: " + count);

        var reservations = reservationManager.getAllReservations();
        Map<String, Integer> seatCounts = new HashMap<>();

        for (Reservation r : reservations) {
            String s = r.getSeat().getSeatNum();
            seatCounts.put(s, seatCounts.getOrDefault(s, 0) + 1);
        }

        long duplicates = seatCounts.values().stream().filter(c -> c > 1).count();
        System.out.println("Total reservations created: " + reservations.size());
        System.out.println("Duplicate seat bookings: " + duplicates);

        System.out.println("===============================================================================================================");
        System.out.println("Printing output on grid:");
        System.out.println("\n--- Airplane Seat Map (30 Rows x 6 Columns) ---");
        System.out.println("     A   B   C     D   E   F");

        Seat[][] map = plane.getSeatMap();

        for (int i = 0; i < 30; i++) {
            System.out.printf("%2d ", (i + 1));
            for (int j = 0; j < 6; j++) {
                System.out.print(map[i][j].isReserved() ? "[X] " : "[ ] ");
            }
            System.out.println();
        }

        System.out.println("===============================================================================================================");
    }
}
