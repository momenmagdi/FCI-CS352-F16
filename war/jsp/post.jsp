<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Post</title>
</head>
<body>
  <form action="/social/CreatePost" method="post">
  Post : <textarea rows="5" cols="50" name = "post"></textarea> <br>
    Feeling : <select name = "Feeling">
  				<option value = "Happy">Happy</option>
  				<option value = "Sad">Sad</option>
  				<option value = "Confused">Confused</option>
  				<option value = "lonely">lonely</option>
  				
  			</select>
  
  
  Privacy : <select name = "privacy">
  				<option value = "public">Public</option>
  				<option value = "private">Private</option>
  				<option value = "closed">Closed</option>
  			</select>
  			
  <input type="submit" value="post">
  
  </form>
  
</body>
</html>
