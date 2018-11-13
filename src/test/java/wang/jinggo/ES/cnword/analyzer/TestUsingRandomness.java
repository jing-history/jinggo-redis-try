package wang.jinggo.ES.cnword.analyzer;
import java.util.Random;

import org.junit.Test;

import com.carrotsearch.randomizedtesting.RandomizedRunner;
import com.carrotsearch.randomizedtesting.RandomizedTest;

import static org.junit.Assert.*;

/**
 * So far we haven't really used the {@link Random} provided by
 * {@link RandomizedRunner}. The idea behind randomized tests is to, for each
 * test execution:
 * <ul>
 * <li>cover a possibly different execution path of the tested component,</li>
 * <li>cover a different data (input) passed to the tested component,
 * <li>
 * <li>execute in a different "environment" if there is environment variability.
 * </li>
 * </ul>
 * 
 * <p>
 * Let's see this on a simple example of a method that adds two
 * integers ({@link Adder#add(int, int)}). We can test this method using a "fixed" test
 * case as shown in {@link #fixedTesting} but this test will always execute in
 * an identical way (which is good if you're looking for regression coverage but
 * bad if you want to expand your tested domain).
 * 
 * <p>
 * A randomized test, on the other hand, will pick parameters from a larger
 * spectrum of values and assert on the method's contract. Here, we can make
 * sure the sum is always larger or equal than the arguments given two positive
 * arguments. This assertion will fail quite often because of integer overflows as shown
 * in {@link #randomizedTesting()} (re-run the test a few times if it doesn't
 * fail the first time).
 * 
 * <p>
 * While the above example is trivial most of the bugs in code stem from similar
 * subtleties (possibly resulting from more complex interactions). In many cases
 * the "contract" that we can assert on can be stated as "throw no-exception" given
 * valid arguments. An example of that is in method {@link #expectNoException()}.
 * At first glance this method will work most of the time, but try passing
 * {@link Integer#MIN_VALUE} as a random number and see what will happen.
 */
public class TestUsingRandomness extends RandomizedTest {

  @Test
  public void expectNoException() {
    String [] words = {"oh", "my", "this", "is", "bad."};
    
    // This will pick a random word from the array above...
    System.out.println(words[Math.abs(randomInt()) % words.length]);
  }
}
