/*
  Authors (group members): Yash Jani, Khurram Valiyev, Vincent Frogale, Sam Kaguma
  Email addresses of group members: yjani2023@my.fit.edu, vfrogale2023@my.fit.edu
  Group name: groupName

  Course: CSE 2010
  Section: 4

  Description of the overall algorithm:
*/

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HangmanPlayer {
    // Array that will store every word in the word list.
    private final String[][] masterWordMatrix = new String[23][];

    // Linked List that will be used to hold all possible words for a given guess.
    private final LinkedList<String> currentPossibleWords = new LinkedList<String>();

    // Holds all the letters already guessed that were incorrect.
    private final HashSet<Character> alreadyGuessedLetters = new HashSet<Character>();

    // Holds the length of the current hidden string
    private int hiddenLength;

    // Holds the letters that have already been guessed and known to be incorrect.
    private StringBuilder incorrectGuessedLetters = new StringBuilder();

    // First guess
    private char[] firstGuess = { 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'e', 'e', 'e', 'i', 'i', 'i', 'i', 'i', 'i', 'i',
            'o', 'o', 'o', 'o', 'a', 'e' };

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
        masterWordMatrix[8] = new String[30867];
        masterWordMatrix[9] = new String[26011];
        masterWordMatrix[10] = new String[20460];
        masterWordMatrix[11] = new String[14938];
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
        final int[] addIndex = new int[23];

        final Scanner wordFileInput = new Scanner(new File(wordFile));

        while (wordFileInput.hasNextLine()) {

            // Finds the length of the next word in the list.
            final String newWord = wordFileInput.nextLine();
            final int newWordLength = newWord.length();

            // Adds the word the the corresponding spot in the array if it is at least two
            // letter long.
            if (newWordLength >= 2) {
                masterWordMatrix[newWordLength - 2][addIndex[newWordLength - 2]] = newWord;
                addIndex[newWordLength - 2] += 1;
            }
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
        if (isNewWord) {
            hiddenLength = currentWord.length();

            // Make the linkedlist

            return firstGuess[hiddenLength - 2];
        }

        // Create a linked list iterator
        Iterator<String> iterator = currentPossibleWords.iterator();
        int count = 0;

        int[] letterCount = new int[26];

        while (iterator.hasNext()) {
            String word = iterator.next();
            count++;

            for (int i = 0; i < hiddenLength; i++) {
                int letter = word.charAt(i) - 'a';
                letterCount[letter]++;
            }
        }

        // Creates the String Builder that will be used to create the Regex Pattern.
        StringBuilder currentWordBuilder = new StringBuilder(currentWord);

        for (int i = 0; i < hiddenLength; i++) {
            if (currentWordBuilder.charAt(i) == ' ') {
                currentWordBuilder.setCharAt(i, '.');
            }
        }

        // Creates the regex pattern that will be used to check each word in the list.
        String regexString = "^(?!*[" + incorrectGuessedLetters + "])." + currentWordBuilder + "$";

        Matcher matcher = Pattern.compile(regexString).matcher("");

        char guess = ' ';
        int max = 0;
        for (int i = 0; i < 26; i++) {
            if (letterCount[i] > max) {
                max = letterCount[i];
                guess = (char) (i + 'a');
            }
        }

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
