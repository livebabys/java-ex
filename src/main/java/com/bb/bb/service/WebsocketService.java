package com.bb.bb.service;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{sid}")
@Component
public class WebsocketService {
    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<WebsocketService> websocketSet = new CopyOnWriteArraySet<WebsocketService>();

    private Session session;

    private String sid = "";

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid){
        this.session = session;
        websocketSet.add(this);
        addOnlineCount();
        System.out.println("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
        this.sid = sid;
        try{
            sendMessage("connect success");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @OnClose
    public void onClose(){
        websocketSet.remove(this);
        subOnlineCount();
        System.out.println("有链接关闭！当前人数为"+getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("收到来自窗口"+sid+"的信息："+message);
        for(WebsocketService item:websocketSet){
            try{
                item.sendMessage(message);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public static void sendInfo(String message,@PathParam("sid") String sid)throws IOException{
        System.out.println("推送消息到窗口"+sid+",推送内容为:"+message);

        for(WebsocketService item:websocketSet){
            try{
                if(sid == null){
                    item.sendMessage(message);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            }catch(IOException e){
                continue;
            }
        }

    }

    public void sendMessage(String message)throws IOException{
        this.session.getBasicRemote().sendText(message);
    }
    public static synchronized int getOnlineCount(){
        return onlineCount;
    }
    public static synchronized void addOnlineCount(){
        WebsocketService.onlineCount++;
    }
    public static synchronized void subOnlineCount(){
        WebsocketService.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebsocketService> getWebsocketSet(){
        return websocketSet;
    }
}
