// Created by Mohit Gedela June 07/2022. Class with two methods. One method checks the file questionWords.txt with the question words and generates a random word to be used as a question. The second method checks the user input if it is an actual word and is part of the word list by checking the answerWords.txt file

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; // Imports 

public class RandomWord {

  public String[] readArray(String file) { // Method that checks the file for question words and returns a random word

    int nNum = 0;

    try {

      Scanner s1 = new Scanner(new File(file));

      while (s1.hasNextLine()) {
        nNum = nNum + 1;
        s1.next();
      } // Checks every line in the file

      String[] sWords = new String[nNum]; // Creates an array with the words
      Scanner s2 = new Scanner(new File(file));

      for (int i = 0; i < nNum; i = i + 1) {
        sWords[i] = s2.next();
      } // Puts values into the array

      return sWords;
    } catch (FileNotFoundException e) {
    }

    return null;
  }

  public static boolean realWord(String sWord) throws IOException { // Method to check if the user input is a real word

    int nCount = 0;
    String s1;
    String[] sWords = null; // Array

    File f1 = new File("answerWords.txt");
    FileReader fr = new FileReader(f1);
    BufferedReader br = new BufferedReader(fr);

    while ((s1 = br.readLine()) != null) {

      sWords = s1.split(" ");

      for (String sWord2 : sWords) {

        if (sWord2.equalsIgnoreCase(sWord)) {
          nCount++;
        } // Checks how many times athe word appears in the file
      }
    }

    br.close();

    if (nCount != 0) { // Checks if the word exists in the file
      return true;
    } else {
      return false;
    }
  }
}