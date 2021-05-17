package dao;
/*
    Created By: David Oberlander
    Project: W6_Movies_doberlander
    Date/Time: 5/10/2021 12:14 AM
    Description: Custom Exception class for movies
*/

public class MovieDaoException extends Exception {

  public MovieDaoException(final String message) {
    super(message); // pass the message to the super class (Exception)
    // This is a wrapper, gives a name that is easier to identify.
  }
}
