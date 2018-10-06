package com.ticketorder.controller;

import java.text.DateFormat;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/MyBookingServer2/{sessionNo}/{memNo}")
public class MyBookingServer implements ServletContextListener {

//	private static final Set<Session> allSessions = Collections.synchronizedSet(new LinkedHashSet<Session>());
	private static final Map<String, LinkedHashSet<Session>> allSessions = Collections
			.synchronizedMap(new HashMap<String, LinkedHashSet<Session>>());

	// 線上訂位的紀錄, 將暫存儲存至這個TreeMap(同步鎖定)
	// TreeMap:元素會依值由小至大排序
	private static Map<String, String> bookingMap = null;

//	private static final Map<String, String> bookingMap = Collections
//			.synchronizedSortedMap(new TreeMap<String, String>());

	// <場次編號,map<座位 , 會員編號>>
	private static final Map<String, Map<String, String>> sessionBookingMap = Collections.synchronizedMap(new HashMap<>());

	// 伺服器時間的推播排程器
	Timer timer;
	List<String> list = Collections.synchronizedList(new ArrayList<>());

	// ********************************** ServletContextListener
	// **********************************
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
//		context.setAttribute("bookingMap", bookingMap);
		context.setAttribute("sessionBookingMap", sessionBookingMap);
		timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				sendTimeToAll(allSessions);
			}
		};
		timer.scheduleAtFixedRate(task, new java.util.Date(), 1000);// 目前時間
		System.out.println("ServletContextListener通知: 已建立時間推播排程 與 bookingList");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("WebSocket Context Destroyed");
		timer.cancel();
		System.out.println("已移除排程!");
	}

	// *************************************** WebSocket
	// ********************************************

	@OnOpen
	public void onOpen(@PathParam("sessionNo") String sessionNo, @PathParam("memNo") String memNo, Session userSession,
			EndpointConfig config) {
		System.out.println("onOpen_session_no=" + sessionNo);
		System.out.println("onOpen_memNo=" + memNo);

		LinkedHashSet<Session> set;
		if (allSessions.containsKey(sessionNo)) {
			set = allSessions.get(sessionNo);
			set.add(userSession);
		} else {
			set = new LinkedHashSet<Session>();
			set.add(userSession);
		}
		allSessions.put(sessionNo, set);
		bookingMap = Collections.synchronizedSortedMap(new TreeMap<String, String>());
//		allSessions.add(userSession); // LinkedHashSet
		System.out.println(userSession.getId() + ": 已連線");
		userSession.getAsyncRemote().sendText("myID=" + userSession.getId());
	}

	@OnMessage
	public void onMessage(@PathParam("sessionNo") String sessionNo, @PathParam("memNo") String memNo,
			Session userSession, String message) {
		for (String str : allSessions.keySet()) {
			LinkedHashSet<Session> set;
			if (str.equals(sessionNo)) {
				set = allSessions.get(str);
				String[] strs = message.split("@");
				String b = strs[1];
				String seat = strs[2];
				String id = strs[3];
				System.out.println("seat=" + seat);
				System.out.println("id=" + id);
				if (b.equals("true")) {
					bookingMap.put(seat, memNo);
				} else if (b.equals("false")) {
					bookingMap.remove(seat);
				}

				for (Session session : set) {
					if (session.isOpen())
						session.getAsyncRemote().sendText(message);
					System.out.println("=======================");
					System.out.println("message:" + message);
				}
			}
		}
		sessionBookingMap.put(sessionNo, bookingMap);
//		for (Session session : allSessions) {
//			if (session.isOpen())
//				session.getAsyncRemote().sendText(message);
//			System.out.println("=======================");
//			System.out.println("message:" + message);
//		}

		// @true@1-2@0 <-自訂的格式
//		String[] str = message.split("@");
//		String b = str[1];
//		String seat = str[2];
//		String id = str[3];
//		System.out.println("seat=" + seat);
//		System.out.println("id=" + id);
//		if (b.equals("true")) {
//			bookingMap.put(seat, memNo);
//		} else if (b.equals("false")) {
//			bookingMap.remove(seat);
//		}

		System.out.println("Message received: " + message);
		System.out.println("bookingMap: " + bookingMap);
		System.out.println("sessionBookingMap:" + sessionBookingMap);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		// e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out
				.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

	private void sendTimeToAll(Map<String, LinkedHashSet<Session>> allSessions) {
		for (String str : allSessions.keySet()) {
			LinkedHashSet<Session> set = allSessions.get(str);
			for (Session session : set) {
				DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
				String clock = df.format(new java.util.Date());
				session.getAsyncRemote().sendText(clock + "【在線人數:" + allSessions.size() + "】");
			}
		}
//		for (Session aUserSession : allSessions) {
//			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
//			String clock = df.format(new java.util.Date());
//			aUserSession.getAsyncRemote().sendText(clock + "【在線人數:" + allSessions.size() + "】");
//		}
	}

}
