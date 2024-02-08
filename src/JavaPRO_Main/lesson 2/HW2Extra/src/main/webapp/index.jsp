<%@ page import="hw.example.models.Client" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>HW2</title>
</head>
<body>
<% String login = (String)session.getAttribute("user_login"); %>

<% if (login == null || "".equals(login)) { %>
<form action="/login" method="POST">
    Login: <input type="text" name="login"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" />
</form>
<% } else { %>

<% Client client = (Client) session.getAttribute("client"); %>

<h1>Answer "banana" has <%= client.getBanana() %> point</h1>
<h1>Answer "orange" has <%= client.getOrange() %> point</h1>
<h1>Answer "sleep" has <%= client.getSleep() %> point</h1>
<h1>Answer "run" has <%= client.getRun() %> point</h1>
<hr>
<br>Click this link to <a href="/login?a=exit">logout</a>
<% } %>
</body>
</html>
