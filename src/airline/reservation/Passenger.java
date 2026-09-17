package airline.reservation;

public class Passenger {

    private String passengerID;
    private String name;
    private String surname;
    private String contactInfo;

    public Passenger(String passengerID, String name, String surname, String contactInfo) {
        this.passengerID = passengerID;
        this.name = name;
        this.surname = surname;
        this.contactInfo = contactInfo;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    //gerek yok şimdilik
    public String getContactInfo() {
        return contactInfo;
    }

    @Override
    public String toString() {
        return name + " " + surname + " (ID: " + passengerID + ")";
    }
}
