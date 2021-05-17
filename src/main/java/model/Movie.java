package model;
/*
    Created By: David Oberlander
    Project: Movies_doberlander
    Date/Time: 4/25/2021 4:40 PM
    Description: The Movie Class (JavaBean)
*/

import java.io.Serializable;

public class Movie implements Serializable {
  // Declare variables
  private Integer movieID;
  private String title;
  private String director;
  private Integer releaseYear;
  private Integer lengthInMinutes;
  private String genre;
  private String language;
  private String description;
  private String movieImage;

  // default constructor
  public Movie() {
  }

  // overloaded constructor

  public Movie(String title, String director, Integer releaseYear, Integer lengthInMinutes, String genre, String language, String description, String movieImage) {
    this.title = title;
    this.director = director;
    this.releaseYear = releaseYear;
    this.lengthInMinutes = lengthInMinutes;
    this.genre = genre;
    this.language = language;
    this.description = description;
    this.movieImage = movieImage;
  }

  // overloaded constructor including movieID
  public Movie(Integer movieID, String title, String director, Integer releaseYear, Integer lengthInMinutes, String genre, String language, String description, String movieImage) {
    this.title = title;
    this.director = director;
    this.releaseYear = releaseYear;
    this.lengthInMinutes = lengthInMinutes;
    this.genre = genre;
    this.language = language;
    this.description = description;
    this.movieImage = movieImage;
  }


  // getters and setters

  public Integer getMovieID() { return movieID;  }

  public void setMovieID(Integer movieID) { this.movieID = movieID; }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public Integer getReleaseYear() {
    return releaseYear;
  }

  public void setReleaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
  }

  public Integer getLengthInMinutes() {
    return lengthInMinutes;
  }

  public void setLengthInMinutes(Integer lengthInMinutes) {
    this.lengthInMinutes = lengthInMinutes;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getMovieImage() { return movieImage; }

  public void setMovieImage(String movieImage) { this.movieImage = movieImage; }

  @Override
  public String toString() {
    return "Movie{" +
        "title='" + title + '\'' +
        ", director='" + director + '\'' +
        ", releaseYear=" + releaseYear +
        ", lengthInMinutes=" + lengthInMinutes +
        ", genre='" + genre + "\'" +
        ", language='" + language + '\'' +
        ", description='" + description + '\'' +
        ", movieImage='" + movieImage + '\'' +
        '}';
  }
}
