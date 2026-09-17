package airline.gui;

import airline.manager.FlightManager;
import airline.manager.ReservationManager;
import airline.flight.Flight;
import airline.reservation.Passenger;
import airline.reservation.Reservation;

import javax.swing.*;
import java.util.List;

public class BookingPanel extends JPanel {
	
    private final FlightManager flightManager = new FlightManager();
    private final ReservationManager reservationManager = new ReservationManager();
    private Reservation currentReservation; 
    private SeatGridDialog seatGrid; 
    private Flight selectedFlight;
    private final DefaultListModel<Flight> model = new DefaultListModel<>();
    private final JList<Flight> flightList = new JList<>(model);

    public SeatGridDialog getSeatGrid() {
        return seatGrid;
    }

    public Flight getSelectedFlight() {
        return selectedFlight;
    }
    
    public BookingPanel() {
    	
    	flightList.addListSelectionListener(e -> {
    	    selectedFlight = flightList.getSelectedValue();
    	});
        flightManager.loadPlanes("planes.txt");
        flightManager.loadFlights("flights.txt");

        JTextField tfDestination = new JTextField(10);       
        JTextField tfSeat = new JTextField(4);
        JTextField tfPassenger = new JTextField(8);
        JButton btnSearch = new JButton("Search Flights");
        JButton btnSeat = new JButton("Select Seat");
        JButton btnReserve = new JButton("Reserve Seat");
        JButton btnCancel = new JButton("Cancel Reservation");
        
        btnSearch.addActionListener(e -> {
            model.clear();
            List<Flight> flights = flightManager.searchByDestination(tfDestination.getText());
            flights.forEach(model::addElement);
        });

        btnSeat.addActionListener(e -> {
            try {
                if (selectedFlight == null)
                    throw new IllegalStateException("Select a flight first");

                seatGrid = new SeatGridDialog(selectedFlight, reservationManager);
                seatGrid.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        btnReserve.addActionListener(e -> {
            try {
                Flight selectedFlight = flightList.getSelectedValue();
                if (selectedFlight == null) {
                    throw new IllegalStateException("Please select a flight");
                }

                Passenger passenger = new Passenger(
                        "P1",
                        tfPassenger.getText(),
                        "User",
                        "mail@test.com"
                );
                String seatNum = tfSeat.getText();
                if (seatNum == null || seatNum.isEmpty()) {
                    throw new IllegalStateException("Please enter seat number");
                }
                Reservation r = reservationManager.makeReservation(
                        passenger,
                        selectedFlight,
                        seatNum,
                        true   //synchronized olduğu için
                );
                currentReservation = r;

                JOptionPane.showMessageDialog(this,
                        "Reservation successful!\nSeat: " + r.getSeat().getSeatNum());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> {
            reservationManager.cancelReservation(currentReservation);
            JOptionPane.showMessageDialog(this, "Reservation cancelled");
        });

        add(new JLabel("Destination:"));
        add(tfDestination);
        add(btnSearch);
        add(new JScrollPane(flightList));
        add(new JLabel("Passenger Name:"));
        add(tfPassenger);
        add(btnSeat);
        add(tfSeat);
        add(btnReserve);
    }
}
