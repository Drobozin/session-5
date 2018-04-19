package problem2.main;

import problem2.JSON.JSONFormatterImpl;
import problem2.JSON.JSONTypeFormatter;
import problem2.data.Account;
import problem2.logic.PrimitiveFormatter;

public class Main {

    public static void main(String [] args) {
        JSONFormatterImpl json = new JSONFormatterImpl();
        JSONTypeFormatter<Object> ob = new PrimitiveFormatter();
        json.addType(Byte.class, new PrimitiveFormatter());
        Account acc = new Account();
        System.out.println(json.marshall(acc));
    }
}

