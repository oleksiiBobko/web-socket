package com.bobko.chat.task.websocket.handler;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.bobko.chat.client.domain.Client;
import com.bobko.chat.client.repository.ClientRepository;
import com.bobko.chat.websocket.handler.WebSocketHandler;

@ServerEndpoint(value = "/channel/task", configurator = SpringConfigurator.class)
public class TaskWebSocketHandler implements WebSocketHandler {
	@Autowired
	private ClientRepository clientRepository;
	
	@OnOpen
	public void onOpen(Session session) {
		this.clientRepository.add(new Client(session));
	}
	
	@OnClose
	public void onClose(CloseReason reason, Session session) {
		this.clientRepository.remove(new Client(session));
	}
	
}