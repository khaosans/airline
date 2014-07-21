package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.InvokeMainTestCase;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by sk on 7/16/14.
 */
public class Project2Test extends InvokeMainTestCase {

    private MainMethodResult invokeMain(String... args) {
        return invokeMain(Project2.class, args);
    }

    @Test
    public void testNoCommandLineArguments1() {
        MainMethodResult result = invokeMain();
        Assert.assertEquals(new Integer(1), result.getExitCode());
        assertTrue(result.getErr().contains("Missing command line arguments"));
    }

    @Test
    public void test2Readme() {
        MainMethodResult result = invokeMain("-README");
        assertEquals(result.getOut(), "Souriya Khaosanga CS410J Project 2\n" +
                "The purpose of this program is to be able to add airlines \n" +
                "and flights to an existing database.  This tool also allows you to write and read\n" +
                "from existing files.  This will vary depending on your input.  If a file exist,\n" +
                "it'll read it and validate it's the same airline and continue to write, otherwise it\n" +
                "should not write\n" +
                "usage: java edu.pdx.cs410J.<login-id>.Project1 [options] <args>\n" +
                "args are (in this order):\n" +
                "name The name of the airline\n" +
                "flightNumber The flight number\n" +
                "src Three-letter code of departure airport\n" +
                "departTime Departure date and time (24-hour time)\n" +
                "dest Three-letter code of arrival airport\n" +
                "arriveTime Arrival date and time (24-hour time)\n" +
                "options are (options may appear in any order):\n" +
                "-textFile file Where to read/write the airline info\n" +
                "-print Prints a description of the new flight\n" +
                "-README Prints a README for this project and exits\n" +
                "Date and time should be in the format: mm/dd/yyyy hh:mm\n\n");

    }

    @Test
    public void test3FlightTimeMalformed() {
       
    }
}
