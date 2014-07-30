package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirportNames;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by sk on 7/6/14.
 */
public class Flight extends AbstractFlight implements Comparable<Flight> {
    private String flightNumber;
    private String source;
    private String destination;
    private long duration;

    private Date departureTime;
    private Date arrivalTime;

    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    private DateFormat dfLong = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);

    /**
     * Constructor for the flight
     * @param number flight number
     * @param source airport source
     * @param departure departure time
     * @param destination airpirt destination
     * @param arrival arival time
     */
    public Flight(String number, String source, Date departure, String destination, Date arrival, long durationOfFlight) {
        this.flightNumber = number;
        this.source = source;
        this.departureTime = departure;
        this.destination = destination;
        this.arrivalTime = arrival;
        this.duration = durationOfFlight;
    }

    /**
     * Method for getting length of flight
     * @return long value of the duration of the flight
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Used to check equality
     * @param o object of flight
     * @return boolean if the flights are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (arrivalTime != null ? !arrivalTime.equals(flight.arrivalTime) : flight.arrivalTime != null) return false;
        if (departureTime != null ? !departureTime.equals(flight.departureTime) : flight.departureTime != null)
            return false;
        if (destination != null ? !destination.equals(flight.destination) : flight.destination != null) return false;
        if (df != null ? !df.equals(flight.df) : flight.df != null) return false;
        if (flightNumber != null ? !flightNumber.equals(flight.flightNumber) : flight.flightNumber != null)
            return false;
        if (source != null ? !source.equals(flight.source) : flight.source != null) return false;

        return true;
    }

    /**
     * Method for getting the length of flight
     * @param arrival takes the arrival date
     * @param departure takes the departure date
     * @return long value for the duration of flight
     */
    public static long getLengthOfFlight(Date arrival, Date departure) {
        return (arrival.getTime() - departure.getTime()) / 6000;
    }


    /**
     * MEthod used for validating the command line
     * @param args command line arguments
     * @return Flight constructed from arguments
     */
    public static Flight getFlightFromArgs(String[] args) {
        /*int i =0;
        for(String s:args){
            System.out.println(i+" "+s);
            ++i;
        }*/
        int validFlight = intParser(args[1]);

        validAirportName(args[2]);
        String validSource = airportValidator(args[2]);

        String validSourceDate = dateFormatValidator(args[3]);
        String validSourceTime = timeFormatValidator(args[4]);
        String sourceAmPM = amPmValidator(args[5]);

        validAirportName(args[6]);
        String validDest = airportValidator(args[6]);

        String validDestDate = dateFormatValidator(args[7]);
        String validDestTime = timeFormatValidator(args[8]);
        String destAmPm = amPmValidator(args[9]);


        Date departureString = parseDate(validSourceDate, validSourceTime,sourceAmPM);
        Date arrivalString = parseDate(validDestDate, validDestTime,destAmPm);

        long durationOfFlight = getLengthOfFlight(arrivalString, departureString);

        return new Flight(String.valueOf(validFlight), validSource, departureString, validDest, arrivalString, durationOfFlight);

    }

    /**
     * Getter for the identification number
     *
     * @return the identification number
     */
    @Override
    public int getNumber() {
        return intParser(flightNumber);
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
        return df.format(departureTime);
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
        return df.format(arrivalTime);
    }


    /**
     * Getter for the arrival date
     * @return arrvial date object
     */
    @Override
    public Date getArrival() {
        return super.getArrival();
    }

    /**
     * Getter for the departure date
     * @return the departue date object
     */
    @Override
    public Date getDeparture() {
        return super.getDeparture();
    }


    /**
     * Method used to part the date from string
     * @param date string of the date
     * @param time a string of the time that is in the format of HH:mm.
     * @param a am or pm value
     * @return return a date object parsed from those 3 args
     */
    public static Date parseDate(String date, String time, String a){
        Date date1 = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy hh:mm a");
        String dateAndTime = date + " " + time + " " + a;
        try{
            date1 = dateFormat.parse(dateAndTime);
        } catch (ParseException e) {

            System.err.println("date and time are malformed " + dateAndTime);
            System.exit(1);
        }
        return date1;
    }

    /**
     * Method used to validate the format of the input date
     *
     * @param arg is a string of the date as follows MM/DD/YYYY
     */
    public static String dateFormatValidator(String arg) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date date = dateFormat.parse(arg);
            if (!isDateValid(arg)) {
                throw new ParseException("", 1);
            }
        } catch (ParseException e) {
            System.err.println(arg + " is an invalid date");
            System.exit(1);
        }

        if (!isDateValid(arg)) {
            System.err.println(arg + " is an invalid date");
            System.exit(1);
        }

        return arg;


    }

    /**
     * Method used to validate the individual numbers in the date.
     *
     * @param date a string that that contains the date month/day/year
     * @return a boolean value of true the date numbers are valid
     */
    public static boolean isDateValid(String date) {
        String[] values = date.split("/");
        int mm = Integer.parseInt(values[0]);
        int dd = Integer.parseInt(values[1]);
        int yyyy = Integer.parseInt(values[2]);
        if (values[0].length() > 2 || values[1].length() > 2 || values[2].length() > 4) {
            return false;
        } else if (values.length != 3) {
            return false;
        } else if (mm > 12 || mm < 1) {
            return false;
        } else if (dd < 1 || dd > 31) {
            return false;
        } else if (yyyy < 0 || yyyy > 9999) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method used to validate the time formatt such that it is always HH:mm
     *
     * @param arg a string of time as above.
     */
    public static String timeFormatValidator(String arg) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        try {
            Date time = timeFormat.parse(arg);
            if (!isTimeValid(arg)) {
                throw new ParseException("", 1);
            }
        } catch (ParseException e) {
            System.err.println(arg + " Time Format is wrong");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println(arg + " is not valid time");
            System.exit(1);
        }

        if (!isTimeValid(arg)) {
            System.err.println(" invalid time format");
            System.exit(1);
        }
        return arg;
    }

    /**
     * Method used to validate that the integer values of the time are proper.
     *
     * @param time a string of the time that is in the format of HH:mm.
     * @return a boolean value signalling that the values are within specification.
     */
    public static boolean isTimeValid(String time) {
        String[] values = time.split(":");
        int hh = Integer.parseInt(values[0]);
        int mm = Integer.parseInt(values[1]);
        if (values[0].length() > 2 || values[1].length() > 2) {
            return false;
        } else if (values.length != 2) {
            return false;
        } else if (hh > 12 || hh < 0) {
            return false;
        } else if (mm >= 60 || mm < 0) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Method that is used to parse a string into an integer, that throws exception when the value is
     * not an integer.
     *
     * @param arg is a string value that may or may not be a number.
     * @return the integer converted from the string args.
     */
    public static int intParser(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            System.err.println("invalid flight value");
            System.exit(1);
        }
        return 0;
    }

    /**
     * Method used to validate that a string only contains three letters and is a letter from the alphabet.
     * If the string is not a valid 3 letters, it will signal an error and exit the program.
     *
     * @param arg a string value.
     */
    public static String airportValidator(String arg) {
        if (!isAlpha(arg) || arg.length() != 3 || arg.length() != 3) {
            System.err.println(arg + " invalid airport");
            System.exit(1);
        }
        return arg;
    }

    /**
     * Method using a regular expression to see whether the input only contains letters from the alphabet.
     *
     * @param word
     * @return a boolean value that signals whether the input is only letters.
     */
    public static boolean isAlpha(String word) {
        return word.matches("[a-zA-Z]+");
    }

    /**
     * Used to compare object to eachother for sorting
     * @param o a flight object used to compare the airport source then date
     * @return integer value signaling the difference
     */
    @Override
    public int compareTo(Flight o) {
        if (this.getSource().compareTo(o.getSource()) != 0) {
            return this.getSource().compareTo(o.getSource());
        } else {
            return this.departureTime.toString().compareTo(o.departureTime.toString());
            //return this.getDepartureString().compareTo(o.getDepartureString());
        }
    }

    /**
     * Method used to validate airports
     * @param name any 3 digit string for an airport
     */
    public static void validAirportName(String name) {
        if (AirportNames.getName(name.toUpperCase()) == null) {
            System.err.println(name + " is Invalid");
            System.exit(1);
        }
    }

    /**
     * method used to validate am or pm input
     * @param arg input of am or pm string
     * @return if valid returns the args of am or pm not case sensitive
     */
    public static String amPmValidator(String arg){
        if (arg.toUpperCase().equals("AM") || arg.toUpperCase().equals("PM")){
            return arg;
        }else{
            System.err.println("Not valid AM or PM");
            System.exit(1);
        }
        return null;
    }
}

