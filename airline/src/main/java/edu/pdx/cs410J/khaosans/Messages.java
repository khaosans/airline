package edu.pdx.cs410J.khaosans;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages {
    /**
     *
     * @param count
     * @return
     */
    public static String getMappingCount(int count) {
        return String.format("Server contains %d Airlines", count);
    }

    /**
     *
     * @param key
     * @return
     */
    public static String missingRequiredParameter(String key) {
        return String.format("The required parameter \"%s\" is missing", key);
    }

}
