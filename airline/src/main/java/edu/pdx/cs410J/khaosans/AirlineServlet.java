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
    private final Map<String, Airline> data = new HashMap<String, Airline>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter pw = response.getWriter();
        pw.println(this.data.size());
        pw.flush();

        String key = getParameter("name", request);
        if (key != null) {
            writeValue(key, response);

        } else {
            writeAllMappings(response);
        }
    }

    //String name, String flightNumber, String src,
    //String departTime, String dest, String arrivalTime
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        String name = getParameter("name", request);
        if (name == null) {
            missingRequiredParameter(response, name);
            return;
        }

        Airline airline = new Airline(name);

        String flightNumber = getParameter("flightNumber", request);
        if (flightNumber == null) {
            missingRequiredParameter(response, flightNumber);
            return;
        }

        String src = getParameter("src", request);
        if (src == null) {
            missingRequiredParameter(response, src);
            return;
        }

        String departTime = getParameter("departTime", request);

        String[] departArray = departTime.split(" ");
        if (departTime == null) {
            missingRequiredParameter(response, departTime);
            return;
        }

        String dest = getParameter("dest", request);
        if (dest == null) {
            missingRequiredParameter(response, dest);
            return;
        }

        String arrivalTime = getParameter("arrivalTime", request);
        String[] arrivalArray = arrivalTime.split(" ");
        if (arrivalTime == null) {
            missingRequiredParameter(response, arrivalTime);
            return;
        }

        String[] args = {name, flightNumber, src, departArray[0], departArray[1], departArray[2], dest,
                arrivalArray[0], arrivalArray[1], arrivalArray[2]};
        Flight flight = Flight.getFlightFromArgs(args);


        if (this.data.get(name) == null) {
            airline.addFlight(flight);
            this.data.put(name, airline);
        }
        if(this.data.get(name)!=null){
            Airline airline1 = new Airline(name);
            airline1.addFlight(flight);
            this.data.put(name,airline1);
        }

        PrintWriter pw = response.getWriter();
        pw.println("this shit got added");
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }


    private void missingRequiredParameter(HttpServletResponse response, String key)
            throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println(Messages.missingRequiredParameter(key));
        pw.flush();

        response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
    }

    private void writeValue(String key, HttpServletResponse response) throws IOException {
        Airline airline = this.data.get(key);

        PrintWriter pw = response.getWriter();
        pw.println(Messages.getMappingCount(airline != null ? 1 : 0));
        pw.println(Messages.formatKeyValuePair(key, airline.toString()));

        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void writeAllMappings(HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println(Messages.getMappingCount(data.size()));

        for (Map.Entry<String, Airline> entry : this.data.entrySet()) {
            pw.println(Messages.formatKeyValuePair(entry.getKey(), entry.getValue().getFlights().toString()));
        }

        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    private String getParameter(String name, HttpServletRequest request) {
        String value = request.getParameter(name);
        if (value == null || "".equals(value)) {
            return null;

        } else {
            return value;
        }
    }

    public String[] getStringFromCommandLine(String cmdline) {
        return cmdline.split(" ");
    }

}
