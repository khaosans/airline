package edu.pdx.cs410J.khaosans;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * The main class for the CS410J airline Project
 */
public class Project3 {
    private static int numberOfOptions = 0;
    private static Boolean readmeFlag = false;
    private static Boolean printFlag = false;
    private static Boolean textFileFlag = false;
    private static Boolean prettyPrintFlag = false;
    private static String fileName;
    private static String prettyFileName;
    private static Flight flight;
    private static Airline airline;
    private static File file = null;
    private static File file2 = null;
    private static Airline airlineFromFile;


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
        //System.out.println(Arrays.toString(removedOptionsArguments));

        if (removedOptionsArguments.length == 10) {
            airline = new Airline(removedOptionsArguments[0]);
            flight = Flight.getFlightFromArgs(removedOptionsArguments);
            airline.addFlight(flight);
        }

        //no pretty print flag
        if (textFileFlag && removedOptionsArguments.length == 10 && !prettyPrintFlag) {
            file = new File(fileName);

            if (file.exists()) {
                String fileToRead = fileName;
                TextParser textParser = new TextParser(fileToRead, removedOptionsArguments[0]);
                Airline toDump = textParser.getAirlineFromFile();
                TextDumper textDumper = new TextDumper(fileName);
                textDumper.dump(airline);

            } else {
                TextDumper textDumper = new TextDumper(fileName);
                textDumper.dump(airline);
            }
        }

        //with prettty print flag
        if (textFileFlag && prettyPrintFlag && removedOptionsArguments.length == 10) {
            if (prettyFileName.equals("-")) {
                if (printFlag) {
                    System.out.println(removedOptionsArguments[0] + " " + flight.toString());
                }
                file = new File(fileName);

                TextParser textParser = new TextParser(fileName, removedOptionsArguments[0]);
                Airline toDump = textParser.getAirlineFromFile();

                System.out.println(toDump.prettyDump());
                System.exit(1);

            } else {

                file = new File(fileName);

                String fileToDumpTo = prettyFileName;
                TextParser textParser = new TextParser(fileName, removedOptionsArguments[0]);
                Airline toDump = textParser.getAirlineFromFile();

                PrettyPrinter prettyPrinter = new PrettyPrinter(fileToDumpTo);
                prettyPrinter.dump(toDump);
            }
        }

        if (printFlag && flight != null) {
            System.out.println(removedOptionsArguments[0] + " " + flight.toString());
            System.exit(1);
        }

        if (readmeFlag) {
            System.out.println("Souriya Khaosanga CS410J Project 3\n" +
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

        if (removedOptionsArguments.length > 10)

        {
            System.err.println("too many arguments");
            System.exit(1);
        }

        if (removedOptionsArguments.length < 10)

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
            } else if (args[i].equals("-textFile")) {
                textFileFlag = true;
                fileName = args[i + 1];
                numberOfOptions += 2;
                ++i;
            } else if (args[i].equals("-pretty")) {
                prettyPrintFlag = true;
                prettyFileName = args[i + 1];
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


}