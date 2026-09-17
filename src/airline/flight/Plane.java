package airline.flight;

import java.util.ArrayList;
import java.util.List;

public class Plane {
    // Defining members
    private final String planeID;
    private final String planeModel;
    private final int capacity;
    private final Seat[][] seatMap;
    private final int rows;
    private final int cols;
    // Change he made
    private Seat[][] seatMatrix;

    // Defining Constructor
    public Plane(String planeID, String planeModel, int capacity, int rows, int cols) {
        this.planeID = planeID;
        this.planeModel = planeModel;
        this.capacity = capacity;

        this.rows = rows;
        this.cols = cols;

        this.seatMap = new Seat[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                String seatNum = (r + 1) + String.valueOf((char)('A' + c));
                seatMap[r][c] = new Seat(seatNum, SeatClasses.ECONOMY, 100, false);   // or new Seat(r+1, (char)('A'+c))
            }
        }
    }

    // Defining Getters and Setters
    // For planeID
    public String getPlaneID() {
        return planeID;
    }
    // No setter, it's a legal identifier that seldom, if ever changes

    // For planeModel
    public String getPlaneModel() {
        return planeModel;
    }
    // No setter, a plane's model simply doesn't change

    // For capacity
    public int getCapacity() {
        return capacity;
    }
    /* No setter, capacity for an airplane is fairly static unless a major retrofit
    is ordered. Modelling such a retrofit is out of the scope of this project*/

    // For rows and cols
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }

    // Adding this to allow later flexibility for the management module
    public void setSeat(int row, int col, Seat seat) {
        // Using if condition to force legal assignment
        if (row >= 0 && row <= rows && col >= 0 && col <= cols) {
            this.seatMap[row][col] = seat;
        }
        else{
            System.out.println("Invalid row or column. Please ensure coordinates are within legal bounds");
        }
    }

    // Adding reserved seat counter for report later on
    public int reservedSeatCounter(){
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (seatMap[i][j] != null && seatMap[i][j].getReserveStatus() == true) {
                    count++;
                }
            }
        }
        return count;
    }

    // Adding getSeat methods for use by other modules and methods
    // Simple version of getSeat, uses coordinates
    public Seat getSeatByCoordinates(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return this.seatMap[row][col];
        }
        else{
            return null;
        }
    }
    // String name version (e.g 15B or 12A), primarily for GUI later on
    public Seat getSeatBySeatNum(String seatNum) {
        // Guarding against potential abuse
        if (seatNum == null || seatNum.length() < 2) return null;

        try {
            // Converting the letter part of the seatNum into an integer for the column index
            char columnLetter = Character.toUpperCase(seatNum.charAt(seatNum.length() - 1));
            int colIdx = columnLetter - 'A';

            // Converting the number part of seatNum into an integer for the row index
            String rowPart = seatNum.substring(0, seatNum.length() - 1);
            int rowIdx = Integer.parseInt(rowPart) - 1;

            // Using existing index-based getter
            return getSeatByCoordinates(rowIdx, colIdx);
        } catch (Exception e) {
            // If someone passes "ABC" or other invalid inputs
            return null;
        }
    }

    // Change he made
    public Iterable<Seat> getAllSeats() {
        List<Seat> seats = new ArrayList<>();
        for (Seat[] row : seatMatrix) {
            for (Seat seat : row) {
                seats.add(seat);
            }
        }
        return seats;
    }

    public Seat[][] getSeatMap() {
        return seatMap;
    }

    // Adding a toString method
    @Override
    public String toString() {
        return "Plane ID: " + planeID + " | Model: " + planeModel + " | Capacity: " + capacity;
    }

    // Adding a toFileString method for later file I/O operations
    public String toFileString() {
        // Format: planeID,model,capacity,rows,cols
        return planeID + "," + planeModel + "," + capacity + "," + rows + "," + cols;
    }
}