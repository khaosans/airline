package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sk on 7/6/14.
 */
public class Airline extends AbstractAirline {
    private String name;
    private List<Flight> flights;

    /**
     * @param name :  the name of this airline
     */
    public Airline(String name) {
        this.name = name;
        this.flights = new LinkedList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airline airline = (Airline) o;

        if (flights != null ? !flights.equals(airline.flights) : airline.flights != null) return false;
        if (name != null ? !name.equals(airline.name) : airline.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (flights != null ? flights.hashCode() : 0);
        return result;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addFlight(AbstractFlight flight) {
        flights.add((Flight) flight);
        Collections.sort(flights);
    }

    @Override

    public Collection getFlights() {
        return flights;
    }

    public String flightDump() {
        String dump = "";
        for (Flight flight : flights) {
            dump += name + " " +
                    flight.getNumber() + " " +
                    flight.getSource() + " " +
                    flight.getDepartureString() + " " +
                    flight.getDestination() + " " +
                    flight.getArrivalString() + "\n";
        }
        return dump;
    }

    public String prettyDump() {

        String dump = "============== Airline: "+name+" ==============\n";
        for (Flight flight : flights) {
            dump += "FlightNumber: " + flight.getNumber() + "\n" +
                    "\t From Airport: " + flight.getSource() + "\n" +
                    "\t Departure: " + flight.getDepartureString() + "\n" +
                    "\t To Airport: " + flight.getDestination() + "\n" +
                    "\t Arrival: " + flight.getArrivalString() + "\n" +
                    "\t Duration of Flight(Minutes): " + flight.getDuration() + "\n";
        }
        return dump;
    }


}
