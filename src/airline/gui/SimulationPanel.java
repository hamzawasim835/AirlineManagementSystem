package airline.gui;

import airline.manager.MultiThreadingSimulator;
import airline.flight.Flight;

import javax.swing.*;

public class SimulationPanel extends JPanel {

    private BookingPanel bookingPanel;

    public SimulationPanel(BookingPanel bookingPanel) {

        this.bookingPanel = bookingPanel;

        JCheckBox syncBox = new JCheckBox("Synchronized");
        JButton runBtn = new JButton("Run Simulation");
        JLabel status = new JLabel("Idle");

        runBtn.addActionListener(e -> {

            SeatGridDialog seatGrid = bookingPanel.getSeatGrid();
            Flight flight = bookingPanel.getSelectedFlight();

            if (seatGrid == null || flight == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a flight and seat first");
                return;
            }

            status.setText("Running...");

            new Thread(() -> {
                try {
                	MultiThreadingSimulator.startSimulation(
                	        bookingPanel.getSelectedFlight(),
                	        syncBox.isSelected()
                	);

                    SwingUtilities.invokeLater(() -> {
                        seatGrid.refresh(flight);
                        status.setText("Completed");
                    });

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
        });

        add(syncBox);
        add(runBtn);
        add(status);
    }
}
