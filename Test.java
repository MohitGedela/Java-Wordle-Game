// Created by Mohit Gedela June 07/2022. Class with onw method that takes a guessed word and outputs a hint and keeps track of already discovered hints. Also checks if guess is correct. 

public class Test { // Test class

  public String sWord; // Variables
  public String[] arsHint = new String[6];
  public char[] arcAlphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
  char[] arcGreen = new char[26];
  char[] arcYellow = new char[26];
  char[] arcWhite = new char[26];

  int nGreenCount = 0; // Reinitializing counts
  int nYellowCount = 0;
  int nWhiteCount = 0;

  int[] GreenEmojiText = { 0xd83d, 0xdfe9 }; // Setting up emoji outputs
  String GreenEmojiString = new String(GreenEmojiText, 0, GreenEmojiText.length);

  int[] YellowEmojiText = { 0xd83d, 0xdfe8 };
  String YellowEmojiString = new String(YellowEmojiText, 0, YellowEmojiText.length);

  int[] WhiteEmojiText = { 0x2b1c };
  String WhiteEmojiString = new String(WhiteEmojiText, 0, WhiteEmojiText.length);

  int[] QuestionMarkEmojiText = { 0x2754 };
  String QuestionMarkEmojiString = new String(QuestionMarkEmojiText, 0, QuestionMarkEmojiText.length);

  public Test(String sPassWord) { // Passing the correct word from main
    sWord = sPassWord; // Initializing variables
  }

  public boolean Check(String sGuess, Boolean bCorrect, boolean bLooped) { // Method to check and create hint based on guess and correct answer

    boolean bGreenLetter; // Method only variables that can be reset
    boolean bYellowLetter;
    boolean bWhiteLetter;

    boolean bGreenRepeat;
    boolean bYellowRepeat;
    boolean bWhiteRepeat;

    int nGreenFound;
    int nGuessedLetterCount;

    bGreenRepeat = false; // Reinitializing
    bYellowRepeat = false;
    bWhiteRepeat = false;

    if (bLooped == true) { // Reinitializing arrays if progra has been looped
      arcGreen = new char[26];
      arcYellow = new char[26];
      arcWhite = new char[26];
    }

    for (int i = 0; i < 5; i++) { // Checking each char in guess
      if ((sGuess.charAt(i)) == (sWord.charAt(i))) { // If the correct letter is in the correct space, set the hint as green
        bGreenRepeat = false; // Reinitializing boolean per green
        arsHint[i] = GreenEmojiString;
        for (int j = 0; j < nGreenCount; j++) { // If this green has already been found, remember that
          if (sGuess.charAt(i) == arcGreen[j]) {
            bGreenRepeat = true;
          }
        }
        if (bGreenRepeat == false) { // If this green hasn't been found before, add it to the list of found greens
          arcGreen[nGreenCount] = sGuess.charAt(i);
          nGreenCount++;
        }
      }

      if (sWord.indexOf(sGuess.charAt(i)) >= 0 && (sGuess.charAt(i)) != (sWord.charAt(i))) { // If the guessed letter is in the correct word but in a different spot
        nGreenFound = 0; // Resetting variables for each potetial yellow
        bYellowRepeat = false;
        nGuessedLetterCount = 0;

        for (int j = 0; j < nYellowCount; j++) { // Checking if this was already found to be yellow
          if (sGuess.charAt(i) == arcYellow[j]) {
            bYellowRepeat = true;
          }
        }
        for (int j = 0; j < 5; j++) { // Checking if in this guess they have or will find the same letter as green
          if ((sGuess.charAt(j)) == (sWord.charAt(j)) && sGuess.charAt(i) == sGuess.charAt(j)) {
            nGreenFound++; // Count how many times that letter was found as green
          }
          if (sGuess.charAt(i) == sWord.charAt(j)) {
            nGuessedLetterCount++; // Count how many times that letter shows up in the correct word
          }
        }
        if (nGreenFound != nGuessedLetterCount) { // If there are more unfound instances of that letter, print yellow
          arsHint[i] = YellowEmojiString;
        } else { // IF all instances of that letter have been found already, print white
          arsHint[i] = WhiteEmojiString;
        }
        if (bYellowRepeat == false) { // If this is the first time this yellow has been found, add it to the letter hint
          arcYellow[nYellowCount] = sGuess.charAt(i);
          nYellowCount++;
        }
      }

      if ((sGuess.charAt(i)) != (sWord.charAt(i))) { // If this is not the right letter in the right spot
        bWhiteRepeat = false; // Resetting boolean
        if (sWord.indexOf(sGuess.charAt(i)) < 0) { // If this letter is nowhere in the correct word
          arsHint[i] = WhiteEmojiString; // Set the hint as white
          for (int j = 0; j < nWhiteCount; j++) { // Check if this white has been found before
            if (sGuess.charAt(i) == arcWhite[j]) {
              bWhiteRepeat = true;
            }
          }
          if (bWhiteRepeat == false) { // If not, add it to the letter hint
            arcWhite[nWhiteCount] = sGuess.charAt(i);
            nWhiteCount++;
          }
        }
      }

      if (arsHint[i] == null) { // Just in case a letter somehow meets none of the conditions (which is probably impossible) set it to white
        arsHint[i] = WhiteEmojiString;
      }
      
      System.out.print(arsHint[i]); // Printing the hint
    }

    System.out.print("\n");

    for (int a = 0; a < 26; a++) { // Checking each letter
      bGreenLetter = false; // Resetting booleans
      bYellowLetter = false;
      bWhiteLetter = false;

      for (int b = 0; b < 26; b++) { // Checking if letter was found to be green
        if (arcGreen[b] == arcAlphabet[a]) {
          bGreenLetter = true;
          break;
        }
      }

      for (int b = 0; b < 26; b++) { // Checking if letter was found to be yellow or white
        if (arcYellow[b] == arcAlphabet[a] && bGreenLetter == false) { // If yellow was later turned green don't set as yellow
          bYellowLetter = true;
          break;
        } else if (arcWhite[b] == arcAlphabet[a]) {
          bWhiteLetter = true;
          break;
        }
      }

      if (bGreenLetter == true) { // Print letter hint based on what the letter was found as
        System.out.print(arcAlphabet[a] + GreenEmojiString + " ");
      } else if (bYellowLetter == true) {
        System.out.print(arcAlphabet[a] + YellowEmojiString + " ");
      } else if (bWhiteLetter == true) {
        System.out.print(arcAlphabet[a] + WhiteEmojiString + " ");
      } else { // If letter has not been guessed yet, set hint as ?
        System.out.print(arcAlphabet[a] + QuestionMarkEmojiString + " ");
      }
    }

    System.out.println("\n\n");

    if (sGuess.equals(sWord)) { // If the guess is correct, tell main and tell main that if this method is called agin, the user must have looped the program
      bCorrect = true;
      bLooped = true;
      return bCorrect;
    } else { // If guess is incorrect, tell main
      return bCorrect;
    }
  }
}