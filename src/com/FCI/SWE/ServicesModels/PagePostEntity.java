package com.FCI.SWE.ServicesModels;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import java.util.ArrayList;

public class PagePostEntity {
	private String PageName;
	private String Postpage;
	private static PageEntity currentActiveUser;
	private long id;
	
	public PagePostEntity(String PageName,  String Postpage) {
		this.Postpage = Postpage;
		
		this.PageName = PageName;
	}

	public PagePostEntity() {
        this.Postpage = Postpage;
		
		this.PageName = PageName;
	}
	public PagePostEntity(String Postpage) {
		this.Postpage = Postpage;
	
	}

	private void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getPagePost() {
		return Postpage;
	}

	

	public String getPage() {
		return PageName;
	}

	public Boolean WritePost() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("PagePost");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println("Size = " + list.size());

		try {
			Entity employee = new Entity("PagePosts", list.size() + 2);
			employee.setProperty("PageName", this.PageName);
			employee.setProperty("Postpage", this.Postpage);
			
			

			datastore.put(employee);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}


	
}
