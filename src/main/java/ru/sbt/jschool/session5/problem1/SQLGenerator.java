package ru.sbt.jschool.session5.problem1;


import java.lang.reflect.Field;

/**
 */
public class SQLGenerator {
    public <T> String insert(Class<T> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(clazz.getAnnotation(Table.class).name());
        Field[] fields = clazz.getDeclaredFields();
        int count = 0;
        for(Field f : fields){
            if(f.isAnnotationPresent(Column.class)) {
                if(f.getAnnotation(Column.class).name().length() > 0){
                    sb.append(count++ == 0 ? "(" : ", ").append(f.getAnnotation(Column.class).name().toLowerCase());
                }
                else{
                    sb.append(count++ == 0 ? "(" : ", ").append(f.getName().toLowerCase());
                }
            }
            else if(f.isAnnotationPresent(PrimaryKey.class)) {
                if(f.getAnnotation(PrimaryKey.class).name().length() > 0){
                    sb.append(count++ == 0 ? "(" : ", ").append(f.getAnnotation(PrimaryKey.class).name().toLowerCase());
                }
                else{
                    sb.append(count++ == 0 ? "(" : ", ").append(f.getName().toLowerCase());
                }
            }
        }
        sb.append(") VALUES (");
        while (count != 0){
            if (count == 1){
                sb.append("?)");
                break;
            }
            sb.append("?, ");
            count--;
        }
        return sb.toString();
    }

    public <T> String update(Class<T> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(clazz.getAnnotation(Table.class).name()).append(" SET");
        Field[] fields = clazz.getDeclaredFields();
        int count = 0;
        for(Field f : fields){
            if(f.isAnnotationPresent(Column.class)){
                if(f.getAnnotation(Column.class).name().length() > 0){
                    sb.append(count++ == 0 ? " " : ", ").append(f.getAnnotation(Column.class).name().toLowerCase()).append(" = ?");
                }else {
                    sb.append(count++ == 0 ? " " : ", ").append(f.getName().toLowerCase()).append(" = ?");
                }
            }
        }
        sb.append(" WHERE");
        count = 0;
        for(Field f : fields){
            if(f.isAnnotationPresent(PrimaryKey.class)){
                sb.append(count++==0?" ":" AND ").append(f.getName().toLowerCase()).append(" = ?");
            }
        }
        return sb.toString();
    }

    public <T> String delete(Class<T> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE");
        Field[] fields = clazz.getDeclaredFields();
        sb.append(" FROM ").append(clazz.getAnnotation(Table.class).name());
        sb.append(" WHERE");
        int count = 0;
        for(Field f : fields){
            if(f.isAnnotationPresent(PrimaryKey.class)){
                sb.append(count++==0?" ":" AND ").append(f.getName().toLowerCase()).append(" = ?");
            }
        }
        return sb.toString();
    }

    public <T> String select(Class<T> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        Field[] fields = clazz.getDeclaredFields();
        int count = 0;
        for(Field f : fields){
            if(f.isAnnotationPresent(Column.class)){
                if(f.getAnnotation(Column.class).name().length() > 0){
                    sb.append(count++ == 0 ? " " : ", ").append(f.getAnnotation(Column.class).name().toLowerCase());
                }else {
                    sb.append(count++ == 0 ? " " : ", ").append(f.getName().toLowerCase());
                }
            }
        }
        sb.append(" FROM ").append(clazz.getAnnotation(Table.class).name());
        sb.append(" WHERE");
        count = 0;
        for(Field f : fields){
            if(f.isAnnotationPresent(PrimaryKey.class)){
                sb.append(count++==0?" ":" AND ").append(f.getName().toLowerCase()).append(" = ?");
            }
        }
        return sb.toString();
    }
}
