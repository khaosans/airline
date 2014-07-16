package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractAirline;

import java.io.*;

/**
 * Created by souriyakhaosanga on 7/15/14.
 */
public class TextDumper implements edu.pdx.cs410J.AirlineDumper {

    String fileName;

    public TextDumper(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void dump(AbstractAirline abstractAirline) throws IOException {
        try {
            if (fileName == null) {
                FileWriter fileWriter = new FileWriter(new File(fileName));
                Airline newAirline = (Airline) abstractAirline;
                fileWriter.write(newAirline.airlineDump()+"\n");
                fileWriter.close();
            } else {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
                Airline newAirline = (Airline) abstractAirline;
                printWriter.write(newAirline.airlineDump() + "\n");
                printWriter.close();
            }
        } catch (IOException io) {
            System.err.print("Error writing to a file " + fileName);
        }
    }
}

