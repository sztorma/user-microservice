package com.sztorma.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoServiceTest {

    private static UserDaoService userDaoService;
    private static List<User> testUsers;

    static {
        testUsers = new ArrayList<>();
        testUsers.add(new User(1, "Adam", new Date()));
        testUsers.add(new User(2, "Eve", new Date()));
        testUsers.add(new User(3, "Jack", new Date()));
        userDaoService = new UserDaoService();
    }

    @Test
    @DisplayName("Find first element")
    public void testFindFirstOne() {
        User found = userDaoService.findOne(1);
        assertEquals(found, testUsers.get(0));
    }

    @Test
    @DisplayName("Find last element")
    public void testFindLastOne() {
        User found = userDaoService.findOne(3);
        assertEquals(found, testUsers.get(2));
    }

    @Test
    @DisplayName("Not found")
    public void testFindNone() {
        User user = userDaoService.findOne(4);
        assertEquals(null, user);
    }
}
