package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.AbstractAirline;

import java.io.*;

/**
 * Created by sk on 7/19/14.
 */
public class PrettyPrinter implements edu.pdx.cs410J.AirlineDumper {

    String fileName;

    public PrettyPrinter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Method dump is used write to file from the command line or add to file.
     *
     * @param abstractAirline the airline object that is used to dump the data from
     */
    @Override
    public void dump(AbstractAirline abstractAirline) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                createDirectory();
                FileWriter fileWriter = new FileWriter(new File(fileName));
                Airline newAirline = (Airline) abstractAirline;
                fileWriter.write(newAirline.prettyDump());
                fileWriter.close();
            } else {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
                Airline newAirline = (Airline) abstractAirline;
                printWriter.write(newAirline.prettyDump());
                printWriter.close();
            }
        } catch (IOException io) {
            System.err.print("Error writing to a file " + fileName);
        }
    }

    public void createDirectory(){
        String [] split = fileName.split("/");
        String filePath = "";
        for(int i = 0; i<split.length-1;++i){
            filePath+=split[i]+"/";
        }
        boolean buildDirectorySuccessful = (new File(filePath)).mkdirs();
    }


}
