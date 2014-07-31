package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;

/**
 * This class is how to interact with the servlet
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
     * Returns all values for the given airline name
     */
    public Response getAirline(String name) throws IOException {
        return get(this.url, "name", name);
    }

    /**
     * Used to serch the servlet for flights for given parameters
     * @param name of the airline
     * @param src airport coming from
     * @param dest airport going to
     * @return Response  object
     * @throws IOException when the url is no good
     */
    public Response getAirlineSrcDest(String name, String src, String dest) throws IOException {
        return get(this.url, "name", name, "src", src, "dest", dest);
    }

    /**
     *
     * @param name of the airline to add
     * @param flight object that will be sent over the wire
     * @return Response object
     * @throws IOException
     */
    public Response addFlight(String name, Flight flight) throws IOException {

        return post(this.url, "name", name, "flightNumber", flight.getFlightNumber(),
                "src", flight.getSource(), "departTime", flight.getDepartureString(),
                "dest", flight.getDestination(), "arrivalTime", flight.getArrivalString());
    }
}
