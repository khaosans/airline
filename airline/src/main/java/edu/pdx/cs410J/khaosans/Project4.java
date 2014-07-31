package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.util.Arrays;

/**
 * The main class for the CS410J airline Project
 */
public class Project4 {
    private static int numberOfOptions = 0;
    private static Boolean readmeFlag = false;
    private static Boolean printFlag = false;
    private static Boolean portFlag = false;
    private static Boolean hostFlag = false;
    private static Boolean searchFlag = false;

    private static Flight flight;
    private static Airline airline;
    private static String port = null;
    private static String host = null;

    /**
     * Main method used to run everything
     *
     * @param args are the command line arguments
     */
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        if (args.length == 1 && args[0].equals("-print")) {
            System.err.println("Nothing to print");
            System.exit(1);
        }

        String[] removedOptionsArguments = Arrays.copyOfRange(parseCL(args), 0, args.length - numberOfOptions);

        if (portFlag && !hostFlag) {
            System.err.println("missing host");
            System.exit(1);
        }
        if (!portFlag && hostFlag) {
            System.err.println("missing port");
            System.exit(1);
        }
        if (!portFlag) {
            System.err.println("missing port and host");
            System.exit(1);
        }

        AirlineRestClient client = new AirlineRestClient(host, intParser(port));

        if (removedOptionsArguments.length == 10 && !searchFlag) {
            airline = new Airline(removedOptionsArguments[0]);
            flight = Flight.getFlightFromArgs(removedOptionsArguments);
            client.addFlight(airline.getName(), flight);
        }

        HttpRequestHelper.Response response;

        if (removedOptionsArguments.length == 1 && searchFlag) {
            response = client.getAirline(removedOptionsArguments[0]);
            System.out.println(response.getContent());
        }

        if (removedOptionsArguments.length == 3 && searchFlag) {
            response = client.getAirlineSrcDest(removedOptionsArguments[0], removedOptionsArguments[1],
                    removedOptionsArguments[2]);
            System.out.println(response.getContent());
        }

        if (readmeFlag) {
            System.out.println("Souriya Khaosanga CS410J Project 4\n" +
                    "The purpose of this program is to be able to add airlines \n" +
                    "and flights to an existing database.  This tool also allows you to write and read\n" +
                    "from existing files.  This will vary depending on your input.  If a file exist,\n" +
                    "it'll read it and validate it's the same airline and continue to write\n" +
                    "It also has a pretty print feature that can print to a screen or output to a file\n" +
                    "usage: java edu.pdx.cs410J.<login-id>.Project3 [options] <args> \n" +
                    "args are (in this order): \n" +
                    "name The name of the airline \n" +
                    "flightNumber The flight number\n " +
                    "src Three-letter code of departure airport \n" +
                    "departTime Departure date time am/pm\n" +
                    "dest Three-letter code of arrival airport\n" +
                    "arriveTime Arrival date time am/pm\n" +
                    "options are (options may appear in any order):\n" +
                    "-pretty file Pretty print the airline’s flights to\n" +
                    "a text file or standard out (file -)\n" +
                    "-textFile file Where to read/write the airline info\n" +
                    "-print Prints a description of the new flight\n" +
                    "-README Prints a README for this project and exits\n");
            System.exit(1);
        }

        if (removedOptionsArguments.length > 10 && removedOptionsArguments.length != 3 &&
                removedOptionsArguments.length != 1)

        {
            System.err.println("too many arguments");
            System.exit(1);
        }

        if (removedOptionsArguments.length < 10 && removedOptionsArguments.length != 3 &&
                removedOptionsArguments.length != 1)

        {
            System.err.println("too few arguments");
            System.exit(1);
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
            } else if (args[i].equals("-port")) {
                portFlag = true;
                port = args[i + 1];
                numberOfOptions += 2;
                ++i;
            } else if (args[i].equals("-host")) {
                hostFlag = true;
                host = args[i + 1];
                numberOfOptions += 2;
                ++i;
            } else if (args[i].equals("-search")) {
                searchFlag = true;
                ++numberOfOptions;
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
            System.err.println("Invalid Port " + arg);
            System.exit(1);
        }
        return 0;
    }

}