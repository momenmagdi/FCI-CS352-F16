<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<p> Welcome ${it.name} ^_^ </p>


<form action="/social/doSearch" method="post"> 
<input type="hidden" name="userName" value="${it.name}" >
<p>Enter user name</p>
<input type="text" name="friendName" /> 
<input type="submit" value="Search" />

</form>

<!-- shai bl ne3na3 -->
<p>click</p>
<form action="/social/showURFR" method="post"> 
<input type="submit" value="See" />

</form>

<p>click</p>
<form action="/social/TimeLine" method="post"> 
<input type="submit" value="urPosts" />

</form>


<br>
<br>
<br>

<!-- lactel :D -->
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
  <input type="submit" value="Post">
  
  </form>
  
  <pre>
<a href="/social/CreatePage/">CreatePage</a> <br>

<h1><a href="/social/CreatePages/">Write a post in by my page</a> <br></h1>
<h1><a href="/social/likepage/">choose page to like</a> <br></h1>
</pre>


</body>
</html>