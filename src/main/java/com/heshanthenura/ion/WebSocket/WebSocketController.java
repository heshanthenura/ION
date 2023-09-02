package com.heshanthenura.ion.WebSocket;

import com.heshanthenura.ion.Database.Entities.User;
import com.heshanthenura.ion.Database.Repositories.UserRepository;
import com.heshanthenura.ion.Database.Serives.DBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class WebSocketController {

    Logger infoLogger = Logger.getLogger("info-logger");

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    DBServices dbServices = new DBServices();

    @Autowired
    UserRepository userRepository;

    @MessageMapping("/connect")
    @SendTo("/topic/connect")
    public String ConnectUser(String data){
        infoLogger.info("[Connected] "+data);
        dbServices.SaveUser(data);
        return data;
    }

    @MessageMapping("/disconnect")
    @SendTo("/topic/disconnect")
    public String DisconnectUser(String data){
        infoLogger.info("[Disconnected] "+data);
        dbServices.DeleteUser(data);
        return data;
    }

    @MessageMapping("/users")
    public void Users(String data){
        infoLogger.info("[ASKED FOR USER] "+data);
        List<User> users = userRepository.findAll();
        simpMessagingTemplate.convertAndSend("/topic/users/"+data,users);
    }


}
