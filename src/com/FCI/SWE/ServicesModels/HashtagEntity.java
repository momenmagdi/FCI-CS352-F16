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

public class HashtagEntity {
	private static String Hashtag;
	private static int Occerance;
	private static long Hashtag_ID;
	
	public String getHashtag(){
		return Hashtag;
	}
	
	public long getHashtag_ID(){
		return Hashtag_ID;
	}
	
	public int getOccerance(){
		return Occerance;
	}
	
	public void setHashtag(String Hashtag){
		this.Hashtag = Hashtag;
	}
	
	public void setOccerance(int Occerance){
		this.Occerance = Occerance;
	}
	
	public void setHashtag_ID(long Hashtag_ID){
		this.Hashtag_ID=Hashtag_ID;
	}
	
	
	public Boolean saveHashtag(String hash) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Hashtag");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

//		 here we want to do 2 tables 
//		 table contain Hashtage and num Hashtag_ID and number of occurence
//		 table contain Hashtag_ID and Post_ID 
		
		Entity Hashtag_stat = new Entity("Hashtag", list.size() + 3);
        long num=UpdateHashtag(hash);
		Hashtag_stat.setProperty("Hashtag",hash);
		Hashtag_stat.setProperty("Hashtag_ID",getID());
		Hashtag_stat.setProperty("Occerance",num);
					
		if(datastore.put(Hashtag_stat).isComplete())
				return true;
			else return false;
	}
	
	public static String get(long Hashtag_ID) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Hashtag");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("Hashtag_ID").toString().equals(Hashtag_ID)) {
				String returnedUser = new String(entity.getProperty("Hashtag_ID")
						.toString());
				return returnedUser;
			}
		}

		return null;
	}
	
	
	public static Boolean HashtagView(String Hashtag, long Post_ID) {
		
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("Hashtag");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println("Size = " + list.size());

		try {
			Entity employee = new Entity("Hashtag", list.size() + 4);

			employee.setProperty("Hashtag", Hashtag);
			employee.setProperty("Occerance", Occerance);

			employee.setProperty("Hashtag", "true");

			System.out.println(datastore.put(employee).isComplete());
			txn.commit();
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
		}
		return true;

	}	

	public static ArrayList<HashtagEntity> listOfHashtag() {

		ArrayList<HashtagEntity> list = new ArrayList<HashtagEntity>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("Hashtag");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("Hashtag").toString().equals("Hashtag")
					&& entity.getProperty("Hashtag").toString()
							.equals("true")) {
				
				HashtagEntity user=new HashtagEntity();
				list.add(user);
			}
		}
		return list;
	}

	
	public long UpdateHashtag(String hash){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Hashtag");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("Hashtag").toString().equals(hash)) {
				long Occerance = (long)entity.getProperty("Occerance");
				Occerance++;
				 return Occerance;
			}
		}

		return 1;
	}
	
	
	public long getID(){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Hashtag");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		long ID=0;
		for (Entity entity : pq.asIterable()) {
				ID = (long)entity.getProperty("Hashtag_ID");
			}
		 return ++ID;

	}	
	
	
	
	
}