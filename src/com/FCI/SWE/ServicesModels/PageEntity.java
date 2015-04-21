package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class PageEntity {
	private String UserName;
	private String PageName;
	private static PageEntity currentActiveUser;
	private long id;
	
	public PageEntity(String PageName,  String UserName) {
		this.UserName = UserName;
		
		this.PageName = PageName;
	}

	public PageEntity() {
        this.UserName = UserName;
		
		this.PageName = PageName;
	}
	public PageEntity(String UserName) {
		this.UserName = UserName;
	
	}

	private void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return UserName;
	}

	

	public String getPage() {
		return PageName;
	}

	public Boolean CreatePage() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("Pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println("Size = " + list.size());

		try {
			Entity employee = new Entity("Pages", list.size() + 2);
			employee.setProperty("PageName", this.PageName);
			employee.setProperty("UserName", this.UserName);
			
			

			datastore.put(employee);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}
	public static PageEntity getUser(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			currentActiveUser = new PageEntity(object.get("PageName").toString(), object.get(
					"UserName").toString());
		
			currentActiveUser.setId(Long.parseLong(object.get("id").toString()));
			return currentActiveUser;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static PageEntity getPage(String name) {
	
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);

		for (Entity entity : pq.asIterable()) {
			System.out.println("name : "+entity.getProperty("UserName").toString());
			if (entity.getProperty("UserName").toString().equals(name))
					 {
				PageEntity returnedPage = new PageEntity(entity.getProperty(
						"PageName").toString(), entity.getProperty("UserName").toString());

				returnedPage.setId(entity.getKey().getId());
				return returnedPage;
			}
		}

		return null;
	}
	
	
	
	
}
