package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.Test;

public class AirlineRestClientTest {

    @Test
    public void testAddFlight() throws Exception {
        AirlineRestClient airlineRestClient = new AirlineRestClient("localhost", 8080);

        String[] args = getStringFromCommandLine("Project3 700 PDX 01/07/2014 07:00 AM MHT 01/17/2014 7:00 PM");
        Flight flight = Flight.getFlightFromArgs(args);

        HttpRequestHelper.Response response = airlineRestClient.addFlight(args[0], flight);

        response.getContent();
    }

    @Test
    public void testAddFlight2() throws Exception {
        AirlineRestClient airlineRestClient = new AirlineRestClient("localhost", 8080);

        String[] args = getStringFromCommandLine("Project3 701 PDX 01/07/2014 07:00 AM MHT 01/17/2014 7:00 PM");
        Flight flight = Flight.getFlightFromArgs(args);

        HttpRequestHelper.Response response = airlineRestClient.addFlight(args[0], flight);

        response.getContent();
    }

    @Test
    public void test3AddFlight() throws Exception {
        AirlineRestClient airlineRestClient = new AirlineRestClient("localhost", 8080);

        String[] args = getStringFromCommandLine("Project3 715 PDX 01/07/2014 07:00 AM MHT 01/17/2014 7:00 PM");
        Flight flight = Flight.getFlightFromArgs(args);

        HttpRequestHelper.Response response = airlineRestClient.addFlight(args[0], flight);

        response.getContent();
    }

    @Test
    public void test4AddFlight() throws Exception {
        AirlineRestClient airlineRestClient = new AirlineRestClient("localhost", 8080);

        String[] args = getStringFromCommandLine("New 812 PDX 01/07/2014 07:00 AM MHT 01/17/2014 7:00 PM");
        Flight flight = Flight.getFlightFromArgs(args);

        HttpRequestHelper.Response response = airlineRestClient.addFlight(args[0], flight);

        response.getContent();
    }

    @Test
    public void test5AddFlight() throws Exception {
        AirlineRestClient airlineRestClient = new AirlineRestClient("localhost", 8080);

        String[] args = getStringFromCommandLine("New 820 PDX 01/07/2014 07:00 AM MHT 01/17/2014 7:00 PM");
        Flight flight = Flight.getFlightFromArgs(args);

        HttpRequestHelper.Response response = airlineRestClient.addFlight(args[0], flight);

        response.getContent();
    }

    @Test
    public void test6AddFlight() throws Exception {
        AirlineRestClient airlineRestClient = new AirlineRestClient("localhost", 8080);

        String[] args = getStringFromCommandLine("New 100 PDX 01/07/2014 07:00 AM MHT 01/17/2014 7:00 PM");
        Flight flight = Flight.getFlightFromArgs(args);

        HttpRequestHelper.Response response = airlineRestClient.addFlight(args[0], flight);

        response.getContent();
    }

    @Test
    public void test7AddFlight() throws Exception {
        AirlineRestClient airlineRestClient = new AirlineRestClient("localhost", 8080);

        String[] args = getStringFromCommandLine("New2A 100 PDX 01/07/2014 07:00 AM MHT 01/17/2014 7:00 PM");
        Flight flight = Flight.getFlightFromArgs(args);

        HttpRequestHelper.Response response = airlineRestClient.addFlight(args[0], flight);

        response.getContent();
    }

    public String[] getStringFromCommandLine(String cmdline) {
        return cmdline.split(" ");
    }
}