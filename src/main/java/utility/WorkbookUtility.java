package utility;
/*
    Created By: David Oberlander
    Project: Movies_doberlander
    Date/Time: 4/25/2021 4:42 PM
    Description: Class used to access the Excel workbook
*/

import model.Movie;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkbookUtility {

  public final static String INPUT_FILE = "assets\\MovieList.xlsx";

  public static List<Movie> retrieveMoviesFromWorkbook(final File inputFile) throws IOException, InvalidFormatException {
    final List<Movie> movies = new ArrayList<>();

    // Access the workbook file
    final Workbook workbook = WorkbookFactory.create(inputFile);

    // Get the first sheet, which is indexed 0
    final Sheet sheet = workbook.getSheetAt(0);

    // Loop through the rows
    for(final Row row : sheet) {
      final Cell titleCell = row.getCell(0);
      final Cell directorCell = row.getCell(1);
      final Cell releaseYearCell = row.getCell(2);
      final Cell lengthInMinutesCell = row.getCell(3);
      final Cell genreCell = row.getCell(4);
      final Cell languageCell = row.getCell(5);
      final Cell descriptionCell = row.getCell(6);
      final Cell movieImageCell = row.getCell(7, Row.CREATE_NULL_AS_BLANK); // Display the results, else return blank


      // Get information from each of the cells
      final Movie movie = new Movie(
          titleCell.getStringCellValue().trim(),
          directorCell.getStringCellValue().trim(),
          (int)releaseYearCell.getNumericCellValue(),
          (int)lengthInMinutesCell.getNumericCellValue(),
          genreCell.getStringCellValue().trim(),
          languageCell.getStringCellValue().trim(),
          descriptionCell.getStringCellValue().trim(),
          movieImageCell.getStringCellValue().trim()
      );
      // Add the movie to the movies list object
      movies.add(movie);
    }

    // Return the populated movies list object
    return movies;
  }
}
