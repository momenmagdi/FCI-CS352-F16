package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class MessagEntity {
	public String getSender() {
		return sender;
	}
 
	public void setSender(String sender) {
		this.sender = sender;
	}
 
	public String getReceiver() {
		return receiver;
	}
 
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
 
	public String getText() {
		return text;
	}
 
	public void setText(String text) {
		this.text = text;
	}
	private String sender;
	private String receiver;
	private long id;
	private String text;
 
	public MessagEntity(String sender,String receiver,String text) {
		// TODO Auto-generated constructor stub
 
		this.sender = sender;
		this.receiver = receiver;
		this.text = text;
	}
		public void saveMsg(){
		DatastoreService datastore = DatastoreServiceFactory .getDatastoreService();
		Query gaeQuery = new Query("messages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		ArrayList<Entity> list = (ArrayList<Entity>) pq.asList(FetchOptions.Builder.withDefaults());
		int listSize = list.size();
		long lastId;
		if(listSize == 0 )
			lastId = 0;
		else
			lastId  = list.get(listSize - 1).getKey().getId();
		Entity message = new Entity("messages",lastId+1);
 
 
		message.setProperty("sender",this.sender);
		message.setProperty("receiver", this.receiver);
		message.setProperty("text", this.text);
 
		datastore.put(message);
 
 
	}
	public void setId(long id){
		this.id = id;
	}
	public long getID(){
		return id;
	}
	}