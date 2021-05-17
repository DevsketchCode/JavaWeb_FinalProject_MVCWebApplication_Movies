<%--
    Created By: David Oberlander
    Project: W6_Movies_doberlander
    Date/Time: 5/9/2021 9:40 PM
    Description: Reset the database to the records in the Excel File
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="description" content="This demonstrates my ability to be able to make a Movie Application using servlets.">
  <title>Java Web Programming: Movies Application: Populate DB</title>
  <%@ include file="includes/header.jsp" %>
</head>
<body>
<header>
  <h1>Java Web Programming</h1>
  <h2>Movies Application: Populate DB</h2>
</header>
<%@ include file="includes/navigation.jsp" %>
<main>
  <section id="formSection">
    <h3 class="message">WARNING: This action will overwrite the existing database with the records from the Excel file.</h3>

      <form action="PopulateDB" method="post">
        <div id="formContainer">
          <input type="hidden" name="populateDB" value="true">
          <input type="submit" value="Populate DB">
        </div>
      </form>


    <p>${message}</p>
  </section>
</main>
<%@ include file="includes/footer.jsp" %>
</body>
</html>