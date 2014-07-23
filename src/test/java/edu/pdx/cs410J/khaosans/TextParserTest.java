package edu.pdx.cs410J.khaosans;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TextParserTest {

    /*@Test
    public void testGetAirlineFromFile() throws Exception {

    }

    @Test
    public void testIsSameAirline() throws Exception {
        TextParser textParser = new TextParser("khaosans/khaosans2.txt","same");
        assertTrue(textParser.isSameAirline());
    }

    @Test
    public void testIsNotSameAirline() throws Exception {
        TextParser textParser = new TextParser("khaosans/khaosans2.txt","different");
        assertFalse(textParser.isSameAirline());
    }

    @Test
    public void testAirline() throws Exception {
        TextParser textParser = new TextParser("khaosans/khaosans2.txt","same");
        Airline toValidate = textParser.getAirlineFromFile();

        Airline testAirline = new Airline("same");

        Flight flight1 = Flight.getFlightFromArgs(getStringFromCommandLine("same 800 MHT 01/08/2014 08:00 BTV 01/08/2014 18:00"));

        for (int i = 0; i < 6; i++) {
            testAirline.addFlight(flight1);
        }

        assertTrue(testAirline.equals(toValidate));

    }

    public String[] getStringFromCommandLine(String cmdline) {
        return cmdline.split(" ");
    }*/

}