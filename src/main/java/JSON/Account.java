package JSON;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Account {
    private int id;
    private double balance;
    private String[] arr;
    private List list;
    private Set set;
    private User user;

    public Account() {
        id = 1;
        balance = 100;
        arr = new String[]{"100", "200", "300"};
        list = new ArrayList();
        list.add(1);
        list.add("two");

        set = new HashSet();
        set.add(3);
        set.add("four");

        user = new User("Ivanov Ivan Ivanonich", 25);
    }
}
