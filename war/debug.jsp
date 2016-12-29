<%@page import="com.mame.impression.util.TimeUtil"%>
<%-- <%@ page language="java" contentType="text/html; charset=windows-31j"
	pageEncoding="utf-8"%>
 --%>
<%@ page import="com.mame.impression.util.TimeUtil"%>
<%@ page import="com.mame.impression.debug.MockJsonGenerator"%>

<!doctype html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>debug</title>
<script type="text/javascript" language="javascript"
	src="../firstimpression_backend/firstimpression_backend.nocache.js"></script>
</head>

<%
	TimeUtil util = new TimeUtil();
%>

<body>
<!-- 	<form action="/api/v1/user" method="post">
		<INPUT type="hidden" name="id" value=1> <INPUT type="hidden"
			name="param"
			value=<%=MockJsonGenerator.createInputForCreateNewUser()%>>
		<input type="submit" value="Create new user (POST)">
	</form> -->

<!-- 	<form action="/api/v1/questions" method="post">
		<INPUT type="hidden" name="id" value=2> <INPUT type="hidden"
			name="param"
			value=<%=MockJsonGenerator.createInputForCreateNewQuestion()%>>
		<input type="submit" value="Create new questions(POST)">
	</form> -->

	<form action="/api/v1/debug" method="post">
		<INPUT type="hidden" name="created_user_id" value="1482999142662"/>
		<INPUT type="hidden" name="deviceid" value="dMxZUGO_wac:APA91bFfKoPmjRhGp_od7V5X1vKY7dNlI41pDMrtnpm-5Jd9qutSYksLViclkAcNcng3BlsnAIhoQLWbo2sRhQqoct1MFeLk80OzoQSpngTHeKQOmGPnWywqEWUdxIDwxWQEuBhxvXUp "/>
		Description: <INPUT type="text" name="description"/>
		Choice A: <INPUT type="text" name="choice_a"/>
		Chocie B: <INPUT type="text" name="choice_b"/>
		Created username: <INPUT type="text" name="created_user_name"/>
		<input type="submit" value="Create new questions(POST)">
	</form>

</body>
</html>