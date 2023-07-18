package com.chatting.client;

import com.chatting.client.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ObjectTest {

    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        User user = new User();
        user.setName("A");
        userList.add(user);

        user = new User();
        user.setName("B");
        userList.add(user);

        user = new User();
        user.setName("C");
        userList.add(user);

        user = new User();
        user.setName("D");
        userList.add(user);
    }

    @Test
    void obejctTest(){

    }
}
