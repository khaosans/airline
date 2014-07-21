package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractFlight;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by sk on 7/6/14.
 */
public class Flight extends AbstractFlight implements Comparable<Flight> {
    private int identificationNumber;
    private String source;
    private String departureTime;
    private String destination;
    private String arrivalTime;

    private java.util.Date departureTime2;
    private java.util.Date arrivaleTime2;


    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

    /**
     * Constructor for the flight
     *
     * @param args are the command line arguments
     */
    public Flight(String[] args) {
        this.identificationNumber = Integer.parseInt(args[1]);
        this.source = args[2];
        this.departureTime = args[3] + " " + args[4];
        this.destination = args[5];
        this.arrivalTime = args[6] + " " + args[7];

        this.departureTime2 = parseDate(args[3], args[4]);
        this.arrivaleTime2 = parseDate(args[6], args[7]);

    }

    /**
     * Getter for the identification number
     *
     * @return the identification number
     */
    @Override
    public int getNumber() {
        return identificationNumber;
    }

    /**
     * Getter for the source airport
     *
     * @return a string of the source airport
     */
    @Override
    public String getSource() {
        return source;
    }

    /**
     * Getter for the departure string
     *
     * @return the get departure time string
     */
    @Override
    public String getDepartureString() {
        return df.format(departureTime2);
    }

    /**
     * Getter for the destination string
     *
     * @return the string destination
     */
    @Override
    public String getDestination() {
        return destination;
    }

    /**
     * Getter for the arrival string
     *
     * @return the arrival time
     */
    @Override
    public String getArrivalString() {
        return df.format(arrivaleTime2);
    }

    @Override
    public Date getArrival() {
        return super.getArrival();
    }

    @Override
    public Date getDeparture() {
        return super.getDeparture();
    }


    public java.util.Date parseDate(String date, String time) {

        String[] dateSplit = date.split("/");
        String[] timeSplit = time.split(":");

        return new Date(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]),
                Integer.parseInt(timeSplit[0]), Integer.parseInt(timeSplit[1]));
    }

    @Override
    public int compareTo(Flight o) {
        return this.source.compareTo(o.getSource());
    }
}
