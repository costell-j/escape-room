package com.escape.code;
import java.util.UUID;
/*
 * Class used to create a new UUID
 * @Author Erin Check
 */
public class UUIDGenerator {
    public void generateUUID(){
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }
}
