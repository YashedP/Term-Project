/*
  Authors (group members): Yash Jani, Khurram Valiyev, Vincent Frogale 
  Email addresses of group members: vfrogale2023@my.fit.edu
  Group name: groupName

  Course: CSE 2010
  Section: 4

  Description of the overall algorithm:


*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HangmanPlayer {

  // Array that will store every word in the word list.
  private final String[][] masterWordMatrix = new String[23][];

  // initialize HangmanPlayer with a file of English words
  // Pre-processing the word file that contains a list of English words in each
  // line
  // Array of lists
  public HangmanPlayer(String wordFile) throws FileNotFoundException {

    // Creates the size of array for each length of word
    masterWordMatrix[0] = new String[155];
    masterWordMatrix[1] = new String[1351];
    masterWordMatrix[2] = new String[5110];
    masterWordMatrix[3] = new String[9987];
    masterWordMatrix[4] = new String[17477];
    masterWordMatrix[5] = new String[23734];
    masterWordMatrix[6] = new String[29926];
    masterWordMatrix[7] = new String[32380];
    masterWordMatrix[8] = new String[3086];
    masterWordMatrix[9] = new String[2601];
    masterWordMatrix[10] = new String[2064];
    masterWordMatrix[11] = new String[1493];
    masterWordMatrix[12] = new String[9762];
    masterWordMatrix[13] = new String[5924];
    masterWordMatrix[14] = new String[3377];
    masterWordMatrix[15] = new String[1813];
    masterWordMatrix[16] = new String[842];
    masterWordMatrix[17] = new String[428];
    masterWordMatrix[18] = new String[198];
    masterWordMatrix[19] = new String[82];
    masterWordMatrix[20] = new String[41];
    masterWordMatrix[21] = new String[17];
    masterWordMatrix[22] = new String[5];

    // Array that holds the current index to add each new word to the
    // masterWordMatrix.
    final int[] addIndex = new int[26];

    final Scanner wordFileInput = new Scanner(new File(wordFile));

    while (wordFileInput.hasNextLine()) {

      final String newWord = wordFileInput.nextLine();
      final int newWordLength = newWord.length();
      masterWordMatrix[newWordLength][addIndex[newWordLength]] = newWord;
      addIndex[newWordLength] += 1;
    }
  }

  // based on the current (partial or intitially blank) word
  // guess a letter
  // currentWord: current word, currenWord.length has the length of the hidden
  // word
  // isNewWord: indicates a new hidden word
  // returns the guessed letter
  // assume all letters are in lower case
  public char guess(String currentWord, boolean isNewWord) {
    char guess = ' ';

    return guess;
  }

  // feedback on the guessed letter
  // isCorrectGuess: true if the guessed letter is one of the letters in the
  // hidden word
  // currentWord: partially filled or blank word
  //
  // Case isCorrectGuess currentWord
  // a. true partial word with the guessed letter
  // or the whole word if the guessed letter was the
  // last letter needed
  // b. false partial word without the guessed letter
  public void feedback(boolean isCorrectGuess, String currentWord) {

  }

}
