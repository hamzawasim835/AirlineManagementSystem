package airline.gui;

import javax.swing.*;

import airline.manager.FlightManager;

public class MainFrame extends JFrame {

    public MainFrame(String role) {
    	
    	FlightManager sharedManager = new FlightManager();
    	sharedManager.loadPlanes("planes.txt");
    	sharedManager.loadFlights("flights.txt");


        setTitle("Airline Reservation System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // ekran ortası

        JTabbedPane tabs = new JTabbedPane();

        //user
        if (role.equals("USER")) {
            BookingPanel bookingPanel = new BookingPanel();
            tabs.addTab("Booking", bookingPanel);

            SimulationPanel simulationPanel = new SimulationPanel(bookingPanel);

            tabs.addTab("Simulation", simulationPanel);
        }

        //admin
        if (role.equals("ADMIN")) {
            AdminPanel adminPanel = new AdminPanel(sharedManager);
            tabs.addTab("Admin", adminPanel);
        }

        add(tabs);
        setVisible(true);
    }


}
