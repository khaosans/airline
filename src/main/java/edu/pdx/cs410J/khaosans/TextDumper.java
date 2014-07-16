package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractAirline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by souriyakhaosanga on 7/15/14.
 */
public class TextDumper implements edu.pdx.cs410J.AirlineDumper{
    private File airlineData;

    @Override
    public void dump(AbstractAirline abstractAirline) throws IOException {
        if(abstractAirline!=null) {
            FileWriter fileWriter = new FileWriter(new File("testFile"));
            Airline airline = (Airline) abstractAirline;
            fileWriter.write(airline.airlineDump());
        }else{
            System.exit(1);
        }
    }
}
