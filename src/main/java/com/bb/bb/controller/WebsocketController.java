package com.bb.bb.controller;

import com.bb.bb.service.WebsocketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("websocket")
public class WebsocketController {
    @RequestMapping("push/{cid}")
    public Map pushToWeb(@PathVariable String cid ,String message){
        Map result = new HashMap();
        try{
            WebsocketService.sendInfo(message,cid);
            result.put("code",0);
            result.put("msg","success");
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
