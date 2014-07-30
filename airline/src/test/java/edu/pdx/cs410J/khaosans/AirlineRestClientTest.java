package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.Test;

import static org.junit.Assert.*;

public class AirlineRestClientTest {

    @Test
    public void testAddFlight() throws Exception {
        AirlineRestClient airlineRestClient = new AirlineRestClient("localhost", 8080);

        System.out.println(airlineRestClient.addFlight("flightame", "flighNumber", "src", "departtime", "Destin", "arrivalTime"));


    }

}