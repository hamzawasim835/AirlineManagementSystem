package airline.gui;

import airline.flight.Flight;
import airline.flight.Seat;
import airline.manager.ReservationManager;

import javax.swing.*;
import java.awt.*;

public class SeatGridDialog extends JDialog {

    public SeatGridDialog(Flight flight, ReservationManager manager) {

        setTitle("Seat Selection");
        setSize(600, 400);
        setLayout(new GridLayout(30, 6, 5, 5));

        for (int row = 1; row <= 30; row++) {
            for (char col = 'A'; col <= 'F'; col++) {
                String seatNum = row + "" + col;
                Seat seat = flight.getPlane().getSeatBySeatNum(seatNum);

                JButton btn = new JButton(seatNum);
                btn.setBackground(seat.isReserved() ? Color.RED : Color.GREEN);
                add(btn);
            }
        }
    }

    public void refresh(Flight flight) {

        getContentPane().removeAll();
        setLayout(new GridLayout(30, 6, 5, 5));

        for (int row = 1; row <= 30; row++) {
            for (char col = 'A'; col <= 'F'; col++) {
                String seatNum = row + "" + col;
                Seat seat = flight.getPlane().getSeatBySeatNum(seatNum);

                JButton btn = new JButton(seatNum);
                btn.setBackground(seat.isReserved() ? Color.RED : Color.GREEN);
                add(btn);
            }
        }

        revalidate();
        repaint();
    }
}
