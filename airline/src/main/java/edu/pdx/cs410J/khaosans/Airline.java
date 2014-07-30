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
     * Contructor for Airline
     * @param name :  the name of this airline
     */
    public Airline(String name) {
        this.name = name;
        this.flights = new LinkedList<>();
    }

    /**
     * Method used to compare equality
     * @param o Pass in a flight object to chack for equality
     * @return returns boolean if it's equal to the other object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airline airline = (Airline) o;

        if (flights != null ? !flights.equals(airline.flights) : airline.flights != null) return false;
        if (name != null ? !name.equals(airline.name) : airline.name != null) return false;

        return true;
    }

    /**
     * Getter for name field
     * @return string for name field
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Method for adding flights to the airline
     * @param flight object of flight to be added
     */

    @Override
    public void addFlight(AbstractFlight flight) {
        flights.add((Flight) flight);
        Collections.sort(flights);
    }

    /**
     * Method for getting the flights
     * @return collection or list of flights
     */
    @Override
    public Collection getFlights() {
        return flights;
    }

    /**
     * Method used to dump all flights in list
     * @return returns strings of the list of flights
     */
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

    /**
     * Method used for pretty print feature
     * @return returns a nicely formatted string of flights
     */
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