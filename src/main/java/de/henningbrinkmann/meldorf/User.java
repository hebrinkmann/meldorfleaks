package de.henningbrinkmann.meldorf;

import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by henning on 16.07.17.
 */
public class User {
    private final String firstName;
    private final String lastName;
    private final Integer uid;
    private final String address;
    private final String city;
    private final String postcode;
    private final String password;
    private final String pwdHash;
    private final String keycode;
    private final DateTime birthDay;

    public User(String firstName, String lastName, Integer uid, String address, String city, String postcode, String password, String pwdHash, String keycode, DateTime birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uid = uid;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.password = password;
        this.pwdHash = pwdHash;
        this.keycode = keycode;
        this.birthDay = birthDay;
    }

    public String toTSV() {
        final StringBuilder sb = new StringBuilder();
        sb
                .append("\"")
                .append(firstName)
                .append("\"");
        sb.append("\t")
                .append("\"")
                .append(lastName)
                .append("\"");
        sb.append("\t")
                .append("\"")
                .append(uid)
                .append("\"");
        sb.append("\t")
                .append("\"")
                .append(address)
                .append("\"");
        sb.append("\t")
                .append("\"")
                .append(city)
                .append("\"");
        sb.append("\t")
                .append("\"")
                .append(postcode)
                .append("\"");
        sb.append("\t")
                .append("\"")
                .append(password)
                .append("\"");
        sb.append("\t")
                .append("\"")
                .append(pwdHash)
                .append("\"");
        sb.append("\t")
                .append("\"")
                .append(keycode)
                .append("\"");
        sb.append("\t")
                .append("\"")
                .append(birthDay)
                .append("\"");
        return sb.toString();
    }

    public static String toHeaders() {
        return Arrays.stream(User.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.joining("\t"));
    }
}
