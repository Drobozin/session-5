package JSON;

import logic.*;

import java.lang.reflect.Array;
import java.util.*;

public class JSONFormatterImpl implements JSONFormatter {
    private Map<Class, JSONTypeFormatter> types = new HashMap<>();
    Map<String, Object> ctx = new HashMap<>();
    public JSONFormatterImpl(){
        types.put(Integer.class, new PrimitiveFormatter());
        types.put(Double.class, new PrimitiveFormatter());
        types.put(Boolean.class, new PrimitiveFormatter());
        types.put(char.class, new PrimitiveFormatter());
        types.put(Set.class, new SetFormatter());
        types.put(List.class, new ListFormatter());
        types.put(String.class, new StringFormatter());
        types.put(Date.class, new DateFormatter());
        types.put(Calendar.class, new CalendarFormatter());
        types.put(Object.class, new ObjectFormatter());
        types.put(Array.class, new ArrayFormatter());
        ctx.put("indentCount", 0);
    }

    @Override
    public String marshall(Object obj) {
        if ( obj == null ) return "";

        if ( types.containsKey(obj.getClass()) ) {
            return types.get(obj.getClass()).format(obj, this, ctx);
        }
        if(obj.getClass().isArray()){
            return types.get(Array.class).format(obj, this, ctx);
        }
        else if(obj instanceof Calendar){
            return types.get(Calendar.class).format(obj, this, ctx);
        }
        else if(obj instanceof Character){
            return types.get(char.class).format(obj, this, ctx);
        }
        else if(obj instanceof List){
               return types.get(List.class).format(obj, this, ctx);
        }else if(obj instanceof Set){
            return types.get(Set.class).format(obj, this, ctx);
        }
        return types.get(Object.class).format(obj, this, ctx);
    }

    @Override
    public <T> boolean addType(Class<T> clazz, JSONTypeFormatter<T> format) {
        if (types.containsKey(clazz)) {
            return true;
        } else {
            types.put(clazz, format);
            return false;
        }
    }

    public String getIndents(Object obj){
        int count = Integer.valueOf(obj.toString());
        StringBuilder sb = new StringBuilder();
        while(count-- >0){
            sb.append("\t");
        }
        return sb.toString();
    }
}