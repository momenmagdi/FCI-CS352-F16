package com.FCI.SWE.Services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.ServicesModels.ConnectHashtagAndPost;
import com.FCI.SWE.ServicesModels.HashtagEntity;
import com.FCI.SWE.ServicesModels.PostEntity;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class PostServices {

	@POST
	@Path("/CreatePostService")
	public String createPost(@FormParam("user_ID") String userId,
			@FormParam("post") String post,
			@FormParam("privacy") String privacy,
			@FormParam("Feeling") String feeling) {
	
		System.out.println();
		PostEntity postEntity = new PostEntity();
		postEntity.setFeeling(feeling);
		postEntity.setPost(post);
		postEntity.setUser_ID(Long.parseLong(userId));
		postEntity.setPrivacy(privacy);
		JSONObject json = new JSONObject();
		
		HashtagEntity hash = new HashtagEntity();
		ConnectHashtagAndPost connect= new ConnectHashtagAndPost();
		String words[] = post.split(" ");
		String word="";
		for(int i=0; i <words.length ; i++){
			word=words[i];
			if(words[i].charAt(0)=='#'){ 
				String h=words[i];
				//hashTags.add(words[i]);
				//hash.setHashtag(words[i]);
				hash.saveHashtag(h);
				connect.saveAll();
			}
		}
		
		if(postEntity.savePost())
			json.put("Status", "Posted");
		else
			json.put("Status", "Failed");
		return json.toJSONString();
	}

}
