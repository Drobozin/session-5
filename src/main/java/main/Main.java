package main;

import JSON.JSONFormatterImpl;
import JSON.JSONTypeFormatter;
import data.Account;
import logic.PrimitiveFormatter;
import java.math.BigDecimal;

public class Main {

    public static void main(String [] args) {
        JSONFormatterImpl json = new JSONFormatterImpl();
        JSONTypeFormatter<Object> ob = new PrimitiveFormatter();
        json.addType(Byte.class, new PrimitiveFormatter());
        Account acc = new Account();
        System.out.println(json.marshall(acc));
    }
}

