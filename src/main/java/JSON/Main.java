package JSON;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception{
        Account acc = new Account();
        createJSON(acc);

    }

    public static void createJSON(Object ob) throws Exception{
        Field[] fields = ob.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.getType().isPrimitive()) {
                sb.append(String.format("\t\"%s\": ", f.getName())).append(getFormattetValue(f, ob)).append(",\n");
            }else if(f.getType().isArray()){
                Object[] arr = (Object[])f.get(ob);
                sb.append(String.format("\t\"%s\": [\n", f.getName()));
                for(int i=0; i<arr.length; i++){
                    sb.append("\t\t").append(getFormattetValue(arr[i])).append(i == arr.length-1?"\n":",\n");
                }
                sb.append("\t]\n");
            }else if(f.getType().equals(List.class)){
                List list = (List)f.get(ob);
                sb.append(String.format("\t\"%s\": [\n", f.getName()));
                for(int i=0; i<list.size(); i++){
                    sb.append("\t\t").append(getFormattetValue(list.get(i))).append(i == list.size()-1?"\n":",\n");
                }
                sb.append("\t]\n");
            }else if(f.getType().equals(Set.class)){
                Set set = (Set)f.get(ob);
                sb.append(String.format("\t\"%s\": [\n", f.getName()));
                Iterator iter = set.iterator();
                while (iter.hasNext()){
                    sb.append("\t\t").append(getFormattetValue(iter.next())).append(iter.hasNext()?",\n":"\n");
                }
                sb.append("\t]\n");
            }else{
                User user = (User)f.get(ob);


            }
        }
        sb.append("\n}");
        System.out.println(sb.toString());
    }
    public static String getFormattetValue(Field f, Object ob) throws Exception{

        if (f.getType().equals(int.class) || f.getType().equals(double.class)) {
            return f.get(ob).toString();
        }else {
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

    public static String getTab(int count){
        StringBuilder sb = new StringBuilder();
        while(count>0){
            sb.append("\t");
        }
        return sb.toString();
    }
}


