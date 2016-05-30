package com.bobko.chat.task.websocket.broadcaster;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.bobko.chat.client.repository.ClientRepository;
import com.bobko.chat.task.domain.Task;
import com.bobko.chat.websocket.broadcaster.Broadcaster;

@Component
public class TaskBroadcaster implements Broadcaster<Task> {
	@Autowired
	private ClientRepository clients;
	private Gson gson;
	
	@PostConstruct
	public void init() {
		this.gson = new Gson();
	}
	
	@Override
	public void broadcast(List<Task> task) {
		this.clients.forEach(client -> {
			try {
				client.sendText(this.gson.toJson(task));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
