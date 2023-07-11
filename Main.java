
// created by Mohit Gedela June 07/2022. Main class that holds ui, and calls the methods in the other classes.

import java.io.IOException; // Imports
import java.util.Scanner;

class Main {
  public static void main(String[] args) throws IOException {
    Scanner Scan = new Scanner(System.in); // Variables
    boolean bCorrect;
    int nGuessCount;
    String sGuess;
    boolean bFormat;
    String sLoop;
    String sWord;
    boolean bLooped;
    int sLoopTwiceCheck;
    boolean bEnter;

    RandomWord Words = new RandomWord(); // Words class
    String[] sWordArr = Words.readArray("questionWords.txt"); // Creating an array of potential correct word using the readArray method in the Words class

    bCorrect = false; // Intializing booleans
    bLooped = false;
    bEnter = false;

    System.out.print("\033[H\033[2J"); // Clearing text

    do { // do loop that the program repaets if the user wants toplay again
      bCorrect = false; // Resetting/initalizing variables
      sLoopTwiceCheck = 0;
      nGuessCount = 0;

      System.out.println(" __          __              _         _ \n \\ \\        / /             | |       | |\n  \\ \\  /\\  / /___   _ __  __| |  ___  | |\n   \\ \\/  \\/ // _ \\ | '__|/ _` | / _ \\ | |\n    \\  /\\  /| (_) || |  | (_| || (_) ||_|\n     \\/  \\/  \\___/ |_|   \\__,_| \\___/ (_)                                         \n"); // ascii art text logo

      System.out.println("Type in a five letter guess! \n"); // Prompt

      sWord = sWordArr[(int) (Math.random() * sWordArr.length)]; // Randomly selecting the correct word from the array created from the RondomWord class
      Test Test = new Test(sWord); // Resetting the Test class per loop
      do { // Loop that repeats making the user keep guessing until they've guessed 6 times or guessed correctly
        do { // Loop that can only be broken by inputting a properly formatted guess
          bFormat = true; // Resetting boolean

          sGuess = Scan.nextLine(); // Taking the user's input
          sGuess = sGuess.toLowerCase(); // Setting it to lower case for simplicity
          
          if (sGuess.length() < 1) { // If the user hits enter before typing anything, an error will occur unless the guess is turned into null instead
            sGuess = null;
            System.out.println("Please do not press enter before typing anything.\n");
            bEnter = true;  // Variable so that there's only the relevent warning.
          }

          if (RandomWord.realWord(sGuess) == false) { // Using realWord method to check if the input is an actual word
            bFormat = false;
          }

          if (bFormat == false && bEnter == false) { // If the input is not a word, tell them to input something else
            System.out.println("That is not in the word list.\n");
          }
          bEnter = false;
        } while (bFormat == false);

        if (bLooped == true) { // Resetting boolean after one guess
          sLoopTwiceCheck++;
          if (sLoopTwiceCheck == 2) {
            bLooped = false;
          }
        }

        bCorrect = Test.Check(sGuess, bCorrect, bLooped); // Calling the Check method to create the hints and check if the guess is correct
        nGuessCount++; // Counting guesses
      } while (bCorrect == false && nGuessCount < 6); // If the user does not guess correctly in six guesses or guesses correctly, end the loop
      if (bCorrect == true) { // If the guess is correct, tell the user
        System.out.println("That's correct!");
      }
      if (bCorrect == false) { // If the user runs out of guesses, tell the the correct answer
        System.out.println("\nThe answer was: " + sWord);
      }

      bLooped = true; // If the checkmethod is called again, the user must have looped the program
      System.out.println("Would you like to play with a new word or exit? 1 = Play Again, 2 = exit."); // Prompt
      sLoop = Scan.next(); // Taking the user's prompt
      while (!sLoop.equals("2") && !sLoop.equals("1")) { // Making the user input 1 or 2 by keeping them in a loop until they do
        System.out.println("Please input 1 or 2.");
        sLoop = Scan.next();
      }
      Scan.close();
      System.out.print("\033[H\033[2J"); // Clearing text
    } while (sLoop.equals("1")); // If the user chooses to exit don't loop
    System.out.println(
        "  ____                _ \n |  _ \\              | |\n | |_) | _   _   ___ | |\n |  _ < | | | | / _ \\| |\n | |_) || |_| ||  __/|_|\n |____/  \\__, | \\___|(_)\n          __/ |         \n         |___/          "); // ascii art "Bye!"
  }
}