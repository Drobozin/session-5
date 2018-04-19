package JSON;


import java.util.Map;

@FunctionalInterface
public interface JSONTypeFormatter<T> {
    String format(T t, JSONFormatter formatter, Map<String, Object> ctx);

}