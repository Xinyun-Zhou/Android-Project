package com.example.mymarketplaceapp.models;

import junit.framework.TestCase;

import org.junit.Test;

public class UserDaoTest extends TestCase {
    @Test
    public void testSearchUser() {
        User user1 = UserDao.getInstance().searchUser("admin");
        assertEquals(0, user1.getUid());
        User user2 = UserDao.getInstance().searchUser("comp2100@anu.au");
        assertEquals(1, user2.getUid());
        User user3 = UserDao.getInstance().searchUser("comp6442@anu.au");
        assertEquals(2, user3.getUid());
        assertNull(UserDao.getInstance().searchUser("abcd"));
    }

}
