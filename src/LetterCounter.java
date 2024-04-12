// used to count the number of letters in each word
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class LetterCounter {
   private record LetterCount(char letter, int count) {
   }
   public static void main(String[] args) {
      long time = System.currentTimeMillis();
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
      ArrayList<ArrayList<LetterCount>> letterCounts = new ArrayList<>(24);
      for (int i = 0; i < 26 ; i++) {
         letterCounts.add(new ArrayList<>());
      }
      // initialize the arraylist
      for (int i = 0; i < 24; i++) {
         for(int j = 0; j < 26; j++) {
            letterCounts.get(i).add(new LetterCount((char)('a' + j), 0));
         }
      }
      String line;
      try {
         while ((line = reader.readLine()) != null) {
            line = line.toLowerCase();
            // line is going to be 1 word
            int index = line.length()-1;
            // remove duplicate letters from the word
            HashSet<Character> set = new HashSet<Character>();
            for (int i = 0; i < line.length(); i++) {
               set.add(line.charAt(i));
            }

            // count the number of letters in the word and store it in the arraylist
            for (char c : set) {
               letterCounts.get(index).set(c - 'a', new LetterCount(c, letterCounts.get(index).get(c - 'a').count + 1));
            }
      
         }

      } catch (IOException e) {
         e.printStackTrace();
      }
      System.out.println();
      int[] totals = new int[24];
      totals[0] = 52;
      totals[1] = 155;
      totals[2] = 1351;
      totals[3] = 5110;
      totals[4] = 9987;
      totals[5] = 17477;
      totals[6] = 23734;
      totals[7] = 29926;
      totals[8] = 32380;
      totals[9] = 30867;
      totals[10] = 26011;
      totals[11] = 20460;
      totals[12] = 14938;
      totals[13] = 9762;
      totals[14] = 5924;
      totals[15] = 3377;
      totals[16] = 1813;
      totals[17] = 842;
      totals[18] = 428;
      totals[19] = 198;
      totals[20] = 82;
      totals[21] = 41;
      totals[22] = 17;
      totals[23] = 5;
      for (int i = 0; i < 24; i++) {
         System.out.println("Words of length " + (i+1) + ":");
         // sort the arraylist by the number of letters
         letterCounts.get(i).sort((a, b) -> b.count - a.count);
         for (LetterCount letterCount : letterCounts.get(i)) {
            System.out.print(letterCount.letter + ":" + letterCount.count + " ");
         }
         System.out.println();
         System.out.println("Probability of a word of length " + (i+1) + " containing a given letter: ");
         for (LetterCount letterCount : letterCounts.get(i)) {
            System.out.printf("%c: %.2f%% ", letterCount.letter, (double)letterCount.count / totals[i] * 100.0);
            System.out.println();
         }
         System.out.println();
      }
      System.out.println("Time taken: " + (System.currentTimeMillis() - time) + "ms");
   }
}
