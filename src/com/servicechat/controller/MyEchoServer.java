package com.servicechat.controller;
import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.sun.corba.se.spi.activation.ServerManager;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/ServiceChat/{name}/{room}")
public class MyEchoServer {
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(@PathParam("name") String myName, @PathParam("room") String myRoom, Session userSession) throws IOException {
		allSessions.add(userSession);
		System.out.println(userSession.getId() + ": 連線");
		System.out.println(myName + ": 加入");
		System.out.println(myRoom + ": 房");
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
		
	}

	
	@OnMessage
	public void onMessage(@PathParam("name") String name , @PathParam("room") String myRoom ,Session userSession, String message) {
		for (Session session : allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(name+":"+message);
		}
		System.out.println("Message received: " + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
		e.printStackTrace(System.err);
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
