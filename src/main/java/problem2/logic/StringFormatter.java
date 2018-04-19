package problem2.logic;

import problem2.JSON.JSONFormatter;
import problem2.JSON.JSONTypeFormatter;

import java.util.Map;

public class StringFormatter implements JSONTypeFormatter<Object> {
    @Override
    public String format(Object o, JSONFormatter formatter, Map<String, Object> ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\"%s\"", o.toString()));
        return sb.toString();
    }
}
