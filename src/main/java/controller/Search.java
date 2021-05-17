package controller;
/*
    Created By: David Oberlander
    Project: W5_Movies_doberlander
    Date/Time: 5/3/2021 10:04 PM
    Description: 
*/


import dao.MovieDao;
import dao.MovieDaoException;
import dao.MovieDaoImpl;
import model.Movie;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import utility.WorkbookUtility;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@WebServlet(name = "SearchServlet", urlPatterns = "/Search")
public class Search extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // get access to the spreadsheet
    //final String filePath = getServletContext().getRealPath(WorkbookUtility.INPUT_FILE);
    //final File inputFile = new File(filePath);

    // Set default path to the search page
    String target = "/view-all-movies.jsp";

    // get the list of movies
    try {
      // Used for retrieving records from an Excel Workbook
      //final List<Movie> movies = WorkbookUtility.retrieveMoviesFromWorkbook(inputFile);


      // Used to retrieve records from database
      final MovieDao movieDao = new MovieDaoImpl();
      final List<Movie> movies = movieDao.retrieveMovie();

      // Get the parameter that was passed, if any
      String filterTitleString = request.getParameter("title");
      String filterDirectorString = request.getParameter("director");

      // Check to see if a parameter was passed, if so filter the movies to match
      // If both title and director had values
      if((filterTitleString != null && !filterTitleString.isEmpty()) &&
             (filterDirectorString != null && !filterDirectorString.isEmpty())) {

        // filter the list by title or director
        final List<Movie> filteredList = movies.stream()
                                             .filter((Movie m) -> m.getTitle().toLowerCase().contains(filterTitleString.toLowerCase()) &&
                                             m.getDirector().toLowerCase().contains(filterDirectorString.toLowerCase()))
                                             .collect(Collectors.toList());

        // attach the list (model) to the request
        request.setAttribute("movies", filteredList);

      } else if((filterTitleString != null && !filterTitleString.isEmpty()) && (filterDirectorString == null || filterDirectorString.isEmpty())) {
        // filter the list by title
        final List<Movie> filteredList = movies.stream()
                                             .filter((Movie m) -> m.getTitle().toLowerCase().contains(filterTitleString.toLowerCase()))
                                             .collect(Collectors.toList());

        // attach the list (model) to the request
        request.setAttribute("movies", filteredList);

      } else if((filterTitleString == null || filterTitleString.isEmpty()) && (filterDirectorString != null && !filterDirectorString.isEmpty())) {
        // filter the list by director
        final List<Movie> filteredList = movies.stream()
                                             .filter((Movie m) -> m.getDirector().toLowerCase().contains(filterDirectorString.toLowerCase()))
                                             .collect(Collectors.toList());

        // attach the list (model) to the request
        request.setAttribute("movies", filteredList);

      } else {
        // change the target to return back to the search page
        target = "/search.jsp";
      }

    } catch (MovieDaoException e) {
      e.printStackTrace();
    }

    // forward the request (to the view)
    getServletContext().getRequestDispatcher(target).forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
