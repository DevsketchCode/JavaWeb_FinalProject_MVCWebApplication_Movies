var MovieDetails = {

  init: function() {
    // Get the list of movieDetails divs displayed
    let movieCardDivs = document.querySelectorAll("div.movieCard");

    for (let i = 0; i < movieCardDivs.length; i++) {
      // Set a click listener to show or hide the movie details
      movieCardDivs[i].addEventListener("click", MovieDetails.toggleDetails, false);
    }

  }
}

MovieDetails.toggleDetails = function() {

  let movieDetails = this.querySelector(".movieDetails").style;
  let movieDetailsToggleButton = this.querySelector(".seeDetailsLink");

  // If the movieDetails div is showing than hide it, else show it
  if (movieDetails.display === "block") {
    movieDetails.display = "none";
    movieDetailsToggleButton.innerHTML = "Show Details";


  } else {
    movieDetails.display = "block";
    movieDetailsToggleButton.innerHTML = "Hide Details";
  }
}

window.onload = MovieDetails.init;