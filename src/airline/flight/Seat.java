package airline.flight;

public class Seat {
    // Defining Members
    private final String seatNum;
    private final SeatClasses Class;
    private int price;
    private boolean reserveStatus;

    // Defining Constructor
    public Seat(String seatNum, SeatClasses Class, int price, boolean reserveStatus) {
        this.seatNum = seatNum;
        this.Class = Class;
        this.price = price;
        this.reserveStatus = reserveStatus;
    }

    // Defining getters & setters
    // For seatNum
    public String getSeatNum() {
        return seatNum;
    }
    // No setter for seatNum. It's inherent to the seat and doesn't change

    // For Class
    public SeatClasses getSeatClass(){
        return Class;
    }
    /*Again, no setter here. A seat's class usually gets configured once in factory
    then stays the same.*/

    // For Price
    public int getPrice() {
        return price;
    }
    /* No setter for price. Makes more sense to constrain price control to
    Constructor and Service module.*/

    // For reserveStatus
    public boolean getReserveStatus() {
        return reserveStatus;
    }
    public void setReserveStatus(boolean newStatus) {
        this.reserveStatus = newStatus;
    }
    
    public void cancelReservation() {
        this.reserveStatus = false;
    }

    // ToString method
    @Override
    public String toString() {
        return seatNum + " (" + Class + ") - " + (reserveStatus ? "Reserved" : "Available");
    }

    // ToFileString method, for later file I/O operations
    public String toFileString() {
        // Format: seatNum,class,price,reserveStatus
        return seatNum + "," + Class + "," + price + "," + reserveStatus;
    }

    //these 2 will be needed for my reservationmanager class
    public boolean isReserved() {
        return reserveStatus;
    }

    public void reserveSeat() {
        this.reserveStatus = true;
    }

}