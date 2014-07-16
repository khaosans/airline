package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractAirline;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * The main class for the CS410J airline Project
 */
public class Project2 {
    private static int numberOfOptions = 0;
    private static Boolean readmeFlag = false;
    private static Boolean printFlag = false;
    private static Boolean textFileFlag = false;
    private static String fileName = null;
    private static Flight flight;
    private static Airline airline;

    /**
     * Main method used to run everything
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

        String[] removedOptionsArguments = Arrays.copyOfRange(parseCL(args), 0, args.length - numberOfOptions);

        if (removedOptionsArguments.length > 8 || removedOptionsArguments.length < 8) {
            System.err.println("Wrong number of arguments");
            System.exit(1);
        }

        if (removedOptionsArguments.length == 8) {
            intParser(removedOptionsArguments[1]);
            airportValidator(removedOptionsArguments[2]);
            dateFormatValidator(removedOptionsArguments[3]);
            timeFormatValidator(removedOptionsArguments[4]);
            airportValidator(removedOptionsArguments[5]);
            dateFormatValidator(removedOptionsArguments[6]);
            timeFormatValidator(removedOptionsArguments[7]);

            flight = new Flight(removedOptionsArguments);
            airline = new Airline(removedOptionsArguments[0]);
            airline.addFlight(flight);
        }
        if (textFileFlag) {
            try {
                if(new TextParser(fileName).isSameAirline(airline.getName())){
                    new TextDumper(fileName).dump(airline);
                }else{
                    System.err.print("Not the same airline");
                    System.exit(1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (printFlag && flight != null) {
            System.out.println(flight.toString());
        }

        if (readmeFlag) {
            System.out.println(
                    "usage: java edu.pdx.cs410J.<login-id>.Project1 [options] <args>\n" +
                            "args are (in this order):\n" +
                            "name The name of the airline\n" +
                            "flightNumber The flight number\n" +
                            "src Three-letter code of departure airport\n" +
                            "departTime Departure date and time (24-hour time)\n" +
                            "dest Three-letter code of arrival airport\n" +
                            "arriveTime Arrival date and time (24-hour time)\n" +
                            "options are (options may appear in any order):\n" +
                            "-print Prints a description of the new flight\n" +
                            "-README Prints a README for this project and exits\n" +
                            "Date and time should be in the format: mm/dd/yyyy hh:mm\n");
        }
        System.exit(1);
    }


    /**
     * Method is used to parse the command line arguments and sets the flags from the every option.
     * It also removes the options from the command line arguments.
     *
     * @param args is the command line arguments
     * @return an array of arguments minus all the options passed in.
     */
    public static String[] parseCL(String[] args) {
        String[] argsToReturn = new String[args.length];
        int indexNumber = 0;

        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-README")) {
                readmeFlag = true;
                ++numberOfOptions;
            } else if (args[i].equals("-print")) {
                printFlag = true;
                ++numberOfOptions;
            } else if (args[i].equals("-textFile")) {
                textFileFlag = true;
                fileName = args[i + 1];
                numberOfOptions += 2;
                ++i;
            } else if (args[i].startsWith("-")) {
                System.err.print("Invalid argument");
                System.exit(1);
            } else {
                argsToReturn[indexNumber] = args[i];
                ++indexNumber;
            }
        }

        return argsToReturn;
    }

    /**
     * Method used to validate the format of the input date
     *
     * @param arg is a string of the date as follows MM/DD/YYYY
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
        } else if (yyyy < 1000 || yyyy > 9999) {
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
        } else if (hh > 24 || hh < 0) {
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
     * @param arg is a string value that may or maynot be a number.
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
    public static void airportValidator(String arg) {
        if (!isAlpha(arg) || arg.length() != 3 || arg.length() != 3) {
            System.err.println(arg + " invalid airport");
            System.exit(1);
        }
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
}