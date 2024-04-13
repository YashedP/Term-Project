import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

   public static void main(final String[] args) throws FileNotFoundException {
      // long startTime = System.nanoTime();
      // HangmanPlayer testPlayer = new HangmanPlayer("words.txt");
      // long endTime = System.nanoTime();
      // System.out.println("Pre-Processed!");
      // System.out.printf("Execution Time: %dms%n", (endTime - startTime) / 1000000);

      // String testRegexPattern = "^(?!.*[b]).ell$";
      // // String testRegexExclude = "^[^bh]{4}";

      // Matcher matcherPattern = Pattern.compile(testRegexPattern).matcher("");
      // // Matcher matcherExclude = Pattern.compile(testRegexExclude).matcher("");

      // if (matcherPattern.reset("bell").matches()) {
      //    System.out.println("bell matches!");
      // }

      // if (matcherPattern.reset("hell").matches()) {
      //    System.out.println("hell matches!");
      // }

      // if (matcherPattern.reset("sell").matches()) {
      //    System.out.println("sell matches!");
      // }

      // if (matcherPattern.reset("that").matches()) {
      //    System.out.println("that matches!");
      // }


      // Yash's test work
      HangmanPlayer testPlayer = new HangmanPlayer("words.txt");
      String word = "Nambe";
      System.out.println(testPlayer.guess("     ", true));
      System.out.println(testPlayer.guess(" a   ", false));
   }
}
