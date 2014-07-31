package edu.pdx.cs410J.khaosans;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AirlineServlet extends HttpServlet {
    private Map<String, Airline> data = new HashMap<String, Airline>();

    /**
     * Used for getting information from the servlet
     *
     * @param request  From the client
     * @param response gives to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter pw = response.getWriter();
        pw.println("Welcome to the Airline Server");
        pw.flush();

        String name = getParameter("name", request);
        String src = getParameter("src", request);
        String dest = getParameter("dest", request);

        if (name == null && src == null && dest == null) {
            writeAllMappings(response);
        } else if (name != null && src != null && dest != null) {
            writeAllMatchedMappings(response, name, src, dest);
        } else if (name != null && src == null && dest == null) {
            writeAllMatchedAirlineMappings(response, name);
        } else {
            pw.println("Error, this isn't valid URL");
            pw.flush();
        }

    }

    /**
     * Method used for posting on the servlet
     *
     * @param request  comes from the client
     * @param response gives to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        String name = getParameter("name", request);
        if (name == null) {
            missingRequiredParameter(response, "name");
            return;
        }

        String flightNumber = getParameter("flightNumber", request);
        if (flightNumber == null) {
            missingRequiredParameter(response, "flight number");
            return;
        }

        String src = getParameter("src", request);
        if (src == null) {
            missingRequiredParameter(response, "source airport");
            return;
        }

        String departTime = getParameter("departTime", request);

        String[] departArray = departTime.split(" ");
        if (departTime == null) {
            missingRequiredParameter(response, "departure time");
            return;
        }

        String dest = getParameter("dest", request);
        if (dest == null) {
            missingRequiredParameter(response, "destination airport");
            return;
        }

        String arrivalTime = getParameter("arrivalTime", request);
        String[] arrivalArray = arrivalTime.split(" ");
        if (arrivalTime == null) {
            missingRequiredParameter(response, "arrival time");
            return;
        }

        String[] args = {name, flightNumber, src, departArray[0], departArray[1], departArray[2], dest,
                arrivalArray[0], arrivalArray[1], arrivalArray[2]};
        Flight flight = Flight.getFlightFromArgs(args);


        if (this.data.containsKey(name)) {
            Airline airline1 = this.data.get(name);

            for (Object flight1 : this.data.get(name).getFlights()) {
                Flight flights2 = (Flight) flight1;
                if (flights2.getFlightNumber().equals(flight.getFlightNumber())) {
                    System.out.println("This Flight number exists");
                    return;
                }
            }
            airline1.addFlight(flight);
            this.data.replace(name, airline1);
            System.out.println("added flight to existing airline");
        }

        if (!this.data.containsKey(name)) {
            Airline airline = new Airline(name);
            airline.addFlight(flight);
            this.data.put(name, airline);
            System.out.println("Created a new flight");
        }


        PrintWriter pw = response.getWriter();
        pw.println("added");
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }


    /**
     * Method used to identify missing paramenters
     *
     * @param response gives to the client
     * @param key      to find this parameter
     * @throws IOException
     */
    private void missingRequiredParameter(HttpServletResponse response, String key)
            throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println(Messages.missingRequiredParameter(key));
        pw.flush();

        response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
    }

    /**
     * Finds all the matched airlines and flights
     *
     * @param response gives to the client
     * @param name     the name of the airline
     * @throws IOException
     */
    private void writeAllMatchedAirlineMappings(HttpServletResponse response, String name) throws IOException {
        int matches = 0;
        PrintWriter pw = response.getWriter();
        pw.println(Messages.getMappingCount(data.size()));

        for (Map.Entry<String, Airline> entry : this.data.entrySet()) {
            if (entry.getKey().equals(name)) {
                pw.println(entry.getKey());
                for (Object flight : entry.getValue().getFlights()) {
                    Flight flight1 = (Flight) flight;
                    ++matches;
                    pw.println("\t" + flight.toString() + " Duration(minutes) " + flight1.getDuration());
                }
            }
        }

        if (matches == 0) {
            pw.println("No airline matches exist for that search");
        }
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Write all the matching fights
     *
     * @param response gives to the client
     * @param name     the name of the airline
     * @param src      source of the airport
     * @param dest     destination of the airport
     * @throws IOException
     */
    private void writeAllMatchedMappings(HttpServletResponse response, String name, String src, String dest) throws IOException {
        int matches = 0;
        PrintWriter pw = response.getWriter();
        pw.println(Messages.getMappingCount(data.size()));

        for (Map.Entry<String, Airline> entry : this.data.entrySet()) {
            if (entry.getKey().equals(name)) {
                pw.println(entry.getKey());
                for (Object flight : entry.getValue().getFlights()) {
                    Flight flight1 = (Flight) flight;
                    if (flight1.getSource().equals(src.toUpperCase()) && flight1.getDestination().equals(dest.toUpperCase())) {
                        pw.println("\t" + flight.toString() + " Duration(minutes) " + flight1.getDuration());
                        ++matches;
                    }
                }
            }
        }
        if (matches == 0) {
            pw.println("There are direct flights between the specified airports");
        }

        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Method used to write the base url
     *
     * @param response gives to the client
     * @throws IOException
     */
    private void writeAllMappings(HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println(Messages.getMappingCount(data.size()));

        for (Map.Entry<String, Airline> entry : this.data.entrySet()) {
            pw.println(entry.getKey());
            for (Object flight : entry.getValue().getFlights()) {
                Flight flight1 = (Flight) flight;
                pw.println("\t" + flight.toString() + " Duration(minutes) " + flight1.getDuration());
            }
        }

        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Method used to get the parameter from the request
     *
     * @param name    name of the parameter
     * @param request comes from the client
     * @return
     */
    private String getParameter(String name, HttpServletRequest request) {
        String value = request.getParameter(name);
        if (value == null || "".equals(value)) {
            return null;

        } else {
            return value;
        }
    }
}
