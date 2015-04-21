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

public class ConnectHashtagAndPost {
	private long Post_ID;
	private static long Hashtag_ID;
	private long Connect_ID;
	
	public long getHashtag_ID(){
		return Hashtag_ID;
	}
	
	public void setHashtag_ID(long Hashtag_ID){
		this.Hashtag_ID=Hashtag_ID;
	}

	public long getPost_ID(){
		return Post_ID;
	}
	
	public void setPost_ID(long Post_ID){
		this.Post_ID = Post_ID;
	}
	
	public long getConnect_ID(){
		return Connect_ID;
	}
	
	public void setConnect_ID(long Connect_ID){
		this.Connect_ID = Connect_ID;
	}
	
	public Boolean saveAll() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("ConnectHashtagAndPost");
		
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		Entity post = new Entity("ConnectHashtagAndPost", list.size() + 5);

		//post.setProperty("name", this.name);
		post.setProperty("Hashtag_ID", this.Hashtag_ID);
		post.setProperty("Post_ID", this.Post_ID);
		
		if(datastore.put(post).isComplete())
			return true;
		else return false;

	}
	
	
	public static String get(long Connect_ID){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("ConnectHashtagAndPost");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("Connect_ID").toString().equals(Connect_ID)) {
				String returnedUser = new String(entity.getProperty("Connect_ID")
						.toString());
				return returnedUser;
			}
		}

		return null;
	}
	
	
	public static Boolean postView(long Post_ID, long Hashtag_ID) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("ConnectHashtagAndPost");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println("Size = " + list.size());

		try {
			Entity employee = new Entity("ConnectHashtagAndPost", list.size() + 5);

			employee.setProperty("Post_ID", Post_ID);
			employee.setProperty("Hashtag_ID", Hashtag_ID);
			employee.setProperty("ConnectHashtagAndPost", "Done");

			System.out.println(datastore.put(employee).isComplete());
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}

	public static ArrayList<PostEntity> listOfPost() {

		ArrayList<PostEntity> list = new ArrayList<PostEntity>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("post");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("post").toString().equals("post")
					&& entity.getProperty("post").toString()
							.equals("true")) {
				
				PostEntity user=new PostEntity();
				list.add(user);
			}
		}
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}