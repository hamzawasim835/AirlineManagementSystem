package airline.reservation;

public class Baggage {

    private double weight;

    public Baggage(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isOverLimit(double allowance) {
        return weight > allowance; //20kg dahil olduğu için 
    }
}
