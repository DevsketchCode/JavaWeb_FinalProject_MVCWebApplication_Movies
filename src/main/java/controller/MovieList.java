package controller;
/*
    Created By: David Oberlander
    Project: Movies_doberlander
    Date/Time: 4/25/2021 4:41 PM
    Description: 
*/


import comparator.LengthComparator;
import comparator.TitleComparator;
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
import java.util.Collections;
import java.util.List;

@WebServlet(name = "MovieListServlet", urlPatterns = "/MovieList")
public class MovieList extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String target = "/view-all-movies.jsp";

    // get access to the spreadsheet
    //final String filePath = getServletContext().getRealPath(WorkbookUtility.INPUT_FILE);
    //final File inputFile = new File(filePath);

    try {
      //final List<Movie> movies = WorkbookUtility.retrieveMoviesFromWorkbook(inputFile);

      // Get the list of movies from the sqlite database
      final MovieDao movieDao = new MovieDaoImpl();
      final List<Movie> movies = movieDao.retrieveMovie();

      // If the parameter doesn't exist, then it returns null
      String sortType = request.getParameter("sortType");

      // sort by title
      if (null != sortType && sortType.equals("title")) {
        Collections.sort(movies, new TitleComparator());
      }

      // sort by length in minutes
      if (null != sortType && sortType.equals("length")) {
        Collections.sort(movies, new LengthComparator());
      }

      // Attach the model to the request
      // The "movies" string needs to match the EL tag in the JSP
      request.setAttribute("movies", movies);

    } catch (MovieDaoException e) {
      e.printStackTrace();
    }

    // forward the request to the view
    getServletContext().getRequestDispatcher(target).forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Redirect to the home page
    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
  }
}
