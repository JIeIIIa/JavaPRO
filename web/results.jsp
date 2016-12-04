<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.HashMap" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Опросник</title>
    <link rel="stylesheet" href="style.css" type="text/css" />
</head>
<body>

    <%! HashMap<String, HashMap<Integer, Integer>> res = new HashMap<String, HashMap<Integer, Integer>>(); %>
    <%
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            HashMap<Integer, Integer> value = res.get(paramName);
            if (value == null) {
                value = new HashMap<Integer, Integer>();
                value.put(0, 0);
                value.put(1, 0);
                value.put(2, 0);
            }
            Integer answerNumber = Integer.parseInt(request.getParameter(paramName));
            value.put(answerNumber, value.get(answerNumber) + 1);
            res.put(paramName, value);
        }

    %>
    <table cellspacing="5" ; cellpadding="5" border="1" align="center">
        <tr>
            <td colspan="2"><h1 align="center">Результаты</h1></td>
        </tr>
        <tr>
            <td><span class="leftcell"><h2>Вариант ответа</h2></span></td>
            <td><span class="leftcell"><h2>Количество</h2></span></td>
        </tr>
        <tr>
            <td colspan="2"><h3 align="left">1. Do you have a brother?</h3></td>
        </tr>
        <tr>
            <td><span class="wrong">Have you</span></td>
            <td><span class="wrong"><%if (res.get("q1") == null || res.get("q1").get(0) == null) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q1").get(0)%>
                                        <%}%>
            </span></td>
        </tr>
        <tr>
            <td><span class="wright" font-style="italic">Do you have</span></td>
            <td><span class="wright"><%if (res.get("q1") == null || res.get("q1").get(1) == null) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q1").get(1)%>
                                        <%}%>
            </span></td>
        </tr>
        <tr>
            <td><span class="wrong">Get you</span></td>
            <td><span class="wrong"><%if (res.get("q1") == null || res.get("q1").get(2) == null) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q1").get(2)%>
                                        <%}%>
            </span></td>
        </tr>
        <tr>
            <td colspan="2"><h3 align="left">2. I like swimming very much.</h3></td>
        </tr>
        <tr>
            <td><span class="wrong">swim very much</span></td>
            <td><span class="wrong"><%if (res.get("q2") == null || res.get("q2").get(0) == 0) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q2").get(0)%>
                                        <%}%>
            </span></td>
        </tr>
        <tr>
            <td><span class="wrong">very much swimming</span></td>
            <td><span class="wrong"><%if (res.get("q2") == null || res.get("q2").get(1) == 0) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q2").get(1)%>
                                        <%}%>
            </span></td>
        </tr>
        <tr>
            <td><span class="wright">swimming very much</span></td>
            <td><span class="wright"><%if (res.get("q2") == null || res.get("q2").get(2) == 0) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q2").get(2)%>
                                        <%}%>
            </span></td>
        </tr>
        <tr>
            <td colspan="2"><h3 align="left">3. She lives in the mountains.</h3></td>
        </tr>
        <tr>
            <td><span class="wright">in</span></td>
            <td><span class="wright"><%if (res.get("q3") == null || res.get("q3").get(0) == 0) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q3").get(0)%>
                                        <%}%>
            </span></td>
        </tr>
        <tr>
            <td><span class="wrong">at</span></td>
            <td><span class="wrong"><%if (res.get("q3") == null || res.get("q3").get(1) == 0) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q3").get(1)%>
                                        <%}%>
            </span></td>
        </tr>
        <tr>
            <td><span class="wrong">on</span></td>
            <td><span class="wrong"><%if (res.get("q3") == null || res.get("q3").get(2) == 0) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q3").get(2)%>
                                        <%}%>
            </span></td>
        </tr>
        <tr>
            <td colspan="2"><h3 align="left">4. How much money do you have?</h3></td>
        </tr>
        <tr>
            <td><span class="wrong">lot of</span></td>
            <td><span class="wrong"><%if (res.get("q4") == null || res.get("q4").get(0) == 0) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q4").get(0)%>
                                        <%}%>
            </span></td>
        </tr>
        <tr>
            <td><span class="wrong">many</span></td>
            <td><span class="wrong"><%if (res.get("q4") == null || res.get("q4").get(1) == 0) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q4").get(1)%>
                                        <%}%>
            </span></td>
        </tr>
        <tr>
            <td><span class="wright">much</span></td>
            <td><span class="wright"><%if (res.get("q4") == null || res.get("q4").get(2) == 0) {%>
                                        0
                                        <%} else {%>
                                        <%=res.get("q4").get(2)%>
                                        <%}%>
            </span></td>
        </tr>

    </table>

    <br>
    <form action="/login.html"><input type="submit" value="Пройти тест еще раз" size="60"></form>




</body>
</html>
