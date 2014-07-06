package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractFlight;

/**
 * Created by sk on 7/6/14.
 */
public class Flight extends AbstractFlight {

    String source;
    String destination;
    String departure;
    String arrival;
    int identificationNumber;

    @Override
    public int getNumber() {
        return identificationNumber;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getDepartureString() {
        return null;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public String getArrivalString() {
        return null;
    }
}
