package com.heshanthenura.ion.Database.Serives;

import com.heshanthenura.ion.Database.Entities.User;
import com.heshanthenura.ion.Database.Repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class DBServices {

    Logger infoLogger = Logger.getLogger("info-logger");

    @Autowired
    public UserRepository userRepository;

    public void SaveUser(String uuid){
        User u = new User();
        u.setUuid(uuid);
        userRepository.save(u);
        infoLogger.info("[H2 Saved] "+uuid+": "+uuid);

    }

    @Transactional
    public void DeleteUser(String uuid){
        User u = userRepository.findByUuid(uuid);
        userRepository.delete(u);
        infoLogger.info("[H2 Deleted] "+uuid);
    }

    @PostConstruct
    public void clearUserDataOnStartup() {
        userRepository.deleteAll();
    }


}
