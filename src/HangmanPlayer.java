/*

  Authors (group members): Yash Jani, Khurram Valiyev, 
  Email addresses of group members:
  Group name: groupName

  Course: CSE 2010
  Section: 4

  Description of the overall algorithm:


*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HangmanPlayer {

    // initialize HangmanPlayer with a file of English words
    // Pre-processing the word file that contains a list of English words in each line
    // Array of lists 
    public HangmanPlayer(String wordFile) throws FileNotFoundException {
        final Scanner wordFileInput = new Scanner(new File(wordFile));
    }

    // based on the current (partial or intitially blank) word
    //    guess a letter
    // currentWord: current word, currenWord.length has the length of the hidden word
    // isNewWord: indicates a new hidden word
    // returns the guessed letter
    // assume all letters are in lower case
    public char guess(String currentWord, boolean isNewWord) {
        char guess = ' ';

        return guess;
    }

    // feedback on the guessed letter
    // isCorrectGuess: true if the guessed letter is one of the letters in the hidden word
    // currentWord: partially filled or blank word
    //   
    // Case       isCorrectGuess      currentWord   
    // a.         true                partial word with the guessed letter
    //                                   or the whole word if the guessed letter was the
    //                                   last letter needed
    // b.         false               partial word without the guessed letter
    public void feedback(boolean isCorrectGuess, String currentWord) {

    }

}
