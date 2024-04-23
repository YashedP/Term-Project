/*
  Authors (group members): Yash Jani, Khurram Valiyev, Vincent Frogale, Sam Kaguma
  Email addresses of group members: yjani2023@my.fit.edu, vfrogale2023@my.fit.edu
  Group name: groupName

  Course: CSE 2010
  Section: 4

  Description of the overall algorithm:
*/

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HangmanPlayer {
    // Array that will store every word in the word list.
    private final String[][] masterWordMatrix = new String[23][];

    // Linked List that will be used to hold all possible words for a given guess.
    // private final ArrayList<String> currentPossibleWords = new ArrayList<String>();

    // Holds the length of the current hidden string
    private int hiddenLength;

    private boolean[] possibleWords = new boolean[32380];

    // Holds the letters that have already been guessed and known to be incorrect.
    private StringBuilder incorrectGuessedLetters = new StringBuilder();

    // Holds the letters that have already been guessed and known to be correct.
    private StringBuilder correctGuessedLetters = new StringBuilder();

    // Holds the previous guess
    private char previousGuess = ' ';

    // Keeps track what characters have been found in the current word being checked
    // so far
    private HashSet<Integer> characterChecked = new HashSet<Integer>();

    // First guess
    private char[] firstGuess = { 'a', 'a', 'e', 'a', 'e', 'e', 'e', 'e', 'e', 'e', 'i', 'i', 'i', 'i', 'i', 'i', 'i',
            'o', 'o', 'o', 'o', 'o', 'a' };

    // initialize HangmanPlayer with a file of English words
    // Pre-processing the word file that contains a list of English words in each
    // line
    // Array of lists
    public HangmanPlayer(String wordFile) throws IOException {

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

        try (BufferedReader wordFileInput = new BufferedReader(new FileReader(wordFile))) {
            String newWord;
            while ((newWord = wordFileInput.readLine()) != null) {
                final int newWordLength = newWord.length();

                // Adds the word the the corresponding spot in the array if it is at least two
                // letters long.
                if (newWordLength >= 2) {
                    // Changes any word that is capitalized and makes it lowercase
                    if (Character.isUpperCase(newWord.charAt(0))) {
                        newWord = newWord.substring(0, 1).toLowerCase() + newWord.substring(1);
                    }

                    // Adds the word to the array and increments the index
                    masterWordMatrix[newWordLength - 2][addIndex[newWordLength - 2]] = newWord;
                    addIndex[newWordLength - 2] += 1;
                }
            }
        }
    }

    // based on the current (partial or initially blank) word
    // guess a letter
    // currentWord: current word, currentWord.length has the length of the hidden
    // word
    // isNewWord: indicates a new hidden word
    // returns the guessed letter
    // assume all letters are in lower case
    public char guess(String currentWord, boolean isNewWord) {
        if (isNewWord) {
            hiddenLength = currentWord.length();

            // Clears the list from the previous guessed word and add all the words of the
            // same length the list
            for (int i = 0; i < masterWordMatrix[hiddenLength - 2].length; i++) {
                possibleWords[i] = true;
            }

            // Resets the incorrectGuessedLetters and correctGuessedLetters
            incorrectGuessedLetters.setLength(0);
            correctGuessedLetters.setLength(0);
            characterChecked.clear();

            // Returns the first guess
            previousGuess = firstGuess[hiddenLength - 2];
            return firstGuess[hiddenLength - 2];
        }

        // Creates the String Builder that will be used to create the Regex Pattern.
        StringBuilder currentWordBuilder = new StringBuilder(currentWord);

        for (int i = 0; i < hiddenLength; i++) {
            if (currentWordBuilder.charAt(i) == ' ') {
                currentWordBuilder.setCharAt(i, '.');
            } else {
                correctGuessedLetters.append(currentWordBuilder.charAt(i));
            }
        }

        int[] letterCount = new int[26];
        
        matchesPreq(currentWord);

        for (int i = 0; i < masterWordMatrix[hiddenLength - 2].length; i++) {
            if (!possibleWords[i]) {
                continue;
            }

            if (matches(masterWordMatrix[hiddenLength - 2][i], currentWord)) {
                characterChecked.clear();
                for (int j = 0; j < hiddenLength; j++) {
                    if (currentWordBuilder.charAt(j) == '.') {
                        int letter = masterWordMatrix[hiddenLength - 2][i].charAt(j) - 'a';
                        if (characterChecked.add(letter)) {
                            letterCount[letter]++;
                        }
                    }
                }
            } else {
                possibleWords[i] = false;
            }
        }

        ArrayList<Character> guesses = new ArrayList<>(); // In case of a tie

        // Finds the largest element in the array (letter with highest count) and
        // guesses that letter.
        int max = 0;

        letterLoop: for (int i = 0; i < 26; i++) {
            for (int j = 0; j < incorrectGuessedLetters.length(); j++) {
                if (i == incorrectGuessedLetters.charAt(j) - 'a') {
                    continue letterLoop;
                }
            }

            for (int j = 0; j < correctGuessedLetters.length(); j++) {
                if (i == correctGuessedLetters.charAt(j) - 'a') {
                    continue letterLoop;
                }
            }

            if (letterCount[i] > max) {
                max = letterCount[i];
                guesses.clear();
                guesses.add((char) (i + 'a'));
            } else if (letterCount[i] == max) {
                guesses.add((char) (i + 'a'));
            }
        }

        // In the case ofa tie where 2 or more letters have the same max count, guess will be done on the letter frequency of the English language.
        String letterFrequency = "eariotnslcudpmhgbfywkvxzjq";
        char guess = ' ';
        if (guesses.size() > 0) {
            for (char c : letterFrequency.toCharArray()) {
                if (guesses.contains(c)) {
                    guess = c;
                    break;
                }
            }
        }
        // Need to save the guess so it can be used in the feedback method.
        previousGuess = guess;
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

        // Update incorrectGuessedLetters using the isCorrectGuess and the previousGuess
        if (!isCorrectGuess) {
            incorrectGuessedLetters.append(previousGuess);
        }
    }

    public void matchesPreq(String currentWord) {
        incorrectGuessesIndexes.clear();
        correctGuessesIndexes.clear();

        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == ' ') {
                incorrectGuessesIndexes.add(i);
            } else {
                correctGuessesIndexes.add(i);
            }
        }
    }
    
    private ArrayList<Integer> correctGuessesIndexes = new ArrayList<Integer>();
    private ArrayList<Integer> incorrectGuessesIndexes = new ArrayList<Integer>();

    public boolean matches(String word, String currentWord) {
        for (int i = 0; i < correctGuessesIndexes.size(); i++) {
            if (word.charAt(correctGuessesIndexes.get(i)) != currentWord.charAt(correctGuessesIndexes.get(i))) {
                return false;
            }
        }

        for (int i = 0; i < incorrectGuessesIndexes.size(); i++) {
            for (int j = 0; j < correctGuessedLetters.length(); j++) {
                if (correctGuessedLetters.charAt(j) == word.charAt(incorrectGuessesIndexes.get(i))) {
                    return false;
                }
            }

            for (int j = 0; j < incorrectGuessedLetters.length(); j++) {
                if (incorrectGuessedLetters.charAt(j) == word.charAt(incorrectGuessesIndexes.get(i))) {
                    return false;
                }
            }
        }

        return true;
    }
}