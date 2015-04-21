package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class LikePageEntity {
	private String UserName;
	private String LikePage;
	private static PageEntity currentActiveUser;
	private long id;
	
	public LikePageEntity(String LikePage,  String UserName) {
		this.UserName = UserName;
		
		this.LikePage = LikePage;
	}

	public LikePageEntity() {
        this.UserName = UserName;
		
		this.LikePage = LikePage;

	}
	public LikePageEntity(String LikePage) {
		this.LikePage = LikePage;
	
	}

	private void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String UserName() {
		return UserName;
	}

	

	public String LikePage() {
		return LikePage;
	}

	public Boolean Like(String PageName) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("Pages");
		PreparedQuery pq = datastore.prepare(gaeQuery);

		for (Entity entity : pq.asIterable()) {
		//	System.out.println("name : "+entity.getProperty("name").toString());
			if (entity.getProperty("PageName").toString().equals(PageName)) {
				DatastoreService datastore2 = DatastoreServiceFactory
						.getDatastoreService();
				Transaction txn = datastore2.beginTransaction();
				Query gaeQuery2 = new Query("LikePages");
				PreparedQuery pq2 = datastore2.prepare(gaeQuery2);
				List<Entity> list = pq2.asList(FetchOptions.Builder.withDefaults());
			//	System.out.println("Size = " + list.size());

				try {
					Entity employee = new Entity("LikePages", list.size() + 2);
				
					employee.setProperty("LikePage", this.LikePage);
					employee.setProperty("UserName", this.UserName);
					

					datastore2.put(employee);
					txn.commit();
				} finally {
					if (txn.isActive()) {
						txn.rollback();
					}
				}
				return true;		
			}
		}
			
		//	System.out.println("PageName : "+entity.getProperty("PageName").toString());
			
		/*	if (entity.getProperty("PageName").toString().equals(PageName)) {
			/*	System.out.println("yay");
			//save save save 	
				DatastoreService datastore2 = DatastoreServiceFactory
						.getDatastoreService();
				Transaction txn = datastore2.beginTransaction();
				Query gaeQuery2 = new Query("LikePages");
				PreparedQuery pq2 = datastore2.prepare(gaeQuery2);
				List<Entity> list = pq2.asList(FetchOptions.Builder.withDefaults());
			//	System.out.println("Size = " + list.size());

				try {
					Entity employee = new Entity("LikePages", list.size() + 2);
				
					employee.setProperty("LikePage", this.LikePage);
					employee.setProperty("UserName", this.UserName);
					

					datastore2.put(employee);
					txn.commit();
				} finally {
					if (txn.isActive()) {
						txn.rollback();
					}
				}
				return true;		
			}*/
		

		return null;
		
	}

}
