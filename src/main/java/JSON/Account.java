package JSON;

import java.lang.reflect.Field;
import java.util.*;

public class Account {
    private int id;
    private User user;

    private double balance;
    private String[] arr;
    private List list;
    private Set set;
    private Date myDate;
    private Calendar myCalendar;


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

        myDate = new Date();
        myCalendar = Calendar.getInstance();
        myCalendar.setTime(myDate);



        user = new User("Ivanov Ivan Ivanonich", 25);
    }
}
