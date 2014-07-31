package edu.pdx.cs410J.khaosans;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages {
    /**
     * Used for formatting the number of airlines
     * @param count the number of airlines
     * @return String of the count of airlines
     */
    public static String getMappingCount(int count) {
        return String.format("Server contains %d Airlines", count);
    }

    /**
     * Used for identify bad parameters
     * @param key the key which is being looked for
     * @return the string that is missing
     */
    public static String missingRequiredParameter(String key) {
        return String.format("The required parameter \"%s\" is missing", key);
    }

}
