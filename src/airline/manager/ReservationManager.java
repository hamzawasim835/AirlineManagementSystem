package airline.manager;

import airline.flight.Flight;
import airline.flight.Seat;
import airline.reservation.Passenger;
import airline.reservation.Reservation;
import airline.reservation.Ticket;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class ReservationManager {

    private final List<Reservation> allReservations =
            Collections.synchronizedList(new ArrayList<>());

    private final List<Ticket> allTickets =
            Collections.synchronizedList(new ArrayList<>());

    public List<Reservation> getAllReservations() {
        return allReservations;
    }

    public List<Ticket> getAllTickets() {
        return allTickets;
    }

    public Reservation makeReservation(
            Passenger passenger,
            Flight flight,
            String seatNum,
            boolean synchControl
    ) throws InterruptedException {

        Seat seat = flight.getPlane().getSeatBySeatNum(seatNum);
        if (seat == null) {
            throw new IllegalArgumentException("Seat not found: " + seatNum);
        }

        if (synchControl) {
            synchronized (seat) {
                if (seat.isReserved()) return null;
                return createReservation(passenger, flight, seat);
            }
        } else {
            if (seat.isReserved()) return null;
            Thread.sleep(50);
            return createReservation(passenger, flight, seat);
        }
    }

    private Reservation createReservation(
            Passenger passenger,
            Flight flight,
            Seat seat
    ) {

        seat.reserveSeat();

        Reservation r = new Reservation(
                generateReservationCode(),
                flight,
                passenger,
                seat
        );

        double price = calculatePrice(flight);
        double baggageAllowance = calculateBaggageAllowance(flight);

        Ticket t = new Ticket(
                generateTicketID(),
                r,
                price,
                baggageAllowance
        );

        allReservations.add(r);
        allTickets.add(t);

        return r;
    }

    //rez iptalinden sonra dosyaların silinmeme sorunu çözüldü
    public synchronized void cancelReservation(Reservation r) {
        r.getSeat().cancelReservation();
        allReservations.remove(r);

        saveReservations("reservations.txt");
        saveTickets("tickets.txt");
    }
    public void saveReservations(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Reservation r : allReservations) {
                writer.println(
                        r.getReservationCode() + "," +
                        r.getPassenger().getName() + "," +
                        r.getPassenger().getSurname() + "," +
                        r.getPassenger().getPassengerID() + "," +
                        r.getFlight().getFlightNum() + "," +
                        r.getSeat().getSeatNum()
                );
            }
        } catch (Exception e) {
            System.out.println("Error saving reservations: " + e.getMessage());
        }
    }

    public void saveTickets(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Ticket t : allTickets) {
                writer.println(
                        t.getTicketID() + "," +
                        t.getReservation().getReservationCode() + "," +
                        t.getPrice() + "," +
                        t.getBaggageAllowance()
                );
            }
        } catch (Exception e) {
            System.out.println("Error saving tickets: " + e.getMessage());
        }
    }

    private String generateReservationCode() {
        return "R-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String generateTicketID() {
        return "T-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private double calculatePrice(Flight flight) {
        return 1500.0;
    }

    private double calculateBaggageAllowance(Flight flight) {
        return 20.0;
    }
}
