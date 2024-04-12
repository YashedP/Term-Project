import java.io.FileNotFoundException;

public class Test {

   public static void main(final String[] args) throws FileNotFoundException {
      HangmanPlayer testPlayer = new HangmanPlayer("words.txt");
      System.out.println("Pre-Processed!");
   }
}
