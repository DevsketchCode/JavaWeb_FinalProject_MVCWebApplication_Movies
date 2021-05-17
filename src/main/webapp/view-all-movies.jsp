<%--
    Created By: David Oberlander
    Project: Movies_doberlander
    Date/Time: 4/25/2021 10:42 PM
    Description: View All Movies Page for the Movies Java Web Application.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="description" content="This demonstrates my ability to be able to make a Movie Application using servlets.">
  <title>Java Web Programming: Movies Application: Movies List</title>
  <%@ include file="includes/header.jsp" %>
</head>
<body>
<header>
  <h1>Java Web Programming</h1>
  <h2>Movies Application: Movies List</h2>
</header>
<%@ include file="includes/navigation.jsp" %>
<main>
  <section>
    <c:choose>
      <c:when test="${empty movies}">
        <p>Sorry, no movies are available to display at this time.</p>
      </c:when>
      <c:otherwise>
        <c:forEach var="movie" items="${movies}">
          <div class="movieCard">
            <c:choose>
              <c:when test="${empty movie.movieImage}">
                <div class="movieHeader" style="background-image: url('assets/images/theater_red.jpg'); background-size: 100% 100%;">
                  <h2 class="movieTitle">${movie.title}</h2>
                </div>
              </c:when>
              <c:otherwise>
                <div class="movieHeader" style="background-image: url('assets/images/${movie.movieImage}');">
                </div>
              </c:otherwise>
            </c:choose>

            <button class="seeDetailsLink" id="seeDetailsLinkMovieNum${movie.movieID}">Show Details</button>

            <div class="movieDetails" id="movieNum${movie.movieID}">
              <table>
                <tr>
                  <th class="detailHeading">Title:</th>
                  <th class="detailHeading detailTitle">${movie.title}</th>
                </tr>
                <tr>
                  <td class="detailHeading">Director:</td>
                  <td class="detailText">${movie.director}</td>
                </tr>
                <tr>
                  <td class="detailHeading">Released:</td>
                  <td class="detailText">${movie.releaseYear}</td>
                </tr>
                <tr>
                  <td class="detailHeading">Length:</td>
                  <td class="detailText">${movie.lengthInMinutes}</td>
                </tr>
                <tr>
                  <td class="detailHeading">Genres:</td>
                  <td class="detailText">${movie.genre}</td>
                </tr>
                <tr>
                  <td class="detailHeading">Language:</td>
                  <td class="detailText">${movie.language}</td>
                </tr>
                <tr>
                  <td class="detailHeading">Description:</td>
                  <td></td>
                </tr>
                <tr colspan="2">
                  <td class="description" colspan="2">${movie.description}</td>
                </tr>
              </table>
            </div>
          </div>
        </c:forEach>
      </c:otherwise>
    </c:choose>
  </section>
</main>
<%@ include file="includes/footer.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="assets/js/scripts.js"></script>
</body>
</html>