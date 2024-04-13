import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(final String[] args) throws FileNotFoundException {
        long startTime = System.nanoTime();
        HangmanPlayer testPlayer = new HangmanPlayer("words.txt");
        long endTime = System.nanoTime();
        System.out.println("Pre-Processed!");
        System.out.printf("Execution Time: %dms%n", (endTime - startTime) / 1000000);

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

        System.out.println("\nThe word is \"nambe\"");
        double totalTime = 0;
        double deltaTime = 0;

        startTime = System.nanoTime();
        char letter1 = testPlayer.guess("     ", true);
        // System.out.println(testPlayer.guess("     ", true));
        endTime = System.nanoTime();
        deltaTime = (endTime - startTime) / 1000000.0;
        System.out.println("Guess 1: " + letter1 + ", time:" + deltaTime);
        totalTime += deltaTime;

        testPlayer.feedback(true, " a   ");

        startTime = System.nanoTime();
        char letter2 = testPlayer.guess(" a   ", false);
        // System.out.println(testPlayer.guess(" a   ", false));
        endTime = System.nanoTime();
        deltaTime = (endTime - startTime) / 1000000.0;
        System.out.println("Guess 2: " + letter2 + ", time:" + deltaTime);
        totalTime += deltaTime;

        testPlayer.feedback(false, " a   ");

        startTime = System.nanoTime();
        char letter3 = testPlayer.guess(" a   ", false);
        // System.out.println(testPlayer.guess(" a   ", false));
        endTime = System.nanoTime();
        deltaTime = (endTime - startTime) / 1000000.0;
        System.out.println("Guess 3: " + letter3 + ", time:" + deltaTime);
        totalTime += deltaTime;

        testPlayer.feedback(true, "na   ");

        startTime = System.nanoTime();
        char letter4 = testPlayer.guess("na   ", false);
        // System.out.println(testPlayer.guess("na   ", false));
        endTime = System.nanoTime();
        deltaTime = (endTime - startTime) / 1000000.0;
        System.out.println("Guess 4: " + letter4 + ", time:" + deltaTime);
        totalTime += deltaTime;

        testPlayer.feedback(true, "na  e");

        startTime = System.nanoTime();
        char letter5 = testPlayer.guess("na  e", false);
        // System.out.println(testPlayer.guess("na  e", false));
        endTime = System.nanoTime();
        deltaTime = (endTime - startTime) / 1000000.0;
        System.out.println("Guess 5: " + letter5 + ", time:" + deltaTime);
        totalTime += deltaTime;

        testPlayer.feedback(true, "na be");

        startTime = System.nanoTime();
        char letter6 = testPlayer.guess("na be", false);
        // System.out.println(testPlayer.guess("na be", false));
        endTime = System.nanoTime();
        deltaTime = (endTime - startTime) / 1000000.0;
        System.out.println("Guess 6: " + letter6 + ", time:" + deltaTime);
        totalTime += deltaTime;

        System.out.println("totalTime: " + totalTime);
        System.out.println("Average guess time: " + totalTime / 6);
        // LETS FUCKING GOOO IT FUCKING WOKRED, LETS FUCKING GOOOOOOOOOOOOOOOOOOO
    }
}
