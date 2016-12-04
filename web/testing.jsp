<%--
  Created by IntelliJ IDEA.
  SimpleQuestionary.User: JIeIIIa
  Date: 23.11.2016
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Опросник</title>
  </head>
  <body>
  <h1>
    <p>Здравствуйте, <%= request.getParameter("Name")%> <%= request.getParameter("SurName")%> (возраст:
        <%= request.getParameter("Age")%>)</h1></p>
      <form name="answers" method="post" action="results.jsp">
        <h2>1. __________ a brother?</h2>
        <input name="q1" type="radio" value="0">Have you<br>
        <input name="q1" type="radio" value="1">Do you have<br>
        <input name="q1" type="radio" value="2">Got you<br>
        <h2>2. I like __________ .</h2>
        <input name="q2" type="radio" value="0">swim very much<br>
        <input name="q2" type="radio" value="1">very much swimming<br>
        <input name="q2" type="radio" value="2">swimming very much<br>
        <h2>3. She lives __________ the mountains.</h2>
        <input name="q3" type="radio" value="0">in<br>
        <input name="q3" type="radio" value="1">at<br>
        <input name="q3" type="radio" value="2">on<br>
        <h2>4. How __________ money do you have?</h2>
        <input name="q4" type="radio" value="0">lot of<br>
        <input name="q4" type="radio" value="1">many<br>
        <input name="q4" type="radio" value="2">much<br>
        <input type="submit" value="Отправить" size="60">
      </form>
    </h1>


  </body>
</html>
