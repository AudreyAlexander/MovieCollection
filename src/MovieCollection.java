import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;


public class MovieCollection
{
  private ArrayList<Movie> movies;
  private Scanner scanner;

  public MovieCollection(String fileName)
  {
    importMovieList(fileName);
    scanner = new Scanner(System.in);
  }

  public ArrayList<Movie> getMovies()
  {
    return movies;
  }
  
  public void menu()
  {
    String menuOption = "";
    
    System.out.println("Welcome to the movie collection!");
    System.out.println("Total: " + movies.size() + " movies");
    
    while (!menuOption.equals("q"))
    {
      System.out.println("------------ Main Menu ----------");
      System.out.println("- search (t)itles");
      System.out.println("- search (k)eywords");
      System.out.println("- search (c)ast");
      System.out.println("- see all movies of a (g)enre");
      System.out.println("- list top 50 (r)ated movies");
      System.out.println("- list top 50 (h)igest revenue movies");
      System.out.println("- (q)uit");
      System.out.print("Enter choice: ");
      menuOption = scanner.nextLine();
      
      if (!menuOption.equals("q"))
      {
        processOption(menuOption);
      }
    }
  }
  
  private void processOption(String option)
  {
    if (option.equals("t"))
    {
      searchTitles();
    }
    else if (option.equals("c"))
    {
      searchCast();
    }
    else if (option.equals("k"))
    {
      searchKeywords();
    }
    else if (option.equals("g"))
    {
      listGenres();
    }
    else if (option.equals("r"))
    {
      listHighestRated();
    }
    else if (option.equals("h"))
    {
      listHighestRevenue();
    }
    else
    {
      System.out.println("Invalid choice!");
    }
  }

  private void searchTitles()
  {
    System.out.print("Enter a title search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieTitle = movies.get(i).getTitle();
      movieTitle = movieTitle.toLowerCase();

      if (movieTitle.indexOf(searchTerm) != -1)
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    // sort the results by title
    sortResults(results);

    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void sortResults(ArrayList<Movie> listToSort)
  {
    for (int j = 1; j < listToSort.size(); j++)
    {
      Movie temp = listToSort.get(j);
      String tempTitle = temp.getTitle();

      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
      {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }
  
  private void displayMovieInfo(Movie movie)
  {
    System.out.println();
    System.out.println("Title: " + movie.getTitle());
    System.out.println("Tagline: " + movie.getTagline());
    System.out.println("Runtime: " + movie.getRuntime() + " minutes");
    System.out.println("Year: " + movie.getYear());
    System.out.println("Directed by: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Overview: " + movie.getOverview());
    System.out.println("User rating: " + movie.getUserRating());
    System.out.println("Box office revenue: " + movie.getRevenue());
  }
  
  private void searchCast() {
    System.out.print("Enter a person to search for: ");
    String cast = scanner.nextLine();
    cast = cast.toLowerCase();
    ArrayList<String> castResults = new ArrayList<String>();


    for (int i = 0; i < movies.size(); i++) {
      String castMembers = movies.get(i).getCast();
      castMembers = castMembers.toLowerCase();
      String[] castMemberList = castMembers.split("\\|", 0);

      for (String person : castMemberList) {
        if (person.toLowerCase().contains(cast)) {
          int indexOfPerson = castResults.indexOf(person);
          if (indexOfPerson == -1)
              castResults.add(person);
          }
        }
      }


    Collections.sort(castResults);
    System.out.println(castResults);

    for (int k = 0; k < castResults.size(); k++) {
      int choiceNum = k + 1;
      System.out.println("" + choiceNum + ". " + castResults.get(k));
    }

    System.out.println("Which person would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    String selectedCast = castResults.get(choice - 1);

    ArrayList<Movie> newMovie = new ArrayList<Movie>();
    for (int m = 0; m < movies.size(); m++) {
      String actors = movies.get(m).getCast();
      actors = actors.toLowerCase();
      String[] actorList = actors.split("\\|", 0);

      for (String actor : actorList) {
        if (actor.toLowerCase().equals(selectedCast)) {
          newMovie.add(movies.get(m));
        }
      }
    }
    for (int o = 0; o < newMovie.size(); o++) {
      int choiceNum = o + 1;
      System.out.println("" + choiceNum + ". " + newMovie.get(o).getTitle());
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");


    int num = scanner.nextInt();
    scanner.nextLine();

    Movie movChoice = newMovie.get(num - 1);
    displayMovieInfo(movChoice);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

    private void searchKeywords ()
    {
      System.out.print("Enter a keyword search term: ");
      String searchTerm = scanner.nextLine();

      searchTerm = searchTerm.toLowerCase();

      ArrayList<Movie> results = new ArrayList<Movie>();

      for (int i = 0; i < movies.size(); i++) {
        String keywords = movies.get(i).getKeywords();
        keywords = keywords.toLowerCase();

        if (keywords.indexOf(searchTerm) != -1) {
          results.add(movies.get(i));
        }
      }
      // sort the results by title
      sortResults(results);

      // now, display them all to the user
      for (int i = 0; i < results.size(); i++) {
        String title = results.get(i).getTitle();

        // this will print index 0 as choice 1 in the results list; better for user!
        int choiceNum = i + 1;

        System.out.println("" + choiceNum + ". " + title);
      }

      System.out.println("Which movie would you like to learn more about?");
      System.out.print("Enter number: ");

      int choice = scanner.nextInt();
      scanner.nextLine();

      Movie selectedMovie = results.get(choice - 1);

      displayMovieInfo(selectedMovie);

      System.out.println("\n ** Press Enter to Return to Main Menu **");
      scanner.nextLine();

    }

    private void listGenres ()
    {
      ArrayList<String> genres = new ArrayList<String>();
      for (int i = 0; i < movies.size(); i++) {
        String gns = movies.get(i).getGenres();
        String [] genreList = gns.split("\\|", 0);

        for (String genre : genreList){
          int indexOfGenre = genres.indexOf(genre);
          if (indexOfGenre == -1){
            genres.add(genre);
          }
        }
      }
      Collections.sort(genres);

      for (int j = 0; j < genres.size(); j++){
        int choiceNum = j + 1;
        System.out.println("" + choiceNum + ". " + genres.get(j));
      }

      System.out.println("Which genre would you like to learn more about?");
      System.out.print("Enter number: ");

      int num = scanner.nextInt();
      scanner.nextLine();

      String selectedGenre = genres.get(num - 1);

      ArrayList<Movie> moviesForGenre = new ArrayList<Movie>();

      for (Movie movie : movies) {
        String newGns = movie.getGenres();
        if (newGns.contains(selectedGenre)){
          int indexOfMovie = moviesForGenre.indexOf(movie);
          if (indexOfMovie == -1){
            moviesForGenre.add(movie);
          }
        }
      }

      ArrayList<String> listForSorting = new ArrayList<String>();

      for (int f = 0; f <  moviesForGenre.size(); f++){
        listForSorting.add(moviesForGenre.get(f).getTitle());
      }
      Collections.sort(listForSorting);

      for (int o = 0; o < moviesForGenre.size(); o++) {
        int choiceNum = o + 1;
        System.out.println("" + choiceNum + ". " + listForSorting.get(o));
        //System.out.println("" + choiceNum + ". " + moviesForGenre.get(o).getTitle());
      }

      System.out.println("\n ** Press Enter to Return to Main Menu **");
      scanner.nextLine();

    }

    private void listHighestRated ()
    {
      System.out.println("Not in service");
    }

    private void listHighestRevenue ()
    {
      System.out.println("Not in service");
    }

  private void importMovieList(String fileName)
  {
    try
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();

      movies = new ArrayList<Movie>();

      while ((line = bufferedReader.readLine()) != null)
      {

        String[] movieFromCSV = line.split(",");

        String title = movieFromCSV[0];
        String cast = movieFromCSV[1];
        String director = movieFromCSV[2];
        String tagline = movieFromCSV[3];
        String keywords = movieFromCSV[4];
        String overview = movieFromCSV[5];
        int runtime = Integer.parseInt(movieFromCSV[6]);
        String genres = movieFromCSV[7];
        double userRating = Double.parseDouble(movieFromCSV[8]);
        int year = Integer.parseInt(movieFromCSV[9]);
        int revenue = Integer.parseInt(movieFromCSV[10]);

        Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
        movies.add(nextMovie);
      }
      bufferedReader.close();
    }
    catch(IOException exception)
    {
      // Print out the exception that occurred
      System.out.println("Unable to access " + exception.getMessage());
    }
  }


}