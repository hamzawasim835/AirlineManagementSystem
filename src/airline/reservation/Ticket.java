package airline.reservation;

public class Ticket {

    private String ticketID;
    private Reservation reservation;
    private double price;
    private double baggageAllowance;

    public Ticket(String ticketID, Reservation reservation,
                  double price, double baggageAllowance) {
        this.ticketID = ticketID;
        this.reservation = reservation;
        this.price = price;
        this.baggageAllowance = baggageAllowance;
    }

    public String getTicketID() {
        return ticketID;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public double getPrice() {
        return price;
    }

    public double getBaggageAllowance() {
        return baggageAllowance;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID='" + ticketID + '\'' +
                ", price=" + price +
                ", baggageAllowance=" + baggageAllowance +
                '}';
    }
}
