package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday.getTime()
                + '}';
    }

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();

        User userOne = new User("", 1, cal);
        printUserInfo(userOne);
        User userTwo = new User("", 1, cal);
        printUserInfo(userTwo);

        Map<User, Object> map = new HashMap<>();
        map.put(userOne, new Object());
        map.put(userTwo, new Object());
        map.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });
    }

    private static void printUserInfo(User user) {
        int hashCode = user.hashCode();
        int hash = hashCode ^ (hashCode >>> 16);
        int bucket = hash & 15;
        System.out.printf("User - %s\n hashCode - %s\n hash - %s\n bucket - %s\n", user, hashCode, hash, bucket);
    }
}
