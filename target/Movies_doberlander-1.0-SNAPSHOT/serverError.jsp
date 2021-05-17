<%--
    Created By: David Oberlander
    Project: Movies_doberlander
    Date/Time: 4/25/2021 10:44 PM
    Description: Custom Server Error Page
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" import="java.io.*"%>
<html>
<head>
  <title>Internal Server Error</title>
  <link rel="stylesheet" href="assets/css/errors.css">
</head>
<body>
<div class="container">
  <main>
    <h1>Internal Server Error</h1>
    <section>
      <h2>Error details:</h2>
      <p>Type: ${pageContext.exception["class"]}</p>
      <p>Message: ${pageContext.exception.message}</p>
      <h3 id="serverGoBackMsg">Get atta here. Nothin' to see here. Get a movin. Go back <a href="index.jsp">home</a> kid.</h3>
    </section>
  </main>
</div>
</body>
</html>
