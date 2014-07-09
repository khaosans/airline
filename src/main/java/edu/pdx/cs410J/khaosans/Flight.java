package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractFlight;

/**
 * Created by sk on 7/6/14.
 */
public class Flight extends AbstractFlight {
    private int identificationNumber;
    private String source;
    private String departureTime;
    private String destination;
    private String arrivalTime;


    /**
     *
     * @param args
     */
    public Flight(String [] args){
        this.identificationNumber = Integer.parseInt(args[1]);
        this.source = args[2];
        this.departureTime = args[3]+" "+args[4];
        this.destination = args[5];
        this.arrivalTime = args[6]+" "+args[7];
    }

    /**
     *
     * @return
     */
    @Override
    public int getNumber() {
        return identificationNumber;
    }

    /**
     *
     * @return
     */
    @Override
    public String getSource() {
        return source;
    }

    /**
     *
     * @return
     */
    @Override
    public String getDepartureString() {
        return departureTime;
    }

    /**
     *
     * @return
     */
    @Override
    public String getDestination() {
        return destination;
    }

    /**
     *
     * @return
     */
    @Override
    public String getArrivalString() {
        return arrivalTime;
    }
}
