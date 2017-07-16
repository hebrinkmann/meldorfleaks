package de.henningbrinkmann.meldorf;

import org.joda.time.DateTime;

import java.util.Random;
import java.util.stream.IntStream;

@SuppressWarnings("ALL")
public class UserBuilder {
    private static String[] firstNames = {"Axel", "Heinz", "Henner", "Klaus", "Tim", "Tina", "Julius", "Maylin", "Maurice", "Amadeo", "Amon", "Angela", "Ilka", "Juliane", "Erika"};
    private static String[] lastNames = {"Meyer", "Schröder", "Schmidt", "Fontane", "Papandreu", "Zimmermann", "Schneider", "Rodrigues", "Kinski", "Hartmann", "Fernandez", "Ronaldo"};
    private static String[] streets = {"Hauptstraße", "Waldweg", "Eichenweg", "Langer Zug", "Rondel", "Fritz-Schumacher-Allee", "Wiesenpfad", "Geert Sin Pad", "Kirchplatz", "Müllergasse"};
    private static String[] cities = {"Hamburg", "Berlin", "München", "Geertsdörp", "Düsseldorf", "Obersdorf", "Flensburg", "Wilhelmshaven", "Frakfurt"};
    private static char[] passwordChars = "0123456789".toCharArray();
    private static Random random = new Random(7);

    private String firstName;
    private String lastName;
    private Integer uid;
    private String address;
    private String city;
    private String postcode;
    private String password;
    private String keycode;
    private DateTime birthday;

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public UserBuilder setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setKeycode(String keycode) {
        this.keycode = keycode;
        return this;
    }

    public UserBuilder setBirthday(DateTime birthday) {
        this.birthday = birthday;

        return this;
    }

    public UserBuilder shuffle() {
        setFirstName(firstNames[random.nextInt(firstNames.length)]);
        setLastName(lastNames[random.nextInt(lastNames.length)]);
        setUid(random.nextInt(1000));
        setAddress(streets[random.nextInt(streets.length)] + " " + random.nextInt(100));
        setCity(cities[random.nextInt(cities.length)]);
        setPostcode(String.format("%05d", random.nextInt(100000)));
        setPassword(randomString(5));
        setKeycode(String.format("%04d", random.nextInt(10000)));
        setBirthday(DateTime.parse("2017-05-31").minusDays(random.nextInt(100 * 365)));

        return this;
    }

    public User createUser() {
        String pwdHash = hashString(password);
        return new User(firstName, lastName, uid, address, city, postcode, password, pwdHash, keycode, birthday);
    }

    private String hashString(String password) {
        char[] chars = password.toCharArray();
        final int[] quersumme = {0};

        IntStream.range(0, chars.length).forEach(i -> {
            int digit = chars[i] - '0';
            quersumme[0] += digit;
        });

        return String.format("%04d%05d", uid, quersumme[0]);
    }

    private String randomString(int length) {
        StringBuilder sb = new StringBuilder();

        IntStream.range(0, length).forEach(i -> {
            sb.append(passwordChars[random.nextInt(passwordChars.length)]);
        });

        return sb.toString();
    }
}