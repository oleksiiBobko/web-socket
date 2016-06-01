/*1*/
/*2*/
/*3*/
/*4*/
package com.bobko.chat.client.domain;

import java.io.IOException;

import javax.websocket.Session;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Client {
	private final String id;
	private final Session session;
	
	private static final Logger LOG = LogManager.getLogger(Client.class);
	
	public Client(Session session) {
		LOG.info("instantiated");
		//some text
		this.id = this.toString();
		this.session = session;
	}
	
	public void sendText(String text) throws IOException {
		this.session.getBasicRemote().sendText(text);
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((session == null) ? 0 : session.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (session == null) {
			if (other.session != null)
				return false;
		} else if (!session.equals(other.session))
			return false;
		return true;
	}
	
	
	
}
