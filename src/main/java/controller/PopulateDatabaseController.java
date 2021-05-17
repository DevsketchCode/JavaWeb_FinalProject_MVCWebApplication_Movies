package controller;
/*
    Created By: David Oberlander
    Project: W6_Movies_doberlander
    Date/Time: 5/10/2021 12:02 PM
    Description: 
*/


import dao.MovieDao;
import dao.MovieDaoException;
import dao.MovieDaoImpl;
import utility.WorkbookUtility;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PopulateDatabaseController", urlPatterns = "/PopulateDB")
public class PopulateDatabaseController extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // Get filepath of workbook
    final String filePath = getServletContext().getRealPath(WorkbookUtility.INPUT_FILE);

    // Create instance of MovieDao
    final MovieDao movieDao = new MovieDaoImpl();

    String message = "";

    // Get the parameter to see if the form has been submitted
    String populateDB = request.getParameter("populateDB");

    // Check to see if a parameter was passed, if so filter the movies to match
    if(populateDB != null && !populateDB.isEmpty()  && populateDB.equals("true")) {

      try {
        movieDao.populate(filePath);
        message = "The database was successfully populated.";

      } catch (MovieDaoException e) {
        e.printStackTrace();
        message = "There was an error, the database was not populated.";
      }

    }

    request.setAttribute("message", message);
    getServletContext().getRequestDispatcher("/populate.jsp").forward(request, response);

  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }


}
