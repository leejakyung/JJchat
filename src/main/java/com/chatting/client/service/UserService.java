package com.chatting.client.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserService {

    public List<String> getUserList(){

        return new CopyOnWriteArrayList<>();
    }
}
