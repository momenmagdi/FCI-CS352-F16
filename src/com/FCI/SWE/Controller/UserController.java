package com.FCI.SWE.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mortbay.util.ajax.JSON;

import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.google.appengine.labs.repackaged.org.json.JSONException;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces("text/html")
public class UserController {
	public static String username;
	public String UserName=UserController.username;


	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@POST
	@Path("/doSearch")
	public Response usersList(@FormParam("userName") String uname , @FormParam("friendName") String fname){
		System.out.println(uname);
		// lma badoos 3 l button li f l form li hya mawgoda f home.jsp haygi hna 
		//ba3d kda haya5od l path da "http://localhost:8888/rest/search" w yro7 ynf7
		//f l user service 
		String serviceUrl = "http://localhost:8888/rest/search";
		String urlParameters = "userName=" + uname + "&friendName=" + fname ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		return Response.ok(new Viewable("/jsp/search_result")).build();
	}
	@GET
	@Path("/signup")
	public Response signUp() {
		return Response.ok(new Viewable("/jsp/register") ).build();
	}
	
	@GET	
	@Path("/CreatePage")
	public Response CreatePage() {
		//System.out.println("username "+username);

		return Response.ok(new Viewable("/jsp/CreatePage") ).build();
	}
	
	@GET	
	@Path("/CreatePages")
	public Response CreatePages() {
		//System.out.println("username "+username);

		return Response.ok(new Viewable("/jsp/CreatePages") ).build();
	}
	@GET
	@Path("/likepage")
	public Response likepage() {
		//System.out.println("username "+username);

		return Response.ok(new Viewable("/jsp/likepage") ).build();
	}
	
	
	@GET
	@Path("/search")
	public Response search(){
		
		return Response.ok(new Viewable("/jsp/search")).build();
	}
	/**
	 * Action function to render home page of application, home page contains
	 * only signup and login buttons
	 * 
	 * @return enty point page (Home page of this application)
	 */
	@GET
	@Path("/")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	/**
	 * Action function to render login page this function will be executed using
	 * url like this /rest/login
	 * 
	 * @return login page
	 */
	@GET
	@Path("/login")
	public Response login() {
		
		return Response.ok(new Viewable("/jsp/login")).build();
	}

	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/response")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {

		String serviceUrl = "http://localhost:8888/rest/RegistrationService";
		String urlParameters = "uname=" + uname + "&email=" + email
				+ "&password=" + pass;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Registered Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}
	
	@POST
	@Path("/CreatePages")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("PageName") String PageName
			) throws org.json.simple.parser.ParseException, JSONException, ParseException {

		String serviceUrl = "http://localhost:8888//rest/CreateSuccesfully";
		String urlParameters = "&PageName=" + PageName +"&UserName=" + UserName 
				;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		// System.out.println(retJson);
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		Map<String, String> map = new HashMap<String, String>();
		if (object.get("Status").equals("OK")){
			
			return "Created";
	
		}
		return "Failed";
		
	}
	
	
//////////////////////////	/****************/////////////////
	@POST
	@Path("/Write")
	@Produces(MediaType.TEXT_PLAIN)
	public String post(@FormParam("PageName") String PageName,
			@FormParam("YourPost") String YourPost
			) throws org.json.simple.parser.ParseException, JSONException, ParseException {

		String serviceUrl = "http://localhost:8888//rest/PostSuccesfully";
		String urlParameters = "&PageName=" + PageName +"&YourPost=" + YourPost 
				;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		// System.out.println(retJson);
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		Map<String, String> map = new HashMap<String, String>();
		if (object.get("Status").equals("OK")){
			
			return "Writed";
	
		}
		return "Failed";
		
	}
	
	@POST
	@Path("/Likepage")
	@Produces(MediaType.TEXT_PLAIN)
	public String Like(@FormParam("PageName") String PageName
			) throws org.json.simple.parser.ParseException, JSONException, ParseException {

		String serviceUrl = "http://localhost:8888//rest/Liked";
		String urlParameters = "PageName=" + PageName +"&UserName=" + UserName 
				;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		// System.out.println(retJson);
		obj = parser.parse(retJson);
		JSONObject object = (JSONObject) obj;
		Map<String, String> map = new HashMap<String, String>();
		if (object.get("Status").equals("OK")){
			
			return "Liked";
	
		}
		return "Failed";
		
	}

	

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	@POST
	@Path("/home")
	@Produces("text/html")
	public Response home(@FormParam("uname") String uname,
			@FormParam("password") String pass) {

		if(pass.equals("") || uname.equals(""))
		{
			return null ;	
		}
		String urlParameters = "uname=" + uname + "&password=" + pass;
		String retJson = Connection.connect(
				"http://localhost:8888/rest/LoginService"
				//"http://localhost:8888/rest/LoginService"
				, urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			User user = User.getUser(object.toJSONString());
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/home", map)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;

	}
	
	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param 
	 *            take user name and return whos send to him friend request 
	 * @return  page view his pending friends
	 */
	
	
	
	
	@POST
	@Path("/TimeLine")
	@Produces("text/html")
	public Response timeLine() {
		String retJson = Connection.connect(
				"http://localhost:8888/rest/showTimeLine"
				//"http://localhost:8888/rest/LoginService"
				, "",
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		HashMap<String,ArrayList<User>>listof=new HashMap<String,ArrayList<User>>();
		ArrayList<User>Posts=new ArrayList<User>();
		try {
			JSONArray arr=(JSONArray) parser.parse(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			
		for(int i=0;i<arr.size();i++){
			
			object=(JSONObject) arr.get(i);
			Posts.add(User.parsInfo(object.toJSONString()));
		
		}
		listof.put("Posts",Posts);
			
			
			return Response.ok(new Viewable("/jsp/TimeLine", listof)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;

	}
	/**
	 * Action function to response to accept friend request
	 * user from datastore
	 * 
	 * @param user 
	 *            take user name and return whos send to him friend request 
	 * @return  page view his pending friends
	 */

	//
	/*@POST
	@Path("/addfriend")
	@Produces(MediaType.TEXT_PLAIN)
	public String addFriend(@FormParam("friendName") String FName, @FormParam("userName") String UName) {

		String serviceUrl = "http://localhost:8888/rest/addfriend";
		String urlParameters = "friendName=" + FName + "&userName=" + UName;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Accpted"))
				return FName+" is now friend with u";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Failed";
	}*/
	
	/**
	 * Action function to response to 
	 * send message to friends 
	 * user from datastore
	 * 
	 * @param sender
	 *            provided user name
	 * @param reciver
	 *            provided user will recive message
	 *  @param text
	 *            user message          
	 * @return page
	 */
	
	@POST
	@Path("/sendMessgae")
	@Produces("text/html")
	public String sendFriendRequest(@FormParam("sender") String sender,
			@FormParam("receiver") String receiver,@FormParam("text") String text) {
 
		String serviceUrl = "http://localhost:8888/rest/sending";
		String urlParameters = "sender=" + sender + "&receiver=" +receiver + "&text=" +text;
		String retJson = Connection.connect( serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		try {
 
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj; 
			if (object.get("Status").equals("OK"))
				return "Message Sent Successfully";
			else  
				return "User not found";
 
 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
 
	}
	
	
	
	
	
	
	
	@POST
	@Path("/showURFR")
	@Produces("text/html")
	public Response show() {
		String retJson = Connection.connect(
				"http://localhost:8888/rest/showfriends"
				//"http://localhost:8888/rest/LoginService"
				, "",
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		HashMap<String,ArrayList<User>>listof=new HashMap<String,ArrayList<User>>();
		ArrayList<User>users=new ArrayList<User>();
		try {
			JSONArray arr=(JSONArray) parser.parse(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			
		for(int i=0;i<arr.size();i++){
			
			object=(JSONObject) arr.get(i);
			users.add(User.parsInfo(object.toJSONString()));
		
		}
		listof.put("usersList",users);
			
			
			return Response.ok(new Viewable("/jsp/UrRe", listof)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;

	}
	
	
	
	
	
	
	
	
}