package com.FCI.SWE.Controller;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;

@Path("/")
@Produces("text/html")
public class PostController {

	@GET
	@Path("/post")
	public Response post() {

		if (User.getCurrentActiveUser() == null) {
			return Response.serverError().build();
		}
		return Response.ok(new Viewable("/jsp/PostViews/createPost")).build();
	}

	@POST
	@Path("/CreatePost")
	public String createPost(
			@FormParam("Feeling") String Feeling,
			@FormParam("post") String post,
			@FormParam("privacy") String privacy) {
		
		String serviceUrl = "http://localhost:8888/rest/CreatePostService";
		String urlParameters = "user_ID=" + User.getCurrentActiveUser().getId()
				+ "&post=" + post + "&privacy=" + privacy + "&Feeling=" + Feeling ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
 		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Post has been posted Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
