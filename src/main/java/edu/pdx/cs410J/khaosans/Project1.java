package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractAirline;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The main class for the CS410J airline Project
 */
public class Project1 {
    private static Boolean readmeFlag = false;
    private static Boolean printFlag = false;
    private static Flight flight;

    /**
     *
     * @param args are the command line arguments
     */
    public static void main(String[] args) {
        Class c = AbstractAirline.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }
        if (args.length == 1 && args[0].equals("-print")) {
            System.err.println("Nothing to print");
            System.exit(1);
        }

        String[] removedOptionsArguments = parseCL(args);

        if (args.length >= 7) {
            //Validate here
            intParser(removedOptionsArguments[1]);
            airportValidator(removedOptionsArguments[2]);
            dateFormatValidator(removedOptionsArguments[3]);
            timeFormatValidator(removedOptionsArguments[4]);
            airportValidator(removedOptionsArguments[5]);
            dateFormatValidator(removedOptionsArguments[6]);
            timeFormatValidator(removedOptionsArguments[7]);

            flight = new Flight(removedOptionsArguments);
            Airline airline = new Airline(removedOptionsArguments);
            airline.addFlight(flight);
        }

        if (printFlag && flight != null) {
            System.out.println(flight.toString());
        }

        if (readmeFlag) {
            System.out.println("Readme LIVES RIGHT HERE");
            //do some shit
        }

        System.exit(1);
    }

    /**
     *
     * @param args
     * @return
     */
    public static String[] parseCL(String[] args) {
        int indexNumber = 0;
        String[] argsToReturn = new String[8];

        for (String value : args) {
            if (value.equals("-README")) {
                readmeFlag = true;
            } else if (value.equals("-print")) {
                printFlag = true;
            } else {
                argsToReturn[indexNumber] = value;
                ++indexNumber;
            }
        }
        return argsToReturn;
    }

    /**
     *
     * @param arg
     */
    public static void dateFormatValidator(String arg) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
        try {
            Date date = dateFormat.parse(arg);
            if (!isDateValid(arg)) {
                throw new ParseException("", 1);
            }
        } catch (ParseException e) {
            System.err.println(arg + " is an invalid date");
            System.exit(1);
        }
    }

    /**
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
        } else if (yyyy < 1000 || yyyy > 9999) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param arg
     */
    public static void timeFormatValidator(String arg) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        try {
            Date time = timeFormat.parse(arg);
            if (!isTimeValid(arg)) {
                throw new ParseException("", 1);
            }
        } catch (ParseException e) {
            System.err.println(arg + "Time Format is wrong");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println(arg + " is not valid time");
            System.exit(1);
        }
    }

    /**
     *
     * @param time
     * @return
     */
    public static boolean isTimeValid(String time) {
        String[] values = time.split(":");
        int hh = Integer.parseInt(values[0]);
        int mm = Integer.parseInt(values[1]);
        if (values[0].length() > 2 || values[1].length() > 2) {
            return false;
        } else if (values.length != 2) {
            return false;
        } else if (hh > 24 || hh < 0) {
            return false;
        } else if (mm >= 60 || mm < 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param arg
     * @return
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
     *
     * @param arg
     */
    public static void airportValidator(String arg) {
        if (!isAlpha(arg) || arg.length() != 3 || arg.length() != 3) {
            System.err.println(arg + " invalid airport");
            System.exit(1);
        }
    }

    /**
     *
     * @param word
     * @return
     */
    public static boolean isAlpha(String word) {
        return word.matches("[a-zA-Z]+");
    }
}