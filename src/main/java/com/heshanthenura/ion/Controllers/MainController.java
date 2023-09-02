package com.heshanthenura.ion.Controllers;

import com.heshanthenura.ion.Database.Serives.DBServices;
import com.heshanthenura.ion.Services.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.logging.Logger;


@Controller
public class MainController {

    Logger infoLogger = Logger.getLogger("info-logger");

    @Autowired
    UUIDService uuidService = new UUIDService();

    @Autowired
    DBServices dbServices = new DBServices();

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String Index(){
        return "index";
    }

    @RequestMapping(value="/user",method = RequestMethod.POST)
    public String IndexPost(@RequestParam("name") String name, RedirectAttributes redirectAttributes){
        infoLogger.info("[DATA] "+name);
        redirectAttributes.addFlashAttribute("name", name);
        return "redirect:/users";
    }

    @RequestMapping(value="/users",method = RequestMethod.GET)
    public String Users(Model model, @ModelAttribute("name") String name){
        String id = uuidService.GenerateID();
        infoLogger.info("[DATA] "+name+": "+id);
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return "users";
    }



}
