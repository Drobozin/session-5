package problem2.logic;

import problem2.JSON.JSONFormatter;
import problem2.JSON.JSONTypeFormatter;

import java.util.Map;
import java.util.Set;

public class SetFormatter implements JSONTypeFormatter<Set> {
    @Override
    public String format(Set set, JSONFormatter formatter, Map<String, Object> ctx) {
        StringBuilder sb = new StringBuilder();

        sb.append("[\n");
        ctx.replace("indentCount", Integer.valueOf(ctx.get("indentCount").toString())+1);
        int count = 0;
        for(Object val : set){
            sb.append(formatter.getIndents(ctx.get("indentCount")))
                    .append(formatter.marshall(val))
                    .append((++count != set.size())? ",\n" : "\n");
        }
        ctx.replace("indentCount", Integer.valueOf(ctx.get("indentCount").toString())-1);
        sb.append(formatter.getIndents(ctx.get("indentCount"))).append("]");
        return sb.toString();
    }
}
