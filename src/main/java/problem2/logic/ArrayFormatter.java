package problem2.logic;

import problem2.JSON.JSONFormatter;
import problem2.JSON.JSONTypeFormatter;

import java.util.Map;

public class ArrayFormatter implements JSONTypeFormatter<Object> {
    @Override
    public String format(Object ob, JSONFormatter formatter, Map<String, Object> ctx) {

        StringBuilder sb = new StringBuilder();

        sb.append("[\n");
        ctx.replace("indentCount", Integer.valueOf(ctx.get("indentCount").toString())+1);
        int count = 0;
        for(Object val : (Object[])ob){
            sb.append(formatter.getIndents(ctx.get("indentCount")))
                    .append(formatter.marshall(val))
                    .append((++count != ((Object[])ob).length)? ",\n" : "\n");
        }
        ctx.replace("indentCount", Integer.valueOf(ctx.get("indentCount").toString())-1);
        sb.append(formatter.getIndents(ctx.get("indentCount"))).append("]");
        return sb.toString();
    }
}
