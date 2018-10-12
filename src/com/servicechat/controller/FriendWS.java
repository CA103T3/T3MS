package com.servicechat.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.util.SecuritySupport;

@ServerEndpoint("/FriendWS/{userName}")
public class FriendWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	
	Gson gson = new Gson();
	

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		/* save the new user in the map */
		
		sessionsMap.put(userName, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
		State stateMessage = new State("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}
		
		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userName, userNames);
		System.out.println(text);
	}
	
	@OnMessage
	public void onMessage(@PathParam("userName")String userName, Session userSession, String message) {
		
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
				
		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
			if (userSession != null && userSession.isOpen()) {
				System.out.println("===========history==========");
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				System.out.println(cmHistory.getMessage());
				return;
			}
		}
		
		System.out.println("====666666666666666===========");
		System.out.println("receiver="+receiver);
		System.out.println("sender="+sender);
		System.out.println();
		System.out.println("sessionsMap="+sessionsMap);
		Session receiverSession = sessionsMap.get(receiver);
		System.out.println("receiverSession="+receiverSession);
		System.out.println();
		
		
		if (receiverSession != null && receiverSession.isOpen()) {
			System.out.println("========================");
			receiverSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message);
			System.out.println("=====@@@@@@@@==========");
		}
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		e.printStackTrace(System.err);
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
