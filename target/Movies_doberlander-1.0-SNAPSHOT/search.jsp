<%--
    Created By: David Oberlander
    Project: Movies_doberlander
    Date/Time: 5/3/2021 10:00 PM
    Description: Search page to search the movies list
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="description" content="This demonstrates my ability to be able to make a Movie Application using servlets.">
  <title>Java Web Programming: Movies Application: Search</title>
  <%@ include file="includes/header.jsp" %>
</head>
<body>
<header>
  <h1>Java Web Programming</h1>
  <h2>Movies Application: Search</h2>
</header>
<%@ include file="includes/navigation.jsp" %>
<main>

  <section id="formSection">
    <h2>SEARCH BY</h2>

    <form action="Search" method="get">
      <div id="formContainer">
        <label for="title">Search by title:</label>
        <input type="text" name="title" id="title" maxlength="30">

        <h3>And/Or</h3>

        <label for="director">Search by director:</label>
        <input type="text" name="director" id="director" maxlength="30">

        <input type="submit">
      </div>
    </form>
  </section>
</main>
<%@ include file="includes/footer.jsp" %>
</body>
</html>