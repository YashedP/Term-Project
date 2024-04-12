import java.io.FileNotFoundException;

public class Test {

   public static void main(final String[] args) throws FileNotFoundException {
      long startTime = System.nanoTime();
      HangmanPlayer testPlayer = new HangmanPlayer("words.txt");
      long endTime = System.nanoTime();
      System.out.println("Pre-Processed!");
      System.out.printf("Execution Time: %dms", (endTime - startTime) / 1000000);
   }
}
