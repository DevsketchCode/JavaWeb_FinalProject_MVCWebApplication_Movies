package dao;
/*
    Created By: David Oberlander
    Project: W6_Movies_doberlander
    Date/Time: 5/10/2021 12:14 AM
    Description: 
*/
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import model.Movie;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import utility.DBUtility;
import utility.WorkbookUtility;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements MovieDao {

  // SQL Statements that do not have to be dynamic
  final static String DROP_TABLE = "drop table if exists movie;"; // if the table "movie" exists, then delete it.
  final static String CREATE_TABLE = "create table movie (id integer primary key autoincrement, title text, director text, releaseYear integer, lengthInMinutes integer, genre text, language text, description text, movieImage text);";

  final static String SELECT_ALL_FROM_MOVIE = "select * from movie";

  @Override
  public void populate(String filePath) throws MovieDaoException {
    // create a fresh load of the database
    Connection connection = null;
    // Using a prepared statement, to allow apostrophe's in the records to transfer into the database properly.
    Statement resetDBStatement = null;
    PreparedStatement insertStatement = null;

    try {
      connection = DBUtility.createConnection();

      // Reset the database
      resetDBStatement = connection.createStatement();

      resetDBStatement.setQueryTimeout(DBUtility.TIMEOUT);

      resetDBStatement.executeUpdate(DROP_TABLE);
      resetDBStatement.executeUpdate(CREATE_TABLE);


      // populate the database
      final File inputFile = new File(filePath);
      final List<Movie> movies = WorkbookUtility.retrieveMoviesFromWorkbook(inputFile);

      // Loop through the workbook add an entry for each record
      for(final Movie movie : movies) {
        final String insertSQL = "INSERT INTO movie (title, director, releaseYear, lengthInMinutes, genre, language, description, movieImage) values (?, ?, ?, ?, ?, ?, ?, ?) ";

        insertStatement = connection.prepareStatement(insertSQL);

        // Assign the values to the placeholders in the prepared statement
        insertStatement.setString(1, movie.getTitle());
        insertStatement.setString(2, movie.getDirector());
        insertStatement.setInt(3, movie.getReleaseYear());
        insertStatement.setInt(4, movie.getLengthInMinutes());
        insertStatement.setString(5, movie.getGenre());
        insertStatement.setString(6, movie.getLanguage());
        insertStatement.setString(7, movie.getDescription());
        insertStatement.setString(8, movie.getMovieImage());

        insertStatement.setQueryTimeout(DBUtility.TIMEOUT);

        // Send the command to the records
        insertStatement.executeUpdate();
        System.out.println("Values Inserted");
        System.out.println(insertSQL); // NOTES log the sql that we executed

      }

    } catch (ClassNotFoundException | SQLException | IOException | InvalidFormatException e) {
      e.printStackTrace();
      // use our custom exception handler
      throw new MovieDaoException("Error: unable to populate the database.");
    }
  }

  @Override
  public List<Movie> retrieveMovie() throws MovieDaoException {
    // Get all the movies back from the database and create a list of movies

    final List<Movie> movies = new ArrayList<Movie>();

    Connection connection = null;
    Statement statement = null;

    try {
      connection = DBUtility.createConnection();
      statement = connection.createStatement();

      statement.setQueryTimeout(DBUtility.TIMEOUT);

      // Fetch all the records from the movie table
      final ResultSet results = statement.executeQuery(SELECT_ALL_FROM_MOVIE);

      // Loop through the results
      while(results.next()) {
        final int movieID = results.getInt("id");
        final String title = results.getString("title");
        final String director = results.getString("director");
        final int releaseYear = results.getInt("releaseYear");
        final int lengthInMinutes = results.getInt("lengthInMinutes");
        final String genre = results.getString("genre");
        final String language = results.getString("language");
        final String description = results.getString("description");
        final String movieImage = results.getString("movieImage");

        // Add a new movie object (using the constructor) to the movies list that we created earlier.
        movies.add(new Movie(movieID, title, director, releaseYear, lengthInMinutes, genre, language, description, movieImage));
      }

    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
      throw new MovieDaoException("Error: Unable to retrieve the list of movies from the database.");
    }

    // return the list
    return movies;
  }

  @Override
  public void insertMovie(Movie movie) throws MovieDaoException {
    // insert a new movie object into the database

    // Setup database connection and statement
    Connection connection = null;
    PreparedStatement insertStatement = null;

    try {
      connection = DBUtility.createConnection();

      // Use a prepared statement, this allows you to control user input to make sure they don't submit malicious data.
      final String sqlString = "insert into movie(title, director, releaseYear, lengthInMinutes, genre, language, description, movieImage) values (?, ?, ?, ?, ?, ?, ?, ?)";

      insertStatement = connection.prepareStatement(sqlString);

      // Assign values to the placeholders above
      // (NOTE: this is not an array, and it is NOT zero based)
      insertStatement.setString(1, movie.getTitle());
      insertStatement.setString(2, movie.getDirector());
      insertStatement.setInt(3, movie.getReleaseYear());
      insertStatement.setInt(4, movie.getLengthInMinutes());
      insertStatement.setString(5, movie.getGenre());
      insertStatement.setString(6, movie.getLanguage());
      insertStatement.setString(7, movie.getDescription());
      insertStatement.setString(8, movie.getMovieImage());

      insertStatement.setQueryTimeout(DBUtility.TIMEOUT);

      // Send command to insert or update a record, not a query
      insertStatement.executeUpdate();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      throw new MovieDaoException("Error: Unable to insert the movie.");
    }



  }
}
