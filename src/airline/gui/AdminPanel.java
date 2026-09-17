package airline.gui;

import airline.flight.Flight;
import airline.flight.Route;
import airline.flight.Plane;
import airline.manager.FlightManager;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

    private final FlightManager flightManager;

    private DefaultListModel<Flight> model = new DefaultListModel<>();
    private JList<Flight> flightList = new JList<>(model);

    public AdminPanel(FlightManager flightManager) {
    	this.flightManager = flightManager;

        setLayout(new BorderLayout());

        //flight list
        JScrollPane listScroll = new JScrollPane(flightList);
        listScroll.setPreferredSize(new Dimension(350, 0));
        add(listScroll, BorderLayout.WEST);

        //form
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        JTextField tfFlightNo = new JTextField(8);
        JTextField tfFrom = new JTextField(8);
        JTextField tfTo = new JTextField(8);

        tfFlightNo.setEnabled(true); //uçuş no editlenemez dedim

        JButton btnAdd = new JButton("Add Flight");
        JButton btnUpdate = new JButton("Update Flight");

        //uçuşları yükle
        for (Flight f : flightManager.getFlights()) {
            model.addElement(f);
        }

        //list
        flightList.addListSelectionListener(e -> {
            Flight selected = flightList.getSelectedValue();
            if (selected != null) {
                tfFlightNo.setText(selected.getFlightNum());
                tfFrom.setText(selected.getRoute().getOrigin());
                tfTo.setText(selected.getRoute().getDestination());
            }
        });

        //ekle
        btnAdd.addActionListener(e -> {

            String no = tfFlightNo.getText();
            String from = tfFrom.getText();
            String to = tfTo.getText();

            if (no.isEmpty() || from.isEmpty() || to.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields");
                return;
            }

            Plane plane = flightManager.getAnyPlane();
            if (plane == null) {
                JOptionPane.showMessageDialog(this,
                        "No plane available. Load planes first.");
                return;
            }

            Route route = new Route(from, to);
            Flight flight = new Flight(no, plane, route);

            flightManager.addFlight(flight);
            model.addElement(flight);

            JOptionPane.showMessageDialog(this, "Flight added");

            tfFlightNo.setText("");
            tfFrom.setText("");
            tfTo.setText("");
        });

        //edit
        btnUpdate.addActionListener(e -> {

            Flight selected = flightList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Select a flight first");
                return;
            }

            selected.setRoute(new Route(
                    tfFrom.getText(),
                    tfTo.getText()
            ));

            flightList.repaint();
            JOptionPane.showMessageDialog(this, "Flight updated");
        });

        
        formPanel.add(new JLabel("Flight No:"));
        formPanel.add(tfFlightNo);

        formPanel.add(new JLabel("From:"));
        formPanel.add(tfFrom);

        formPanel.add(new JLabel("To:"));
        formPanel.add(tfTo);

        formPanel.add(btnAdd);
        formPanel.add(btnUpdate);

        add(formPanel, BorderLayout.CENTER);
    }
}
