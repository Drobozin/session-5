package logic;

import JSON.JSONFormatter;
import JSON.JSONTypeFormatter;

import java.util.List;
import java.util.Map;


public class ListFormatter implements JSONTypeFormatter<List> {
    @Override
    public String format(List list, JSONFormatter formatter, Map<String, Object> ctx) {
        StringBuilder sb = new StringBuilder();

        sb.append("[\n");
        ctx.replace("indentCount", Integer.valueOf(ctx.get("indentCount").toString())+1);
        int count = 0;
        for(Object val : list){
            sb.append(formatter.getIndents(ctx.get("indentCount")))
                    .append(formatter.marshall(val))
                    .append((++count != list.size())? ",\n" : "\n");
        }
        ctx.replace("indentCount", Integer.valueOf(ctx.get("indentCount").toString())-1);
        sb.append(formatter.getIndents(ctx.get("indentCount"))).append("]");
        return sb.toString();
    }
}
