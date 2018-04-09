package JSON;


public class User {
    private String fio;
    private int age;
    User(String fio, int age){
        this.fio = fio;
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public String getFio() {
        return fio;
    }
}
