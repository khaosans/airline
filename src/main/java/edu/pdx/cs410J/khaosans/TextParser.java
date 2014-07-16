package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sk on 7/15/14.
 */
public class TextParser implements edu.pdx.cs410J.AirlineParser {
    FileReader fileReader;
    Airline airline;
    String airlineName;

    public static void main(String[] args) throws IOException {
        TextParser fileToParse = new TextParser("testFile.txt");
        System.out.println(fileToParse.isSameAirline());
    }

    public TextParser(String fileName) {
        try {
            this.fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.err.print("file doesn't exist");
            System.exit(1);
        }
    }


    @Override
    public AbstractAirline parse() throws ParserException {
        return null;
    }

    /**
     * @return Boolean value signaling if the file contains the same airline
     * @throws IOException
     */
    public boolean isSameAirline() throws IOException {
        String toCheck = "";

        BufferedReader br = new BufferedReader(fileReader);
        String line;
        while ((line = br.readLine()) != null) {
            String[] split = line.split(" ");
            toCheck += split[0] + " ";
        }
        br.close();
        String[] toCompare = toCheck.split(" ");

        String firstValue = toCompare[0];

        for (String value : toCompare) {
            if (!firstValue.equals(value)) {
                return false;
            }
        }
        airlineName = firstValue;
        return true;
    }

    public Airline getAirlineFromFile() throws IOException {
        if (isSameAirline()) {
            airline = new Airline(airlineName);
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                inputValidator(split);
                Flight flight = new Flight(split);
                airline.addFlight(flight);
            }
            br.close();
        }
        return null;
    }

    public void inputValidator(String[] args) {
        int i = 0;
        for (String arg : args) {
            System.out.println("[" + i + "]" + arg);
            ++i;
        }

        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        if (args.length >= 7) {
            intParser(args[1]);
            airportValidator(args[2]);
            dateFormatValidator(args[3]);
            timeFormatValidator(args[4]);
            airportValidator(args[5]);
            dateFormatValidator(args[6]);
            timeFormatValidator(args[7]);
        }
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
