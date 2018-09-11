package com.example.demo;
import org.pac4j.core.config.Config;
import com.fasterxml.jackson.core.JsonEncoding;
import com.google.gson.reflect.TypeToken;
import jdk.nashorn.internal.parser.JSONParser;
import org.pac4j.core.client.Clients;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.sql.profile.service.DbProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import org.pac4j.*;


import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController

public class RestAPI {
    ConfigBD configBD = new ConfigBD();
 /*   public Config getCongif(){
        DbProfileService dbProfileService = new DbProfileService(dataSource);
        final FormClient formClient = new FormClient("http://localhost:4200", dbProfileService);
        final Clients clients = new Clients("http://localhost:8080", formClient);
        final Config configuration = new Config(clients);
        return configuration;
    }*/

    private Config configuration = configBD.config();
    @RequestMapping(value = "/auth", method = RequestMethod.POST )
    @ResponseBody
    public Object authResponse(Map<String,Object> map) {
        final FormClient formClient = (FormClient) configuration.getClients().findClient("FormClient");
        map.put("callbackUrl",formClient.getCallbackUrl());
        Gson g = new Gson();
        return g.toJson(formClient);

    }
}
