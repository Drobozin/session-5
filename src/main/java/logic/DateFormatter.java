package logic;

import JSON.JSONFormatter;
import JSON.JSONTypeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DateFormatter implements JSONTypeFormatter<Date> {
    @Override
    public String format(Date date, JSONFormatter formatter, Map<String, Object> ctx) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }
}
