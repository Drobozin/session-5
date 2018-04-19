package problem2.logic;

import problem2.JSON.JSONFormatter;
import problem2.JSON.JSONTypeFormatter;

import java.lang.reflect.Field;
import java.util.Map;

public class ObjectFormatter implements JSONTypeFormatter<Object> {

    @Override
    public String format(Object o, JSONFormatter formatter, Map<String, Object> ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        ctx.replace("indentCount", Integer.valueOf(ctx.get("indentCount").toString())+1);
        Field[] fields = o.getClass().getDeclaredFields();
        int count = 0;
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                sb.append(formatter.getIndents(ctx.get("indentCount")))
                        .append("\"")
                        .append(f.getName())
                        .append("\": ")
                        .append(formatter.marshall(f.get(o)))
                        .append((++count != fields.length)? ",\n" : "\n");
            }catch(Exception e){}
        }
        ctx.replace("indentCount", Integer.valueOf(ctx.get("indentCount").toString())-1);
        sb.append(formatter.getIndents(ctx.get("indentCount"))).append("}");
        return sb.toString();
    }
}
