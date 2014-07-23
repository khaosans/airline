package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by sk on 7/15/14.
 */
public class TextParser implements edu.pdx.cs410J.AirlineParser {
    String fileName;
    String airlineName;

    /**
     * This method is the constructor used for validating if the file exist or not
     *
     * @param fileName the string of the file name
     */
    public TextParser(String fileName,String airlineName) {
        this.fileName = fileName;
        this.airlineName = airlineName;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            br.close();
        } catch (FileNotFoundException e) {
            System.err.print("No file exists");
        } catch (IOException e) {
            System.err.print("File I/O error");
        }
    }

    @Override
    public AbstractAirline parse() throws ParserException {
        return getAirlineFromFile();
    }

    public Airline getAirlineFromFile() {
        Airline airline = new Airline(airlineName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] args = line.split(" ");
                    Flight flight = Flight.getFlightFromArgs(args);
                    airline.addFlight(flight);
                }catch (Exception e){
                    System.err.println("Illegit input file!!! Check the file input format");
                    System.exit(1);
                }
            }
            bufferedReader.close();
            if(!isSameAirline()){
                System.err.println("Not the same Airline");
                System.exit(1);
            }

        } catch (IOException e) {
            System.err.print("File IO error");
            System.exit(1);
        }
        return airline;
    }

    public boolean isSameAirline() throws IOException {
        String toCheck = "";

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] split = line.split(" ");
            toCheck += split[0] + " ";
        }
        String[] toCompare = toCheck.split(" ");


        for (String value : toCompare) {
            if (!airlineName.equals(value)) {
                return false;
            }
        }
        br.close();
        return true;
    }
}
