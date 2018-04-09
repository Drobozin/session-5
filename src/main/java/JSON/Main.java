package JSON;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static int tabCounter = 0;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    public static void main(String[] args) throws Exception{
        Account acc = new Account();
        createJSON(acc);
        System.out.println(sb.toString());
    }

    public static void createJSON(Object ob) throws Exception{
        sb.append("{\n");
        fillJSON(ob);
        sb.append("\n}");
    }


    public static void fillJSON(Object ob) throws Exception{
        Field[] fields = ob.getClass().getDeclaredFields();

        tabCounter++;
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.getType().isPrimitive()) {
                sb.append(String.format("%s\"%s\": ", getTabs(tabCounter),f.getName())).append(getFormattetValue(f, ob)).append(",\n");
            }else if(f.getType().isArray()){
                Object[] arr = (Object[])f.get(ob);
                sb.append(String.format("%s\"%s\": [\n", getTabs(tabCounter), f.getName()));
                for(int i=0; i<arr.length; i++){
                    sb.append(getTabs(tabCounter+1)).append(getFormattetValue(arr[i])).append(i == arr.length-1?"\n":",\n");
                }
                sb.append(getTabs(tabCounter)).append("]\n");
            }else if(f.getType().equals(List.class)){
                List list = (List)f.get(ob);
                sb.append(String.format("%s\"%s\": [\n", getTabs(tabCounter), f.getName()));
                for(int i=0; i<list.size(); i++){
                    sb.append(getTabs(tabCounter+1)).append(getFormattetValue(list.get(i))).append(i == list.size()-1?"\n":",\n");
                }
                sb.append(getTabs(tabCounter)).append("]\n");
            }else if(f.getType().equals(Set.class)){
                Set set = (Set)f.get(ob);
                sb.append(String.format("%s\"%s\": [\n", getTabs(tabCounter), f.getName()));
                Iterator iter = set.iterator();
                while (iter.hasNext()){
                    sb.append(getTabs(tabCounter+1)).append(getFormattetValue(iter.next())).append(iter.hasNext()?",\n":"\n");
                }
                sb.append(getTabs(tabCounter)).append("]\n");
            }
            else if(f.getType().equals(String.class)){
                sb.append(String.format("%s\"%s\": ", getTabs(tabCounter), f.getName())).append(getFormattetValue(f, ob)).append(",\n");
            }
            else if(f.getType().equals(Date.class) || f.getType().equals(Calendar.class)){
                sb.append(String.format("%s\"%s\": ", getTabs(tabCounter), f.getName())).append(getFormattetValue(f, ob)).append(",\n");
            }
            else {
                sb.append(String.format("%s\"%s\": {\n", getTabs(tabCounter), f.getName()));
                fillJSON(f.get(ob));
                tabCounter--;
                sb.append(getTabs(tabCounter)).append("}\n");
            }
        }
    }

    public static String getTabs(int count){
        StringBuilder sb = new StringBuilder();
        while(count-- >0){
            sb.append("\t");
        }
        return sb.toString();
    }

    public static String getFormattetValue(Field f, Object ob) throws Exception{
        f.setAccessible(true);
        if (f.getType().equals(int.class) || f.getType().equals(double.class)) {
            return f.get(ob).toString();
        }else if(f.getType().equals(Date.class)){
            return sdf.format((Date)f.get(ob));
        }
        else if(f.getType().equals(Calendar.class)){
            return sdf.format(((Calendar)f.get(ob)).getTime());
        }
        else {
            return "\""+f.get(ob).toString()+"\"";
        }
    }

    public static String getFormattetValue(Object ob) throws Exception{
        if (ob instanceof Number) {
            return ob.toString();
        }else {
            return "\""+ob.toString()+"\"";
        }
    }
}