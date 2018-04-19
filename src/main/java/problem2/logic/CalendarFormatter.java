package problem2.logic;

import problem2.JSON.JSONFormatter;
import problem2.JSON.JSONTypeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class CalendarFormatter implements JSONTypeFormatter<Calendar> {
    @Override
    public String format(Calendar cldr, JSONFormatter formatter, Map<String, Object> ctx) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(cldr.getTime());
    }
}
