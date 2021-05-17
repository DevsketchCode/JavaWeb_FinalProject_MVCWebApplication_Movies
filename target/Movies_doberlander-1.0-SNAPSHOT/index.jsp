<%--
    Created By: David Oberlander
    Project: Movies_doberlander
    Date/Time: 4/25/2021 10:42 PM
    Description: Home page for the Movies Java Web Application.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="description" content="This demonstrates my ability to be able to make a Movie Application using servlets.">
  <title>Java Web Programming: Movies Application</title>
  <%@ include file="includes/header.jsp" %>
</head>
<body>
  <header>
    <h1>Java Web Programming</h1>
    <h2>Movies Application</h2>
  </header>
  <%@ include file="includes/navigation.jsp" %>
  <main>
    <section id="welcome">
      <p>Welcome to my Java Web Programming application.</p>
      <p>This is an assignment for multiple modules including.  We are demonstrating our use of servlets in a MVC pattern. And populating records into an Excel sheet or Sqlite database.</p>
      <p>Feel free to click on the movies list to view a list of movies that is populated from an Excel sheet that contains the details of each movie. The application than adds creates each row into a movie object and displays the results in the browser for you to enjoy.</p>
    </section>
  </main>
  <%@ include file="includes/footer.jsp" %>
</body>
</html>