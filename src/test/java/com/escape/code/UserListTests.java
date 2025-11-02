package com.escape.code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
/*
 * Testing for UserList class
 * @Author Erin Check
 */
public class UserListTests {
     private UserList userList;

    @Before
    public void setUp() {
        userList = UserList.getInstance();
        ArrayList<User> users = userList.getUsers();
        if (users != null) {
            users.clear();
        } else {
            fail("UserList.getUsers() returned null — DataLoader.getUsers() must return an ArrayList<User>.");
        }
    }

    @Test
    public void testGetInstanceReturnsSameObject() {
        UserList first = UserList.getInstance();
        UserList second = UserList.getInstance();
        assertSame(first, second);
    }


    @Test
    public void testAddUserValidUser() {
        boolean created = userList.addUser("alice", "password", null, null, null);
        assertTrue(created);

        User u = userList.getUser("alice", "password");
        assertNotNull(u);
        assertEquals("alice", u.getUsername());
        assertEquals("password", u.getPassword());
    }

    @Test
    public void testAddUserNullSettingsCreatesDefaultSettings() {
        boolean created = userList.addUser("bob", "pw123", null, null, null);
        assertTrue(created);

        User u = userList.getUser("bob", "pw123");
        assertNotNull(u);
        assertNotNull(u.getSettings());
    }


    @Test
    public void testAddUserRejectsNullUsername() {
        boolean created = userList.addUser(null, "password", null, null, null);
        assertFalse(created);
    }

    @Test
    public void testAddUserRejectsEmptyUsername() {
        boolean created = userList.addUser("", "password", null, null, null);
        assertFalse(created);
    }

    @Test
    public void testAddUserRejectsWhitespaceOnlyUsername() {
        boolean created = userList.addUser("   ", "password", null, null, null);
        assertFalse(created);
    }

    @Test
    public void testAddUserRejectsUsernameWithNewline() {
        boolean created = userList.addUser("bad\nname", "password", null, null, null);
        assertFalse(created);
    }

    @Test
    public void testAddUserRejectsNullPassword() {
        boolean created = userList.addUser("charlie", null, null, null, null);
        assertFalse(created);
    }

    @Test
    public void testAddUserRejectsEmptyPassword() {
        boolean created = userList.addUser("dave", "", null, null, null);
        assertFalse(created);
    }

    @Test
    public void testAddUserRejectsPasswordWithNewline() {
        boolean created = userList.addUser("ellen", "pw\nbad", null, null, null);
        assertFalse(created);
    }


    @Test
    public void testAddUserRejectsDuplicateUsername() {
        boolean first = userList.addUser("frank", "p1", null, null, null);
        assertTrue(first);

        boolean second = userList.addUser("frank", "different", null, null, null);
        assertFalse(second);

        int count = 0;
        for (User u : userList.getUsers()) {
            if ("frank".equals(u.getUsername())) count++;
        }
        assertEquals(1, count);
    }

    @Test
    public void testNewUserMethodReflectsExistingUsers() {
        assertTrue(userList.newUser("gina"));

        userList.addUser("gina", "pw", null, null, null);
        assertFalse(userList.newUser("gina"));
    }

    @Test
    public void testGetUserReturnsNullWhenNotFound() {
        userList.addUser("harry", "hpw", null, null, null);
        User u = userList.getUser("harry", "wrongpw");
        assertNull(u);

        User notExist = userList.getUser("noone", "nopw");
        assertNull(notExist);
    }

    @Test
    public void testGetUserWithNullArgumentsDoesNotThrow() {
        User result = userList.getUser(null, null);
        assertNull(result);
    }

    @Test
    public void testGetUsersReturnsLiveList() {
        ArrayList<User> list = userList.getUsers();
        assertNotNull(list);
        int before = list.size();

        userList.addUser("ivy", "pw", null, null, null);
        assertEquals(before + 1, list.size());
        boolean found = false;
        for (User u : list) {
            if ("ivy".equals(u.getUsername())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testSaveDoesNotThrow() {
        userList.addUser("jack", "pw", null, null, null);
        try {
            userList.save();
        } catch (Exception e) {
            fail("save() should not throw an exception: " + e.getMessage());
        }
    }
}
