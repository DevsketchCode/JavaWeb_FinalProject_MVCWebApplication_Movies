package comparator;
/*
    Created By: David Oberlander
    Project: W6_Movies_doberlander
    Date/Time: 5/13/2021 12:15 AM
    Description: Compares the length of minutes to sort objects.
*/

import model.Movie;

import java.util.Comparator;

public class LengthComparator implements Comparator<Movie> {
  @Override
  public int compare(Movie m1, Movie m2) {
    return m1.getLengthInMinutes().compareTo(m2.getLengthInMinutes());
  }
}
