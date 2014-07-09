package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sk on 7/6/14.
 */
public class Airline extends AbstractAirline {
    String name;
    List<AbstractFlight> flights;

    /**
     * Constructor for the airline
     * @param args is the name of the airline.
     */
    public Airline(String[]  args){
        this.name = args[0];
        flights = new LinkedList<>();
    }

    /**
     * Getter for the name field
     * @return a string of the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Method used to add flights to the airline.
     * @param abstractFlight is the flight object
     */
    @Override
    public void addFlight(AbstractFlight abstractFlight) {
        flights.add(abstractFlight);
    }

    /**
     * Method used to get all the flights.
     * @return flight list as a list.
     */
    @Override
    public Collection getFlights() {
        return flights;
    }
}
