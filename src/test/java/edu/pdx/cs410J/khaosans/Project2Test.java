package edu.pdx.cs410J.khaosans;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

/**
 * Created by sk on 7/16/14.
 */
public class Project2Test extends InvokeMainTestCase {

    private MainMethodResult invokeMain(String... args) {
        return invokeMain(Project2.class, args);
    }

    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain("Test5 123 PDX 03/03/2014 12:00 ORD 01/04/20/1 16:00");

    }
}
