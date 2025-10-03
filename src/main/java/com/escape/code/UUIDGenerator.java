package com.escape.code;
import java.util.UUID;

public class UUIDGenerator {
    public void generateUUID(){
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }
}
