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
    List<AbstractFlight> flights = new LinkedList<>();

    public Airline(String[]  args){
        this.name = args[0];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addFlight(AbstractFlight abstractFlight) {
        flights.add(abstractFlight);
    }

    @Override
    public Collection getFlights() {
        return flights;
    }
}
