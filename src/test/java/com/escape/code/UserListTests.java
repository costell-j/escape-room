package com.escape.code;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
public class UserListTests {
     private UserList userList;

    @Before
    public void setUp() {
        // Ensure singleton exists and is cleared to give each test a clean state.
        userList = UserList.getInstance();
        ArrayList<User> users = userList.getUsers();
        if (users != null) {
            users.clear();
        } else {
            // defensive: if DataLoader returned null, set an empty list (shouldn't happen in normal code).
            // But we don't call reflection here; instead fail early to highlight the issue.
            fail("UserList.getUsers() returned null — DataLoader.getUsers() must return an ArrayList<User>.");
        }
    }

    // ---------- Singleton tests ----------

    @Test
    public void testGetInstanceReturnsSameObject() {
        UserList first = UserList.getInstance();
        UserList second = UserList.getInstance();
        assertSame("getInstance should always return the same singleton instance", first, second);
    }

    // ---------- addUser valid cases ----------

    @Test
    public void testAddUserValidCreatesUser() {
        boolean created = userList.addUser("alice", "password", null, null, null);
        assertTrue("addUser should return true for valid username/password", created);

        User u = userList.getUser("alice", "password");
        assertNotNull("getUser should return the created user", u);
        assertEquals("username should match", "alice", u.getUsername());
        assertEquals("password should match", "password", u.getPassword());
    }

    @Test
    public void testAddUserNullSettingsCreatesDefaultSettings() {
        // create user with settings == null; addUser should create default Settings
        boolean created = userList.addUser("bob", "pw123", null, null, null);
        assertTrue(created);

        User u = userList.getUser("bob", "pw123");
        assertNotNull("user should exist", u);
        assertNotNull("settings should be non-null when addUser received null settings", u.getSettings());
    }

    // ---------- addUser invalid usernames ----------

    @Test
    public void testAddUserRejectsNullUsername() {
        boolean created = userList.addUser(null, "password", null, null, null);
        assertFalse("addUser must reject null username", created);
    }

    @Test
    public void testAddUserRejectsEmptyUsername() {
        boolean created = userList.addUser("", "password", null, null, null);
        assertFalse("addUser must reject empty username", created);
    }

    @Test
    public void testAddUserRejectsWhitespaceOnlyUsername() {
        boolean created = userList.addUser("   ", "password", null, null, null);
        assertFalse("addUser must reject username that is only whitespace", created);
    }

    @Test
    public void testAddUserRejectsUsernameWithNewline() {
        boolean created = userList.addUser("bad\nname", "password", null, null, null);
        assertFalse("addUser must reject username containing newline", created);
    }

    // ---------- addUser invalid passwords ----------

    @Test
    public void testAddUserRejectsNullPassword() {
        boolean created = userList.addUser("charlie", null, null, null, null);
        assertFalse("addUser must reject null password", created);
    }

    @Test
    public void testAddUserRejectsEmptyPassword() {
        boolean created = userList.addUser("dave", "", null, null, null);
        assertFalse("addUser must reject empty password", created);
    }

    @Test
    public void testAddUserRejectsPasswordWithNewline() {
        boolean created = userList.addUser("ellen", "pw\nbad", null, null, null);
        assertFalse("addUser must reject password containing newline", created);
    }

    // ---------- duplicate username handling ----------

    @Test
    public void testAddUserRejectsDuplicateUsername() {
        boolean first = userList.addUser("frank", "p1", null, null, null);
        assertTrue("first addUser should succeed", first);

        boolean second = userList.addUser("frank", "different", null, null, null);
        assertFalse("second addUser with same username should fail", second);

        // Ensure only one user present
        int count = 0;
        for (User u : userList.getUsers()) {
            if ("frank".equals(u.getUsername())) count++;
        }
        assertEquals("there must be exactly one user with username 'frank'", 1, count);
    }

    @Test
    public void testNewUserMethodReflectsExistingUsers() {
        assertTrue("newUser should return true when list empty", userList.newUser("gina"));

        userList.addUser("gina", "pw", null, null, null);
        assertFalse("newUser should return false for existing username", userList.newUser("gina"));
    }

    // ---------- getUser lookup ----------

    @Test
    public void testGetUserReturnsNullWhenNotFound() {
        userList.addUser("harry", "hpw", null, null, null);
        User u = userList.getUser("harry", "wrongpw");
        assertNull("getUser should return null when password doesn't match", u);

        User notExist = userList.getUser("noone", "nopw");
        assertNull("getUser should return null for non-existing username", notExist);
    }

    @Test
    public void testGetUserWithNullArgumentsDoesNotThrow() {
        // Should simply return null; not throw NPE
        User result = userList.getUser(null, null);
        assertNull("getUser(null, null) should return null (no user matches)", result);
    }

    // ---------- getUsers backing list behavior ----------

    @Test
    public void testGetUsersReturnsLiveList() {
        ArrayList<User> list = userList.getUsers();
        assertNotNull("getUsers must not return null", list);
        int before = list.size();

        // Add a user via addUser and assert the same backing list reflects it
        userList.addUser("ivy", "pw", null, null, null);
        assertEquals("backing getUsers() list must have increased by 1", before + 1, list.size());
        boolean found = false;
        for (User u : list) {
            if ("ivy".equals(u.getUsername())) {
                found = true;
                break;
            }
        }
        assertTrue("user 'ivy' must be present in getUsers() list", found);
    }

    // ---------- save method ----------

    @Test
    public void testSaveDoesNotThrow() {
        // We cannot easily assert that DataWriter.saveUsers() was called without a static mock.
        // This test simply ensures the save() wrapper does not throw.
        userList.addUser("jack", "pw", null, null, null);
        try {
            userList.save();
        } catch (Exception e) {
            fail("save() should not throw an exception: " + e.getMessage());
        }
    }
}
