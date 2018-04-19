package problem2.JSON;

public interface JSONFormatter {
    /**
     * Marshall object to problem2.JSON string.
     *
     * @param obj Any object.
     * @return String in problem2.JSON format.
     */
    String marshall(Object obj);

    /**
     * Add predefined type to formatter.
     *
     * @param clazz Class to add.
     * @param format Formatter for specified class.
     * @param <T> Formatted type.
     * @return True if class been previously known.
     */
    <T> boolean addType(Class<T> clazz, JSONTypeFormatter<T> format);

    String getIndents(Object count);

}
