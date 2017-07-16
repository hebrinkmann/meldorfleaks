package de.henningbrinkmann.meldorf;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by henning on 16.07.17.
 */
public class UserTest {

    @Test
    public void testUserList() {
        List<User> users = IntStream.range(0, 20)
                .mapToObj(i -> new UserBuilder().shuffle().createUser())
                .collect(Collectors.toList());

        String collect = users.stream().map(User::toTSV).collect(Collectors.joining("\n"));

        System.out.println(User.toHeaders());
        System.out.println(collect);

    }
}