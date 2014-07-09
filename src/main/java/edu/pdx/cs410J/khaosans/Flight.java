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
     * Constructor for the flight
     * @param args are the command line arguments
     */
    public Flight(String [] args){
        this.identificationNumber = Integer.parseInt(args[1]);
        this.source = args[2];
        this.departureTime = args[3]+" "+args[4];
        this.destination = args[5];
        this.arrivalTime = args[6]+" "+args[7];
    }

    /**
     * Getter for the identification number
     * @return the identification number
     */
    @Override
    public int getNumber() {
        return identificationNumber;
    }

    /**
     * Getter for the source airport
     * @return a string of the source airport
     */
    @Override
    public String getSource() {
        return source;
    }

    /**
     * Getter for the departure string
     * @return the get departure time string
     */
    @Override
    public String getDepartureString() {
        return departureTime;
    }

    /**
     * Getter for the destination string
     * @return the string destination
     */
    @Override
    public String getDestination() {
        return destination;
    }

    /**
     * Getter for the arrival string
     * @return the arrival time
     */
    @Override
    public String getArrivalString() {
        return arrivalTime;
    }
}
