package controller;
/*
    Created By: David Oberlander
    Project: W6_Movies_doberlander
    Date/Time: 5/10/2021 12:03 PM
    Description: Movie controller for controlling the movie data from the add movie form.
*/


import com.google.common.base.Strings;
import dao.MovieDao;
import dao.MovieDaoException;
import dao.MovieDaoImpl;
import model.Movie;
//import utility.WorkbookUtility;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(name = "AddMovieController", urlPatterns = "/AddMovie")
public class AddMovieController extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // Get the information submitted by the user
    try {
      final String title = request.getParameter("title");
      final String director = request.getParameter("director");
      final String releaseYearString = request.getParameter("releaseYear");
      final String lengthInMinutesString = request.getParameter("lengthInMinutes");
      final String genre = request.getParameter("genre");
      final String language = request.getParameter("language");
      final String description = request.getParameter("description");

      // Image file cannot be final, as if it is invalid, then it's saved as empty.
      String movieImage = request.getParameter("movieImage");

      // initiate variables
      int releaseYear = 1900;
      int lengthInMinutes = 0;
      MovieDao movieDao = null;
      Boolean isValidData = true;
      Boolean isEmptyForm = false;
      String message = "";


      // Check to see if it is just an empty form
      if(Strings.isNullOrEmpty(title)
             && Strings.isNullOrEmpty(director)
             && Strings.isNullOrEmpty(releaseYearString)
             && Strings.isNullOrEmpty(lengthInMinutesString)
             && Strings.isNullOrEmpty(genre)
             && Strings.isNullOrEmpty(language)
             && Strings.isNullOrEmpty(description)) {

        isEmptyForm = true;
        isValidData = false;


      // Verify that the fields have values
      } else if(Strings.isNullOrEmpty(title)
      || Strings.isNullOrEmpty(director)
      || Strings.isNullOrEmpty(releaseYearString)
      || Strings.isNullOrEmpty(lengthInMinutesString)
      || Strings.isNullOrEmpty(genre)
      || Strings.isNullOrEmpty(language)
      || Strings.isNullOrEmpty(description)) {

        // User did not submit all the necessary information
        message = "You must complete all the required fields to submit the form. <br>";
        isValidData = false;

        // Set the completed message that will show in the jsp file
        request.setAttribute("message", message);

      } else {

        // Validate file exists and is an image
        File imageFile = new File("assets\\images\\" + movieImage);
        String mimetype = Files.probeContentType(imageFile.toPath());

        // If movieImage field has data, check if it is not a valid file, or is valid but not an image
        if ((!Strings.isNullOrEmpty(movieImage) && mimetype != null && !mimetype.split("/")[0].equals("image")) ||
                (!Strings.isNullOrEmpty(movieImage) && mimetype == null)) {
          message = "Invalid Image Name: The image was not saved to the database. <br>";

          // Set image to empty and continue to insert the rest of the form, since this field is optional
          movieImage = "";

        } else if (Strings.isNullOrEmpty(movieImage)) {

          // Set image to empty value, this field is optional so it is still considered valid
          movieImage = "";

        }

        // Validate that releaseYear and lengthInMinutes are positive integers
        if (releaseYearString.matches("^[0-9]*$") && lengthInMinutesString.matches("^[0-9]*$")) {

          // User submitted all necessary information, and remove any leading zeros
          releaseYear = Integer.parseInt(releaseYearString.replaceFirst("^0+(?!$)", ""));
          lengthInMinutes = Integer.parseInt(lengthInMinutesString.replaceFirst("^0+(?!$)", ""));

        } else {
          message += "The year released and the length of the movie MUST be numbers. <br>";
          isValidData = false;
        }
      }

      // If valid data, then insert into database.  Either way provide appropriate message
      if (isValidData) {

        // Create the move DAO implementation
        movieDao = new MovieDaoImpl();

        // Create a movie object using the submitted info
        final Movie movies = new Movie(title, director, releaseYear, lengthInMinutes, genre, language, description, movieImage);

        // Insert the movie object into the DB using movieDAO
        movieDao.insertMovie(movies);
        // Set successful message
        message += "The movie was successfully added to the database.";

      } else {
        if (!isEmptyForm) {
          message += "The movie was not added to the database.";
        }
      }

      // Set the completed message that will show in the jsp file
      request.setAttribute("message", message);

    } catch (MovieDaoException e) {
      e.printStackTrace();
      request.setAttribute("message", e.getMessage());
    }

    getServletContext().getRequestDispatcher("/add-movie.jsp").forward(request, response);

  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }


}
