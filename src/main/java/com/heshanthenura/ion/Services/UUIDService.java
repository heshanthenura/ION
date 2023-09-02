package com.heshanthenura.ion.Services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDService {
    public String GenerateID(){
        UUID uuid = UUID.randomUUID();
        return String.valueOf(uuid);
    }
}
