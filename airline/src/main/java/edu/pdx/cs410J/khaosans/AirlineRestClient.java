package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send key/value pairs.
 */
public class AirlineRestClient extends HttpRequestHelper {
    private static final String WEB_APP = "airline";
    private static final String SERVLET = "flights";

    private final String url;


    /**
     * Creates a client to the airline REST service running on the given host and port
     *
     * @param hostName The name of the host
     * @param port     The port
     */
    public AirlineRestClient(String hostName, int port) {
        this.url = String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET);
    }

    /**
     * Returns all keys and values from the server
     */
    public Response getAllKeysAndValues() throws IOException {
        return get(this.url);
    }

    /**
     * Returns all values for the given key
     */
    public Response getAirline(String name) throws IOException {
        return get(this.url, "name", name);
    }

    public Response getAirlineSrcDest(String name, String src, String dest) throws IOException {
        return post(this.url, "name", name, "src", src, "dest", dest);
    }


    public Response addFlight(String name, Flight flight) throws IOException {

        return post(this.url, "name", name, "flightNumber", flight.getFlightNumber(),
                "src", flight.getSource(), "departTime", flight.getDepartureString(),
                "dest", flight.getDestination(), "arrivalTime", flight.getArrivalString());
    }


}
