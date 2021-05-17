package dao;
/*
    Created By: David Oberlander
    Project: W6_Movies_doberlander
    Date/Time: 5/10/2021 12:14 AM
    Description: Movie interface
*/

import model.Movie;

import java.util.List;

public interface MovieDao {

  void populate(String filePath) throws MovieDaoException; // methods close with semicolon in interface because they do not contain implementation code.

  List<Movie> retrieveMovie() throws MovieDaoException;

  void insertMovie(Movie movie) throws MovieDaoException;

}
