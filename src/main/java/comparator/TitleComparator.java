package comparator;
/*
    Created By: David Oberlander
    Project: W5_Movies_doberlander
    Date/Time: 5/2/2021 6:19 PM
    Description: Used to sort the movies by title, as well as handle 404 and exceptions
*/

import model.Movie;

import java.util.Comparator;

public class TitleComparator implements Comparator<Movie> {
  // Compare returns an int, if 1 > 2 than 1, if 1 < 2 than -1, if 1 = 2 than 0
  @Override
  public int compare(Movie movie1, Movie movie2) {
    return movie1.getTitle().compareTo(movie2.getTitle());
  }
}
