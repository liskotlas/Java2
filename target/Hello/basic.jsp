<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Application</title>
</head>
<body>
<%
    String name = request.getParameter("name");
    out.print ("Name - " + name);
%>

<p><%= request.getParameter("name")%></p>


<p>Name: ${name}</p>
<p>Age: ${age}</p>
</body>
</html>
