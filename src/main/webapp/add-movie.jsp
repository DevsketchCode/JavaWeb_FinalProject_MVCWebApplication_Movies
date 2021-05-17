<%--
    Created By: David Oberlander
    Project: W6_Movies_doberlander
    Date/Time: 5/9/2021 9:40 PM
    Description: Add a movie to the database
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="description" content="This demonstrates my ability to be able to make a Movie Application using servlets.">
  <title>Java Web Programming: Movies Application: Add a New Movie</title>
  <%@ include file="includes/header.jsp" %>
</head>
<body>
<header>
  <h1>Java Web Programming</h1>
  <h2>Movies Application: Add a New Movie</h2>
</header>
<%@ include file="includes/navigation.jsp" %>
<main>
  <section id="formSection">
    <h3 class="message">${message}</h3>

    <div id="formContainer">
      <form action="AddMovie" method="post">

        <label for="title">Title:</label>
        <input type="text" name="title" id="title" maxlength="30">

        <label for="director">Director:</label>
        <input type="text" name="director" id="director" maxlength="30">

        <label for="releaseYear">Year Released:</label>
        <input type="text" name="releaseYear" id="releaseYear" maxlength="4">

        <label for="lengthInMinutes">Length (in minutes):</label>
        <input type="text" name="lengthInMinutes" id="lengthInMinutes" maxlength="5">

        <label for="genre">Genre:</label>
        <input type="text" name="genre" id="genre" maxlength="30">

        <label for="language">Language:</label>
        <input type="text" name="language" id="language" maxlength="30">

        <label for="description">Description:</label>
        <textarea name="description" id="description" maxlength="500"></textarea>

        <label for="movieImage">** Movie Image:</label>
        <input type="text" name="movieImage" id="movieImage" placeholder="(not required)">
        <p class="notice">** Only the filename and extension (example: image.jpg)</p>
        <p class="notice">** The file must already be in the assets/images folder.</p>

        <input type="submit">
      </form>
    </div>
  </section>
</main>
<%@ include file="includes/footer.jsp" %>
</body>
</html>